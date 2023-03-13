const  URL_HEAD = "http://localhost:8080/user/";

function onManageLoad() {
    findAll();
}

function onFind() {
    // const cat_id = Number($("#txtFindId").val());
    // const userName = $("#txtFindUserName").val();
    // const email = $("#txtFindEmail").val();
    // const age = Number($("#txtFindAge").val());
    // findGoodsCat(id);
}

function setCookieForId(obj) {
    var thisRow = obj.parentNode.parentNode;
    var id = thisRow.cells[0].innerText;
    document.cookie="id="+id;
}

function fillList(userList) {
    let builder = new StringBuilder();
    for (let i = 0; i < userList.length; i++) {
        let user = userList[i];
        builder.clear();
        builder.append("<tr><td>");
        builder.append(user.member_id);
        builder.append("</td><td>");
        builder.append(user.uname);
        builder.append("</td><td>");
        builder.append(user.sex);
        builder.append("</td><td>");
        builder.append(user.email);
        builder.append("</td><td>");
        builder.append(user.mobile);
        builder.append("</td><td className=\"w1 c\"><a href=\"user-modify.html\" onclick=\"setCookieForId(this);getValues(this);\">修改</a> <a href=\"#\" onclick=\"setCookieForId(this);onDelete();\">删除</a>");
        builder.append("</a></td></tr>");
        $("#tbUserList tbody").append(builder.toString());
    }
}

function clearList() {
    $("#tbUserList tbody").empty();
}

function getValues(obj) {
    var thisRow = obj.parentNode.parentNode;
    var uname = thisRow.cells[1].innerText;
    var sex = thisRow.cells[2].innerText;
    var email = thisRow.cells[3].innerText;
    var mobile = thisRow.cells[4].innerText;
    document.cookie = "uname="+uname;
    document.cookie = "sex="+sex;
    document.cookie = "email="+email;
    document.cookie = "mobile="+mobile;
}

function setValues() {
    $("#uname").val(getCookie("uname"));
    $("#sex").val(getCookie("sex"));
    $("#email").val(getCookie("email"));
    $("#mobile").val(getCookie("mobile"));
}

function onUpdate() {
    const member_id = Number(getCookie("id"));
    const uname = $("#uname").val();
    const sex = $("#sex").val();
    const email = $("#email").val();
    const mobile = $("#mobile").val();
    $.ajax({
        url: URL_HEAD + "update",
        type: "put",
        data: {"member_id":member_id, "uname": uname, "sex": sex, "email": email, "mobile": mobile},
        dataType: "json",
        success: function (result) {
            alert(result.msg);
            findAll();
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("更新异常", xhr, textStatus, errorThrown);
        }
    })
}

function onDelete() {
    const member_id = Number(getCookie("id"));
    $.ajax({
        url: URL_HEAD + "delete",
        type: "delete",
        data: {"member_id": member_id},
        dataType: "json",
        success: function (result) {
            alert(result.msg);
            findAll();
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("删除异常", xhr, textStatus, errorThrown);
        }
    })
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)){
        var a=unescape(arr[2]);
        return unescape(arr[2]);
    }else{
        return null;
    }
}


//TODO:增加和修改
function findAll() {
    find(0, null, 0, null, null);
}

function onAdd() {
    const uname = $("#uname").val();
    const password = $("#password").val();
    const sex = $("input[name='sex']:checked").val();
    const email = $("#email").val();
    const mobile = $("#mobile").val();
    $.ajax({
        url: URL_HEAD + "add",
        type: "post",
        data: {"uname": uname, "password": password, "sex": sex, "email": email, "mobile": mobile},
        dataType: "json",
        success: function (result) {
            alert(result.msg);
            findAll();
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("新增异常", xhr, textStatus, errorThrown);
        }
    })
}

function find(member_id, uname, sex, email, mobile) {
    $.ajax({
        url: URL_HEAD + "find",
        type: "get",
        data: {"member_id": member_id, "uname": uname, "sex": sex, "email": email, "mobile": mobile},
        dataType: "json",
        success: function (result) {
            clearList();
            fillList(result.data);
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("查询异常", xhr, textStatus, errorThrown);
        }
    })
}

