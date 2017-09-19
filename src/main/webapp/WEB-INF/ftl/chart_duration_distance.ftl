<#include "head.ftl"/>
<div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a>数据同步 </h3>
        </div>
        <div class="panel-body">
            <div class="content-row">
                <div class="row" id="chart1" style="height: 800px"></div>
                <div class="row" id="chart2" style="height: 800px"></div>
            </div>
        </div>
</div>
<script type="text/javascript">
    var weekName = ['星期一','星期二','星期三','星期四','星期五','星期六','星期日'];
    var data = [
        [1,0,14],
        [2,8,14],
        [1,18,14],
        [4,18,14],
        [3,8,14],
        [5,18,14],
        [1,4,14],
        [5,6,27],
        [2,18,67],
        [3,8,98],
        [2,18,300],
        [3,5,21],
        [3,3,6],
        [6,7,120],
        [7,21,70],
        [7,12,44],
        [2,8,18],
        [6,23,66],
        [6,11,20]];

    var option = {
        backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
            offset: 0,
            color: '#242424'
        }, {
            offset: 1,
            color: '#282828'
        }]),
        title: {
            text: '时间里程分布',
            textStyle:{
                color:'#CCC'
            }
        },
        xAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
            axisLabel:{
                color:'#CCC',
                formatter:function(value, index){
                    return weekName[value - 1];
                }
            },
            min:0,
            max:7
        },
        yAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
            scale: true,
            min:0,
            max:24,
            axisLabel:{
                color:'#CCC',
                formatter: '{value} 时'
            }
        },
        series: [{
            data: data,
            type: 'scatter',
            symbolSize: function (data) {
                return data[2];
            },
            label: {
                emphasis: {
                    show: true,
                    formatter: function (param) {
                        return param.data[2] + "km";
                    },
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(25, 100, 150, 0.5)',
                    shadowOffsetY: 5,
                    color: '#4FC1E9',
                    opacity:'0.5'
                }
            }
        }]
    };
    var chart1 = echarts.init(document.getElementById('chart1'));
    chart1.setOption(option);
</script>

<#include "footer.ftl" />