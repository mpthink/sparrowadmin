/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author map6
 */
//@RunWith(SpringRunner.class)
//@WebMvcTest(SysUserController.class)
public class SysUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/system/sysUser/hello")).andExpect(status().isOk()).andDo(print());
    }


}
