var url = './adminController/getImageList';
var control = $('#file');
initFileUpload();
initTable();

function initTable() {
    $('#table').bootstrapTable({
        method: 'GET',
        dataType: 'json',
        toolbar: '#query',
        //contentType: "application/x-www-form-urlencoded",
        cache: false,
        striped: true,                      //是否显示行间隔色
        clickToSelect: true,                //是否启用点击选中行
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        // sortable: true,
        // sortOrder: "sname",
        height: $(window).height(),
        url: url,
        showColumns: true,
        pagination: true,                 	//是否启用分页
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 5,                        //每页的记录行数（*）
        pageList: [5, 10, 15, 25, 30],           //可供选择的每页的行数（*）
        queryParams: queryParams,			//传递参数
        minimumCountColumns: 2,
        uniqueId: "sid",                    //每一行的唯一标识，一般为主键列
        responseHandler: responseHandler,
        columns: [{
            field: '',
            title: '编号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'imagepath',
            title: '图片',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<img  onclick="bigImg(this)" src = "' + row.imagepath + '" style= "height:50px;width:50px" >点击查看大图</img>'
            }
        }, {
            field: 'user.nikename',
            title: '创建者',
            align: 'center',
            valign: 'middle',
        }, {
            field: 'classes.classname',
            title: '分组',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'state',
            title: '类型',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (row.state == 0) {
                    return "未标注";
                } else if (row.state == 1) {
                    return "标注中";
                } else if (row.state == -1) {
                    return "异常";
                }
            }
        }, {
            field: 'upload',
            title: '上传时间',
            align: 'center',
            valign: 'middle',
        }
        ]
    });
}

function doQuery(params) {
    $('#table').bootstrapTable('refresh');    //刷新表格
}

function queryParams(params) {
    var param = {
        state:$('#state').val()==99?null:$('#state').val(),
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


function initFileUpload() {
    control.fileinput({
        language: 'zh',  //设置语言
        uploadUrl: './adminController/uploadImageBatch',
//                uploadAsync:false,
        dropZoneEnabled: true,
        showCaption: false,   //是否显示标题
        showPreview: true,
        showUpload: false,//是否显示上传按钮
        allowedFileExtensions: ['zip'],
        maxFileCount: 10,
        enctype: 'multipart/form-data',
        browseClass: "btn btn-success"
    })
        .on("fileuploaded", function (event, data, c, d) {
            if ((data.response)) {
                if (data.response.result==1) {
                    bootbox.alert("处理成功");
                } else if(data.response.result==-1){
                    bootbox.alert("压缩包内图片至少9张");
                }else {
                    bootbox.alert("处理失败");
                }
            } else {
                bootbox.alert("处理失败");
            }

        })
        .on("filebatchselected", function (event, files) {
            $(this).fileinput("upload");
        })
        .on("fileerror", function (a, b, c) {
            bootbox.alert("失败");
        });
}



function bigImg(obj) {
    $('.winright img').attr("src", obj.src);
    var windowW = $(window).width();
    var windowH = $(window).height();
    var rheight = (obj.height * 750) / obj.width;
    var w = (windowW - 750) / 2;
    if (rheight > windowH) {
        var h = 10;
    } else {
        var h = (windowH - rheight) / 2 - 30;
    }

    var myAlert = document.getElementById("imgBig");
    myAlert.style.display = "block";
    myAlert.style.position = "fixed";
    myAlert.style.top = h + "px";
    myAlert.style.left = w + "px";
    var bgObj = document.getElementById("bgDiv");
    bgObj.style.display = "block";
    bgObj.style.position = "fixed";
    bgObj.style.top = "0";
    bgObj.style.left = "0";
    bgObj.style.background = "#777";
    bgObj.style.filter = "alpha(opacity:40)";
    bgObj.style.zoom = "1";
    bgObj.style.opacity = "0.6";
    bgObj.style.width = "100%";
    bgObj.style.height = "100%";
}



function closeDesignPanel() {
    $('#desginPanel').hide();
}

function openDesignPanel(isAdd, id, name, rate, isUse) {
    $('#desginPanel').show();
}








