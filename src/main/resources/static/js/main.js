document.addEventListener("DOMContentLoaded", function () {
    const userIcon = document.querySelector(".userIcon");
    const twoList = document.querySelector(".twoList");
    const userIconImg = userIcon.children;
    const wallet=document.querySelector(".walletLi")
    let userIconImgFlag = true;
    const exitBtn=document.getElementById("exitBtn")
    movieAjaxRequest()
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
    wallet.addEventListener("click",function(){
        window.location.href="../web/wallet.html"
    })
    exitBtn.addEventListener("click",function (){
        // 清除会话数据
        console.log("开始清除")
        $.ajax({
            url: "/user/logOut",
            method: "GET",
            success: function (response){
                let url=response.data.toString()
                console.log("清除完成")
                window.location.href=url
            },
            error: function (xhr){
                console.log(xhr.message)
            }
        })
        window.location.href="/index.html"
    })
    function movieAjaxRequest(){
        $.ajax({
            url: "/ticket/showTicket",
            method: "GET",
            success: function (response) {
                    // 从response中获取数据，假设response是一个包含电影信息的数组
                    let movies = response.data;
                    // 创建一个用于存放电影信息的HTML字符串
                    let moviesHtml = '';
                    // 遍历电影数组，生成HTML代码
                    for (let i = 0; i < movies.length; i++) {
                        let movie = movies[i];
                        moviesHtml += '<div class="movie">' +
                            '<h3>' + movie.name + '</h3>' +
                            '<p>￥' + movie.price + '</p>' +
                            '<p>时长：' + movie.duration + '分钟</p>' +
                            '<p>开始时间：' + movie.startTime + '</p>' +
                            '<p>影厅号：' + movie.room + '</p>' +
                            '<p>开始日期：' + movie.startDate + '</p>' +
                            '</div>';
                    }
                    // 将生成的HTML插入到movieShow元素中
                    $('.movieShow').html(moviesHtml);
                },
            error: function (xhr){
                console.log("查找失败"+xhr.status)
            }
        })
    }
});