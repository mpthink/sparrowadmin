/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.system.mapper;

import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.mapper.SysUserMapper;
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
public class SysUserMapperTest {

    //https://blog.csdn.net/weixin_30945319/article/details/99139194
    //http://www.imooc.com/article/287865
    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    public void testInsertUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("mpthink");
        sysUser.setPassword("password");
        sysUser.setGmtCreate(LocalDateTime.now());
        sysUser.setGmtModified(LocalDateTime.now());

        boolean result = sysUser.insert();
        assert(result == true);
    }

}
