function getAllElementsWithAttribute(attribute) {

	var matchingElements = [];
	var allElements = document.getElementsByTagName('*');
	var valMes;

	$(".submitBtn99").click(
			function() {
				valMes = $(".tjq-error-popup").text();

				for ( var i = 0; i < allElements.length; i++) {
					if (allElements[i].getAttribute(attribute)) {

						var el = allElements[i];
						var value = el.value;

						if (el.name == "regNum") {
							allElements[i].setAttribute("pattern",
									"^[а-яА-Я]{2}[0-9]{8}$");
						}
						
						if (el.name == "amount") {
							allElements[i].setAttribute("pattern",
									"^[0-9]{4,10}$");
						}

						allElements[i].setAttribute("oninvalid",
								"setCustomValidity('" + valMes + "')");

						allElements[i].setAttribute("onchange",
								"setCustomValidity('')");

					}
					matchingElements.push(allElements[i]);
					$(".tjq-error-popup").text("");
				}
			});
	return matchingElements;
}

$(document).ready(function() {
	getAllElementsWithAttribute('required');

});