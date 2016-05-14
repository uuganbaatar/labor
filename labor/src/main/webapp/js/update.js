/*$(function() {
	var isEdit = false, chosenRow;
	var fieldIds = '', staticIds = '', dynamicIds = '';
	
	$('.edit a').click(function(e) {
		
		isEdit = true;
		e.preventDefault();
		$(this).closest('tr').find('td').each(function(index) {
			if(index > 1) {
				$('#serviceForm input[type=text]').eq($(this).index() - 2).val($(this).find('span').text());
				if(index < $('#separator').index()) {
					fieldIds += $(this).find(':hidden:eq(1)').val() + ',';
					staticIds += $(this).find(':hidden:first').val() + ',';
				} else if(index > $('#separator').index()) {{
					dynamicIds += $(this).find(':hidden').val() + ',';
				}
				
			}
		});
		
		chosenRow = $(this).closest('tr');
	});
	
	$('#submit_3').click(function(e) {
		if(isEdit) {
			e.preventDefault();
			var url = location.href;
			url = url.substring(0, url.lastIndexOf('/'));
			
			var vals = '';
			
			$('#serviceForm input[type=text]').each(function() {
				if($(this).val() != '')
					vals += ($(this).val() + ',');
				else
					vals += ('0,');
			});
			
			
			//console.log(vals);
			$.ajax({
				url: url + ':edit',
				data: {
					dataId: dataIds,
					fieldId: fieldIds,
					dataDynamicId: dynamicIds,
					values: vals
				}
			}).done(function() {
				location.reload();
			});
			isEdit = false;
		}
	});
});*/