$(document).ready(function () {
    var isLogin = sessionStorage.getItem("isLogin");
    if(isLogin!="true"){
        localStorage.setItem("jumpUrl",location.href);
        alert("请先登录");
        window.location.href="NewHomePager.html";
    }
});



function generateHouseVerification(){
    var fczhId = $('#fczhId').val();
    if(fczhId==null||fczhId==""){
        $('.verification-prompt-info span').html("房产证号码不能为空");
        $('.verification-prompt-info').css('display', 'block');
    }else {
        var data = {
            "cdno": fczhId,
            "token": sessionStorage.getItem("token")
        };
        $.ajax({
            type: "post",
            url: getUrl() + "api/house/list",
            async: true,
            timeout: 10000,
            data: data,
            dataType: "json",
            error: function () {

            },
            success: function (result) {
                var data=result.data;
                alert(result);
            }
        });
    }
}