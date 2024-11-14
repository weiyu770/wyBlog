package com.wy.wydemo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.mapper.MenuMapper;
import com.wy.wydemo.mapper.RoleMenuMapper;
import com.wy.wydemo.model.entity.Menu;
import com.wy.wydemo.model.entity.RoleMenu;
import com.wy.wydemo.model.vo.query.MenuQuery;
import com.wy.wydemo.model.vo.request.MenuReq;
import com.wy.wydemo.model.vo.response.MenuOptionResp;
import com.wy.wydemo.model.vo.response.MenuResp;
import com.wy.wydemo.model.vo.response.MenuTreeResp;
import com.wy.wydemo.service.MenuService;
import com.wy.wydemo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @class: MenuServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 15:05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    
    @Override
    public List<MenuResp> listMenuVO(MenuQuery menuQuery) {
        // 查询当前菜单列表
        List<MenuResp> menuRespList = menuMapper.selectMenuVOList(menuQuery);
        // 当前菜单id列表
        Set<Integer> menuIdList = menuRespList.stream()
                .map(MenuResp::getId)
                .collect(Collectors.toSet());
        return menuRespList.stream().map(menuVO -> {
            Integer parentId = menuVO.getParentId();
            // parentId不在当前菜单id列表，说明为父级菜单id，根据此id作为递归的开始条件节点
            if (!menuIdList.contains(parentId)) {
                menuIdList.add(parentId);
                return this.recurMenuList(parentId, menuRespList);
            }
            return new ArrayList<MenuResp>();
        }).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }
    
    /**
     * 递归生成菜单列表
     *
     * @param parentId 父菜单id
     * @param menuList 菜单列表
     * @return 菜单列表
     */
    private List<MenuResp> recurMenuList(Integer parentId, List<MenuResp> menuList) {
        return menuList.stream()
                .filter(menuVO -> menuVO.getParentId().equals(parentId))
                .peek(menuVO -> menuVO.setChildren(recurMenuList(menuVO.getId(), menuList)))
                .collect(Collectors.toList());
    }
    
    /**
     * 添加菜单
     * @param menu
     */
    @Override
    public void addMenu(MenuReq menu) {
        // 名称是否存在
        Menu existMenu = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId)
                .eq(Menu::getMenuName, menu.getMenuName()));
        Assert.isNull(existMenu, menu.getMenuName() + "菜单已存在");
        Menu newMenu = BeanCopyUtils.copyBean(menu, Menu.class);
        baseMapper.insert(newMenu);
    }
    
    /**
     * 删除菜单
     * @param menuId
     */
    @Override
    public void deleteMenu(Integer menuId) {
        // 菜单下是否存在子菜单
        Long menuCount = menuMapper.selectCount(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getParentId, menuId));
        Assert.isFalse(menuCount > 0, "存在子菜单");
        // 菜单是否已分配
        Long roleCount = roleMenuMapper.selectCount(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getMenuId, menuId));
        Assert.isFalse(roleCount > 0, "菜单已分配");
        // 删除菜单
        menuMapper.deleteById(menuId);
    }
    
    
    /**
     * 修改菜单
     * @param menu
     */
    @Override
    public void updateMenu(MenuReq menu) {
        // 名称是否存在
        Menu existMenu = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId)
                .eq(Menu::getMenuName, menu.getMenuName()));
        Assert.isFalse(Objects.nonNull(existMenu) && !existMenu.getId().equals(menu.getId()),
                menu.getMenuName() + "菜单已存在");
        Menu newMenu = BeanCopyUtils.copyBean(menu, Menu.class);
        baseMapper.updateById(newMenu);
    }
    
    /**
     * 获取菜单下拉项
     * @return
     */
    @Override
    public List<MenuTreeResp> listMenuTree() {
        List<MenuTreeResp> menuTreeRespList = menuMapper.selectMenuTree();
        return this.recurMenuTreeList(CommonConstant.PARENT_ID, menuTreeRespList);
    }
    
    
    /**
     * 查询菜单选项树
     * @return
     */
    @Override
    public List<MenuOptionResp> listMenuOption() {
        List<MenuOptionResp> menuOptionList = menuMapper.selectMenuOptions();
        return recurMenuOptionList(CommonConstant.PARENT_ID, menuOptionList);
    }
    
    
    /**
     * 编辑菜单
     * @param menuId
     * @return
     */
    @Override
    public MenuReq editMenu(Integer menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    
    /**
     * 递归生成菜单下拉树
     *
     * @param parentId         父菜单id
     * @param menuTreeRespList 菜单树列表
     * @return 菜单列表
     */
    private List<MenuTreeResp> recurMenuTreeList(Integer parentId, List<MenuTreeResp> menuTreeRespList) {
        return menuTreeRespList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(recurMenuTreeList(menu.getId(), menuTreeRespList)))
                .collect(Collectors.toList());
    }
    
    /**
     * 递归生成菜单选项树
     *
     * @param parentId       父菜单id
     * @param menuOptionList 菜单树列表
     * @return 菜单列表
     */
    private List<MenuOptionResp> recurMenuOptionList(Integer parentId, List<MenuOptionResp> menuOptionList) {
        return menuOptionList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(recurMenuOptionList(menu.getValue(), menuOptionList)))
                .collect(Collectors.toList());
    }
    
}
