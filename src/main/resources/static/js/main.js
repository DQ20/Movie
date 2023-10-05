let user;
const userIcon=document.querySelector(".userIcon")
const twoList=document.querySelector(".twoList")
const userIconImg=userIcon.children
let userIconImgFlag=true
let info=document.querySelector(".info")
userIcon.addEventListener('click',function(){
    twoList.classList.toggle('active')
    if(userIconImgFlag){
        userIconImg[0].src='../images/icon/user-active.png'
        userIconImgFlag=false
    }
    else{
        userIconImg[0].src='../images/icon/user.png'
        userIconImgFlag=true
    }

})
$('.infoLi').on('click', function() {
    $.ajax({
        url: '/user/showUser', // 替换为实际的目标URL
        method: 'GET', // 根据您的需求选择请求方法
        success: function(response) {
            // 处理成功响应的数据
            if (response.state === 'OK') {
                // 数据获取成功，处理并显示用户列表
                console.log(response.state)
                user = response.data;
                console.log(user)
            }
            else {
                console.error('请求失败：' + response.message);
            }
        },
        error: function(error) {
            // 处理错误情况
            console.log(error);
        }
    });
    window.location.href='../web/user.html'
});
export {user}

