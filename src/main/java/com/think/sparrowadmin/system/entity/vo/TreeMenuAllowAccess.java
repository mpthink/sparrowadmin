package com.think.sparrowadmin.system.entity.vo;

import com.think.sparrowadmin.system.entity.SysMenu;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 菜单树+是否有权限表示
 *
 * @author map6
 */
@Data
@ToString
public class TreeMenuAllowAccess implements Serializable {

    /**
     * @Fields serialVersionUID : TODO()
     */

    private static final long serialVersionUID = 1L;

    /**
     * 菜单
     */
    private SysMenu sysMenu;
    /**
     * 是否允许访问
     */
    private boolean allowAccess = false;
    /**
     * 子菜单
     */
    private List<TreeMenuAllowAccess> children = new ArrayList<>();
}
