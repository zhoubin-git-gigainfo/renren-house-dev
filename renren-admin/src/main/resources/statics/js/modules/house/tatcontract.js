$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/tatcontract/list',
        datatype: "json",
        colModel: [			
			{ label: 'cId', name: 'cId', index: 'C_ID', width: 50, key: true },
			{ label: '合同号', name: 'contractNo', index: 'CONTRACT_NO', width: 80 }, 			
			{ label: '签约日期', name: 'xydate', index: 'XYDATE', width: 80 }, 			
			{ label: '创建日期', name: 'createdate', index: 'CREATEDATE', width: 80 }, 			
			{ label: '合同模板', name: 'reporid', index: 'REPORID', width: 80 }, 			
			{ label: '业务状态', name: 'state', index: 'STATE', width: 80 }, 			
			{ label: '标题', name: 'title', index: 'TITLE', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'PRICE', width: 80 }, 			
			{ label: '付款方式', name: 'payment', index: 'PAYMENT', width: 80 }, 			
			{ label: '付款方式信息', name: 'paymentMessage', index: 'PAYMENT_MESSAGE', width: 80 }, 			
			{ label: '税费等承担原则', name: 'taxationEar', index: 'TAXATION_EAR', width: 80 }, 			
			{ label: '税费等承担原则信息', name: 'taxationEarMessage', index: 'TAXATION_EAR_MESSAGE', width: 80 }, 			
			{ label: '交付期限', name: 'limitPay', index: 'LIMIT_PAY', width: 80 }, 			
			{ label: '违约责任', name: 'breachInfo', index: 'BREACH_INFO', width: 80 }, 			
			{ label: '争议解决方式', name: 'disputeResolution', index: 'DISPUTE_RESOLUTION', width: 80 }, 			
			{ label: '其他约定事项', name: 'otherAssumpsit', index: 'OTHER_ASSUMPSIT', width: 80 }, 			
			{ label: '创建时间', name: 'createDate', index: 'CREATE_DATE', width: 80 }			
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
		tatContract: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tatContract = {};
		},
		update: function (event) {
			var cId = getSelectedRow();
			if(cId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tatContract.cId == null ? "sys/tatcontract/save" : "sys/tatcontract/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tatContract),
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
			var cIds = getSelectedRows();
			if(cIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/tatcontract/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cIds),
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
		getInfo: function(cId){
			$.get(baseURL + "sys/tatcontract/info/"+cId, function(r){
                vm.tatContract = r.tatContract;
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