
    var downloadFile = function (obj) {
        console.log(obj);
        console.log("click downloadFile");
        var filename = $(obj).find("td:eq(0)").text();
        var filepath = $(obj).find("td:eq(2)").text();
        var directory = $(obj).find("td:eq(3)").text();
        filepath = filepath.replace(/\\/g,"/");
        console.log(directory);
        if(directory=='false'){
            console.log("is file");
            // var tu = window.confirm("are you true download now ?");
            if(true){

                var listStr = localStorage.getItem("downloadedList");
                if(listStr==null){
                    listStr = "";
                }
                listStr += filepath;
                listStr += " &nbsp;&nbsp;(download date:"
                listStr += new Date();
                listStr += " )";
                listStr += ",";
                localStorage.setItem("downloadedList",listStr);
                window.location="/FilesServlet?action=download&filename="+filename+"&path="+filepath;
                listStr = localStorage.getItem("downloadedList");
                listStr = listStr.substring(0,listStr.length-1);
                var listd = listStr.split(",");
                $("#downloaded").html("");
                $.each(listd,function (i,v) {
                    console.log(i+" : "+v);
                    $("#downloaded").append("<li style='margin-top: 10px;'>"+v+"</li>");
                })

            }else{
                return;
            }

        }else{
            console.log("Is not a file");
        }
    }
    var showNextList = function(obj){

        console.log("click showNextList");
        var directory = $(obj).find("td:eq(3)").text();
        console.log(directory);
        if(directory=='false'){
            return;
        }else{
            console.log("is isDirectory")
            var path = $(obj).find("td:eq(2)").text();
            console.log("click showNextList path : "+path);
            filelistMethod(path);
        }

    };
    var filelistMethod = function(path){
        $.ajax({
            url:"/FilesServlet",
            method:"POST",
            dataType:"json",
            data:{
                action:"showfiles",
                path:path
            },
            success:function (data) {
                console.log(data);

                var list = data.list;
                var html = "";

                $.each(list,function (i,v) {
                    // if(i%2==0){
                    //     html+="<tr  onclick='showNextList(this)' class='tr_color'>";
                    // }else{
                    //     html+="<tr  onclick='showNextList(this)' class='tr_color_dubbo'>";
                    // }
                    html+="<tr  onclick='showNextList(this)'>";
                    if(v.directory){
                        html+="<td style='color: blue;'>"
                    }else{
                        html+="<td>"
                    }
                    html+=v.fileName;
                    html+="</td>";

                    html+="<td>";
                    html+=v.fileSize;
                    html+="</td>";

                    html+="<td>";
                    html+=v.filePath;
                    html+="</td>";

                    html+="<td>";
                    html+=v.directory;
                    html+="</td>";


                    html+="</tr>";
                });

                $("#fileList_tb").html(html);
                $("#pwd").val(data.path.replace(/\\/g,"/"));
                $("#filelisttb").xLily6X($.widget.Chameleon);
            }
        });
    };


    var fileOnSelect = function (obj) {
        var path = $("#pwd").val();
        console.log($("#pwd"));
        console.log("lily file on select path ");
        console.log(path);
        $("#uploadpath").val(path);

        console.log(obj);
        console.log("file on select");
        var val = $(obj).val();
        console.log(val);


        var listStr = localStorage.getItem("uploadedList");
        if(listStr==null){
            listStr = "";
        }
        listStr += path;
        listStr += " &nbsp;&nbsp;";
        listStr += val;
        listStr += " &nbsp;&nbsp;(upload date:"
        listStr += new Date();
        listStr += " )";
        listStr += ",";
        localStorage.setItem("uploadedList",listStr);
        sessionStorage.setItem("path",path);
        $("#uploadformbtn").click();

    }


    var mousemove=function(oEvent){
        if(window.event){
            oEvent=window.event;
        }
        //显示鼠标的横纵坐标
        var text=oEvent.clientX+","+oEvent.clientY;
        // console.log(text);
        $("#menu").css("top",oEvent.clientY);
        $("#menu").css("left",oEvent.clientX);
        $("#menu").html(text);
        return text
    }

    var contextMenu = function () {
        // $("#menu").xlily6x("sayhi",{'name':'xiaomi'})

        return;
        console.log("context menu");

        if(window.event){
            oEvent=window.event;
        }
        //显示鼠标的横纵坐标
        var text=oEvent.clientX+","+oEvent.clientY;
        console.log(oEvent);
        $("#menu").css("top",oEvent.clientY);
        $("#menu").css("left",oEvent.clientX);
        $("#menu").html(text);
        $("#menu").show();
    }
    var mouseout = function () {
        $("#menu").hide();
    }


    // windows on load
    $(function(){

        var path = sessionStorage.getItem("path");
        path = path==null?"":path;
        filelistMethod(path);
        // $("#pwd").blur(function () {
        //     filelistMethod($(this).val());
        // });
        $("#pwd").keydown(function (event) {
            if(event.which==13){
                filelistMethod($(this).val());
            }

        });

        $("#return").click(function () {
            console.log("click the return");
            var oldpath = $("#pwd").val();
            var path = oldpath;
            console.log(path);
            console.log(path.lastIndexOf("/"));
            path = path.substring(0,path.lastIndexOf("/"));
            console.log(path);
            if(path.lastIndexOf("/")<0){
                console.log("Is base directory");
                path = path+"/";
            }
            console.log(path);
            filelistMethod(path);
        });


        $("#upload").click(function () {
            var message =  window.prompt();
            console.log(message);
            if(message==asd){
                $("#fileinput").click();
            }

        })

        $("#fileinput").bind("change",function () {
            console.log("select chanage");
            fileOnSelect(this);
        })

        var listStr = localStorage.getItem("uploadedList");
        if(listStr!=null){
            listStr = listStr.substring(0,listStr.length-1);
            var list = listStr.split(",");
            $("#uploaded").html("");
            $.each(list,function (i,v) {
                console.log(i+" : "+v);
                $("#uploaded").append("<li style='margin-top: 10px;'>"+v+"</li>");
            })
        };
        $("#cleanHis").click(function () {
            $("#uploaded").html("");
            localStorage.removeItem("uploadedList");
        });

        var listDStr = localStorage.getItem("downloadedList");
        if(listDStr!=null){
            listDStr = listDStr.substring(0,listDStr.length-1);
            var listd = listDStr.split(",");
            $("#downloaded").html("");
            $.each(listd,function (i,v) {
                console.log(i+" : "+v);
                $("#downloaded").append("<li style='margin-top: 10px;'>"+v+"</li>");
            })
        };
        $("#cleanDHis").click(function () {
            $("#downloaded").html("");
            localStorage.removeItem("downloadedList");
        });

        $("#ftp_div").xLily6X($.widget.ContextMenu,{
            items:[
                {id:"downloadBtn",text:"Download Now",click:function (obj,env) {
                        var $obj = $(obj).closest("tr");
                        console.log($obj.index());
                        console.log($(obj).closest("tr").length>0);
                        console.log($(obj).closest("tbody").length>0);
                        if($obj.length>0 && $(obj).closest("tbody").length>0){
                            console.log($obj);
                            var message =  window.prompt();
                            console.log(message);
                            if(message==asd){
                                downloadFile($obj);
                                console.log(obj);console.log(env);
                            }

                        }else{
                            console.log("In not a file");
                        }

                    }
                }

            ],
            auto_height:true,
            background_color:"#fbf8ff",
            save:function (obj) {
                console.log("options.save");
            }
        });

        var asd = "xiaowenlong";

    });