
jQuery(document).ready(function() {
		
	$(document).on("click", "#selectAll", function(){ 
		$('input[type=checkbox]').prop('checked', true);
		var url = location.href + ':selectAll';
		jQuery.ajax({
			url : url,
		});
		
	}); 
	
	
	$(document).on("click", "#unSelectAll", function(){
		$('input[type=checkbox]').prop('checked',false);
		var url = location.href + ':unSelectAll';
		jQuery.ajax({
			url : url,
		});
		
	}); 
	
	$(document).on("click", ".check", function(){ 
		if($(this).attr('id')!=null){
			
			if($(this).is(':checked')){	
				var url = location.href + ':singleSelect';
				jQuery.ajax({
					url : url,
					data : {
						userId: $(this).attr('id')
					}
				});
			}else{
				var url = location.href + ':singleUnSelect';
				jQuery.ajax({
					url : url,
					data : {
						userIdUnselect: $(this).attr('id')
					}
				});
			}
		}		
	}); 
			
});

function passwordChangedDialog(){
	var url = location.href + ':zoneUpdate';
	jQuery.ajax({
		url : url
	});
	 $( "#dialog-modal" ).dialog({
	      height: 400,
	      modal: true,
	      minWidth: 500,
	      minHeight: 400,
	      overlay: true,
	      position: ["center", "center"],
	      close:function(){
	    	    var url = location.href + ':dialogHide';
				jQuery.ajax({
					url : url
				});
	      }
	   });
}





	