var $table = $('#menuTable');

// bootstrapTable使用中文
$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

// 防止表头与表格不对齐
$(window).resize(function () {
    $table.bootstrapTable('resetView');
});

// 初始化Table
function  initTable() {
    //先销毁表格
    $table.bootstrapTable("destroy");
    //初始化表格,动态从服务器加载数据
    $table.bootstrapTable({
        url : "/api/menu/list_all_menu",
        method: 'get',
        contentType: "application/x-www-form-urlencoded", //请求内容类型
        dataType: "json",  //数据类型
        sortOrder: "asc",  //排序方式
        pagination : true, //是否显示分页（*）
        pageNumber:1,      //初始化加载第一页，默认第一页
        pageSize: 10,      //每页的记录行数（*）
        pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
        striped : true,     //是否显示行间隔色
        cache : false,      //是否使用缓存
        uniqueId: "id",     //每一行的唯一标识，一般为主键列
        sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
        queryParamsType : '',       //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
        //设置为 '' 在这种情况下传给服务器的参数为：pageSize,pageNumber
        columns : [
            {title: '编号', field: 'id', align: 'center', valign: 'middle'},
            {title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle'},
            {title: '菜单父编号', field: 'parentId', align: 'center', valign: 'middle'},
            {title: '请求地址', field: 'href', align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', align: 'center', valign: 'middle'},
            {title: '操作', field: 'operate', align: 'center', valign: 'middle', width: '140px', formatter: operateFormatter, events: operateEvents}
        ],
        queryParams : queryParams
    });
}

// 查询参数
function queryParams(params) {
    var temp = {
        pageSize : params.pageSize,
        pageNumber : params.pageNumber,
        menuName : $("#menuName").val(),
    };
    return temp;
}

// 自定义方法，添加操作按钮
function operateFormatter (value, row, index) {
    return [
        '<button type="button" class="btn btn-success btn-xs" id="edit"><i class="glyphicon glyphicon-pencil"></i> 修改</button>',
        '<button type="button" class="btn btn-danger btn-xs" id="delete" style="margin-left: 5px;"><i class="glyphicon glyphicon-remove"></i> 删除</button>'
    ].join('');
}

//  为操作按钮添加事件
window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        addMenu(row.id);
    },
    'click #delete': function (e, value, row, index) {
        delMenu(row.id);
    }
};


// 刷新页面
function refresh() {
    $table.bootstrapTable('refresh');
}

// 删除按钮点击事件
function delMenu(id) {
    layer.confirm('确定要删除用户编号为' + id + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: 'api/menu/'+id,
            method: 'get',
            success: function () {
                layer.msg("删除成功", {icon: 1, time: 1500});
                refresh();
            }
        });
    });
}

var layerIndex = -1;

// 新增菜单按钮
$("#btn_add").on("click", function () {
     var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['830px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: 'menu/menu_add', //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
    layerIndex = index;
});

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

function closeLayer() {
    parent.layer.close(window.parent.layerIndex);
}




$(function () {
    //使用严格模式
    "use strict";

    //调用函数，初始化表格
    initTable();
    //当点击查询按钮的时候执行
    $("#query").bind("click", initTable);
});