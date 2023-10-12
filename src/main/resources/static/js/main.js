document.addEventListener("DOMContentLoaded", function () {
    const userIcon = document.querySelector(".userIcon");
    const twoList = document.querySelector(".twoList");
    const userIconImg = userIcon.children;
    const wallet=document.querySelector(".walletLi")
    let userIconImgFlag = true;
    const exitBtn=document.getElementById("exitBtn")
    const buyButtons=document.querySelectorAll(".buy-button")
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
    buyButtons.forEach(function(button){
        button.addEventListener("click",function(){
            let movieElement=button.closest('.movie')
            let movieId = movieElement.getAttribute('data-movie-id');
            console.log("电影id为："+movieId)
            $.ajax({
                url: "/user/buyTicket",
                method: "GET",
                data: {
                    id: movieId
                },
                success: function (reponse){
                    console(reponse.state)
                },
                error: function (xhr) {
                    console("失败"+response.status)
                }
            })
        })
    })
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
                        let dateStr = movie.startDate;
                        let date = new Date(dateStr);
                        let year = date.getFullYear(); // 提取年份
                        let month = date.getMonth() + 1; // 月份是从0开始的，所以要加1
                        let day = date.getDate(); // 提取日期
                        let formattedDate = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "") + day;
                        moviesHtml += '<div class="movie" data-movie-index="' + movie.id + '">' +
                            '<h3>' + movie.name + '</h3>' +
                            '<p>￥' + movie.price + '</p>' +
                            '<p>时长：' + movie.duration + '分钟</p>' +
                            '<p>开始时间：' + movie.startTime + '</p>' +
                            '<p>影厅号：' + movie.room + '</p>' +
                            '<p>开始日期：' + formattedDate + '</p>' +
                            '<button class="buy-button">购买</button>'+
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