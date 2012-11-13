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

    
//alert('script.js');
var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
canvas.addEventListener("click", defineImage, false);
            
function init() {
}
            
function getCurrentPos(evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
}
            
function defineImage(evt) {
    var currentPos = getCurrentPos(evt);
    
    for (i = 0; i <document.inputForm.color.length; i++) {
        if (document.inputForm.color[i].checked) {
            color = document.inputForm.color[i];
            break;
        }
    }
            
    for (i = 0; i < document.inputForm.shape.length; i++) {
        if (document.inputForm.shape[i].checked) {
            shape = document.inputForm.shape[i];
            break;
        }
    }
    
    for (i = 0; i< document.inputForm.format.length; i++) {
        if (document.inputForm.format[i].checked) {
            switch (document.inputForm.format[i].value) {
                case "binary":
                    break;
                case "text":
                default:
                    json = JSON.stringify({
                        "shape": shape.value,
                        "color": color.value,
                        "coords": {
                            "x": currentPos.x,
                            "y": currentPos.y
                        }
                    });
                    drawImage(json);
                    sendText(json);
                    break;
            }
        }
    }
}

function drawImage(json) {
    json2 = JSON.parse(json);
//    alert("shape: " + json2.shape);
    context.fillStyle = json2.color;
    switch (json2.shape) {
    case "circle":
        context.beginPath();
        context.arc(json2.coords.x, json2.coords.y, 25, 0, 2 * Math.PI, false);
        context.fill();
        break;
    case "rectangle":
    default:
        context.fillRect(json2.coords.x, json2.coords.y, 50, 50);
        break;
    }
}
