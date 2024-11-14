package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Menu;
import com.wy.wydemo.model.vo.query.MenuQuery;
import com.wy.wydemo.model.vo.request.MenuReq;
import com.wy.wydemo.model.vo.response.MenuOptionResp;
import com.wy.wydemo.model.vo.response.MenuResp;
import com.wy.wydemo.model.vo.response.MenuTreeResp;

import java.util.List;

/**
 * @description:
 * @class: MenuService
 * @author: yu_wei
 * @create: 2024/11/08 15:05
 */
public interface MenuService extends IService<Menu> {
    
    /**
     * 查看菜单列表
     * @param menuQuery
     * @return
     */
    List<MenuResp> listMenuVO(MenuQuery menuQuery);
    
    
    /**
     * 添加菜单
     * @param menu
     */
    void addMenu(MenuReq menu);
    
    /**
     * 删除菜单
     * @param menuId
     */
    void deleteMenu(Integer menuId);
    
    /**
     * 修改菜单
     * @param menu
     */
    void updateMenu(MenuReq menu);
    
    
    /**
     * 获取菜单下拉项
     * @return
     */
    List<MenuTreeResp> listMenuTree();
    
    
    /**
     * 获取菜单选项树
     * @return
     */
    List<MenuOptionResp> listMenuOption();
    
    
    /**
     * 编辑菜单
     * @param menuId
     * @return
     */
    MenuReq editMenu(Integer menuId);
}
