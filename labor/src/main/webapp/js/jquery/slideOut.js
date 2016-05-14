$(document).ready(function() {
	$(document).on("click", "#hideShowBtn", function(){
		if($('#infoContainer').css("margin-left")=="-472px")
			$('#infoContainer').stop().animate({'marginLeft':'-1px'},200);	
		else 
			$('#infoContainer').stop().animate({'marginLeft':'-472px'},200);	
	});
});
