 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.zking.controller;
 

 import com.alibaba.fastjson.JSONObject;
 import com.zking.entity.User;
 import com.zking.service.PrivateWordService;
 import com.zking.service.UserService;
 import com.zking.util.ResultModel;
 import com.zking.util.ResultTools;
 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiOperation;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 import javax.servlet.http.HttpServletRequest;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;


 /**
  * @auther chendesheng
  * @date 2019/1/10
  */
 @Api(value = "用户操作",description = "详细描述")
 @RestController
 @RequestMapping(value = "/user")
 public class UserController {
     @Autowired
     UserService userService;
     @Autowired
     PrivateWordService privateWordService;
    
     /**
      * 根据用户名查找用户ID
      * @param username
      * @return
      */
    @ApiOperation(value = "根据用户名查找用户ID",notes = "获取用户ID") 
    @PostMapping(value = "/findUserIdByUsername")
    public ResultModel findUserIdByUsername(String username){
        try {
            int userId = userService.findUserIdByUsername(username);
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("content",userId);
            return ResultTools.result(200,"",map);
        }catch (Exception e){
            
            return ResultTools.result(404,e.getMessage(),null);
        }
    }
    
     /**
      * 发布悄悄话
      * @param privateWord 悄悄话内容
      */
     @ApiOperation(value = "发布悄悄话",notes = "发布悄悄话")
     @PostMapping(value = "/sendPrivateWord")
     public ResultModel sendPrivateWord(String privateWord, HttpServletRequest request){
         String username = request.getSession().getAttribute("userName").toString();
         try {
             return privateWordService.publishPrivateWord(privateWord,username);
             
         }catch (Exception e){
             return ResultTools.result(404,e.getMessage(),null);
         }
         
     }
    
     /**
      * 安全设置更新用户名和密码
      * @param username 用户名
      * @param username 密码
      */
      @ApiOperation(value = "安全设置更新用户名和密码",notes = "用户名和密码不能为空")
      @PostMapping(value = "/updateUsernameAndPassword")
      public ResultModel updateUsernameAndPassword(String username, String password,HttpServletRequest request){
         String userName = request.getSession().getAttribute("userName").toString();
         int userId = userService.findUserIdByUsername(userName);
         try {
             return userService.updateByPrimaryKeySelective(username,password,userId);
        
         }catch (Exception e){
             return ResultTools.result(404,e.getMessage(),null);
         }
    
     }
     /**
      * 保存个人信息
      * @param user 用户
      */
     @ApiOperation(value = "保存个人信息",notes = "保存个人信息")
     @PostMapping(value = "/savePersonalDate")
     public ResultModel savePersonalDate(@RequestBody User user, HttpServletRequest request){
         
         String userName = request.getSession().getAttribute("userName").toString();
         try {
             return userService.savePersonalDate(user,userName);
         }catch (Exception e){
             return ResultTools.result(404,e.getMessage(),null);
         }
    
     }
    
     /**
      * 获取个人信息
      * @param request
      * @return
      */
     @ApiOperation(value = "获取个人信息",notes = "获取个人信息")
     @GetMapping(value = "/getUserPersonalInfo")
     public ResultModel getUserPersonalInfoByUsername(HttpServletRequest request){
    
         String userName = request.getSession().getAttribute("userName").toString();
         try {
             return userService.getUserPersonalInfoByUsername(userName);
         }catch (Exception e){
             return ResultTools.result(404,e.getMessage(),null);
         }
    
     }
     
     
     /**
      * 更新个人信息
      * @param user 用户
      */
     @ApiOperation(value = "更新个人信息",notes = "更新个人信息")
     @PostMapping(value = "update")
     public JSONObject updateByPrimaryKey(@RequestBody User user){
         JSONObject jsonObject = new JSONObject();
         try {
             int status = userService.updateByPrimaryKeySelective(user);
             jsonObject.put("msg:","更新成功");
             jsonObject.put("data",status);
             
         }catch (Exception e){
             e.printStackTrace();
             jsonObject.put("code:","500");
         }
         return jsonObject;
     }
    
     /**
      * 获得用户总数
      * @return
      */
     @ApiOperation(value = "获得用户总数",notes = "获得用户总数")
     @GetMapping("/countUserNum")
     public JSONObject countUserNum(){
         JSONObject jsonObject = new JSONObject();
         try {
             int num = userService.countUserNum();
             jsonObject.put("code:","200");
             jsonObject.put("data",num);
            
         }catch (Exception e){
             e.printStackTrace();
             jsonObject.put("code:","500");
         }
         return jsonObject;
     }
    
     /**
      * 通过用户名查找ID
      * @param userName
      * @return
      */
     @ApiOperation(value = "通过用户名查找ID",notes = "通过用户名查找ID")
     @PostMapping("/findIdByUserName")
     public JSONObject findIdByUserName(String userName){
         JSONObject jsonObject = new JSONObject();
         try {
             int id = userService.findIdByUsername(userName);
             jsonObject.put("code:","200");
             jsonObject.put("data",id);
            
         }catch (Exception e){
             e.printStackTrace();
             jsonObject.put("code:","500");
         }
         return jsonObject;
     }
 }
