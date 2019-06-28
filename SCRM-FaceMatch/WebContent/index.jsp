<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  String path = request.getScheme()+":"+"//"+request.getServerName()+":"+request.getServerPort()
	+"/"+request.getServletContext().getContextPath()+"/";%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/animate.css">
<script src="js/jquery.min.js"></script>
<script src="js/weather.js"></script>
<title>SCRM新零售系统</title>
</head>
<style>
    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropdown-content a:hover {background-color: #f1f1f1}

html, body {
  height: 100%;
  width: 100%;
  overflow: hidden;
}
.lock {
  z-index: 10;
  position: absolute;
  width: 100%;
  height: 100%;
  background: url(images/lock.jpg) no-repeat center;
  background-size: 100%;
}
    .form {
        position: absolute;
        left: 0;
        right: 0;
        bottom: 0;
        top: 0;
        margin: auto;
        width: 240px;
        height: 270px;
        text-align: center;
        background: rgba(255,255,255,.3);
    }
        .pic {
          margin-top: 30px;
          width: 80px;
          height: 80px;
          background: url(images/pic.jpg) no-repeat center;
          background-size: 100%;
          border-radius: 50%;
          opacity:0.8;
        }
        .pic:hover {
            cursor: pointer;
            opacity: 1;
        }
        .name {
          margin-top: 15px;
          color: white;
          font-size: 18px;
        }
            .input input {
              margin-top: 20px;
              width: 90px;
              height: 25px;
              text-indent: 10px;
            }
.container {
  z-index: 4;
  position: relative;
  width: 100%;
  height: 100%;
  background: url(./images/bg7.jpg) no-repeat center;
  background-size: 100%;
  overflow: hidden;
}
    .nav {
      z-index: 3;
      position: fixed;
      bottom: 0px;
      left: 0px;
      right: 0px;
      margin: 0px auto;
      width: 100px;
      height: 60px;
      background: rgba(229,232,237,.5);
    }
      .nav ul {
        position: relative;
        width: 98%;
        top: 6px;
        left: 13px;
        right: 0;
        bottom: 0;
        margin: auto;
      }
        .nav ul li {
          float: left;
          text-align: center;
          transition: .5s;
          margin: 0 5px;
        }
        .nav ul li:hover {
          cursor: pointer;
          transform: scale(1.2);
        }
            .nav ul li img {
                width: 80%;
            }

            .notice_content ul li {
              height: 30px;
              width: 100%;
              letter-spacing: 2px;
            }
            .notice_content ul li:hover {
              cursor: pointer;
              font-weight: bold;
            }

          .works ul {
            width: 100%;
            height: 100%;
          }
            .works ul li {
              z-index: 3;
              position: relative;
              float: left;
              width: 98px;
              height: 100%;
              background: rgba(240,240,240,.8);
              border-left: 1px solid gray;
              border-right: 1px solid gray;
              display: none;
            }
            .works ul li:nth-of-type(2) {
              border: none;
            }

                .w_content img {
                  width: 100%;
                }

                .loading{position:fixed;z-index:999;top:0;left:0;display:table;width:100%;height:100%;background:#fff;text-align:center}.loading .loader{display:table-cell;vertical-align:middle}.loading p{margin-top:15px;color:#333}.loader_icn{opacity:.75;width:24px;height:24px;display:inline-block;-webkit-animation:clockwise .5s linear infinite;animation:clockwise .5s linear infinite}.loader_cut{width:12px;height:24px;overflow:hidden;position:absolute;top:0;left:0}.loader_donut{box-sizing:border-box;width:24px;height:24px;border:2px solid #000;border-radius:50%;border-left-color:transparent;border-bottom-color:transparent;position:absolute;top:0;left:0;background:0 0;margin:0;-webkit-animation:donut-rotate 1s cubic-bezier(.4,0,.22,1) infinite;animation:donut-rotate 1s cubic-bezier(.4,0,.22,1) infinite}@-webkit-keyframes clockwise{0%{-webkit-transform:rotate(0deg)}100%{-webkit-transform:rotate(360deg)}}@keyframes clockwise{0%{transform:rotate(0deg)}100%{transform:rotate(360deg)}}@-webkit-keyframes donut-rotate{0%{-webkit-transform:rotate(0deg)}50%{-webkit-transform:rotate(-140deg)}100%{-webkit-transform:rotate(0deg)}}@keyframes donut-rotate{0%{transform:rotate(0deg)}50%{transform:rotate(-140deg)}100%{transform:rotate(0deg)}}
</style>
<script type="text/javascript">
</script>
<script>
</script>
<body>
  <div id="tp-weather-widget"></div>
  <form  action="<%=path %>/LoginServlet" method="post" >
  <div class="lock animated">
      <div class="form">
          <div class="pic"></div>
          <div class="name">SCRM新零售系统</div>
          <form  action="<%=path %>/LoginServlet" method="post" >
          <select id="pid" name="admin" class="input" style="width: 65px;height: 25px;margin-top: 10px">
              <option value="man"  name="manager">管理员</option>
              <option value="cus"  name="customers">顾客</option>
          </select>
          <div class="input">
              <input  name="pwd" class="animated" type="password" placeholder="请输入密码" value="" />
          </div>
          <div><input type="submit" value = "确定"><div>
      </div>
  </div>
  </from>
<script src="js/particles.min.js"></script>
<script src="js/zhuti1.js"></script>
</body>
</html>