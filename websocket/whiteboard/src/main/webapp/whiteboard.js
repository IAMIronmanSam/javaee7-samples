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
            var color = document.inputForm.color[i];
            break;
        }
    }
            
    for (i = 0; i < document.inputForm.shape.length; i++) {
        if (document.inputForm.shape[i].checked) {
            var shape = document.inputForm.shape[i];
            break;
        }
    }
    
//    alert(document.inputForm.format.length);
    for (i = 0; i< document.inputForm.format.length; i++) {
        
        if (document.inputForm.format[i].checked) {
            alert(document.inputForm.format[i].value);
            switch (document.inputForm.format[i].value) {
                case "binary":
                    var image = context.getImageData(0, 0, canvas.width, canvas.height);
//                    pixels = image.data;
                    var buffer = new ArrayBuffer(image.data.length);
                    var bytes = new Uint8Array(buffer);
                    for (var i=0; i<bytes.length; i++) {
                        bytes[i] = image.data[i];
                    }
                    sendBinary(buffer);
                    break;
                case "text":
                default:
                    var json = JSON.stringify({
                        "shape": shape.value,
                        "color": color.value,
                        "coords": {
                            "x": currentPos.x,
                            "y": currentPos.y
                        }
                    });
                    sendText(json);
                    drawImage(json);
                    break;
            }
        }
    }
}

function drawImage(image) {
    alert('drawImage: ' + image);
    var json = JSON.parse(image);
    alert('parsing done');
    if (json != null) {
        alert("drawImage(json)")
//        alert("shape: " + json2.shape);
        context.fillStyle = json.color;
        switch (json.shape) {
        case "circle":
            context.beginPath();
            context.arc(json.coords.x, json.coords.y, 25, 0, 2 * Math.PI, false);
            context.fill();
            break;
        case "rectangle":
        default:
            context.fillRect(json.coords.x, json.coords.y, 50, 50);
            break;
        }
    } else {
        alert('drawImage(blob)');
    }
}

