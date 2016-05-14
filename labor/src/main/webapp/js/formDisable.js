function toggleAlert(divId) {
	toggleDisabled(document.getElementById(divId));
}

function toggleDisabled(el) {
	try {
		el.disabled = true;
	} catch (E) {
		
	}
	if (el.childNodes && el.childNodes.length > 0) {
		for ( var x = 0; x < el.childNodes.length; x++) {
				toggleDisabled(el.childNodes[x]);
		}
	}
}