$(document).ready(function () {
    isLogin();
    headerMenu();

    $("#search-context").focus(function () {
        $('.search-history').css('display', 'block');
    });
    $("#search-context").blur(function () {
        $('.search-history').css('display', 'none');
    });
});

function headerMenu() {
    var htmlName = window.location.href;
    htmlName = htmlName.substring(htmlName.lastIndexOf("/") + 1);
    htmlName = htmlName.split("#")[0];
    $('.top-menu .unpitch-on').each(function () {
            alert(1);
            var href = $(this).attr("href");
            if (htmlName == href) {
                $(this).attr("href", "#");
                $(this).attr("class", "pitch-on");
            }
        }
    );
}

function isLogin() {
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
            var apartDay = getDateDiffDay(startTime, endTime);
            if (apartDay <= 7) {
                loginAjax(mobile, password)
            } else {
                localStorage.removeItem("name");
                localStorage.removeItem("password");
                localStorage.removeItem("mobile");
                localStorage.removeItem("startTime");
            }
        }
    } else {
        loginAjax(mobile, password);
    }
}

function loginAjax(mobile, password) {
    var data = {
        "mobile": mobile,
        "password": password
    };
    $.ajax({
        type: "post",
        url: getUrl() + "api/house/login",
        async: true,
        timeout: 10000,
        data: data,
        dataType: "json",
        error: function () {

        },
        success: function (result) {
            if (result.code == 0) {
                sessionStorage.setItem("isLogin",true);
                sessionStorage.setItem("token",result.token);
                isJumpUrl(result.username);
            }
        }
    });
}

function onOutLogin() {
    sessionStorage.removeItem("name");
    sessionStorage.removeItem("password");
    sessionStorage.removeItem("mobile");
    sessionStorage.removeItem("isLogin");
    localStorage.removeItem("name");
    localStorage.removeItem("password");
    localStorage.removeItem("mobile");
    localStorage.removeItem("startTime");
    location.reload();
}

//日期间隔(天)
function getDateDiffDay(startDate, endDate) {
    var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
    var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();
    var dates = Math.abs((startTime - endTime)) / (1000 * 60 * 60 * 24);
    return dates;
}

//日期间隔(分)
function getDateDiffMinute(startDate, endDate) {
    var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
    var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();
    var minutes = Math.abs((startTime - endTime)) / (1000 * 60);
    return minutes;
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
        var url = getUrl() + "api/house/login";
        $.ajax({
            type: "post",
            url: url,
            async: true,
            timeout: 10000,
            data: data,
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
                    sessionStorage.setItem("token",result.token);
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
                    sessionStorage.setItem("isLogin",true);
                    isJumpUrl(result.username);
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
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
    }
    if (second >= 0 && second <= 9) {
        second = "0" + second;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate + " " + hour + ":" + minute + ":" + second;
    return currentdate;
}

//修改密码
function onRetrievePassword() {
    var mobile = $("#PhoneRW").val();
    var password = $('#PasswordRW').val();
    var passwordTwo = $('#PasswordTwoRW').val();
    var dxyzm = $('#DxyzmRW').val();
    var dxyzmOld = sessionStorage.getItem("dxyzm");
    var startTime = sessionStorage.getItem("dxyzmStartTime");
    var minute = getDateDiffMinute(startTime, getNowFormatDate());
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
    } else if (minute > 5) {
        sessionStorage.removeItem("dxyzm");
        $('.retrieve-prompt-info span').html("短信验证码已失效，请重新获取");
        $('.retrieve-prompt-info').css('display', 'block');
    } else if (dxyzm == null || dxyzm == "") {
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
            url: getUrl() + "api/house/updatePassword",
            async: true,
            timeout: 10000,
            data: data,
            dataType: "json",
            error: function () {
                $('.retrieve-prompt-info span').html("修改密码失败");
                $('.retrieve-prompt-info').css('display', 'block');
            },
            success: function (result) {
                sessionStorage.removeItem("dxyzm");
                if (result.code == 200) {
                    $('.retrieve-prompt-info span').html("该手机号码还没注册");
                    $('.retrieve-prompt-info').css('display', 'block');
                } else if (result.code == 0) {
                    $('.retrieve-prompt-info span').html("");
                    $('.retrieve-prompt-info').css('display', 'none');
                    sessionStorage.setItem("token",result.token);
                    $("#retrieveModal").modal("hide");
                    sessionStorage.setItem("name", result.username);
                    sessionStorage.setItem("password", password);
                    sessionStorage.setItem("mobile", mobile);
                    sessionStorage.setItem("isLogin",true);
                    isJumpUrl(result.username);
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
    var startTime = sessionStorage.getItem("dxyzmStartTime");
    var minute = getDateDiffMinute(startTime, getNowFormatDate());
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
    } else if (minute > 5) {
        sessionStorage.removeItem("dxyzm");
        $('.registered-prompt-info span').html("短信验证码已失效，请重新获取");
        $('.registered-prompt-info').css('display', 'block');
    } else if (dxyzm == null || dxyzm == "") {
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
            url: getUrl() + "api/house/register",
            async: true,
            timeout: 10000,
            data: data,
            dataType: "json",
            error: function () {
                $('.registered-prompt-info span').html("注册失败");
                $('.registered-prompt-info').css('display', 'block');
            },
            success: function (result) {
                sessionStorage.removeItem("dxyzm");
                if (result.code == 500) {
                    $('.registered-prompt-info span').html(result.msg);
                    $('.registered-prompt-info').css('display', 'block');
                } else if (result.code == 0) {
                    sessionStorage.setItem("token",result.token);
                    $('.registered-prompt-info span').html("");
                    $('.registered-prompt-info').css('display', 'none');
                    $("#registeredModal").modal("hide");
                    sessionStorage.setItem("name", username);
                    sessionStorage.setItem("password", password);
                    sessionStorage.setItem("mobile", mobile);
                    sessionStorage.setItem("idCard", idCard);
                    sessionStorage.setItem("isLogin",true);
                    isJumpUrl(username);
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
        url: getUrl() + "api/house/message",
        async: true,
        timeout: 10000,
        data: data,
        dataType: "json",
        error: function () {
            alert("获取验证码失败");
        },
        success: function (result) {
            alert(result.code);
            sessionStorage.setItem("dxyzm", result.code);
            sessionStorage.setItem("dxyzmStartTime", getNowFormatDate());
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

function closeModal(btn) {
    sessionStorage.removeItem("dxyzm");
    var modal = $(btn).parents('.bs-example-modal-sm');
    modal.modal("hide");
    var id = modal.attr('id');
    var url = getUrl() + "webapp/header_new.html #" + id + " .modal-dialog";
    modal.load(url);
}

function isJumpUrl(username){
    var jumpUrl=localStorage.getItem("jumpUrl");
    if(jumpUrl==null||jumpUrl==""){
        $(".top dd").html('<span>' + username + '</span><span>|</span><a onclick="onOutLogin()">退出登录</a>');
    }else {
        localStorage.removeItem("jumpUrl");
        window.location.href=jumpUrl;
    }
}