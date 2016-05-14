Tapestry.Initializer.occCustomAutoComplete = function(source) {
	var option = source;
	var codeList = new Array();
	var nameList = new Array();

	var codeOb;
	var nameOb;
	var url = option.url;
	var codeId = option.codeId;
	var nameId = option.nameId;

	for ( var i = 0; i < option.data.length; i++) {
		codeOb = {
			"idx" : option.data[i].id,
			"label" : option.data[i].code,
			"name" : option.data[i].name,
			"desc" : option.data[i].desc
		}
		codeList.push(codeOb);

		nameOb = {
			"idx" : option.data[i].id,
			"label" : option.data[i].name,
			"code" : option.data[i].code,
			"desc" : option.data[i].desc
		}
		nameList.push(nameOb);
	}

	if (codeId == null || codeId.trim().length == 0) {
		codeId = "occCode";
	}

	if (nameId == null || nameId.trim().length == 0) {
		nameId = "occName";
	}

	createAutoCompleteOcc(nameList, codeList, url, codeId, nameId);
};

function createAutoCompleteOcc(nameList, codeList, url, codeId, nameId) {
	$("input[id^=" + codeId.trim() + "]").first().autocomplete({
		source:codeList,
		select : function(event, ui) {
			$("input[id^=" + nameId.trim() + "]").first().val(ui.item.name);
			typeChange(ui.item.idx, url);
		},
		delay: 0 ,
		close : function(event, ui) {

		}
	});

	$("input[id^=" + nameId.trim() + "]").first().autocomplete({
		source: nameList,
		delay: 0,
		select : function(event, ui) {
			$("input[id^=" + codeId.trim() + "]").first().val(ui.item.code);
			typeChange(ui.item.idx, url);
		},
		delay: 0 ,
		close : function(event, ui) {

		}
	});
}

function typeChange(idx, url) {
	jQuery.ajax({
		url : url,
		data : {
			param_id : idx
		}
	});
}

Tapestry.Initializer.setValueForOcc = function(source) {
	$("input[id^=occName]").first().val(source.name);
	$("input[id^=occCode]").first().val(source.code);
	$("input[id^=occDesc]").first().val(source.desc);
};

$(window).load(function() {
	$(".simple").val("");
});