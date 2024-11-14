package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Menu;
import com.wy.wydemo.model.vo.query.MenuQuery;
import com.wy.wydemo.model.vo.request.MenuReq;
import com.wy.wydemo.model.vo.response.MenuOptionResp;
import com.wy.wydemo.model.vo.response.MenuResp;
import com.wy.wydemo.model.vo.response.MenuTreeResp;
import com.wy.wydemo.model.vo.response.UserMenuResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: MenuMapper
 * @author: yu_wei
 * @create: 2024/11/04 18:13
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    
    /**
     * 根据用户id查询用户菜单列表
     *
     * @param userId 用户id
     * @return 用户菜单列表
     */
    List<UserMenuResp> selectMenuByUserId(@Param("userId") Long userId);
    
    
    /**
     * 查询菜单列表
     *
     * @param menuQuery 菜单查询条件
     * @return 菜单列表
     */
    List<MenuResp> selectMenuVOList(@Param("param") MenuQuery menuQuery);
    
    /**
     * 查询菜单下拉树
     *
     * @return 菜单下拉树
     */
    List<MenuTreeResp> selectMenuTree();
    
    
    /**
     * 查询菜单选项树
     *
     * @return 菜单选项树
     */
    List<MenuOptionResp> selectMenuOptions();
    
    /**
     * 根据id查询菜单信息
     *
     * @param menuId 菜单id
     * @return 菜单
     */
    MenuReq selectMenuById(@Param("menuId") Integer menuId);
}
