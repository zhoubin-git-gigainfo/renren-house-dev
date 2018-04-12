$(document).ready(function () {
    var isLogin = sessionStorage.getItem("isLogin");
    if (isLogin != "true") {
        localStorage.setItem("jumpUrl", location.href);
        alert("请先登录");
        window.location.href = "HomePager.html";
    }
    var idCard = sessionStorage.getItem("idCard");
    $('#idCard').val(idCard);

});

function queryHouse() {
    var fczhId = $('#fczhId').val();
    if (fczhId == null || fczhId == "") {
        $('.query-prompt-info span').html("房产证号码不能为空");
        $('.query-prompt-info').css('display', 'block');
    } else {
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
                $('.query-prompt-info span').html("获取核验码失败");
                $('.query-prompt-info').css('display', 'block');
            },
            success: function (result) {
                if (result.code == 0) {
                    if (result.data.resultCode == 1) {
                        var html = '<tr><th>房源核验码</th><th>房屋坐落</th><th>房号</th><th>面积</th><th>房屋性质</th><th>限制状态</th><th>提醒状态</th></tr>';
                        var houses = result.data.data.houses;
                        var bodys = result.data.data.bodys;
                        $('#generate-table').bootstrapTable({
                            data: houses,
                            clickToSelect: true,
                            cache: false,
                            columns: [{
                                checkbox: true,
                                formatter: stateFormatter
                            }, {
                                field: 'code',
                                title: '房源核验码'
                            }, {
                                field: 'lname',
                                title: '房屋坐落'
                            }, {
                                field: 'hdesc',
                                title: '房号'
                            }, {
                                field: 'barea',
                                title: '面积'
                            }, {
                                field: 'huse',
                                title: '房屋性质'
                            }, {
                                field: 'bstru',
                                title: '结构'
                            }, {
                                field: 'bfete',
                                title: '建成年代'
                            }, {
                                field: 'limit_state',
                                title: '限制状态'
                            }, {
                                field: 'warn_state',
                                title: '提醒状态'
                            }],
                            onClickRow: function (row) {
                                //alert(row);
                            }
                        });
                        function stateFormatter(value, row, index) {
                            if (index == 0) {
                                return {
                                    disabled: false,
                                    checked: true//设置选中
                                };
                            }
                            return value;
                        }
                        $('#generate-btn').css('display', 'block');
                        sessionStorage.setItem("bodys", bodys);
                        $('.query-prompt-info span').html("");
                        $('.query-prompt-info').css('display', 'none');
                        //$('#fyhy .table').html(html);
                    } else {
                        $('.query-prompt-info span').html("产权证号不对");
                        $('.query-prompt-info').css('display', 'block');
                    }
                } else {
                    $('.query-prompt-info span').html("获取核验码失败");
                    $('.query-prompt-info').css('display', 'block');
                }

            }
        });
    }
}

function generateHouse() {
    var table = $('#generate-table').bootstrapTable('getSelections');
    if (table.length == 1) {
        var data = {
            "code": table[0].code,
            "token": sessionStorage.getItem("token")
        };
        $.ajax({
            type: "post",
            url: getUrl() + "api/tatcontract/save",
            async: true,
            timeout: 10000,
            data: data,
            dataType: "json",
            error: function () {

            },
            success: function (result) {
                alert(result);
            }
        });
    } else {
        alert("请选中一行")
    }

}