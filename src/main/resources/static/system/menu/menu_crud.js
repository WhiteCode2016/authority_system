// 删除按钮点击事件
$("#btn_del").on("click", function () {
    var datas = $table.bootstrapTable('getSelections');
    if (datas.length < 1) {
        layer.alert('请选择一条或多条数据进行删除！', {icon: 2});
    } else {
        var ids = [];
        for (var i = 0; i < datas.length; i++) {
            //获取所有被勾选的id
            ids.push(datas[i].id);
        }
        delData(ids);
    }
});

// tr中删除按钮点击事件
function delData(ids) {
    layer.confirm('确定要删除用户编号为' + ids + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '/menu/deleteMenu',
            method: 'post',
            data: {
                ids: ids.toString()
            },
            success: function () {
                layer.msg("删除成功", {icon: 1, time: 1500});
                refresh();
            }
        });
    });
}

//刷新页面
function refresh() {
    $table.bootstrapTable('refresh');
}

$("#btn_add").on("click", function () {
    layer.open({
        type: 2,
        title: '添加菜单',
        area: ['830px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: 'menu/menu_add', //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
    // layer.alert('内容', {
    //     icon: 1,
    //     skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
    // })
});































