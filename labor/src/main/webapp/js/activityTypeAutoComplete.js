var nameList;
var codeList;
var option;
var url;
Tapestry.Initializer.activityTypeCustomAutoComplete = function(source) {
	option = source;
	codeList = new Array();
	nameList = new Array();
	
	var codeOb;
	var nameOb;
	url = option.url;
	
	for ( var i = 0; i < option.data.length; i++) {
		codeOb = {
			"idx" : option.data[i].id,
			"label" : option.data[i].code,
			"name" : option.data[i].name,
		}
		codeList.push(codeOb);

		nameOb = {
			"idx" : option.data[i].id,
			"label" : option.data[i].name,
			"code" : option.data[i].code
		}
		nameList.push(nameOb);
	}

	createAutoCompleteAType();
};

function createAutoCompleteAType() {

	$("#activityTypeCode").autocomplete({
		source : codeList,
		select : function(event, ui) {
			$("#activityTypeName").val(ui.item.name);
			$("#actCode").val(ui.item.label);
			activityTypeChange(ui.item.idx);
		},
		close : function(event, ui) {

		}
	});

	$("#activityTypeName").autocomplete({
		source : nameList,
		select : function(event, ui) {
			$("#activityTypeCode").val(ui.item.code);
			$("#actCode").val(ui.item.code);
			activityTypeChange(ui.item.idx);
		},
		close : function(event, ui) {

		}
	});
	
	$(".activityTypeCode").autocomplete({
		source : codeList,
		select : function(event, ui) {
			$(".activityTypeName").val(ui.item.name);
			activityTypeChange(ui.item.idx);
		},
		close : function(event, ui) {

		}
	});

	$(".activityTypeName").autocomplete({
		source : nameList,
		select : function(event, ui) {
			$(".activityTypeCode").val(ui.item.code);
			activityTypeChange(ui.item.idx);
		},
		close : function(event, ui) {

		}
	});
}

function activityTypeChange(idx) {
//	if (url == null || url.trim().length == 0) {
//		if(location.pathname.split( '/' ).length==5){
//			url= location.href+ ':actTypeChange';
//		}
//		else{
//			url = location.protocol + location.pathname.substring( 0, location.pathname.lastIndexOf( '/' )) + ':actTypeChange';
//		}
//	}
	
	jQuery.ajax({
		url : url,
		data : {
			param_id : idx
		}
	});
}

Tapestry.Initializer.setValueForActivityType = function(source) {
	
	$("#activityTypeName").val(source.name);
	$("#activityTypeCode").val(source.code);
	$("#actCode").val(source.code);
};

$(window).load(function(){
	$(".simple").val("");	
});