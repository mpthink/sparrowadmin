//alarm table JS
var dataUrl = '/remexplus/taskRecord/getAll';
var dataColumns = [
    {
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
            return '<a class="btn btn-xs btn-primary" href="/remexplus/job/list/' + "" + taskRecordId + "" + '">Display Jobs</a>';
        }
    }];

function taskRecordsClientPagination() {
    $('#dataTable').bootstrapTable({
        url: dataUrl,
        method: 'get',
        toolbar: '#toolbar',
        striped: true,
        cache: true,
        pagination: true,
        sortName: "gmt_create",
        sortable: true,
        sortOrder: "desc",
        sidePagination: "client",
        pageNumber: 1,
        pageSize: 10,
        pageList: [30, 50, 100],
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
