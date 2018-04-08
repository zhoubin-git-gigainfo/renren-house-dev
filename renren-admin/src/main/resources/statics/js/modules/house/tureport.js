$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/tureport/list',
        datatype: "json",
        colModel: [			
			{ label: 'rId', name: 'rId', index: 'R_ID', width: 50, key: true },
			{ label: '文件名称', name: 'rName', index: 'R_NAME', width: 80 }, 			
			{ label: '合同文件', name: 'rFile', index: 'R_FILE', width: 80 }, 			
			{ label: '创建时间', name: 'createdate', index: 'CREATEDATE', width: 80 }, 			
			{ label: '合同id', name: 'cId', index: 'C_ID', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tuReport: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tuReport = {};
		},
		update: function (event) {
			var rId = getSelectedRow();
			if(rId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(rId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tuReport.rId == null ? "sys/tureport/save" : "sys/tureport/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tuReport),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var rIds = getSelectedRows();
			if(rIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/tureport/delete",
                    contentType: "application/json",
				    data: JSON.stringify(rIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(rId){
			$.get(baseURL + "sys/tureport/info/"+rId, function(r){
                vm.tuReport = r.tuReport;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});