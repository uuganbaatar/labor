var nameList;
var codeList;
var option;
Tapestry.Initializer.mergejilCustomAutoComplete = function(source) {
	option = source;
	codeList = new Array();
	nameList = new Array();

	var codeOb;
	var nameOb;
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
	createAutoComplete();
};

function createAutoComplete() {
	$("#occNegjCode").autocomplete({
		source : codeList,
		select : function(event, ui) {
			$("#occNegjName").val(ui.item.name);
			$("#occCode").val(ui.item.label);
			
			var url = location.href + ':negjTypeChange';
		
			jQuery.ajax({
				url : url,
				data : {
					param_id : ui.item.idx
				}
			});
			
		},
		close : function(event, ui) {

		}
	});

	$("#occNegjName").autocomplete({
		source : nameList,
		select : function(event, ui) {
			$("#occNegjCode").val(ui.item.code);
			$("#occCode").val(ui.item.code);
			
			
			var url = location.href + ':negjTypeChange';
		
			jQuery.ajax({
				url : url,
				data : {
					param_id : ui.item.idx
				}
			});
			
			
		},
		close : function(event, ui) {

		}
	});
}



Tapestry.Initializer.setValueForOccNegj = function(source) {
	
	$("#occNegjName").val(source.name);
	$("#occNegjCode").val(source.code);
	$("#occCode").val(source.code);
};

$(window).load(function(){
	$(".simple").val("");	
});