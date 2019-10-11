//alarm table JS
var dataUrl = '/remexplus/taskRecord/getAll';
var dataColumns = [
    {
       checkbox: true
    }, {
        field: 'Number',
        title: 'No',
        formatter: function (value, row, index) {
            return index + 1;
        }
    }, {
        field: 'name',
        title: 'Task Name'
    }, {
        field: 'gmt_create',
        title: 'Running time',
        formatter: function (value, row, index) {
            return changeDateFormat(value)
        }
    }, {
        title: 'Jobs Status',
        formatter: function (value, row, index) {
            var taskRecordId = row.id;
            return '<a class="btn btn-xs btn-primary" href="/remexplus/job/' + "" + taskRecordId + "" + '">Display</a>';
        }
    }];


$('#batchDeleteBtn').on("click",function () {
    var index = layer.confirm('Are you sure to delete selected itmes？', {icon: 0,title:'Info',closeBtn: 0,skin: 'layui-layer-molv',btn: ['Confirm','Cancel']}, function(){
        var rows = $("#dataTable").bootstrapTable('getSelections');// 获得要删除的数据
        if (rows.length == 0) {// rows 主要是为了判断是否选中，下面的else内容才是主要
            layer.alert('Please select items to delete!', {icon: 0,title:'Info',closeBtn: 0,skin: 'layui-layer-lan',btn: ['OK']});
            return;
        } else {
            var ids = new Array();// 声明一个数组
            $(rows).each(function() {// 通过获得别选中的来进行遍历
                ids.push(this.id);// cid为获得到的整条数据中的一列
            });
            deleteRecords(ids)
        }
        layer.close(index);
    });
});

var deleteUrl = '/remexplus/taskRecord/delete';

function deleteRecords(ids) {
    $.ajax({
        url : deleteUrl,
        data: {
            ids: ids.toString()
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            $('#dataTable').bootstrapTable('refresh', {
                url : dataUrl
            });
        }
    });
}

function taskRecordsClientPagination() {
    $('#dataTable').bootstrapTable({
        url: dataUrl,
        method: 'get',
        toolbar: '#toolbar',
        striped: true,
        cache: true,
        pagination: false,
        sortName: "gmt_create",
        sortable: true,
        sortOrder: "asc",
        sidePagination: "client",
        pageNumber: 1,
        pageSize: 20,
        pageList: [50, 100],
        strictSearch: false,
        clickToSelect: true,
        cardView: false,
        detailView: false,
        showRefresh: false,
        search: false,
        searchAlign: 'right',
        columns: dataColumns
    });
}

//转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval) {
    var dateVal = cellval + "";
    if (cellval != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }
}

//init functions
$(function () {
    taskRecordsClientPagination();
})
