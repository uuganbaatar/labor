define(
		[ "jquery", "t5/core/messages" ],
		function($, messages) {

			return function(chartType, chartData) {
				$(function() {
					// Build the chart
					Highcharts.setOptions({
						 colors: [ '#7CB5EC', '#FF6060', '#41BA55']
						});
					if (chartType == 'pie') {
						$('#pie')
								.highcharts(
										{
											chart : {
												plotBackgroundColor : null,
												plotBorderWidth : null,
												plotShadow : false,
												backgroundColor : 'transparent'
											},
											title : {
												text : ''
											},
											tooltip : {
												pointFormat : '{series.name}: <b>{point.y:.0f}</b>'
											},
											plotOptions : {
												pie : {
													allowPointSelect : true,
													cursor : 'pointer',
													dataLabels : {
														enabled : false
													},
													showInLegend : true
												}
											},
											series : [ {
												type : 'pie',
												name : messages('total-label'),
												data : chartData
											} ],
											credits : {
												enabled : false
											},
											lang : {
												noData : messages('core-no-grid-data-to-display')
											}
										});
					} else if (chartType == 'bar') {
						$('#bar')
								.highcharts(
										{
											chart : {
												type : 'column',
												backgroundColor : 'transparent'
											},
											title : {
												text : ''
											},
											xAxis : {
												categories : chartData[0],
												title : {
													text : null
												}
											},
											yAxis : {
												min : 0,
												title : {
													text : messages('total-label')
												}
											},
											tooltip : {
												headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
												pointFormat : '<tr><td style="color:{series.color};padding:0">{series.key}: </td>'
														+ '<td style="padding:0"><b>{point.y:.0f}</b></td></tr>',
												footerFormat : '</table>',
												shared : true,
												useHTML : true
											},
											plotOptions : {
												column : {
													pointPadding : 0.2,
													borderWidth : 0
												}
											},

											legend : {
												enabled : false
											},
											series : [
													{
														name : messages('formNum-label'),
														data : chartData[1]
													} ],
											credits : {
												enabled : false
											}
										});
					} else if (chartType == 'spiderweb') {
						$('#spiderweb')
								.highcharts(
										{

											chart : {
												polar : true,
												type : 'line'
											},

											title : {
												text : '',
												x : -80
											},

											pane : {
												size : '80%'
											},

											xAxis : {
												categories : chartData[0],
												tickmarkPlacement : 'on',
												lineWidth : 0
											},

											yAxis : {
												gridLineInterpolation : 'polygon',
												lineWidth : 0,
												min : 0,
												title:""
											},

											tooltip : {
												shared : true,
												pointFormat : '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
											},

											legend : {
												enabled : false
											},

											series : [ {
												name : 'Too',
												data : chartData[1],
												pointPlacement : 'on'
											} ]

										});
					} else {
						$('#stackedBar').highcharts({
							chart : {
								type : 'bar'
							},
							title : {
								text : ''
							},
							xAxis : {
								categories : chartData[0]
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Тоо:'
								}
							},
							legend : {
								reversed : true
							},
							plotOptions : {
								series : {
									stacking : 'normal'
								}
							},
							series : [ {
								name : 'Мэдээллээ оруулах',
								data : chartData[1]
							}, {
								name : 'Мэдээллээ оруулсан',
								data : chartData[2]
							}, {
								name : 'Хоцорсон',
								data : chartData[3]
							}, {
								name : 'Засварласан',
								data : chartData[4]
							}, {
								name : 'Мэдээллээ оруулаагүй',
								data : chartData[5]
							} ],
							credits : {
												enabled : false
											},
											lang : {
												noData : messages('core-no-grid-data-to-display')
											}
						});
					}

				})
			}
		})