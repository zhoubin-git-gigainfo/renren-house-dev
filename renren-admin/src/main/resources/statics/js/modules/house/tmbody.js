$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/tmbody/list',
        datatype: "json",
        colModel: [			
			{ label: 'mId', name: 'mId', index: 'M_ID', width: 50, key: true },
			{ label: '证件号', name: 'icNo', index: 'IC_NO', width: 80 }, 			
			{ label: '证件类型', name: 'icType', index: 'IC_TYPE', width: 80 }, 			
			{ label: '电话', name: 'mdTel', index: 'MD_TEL', width: 80 }, 			
			{ label: '地址', name: 'mdAddr', index: 'MD_ADDR', width: 80 }, 			
			{ label: '发证机关', name: 'icOrg', index: 'IC_ORG', width: 80 }, 			
			{ label: '类型', name: 'mdType', index: 'MD_TYPE', width: 80 }, 			
			{ label: '所属区域', name: 'mdAname', index: 'MD_ANAME', width: 80 }, 			
			{ label: '业务主体类型', name: 'maintypeid', index: 'MAINTYPEID', width: 80 }, 			
			{ label: '占用份额', name: 'percant', index: 'PERCANT', width: 80 }, 			
			{ label: '业务主体id', name: 'cId', index: 'C_ID', width: 80 }			
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
		tmBody: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tmBody = {};
		},
		update: function (event) {
			var mId = getSelectedRow();
			if(mId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(mId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tmBody.mId == null ? "sys/tmbody/save" : "sys/tmbody/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tmBody),
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
			var mIds = getSelectedRows();
			if(mIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/tmbody/delete",
                    contentType: "application/json",
				    data: JSON.stringify(mIds),
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
		getInfo: function(mId){
			$.get(baseURL + "sys/tmbody/info/"+mId, function(r){
                vm.tmBody = r.tmBody;
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