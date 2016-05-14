$(document).ready(function() {
	$("#startDate").datepicker({
		onSelect : function(selected) {
			$("#endDate").datepicker("option", "minDate", selected)
		}
	});
	$("#endDate").datepicker({
		onSelect : function(selected) {
			$("#startDate").datepicker("option", "maxDate", selected)
		}
	});
	$("#regStartDate").datepicker({
		onSelect : function(selected) {
			$("#regEndDate").datepicker("option", "minDate", selected)
		}
	});
	$("#regEndDate").datepicker({
		onSelect : function(selected) {
			$("#regStartDate").datepicker("option", "maxDate", selected)
		}
	});
});