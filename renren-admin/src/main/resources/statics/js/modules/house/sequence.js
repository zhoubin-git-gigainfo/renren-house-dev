$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/sequence/list',
        datatype: "json",
        colModel: [
            {label: 'seqName', name: 'seqName', index: 'seq_name', width: 50, key: true},
            {label: '当前值', name: 'currentVal', index: 'current_val', width: 80},
            {label: '每次步进', name: 'incrementVal', index: 'increment_val', width: 80}
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
        sequence: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.sequence = {};
        },
        update: function (event) {
            var seqName = getSelectedRow();
            if (seqName == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(seqName)
        },
        saveOrUpdate: function (event) {
            var url = vm.sequence.seqName == null ? "sys/sequence/save" : "sys/sequence/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.sequence),
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
            var seqNames = getSelectedRows();
            if (seqNames == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/sequence/delete",
                    contentType: "application/json",
                    data: JSON.stringify(seqNames),
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
        getInfo: function (seqName) {
            $.get(baseURL + "sys/sequence/info/" + seqName, function (r) {
                vm.sequence = r.sequence;
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