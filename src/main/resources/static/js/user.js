// 在user.js中，将用户数据渲染到页面中
import { user } from './main.js'; // 假设您的用户数据是在main.js中导出的

document.addEventListener('DOMContentLoaded', function() {
    if (user) {
        const nameElement = document.querySelector('.name p');
        const ageElement = document.querySelector('.age p');
        const genderElement = document.querySelector('.gender p');
        const registerTimeElement = document.querySelector('.registerTime p');
        const emailElement = document.querySelector('.email p');
        const phoneElement = document.querySelector('.phone p');

        // 将用户数据填充到页面元素中
        nameElement.textContent += user.name;
        ageElement.textContent += user.age;
        genderElement.textContent += user.gender;
        registerTimeElement.textContent += user.registerTime;
        emailElement.textContent += user.email;
        phoneElement.textContent += user.phone;
    }
});
