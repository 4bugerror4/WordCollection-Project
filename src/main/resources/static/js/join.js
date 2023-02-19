
function userJoin(event) {
	
	event.preventDefault();
	
	let data = {
		username: $('#username').val(),
		password: $('#password').val(),
		email: $('#email').val(),
		nickname: $('#nickname').val(),
		grade: $('#grade').val()
	};
	
	$.ajax({
		type: 'POST',
		url: '/api/user/join',
		contentType: 'application/json; charset=utf-8',
		data:JSON.stringify(data),
		dataType: 'JSON',
		
		success: function(res) {
			alert(res.msg);
			console.log('성공', res);
			location.href=`/auth/login`;
		},
		
		error: function(error) {
			alert(JSON.stringify(error.responseJSON.data, '\t'));
			console.log('실패', error);
		}
	});
	
}