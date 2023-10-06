document.addEventListener("DOMContentLoaded", function () {
    const userIcon = document.querySelector(".userIcon");
    const twoList = document.querySelector(".twoList");
    const userIconImg = userIcon.children;
    let userIconImgFlag = true;
    let info = document.querySelector(".infoU"); // 注意这里的类名更改为 .infoU
    let userData;
    const nameElement = document.querySelector('.name p');
    const ageElement = document.querySelector('.age p');
    const genderElement = document.querySelector('.gender p');
    const registerTimeElement = document.querySelector('.registerTime p');
    const emailElement = document.querySelector('.email p');
    const phoneElement = document.querySelector('.phone p');

    userIcon.addEventListener('click', function () {
        twoList.classList.toggle('active');
        if (userIconImgFlag) {
            userIconImg[0].src = '../images/icon/user-active.png';
            userIconImgFlag = false;
        } else {
            userIconImg[0].src = '../images/icon/user.png';
            userIconImgFlag = true;
        }
    });

    $('.infoLi').on('click', function () {
        window.location.href = '../web/user.html';
    });
});