(function($){
	var ms = {
		init:function(totalsubpageTmep,args){
			return (function(){
				ms.fillHtml(totalsubpageTmep,args);
				ms.bindEvent(totalsubpageTmep,args);
			})();
		},
		//���html
		fillHtml:function(totalsubpageTmep,args){
			return (function(){
				totalsubpageTmep="";
				/************************START*********************/
				if(args.currPage > 1){
					totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltPrev_pager' data-go='' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
				}else{
					totalsubpageTmep += "<li class='disabled'><span class='geraltPrev_pager' data-go='' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></span></li>";
				}
				
				// 页码大于等于4的时候，添加第一个页码元素
				if(args.currPage!=1 && args.currPage>=4 && args.totalPage!=4) {
					totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go='' >"+1+"</a></li>";
				}
				/* 当前页码>4, 并且<=总页码，总页码>5，添加“···”*/
				if(args.currPage-2>2 && args.currPage<=args.totalPage && args.totalPage>5) {
					totalsubpageTmep += "<li class='ali'><span>...</span></li>";
				}
				/* 当前页码的前两页 */
				var start = args.currPage-2;
				/* 当前页码的后两页 */
				var end = args.currPage+2;
				
				if((start>1 && args.currPage<4) || args.currPage==1) {
					end++;
				}
				if(args.currPage>args.totalPage-4 && args.currPage>=args.totalPage) {
					start--;
				}
				
				for(; start<=end; start++) {
					if(start<=args.totalPage && start>=1) {
						if(start != args.currPage) {
							totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go='' >"+start+"</a></li>";
						}else{
							totalsubpageTmep += "<li class='active'><span class='geraltTb_pager' data-go='' >"+start+"</span></li>";
						}
					}
				}
				
				if(args.currPage+2<args.totalPage-1 && args.currPage>=1 && args.totalPage>5) {
					totalsubpageTmep += "<li class='ali'><span>...</span></li>";
				}
				
				if(args.currPage!=args.totalPage && args.currPage<args.totalPage-2 && args.totalPage!=4) {
					totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go='' >"+args.totalPage+"</a></li>";
				}
				
				if(args.currPage < args.totalPage){
					totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltNext_pager' data-go='' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
				}else{
					totalsubpageTmep += "<li class='disabled'><span class='geraltNext_pager' data-go=''  aria-label='Next'><span aria-hidden='true'>&raquo;</span></span></li>";
				}
				$(".pagination").html(totalsubpageTmep);
			})();
		},
		//绑定事件
		bindEvent:function(totalsubpageTmep,args){
			return (function(){
				totalsubpageTmep.on("click","a.geraltTb_pager",function(event){
					var current = parseInt($(this).text());
					ms.fillHtml(totalsubpageTmep,{"currPage":current,"totalPage":args.totalPage,"turndown":args.turndown});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
				//上一页
				totalsubpageTmep.on("click","a.geraltPrev_pager",function(){
					var current = parseInt($(".active span").html());
					ms.fillHtml(totalsubpageTmep,{"currPage":current-1,"totalPage":args.totalPage,"turndown":args.turndown});
					if(typeof(args.backFn)=="function"){
						console.log("b:"+current-1);
						args.backFn(current-1);
					}
				});
				//下一页
				
				totalsubpageTmep.on("click","a.geraltNext_pager",function(){
					var current = parseInt($(".active span").html());
					ms.fillHtml(totalsubpageTmep,{"currPage":current+1,"totalPage":args.totalPage,"turndown":args.turndown});
					if(typeof(args.backFn)=="function"){
						console.log("c:"+current+1);
						args.backFn(current+1);
					}
				});
			})();
		}
	}
	$.fn.createPage = function(options){		
		ms.init(this,options);
	}
})(jQuery);
