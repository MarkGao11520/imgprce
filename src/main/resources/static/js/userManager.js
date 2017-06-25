var url = './adminController/getAdminList';
initTable();

function initTable() {
    $('#table').bootstrapTable({
        dataType: 'json',
        cache: false,
        striped: true, //是否显示行间隔色
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        url: url,
        toolbar: '#query',
        height: $(window).height() - 110,
        //				width: $(window).width(),
        showColumns: true,
        pagination: true,
              queryParams : queryParams,
        minimumCountColumns: 2,
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 7, //每页的记录行数（*）
        pageList: [7, 10, 15, 20], //可供选择的每页的行数（*）
        // uniqueId: "id", //每一行的唯一标识，一般为主键列
        exportDataType: 'all',
        responseHandler: responseHandler,
        columns: [{
            field: '',
            title: '编号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'login.uname',
            title: '用户名',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'nikename',
            title: '昵称',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'qq',
            title: '手机号',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: '',
            title: '操作',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: function (value, row, index) {
                var str = '';
                str += '<button class="btn btn-warning" onclick="openDesignPanel(' + false + ',' + row.login.id + ',\'' + row.nikename + '\'' + ',\'' + row.qq + '\'' + ')">编辑</button>&nbsp;&nbsp;';
                return str;
            }
        }
        ]
    });
}

function queryParams(params) {
    var param = {
        page: this.pageNumber,
        rows: this.pageSize,
    }
    return param;
}

// 用于server 分页，表格数据量太大的话 不想一次查询所有数据，可以使用server分页查询，数据量小的话可以直接把sidePagination: "server"  改为 sidePagination: "client" ，同时去掉responseHandler: responseHandler就可以了，
function responseHandler(res) {
    if (res) {
        return {
            "rows": res.list,
            "total": res.total
        };
    } else {
        return {
            "rows": [],
            "total": 0
        };
    }
}

function closeDesignPanel() {
    $('#desginPanel').hide();
    $('#nikeName').val("");
    $('#phone').val("");
    prizePic = null;
}

function openDesignPanel(isAdd, id, name, phone) {
    if (isAdd) {
        $('#desigenSumbit').attr('onclick', 'add()');
    }
    else {
        $('#nikeName').val(name);
        $('#phone').val(phone);
        $('#desigenSumbit').attr('onclick', 'update(' + id + ')');
    }
    $('#desginPanel').show();
}

function check() {
    if ($('#nikeName').val() == null || $('#nikeName').val() == "") {
        bootbox.alert("昵称不能为空");
        return false;
    } else if ($('#phone').val() == null || $('#phone').val() == "") {
        bootbox.alert("手机号不能为空")
        return false;
    } else {
        return true;
    }
}



function add() {
    if (check()) {
            $.ajax({
                type: 'post',
                url: './adminController/addAdmin',
                data: {
                    nikename: $('#nikeName').val(),
                    qq: $('#phone').val(),
                },
                success: function (result) {
                    if (result.result == 1) {
                        bootbox.alert("添加成功");
                        $('#table').bootstrapTable('refresh');    //刷新表格
                        closeDesignPanel();
                    } else if (result.result == 0) {
                        bootbox.alert("该用户名存在");
                    } else {
                        bootbox.alert("添加失败");
                    }
                },
                error: function (error) {
                    bootbox.alert("访问服务器失败")
                }
            })
        }
}

function update(id) {
    if (check()) {
            $.ajax({
                type: 'post',
                url: './androidController/updateUserByUid',
                data: {
                    uid:id,
                    nikename: $('#nikeName').val(),
                    qq: $('#phone').val()
                },
                success: function (result) {
                    if (result.result == 1) {
                        bootbox.alert("编辑成功");
                        $('#table').bootstrapTable('refresh');    //刷新表格
                        closeDesignPanel();
                    } else {
                        bootbox.alert("编辑失败");
                    }
                },
                error: function (error) {
                    alert("访问服务器失败")
                }
            })
        }
}
