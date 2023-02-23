function boardAdd() {
	
	let data = {
		title: $('#title').val(),
		content: $('#content').val()
	};
	
	$.ajax({
		type: 'POST',
		url: '/api/board/save',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert(res.msg);
			location.href='/board/list';
		},
		
		error: function(error) {
			console.log('실패', error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
};

function boardModify() {
	
	let data = {
		id: $('#id').val(),
		title: $('#title').val(),
		content: $('#content').val()
	};
	
	$.ajax({
		type: 'PUT',
		url: '/api/board/modify',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert(res.msg);
			location.href='/board/list/' + data.id;
		},
		
		error: function(error) {
			console.log('실패', error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
	
	
};

function boardDelete() {
	
	let data = {
		id: $('#id').val()
	};
	
	$.ajax({
		type: 'DELETE',
		url: '/api/board/delete',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert(res.msg);
			location.href='/board/list';
		},
		
		error: function(error) {
			console.log('실패', error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
	
};