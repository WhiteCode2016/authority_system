//  为操作按钮添加事件
window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        alert('You click like icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
    },
    'click #delete': function (e, value, row, index) {
        alert(row.id);
    }
};

var menuTable = $("#menuTable").bootstrapTable({
    url : "/menu/list_parent_menu",
    method: 'get',                      //请求方式（*）
    toolbar: '#toolbar',                //工具按钮用哪个容器
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    sortable: false,                     //是否启用排序
    sortOrder: "asc",                   //排序方式
    queryParamsType: '',
    queryParams: queryParams,         //传递参数（*）
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber: 1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
    // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    strictSearch: true,
    // showColumns: true,                  //是否显示所有的列
    // showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: true,                //是否启用点击选中行
    height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
    // showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false, //是否显示父子表
    columns : [
        {field: 'selectItem', checkbox: true},
        {title: '编号', field: 'id', align: 'center', valign: 'middle'},
        {title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle'},
        {title: '菜单父编号', field: 'parentId', align: 'center', valign: 'middle'},
        {title: '请求地址', field: 'href', align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle'},
        {title: '操作', field: 'operate', align: 'center', valign: 'middle', width: '140px', formatter: operateFormatter, events: operateEvents}
    ],
    // 注册加载子表的事件。注意下这里的三个参数！
    // onExpandRow: function (index, row, $detail) {
    //     InitSubTable(index, row, $detail);
    // }
});

// 自定义方法，添加操作按钮
function operateFormatter (value, row, index) {
    return [
        '<button type="button" class="btn btn-success btn-xs" id="edit"><i class="glyphicon glyphicon-pencil"></i> 修改</button>',
        '<button type="button" class="btn btn-danger btn-xs" id="delete" style="margin-left: 5px;"><i class="glyphicon glyphicon-remove"></i> 删除</button>'
    ].join('');
}

function InitSubTable(index, row, $detail) {
    var parentId = row.id;
    var cur_table = $detail.html('<table></table>').find('table');
    $(cur_table).bootstrapTable({
        url: '/menu/list_chid_menu?parentId='+parentId,
        method: 'get',
        pagination:false,
        sidePagination : "server",
        // queryParams: {
        //     sortOrder:"asc",
        //     pageSize:"10",
        //     pageNumber:"1",
        //     parentId: parentid },
        clickToSelect: true,
        uniqueId: "id",
        pageSize: 10,
        pageList: [10, 25],
        columns: [
            {field: 'selectItem', checkbox: true},
            {title: '编号', field: 'id', align: 'center', valign: 'middle'},
            {title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle'},
            {title: '菜单父编号', field: 'parentId', align: 'center', valign: 'middle'},
            {title: '请求地址', field: 'href', align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', align: 'center', valign: 'middle'},
            {title: '操作', field: 'operate', align: 'center', valign: 'middle', width: '140px', formatter: operateFormatter, events: operateEvents}
        ],
        //无线循环取子表，直到子表里面没有记录
        //onExpandRow: function (index, row, $Subdetail) {
        //    InitSubTable(index, row, $Subdetail);
        //}
    });
};

// 查询参数
function queryParams(params) {
    var temp = {
        pageSize : params.pageSize,
        pageNumber : params.pageNumber,
        menuName : $("#menuName").val(),
    };
    return temp;
}