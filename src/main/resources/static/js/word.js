
function wordAdd() {
	
	let data = {
		eng: $('#eng').val(),
		kor: $('#kor').val(),
		type: $('#type').val()
	};
	
	$.ajax({
		type: 'POST',
		url: '/api/word/add',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		datatype: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
		},
		
		error: function(error) {
			console.log("실패", error);
		}
	});
}

function wordModify() {
	let data = {
		eng: $('#eng').val(),
		kor: $('#kor').val(),
		type: $('#type').val()
	};
	
	$.ajax({
		type: 'PUT',
		url: '/api/word/modify',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		datatype: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
		},
		
		error: function(error) {
			console.log("실패", error);
		}
	});
	 
}

function wordDelete() {
	let data = {
		id: $('#eng').val(),
	};
	
	$.ajax({
		type: 'DELETE',
		url: '/api/word/delete',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		datatype: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
		},
		
		error: function(error) {
			console.log("실패", error);
		}
	});
	 
}