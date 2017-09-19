# xingzheplus
行者骑行数据分析。

总里程饼图
```js
option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'horizontal',
        bottom: '1',
        data: ['上坡','下坡','平路']
    },
    series: [{
        name: '总里程',
        type: 'pie',
        radius: ['60%', '70%'],
        label: {
            normal: {
                position: 'center'
            }
        },
        data: [{
            value: 20,
            name: '平路',
            label: {
                normal: {
                    formatter: '14555.98km',
                    textStyle: {
                        fontSize: 50
                    }
                }
            },
            tooltip: {
                show: true,
                formatter:'{b}({c}km):{d}%'
            }
        }, {
            value: 80,
            name: '上坡',
            label: {
                normal: {
                    formatter: '\n总里程',
                    textStyle: {
                        color: '#555',
                        fontSize: 20
                    }
                }
            },
            tooltip: {
                show: true,
                formatter:'{b}({c}km):{d}%'
            },
            itemStyle: {
                normal: {
                    color: '#aaa'
                },
                emphasis: {
                    color: '#aaa'
                }
            }
        }, {
            value: 200,
            name: '下坡',
            label: {
                normal: {
                    formatter: '',
                    textStyle: {
                        color: '#123456',
                        fontSize: 20
                    }
                }
            },
            tooltip: {
                show: true,
                formatter:'{b}({c}km):{d}%'
            },
            itemStyle: {
                normal: {
                    color: '#123456'
                },
                emphasis: {
                    color: '#123456'
                }
            }
        }]
    }]
};

```

总时间饼图
```js
var values = [100, 30, 21];
var radius = [0.6];
for (var i = 1; i < values.length; ++i) {
    radius.push(Math.sqrt(radius[0] * radius[0] * values[i] / values[0]));
}
var colors = ['#48CFAD', '#4FC1E9', '#ED5565'];
var labels = ['生涯','在路上','运动时间'];
var top = 0.5;

var series = [];

for (var i = 0; i < values.length; ++i) {
    series.push({
        type: 'pie',
        silent: true,
        name: labels[values.length - i - 1],
        radius: [0, radius[i] * 100 + '%'],
        label: {
            normal: {
                show: true,
                position:'inside',
                padding:[0,0,radius[i]*1300,0],
                fontSize:18
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        color: [colors[i]],
        center: ['35%', (top + (-radius[i] + radius[0]) / 2) * 100 + '%'],
        data: [{
            value: 1,
            name:labels[i],
            itemStyle: {
                normal: {
                    color: colors[i],
                    shadowBlur: 30,
                    shadowColor: 'rgba(0, 0, 0, 0.25)'
                }
            }
        }],
        animationType: 'scale'
    });
}
myChart.setOption({
    legend: {
        data: labels,
        top: 'center',
        right: 10,
        orient: 'vertical'
    },
    series: series
});
```

//单个圆环
```js
var placeHolderStyle = {
    normal: {
        color: 'rgba(44,59,70,1)',//未完成的圆环的颜色
        label: {
            show: false
        },
        labelLine: {
            show: false
        }
    },
    emphasis: {
        color: 'rgba(44,59,70,1)'//未完成的圆环的颜色
    }
};
option = {
    silent:true,
    title: {
        text: '2080km',
        x: 'center',
        y: 'center',
        textStyle: {
            fontWeight: 'normal',
            color: "#0bb6f0",
            fontSize: 60
        }
    },
    backgroundColor: '#011128',
    color: ['#eb644b', '#313443', '#fff'],
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
            name: 'Line 2',
            label: {
                normal: {
                    position: 'center'
                }
            },
            type: 'pie',
            animation: false,
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
                            formatter: '\n\n\n\n\n总里程',
                            textStyle: {
                                color: '#555',
                                fontSize: 20
                            }
                        }
                    }
                }

            ]
        },


    ]
};
```
