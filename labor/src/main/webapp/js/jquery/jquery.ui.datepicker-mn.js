﻿/* Mongolian initialisation for the jQuery UI date picker plugin. */
jQuery(function ($) {
	
	$.datepicker.regional['mn'] = {
		clearStatus: '',
		closeText: 'Хаах',
		prevText: 'Өмнөх',
		nextText: 'Дараах',
		currentText: 'Одоо',
		changeMonth: true,
	    changeYear: true,
		monthNames: ['1 дүгээр сар','2 дугаар сар','3 дугаар сар','4 дүгээр сар','5 дугаар сар','6 дугаар сар',
		'7 дугаар сар','8 дугаар сар','9 дүгээр сар','10 дугаар сар','11 дүгээр сар','12 дугаар сар'],
		monthNamesShort: ['1 сар','2 сар','3 сар','4 сар','5 сар','6 сар',
		'7 сар','8 сар','9 сар','10 сар','11 сар','12 сар'],
		dayNames: ['Ням','Даваа','Мягмар','Лхагва','Пүрэв','Баасан','Бямба'],
		dayNamesShort: ['Ням','Дав','Мяг','Лха','Пүр','Баа','Бям'],
		dayNamesMin: ['Ня','Да','Мя','Лх','Пү','Ба','Бя'],
		firstDay: 1,		
		isRTL: false,
		yearSuffix:''
	};
	
	$.datepicker.setDefaults($.datepicker.regional['mn']);
	

});