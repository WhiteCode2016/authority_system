//  为操作按钮添加事件
window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        alert('You click like icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
    }
};

$("#menuTable").bootstrapTable({
    method: 'get',
    url : "/menu/list",
    toolbar : "#toolbar",
    pagination : true,  // 是否显示分页（*）
    pageNumber:1,      // 初始化加载第一页，默认第一页
    pageSize: 10,      // 每页的记录行数（*）
    pageList: [10, 25, 50, 100],  // 可供选择的每页的行数（*）
    striped : true,     // 是否显示行间隔色
    cache : false,      // 是否使用缓存
    uniqueId: "id", // 每一行的唯一标识，一般为主键列
    sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
    queryParamsType : '', // 默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                            // 设置为 '' 在这种情况下传给服务器的参数为：pageSize,pageNumber
    columns : [
        {field: 'selectItem', checkbox: true},
        {title: '编号', field: 'id', align: 'center', valign: 'middle'},
        {title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle'},
        {title: '菜单父编号', field: 'parentId', align: 'center', valign: 'middle'},
        {title: '请求地址', field: 'href', align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle'},
        {title: '操作', field: 'operate', align: 'center', valign: 'middle', width: '140px', formatter: operateFormatter, events: operateEvents
        }
    ]
});

// 自定义方法，添加操作按钮
function operateFormatter (value, row, index) {
    return [
        '<button type="button" class="btn btn-success btn-xs" id="edit"><i class="glyphicon glyphicon-pencil"></i> 修改</button>',
        '<button type="button" class="btn btn-danger btn-xs" id="delete" style="margin-left: 5px;"><i class="glyphicon glyphicon-remove"></i> 删除</button>'
    ].join('');
}

