document.addEventListener('DOMContentLoaded', function () {
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');
    const passInput = document.getElementById('pass');
    const rePassInput = document.getElementById('re_pass');
    const countryInput = document.getElementById('country');
    const contactInput = document.getElementById('contact');
    const agreeTermCheckbox = document.getElementById('agree-term');
    const registerButton = document.getElementById('signup');

    // Hàm kiểm tra xem tất cả các trường đã được nhập đủ thông tin chưa
    function checkInputs() {
        const nameValue = nameInput.value.trim();
        const emailValue = emailInput.value.trim();
        const passValue = passInput.value.trim();
        const rePassValue = rePassInput.value.trim();
        const countryValue = countryInput.value.trim();
        const contactValue = contactInput.value.trim();
        const agreeTermChecked = agreeTermCheckbox.checked;

        // Kiểm tra nếu tất cả các trường đều đã được nhập và người dùng đã đồng ý với các điều khoản
        if (nameValue !== '' && emailValue !== '' && passValue !== '' && rePassValue !== '' && countryValue !== '' && contactValue !== '' && agreeTermChecked) {
            registerButton.disabled = false; // Kích hoạt nút Register
        } else {
            registerButton.disabled = true; // Vô hiệu hóa nút Register
        }
    }

    // Gọi hàm kiểm tra khi có sự kiện nhập vào các trường
    nameInput.addEventListener('input', checkInputs);
    emailInput.addEventListener('input', checkInputs);
    passInput.addEventListener('input', checkInputs);
    rePassInput.addEventListener('input', checkInputs);
    countryInput.addEventListener('input', checkInputs);
    contactInput.addEventListener('input', checkInputs);
    agreeTermCheckbox.addEventListener('change', checkInputs);
});
