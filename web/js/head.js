

function isLogin(userName) {
    userId = 1000;
    $.ajax({
        url: "http://localhost:8080/user/isLogin",//要请求的服务器url
        data: {
            "userName":userName,
        },
        xhrFields: {
            withCredentials:true
        },
        type: "POST", //请求方式为POST
        dataType: "json",   //服务器返回的数据是什么类型
        success: function(result){  //这个方法会在服务器执行成功是被调用 ，参数result就是服务器返回的值(现在是json类型)
            var result = JSON.parse(result);
            if(result.result){
                userId = result.id;
            }
        }
    });
    return userId;
}

function login_out() {
    $.ajax({
        url: "http://localhost:8080/user/loginOut",//要请求的服务器url
        xhrFields: {
            withCredentials:true
        },
        type: "POST", //请求方式为POST
        dataType: "json",   //服务器返回的数据是什么类型
        success: function(result){  //这个方法会在服务器执行成功是被调用 ，参数result就是服务器返回的值(现在是json类型)
            var result = JSON.parse(result);
            if(result.result){
                alert("注销成功！");
            }
        }
    });
    localStorage.clear();
    window.location.href = "../index.html";
}


document.writeln("<nav class=\'navbar navbar-inverse navbar-fixed-top\'>");
document.writeln("      <div class=\'container\'>");
document.writeln("        <div class=\'navbar-header\'>");
document.writeln("          <button type=\'button\' class=\'navbar-toggle collapsed\' data-toggle=\'collapse\' data-target=\'#navbar\' aria-expanded=\'false\' aria-controls=\'navbar\'>");
document.writeln("            <span class=\'sr-only\'>Toggle navigation</span>");
document.writeln("            <span class=\'icon-bar\'></span>");
document.writeln("            <span class=\'icon-bar\'></span>");
document.writeln("            <span class=\'icon-bar\'></span>");
document.writeln("          </button>");
document.writeln("          <a class=\'navbar-brand\' href=\'../index.html\'>大学生就业信息发布</a>");
document.writeln("        </div>");
document.writeln("        <div id=\'navbar\' class=\'navbar-collapse collapse\'>");

document.writeln("<script type=\'text/javascript\' src=\'jquery-3.2.1.min.js\'></script> ");

//document.write("<script src='./isLogin.js'></script>");
var userName = localStorage.getItem("userName");
if (userName) {
    var id = isLogin(userName);
} else {
    var id = null;
}

if (!id) {
    document.writeln("          <form class=\'navbar-form navbar-right\' action=\'localhost:8080/user/login\' method='post'>");
    document.writeln("            <div class=\'form-group\'>");
    document.writeln("              <input type=\'text\' id=\'user\' placeholder=\'账号\' class=\'form-control\'>");
    document.writeln("            </div>");
    document.writeln("            <div class=\'form-group\'>");
    document.writeln("              <input type=\'password\' id=\'password\' placeholder=\'密码\' class=\'form-control\'>");
    document.writeln("            </div>");
    document.writeln("            <button type=\'button\' class=\'btn btn-success\' value=\"登录\" onclick=\"login()\" id=\'sign_in\'>登录</button>");
    document.writeln("          </form>");
} else {
    document.writeln("          <form class=\'navbar-form navbar-right\' action=\'localhost:8080/user/login\' method='post'>");
    document.writeln("          <a class=\'navbar-brand\' href=\'../individual.html?id=" + id + "\'>个人中心</a>");
    document.writeln("            <button type=\'button\' class=\'btn btn-success\' value=\"注销\" onclick=\"login_out()\" id=\'sign_in\'>注销</button>");
    document.writeln("          </form>");

}



document.writeln("        </div><!--/.navbar-collapse -->");
document.writeln("      </div>");
document.writeln("    </nav>");
document.writeln("");
document.writeln("    <!-- Main jumbotron for a primary marketing message or call to action -->");
document.writeln("    <div class=\'jumbotron\'>");
document.writeln("      <div class=\'container\'>");
document.writeln("        <h1>岗位招聘信息</h1>");
document.writeln("        <h4>查看适合您的岗位，找到心仪的公司!</h4>");
document.writeln("        <h4>我们为您提供最新的公司招聘信息，您能通过本页面查看您所期望的岗位和满意的薪资信息。</h4>");
document.writeln("           <p><a class=\"btn btn-primary btn-lg\" href=\"sign_up.html\" role=\"button\"> 注册&raquo;</a></p>");
document.writeln("      </div>");
document.writeln("    </div>");

function login() {
	$.ajax({
            url: "http://localhost:8080/user/login",//要请求的服务器url
            data: {
                "user": $("#user").val(),
                "password": $("#password").val()
            },
            type: "POST", //请求方式为POST
            dataType: "json",   //服务器返回的数据是什么类型
            success: function(result){  //这个方法会在服务器执行成功是被调用 ，参数result就是服务器返回的值(现在是json类型) 
                var result = JSON.parse(result);
                if(result.result){
                    alert("登录成功！");
                    localStorage.setItem("userName", $("#user").val());
                    window.location.href = "../index.html";
                } else {
                    alert("false");
                }
            },
            error: function() {
            	alert("error");
            }
        });
}