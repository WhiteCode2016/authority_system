// 添加
$("#btn_submit").on("click", function () {
    $.ajax({
        url: '/api/menu/',
        method: 'post',
        data: {
            id: $("#id").val(),
            menuName : $("#menuName").val(),
            sort : $("#sort").val()
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg("操作成功", {icon: 1, time: 1500});
                closeLayer();
                refresh();
            }else {
                layer.msg("操作失败", {icon: 1, time: 1500});
            }

        }
    });
});

// 取消
$("#btn_cancel").on("click", function () {
    closeLayer();
});


