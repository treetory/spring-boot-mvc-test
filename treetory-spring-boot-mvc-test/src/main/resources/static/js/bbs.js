
function getMultiData(param) {
	$.ajax({
		url : "/test/select/multi",
		async : false,
		method : "get",
		data : /*JSON.stringify({})*/"",
		dataType : "json",
		contentType : "application/json;charset=UTF-8",
		success : function(data) {
			
			var html = "";
			
			if (data instanceof Array) {
				//console.log(data);
				html += "<ul>";
				for (var i=0; i<data.length; i++) {
					html += "<li>";
					html += (
							"<p>"+data[i].col1+"</p>" +
							"<p>"+data[i].col2+"</p>" +
							"<p>"+data[i].col3+"</p>" +
							"<p>"+data[i].col4+"</p>"
							);
					html += "</li>";
				}
				html += "</ul>";
			}
			
			$('#t01').html(html);
		},
		timeout : 100000,
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
}

$(document).ready(function() {
	
	$('#t01').empty();
	
	var param = {};
	
	getMultiData(param);
	
});