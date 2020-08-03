/**
 * 添加
 */
function add() {
	$("#addButton").click(function(){
		var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
		formData.append("file",$("#file")[0].files[0]);
			var student = $("#addform").serializeObject();
			//序列化表单不会序列化file字段.
			//把json转换成formdate
			Object.keys(student).forEach((key) => {
				formData.append(key, student[key]);
			});
			$.ajax({
				url :"add",
				type : "post",
				dataType : "json",
				data :formData,
				processData: false,//默认true,会转换成字符串,因为我们需要提交流,所以不能使用True
                contentType: false,//这个必须有，不然会报错
				success : function(stu){//不能与序列化的参数一样 否则冲突
					$("#test")
							.append(
								"<tr><td>"
								+ stu.id
								+ "</td><td><img alt='头像' width=60 height=90 src='download?fileName="
								+ stu.head
								+ "'></td><td>"
								+ stu.name
								+ "</td><td>"
								+ stu.email
								+ "</td><td>"
								+ (stu.sex == 1 ? '男': '女')
								+ "</td><td>"
								+ stu.time
								+ "</td><td>"
								+ stu.money
								+ "</td><td>"
								+ stu.school.schoolName
								+ "</td><td><a href='stu/"+ stu.id +"'>修改</a></td>"
								+ "<td><a class='delete' href='stu/"+ stu.id +"'>删除</a></td>"
								+ "</tr>");
							$("#addDiv").hide();// 添加成功之后隐藏div
							query(1);
						},
						error : function() {
							alert("错误");
						}
					});
				});
			}