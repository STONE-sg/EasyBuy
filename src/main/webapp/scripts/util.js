function showUsername() {
    $("#usernameShow").html(getCookie("username"));
}

function showError(label, xhr, textStatus, errorThrown) {
    /* 错误信息处理 */
    alert(label + "：\n"
        + "状态码：" + xhr.status + "\n"
        + "状态：" + xhr.readyState + "\n" /* 当前状态,，0-未初始化，1-正在载入，2-已经载入，3-数据进行交互，4-完成 */
        + "错误信息：" + xhr.statusText + "\n"
        + "返回响应信息：" + xhr.responseText + "\n" /* 这里时详细的信息 */
        + "请求状态：" + textStatus + "\n"
        + "完整异常：" + errorThrown)
}

function StringBuilder() {
    this.strings = new Array("");
}

StringBuilder.prototype.append = function (value) {
    if (value){
        this.strings.push(value);
    }
}

StringBuilder.prototype.clear = function () {
    this.strings.length = 0;
}

StringBuilder.prototype.toString = function () {
    return this.strings.join("");
}

function getQueryString(name) {
    const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    const r = window.location.search.substr(1).match(reg);
    if (r != null){
        return decodeURI(r[2]);
    }
    return null;
}