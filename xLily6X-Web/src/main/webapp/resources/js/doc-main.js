$(function () {
    //首页背景自动更换
    setTimeout(function () {
        setInterval(backgroundRuning("top-section"),20000);
    },2000);
//
    //滚动条滚动锁定左边菜单工具栏
    $(document).scroll(function(){
        var scrollTop = $(this).scrollTop();
//            console.log(scrollTop+", "+$("#top-section").height());
//            window.sessionStorage.setItem("scrollTop",scrollTop);
        $(".sidebar").css({"position":"static"});
        if(scrollTop>$("#top-section").height()&&scrollTop>$(".sidebar").height()){
            $(".sidebar").css({"position":"fixed","top":0});
        }
    });

//        $(document).scrollTop(window.sessionStorage.getItem("scrollTop"));


    var height = document.documentElement.clientHeight;
    var width = document.documentElement.clientWidth;
    $("#top-section").css({"height":height/2,"width":width});
    $(".cover-main").css({"top":150,"left":500});

    // 初始化加载页面  quick-get-start
    var page = window.sessionStorage.getItem("page");
    page = page==undefined||page==null||page==""?"brief-introduction":page;
    page = page+".html";
    console.log("on load");
    console.log(page);
    // 页面跳转
    go(page);


    // 处理菜单请求
    $(".sidebar").find("li a").bind("click",function () {
        $(this).parent().siblings().find("ul").hide();
        var $obj = $(this);
        var href = $obj.attr("href");
        console.log(href);
        if(href.indexOf("/")<=0){
            // console.log("Has / ");
            return;
        }
        href = href.substr(2,href.length);
        window.sessionStorage.setItem("page",href);
        console.log(href);
        var page = href+".html";
        go(page);
    });

    $("#homeLink").click(function () {
        console.log("homeLink");
        window.sessionStorage.setItem("page","");
    })

    console.log($(".sidebar-nav").children("ul").children("li").children("ul").children("li").children("ul"));

//        $("#mainUl").children("li").hide();
    $(".sidebar-nav").children("ul").children("li").children("ul").children("li").children("ul").hide();
    $(".sidebar .sidebar-nav a").click(function () {
        console.log($(".sidebar").find("li"));

//            console.log($(this).parent().find("ul").length);
        $(this).parent().find("ul").show();
        $(".sidebar").find("li").removeClass("selected");
        $(this).parent().addClass("selected");
    })
})

/**
 * 页面跳转
 * @param page
 */
var go = function (page) {
    var timestamp = (new Date()).valueOf();
    $.ajax({
        url:""+page+"?time="+timestamp,
        cache:false,
        success:function (data) {
            $("#main").html(data);
            $.Xlily6X();
            $(document).scrollTop($("#top-section").height());
        }
    });
}
