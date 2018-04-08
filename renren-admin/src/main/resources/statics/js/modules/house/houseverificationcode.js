$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/houseverificationcode/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'ID', width: 50, key: true},
            {label: '核验码', name: 'code', index: 'CODE', width: 80},
            {label: '房屋id', name: 'houseId', index: 'HOUSE_ID', width: 80},
            {label: '身份证号', name: 'icno', index: 'ICNO', width: 80},
            {label: '产权证号', name: 'cdno', index: 'CDNO', width: 80},
            {label: '创建时间', name: 'vDate', index: 'V_DATE', width: 80},
            {label: '失效时间', name: 'fDate', index: 'F_DATE', width: 80},
            {label: '状态（有效，失效）', name: 'state', index: 'STATE', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        houseVerificationCode: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.houseVerificationCode = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.houseVerificationCode.id == null ? "sys/houseverificationcode/save" : "sys/houseverificationcode/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.houseVerificationCode),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/houseverificationcode/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "sys/houseverificationcode/info/" + id, function (r) {
                vm.houseVerificationCode = r.houseVerificationCode;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});