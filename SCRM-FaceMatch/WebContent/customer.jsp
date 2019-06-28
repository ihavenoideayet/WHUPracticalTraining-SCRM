<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%  String path = request.getScheme()+":"+"//"+request.getServerName()+":"+request.getServerPort()
	+"/"+request.getServletContext().getContextPath()+"/";%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, shrink-to-fit=no">
        <title>顾客页面</title>
        <link rel="stylesheet" href="cssc/bootstrap.min.css">
        <style>
            body,html{
                height: 100%;
                background: linear-gradient(#0c5460, #6f42c1);
                color: white;
            }
            #particles-js {
                position: absolute;
                width: 100%;
                height: 100%;
            }
            .button{
                border-radius:10px;
                outline:none;
                color:white;
                background: none;
            }
        </style>
        <%
        Scanner scanner = new Scanner();
        scanner.startScan();
        
        %>
    </head>
    <script>
        function unit() {
            // 老的浏览器可能根本没有实现 mediaDevices，所以我们可以先设置一个空的对象
            if (navigator.mediaDevices === undefined) {
                navigator.mediaDevices = {};
            }
            if (navigator.mediaDevices.getUserMedia === undefined) {
                navigator.mediaDevices.getUserMedia = function (constraints) {
                    // 首先，如果有getUserMedia的话，就获得它
                    var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

                    // 一些浏览器根本没实现它 - 那么就返回一个error到promise的reject来保持一个统一的接口
                    if (!getUserMedia) {
                        return Promise.reject(new Error('getUserMedia is not implemented in this browser'));
                    }

                    // 否则，为老的navigator.getUserMedia方法包裹一个Promise
                    return new Promise(function (resolve, reject) {
                        getUserMedia.call(navigator, constraints, resolve, reject);
                    });
                }
            }
            const constraints = {
                video: true,
                audio: false
            };
            let promise = navigator.mediaDevices.getUserMedia(constraints);
            promise.then(stream => {
                let v = document.getElementById('v');
                // 旧的浏览器可能没有srcObject
                if ("srcObject" in v) {
                    v.srcObject = stream;
                } else {
                    // 防止再新的浏览器里使用它，应为它已经不再支持了
                    v.src = window.URL.createObjectURL(stream);
                }
                v.onloadedmetadata = function (e) {
                    v.play();
                };
            }).catch(err => {
                console.error(err.name + ": " + err.message);
            })
            window.setInterval("getPic()",500);
        };
        window.onload = unit
    </script>
    <body>
        <div id="particles-js"><input type="hidden" value="<%=Scanner.flag%>" id="flag"></div>
        <div class="container mt-5">
            <h3 class="text-center">SCRM新零售系统</h3><br>
            <a href="../index.html">
                <img src="imagesc/icon9.png" style="position: fixed;top: 20px;right: 20px">
            </a>
            <section class="goods">
                <div class="row justify-content-center">
                    <div style="height: 240px;width: 240px;margin-top: 50px;margin-right: 50px">
                        <video id="v" style="width: 100%;height: 100%"></video>
                        <canvas id="canvas" style="display:none;"></canvas>
                    </div>
                    <div class="col-sm-6">
                        <span class="text-center">商品推荐</span>
                        <div class="row justify-content-center">
                            <div class="recommend">
                                <select style="margin-bottom: 20px;background: none;color: #ffffff;height: 30px">
                                    <option style="color: black">个性化推荐</option>
                                    <option style="color: black">季节性推荐</option>
                                    <option style="color: black">特价推荐</option>
                                </select>
                            </div>
                        </div>
                        <div align=center>
                            <table style="BORDER-COLLAPSE: collapse"  cellSpacing=1 cellPadding=0 width=500 border=0>
                                <tr>
                                    <td>
                                        <table cellSpacing=1 cellPadding=0 width="100%" align=center summary="" border=0>
                                            <tr>
                                                <td width="24%">编号</td>
                                                <td width="24%">品名</td>
                                                <td width="24%">单价</td>
                                                <td width="24%">位置</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="OVERFLOW: auto; WIDTH: 100%; HEIGHT: 150px">
                                            <table style="BORDER-COLLAPSE: collapse" borderColor="#ffffff" height=273 cellSpacing=1 cellPadding=12 width="99%">
                                                <tr>
                                                    <td width="25%"  height=17>1</td>
                                                    <td width="25%"  height=17>可口可乐</td>
                                                    <td width="25%"  height=17>4</td>
                                                    <td width="25%"  height=17>1区-2架-3层</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
            <hr style="height: 3px;background: white">
            <section class="pricing mt-3">
                <div class="row justify-content-center">
                    <div class="col-sm-6">
                        <p>购买列表</p>
                        <table id="total" style="BORDER-COLLAPSE: collapse"  cellSpacing=1 cellPadding=0 width=500 border=0>
                            <tr>
                                <td>
                                    <table cellSpacing=1 cellPadding=0 width="100%" align=center summary="" border=0>
                                        <tr>
                                            <td width="20%">商品ID</td>
                                            <td width="20%">品名</td>
                                            <td width="20%">数量</td>
                                            <td width="20%">单价</td>
                                            <td width="20%">操作</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div style="OVERFLOW: auto; WIDTH: 100%; HEIGHT: 150px">
                                        <table style="BORDER-COLLAPSE: collapse" borderColor="#ffffff" height=273 cellSpacing=1 cellPadding=12 width="99%">
                                            <tr>
                                                <td width="20%"  height=17>10086</td>
                                                <td width="20%"  height=17>脉动</td>
                                                <td width="20%"  height=17>2</td>
                                                <td width="20%"  height=17>5</td>
                                                <td width="20%">
                                                    <input class="button" type="button" value="删除">
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-sm-6">
                        <input class="button" type="button" value="结账">
                        <p style="margin-left: 140px">总价</p>
                        <label style="margin-left: 140px">5</label>
                        <div class="photo" style="height: 200px;width: 200px;margin-top: 30px;margin-left: 100px">
                            <img src="imagesc/1.png">
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <script>
        function getPic(){
        	let flag = document.getElmenetById("flag").innerHTML;
        	if(flag){
        	let v = document.getElementById('v');
            let canvas = document.getElementById('canvas');
            canvas.width = v.videoWidth;
            canvas.height = v.videoHeight;
            canvas.getContext('2d').drawImage(v, 0, 0);
            var data = canvas.toDataURL();
            var imgData = data.substring(22);
            console.log(imgData);
            $.ajax({
        			type: "post",
        			url: "PersonMatchServlet",
        			data: {"img":imgData}
        		}); 
            }        
        }    
        function num(){
        	return nums = document.getElementById("total").getElementsByTagName("tr").length;
        };
        </script>
        <script src="jsc/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="jsc/bootstrap.min.js"></script>
        <script src="script.js"></script>
        <script src="jsc/particles.min.js"></script>
        <script src="jsc/zhuti1.js"></script>
    </body>
</html>