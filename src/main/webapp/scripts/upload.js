function doUpload() {
    const formData = new FormData($("#uploadForm")[0]);
    $.ajax({
        url: "http://localhost:8080/file/" + "upload",
        type: "post",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (result) {
            alert(result.msg);
            const fileUrl = result.data;
            $("#fileUrl").attr("value", fileUrl);
            // $("#text").append('<img src="' + fileUrl + '">');
        },
        error: function (xhr, textStatus, errorThrown) {
            showError("上传图片异常", xhr, textStatus, errorThrown);
        }
    })
}