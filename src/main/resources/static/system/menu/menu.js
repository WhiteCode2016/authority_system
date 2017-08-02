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
        url : "/menu/list_all_menu",
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
            {field: 'selectItem', checkbox: true},
            {title: '编号', field: 'id', align: 'center', valign: 'middle'},
            {title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle'},
            {title: '菜单父编号', field: 'parentId', align: 'center', valign: 'middle'},
            {title: '请求地址', field: 'href', align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', align: 'center', valign: 'middle'}
            // {title: '操作', field: 'operate', align: 'center', valign: 'middle', width: '140px', formatter: operateFormatter, events: operateEvents}
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

$(function () {
    //使用严格模式
    "use strict";

    //调用函数，初始化表格
    initTable();
    //当点击查询按钮的时候执行
    $("#query").bind("click", initTable);
});