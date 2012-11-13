<%-- 
/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>White Board</title>
    </head>
    <body>
        <h1>White Board</h1>
    <canvas id="myCanvas" width="300" height="300" style="border:1px solid #000000;"></canvas>
    <form name="inputForm">
        <table>

            <tr>
                <th>Color</th>
                <td>
                    <input type="radio" name="color" value="#FF0000" checked="true">Red
                </td>
                <td>
                    <input type="radio" name="color" value="#0000FF">Blue
                </td>
                <td>
                    <input type="radio" name="color" value="#FFFF00">Yellow
                </td>
                <td>
                    <input type="radio" name="color" value="#00FF00">Green
                </td>
            </tr>

            <tr>
                <th>Shape</th>
                <td>
                    <input type="radio" name="shape" value="rectangle" checked="true">Rectangle
                </td>
                <td>
                    <input type="radio" name="shape" value="circle">Circle
                </td>
            </tr>

            <tr>
                <th>Format</th>
                <td>
                    <input type="radio" name="format" value="text" checked="true">Text
                </td>
                <td>
                    <input type="radio" name="format" value="binary">Binary
                </td>
            </tr>
        </table>
    </form>
    <div id="output"></div>
    <script type="text/javascript" src="websocket.js"></script>
    <script type="text/javascript" src="whiteboard.js"></script>

</body>
</html>