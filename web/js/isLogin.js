function isLogin(userName) {
    return 1001;
    $.ajax({
        url: "http://localhost:8080/user/isLogin",//要请求的服务器url
        data: {
            "user":userName,
        },
        type: "POST", //请求方式为POST
        dataType: "json",   //服务器返回的数据是什么类型
        success: function(result){  //这个方法会在服务器执行成功是被调用 ，参数result就是服务器返回的值(现在是json类型)
            alert(result);
            var result = JSON.parse(result);
            if (result.result) {
                return result.id;
            }
        }
    });
}