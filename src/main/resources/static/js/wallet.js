document.addEventListener('DOMContentLoaded', function () {
    const toggleHas = document.querySelector(".toggleHas");
    const showHas = document.querySelector(".showHas");
    const history=document.querySelector("#sidebar")
    let walletData;
    let hasTickets;
    let preTickets;
    let balance = document.querySelector(".balance");
    const rechargeBtn=document.getElementById("recharge-button")
    showWallet()
    rechargeBtn.addEventListener("click", function () {
        const moneyInput = prompt("请输入整数充值金额");

            // 使用正则表达式检查输入是否为整数
            if (/^\d+$/.test(moneyInput)&&moneyInput>0) {
                $.ajax({
                    url: "/wallet/topUp",
                    method: "GET",
                    data:{
                        balance: moneyInput
                    },
                    success: function(response){
                        alert("充值成功:"+response.state)
                        showWallet()
                    },
                    error: function(xhr){
                        alert("充值失败:"+xhr.status)
                    }
                }) 
            } else {
                alert("请输入有效的非负整数金额");
            }

    });
    function showWallet(param) { 
        $.ajax({
        url: "/wallet/showWallet",
        method: "GET",
        success: function (response) {
            walletData = response.data;
            hasTickets=response.tickets[0];
            preTickets=response.tickets[1];
            let hasTicketHTML=''
            Object.keys(hasTickets).forEach(key=>{
                let element=hasTickets[key]
                let date = new Date(element.startDate);
                let year = date.getFullYear(); // 提取年份
                let month = date.getMonth() + 1; // 月份是从0开始的，所以要加1
                let day = date.getDate(); // 提取日期
                let formattedDate = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "") + day;
                let elementHTML=`<div class="showHas-movie">
                        <h3> ${element.name}</h3>
                        <p>价格￥: ${element.price} </p>
                        <p>开始日期: `+formattedDate+`</p>
                        <p>时长: ${element.duration}分钟</p>
                        <p>影厅号: ${element.room}</p>
                    </div>
            `
            hasTicketHTML+=elementHTML
            })
            showHas.innerHTML=hasTicketHTML
            let preTicketHTML=''
            Object.keys(preTickets).forEach(key=>{
                let element=preTickets[key]
                let elementHTML=`<div class="sidebar-movie">
                        <h3> ${element.name}</h3>
                        <p>价格￥: ${element.price} </p>
                        <p>开始日期: ${element.startDate}</p>
                    </div>
            `
            preTicketHTML+=elementHTML
            })
            let tempHTML=history.innerHTML
            history.innerHTML+=preTicketHTML
            // 在成功回调中更新余额
            balance.textContent = "余额：￥" + walletData.balance;
        },
        error: function (param) {
            console.log("发生未知错误：" + param.status);
        }
    });
    }
});
