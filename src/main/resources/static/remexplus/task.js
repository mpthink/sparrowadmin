var fileUploadUrl = "/file/upload/remexplus";
var fileDeleteUrl = "/file/delete";

//初始化fileinput控件
function initFileInput(ctrlName) {
    var control = $('#' + ctrlName);
    control.fileinput({
        initialPreviewAsData: false,
        deleteUrl: fileDeleteUrl,
        language: 'zh', //设置语言
        uploadUrl: fileUploadUrl, //上传的地址
        allowedFileExtensions : ['xml'],//接收的文件后缀
        browseClass: "btn btn-primary"
    });
}

initFileInput("remexPomFile");

//上传成功回调函数
$('#remexPomFile').on("fileuploaded", function(event, data, previewId, index) {
    var result = data.response;
    if(result.status == 'success'){
        $('#remexPom').val(result.urls[0]);
    }else{
        layer.alert(result.content, {icon: 0,title:'Info',closeBtn: 0,skin: 'layui-layer-lan',btn: ['OK']});
    }
    //console.log(result.status);
    //console.log(result.urls);
});

initFileInput("submitPomFile");

//上传成功回调函数
$('#submitPomFile').on("fileuploaded", function(event, data, previewId, index) {
    var result = data.response;
    if(result.status == 'success'){
        $('#submitPom').val(result.urls[0]);
    }else{
        layer.alert(result.content, {icon: 0,title:'Info',closeBtn: 0,skin: 'layui-layer-lan',btn: ['OK']});
    }
});

function addTask() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/remexplus/task/doAdd",
        data: $('#addTaskForm').serialize(),
        success: function (result) {
            //console.log(result);
            if (result.code == 200) {
                layer.alert("Success", {icon: 6,title:'Info',closeBtn: 0, time:5000, skin: 'layui-layer-molv',btn: ['OK']});
                parent.layer.closeAll();
                parent.location.replace("/remexplus/task/list/1")
            }else {
                layer.alert(result.msg, {icon: 5,title:'Info',closeBtn: 0,time:5000 ,skin: 'layui-layer-lan',btn: ['OK']});
            }
        },
        error : function() {
            layer.alert("Submit ajax data exception", {icon: 2,title: 'Info',time:5000, closeBtn: 0,skin: 'layui-layer-lan',btn: ['OK']});
        }
    });

};