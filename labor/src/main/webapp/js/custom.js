function getElementsByTagNames(list, obj) {
	if (!obj)
		var obj = document;
	var tagNames = list.split(',');
	var resultArray = new Array();
	for ( var i = 0; i < tagNames.length; i++) {
		var tags = obj.getElementsByTagName(tagNames[i]);
		for ( var j = 0; j < tags.length; j++) {
			resultArray.push(tags[j]);
		}
	}
	var testNode = resultArray[0];
	if (!testNode)
		return [];
	if (testNode.sourceIndex) {
		resultArray.sort(function(a, b) {
			return a.sourceIndex - b.sourceIndex;
		});
	} else if (testNode.compareDocumentPosition) {
		resultArray.sort(function(a, b) {
			return 3 - (a.compareDocumentPosition(b) & 6);
		});
	}
	return resultArray;
}

var SITE = SITE || {};

SITE.fileInputs = function() {
	var $this = $(this), $val = $this.val(), valArray = $val.split('\\'), newVal = valArray[valArray.length - 1], $button = $this
			.siblings('.button'), $fakeFile = $this.siblings('.file-holder');
	if (newVal !== '') {
		$button.text('Зураг оруулах');
		if ($fakeFile.length === 0) {
			$button
					.after('<span class="file-holder" style="padding-left:15px;">'
							+ newVal + '</span>');
		} else {
			$fakeFile.text(newVal);
		}
	}
};

$(document).ready(
		function() {
			$('.file-wrapper input[type=file]').bind('change focus click',
					SITE.fileInputs);
		});

$(function() {
	$("#dropdown1").hide();
	$("#mini_pro").click(function() {
		$("#dropdown2").hide();
		$("#dropdown1").show();

		var curr_width = $("#modules").width();
		$(".subProfile").css('margin-left', (curr_width + 30) * (-1));
	});
});
//
$(document).ready(function(){
	$(".advanced_search").hide();	
	$(".ad_search").click(function () {
	  $(".advanced_search").slideToggle("slow");
	});
});
//
$(document).ready(function(){
	$(".advanced_search1").hide();	
	$(".ad_search1").click(function () {
	  $(".advanced_search1").slideToggle("slow");
	});
});
\

$(function() {
	$("#dropdown2").hide();
	$("#modules").click(function() {
		$("#dropdown1").hide();
		$("#dropdown2").show();
	});
});

$(document).click(
		function(e) {
			var target = e.target;
			if (!$(target).is('#modules') && !$(target).is('#mini_pro')
					&& !$(target).is('.org') && !$(target).is('.occ')
					&& !$(target).is('.name') && !$(target).is('.more')
					&& !$(target).is('.not')) {
				$("#dropdown1").hide();
				$("#dropdown2").hide();
			}

			if ($(target).is('.tjq-error-popup')) {
				var field = $(target);
				field.hide();
			}
		});

$(function() {
	$(".hasDatepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		showOn : "button",
		buttonImage : "../images/calendar-icon.png",
		buttonImageOnly : true,
		dateFormat : 'mm/dd/yy'

	});
});

$(document)
		.ready(
				function() {
					$(".advanced_search").show();
					$(".show").click(function() {
						$(".advanced_search").slideToggle("slow");
						if ($(this).attr('class') == 'icon show')
							$(this).attr('class', 'icon hide');
						else
							$(this).attr('class', 'icon show');
					});

					$(".historyDetail").click(function(c) {
						$("#historyDetail").dialog({
							modal : true,
							width : 670,
						});
						c.preventDefault();
					});

					$(".dialog-link").click(function(e) {
						$("#deleteDialog").dialog({
							modal : true,
							width : 400,
						});
						e.preventDefault();
					});

					$(".not").click(function(e) {
						$("#notif").dialog({
							modal : true,
							width : 500,
							height : 500,
						});
						e.preventDefault();
					});

					$("#change-dialog-link").click(function(e) {
						$("#changeDialog").dialog({
							modal : true,
							width : 400,
							buttons : {
								"Тийм" : function() {
									$(this).dialog("close");
									$("#passwordDialog").dialog({
										modal : true,
										width : 400,
										buttons : {
											"Хаах" : function() {
												$(this).dialog("close");
											}
										},
									});
								},
								"Үгүй" : function() {
									$(this).dialog("close");
								},
							},
						});

						e.preventDefault();
					});

					$(".config-column").click(function() {
						$("#configColumn").dialog({
							modal : true,
							width : 400,
							height : 500,
						});
					});

					// $(".saveConfig").click(function(){
					// $("#dialog_").each(function() {
					// $(this).dialog("close");
					// });
					// });

					$(".deleteLink").click(function() {
						alert('Сонгосон мөрийг системээс устгах уу?');
					});

					$(function() {
						$("#tabs2").tabs({
							beforeLoad : function(event, ui) {
								ui.jqXHR.error(function() {
									ui.panel.html("Таб ачааллагдсангүй!");
								});
							}
						});
					});

					$(function() {
						$("#tabs").tabs({
							beforeLoad : function(event, ui) {
								ui.jqXHR.error(function() {
									ui.panel.html("Таб ачааллагдсангүй!");
								});
							}
						});
					});
					
					$("a > .confirm").click(function() {
						$("#dialog-message").dialog("open");
						return false;
					});

					$("a > .confirm").click(function() {
						$("#dialog-account").dialog("open");
						return false;
					});
					/* bitii ustgaarai ashiglaj bga begin */
					$(function() {
						$("#stab").tabs({
							selected : 0
						});
					});

					var accordionIndex;

					$(function() {

						if (accordionIndex == null)
							accordionIndex = 0;

						$("#accordion2").accordion({
							heightStyle : "content",
							collapsible : true,
							active : 0,
							autoHeight : false,
							activate : function(event, ui) {
								// alert('');
							}
						});

					});

					/* bitii ustgaarai ashiglaj bga end */
					$(function() {
						$("#accordion").accordion(
								{
									heightStyle : "content",
									collapsible : true,
									active : false,
									autoHeight : false,

									changestart : function(event, ui) {
										var clicked = $(this).find(
												'.ui-state-active').attr('id');
										$('#' + clicked).load(
												'/widgets/' + clicked);
									}
								});
					});

					// If you're interested in detecting a user typing a
					// character, use
					// the keypress event. IE bizarrely only stores the
					// character code
					// in keyCode while all other browsers store it in which.
					// Some (but
					// not all) browsers also store it in charCode and/or
					// keyCode.
					$("input[type=text], textarea")
							.live(
									"keypress",
									function(event) {
										if (!$(this).hasClass("username")
												&& !$(this).hasClass("code")) {

											event = event || window.event;
											var charCode = event.which
													|| event.keyCode;
											var charStr = String
													.fromCharCode(charCode);

											var regExp = new RegExp('[a-zA-Z]');
											var regExpMN = new RegExp(
													'[а-яА-Я|ү|Ү|ө|Ө|ё|Ё]');
											if (charStr == regExp.exec(charStr)) {

												event.preventDefault();

												alert("Та зөвхөн монголоор бичнэ үү!");
											} else if (charCode > 223
													&& charStr != regExpMN
															.exec(charStr)) {
												event.preventDefault();

												alert("Та стандарт гарын драйвер ашиглаж, зөвхөн монголоор бичнэ үү!");
											}
										}
									});

					// DISABLE Ctrl+C
					$(document).bind('copy', function(event) {
						event.preventDefault();
						alert("Хуудасны өгөгдлийг хуулж авахыг хориглоно!");
					});

					 // DISABLE RIGHT CLICK
					jQuery(document).bind("contextmenu", function(e) {
						e.preventDefault();
					});

					$("input[name^=jobSeekerRegNum]")
							.live(
									"keypress",
									function(event) {
										event = event || window.event;
										var charCode = event.which
												|| event.keyCode;
										var charStr = String
												.fromCharCode(charCode);

										var num = new RegExp('[0-9]');
										var upper = new RegExp('[А-Я|Ү|Ө|Ё]');
										var lower = new RegExp('[а-я|ү|ө|ё]');
										var regExp = new RegExp('[a-zA-Z]');
										var regExpMN = new RegExp(
												'[а-яА-Я|ү|Ү|ө|Ө|ё|Ё]');
										var enterKey = 13;

										if (charStr != regExp.exec(charStr)
												&& (charCode <= 223 || charStr == regExpMN
														.exec(charStr))
												&& charCode != enterKey) {
											var regNum = $(this).val();

											if (regNum.length < 2) {
												if (charStr == lower
														.exec(charStr)) {

													event.preventDefault();

													alert("Регистрийн дугаарын үсгүүдийг ТОМООР бичнэ үү!");
												} else if (charStr != upper
														.exec(charStr)) {
													event.preventDefault();

													alert("Регистрийн дугаарын эхний хоёр тэмдэгт ҮСЭГ байх ёстой!");
												}
											} else if (regNum.length > 9) {
												event.preventDefault();

												alert("Регистрийн дугаар 10 тэмдэгтээс бүрдэх ёстой!");
											} else if (charStr != num
													.exec(charStr)) {
												event.preventDefault();

												alert("Регистрийн дугаарын энэ хэсэг ТОО байх ёстой!");
											}
										}
									});

				});

$(document).ajaxError(function() {
	// alert("Session expired");
	console.log("jquery ajax error!");

});

function getElementsStartsWithId(id) {
	var children = document.body.getElementsByTagName('input');
	var element, child;

	for ( var i = 0, length = children.length; i < length; i++) {
		child = children[i];
		if (child.id.substr(0, id.length) == id)
			element = child;
	}

	return element;
}
//
var sorter = new TINY.table.sorter("sorter");
sorter.head = "head";
sorter.asc = "asc";
sorter.desc = "desc";
sorter.even = "evenrow";
sorter.odd = "oddrow";
sorter.evensel = "evenselected";
sorter.oddsel = "oddselected";
sorter.paginate = true;
sorter.currentid = "currentpage";
sorter.limitid = "pagelimit";
sorter.init("table",0);