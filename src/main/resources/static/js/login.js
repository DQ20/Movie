    // 验证表单并发送 AJAX 请求
    $(document).ready(function() {
    $("#registration-form").submit(function(e) {
        e.preventDefault();

        const username = $("#username").val();
        const password = $("#password").val();

        // 前端验证示例：检查用户名和密码是否为空
        if (username.trim() === "") {
            $("#username-error").text("账号不能为空");
            return;
        } else {
            $("#username-error").text("");
        }

        if (password.trim() === "") {
            $("#password-error").text("密码不能为空");
            return;
        } else {
            $("#password-error").text("");
        }

        // 构建要发送到后端的数据对象
        const formData = {
            username: username,
            password: password
        };

        // 使用 jQuery 发送 POST 请求（这里只是示例，实际需要根据后端API进行配置）
        $.ajax({
            url: "/user/login",
            type: "POST",
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function(response) {
                // 注册成功，可以在此处进行页面跳转等操作
                alert("登录成功！");
                window.location.href="/web/main.html"
            },
            error: function(error) {
                alert("登陆失败，请重试。"+error.message);
                console.error("请求出错:", error);
            }
        });
    });
});
