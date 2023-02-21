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
			alert('단어가 추가되었습니다.')
			$('#wordAddModal').modal('hide');
			location.reload();
		},
		
		error: function(error) {
			console.log("실패", error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}, 
		
	});
}

function wordModify() {
	let data = {
		id: $('#wordId').val(),
		eng: $('#modifyEng').val(),
		kor: $('#modifyKor').val(),
		type: $('#modifyType').val()
	};
	
	$.ajax({
		type: 'PUT',
		url: '/api/word/modify',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		datatype: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert('단어가 수정되었습니다..')
			$('#wordAddModal').modal('hide');
			location.reload();
		},
		
		error: function(error) {
			console.log("실패", error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
	 
}

function wordDelete(wordId) {
	
	let data = {
		id: wordId
	}
	
	$.ajax({
		type: 'DELETE',
		url: '/api/word/delete',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		datatype: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert('단어가 삭제 되었습니다.')
			location.reload();
		},
		
		error: function(error) {
			console.log("실패", error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
	 
}

// 모달창 값 전달 받기
$(document).ready(function() {
	$('#wordModifyDeleteModal').on('show.bs.modal', function (event) {
		  let button = $(event.relatedTarget);
 	 	  let recipient1 = button.data('id');
		  let recipient2 = button.data('eng');
		  let recipient3 = button.data('kor');
		  let recipient4 = button.data('type');
		  let modal = $(this);
		  modal.find('.modal-body #wordId').val(recipient1);
		  modal.find('.modal-body #modifyEng').val(recipient2);
		  modal.find('.modal-body #modifyKor').val(recipient3);
		  modal.find('.modal-body #modifyType').val(recipient4);
	});
});