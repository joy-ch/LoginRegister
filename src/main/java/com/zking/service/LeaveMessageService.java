 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.zking.service;

 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.zking.entity.LeaveMessageRecord;
 import com.zking.util.ResultModel;

 /**
  * @auther chendesheng
  * @date 2019/1/28
  */
 public interface LeaveMessageService {
    
      
     int insertLeaveMessage(String leaveMessageContent, String pageName, String answerer);
     
 }
