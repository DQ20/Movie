// 在user.js中，将用户数据渲染到页面中
document.addEventListener('DOMContentLoaded', function() {
        const nameElement = document.querySelector('.name p');
        const ageElement = document.querySelector('.age p');
        const genderElement = document.querySelector('.gender p');
        const registerTimeElement = document.querySelector('.registerTime p');
        const emailElement = document.querySelector('.email p');
        const phoneElement = document.querySelector('.phone p');
        const userDataJSON = localStorage.getItem('userData');
        let userData;
        //获取用户信息并渲染
        showUserAjax()
        //点击修改密码出现弹窗
        document.querySelector(".modifyPassword").addEventListener("click", function() {
                document.getElementById("passwordModal").style.display = "block";
        });

        // 关闭/密码弹窗
        $(".closeModel").click(closeM);

        // 处理密码修改逻辑
        $(".changePassword").click(function (){
                let oldPasswordInput = document.getElementById("oldPasswordInput");
                let newPasswordInput = document.getElementById("newPasswordInput");
                let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput");
                let oldPassword = oldPasswordInput.value;
                let newPassword = newPasswordInput.value;
                let confirmNewPassword = confirmNewPasswordInput.value;
                const modifyPasswordData={
                        "oldPassword": oldPassword,
                        "newPassword": newPassword,
                        "confirmNewPassword": confirmNewPassword
                }
                $.ajax({
                        url: "/user/modifyPassword" ,
                        type: "POST",
                        contentType: 'application/json',
                        data: JSON.stringify(modifyPasswordData),
                        success: function (response){
                                closeM()
                                alert("修改成功")

                        },
                        error: function (error){
                                closeM()
                                alert("修改失败："+error.message)
                        }
                })
        })
        function closeM(){
                document.getElementById("passwordModal").style.display = "none";
        }
        //点击弹出修改信息弹窗
        document.querySelector(".modifyInfo").addEventListener("click", function() {
                let infoModal = document.getElementById("infoModal");
                infoModal.style.display = "block";
                $.ajax({
                        url: "/user/showUser",
                        method: "GET",
                        success: function(response) {
                                console.log(response)
                                document.getElementById("nameInput").value = response.data.name;
                                document.getElementById("ageInput").value = response.data.age;
                                document.getElementById("genderInput").value = response.data.gender;
                                document.getElementById("phoneInput").value = response.data.phone;
                                document.getElementById("emailInput").value = response.data.email;
                                // closeInfoM()
                        },
                        error: function(error) {
                                closeInfoM();
                                alert("发生未知错误:" + error.status + "," + error.message);
                        }
                });
        });

// 关闭弹窗
        document.querySelector(".closeInfoModel").addEventListener("click", closeInfoM);

// 保存信息
        document.querySelector(".saveInfo").addEventListener("click", function() {
                let nameInput = document.getElementById("nameInput").value;
                let ageInput = document.getElementById("ageInput").value;
                let genderInput = document.getElementById("genderInput").value;
                let phoneInput = document.getElementById("phoneInput").value;
                let emailInput = document.getElementById("emailInput").value;
                const modifyInfoData={
                            "name": nameInput,
                            "age": ageInput,
                            "gender": genderInput,
                            "phone": phoneInput,
                            "email": emailInput
                    }
                // 在这里执行保存操作，可以将获取到的新信息发送到服务器等
                $.ajax({
                        url: "/user/modifyInfo" ,
                        type: "PUT",
                        contentType: 'application/json',
                        data: JSON.stringify(modifyInfoData),
                        success: function (response){
                                closeInfoM()
                                alert("修改成功")
                                showUserAjax()
                        },
                        error: function (error){
                                closeInfoM()
                                alert("修改失败："+error.message)
                        }
                })
                // 关闭弹窗
                closeInfoM();
        });
        function closeInfoM(){
                document.getElementById("infoModal").style.display="none";
        }
        function showUserAjax(){
                $.ajax({
                        url: '/user/showUser', // 替换为实际的目标URL
                        method: 'GET', // 根据您的需求选择请求方法
                        success: function (response) {
                                if (response.state === 200) {
                                        userData = response.data;
                                        nameElement.innerHTML = `昵称：${userData.name}`;
                                        ageElement.innerHTML = `年龄：${userData.age}`;
                                        genderElement.innerHTML = `性别：${userData.gender}`;
                                        registerTimeElement.innerHTML = `注册时间：${userData.registerDate}`;
                                        emailElement.innerHTML = `邮箱：${userData.email}`;
                                        phoneElement.innerHTML = `手机号：${userData.phone}`;
                                } else {
                                        console.error('请求失败：' + response.message);
                                }
                        },
                        error: function (error) {
                                // 处理错误情况
                                console.log(error);
                        }
                });
        }
});
