Tapestry.Initializer.smallFullCalendar = function(eventData) {

	var titleFormat = {
		month : 'yyyy оны MMM',
		week : "yyyy оны MMM d[ yyyy]{ '&#8212;'[ MMM] d }",
		day : 'yyyy оны MMM d, dddd '
	}, monthNames = [ '1-р сар', '2-р сар', '3-р сар',
			'4-р сар', '5-р сар', '6-р сар', '7-р сар',
			'8-р сар', '9-р сар', '10-р сар', '11-р сар',
			'12-р сар' ], monthNamesShort = [ '1 сар', '2 сар', '3 сар',
			'4 сар', '5 сар', '6 сар', '7 сар', '8 сар', '9 сар', '10 сар',
			'11 сар', '12 сар' ], dayNames = [ 'Ням', 'Даваа', 'Мягмар',
			'Лхагва', 'Пүрэв', 'Баасан', 'Бямба' ], dayNamesShort = [ 'Ням',
			'Дав', 'Мяг', 'Лха', 'Пүр', 'Баа', 'Бям' ], today = 'Өнөөдөр', month = 'Сар', week = 'Долоо хоног', day = 'Өдөр', allDayText = 'Бүх өдөр';

	

	$('#smallfullCalendar').fullCalendar({
		
		timeFormat : {
			'' : 'HH:mm'
		},
		axisFormat : 'HH:mm',
		monthNames : monthNames,
		monthNamesShort : monthNamesShort,
		dayNames : dayNames,
		dayNamesShort : dayNamesShort,
		allDayText : allDayText,
		buttonText : {
			prev : '&nbsp;&#9668;&nbsp;',
			next : '&nbsp;&#9658;&nbsp;',
			prevYear : '&nbsp;&lt;&lt;&nbsp;',
			nextYear : '&nbsp;&gt;&gt;&nbsp;',
			today : today,
			month : month,
			week : week,
			day : day
		},
		eventSources : [ eventData.data ],
		eventMouseover: function(calEvent, jsEvent) {
			var month=calEvent.start.getMonth()+1;
			
			var customStart=calEvent.start.getFullYear()+"/"+month+"/"+calEvent.start.getDate()
			+" "+calEvent.start.getHours()+":"+calEvent.start.getMinutes();
			var month1=calEvent.end.getMonth()+1;
			
			var customEnd=calEvent.end.getFullYear()+"/"+month1+"/"+calEvent.end.getDate()
			+" "+calEvent.end.getHours()+":"+calEvent.end.getMinutes();
			


		    var tooltip = '<div class="tooltipevent"'+
		    			  ' style="padding: 8px; width:180px;height:80px;'+
		    			  ' display:inline; position:absolute; color:#111;'+
		    			  ' border:1px solid #DCA; background:#fffAF0;'+
		    			  ' z-index:10001;"><b>' + calEvent.title +'</b> <br/>'
		    			  +customStart+' - '+customEnd+'</div>';
		    
		    
		    $("body").append(tooltip);
		    $(this).mouseover(function(e) {
		        $(this).css('z-index', 10000);
		        $('.tooltipevent').fadeIn('500');
		        $('.tooltipevent').fadeTo('10', 1.9);
		    }).mousemove(function(e) {
		        $('.tooltipevent').css('top', e.pageY + 10);
		        $('.tooltipevent').css('left', e.pageX + 20);
		    });
		},
		eventMouseout: function(calEvent, jsEvent) {
		    $(this).css('z-index', 8);
		    $('.tooltipevent').remove();
		}
	});
};
