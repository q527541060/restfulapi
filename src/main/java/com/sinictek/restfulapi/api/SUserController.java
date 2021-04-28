package com.sinictek.restfulapi.api;


import com.sinictek.restfulapi.model.SUser;
import com.sinictek.restfulapi.model.apiResponse.ApiResponse;
import com.sinictek.restfulapi.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/sUser")
public class SUserController {

    @Autowired
    SUserService sUserService;

    @GetMapping("getUser")
    @ResponseBody
    public ApiResponse getTestUser(){
        return new ApiResponse(true,"",sUserService.getTestAllUser());
    }

    @PostMapping("addUser")
    @ResponseBody
    public ApiResponse addTestUser(@RequestBody SUser sUser){

        sUser.setCreate_time("21000101");
        return new ApiResponse(true,"",sUserService.addTestAllUser(sUser));
    }
}

