document.addEventListener('DOMContentLoaded', function() {
    document.getElementById("confirm_password").onchange = validatePassword;
});

function validatePassword() {
    var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("confirm_password").value;
    if (pass1 != pass2)
        document.getElementById("confirm_password").setCustomValidity("Mật khẩu không khớp, vui lòng nhập lại");
    else
        document.getElementById("confirm_password").setCustomValidity('');
}
