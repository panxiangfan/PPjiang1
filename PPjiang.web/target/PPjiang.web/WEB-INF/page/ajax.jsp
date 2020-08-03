<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/serializeObject.js"></script>

	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/query.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/add.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/delete.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/update.js"></script>

	<!-- 引用jquery.js -->
	<title>Insert title here</title>
	<script type="text/javascript">
		$(function() {
			query(1);
			add();
			getSchool();
			//取消修改
			$("#calUpdate").click(function() {
				$("#updateDiv").hide();
			})
			//显示新增
			$("#addStu").click(function() {
				$("#addDiv").show();
				cal();//清空新增框里面的值
				$("#addButton").show();
				$("#updateButton").hide();
			});
			//取消(隐藏)新增
			$("#calStu").click(function() {
				$("#addDiv").hide();
				cal();//清空新增框里面的值
			});

			//首页
			$("#first").click(function() {
				query(1);
			});

			//尾页
			$("#last").click(function() {
				//获取最后一页
				var count = $("#pageCount").val();
				query(count);
			});

			//上页
			$("#up").click(function() {
				var pageNo = $("#pageNo").val();
				if (pageNo != "1")

				{
					//parseInt  10-1=9
					//10-1
					query(parseInt(pageNo) - 1);
				}
			});

			//下页
			$("#down").click(function() {
				var pageNo = $("#pageNo").val();
				var pageCount = $("#pageCount").val();
				if (pageNo != pageCount) {
					query(parseInt(pageNo) + 1);
				}
			});

			//改变每页显示多少条记录
			$("#ps").change(function() {
				var pageSize = $("#ps").val();//获取下拉框选中的值
				$("#pageSize").val(pageSize);
				query(1);
			});

		});
		//清空新增框里值
		function cal() {
			//清空文本框
			$("#name").val("");
			$("#schoolName").val("");//给下拉框设默认值
			$("#email").val("");
			$("#money").val("");
			$("#file").val("");
			$("#time").val("");
			$("#head").attr('src', "");
			//清空单选框
			$("input[type='radio'][name='sex']").each(function(){
				$(this).prop("checked",false);
			});
		}

		function getSchool() {
			$("select[name='school.id']").empty();
			$.ajax({
				url : "school",
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var sch = data[i];
						$("select[name='school.id']").append(
								"<option value="+sch.id+">"
								+ sch.schoolName + "</option>");
					}
				},
				error : function() {
					alert("失败");
				}
			});
		}
	</script>
</head>
<body style="text-align: center">
<input type="hidden" name="pageSize" id="pageSize" value="5">
<input type="hidden" name="pageNo" id="pageNo">
<input type="hidden" name="pageCount" id="pageCount">
<input type="hidden" name="sum" id="sum">
<br>
<%-- 	<fmt:message key="i18n.Languageselection"></fmt:message>
<select id="language">
    <option value="zh_CN">中文</option>
    <option value="en_US">English</option>
</select>
--%>
<input type="button" id="addStu" value="新增">
<div align="center">
	<table border="1" id="table">
		<tr>
			<th><fmt:message key="i18n.id"></fmt:message></th>
			<th><fmt:message key="i18n.head"></fmt:message></th>
			<th><fmt:message key="i18n.name"></fmt:message></th>
			<th><fmt:message key="i18n.email"></fmt:message></th>
			<th><fmt:message key="i18n.sex"></fmt:message></th>
			<th><fmt:message key="i18n.time"></fmt:message></th>
			<th><fmt:message key="i18n.money"></fmt:message></th>
			<th><fmt:message key="i18n.school"></fmt:message></th>
			<th><fmt:message key="i18n.put"></fmt:message></th>
			<th><fmt:message key="i18n.delete"></fmt:message></th>
		</tr>
	</table>
</div>
<br> 每页
<span style="color: red" id="a"></span>条 &nbsp;&nbsp;&nbsp; 第
<span style="color: red" id="b"></span>页&nbsp;&nbsp;&nbsp; 总共
<span style="color: red" id="c"></span>页&nbsp;&nbsp;&nbsp; 总共s
<span style="color: red" id="d"></span>条
<br>
<input type="button" id="first" value="首页">
<input type="button" id="up" value="上页">
<input type="button" id="down" value="下页">
<input type="button" id="last" value="尾页"> 每页
<select id="ps">
	<option value="5">5</option>
	<option value="4">4</option>
	<option value="3">3</option>
	<option value="2">2</option>
	<option value="1">1</option>
</select>条数据
<br>

<div id="addDiv" style="display: none;">
	<!-- 图片要放在from表单外 -->
	<img width="40" height="60" alt="" src="" id="head">
	<form id="addform">
		<input type="hidden" name="id" id="id" >
		姓名：<input type="text" name="name" id="name" ><br>
		性别:<input type="radio" name="sex" value="1"  >男
			<input type="radio" name="sex" value="0">女 <br>
		邮箱:<input type="text" name="email" id="email"  > <br>
		零花钱:<input type="text" name="money" id="money" > <br>
		生日:<input type="text" name="time" id="time" onClick="WdatePicker()" > <br>
		头像:<input type="file" name="file" id="file"> <br>
		学校：<select id="school.id" name="school.id">
	</select> <br> <input type="button" id="addButton" value="新增">
		<input type="button" id="updateButton" value="修改">
		<input type="button" id="calStu" value="取消">
	</form>
</div>
</body>
</html>