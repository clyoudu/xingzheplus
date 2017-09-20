<#include "head.ftl"/>
<div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a>数据同步 </h3>
        </div>
        <div class="panel-body">
            <div class="content-row">
                <div class="row">
                    <div class="col-sm-4" id="chartSum" style="height: 800px">

                    </div>
                    <div class="col-sm-4" id="chartMax" style="height: 800px">

                    </div>
                    <div class="col-sm-4" id="chartMaxDay" style="height: 800px">

                    </div>
                </div>
                <div class="row" id="chart1" style="height: 800px">

                </div>
            </div>
        </div>
</div>
<script type="text/javascript">
    $(".toggle-sidebar").click();
    function getCircleOption(distance,name){
        return {
            silent:true,
            title: {
                text: distance + ' km',
                x: 'center',
                y: 'center',
                textStyle: {
                    fontWeight: 'normal',
                    color: "#ccc",
                    fontSize: 50
                }
            },
            backgroundColor: '#434A54',
            color: ['#4FC1E9', '#313443', '#fff'],
            tooltip: {
                show: false,
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            toolbox: {
                show: false,
                feature: {
                    mark: {
                        show: true
                    },
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            series: [{
                name: name,
                label: {
                    normal: {
                        position: 'center'
                    }
                },
                type: 'pie',
                animation: true,
                clockWise: false,
                radius: [220, 218],
                data: [{
                    value: 100,
                    name: '02',
                    itemStyle: {
                        emphasis: {
                            color: '#313443'
                        }
                    },
                    label: {
                        normal: {
                            formatter: '\n\n\n\n\n'+name,
                            textStyle: {
                                color: '#AAB2BD',
                                fontSize: 20
                            }
                        }
                    }
                }

                ]
            }]
        };
    }
    echarts.init(document.getElementById('chartSum')).setOption(getCircleOption(${distance.sum_distance},"总里程"));
    echarts.init(document.getElementById('chartMax')).setOption(getCircleOption(${distance.max_distance},"最长轨迹"));
    echarts.init(document.getElementById('chartMaxDay')).setOption(getCircleOption(${distance.max_distance_day},"单日最长轨迹"));

    var weekName = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
    var data = ${time_distance};

    var option = {
        backgroundColor: "#434A54",
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
                    return weekName[value];
                }
            },
            min:0,
            max:6
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