const URL_HEAD_MEMBER = "http://localhost:8080/member/"

function onRegister(frm){
    var success = false;
    if(checkForm(frm)){
        var uname = $("#userName").val();
        var password = hex_md5($("#passWord").val());
        var captcha = $("#captcha").val();
        $.ajax({
            url: URL_HEAD_MEMBER+"register",
            type:"post",
            headers:{"captcha":captcha},
            data:{"uname":uname,"password":password},
            dataType:"json",
            async: false,
            success:function (result){
                alert(result.msg);
                setCookie("username", uname);
                if(result.status == 0){
                    success = true;
                }
            },
            error:function(xhr, textStatus, errorThrown) {
                showError("用户注册异常",xhr,textStatus,errorThrown);
            }
        });
    }
    return success;
}

function onLogin(frm){
    var success = false;
    if (checkForm(frm)){
        var uname = $("#userName").val();
        var password = hex_md5($("#passWord").val());
        var captcha = $("#captcha").val();
        $.ajax({
            url:URL_HEAD_MEMBER+"login",
            type: "post",
            headers:{"captcha":captcha},
            data: {"uname": uname, "password": password},
            dataType: "json",
            async: false,
            success: function (result) {
                alert(result.msg);
                setCookie("username", uname);
                if( result.status == 0){
                    success = true;
                }
            },
            error: function (xhr, textStatus, errorThrown) {
                showError("用户登录异常",xhr,textStatus,errorThrown);
            }
        });
    }
    return success;
}
