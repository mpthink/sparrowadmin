/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.system.service;

import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author map6
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class SysUserServiceTest {

    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void testUpdateUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        sysUser.setPassword("123456");
        sysUser.setGmtCreate(LocalDateTime.now());
        sysUser.setGmtModified(LocalDateTime.now());
        sysUserService.insertUser(sysUser, null);
    }
}
