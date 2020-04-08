/**
 * 新增附加js
 */

// 错误提示
$(function() {
	var error = $("#error").val();
	if (error != "") {
		alert(error);
		$("#error").val("");
		$.ajax({
			url : "/delSession",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				session : "error"
			}
		});
	}

})

// CheckBox操作
function checkAll() {
	var all = $("#all");
	if (all.prop('checked')) {
		$(".checkboxes").attr("checked", true);
	} else {
		$(".checkboxes").attr("checked", false);
	}
}
function checkOne() {
	var all_check = true;
	$(".checkboxes").each(function() {
		while (this.checked == false) {
			all_check = false;
			break;
		}
	})
	if (all_check) {
		$("#all").attr("checked", true);
	} else {
		$("#all").attr("checked", false);
	}
}