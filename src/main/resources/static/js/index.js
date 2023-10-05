let form_reg=document.getElementById("form_reg");
let btn=document.getElementById("btn")
console.log(form_reg)
btn.addEventListener('click',function (){
    let password=document.getElementById("password").value;
    let name=document.getElementById("name").value;
    $.ajax({
        url: "/user/register",
        // contentType: "application/json",
        type: "POST",
        data: $("#form_reg").serialize(),
        dataType: "JSON",
        success: function (json){
            if(json.state==200){
                alert("注册成功")
            }
            else {
                alert("注册失败:"+json.message)
            }
        },
        error: function (xhr){
            alert("注册时产生错误:"+xhr.message+xhr.status)
        }
    })
})

