<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="icon" type="image/gif" href="img/churchlogo.png">
<script src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.addAdmin').click(function() {
			$('form[name=adminForm]').attr('action', 'addAdmin.do');
		});
		$('.deleteAdmin').click(function() {
			var adminSq = $(this).attr('adminSq');
			$('#adminSqId').attr('value', adminSq);
			$('form[name=adminForm]').attr('action', 'deleteAdmin.do');
		});
	});
</script>
<title>ReciteBibleServer</title>
</head>
<body>
<h2>ReciteBibleServer ADMIN PAGE</h2>
<div>
	<h3>
		<s:property value="userId"/>
		<s:if test="#session.userId == userId">
				<a href="logout.do">logout</a>
		</s:if>
	</h3>
	<div style="float: left;">
		<a href="index.do">List Verse</a><br>
		<a href="year.do">List Year</a><br>
		<a href="update.do">List Update</a><br>
		<a href="admin.do" style="font-weight: bold;">List Admin</a><br>
	</div>
<div style="margin-left: 8em;">
		<s:form name="adminForm" method="post" onsubmit="return true">
		<table border="1" style="text-align: center;">
			<thead>
				<tr>
					<th>
					<th>NAME
					<th>ID
					<th>PASSWORD
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listAdmins" status="adminsStatus">
				<tr>
					<td><button class="deleteAdmin"
					adminSq="<s:property value="listAdmins[#adminsStatus.index].admins_sq"/>">
						delete</button>
					<td><s:property value="listAdmins[#adminsStatus.index].name"/>
					<td><s:property value="listAdmins[#adminsStatus.index].id"/>
					<td><input readonly type="password" value="<s:property value="listAdmins[#adminsStatus.index].pw"/>">
					
				</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td><button class="addAdmin">add</button>
					<td><input id="adminName" type="text" name="adminsVo.name"/>
					<td><input id="adminId" type="text" name="adminsVo.id"/>
					<td><input id="adminPw" type="text" name="adminsVo.pw"/>
				</tr>
			</tfoot>
		</table>
		<input id="adminSqId" type="hidden" name="adminsVo.admins_sq" value="">
		</s:form>
	</div>
</div>
</body>
</html>