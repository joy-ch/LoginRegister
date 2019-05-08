
var tag="";

//添加所有标签
function putInAllTags(data) {
    var allTags = $('.allTags');
    allTags.empty();
    allTags.append($('<div class="allTagsTitle">' +
        '<h2 style="font-size: 20px">Tags</h2>' +
        '</div>'));
    allTags.append($('<div class="allTagsNum">' +
        '目前共计 <span class="num" style="font-size: 20px">' + data['tagsNum'] + '</span> 个标签' +
        '</div>'));
    var allTagsCloud = $('<div class="allTagsCloud categories-comment am-animation-slide-top"></div>');
    $.each(data['result'], function (index, obj) {
        allTagsCloud.append($('<a href="/front/tags?tag=' + obj['tagname'] + '" style="font-size:' + obj['tagSize'] + 'px">' + obj['tagname'] + '</a>'));
    });
    allTags.append(allTagsCloud);
}


//添加该标签的所有文章信息
function putInTagArticleInfo(data) {
    var siteInner = $('.site-inner');
    siteInner.empty();
    var timeLine = $('<div class="timeline timeline-wrap"></div>');
    timeLine.append($('<div class="timeline-row">' +
        '<span class="node" style="-webkit-box-sizing: content-box;-moz-box-sizing: content-box;box-sizing: content-box;">' +
        '<i class="fa fa-tag"></i>' +
        '</span>' +
        '<h1 class="title  am-animation-slide-top"># '+ data['tag'] + '</h1>' +
        '</div>'));
    $.each(data['result'],function (index,obj) {

        var timelineRowMajor = $('<div class="timeline-row-major"></div>');
        timelineRowMajor.append($('<span class="node am-animation-slide-top am-animation-delay-1"></span>'));
        var content = $('<div class="content am-comment-main am-animation-slide-top am-animation-delay-1"></div>');
        content.append($('<header class="am-comment-hd" style="background: #fff">' +
            '<div class="contentTitle am-comment-meta">' +
            '<a href="detail/' + obj['id']  +'">' + obj['articleTitle'] + '</a>' +
            '</div>' +
            '</header>'));
        var amCommentBd = $('<div class="am-comment-bd"></div>');
        amCommentBd.append($('<i class="fa fa-calendar"> <a href="/archives?archive=' + obj['publishDate'] + '">' + obj['publishDate'] + '</a></i>' +
            '<i class="fa fa-folder"> <a href="/categories?category=' + obj['articleCategories'] + '">' + obj['articleCategories'] + '</a></i>'));
        var amCommentBdTags = $('<i class="am-comment-bd-tags fa fa-tag"></i>')
        for(var i=0;i<obj['articleTags'].length;i++){
            var tag = $('<a href="/tags?tag=' + obj['articleTags'][i] + '">' + obj['articleTags'][i] + '</a>');
            amCommentBdTags.append(tag);
            if(i != (obj['articleTags'].length-1)){
                amCommentBdTags.append(",");
            }
        }
        amCommentBd.append(amCommentBdTags);
        content.append(amCommentBd);
        timelineRowMajor.append(content);
        timeLine.append(timelineRowMajor);
    });

    siteInner.append(timeLine);
    siteInner.append($('<div class="my-row" id="page-father">' +
        '<div id="pagination">' +
        '<ul class="am-pagination  am-pagination-centered">' +
        '<li class="am-disabled"><a href="#">&laquo; 上一页</a></li>' +
        '<li class="am-active"><a href="#">1</a></li>' +
        '<li><a href="#">2</a></li>' +
        '<li><a href="#">3</a></li>' +
        '<li><a href="#">4</a></li>' +
        '<li><a href="#">5</a></li>' +
        '<li><a href="#">下一页 &raquo;</a></li>' +
        '</ul>' +
        '</div>' +
        '</div>'));

}

$.ajax({
    type: 'HEAD', // 获取头信息，type=HEAD即可
    url : window.location.href,
    async:false,
    success:function (data, status, xhr) {
        
        tag = xhr.getResponseHeader("tag");
    }
});

//获取url参数tag的值
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

console.log("tag：", getQueryString("tag"));
tag = getQueryString("tag");


//加载tags页时请求
function ajaxFirst(currentPage) {

    $.ajax({
        type:'post',
        url:'../tags/getTagArticle',
        dataType:'json',
        data:{
            tag:tag,
            rows:"10",
            pageNum:currentPage
        },
        success:function (data) {
            if(data['status'] == 200){
                putInAllTags(data);
            } else {
                putInTagArticleInfo(data);
                scrollTo(0,0);//回到顶部
                //分页
                $("#pagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        ajaxFirst(currentPage);
                    }
                });
            }
        },
        error:function () {
            alert("获取标签文章失败");
        }
    });
}
ajaxFirst(1);