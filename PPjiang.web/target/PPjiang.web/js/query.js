/**
 * 查询  
 */
function query(pageNo) {
	var pageSize = $("#pageSize").val();
	
		$.ajax({
			type : "get",
			url : "stulist",
			dataType : "json",
			data : {
				"pageNo" : pageNo,
				"pageSize" : pageSize
			},
			success : function(page)// page(list)
			{
				var pageSize = page.pageSize;
				var pageNo = page.pageNo;
				var pageCount = page.pageCount;
				var sum = page.sum;

				$("#a").text(pageSize);
				$("#b").text(pageNo);
				$("#c").text(pageCount);
				$("#d").text(sum);

				// 设置隐藏值,就是分页信息
				$("#pageSize").val(page.pageSize);
				$("#pageNo").val(page.pageNo);
				$("#pageCount").val(page.pageCount);
				$("#sum").val(page.sum);
				
				
				// 首尾给效果
				if (page.pageNo == page.pageCount) {
					$("#down").css("background-color", "gray");
				} else {
					$("#down").css("background-color", "");
				}
				if (page.pageNo == 1) {
					$("#up").css("background-color", "gray");
				} else {
					$("#up").css("background-color", "");
				}
				$("#table").empty();// 先清空内容,这里会给表头一起清空.
				// 所以给表头加上
				$("#table").append(
					"<tr><td>ID</td><td>头像</td><td>名字</td><td>邮箱</td><td>性别</td><td>时间</td><td>零花钱</td><td>学校</td><td>修改</td><td>删除</td></tr>");
				var list = page.list;
				for (var i = 0; i < list.length; i++) { 
					var student = list[i];// 获取一个一个对象(student)
					$("#table")
							.append(
								"<tr><td>"
								+ student.id
								+ "</td><td><img alt='头像' width=60 height=90 src='download?fileName="
								+ student.head
								+ "'></td><td>"
								+ student.name
								+ "</td><td>"
								+ student.email
								+ "</td><td>"
								+ (student.sex == 1 ? '男': '女')
								+ "</td><td>"
								+ student.time
								+ "</td><td>"
								+ student.money
								+ "</td><td>"
								+ student.school.schoolName
								+ "</td><td><a class='update' href='update/"+ student.id +"'>修改</a></td>"
								+ "<td><a class='delete' href='stu/"+ student.id +"'>删除</a></td>"
								+ "</tr>");
						}
						//让删除的JQUERY选择器生效
						del();
						update()
					},
					
				})
			}


