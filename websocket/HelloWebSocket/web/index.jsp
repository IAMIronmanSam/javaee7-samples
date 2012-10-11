<%-- 
    Document   : index
    Created on : Oct 9, 2012, 4:22:12 PM
    Author     : arungup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script language="javascript" type="text/javascript">
            var wsUri = "ws://localhost:8080/HelloWebSocket/hello";
            var websocket = new WebSocket(wsUri);
            websocket.onopen = function(evt) { onOpen(evt) };
            websocket.onmessage = function(evt) { onMessage(evt) };
            websocket.onerror = function(evt) { onError(evt) };

            function init() {
                output = document.getElementById("output");
            }

            function say_hello() {
                websocket.send(nameField.value);
                writeToScreen("SENT: " + nameField.value);
            }

            function onOpen(evt) {
                writeToScreen("CONNECTED");
            }

            function onMessage(evt) {
                writeToScreen("RECEIVED: " + evt.data);
            }

            function onError(evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            }

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
        <h1>Getting Started with WebSocket!!</h1>

        <div style="text-align: center;">
            <form action=""> 
                <input onclick="say_hello()" value="Say Hello" type="button"> 
                <input id="nameField" name="name" value="WebSocket" type="text"><br>
            </form>
        </div>
        <div id="output"></div>
    </body>
</html>
