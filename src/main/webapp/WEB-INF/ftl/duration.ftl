<#include "head.ftl"/>
<div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a>时间 </h3>
        </div>
        <div class="panel-body">
            <div class="content-row">
                <div class="row">
                    <div class="col-sm-12" id="chartDuration" style="height: 800px">

                    </div>
                    <#--<div class="col-sm-4" id="chartMax" style="height: 800px">-->

                    <#--</div>-->
                    <#--<div class="col-sm-4" id="chartMaxDay" style="height: 800px">-->

                    <#--</div>-->
                </div>
                <div class="row" id="chart1" style="height: 800px">

                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var color = ['#48CFAD', '#CCD1D9', '#ED5565'];
        var labels = ['生涯', '在路上', '运动时长'];
        var data=[${duration_sum.days},${duration_sum.on_the_way},${duration_sum.sport_duration}];
        var series = [];
        for(var i = 0;i < data.length;i ++){
            series.push({
            name: 'UFD占比',
            type: 'pie',
            selectedMode: 'single',
            radius: ['0%', 80 * Math.sqrt(data[i] / data[0])+'%'],
            center:['50%', 90-40 * Math.sqrt(data[i] / data[0]) +'%'],
            label: {
                normal: {
                    position: 'inner',
                    textStyle: {
                        color: '#fff',
                        fontSize: 14,
                        padding:[0,0,Math.sqrt(data[i] / data[0])*700,0]
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: 1,
                name: labels[i] + "\n\n" + Math.floor(data[i]/60/60/24) + "-" + Math.floor(data[i]%(60*60*24)/60/60) + ":" + Math.floor(data[i]%(60*60)/60) + ":" + Math.floor(data[i]%(60)),
                itemStyle: {normal:{color:color[i]}}
            }]
        });
        }
        echarts.init(document.getElementById('chartDuration')).setOption({
            backgroundColor: "#434A54",
            silent:true,
            title: {
                text: '时间',
                left: 0,
                top: 0,
                textStyle: {
                    color:'#ccc',
                    fontWeight: 'normal',
                    fontSize: 22
                }
            },
            legend: {
                show:false
            },
            series: series
        });
    </script>

<#include "footer.ftl" />