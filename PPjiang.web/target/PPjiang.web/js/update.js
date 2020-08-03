/**
 * 修改
 */
function update() {
	
	// 数据回显 修改前端
	$(".update").click(function() {	
				var url = $(this).attr("href");
				$("#addDiv").show();
				$("#addButton").hide();
				$("#updateButton").show();
				// 直接从界面获取值，填入到输入框当中
				// 先获取tr.再通过tr获取TD集合，再获取td里的值
				// 通过ID查询对要修改的对象,进行数据回显
				$.ajax({
					url:url,
					type:"get",
					dataType:"json",
					success:function(student) {
						$("#id").val(student.id);// 修改,是根据ID来修改
						var file  = "download?fileName="+student.head;
						$("#head").attr("src", file);
						$("#name").val(student.name);
						$("#email").val(student.email);
						$(":radio[name='sex'][value="+student.sex+"]").prop("checked",true);
						$("#time").val(student.time);
						$("#money").val(student.money);
						$("select option[value='"+student.school.id+"']").prop('selected',true);//设为选中
						$("#school.id").val(student.school.id);
					},
					error : function() {
						alert("发生错误");
					}
				});
				return false;
			});
	
	// 修改后端
	$("#updateButton").click(function() {
		var formData = new FormData();// 这里需要实例化一个FormData来进行文件上传
		formData.append("file",$("#file")[0].files[0]);
			var student = $("#addform").serializeObject();
			// 序列化表单不会序列化file字段.
			// 把json转换成formdate
			Object.keys(student).forEach((key) => {
				formData.append(key, student[key]);
			});
				formData.append("_method", "put");
		$.ajax({
			url :"updates",
			type : "post",
			data : formData,
			dataType: "json",
			processData: false,//默认true,会转换成字符串,因为我们需要提交流,所以不能使用True
            contentType: false,//这个必须有，不然会报错
			success : function(student) {
				// 循环所有的TR里面的第一个TD
				$("#table tr").find("td:eq(0)").each(function() {
					// 如果第一个TD的值正好是我们需要修改的ID值，就表示我们刚才修改的就是这行
					if ($(this).text() == student.id) {
						$(this).parent().find("td:eq(1)").html("<img alt='头像' width=60 height=90 src='download?fileName="+ student.head+"'>" );
						$(this).parent().find("td:eq(2)").text(student.name);
						$(this).parent().find("td:eq(3)").text(student.email);
						$(this).parent().find("td:eq(4)").html((student.sex == 1? '男': '女'));
						$(this).parent().find("td:eq(5)").text(student.time);
						$(this).parent().find("td:eq(6)").text(student.money);
						$(this).parent().find("td:eq(7)").text(student.school.schoolName);
					}
					cal();
					$("#addDiv").hide();
				});
			},
		});
	});
}