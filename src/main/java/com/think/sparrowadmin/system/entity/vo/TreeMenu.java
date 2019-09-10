package com.think.sparrowadmin.system.entity.vo;

import com.think.sparrowadmin.system.entity.SysMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树
 *
 * @author map6
 */
public class TreeMenu implements Serializable {

    /**
     * @Fields serialVersionUID : TODO()
     */

    private static final long serialVersionUID = 1L;
    /**
     * 菜单
     */
    private SysMenu sysMenu;
    /**
     * 子菜单
     */
    private List<TreeMenu> children = new ArrayList<>();

    public SysMenu getSysMenu() {
        return sysMenu;
    }

    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
    }

    public List<TreeMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TreeMenu> children) {
        this.children = children;
    }


}
