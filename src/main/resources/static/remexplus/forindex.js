var dataUrl = '/remexplus/task/last/status';

function viewTaskStatus(){
    require.config({
        paths: {
            echarts: '/plugins/echart/dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            var Chart=ec.init(document.getElementById("main"));
            Chart.showLoading({text: 'Loding...'});

            var taskName=[];
            var passedNum=[];
            var failedNum=[];
            var otherNum=[];
            $.ajax({
                url:dataUrl,
                dataType:"json",
                type:'post',
                success:function(result){
                    if(result==null||result==''){
                        //layer.alert('No data found', {icon: 0,title:'Info',closeBtn: 0,skin: 'layui-layer-molv',btn: ['OK']});
                        Chart.hideLoading();
                    }else{
                        for(var i=0;i<result.length;i++) {
                            taskName.push(result[i]['task']);
                            passedNum.push(result[i]['passed']);
                            failedNum.push(result[i]['failed']);
                            otherNum.push(result[i]['other']);
                        }
                    }

                    var option = {
                        title : {
                            text: 'Task Last Running Result',
                            textStyle:{
                                fontSize: 15,
                                fontWeight: 'bolder',
                                color: '#333'
                            }
                        },
                        noDataLoadingOption: {
                            text: 'No data found',
                            textStyle: {fontSize:16,fontWeight:'bold'},
                            effect:'bubble'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        legend: {
                            x:"center",
                            textStyle:{color:'auto'},
                            borderColor:'#333',
                            borderWidth:1,
                            data: ['Passed','Failed','Other']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis: [
                            {
                                type: 'category',
                                data: taskName,
                                axisLabel:{
                                    interval:0,
                                    rotate:55,
                                    margin:2
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel : {
                                    formatter: '{value}'
                                }
                            }
                        ],
                        series: [
                            {
                                'name': 'Passed',
                                'type': 'bar',
                                'stack':'group1',
                                barWidth: 30,
                                'data': passedNum,
                                itemStyle: { normal: { color: '#228B22' } }
                            },
                            {
                                'name': 'Failed',
                                'type': 'bar',
                                'stack':'group1',
                                barWidth: 30,
                                'data': failedNum,
                                itemStyle: { normal: { color: '#d33724' } }

                            },
                            {
                                'name': 'Other',
                                'type': 'bar',
                                'stack':'group1',
                                barWidth: 30,
                                'data': otherNum,
                                itemStyle: { normal: { color: '#f7ed00' } }

                            }
                        ]
                    };
                    Chart.hideLoading();
                    Chart.setOption(option);
                },
                error : function() {
                    //请求失败时执行该函数
                    alert("Failed to get ajax data!");
                    Chart.hideLoading();
                }
            });
        })
}

//init functions
$(function(){
    viewTaskStatus();
})