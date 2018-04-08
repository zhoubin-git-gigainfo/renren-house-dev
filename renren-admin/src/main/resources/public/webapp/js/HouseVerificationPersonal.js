$(document).ready(function () {

});



function generateHouseVerification(){
    var fczhId = $('#fczhId').val();
    var idCard = $('#Idcard').val();
    if(fczhId==null||fczhId==""){
        $('.verification-prompt-info span').html("房产证号码不能为空");
        $('.verification-prompt-info').css('display', 'block');
    }else if (idCard == null || idCard == "" ) {
        $('.verification-prompt-info span').html("身份证号码不能为空");
        $('.verification-prompt-info').css('display', 'block');
    }else if (!(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idCard))){
        $('.verification-prompt-info span').html("请输入正确的身份证号码");
        $('.verification-prompt-info').css('display', 'block');
    }else {
        var data = {
            "fczhId": fczhId,
            "idCard": idCard
        };
        $.ajax({
            type: "post",
            contentType: "application/json;charset=UTF-8",
            url: getUrl() + "app/house/",
            async: true,
            timeout: 10000,
            data: JSON.stringify(data),
            dataType: "json",
            error: function () {
                $('.verification-prompt-info span').html("房源核验码生成失败");
                $('.verification-prompt-info').css('display', 'block');
            },
            success: function (result) {
                if (result.code == 500) {
                    $('.verification-prompt-info span').html("该手机号码已注册");
                    $('.verification-prompt-info').css('display', 'block');
                } else if (result.code == 0) {
                    $('.verification-prompt-info span').html("");
                    $('.verification-prompt-info').css('display', 'none');
                    alert("注册成功");
                }
            }
        });
    }
}