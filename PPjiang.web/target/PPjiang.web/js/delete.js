/**
 * 删除
 */
function del() {
		//这句话比上面先执行
		$(".delete").click(function() {
			var httpurl = $(this).attr("href");//获取这一行数据
			var tr = $(this).parent().parent();
			$.ajax({
				type : "delete",//ajax直接发送delete或put请求,页面不能传递参数
				url : httpurl,
				dataType : "text", 
				success : function(result) {
					tr.remove();
					query(1);
				},
				error : function(xhr, textStatus, errorThrown) {
				}
			});
			return false;
		});
	}