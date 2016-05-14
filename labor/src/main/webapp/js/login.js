$(document).ready(function () {
    $('#loginForm').validate({
        rules: {
            j_username: {required : true},
            j_password: {required : true}
        },
        messages: {
            j_username: "Хэрэглэгчийн нэрээ оруулна уу",
            j_password: "Нууц үгээ оруулна уу"
        },
		submitHandler: function(form) {
	        form.submit();
		}
    });
});