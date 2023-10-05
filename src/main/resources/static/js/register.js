const registrationForm = document.getElementById('registrationForm');
const usernameInput = document.getElementById('username');
const ageInput = document.getElementById('age');
const genderInput = document.getElementById('gender');
const emailInput = document.getElementById('email');
const phoneInput = document.getElementById('phone');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');
const emailError = document.getElementById('emailError');
const phoneError = document.getElementById('phoneError');
const passwordError = document.getElementById('passwordError');
const ageError = document.getElementById('ageError');
const genderError = document.getElementById('genderError');

registrationForm.addEventListener('submit', function (e) {
    e.preventDefault(); // 阻止默认表单提交行为

    let valid = true;

    // 验证用户名（你可以根据需要添加更多规则）
    if (usernameInput.value.trim() === '') {
        valid = false;
        showError(usernameInput, '用户名不能为空');
    } else {
        hideError(usernameInput);
    }

    // 验证年龄
    const age = parseInt(ageInput.value);
    if (isNaN(age) || age <= 0 || age >= 128) {
        valid = false;
        showError(ageInput, '年龄必须大于0且小于128');
    } else {
        hideError(ageInput);
    }

    // 验证性别
    const gender = genderInput.value;
    if (gender !== '男' && gender !== '女') {
        valid = false;
        showError(genderInput, '请选择有效的性别');
    } else {
        hideError(genderInput);
    }

    // 验证邮箱格式
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailPattern.test(emailInput.value)) {
        valid = false;
        showError(emailInput, '请输入有效的邮箱地址');
    } else {
        hideError(emailInput);
    }

    // 验证手机号格式
    const phonePattern = /^1\d{10}$/;
    if (!phonePattern.test(phoneInput.value)) {
        valid = false;
        showError(phoneInput, '请输入有效的手机号');
    } else {
        hideError(phoneInput);
    }

    // 验证密码格式
    if (passwordInput.value.length < 6 || passwordInput.value.length > 16) {
        valid = false;
        showError(passwordInput, '密码长度必须在6到16位之间');
    } else {
        hideError(passwordInput);
    }

    // 验证密码一致性
    if (passwordInput.value !== confirmPasswordInput.value) {
        valid = false;
        showError(confirmPasswordInput, '两次输入的密码不一致');
    } else {
        hideError(confirmPasswordInput);
    }

    if (valid) {
        // 构建包含表单数据的对象
        const formData = {
            name: usernameInput.value,
            age: ageInput.value,
            gender: genderInput.value,
            email: emailInput.value,
            phone: phoneInput.value,
            password: passwordInput.value,
        };

        // 发送AJAX POST请求
        // 请确保你的服务器已正确设置并能处理这个请求
        // 这里的$.ajax代码需要jQuery库的支持
        $.ajax({
            url: "/user/register",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                console.log(response.state);
                // 处理成功的响应
            },
            error: function (error) {
                console.error(error.status);
                // 处理错误的响应
            }
        });
    }
});

// 显示错误消息
function showError(inputElement, errorMessage) {
    const errorElement = inputElement.nextElementSibling;
    errorElement.textContent = errorMessage;
    errorElement.style.color = 'red'; // 设置红色字体颜色
    errorElement.style.fontSize = '14px'; // 调整字体大小
    errorElement.style.visibility = 'visible';
}

// 隐藏错误消息
function hideError(inputElement) {
    const errorElement = inputElement.nextElementSibling;
    errorElement.style.visibility = 'hidden';
}
