
function userModify() {
	
	let data = {
		id: $('#id').val(),
		nickname: $('#nickname').val(),
		password: $('#password').val(),
		email: $('#email').val(),
		grade: $('#grade').val(),
		gender: $('#gender').val(),
		bio: $('#bio').val()
	}

	let form = $('#userModifyForm')[0];
	
	let formData = new FormData(form);
	formData.append('file', $('#file'));
	formData.append('dto', new Blob([JSON.stringify(data)], {type: 'application/json'}));
	
	$.ajax({
		type: 'PUT',
		url: '/api/user/modify',
		data: formData,
		contentType: false, // 필수 : x-www-form-urlencoded로 파싱되는 것을 방지
		processData: false, // 필수 : contentType을 false로 줬을 때 QueryString 자동 설정됨. 해제
		enctype: 'multipart/form-data',
		dataType: 'json',
		
		success: function(res) {
			console.log("성공", res);
			alert(res.msg);
			location.href='/user/my_profile';
		},
		error: function(error) {
			console.log('실패', error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
	
}     


