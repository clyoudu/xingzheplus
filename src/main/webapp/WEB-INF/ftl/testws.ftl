<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="http://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
    <script language="javascript" type="text/javascript">
        var echo_websocket,flag = false;
        function init() {
            output = document.getElementById("output");
        }

        function send_echo() {
            var wsUri = "ws://localhost:8080/websocket.ws";
            writeToScreen("Connecting to " + wsUri);
            echo_websocket = new WebSocket(wsUri);
            echo_websocket.onopen = function (evt) {
                writeToScreen("Connected !");
            };
            echo_websocket.onmessage = function (evt) {
                writeToScreen("Received message: " + evt.data);
                //echo_websocket.close();
            };
            echo_websocket.onerror = function (evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> '
                        + evt.data);
                echo_websocket.close();
            };
            echo_websocket.onclose = function(evt){
                writeToScreen("Closed !");
                flag = false;
            }

            flag = true;
        }
        /*function doSend(message) {
            if(!flag){
                send_echo();
            }
            echo_websocket.send(message);
            writeToScreen("Sent message: " + message);
        }*/


         this.doSend = function (message, callback) {
             if(!flag){
                 send_echo();
             }
            this.waitForConnection(function () {
                echo_websocket.send(message);
                writeToScreen("Sent message: " + message);
                if (typeof callback !== 'undefined') {
                    callback();
                }
            }, 1000);
        };

        this.waitForConnection = function (callback, interval) {
            if (echo_websocket.readyState === 1) {
                callback();
            } else {
                var that = this;
                // optional: implement backoff for interval here
                setTimeout(function () {
                    that.waitForConnection(callback, interval);
                }, interval);
            }
        };

        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }
        window.addEventListener("load", init, false);
    </script>
</head>
<body>
<h1>Echo Server</h1>
<div style="text-align: left;">
    <form action="">
        <input onclick="doSend(this.value)" value="发送socket请求" type="button">
        <input id="textID" name="message" value="Hello World, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>