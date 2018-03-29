$(document).ready(function () {
    var username = sessionStorage.getItem("name");
    var password = sessionStorage.getItem("password");
    var mobile = sessionStorage.getItem("mobile");
    if (username == null || username == "" || password == null || password == "" || mobile == null || mobile == "") {
        username = localStorage.getItem("name");
        password = localStorage.getItem("password");
        mobile = localStorage.getItem("mobile");
        if (username != null && username != "" && password != null && password != "" && mobile != null && mobile != "") {
            var startTime = localStorage.getItem("startTime");
            var endTime = getNowFormatDate();
            var apartDay=getDateDiff(startTime,endTime);
            if(apartDay<=7){
                var data = {
                    "mobile": mobile,
                    "password": password
                };
                $.ajax({
                    type: "post",
                    contentType: "application/json;charset=UTF-8",
                    url: getUrl() + "app/house/login",
                    async: true,
                    timeout: 10000,
                    data: JSON.stringify(data),
                    dataType: "json",
                    error: function () {

                    },
                    success: function (result) {
                        if (result.code == 0) {
                            $(".top dd").html('<span>'+username+'</span><span>|</span><a onclick="onOutLogin()">退出登录</a>');
                        }
                    }
                });
            }else {
                localStorage.removeItem("name");
                localStorage.removeItem("password");
                localStorage.removeItem("mobile");
                localStorage.removeItem("startTime");
            }
        }
    }else {
        var data = {
            "mobile": mobile,
            "password": password
        };
        $.ajax({
            type: "post",
            contentType: "application/json;charset=UTF-8",
            url: getUrl() + "app/house/login",
            async: true,
            timeout: 10000,
            data: JSON.stringify(data),
            dataType: "json",
            error: function () {

            },
            success: function (result) {
                if (result.code == 0) {
                    $(".top dd").html('<span>'+username+'</span><span>|</span><a onclick="onOutLogin()">退出登录</a>');
                }
            }
        });
    }

});

function onOutLogin(){
    sessionStorage.removeItem("name");
    sessionStorage.removeItem("password");
    sessionStorage.removeItem("mobile");
    localStorage.removeItem("name");
    localStorage.removeItem("password");
    localStorage.removeItem("mobile");
    localStorage.removeItem("startTime");
    location.reload();
}
//日期间隔
function getDateDiff(startDate,endDate)
{
    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();
    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();
    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);
    return  dates;
}

//注册跳转登录
function openLoginModal() {
    $("#registeredModal").modal("hide");
    $("#registeredModal").on("hidden.bs.modal", function () {
        $("#loginModal").modal("show");
        $("#registeredModal").off().on("hidden", "hidden.bs.modal");
    })
}

//登录跳转修改密码
function jumpRW() {
    $("#loginModal").modal("hide");
    $("#loginModal").on("hidden.bs.modal", function () {
        $("#retrieveModal").modal("show");
        $("#loginModal").off().on("hidden", "hidden.bs.modal");
    })
}

//搜索类型的选择
function onTypeChange(check) {
    $(".house-type li").attr("class", "house-type-uncheck");
    $(check).attr("class", "house-type-check");
}

//登录
function onLogin() {
    var mobile = $("#PhoneL").val();
    var password = $('#PasswordL').val();
    var qtmdl = $('#qtmdl').is(':checked');
    if (mobile == null || mobile == "" || !(/^1[3-9][0-9]\d{8}$/.test(mobile))) {
        $('.login-prompt-info span').html("请输入有效的手机号码");
        $('.login-prompt-info').css('display', 'block');
    } else if (password == null || password == "") {
        $('.login-prompt-info span').html("请输入密码");
        $('.login-prompt-info').css('display', 'block');
    } else {
        var data = {
            "mobile": mobile,
            "password": password
        };
        var url=getUrl() + "app/house/login";
        $.ajax({
            type: "post",
            contentType: "application/json;charset=UTF-8",
            url: url,
            async: true,
            timeout: 10000,
            data: JSON.stringify(data),
            dataType: "json",
            error: function () {
                $('.login-prompt-info span').html("登录失败");
                $('.login-prompt-info').css('display', 'block');
            },
            success: function (result) {
                if (result.code == 500) {
                    $('.login-prompt-info span').html("手机号码或密码不对");
                    $('.login-prompt-info').css('display', 'block');
                } else if (result.code == 200) {
                    $('.login-prompt-info span').html("该手机号码还没注册");
                    $('.login-prompt-info').css('display', 'block');
                } else if (result.code == 0) {
                    $('.login-prompt-info span').html("");
                    $('.login-prompt-info').css('display', 'none');
                    $("#loginModal").modal("hide");
                    if (qtmdl) {
                        localStorage.setItem("name", result.username);
                        localStorage.setItem("password", password);
                        localStorage.setItem("mobile", mobile);
                        localStorage.setItem("startTime", getNowFormatDate());
                        sessionStorage.removeItem("name");
                        sessionStorage.removeItem("password");
                        sessionStorage.removeItem("mobile");
                    } else {
                        localStorage.removeItem("name");
                        localStorage.removeItem("password");
                        localStorage.removeItem("mobile");
                        localStorage.removeItem("startTime");
                        sessionStorage.setItem("name", result.username);
                        sessionStorage.setItem("password", password);
                        sessionStorage.setItem("mobile", mobile);
                    }
                    $(".top dd").html('<span>'+result.username+'</span><span>|</span><a onclick="onOutLogin()">退出登录</a>');
                    alert("登录成功");
                }
            }
        });
    }
}

//获取当前时间yyyy-mm-dd
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

//修改密码
function onRetrievePassword() {
    var mobile = $("#PhoneRW").val();
    var password = $('#PasswordRW').val();
    var passwordTwo = $('#PasswordTwoR').val();
    var dxyzm = $('#DxyzmR').val();
    var dxyzmOld = sessionStorage.getItem("dxyzm");
    if (mobile == null || mobile == "" || !(/^1[3-9][0-9]\d{8}$/.test(mobile))) {
        $('.retrieve-prompt-info span').html("请输入有效的手机号码");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (password == null || password == "" || passwordTwo == null || passwordTwo == "") {
        $('.retrieve-prompt-info span').html("请输入密码");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (password.length < 6 || passwordTwo.length < 6) {
        $('.retrieve-prompt-info span').html("密码至少6位");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (password.length > 20 || passwordTwo.length > 20) {
        $('.retrieve-prompt-info span').html("密码最多20位");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (password != passwordTwo) {
        $('.retrieve-prompt-info span').html("前后两次密码不一致");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (!(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(password)) || !(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(passwordTwo))) {
        $('.retrieve-prompt-info span').html("密码只能由字母和数字，并且必须同时包含字母和数字");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (dxyzm == null || password == "") {
        $('.retrieve-prompt-info span').html("请输入短信验证码");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (dxyzm != dxyzmOld) {
        $('.retrieve-prompt-info span').html("短信验证码不对");
        $('.retrieve-prompt-info').css('display', 'block');
    } else {
        var data = {
            "mobile": mobile,
            "password": password
        };
        $.ajax({
            type: "post",
            contentType: "application/json;charset=UTF-8",
            url: getUrl() + "app/house/login",
            async: true,
            timeout: 10000,
            data: JSON.stringify(data),
            dataType: "json",
            error: function () {
                $('.retrieve-prompt-info span').html("修改密码失败");
                $('.retrieve-prompt-info').css('display', 'block');
            },
            success: function (result) {
                if (result.code == 200) {
                    $('.retrieve-prompt-info span').html("该手机号码还没注册");
                    $('.retrieve-prompt-info').css('display', 'block');
                } else if (result.code == 0) {
                    $('.retrieve-prompt-info span').html("");
                    $('.retrieve-prompt-info').css('display', 'none');
                    $("#retrieveModal").modal("hide");
                    sessionStorage.setItem("name", result.username);
                    sessionStorage.setItem("password", password);
                    sessionStorage.setItem("mobile", mobile);
                    $(".top dd").html('<span>'+result.username+'</span><span>|</span><a onclick="onOutLogin()">退出登录</a>');
                    alert("修改密码成功");
                }
            }
        });
    }
}

//注册
function onRegistered() {
    var mobile = $("#PhoneR").val();
    var password = $('#PasswordR').val();
    var passwordTwo = $('#PasswordTwoR').val();
    var username = $('#NameR').val();
    var idCard = $('#IdcardR').val();
    var isCheck = $('#isAgreed').is(':checked');
    var dxyzm = $('#DxyzmR').val();
    var dxyzmOld = sessionStorage.getItem("dxyzm");
    if (mobile == null || mobile == "" || !(/^1[3-9][0-9]\d{8}$/.test(mobile))) {
        $('.registered-prompt-info span').html("请输入有效的手机号码");
        $('.registered-prompt-info').css('display', 'block');
    } else if (username == null || username == "" || !(/^[\u4E00-\u9FA5]{1,6}$/.test(username))) {
        $('.registered-prompt-info span').html("请输入正确的姓名");
        $('.registered-prompt-info').css('display', 'block');
    } else if (idCard == null || idCard == "" || !(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idCard))) {
        $('.registered-prompt-info span').html("请输入正确的身份证号码");
        $('.registered-prompt-info').css('display', 'block');
    } else if (password == null || password == "" || passwordTwo == null || passwordTwo == "") {
        $('.registered-prompt-info span').html("请输入密码");
        $('.registered-prompt-info').css('display', 'block');
    } else if (password.length < 6 || passwordTwo.length < 6) {
        $('.registered-prompt-info span').html("密码至少6位");
        $('.registered-prompt-info').css('display', 'block');
    } else if (password.length > 20 || passwordTwo.length > 20) {
        $('.registered-prompt-info span').html("密码最多20位");
        $('.registered-prompt-info').css('display', 'block');
    } else if (password != passwordTwo) {
        $('.registered-prompt-info span').html("前后两次密码不一致");
        $('.registered-prompt-info').css('display', 'block');
    } else if (!(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(password)) || !(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(passwordTwo))) {
        $('.registered-prompt-info span').html("密码只能由字母和数字，并且必须同时包含字母和数字");
        $('.registered-prompt-info').css('display', 'block');
    } else if (!isCheck) {
        $('.registered-prompt-info span').html("请勾选我已阅读并同意《用户使用协议》");
        $('.registered-prompt-info').css('display', 'block');
    } else if (dxyzm == null || password == "") {
        $('.registered-prompt-info span').html("请输入短信验证码");
        $('.registered-prompt-info').css('display', 'block');
    } else if (dxyzm != dxyzmOld) {
        $('.registered-prompt-info span').html("短信验证码不对");
        $('.registered-prompt-info').css('display', 'block');
    } else {
        var data = {
            "mobile": mobile,
            "password": password,
            "username": username,
            "idCard": idCard
        };
        $.ajax({
            type: "post",
            contentType: "application/json;charset=UTF-8",
            url: getUrl() + "app/house/register",
            async: true,
            timeout: 10000,
            data: JSON.stringify(data),
            dataType: "json",
            error: function () {
                $('.registered-prompt-info span').html("注册失败");
                $('.registered-prompt-info').css('display', 'block');
            },
            success: function (result) {
                if (result.code == 500) {
                    $('.registered-prompt-info span').html("该手机号码已注册");
                    $('.registered-prompt-info').css('display', 'block');
                } else if (result.code == 0) {
                    $('.registered-prompt-info span').html("");
                    $('.registered-prompt-info').css('display', 'none');
                    $("#registeredModal").modal("hide");
                    sessionStorage.setItem("name", username);
                    sessionStorage.setItem("password", password);
                    sessionStorage.setItem("mobile", mobile);
                    $(".top dd").html('<span>'+username+'</span><span>|</span><a onclick="onOutLogin()">退出登录</a>');
                    alert("注册成功");
                }
            }
        });
    }
}

//注册手机验证码
function registeredVerificationCode(obj) {
    var mobile = $("#PhoneR").val();
    if (mobile == null || mobile == "" || !(/^1[3-9][0-9]\d{8}$/.test(mobile))) {
        $('.registered-prompt-info span').html("请输入有效的手机号码");
        $('.registered-prompt-info').css('display', 'block');
    } else {
        $('.registered-prompt-info span').html("");
        $('.registered-prompt-info').css('display', 'none');
        onInvokeSettime(obj, mobile);
    }
}

//修改密码手机验证码
function retrievePasswordVerificationCode(obj) {
    var mobile = $("#PhoneRW").val();
    if (mobile == null || mobile == "" || !(/^1[3-9][0-9]\d{8}$/.test(mobile))) {
        $('.retrieve-prompt-info span').html("请输入有效的手机号码");
        $('.retrieve-prompt-info').css('display', 'block');
    } else {
        $('.retrieve-prompt-info span').html("");
        $('.retrieve-prompt-info').css('display', 'none');
        onInvokeSettime(obj, mobile);
    }
}

//手机验证码
function onInvokeSettime(obj, mobile) {
    var data = {
        "mobile": mobile
    };
    $.ajax({
        type: "post",
        contentType: "application/json;charset=UTF-8",
        url: getUrl()+"app/house/message",
        async: true,
        timeout: 10000,
        data: JSON.stringify(data),
        dataType: "json",
        error: function () {
            alert("获取验证码失败");
        },
        success: function (result) {
            alert(result.code);
            sessionStorage.setItem("dxyzm", result.code);
            var countdown = 60;
            settime(obj);

            function settime(obj) {
                if (countdown == 0) {
                    $(obj).attr("disabled", false);
                    $(obj).text("获取验证码");
                    countdown = 60;
                    return;
                } else {
                    $(obj).attr("disabled", true);
                    $(obj).text(countdown + "s重新发送");
                    countdown--;
                }
                setTimeout(function () {
                        settime(obj)
                    }
                    , 1000)
            }
        }
    });
}

//搜索结果跳转页面
function onHouseSearch() {
    var houseType = $('.house-type .house-type-check').html();
    var searchContext = $('#search-context').val();
    if (houseType == "二手房") {
        window.location.href = "BuyHouse.html";
    } else if (houseType == "新房") {
        window.location.href = "BuyHouse.html";
    } else if (houseType == "租房") {
        window.location.href = "RentHouse.html";
    }
}
