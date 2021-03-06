 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.zking.service;

 import com.alibaba.fastjson.JSONObject;
 import com.zking.entity.Article;
 import net.sf.json.JSONArray;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.List;

 /**
  * @auther chendesheng
  * @date 2019/1/29
  */
 public interface ArticleService {
     /**
      * 插入文章
      * param record
      * @return
      */
     int insert(Article article);
     
     /**
      * 保存文章
      * @param article 文章
      * @return  status: 200--成功   500--失败
      */
     JSONObject insertArticle(Article article);
     /**
      * 通过文章id更新它的上一篇或下一篇文章id
      * @param lastOrNext
      * @param lastOrNextArticleId
      * @param articleId
      */
     void updateArticleLastOrNextId(String lastOrNext, long lastOrNextArticleId, long articleId);
     /**
      * 修改文章
      * @return
      */
     @Transactional
     JSONObject updateArticleById(Article article);
     /**
      * 分页获得该分类下的所有文章
      * @param category 分类名
      * @param rows 一页大小
      * @param pageNum 页数
      * @return
      */
     JSONObject findArticleByCategory(String category, int rows, int pageNum);
     /**
      * 计算该分类文章的数目
      * @param category 分类名
      * @return 该分类下文章的数目
      */
     int countArticleCategoryByCategory(String category);
     /**
      * 通过标签分页获得文章部分信息
      * @param tag
      * @return
      */
     JSONObject findArticleByTag(String tag, int rows, int pageNum);
     /**
      * 获得所有文章
      * @return
      */
     List<Article> listArticles();
    
     /**
      * 分页获取文章
      * @param rows
      * @param pageNO
      * @return
      */
     JSONArray findAllArticles(String rows, String pageNO);
     /**
      * 通过id获得文章
      * @return
      */
     Article getById(int id);
     /**
      * 获得文章总数
      * @return
      */
     int countArticleNum();
    
     /**
      * 获得分类名获得文章总数
      * @return
      */
     int countArticleByCategoryName(String categoryName);
     /**
      * 获得分类名获得文章总数
      * @return
      */
     int countArticleByTags(String tagsName);
     
     /**
      * 分页获得文章管理
      */
     JSONObject getArticleManagement(int rows, int pageNum);
     /**
      * 通过id删除文章
      * @param id 文章id
      * @return 1--删除成功  0--删除失败
      */
     @Transactional
     int deleteArticle(Integer id);
     /**
      * 通过categoryName删除文章
      * @param categoryName 文章id
      * @return 1--删除成功  0--删除失败
      */
     @Transactional
     int deleteArticleByCategoryName(String categoryName);
     
 }
