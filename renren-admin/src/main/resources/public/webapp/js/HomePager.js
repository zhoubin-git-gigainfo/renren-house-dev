$(document).ready(function () {
    localStorage.setItem("thisUrl",location.href);
});

//搜索结果跳转页面
function onHouseVerification() {
    var searchContext = $('#search-context').val();
    if (searchContext != null || searchContext != "") {
//点击搜索按钮时，去重
        KillRepeat(searchContext);
//去重后把数组存储到浏览器localStorage
        localStorage.houseSearch = houseSearchArr;
//然后再把搜索内容显示出来
        MapSearchArr();
        $('#search-context').val("");
        window.location.href = "RentHouse.html";
    }
}

var houseSearchArr;
//定义一个search的，判断浏览器有无数据存储（搜索历史）
if (localStorage.houseSearch) {
//如果有，转换成 数组的形式存放到searchArr的数组里（localStorage以字符串的形式存储，所以要把它转换成数组的形式）
    houseSearchArr = localStorage.houseSearch.split(",")
} else {
//如果没有，则定义searchArr为一个空的数组
    houseSearchArr = [];
}
//把存储的数据显示出来作为搜索历史
MapSearchArr();

function MapSearchArr() {
    var tmpHtml = "";
    for (var i = houseSearchArr.length - 1; i >= 0; i--) {
        tmpHtml += "<li>" + houseSearchArr[i] + "</li>"
    }
    $("#key-history").html(tmpHtml);
}

//去重
function KillRepeat(val) {
    var kill = 0;
    for (var i = 0; i < houseSearchArr.length; i++) {
        if (val === houseSearchArr[i]) {
            kill++;
        }
    }
    if (kill < 1) {
        if (houseSearchArr.length >= 5) {
            houseSearchArr.splice(0, 1);
        }
        houseSearchArr.push(val);
    }
}

function onJump(address){
    var isLogin = sessionStorage.getItem("isLogin");
    if(isLogin=="true"){
        location.href=$(address).attr("id")+".html";
    }else {

    }
}