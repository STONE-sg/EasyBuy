const  URL_HEAD = "http://localhost:8080/goods/";

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

function fillList(goodsList) {
    let builder = new StringBuilder();
    for (let i = 0; i < goodsList.length; i++) {
        let goods = goodsList[i];
        builder.clear();
        builder.append("<tr><td>");
        builder.append(goods.goods_id);
        builder.append("</td><td>");
        builder.append(goods.name);
        builder.append("</td><td>");
        if (goods.cat_id == 1){
            builder.append("电器");
        }
        if (goods.cat_id == 2){
            builder.append("衣服");
        }
        builder.append("</td><td>");
        builder.append(goods.price);
        builder.append("</td><td>");
        builder.append('<img src="' + goods.goods_pic + '">');
        builder.append('<input type="hidden" value="' + goods.goods_pic + '"/>');
        builder.append("</td><td>");
        builder.append(goods.description);
        builder.append("</td><td className=\"w1 c\"><a href=\"product-modify.html\" onclick=\"setCookieForId(this);getValues(this);\">修改</a> <a href=\"#\" onclick=\"setCookieForId(this);onDelete();\">删除</a>");
        builder.append("</a></td></tr>");
        $("#tbGoodsList tbody").append(builder.toString());
    }
}

function clearList() {
    $("#tbGoodsList tbody").empty();
}

function getValues(obj) {
    var thisRow = obj.parentNode.parentNode;
    var name = thisRow.cells[1].innerText;
    var goodCat = thisRow.cells[2].innerText;
    var goodPrice = thisRow.cells[3].innerText;
    var fileUrl = thisRow.cells[4].childNodes[1].value;
    var goodPic = fileUrl.replace("http://localhost:8080/upload/","");
    var description = thisRow.cells[5].innerText;
    document.cookie = "name="+name;
    document.cookie = "goodCat="+goodCat;
    document.cookie = "goodPrice="+goodPrice;
    document.cookie = "goodPic="+goodPic;
    document.cookie = "description="+description;
}

function setValues() {
    $("#productName").val(getCookie("name"));
    $("#parentId option:selected").val(getCookie("goodCat"));
    $("#productPrice").val(getCookie("goodPrice"));
    $("#photo").val(getCookie("goodPic"));
    $("#description").val(getCookie("description"));
}

function onUpdate() {
    const goods_id = Number(getCookie("id"));
    const name = $("#productName").val();
    const cat_id = $("#parentId option:selected").val();
    const goods_pic = $("#fileUrl").val();
    const price = $("#productPrice").val();
    const description = $("#description").val();
    $.ajax({
        url: URL_HEAD + "update",
        type: "put",
        data: {"goods_id":goods_id, "name": name, "cat_id": cat_id, "goods_pic": goods_pic, "price": price, "description": description},
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
    const goods_id = Number(getCookie("id"));
    $.ajax({
        url: URL_HEAD + "delete",
        type: "delete",
        data: {"goods_id": goods_id},
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
    find(0, null, 0, null, null, null);
}

function onAdd() {
    const name = $("#productName").val();
    const catId = $("#parentId option:selected").val();
    const goodsPic = $("#fileUrl").val();
    const price = $("#productPrice").val();
    const description = $("#description").val();
    $.ajax({
        url: URL_HEAD + "add",
        type: "post",
        data: {"name": name, "cat_id": catId, "goods_pic": goodsPic, "price": price, "description": description},
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

function find(goodsId, name, catId, goodsPic, price, description) {
    $.ajax({
        url: URL_HEAD + "find",
        type: "get",
        data: {"goods_id": goodsId, "name": name, "cat_id": catId, "goods_pic": goodsPic, "price": price, "description": description},
        dataType: "json",
        success: function (result) {
            clearList();
            fillList(result.data);
            // //对首页更新
            // clearList_index();
            // fillList_index(result.data);
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("查询异常", xhr, textStatus, errorThrown);
        }
    })
}

// //首页列表更新
// function onManageLoad_index() {
//     findAllGoodsCat();
// }
//
// function fillList_index(goodCats) {
//     let builder = new StringBuilder();
//     for (let i = 0; i < goodCats.length; i++) {
//         let goodCat = goodCats[i];
//         builder.clear();
//         builder.append("<dd><a href=\"javascript:void(0);\" onclick=\"changeMyPosition(this);\">");
//         builder.append(goodCat.name);
//         builder.append("</a></dd>");
//         if (goodCat.parent_id == 1) {
//             $("#dlgoodsCatList #dt1").append(builder.toString());
//         } else if (goodCat.parent_id == 2) {
//             $("#dlgoodsCatList #dt2").append(builder.toString());
//         }
//     }
// }
//
// function clearList_index() {
//     $("#dlgoodsCatList dd").empty();
// }

// //product.html上方”您现在的位置“更新
// function changeMyPosition(obj) {
//     const goodCatName = $(obj).html();
//     const parentName = $(obj).parent().parent().find("span").text();
//     $("#parentName").html(parentName);
//     $("#goodCatName").html(goodCatName);
// }
