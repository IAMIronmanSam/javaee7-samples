<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
</head>

<body>
<meta charset="utf-8">
<title>WebSocket: Echo Text and Binary Data</title>

<h2 style="text-align: center;">WebSocket: Echo Text and Binary Data</h2>

<div style="text-align: center;">
    <form action="">
        <input onclick="echo_text()" value="Echo Text" type="button">
        <input onclick="echo_binary()" value="Echo Binary" type="button">
    </form>
</div>
<div id="output"></div>
<script language="javascript" type="text/javascript" src="websocket.js">
</script>

</body>
</html>