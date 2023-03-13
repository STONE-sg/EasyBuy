const  URL_HEAD = "http://localhost:8080/cat/";

function onGoodsCatManageLoad() {
    findAllGoodsCat();
}

function onFindGoodsCat() {
    // const cat_id = Number($("#txtFindId").val());
    // const userName = $("#txtFindUserName").val();
    // const email = $("#txtFindEmail").val();
    // const age = Number($("#txtFindAge").val());
    // findGoodsCat(id);
}

function setCookieForCatId(obj) {
    var table = obj.parentNode.parentNode;
    var cat_id = table.cells[0].innerText;
    // setCookie("cat_id",cat_id);
    document.cookie="cat_id="+cat_id;
}

function fillGoodsCatList(goodCats) {
    let builder = new StringBuilder();
    for (let i = 0; i < goodCats.length; i++) {
        let goodCat = goodCats[i];
        builder.clear();
        builder.append("<tr><td>");
        builder.append(goodCat.cat_id);
        builder.append("</td><td>");
        builder.append(goodCat.name);
        builder.append("</td><td className=\"w1 c\"><a href=\"productClass-modify.html\" onclick=\"setCookieForCatId(this);\">修改</a> <a href=\"#\" onclick=\"setCookieForCatId(this);onDeleteGoodsCat();\">删除</a>");
        builder.append("</a></td></tr>");
        // <td className="w1 c"><a href="productClass-modify.html">修改</a> <a href="javascript:Delete(1);">删除</a></td>
        $("#tbGoodsCatList tbody").append(builder.toString());
    }
}

function clearGoodsCatList() {
    $("#tbGoodsCatList tbody").empty();
}

function onUpdateGoodsCat() {
    const cat_id = Number(getCookie("cat_id"));
    const name = $("#className").val();
    // if (id && !(name)){
    //     alert("请保证更新参数不为空");
    //     return;
    // }
    $.ajax({
        url: URL_HEAD + "update",
        type: "put",
        data: {"cat_id":cat_id, "name": name},
        dataType: "json",
        success: function (result) {
            alert(result.msg);
            findAllGoodsCat();
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("更新用户异常", xhr, textStatus, errorThrown);
        }
    })
}

function onDeleteGoodsCat() {
    const cat_id = Number(getCookie("cat_id"));
    $.ajax({
        url: URL_HEAD + "delete",
        type: "delete",
        data: {"cat_id": cat_id},
        dataType: "json",
        success: function (result) {
            alert(result.msg);
            findAllGoodsCat();
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("删除分类异常", xhr, textStatus, errorThrown);
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
function findAllGoodsCat() {
    findGoodsCat(0, null, 0);
}

function onAddGoodsCat() {
    const name = $("#className").val();
    const parent_id = $("#parentId option:selected").val();
    $.ajax({
        url: URL_HEAD + "add",
        type: "post",
        data: {"name": name, "parent_id": parent_id},
        dataType: "json",
        success: function (result) {
            alert(result.msg);
            findAllGoodsCat();
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("新增分类异常", xhr, textStatus, errorThrown);
        }
    })
}

function findGoodsCat(cat_id, name, parent_id) {
    $.ajax({
        url: URL_HEAD + "find",
        type: "get",
        data: {"cat_id": cat_id, "name": name, "parent_id": parent_id},
        dataType: "json",
        success: function (result) {
            clearGoodsCatList();
            fillGoodsCatList(result.data);
            //对首页更新
            clearGoodsCatList_index();
            fillGoodsCatList_index(result.data);
            //对商品列表页更新
            clearGoodsCatList_goodList();
            fillGoodsCatList_goodList(result.data);
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("查询用户异常", xhr, textStatus, errorThrown);
        }
    })
}

//首页列表更新
function onGoodsCatManageLoad_index() {
    findAllGoodsCat();
}

function fillGoodsCatList_index(goodCats) {
    let builder = new StringBuilder();
    for (let i = 0; i < goodCats.length; i++) {
        let goodCat = goodCats[i];
        builder.clear();
        builder.append("<dd><a href=\"product-list.html\" onclick=\"changeMyPosition(this);\">");
        builder.append(goodCat.name);
        builder.append("</a></dd>");
        if (goodCat.parent_id == 1) {
            $("#dlgoodsCatList #dt1").append(builder.toString());
        } else if (goodCat.parent_id == 2) {
            $("#dlgoodsCatList #dt2").append(builder.toString());
        }
    }
}

function clearGoodsCatList_index() {
    $("#dlgoodsCatList dd").empty();
}

//商品列表页更新
function onGoodsCatManageLoad_goodList() {
    findAllGoodsCat();
}

function fillGoodsCatList_goodList(goodCats) {
    let builder = new StringBuilder();
    for (let i = 0; i < goodCats.length; i++) {
        let goodCat = goodCats[i];
        builder.clear();
        builder.append("<dd><a href=\"#\" onclick=\"changeMyPosition(this);\">");
        builder.append(goodCat.name);
        builder.append("</a></dd>");
        if (goodCat.parent_id == 1) {
            $("#dlgoodsCatList2 #dt1").append(builder.toString());
        } else if (goodCat.parent_id == 2) {
            $("#dlgoodsCatList2 #dt2").append(builder.toString());
        }
    }
}

function clearGoodsCatList_goodList() {
    $("#dlgoodsCatList2 dd").empty();
}

//product.html上方”您现在的位置“更新
function changeMyPosition(obj) {
    const goodCatName = $(obj).html();
    const parentName = $(obj).parent().parent().find("span").text();
    $("#parentName").html(parentName);
    $("#goodCatName").html(goodCatName);
}






// function onGoodsCatManageLoad_myPosition() {
//     findAllGoodsCat_myPosition();
// }
//
// function findAllGoodsCat_myPosition() {
//     findGoodsCat_myPosition(0, null, 0);
// }
//
// function findGoodsCat_myPosition(cat_id, name, parent_id) {
//     $.ajax({
//         url: URL_HEAD + "find",
//         type: "get",
//         data: {"cat_id": cat_id, "name": name, "parent_id": parent_id},
//         dataType: "json",
//         success: function (result) {
//             //对product.html上方”您现在的位置“更新
//             clearGoodsCatList_index();//product.html和首页的分类列表id相同，所以直接用该函数
//             fillGoodsCatList_myPosition(result.data);
//         },
//         error: function (xhr, textStatus, errorThrown) {
//             showError("查询用户异常", xhr, textStatus, errorThrown);
//         }
//     })
// }
//
// function fillGoodsCatList_myPosition(goodCats) {
//     let builder = new StringBuilder();
//     for (let i = 0; i < goodCats.length; i++) {
//         let goodCat = goodCats[i];
//         builder.clear();
//         builder.append("<dd><a href=\"product-list.html\">");
//         builder.append(goodCat.name);
//         builder.append("</a></dd>");
//         if (goodCat.parent_id == 1) {
//             $("#dlgoodsCatList #dt1").append(builder.toString());
//             $("#parentId").html("电器");
//             $("#goodCatName").html("name");
//         } else if (goodCat.parent_id == 2) {
//             $("#dlgoodsCatList #dt2").append(builder.toString());
//             $("#parentId").html("衣服");
//             $("#goodCatName").html("name");
//         }
//     }
// }







//设置Cookie
// function setCookies(name, value, seconds) {
//     seconds = seconds || 0;   //seconds有值就直接赋值，没有为0，这个根php不一样。
//     var expires = "";
//     if (seconds != 0 ) {      //设置cookie生存时间
//         var date = new Date();
//         date.setTime(date.getTime()+(seconds*1000));
//         expires = "; expires="+date.toGMTString();
//     }
//     document.cookie = name+"="+escape(value)+expires+"; path=/";   //转码并赋值
// }