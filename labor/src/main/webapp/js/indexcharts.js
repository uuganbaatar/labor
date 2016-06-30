$(function () {
    $('#container').highcharts({
        data: {
            table: 'datatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: 'Ажилчдын харьцаа'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: ''
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + ' ' + this.point.name.toLowerCase();
            }
        }
    });
});

$(function () {
    $('#jobchart').highcharts({
        chart: {
            type: 'area'
        },
        title: {
            text: 'Ажлын байрны орон тоо болон шинээр нэмэгдсэн'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: ['2010', '2011', '2012', '2013', '2014', '2015', '2016'],
            tickmarkPlacement: 'on',
            title: {
                enabled: false
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            labels: {
                formatter: function () {
                    return this.value / 1000;
                }
            }
        },
        tooltip: {
            shared: true,
            valueSuffix: ' '
        },
        plotOptions: {
            area: {
                stacking: 'normal',
                lineColor: '#666666',
                lineWidth: 1,
                marker: {
                    lineWidth: 1,
                    lineColor: '#666666'
                }
            }
        },
        series: [{
            name: 'Ажлын байрны тоо',
            data: [5, 6, 9, 7, 10, 6, 2]
        }, {
            name: 'Шинээр нэмэгдсэн',
            data: [2, 3, 1, 6, 13, 0, 6]
        }]
    });
});