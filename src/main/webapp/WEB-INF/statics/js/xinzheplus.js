/**
 * xingzheplus 1.0 @chenlei
 */
function appendLg(child){
    $("#lg").append(child);
}

var socket,initFlag = false;
function initSocket(){
    var wsUri = "ws://localhost:8080/crawler.ws";
    appendLg("<p>Connecting to " + wsUri + "</p>");
    socket = new WebSocket(wsUri);
    socket.onopen = function (evt) {
        appendLg("<p>Connected !</p>");
    };
    socket.onmessage = function (evt) {
        appendLg('<div class="alert alert-success">'+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
            '<strong>消息：</strong> '+ evt.data +
            '</div>');
    };
    socket.onerror = function (evt) {
        appendLg('<p><span style="color: red;">ERROR:</span> '
            + evt.data+"</p>");
        socket.close();
    };
    socket.onclose = function(evt){
        appendLg("<p>Closed !</p>");
        initFlag = false;
    }

    flag = true;
}

$(function(){
    $("#s-c").click(function(){
        var y1 = $("#y1").val();
        var y2 = $("#y2").val();
        var m1 = $("#m1").val();
        var m2 = $("#m2").val();
        var sessionId = $("#sid").val();

        if(y1 > y2 || (y1 == y2 && m1 > m2)){
            appendLg('<div class="alert alert-danger">'+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
            '<strong>错误！</strong> 开始时间必须小于或等于结束时间。'+
            '</div>');
        }else if(!sessionId || $.trim(sessionId).length == 0){
            appendLg('<div class="alert alert-danger">'+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
                '<strong>错误！</strong> 请填写sessionId。'+
                '</div>');
        }else{
            $.ajax({
                'url':'startCrawler',
                'type':'POST',
                'dataType':'JSON',
                'data':JSON.stringify({y1:y1,y2:y2,m1:m1,m2:m2,sessionId:sessionId}),
                'contentType': "application/json; charset=utf-8",
                success:function(data){
                    if(data && data.code == 0){
                        if(!initFlag){
                            initSocket();
                        }
                        appendLg('<div class="alert alert-success">'+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
                            '<strong>消息：</strong> '+ JSON.stringify(data) +
                            '</div>');
                    }else{
                        appendLg('<div class="alert alert-danger">'+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
                            '<strong>错误！</strong> '+ JSON.stringify(data) +
                            '</div>');
                    }

                },
                error:function (data) {
                    appendLg('<div class="alert alert-danger">'+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
                        '<strong>错误！</strong> '+ JSON.stringify(data) +
                        '</div>');
                }
            });
        }
    });
});
