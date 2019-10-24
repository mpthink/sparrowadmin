//alarm table JS
var taskRecordId = $('#taskRecordId').val();
var dataUrl = '/remexplus/job/listJobs/' + taskRecordId;
var jobDetail = 'http://remex.compellent.com:8080/commander/jobs/jobDetail?jobID=';
var dataColumns = [
    {
        field: 'Number',
        title: 'No',
        formatter: function (value, row, index) {
            return index + 1;
        }
    }, {
        field: 'name',
        title: 'Job Name'
    }, {
        field: 'remex_job_id',
        title: 'Remex Job ID',
        formatter: function (value, row, index) {
            var jobId = row.remex_job_id;
            var jobUrl = jobDetail + jobId;
            return '<a class="btn btn-xs btn-primary" href="' + "" + jobUrl + "" + '" target="_blank">Job Detail</a>';
        }
    }, {
        field: 'status',
        title: 'Job Status'
    }, {
        field: 'result',
        title: 'Job Result'
    }, {
        field: 'task',
        title: 'Task Name'
    }];

function taskRecordsClientPagination() {
    $('#dataTable').bootstrapTable({
        url: dataUrl,
        method: 'get',
        toolbar: '#toolbar',
        striped: true,
        cache: true,
        pagination: true,
        sortName: "name",
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

//init functions
$(function () {
    taskRecordsClientPagination();
})
