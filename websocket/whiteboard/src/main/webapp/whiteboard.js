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
    
    var json = JSON.stringify({
        "shape": shape.value,
        "color": color.value,
        "coords": {
            "x": currentPos.x,
            "y": currentPos.y
        }
    });
    drawImageText(json);
    if (document.getElementById("format").checked) {
        sendText(json);
    }
}

function defineImageBinary() {
//    console.log('defineImageBinary');
    var image = context.getImageData(0, 0, canvas.width, canvas.height);
//    pixels = image.data;
    var buffer = new ArrayBuffer(image.data.length);
    var bytes = new Uint8Array(buffer);
    for (var i=0; i<bytes.length; i++) {
        bytes[i] = image.data[i];
    }
    console.log("sendImageBinary: " + bytes.length);
    sendBinary(buffer);
}

function drawImageText(image) {
    var json = JSON.parse(image);
//    console.log("shape: " + json.shape);
    context.fillStyle = json.color;
    switch (json.shape) {
    case "circle":
        context.beginPath();
        context.arc(json.coords.x, json.coords.y, 5, 0, 2 * Math.PI, false);
        context.fill();
        break;
    case "rectangle":
    default:
        context.fillRect(json.coords.x, json.coords.y, 10, 10);
        break;
    }
}

function drawImageBinary(blob) {
//    console.log("drawImageBinary (blob.size): " + blob.byteLength);
//    console.log("drawImageBinary: " + blob);
//    alert(typeof image == "object");
//    alert('drawImageBinary: ' + blob.size);
//    console.log(typeof blob);
//    var reader = new FileReader();
//    var buffer = reader.readAsArrayBuffer(blob);
    console.log("instanceof ArrayBuffer: " + (blob instanceof ArrayBuffer));
    console.log("blob type: " + Object.prototype.toString.call(blob));
//    var bytes = new Uint8Array(blob.size);
    var bytes = new Uint8Array(blob);
    console.log('drawImageBinary (bytes.length): ' + bytes.length);
//    var myCanvas = document.getElementById("myCanvas");
//    var myContext = myCanvas.getContext("2d");
//    var imageData = myContext.getImageData(0, 0, myCanvas.width, myCanvas.height);
    
//    var myCanvas = document.getElementById("myCanvas2");
//    var myContext = myCanvas.getContext("2d");
    
    var imageData = context.createImageData(canvas.width, canvas.height);
    
    console.log("imageData: " + imageData.data.length);
    for (var i=8; i<imageData.data.length; i++) {
//        console.log(i);
        imageData.data[i] = bytes[i];
    }
    console.log('drawImageBinary (imageData.length): ' + imageData.data.length);
    context.putImageData(imageData, 0, 0);
//    myContext.drawImage(imageData, 0, 0);
    
    var img = document.createElement('img');
    img.height = canvas.height;
    img.width = canvas.width;
    img.src = canvas.toDataURL();
//    context.drawImage(image, 0, 0);
    
//    var img = document.createElement('img');
//    img.height = canvas.height;
//    img.width = canvas.width;
//    img.src = canvas.toDataURL();
//    
//    output.appendChild(img);
//    output.innerHTML = canvas.innerHTML + "<br />";
        
//    context.drawImage(image, 0, 0);
}

function drawImageBinary3(blob) {
    var myCanvas = document.getElementById("myCanvas");
    var myContext = myCanvas.getContext("2d");
    var imageData = myContext.getImageData(0, 0, myCanvas.width, myCanvas.height);
    
    var myCanvas2 = document.getElementById("myCanvas2");
    var myContext2 = myCanvas2.getContext("2d");
    
    var imageData2 = myContext2.createImageData(canvas.width, canvas.height);
    
    console.log("imageData2: " + imageData2.data.length);
    for (var i=0; i<imageData.data.length; i++) {
        imageData2.data[i] = imageData.data[i];
    }
    console.log('drawImageBinary (imageData.length): ' + imageData.data.length);
    myContext2.putImageData(imageData2, 0, 0);
}

function drawImageBinary2(blob) {
    console.log('drawImageBinary: ' + blob.size);
    
    var DOMURL = self.URL || self.webkitURL || self;
    var image = new Image();
    var svg = new Blob([data], {type: "image/svg+xml;charset=utf-8"});
    var url = DOMURL.createObjectURL(svg);
    image.onload = function() {
        context.drawImage(image, 0, 0);
        DOMURL.revokeObjectURL(url);
    };
    image.src = url;
}

