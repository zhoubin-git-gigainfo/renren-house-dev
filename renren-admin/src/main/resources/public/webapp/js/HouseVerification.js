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
                $('.verification-prompt-info span').html("获取核验码失败");
                $('.verification-prompt-info').css('display', 'block');
            },
            success: function (result) {
                if(result.code==0){
                    if(result.data.resultCode==1){
                        var html='<tr><th>房源核验码</th><th>房屋坐落</th><th>房号</th><th>面积</th><th>房屋性质</th><th>限制状态</th><th>提醒状态</th></tr>';
                        var houses=result.data.data.houses;
                        var bodys=result.data.data.bodys;
                        for(var i=0;i<houses.length;i++){
                            html+='<tr><td>'+houses[i].code+
                                '</td><td>'+houses[i].lname+
                                '</td><td>'+houses[i].hdesc+
                                '</td><td>'+houses[i].barea+
                                '</td><td>'+houses[i].huse+
                                '</td><td>'+houses[i].limit_state+
                                '</td><td>'+houses[i].warn_state+'</td></tr>';
                        }
                        $('.table').html(html);
                    }else {
                        $('.verification-prompt-info span').html("产权证号不对");
                        $('.verification-prompt-info').css('display', 'block');
                    }
                }else{
                    $('.verification-prompt-info span').html("获取核验码失败");
                    $('.verification-prompt-info').css('display', 'block');
                }

            }
        });
    }
}