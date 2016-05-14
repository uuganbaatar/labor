Tapestry.Initializer.eventFullCalendar = function(eventData) {

	var titleFormat = {
		month : 'yyyy оны MMM',
		week : "yyyy оны MMM d[ yyyy]{ '&#8212;'[ MMM] d }",
		day : 'yyyy оны MMM d, dddd '
	}, monthNames = [ '1 дүгээр сар', '2 дугаар сар', '3 дугаар сар',
			'4 дүгээр сар', '5 дугаар сар', '6 дугаар сар', '7 дугаар сар',
			'8 дугаар сар', '9 дүгээр сар', '10 дугаар сар', '11 дүгээр сар',
			'12 дугаар сар' ], monthNamesShort = [ '1 сар', '2 сар', '3 сар',
			'4 сар', '5 сар', '6 сар', '7 сар', '8 сар', '9 сар', '10 сар',
			'11 сар', '12 сар' ], dayNames = [ 'Ням', 'Даваа', 'Мягмар',
			'Лхагва', 'Пүрэв', 'Баасан', 'Бямба' ], dayNamesShort = [ 'Ням',
			'Дав', 'Мяг', 'Лха', 'Пүр', 'Баа', 'Бям' ], today = 'Өнөөдөр', month = 'Сар', week = 'Долоо хоног', day = 'Өдөр', allDayText = 'Бүх өдөр';

	$('#eventFullCalendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
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
		},
		eventClick : function(calEvent, jsEvent, view) {
			var month=calEvent.start.getMonth()+1;
			var customStart=calEvent.start.getFullYear()+"/"+month+"/"+calEvent.start.getDate()
			+" "+calEvent.start.getHours()+":"+calEvent.start.getMinutes();
			var month1=calEvent.end.getMonth()+1;
			var customEnd=calEvent.end.getFullYear()+"/"+month1+"/"+calEvent.end.getDate()
			+" "+calEvent.end.getHours()+":"+calEvent.end.getMinutes();
			
			
			$('#customStartDate').val(customStart);
			$('#customEndDate').val(customEnd);
			$('#customTitle').val(calEvent.title);
			$('#customContent').val(calEvent.description);
			
		
			if(eventData.currentUserId==calEvent.userId){
				$('#delete').show();
				$('#eventEdit').show();
			}else{
				$('#delete').hide();
				$('#eventEdit').hide();
			}
			
			var url = location.href + ':eventSelected';
			jQuery.ajax({
				url : url,
				data : {
					eventId : calEvent.id
				}
			}).done(function(html) {
				$('#userZone').html(html.content);
			});
			$('#formZoneContainer').hide();	
			$('#customForm').show();
		}
	});
};
