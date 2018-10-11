    var asd = "wenlongxiao";
    var downloadFile = function (obj) {
        console.log(obj);
        console.log("click downloadFile");
        var filename = $(obj).find("td:eq(0)").text();
        var filepath = $(obj).find("td:eq(2)").text();
        var directory = $(obj).find("td:eq(3)").text();
        filepath = filepath.replace(/\\/g,"/");
        console.log(directory);
        if(directory=='false'){
            var message =  window.prompt();
            console.log(message);
            // if(message!=asd){
            //     return;
            // }
            console.log("is file");
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
                window.location="/FilesServlet?action=download&filename="+filename+"&path="+filepath+"&access="+message;
                listStr = localStorage.getItem("downloadedList");
                listStr = listStr.substring(0,listStr.length-1);
                var listd = listStr.split(",");
                $("#downloaded").html("");
                $.each(listd,function (i,v) {
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
            console.log("is isDirectory");
            var path = $(obj).find("td:eq(2)").text();
            console.log("click showNextList path : "+path);
            filelistMethod(path);

        }

    };
    var filelistMethod = function(path){
        $("#pwd").val(path.replace(/\\/g,"/"));
        sessionStorage.setItem("path",path);
        $("#fileListTB").xLily6X($.widget.TableReload,{path:path});
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

    var fileNameColor = function (type) {
        // var point = fileName.lastIndexOf(".");
        // var type = fileName.substr(point+1);
        var color = "#585655";
        switch (type){
            case "zip":
                color = "#e33256";
                break;
            case "rar":
                color = "#c04144";
                break;
            case "gz":
                color = "#c04144";
                break;
            case "exe":
                color = "#13e947";
                break;
            case "sh":
                color = "#54ff6d";
                break;
            case "bat":
                color = "#54ff6d";
                break;
            case "jar":
                color = "#fcfeff";
                break;

        }
        return color;
    }


    // windows on load
    $(function(){

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
            $("#access").val(message);
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

        $(function () {
            var path = sessionStorage.getItem("path");
            path = path==null?"D:/":path;
            $("#pwd").val(path.replace(/\\/g,"/"));
            sessionStorage.setItem("path",path);
            console.log(path);
            $("#fileListTB").xLily6X($.widget.Table,{
                columns:[
                    {name:"fileName",title:"File Name",width:"90px",isFormat:true,format:function (obj) {
                        var d = obj.directory;
                        console.log(obj.fileType);
                        if(d){
                            var color = "#847edc";
                            return '<td style="color: '+color+'">'+obj.fileName+'</td>';
                        }else{
                            var color = fileNameColor(obj.fileType);
                            return '<td style="color: '+color+'">'+obj.fileName+'</td>';
                        }

                    }},
                    {name:"fileSize",title:"File Size",width:"80px"},
                    {name:"filePath",title:"File Path",width:"230px",align:"left"},
                    {name:"directory",title:"Directory",width:"80px"}
                ],
                url:"/FilesServlet",
                width:"800px",
                paging:true,
                pagination:{
                    currPage:1,
                    pageSize:20
                },
                parameters:{path:path,action:"showfiless"},
                onLoad:function ($target,options) {
                    // console.log("onload");
                    // console.log($target.xLily6X($.widget.TableGetRows));
                    $.each($target.xLily6X($.widget.TableGetRows),function (i,v) {
                        $(v).bind("click",function () {
                            showNextList(this);
                        })
                    })
                }
            });
        })

        $("#ftp_div").xLily6X($.widget.ContextMenu,{
            items:[
                {id:"downloadBtn",text:"Download Now",click:function (obj,env) {
                        var $obj = $(obj).closest("tr");
                        console.log($obj.index());
                        console.log($(obj).closest("tr").length>0);
                        console.log($(obj).closest("tbody").length>0);
                        if($obj.length>0 && $(obj).closest("tbody").length>0){
                            console.log($obj);
                            // var message =  window.prompt();
                            // console.log(message);
                            // if(message==asd){
                            //     downloadFile($obj);
                            //     console.log(obj);console.log(env);
                            // }
                            downloadFile($obj);
                        }else{
                            console.log("In not a file");
                        }

                    }
                }

            ],
            auto_height:true,
            background_color:"#fbf8ff"
        });
        $("#return").xLily6X($.widget.Tips,{text:"Return to the higher level directory"})
        $("#upload").xLily6X($.widget.Tips,{text:"Upload a file to the current directory"})



    });