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
		$('.addYear').click(function() {
			var newYr = $('#textYear').val().trim();
			var check = false;
			if (newYr == '') {
				alert('Please put a year.');
				return false;
			}
			
			$('.years').each(function() {
				var yr = $(this).val();
				if (newYr == yr) {
					alert('Not allowed to put the same year in the list.');
					check = true;
				}
			});
			if (check) {
				return false;
			}
			
			$('form[name=yearForm]').attr('action', 'addYear.do');
			return true;
		});
		$('.deleteYear').click(function() {
			var yr = $(this).attr('yr');
			$('#yrId').attr('value', yr);
			$('form[name=yearForm]').attr('action', 'deleteYear.do');
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
		<a href="year.do" style="font-weight: bold;">List Year</a><br>
		<a href="update.do">List Update</a><br>
		<a href="admin.do">List Admin</a><br>
	</div>
	<div style="margin-left: 8em;">
		<s:form name="yearForm" method="post" onsubmit="return true">
		<table border="1" style="text-align: center;">
			<thead>
				<tr>
					<th>
					<th>Year
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listYears" status="yearsStatus">
				<tr>
					<td><button class="deleteYear" yr="<s:property value="listYears[#yearsStatus.index].yr"/>"">
						delete</button>
					<td><s:property value="listYears[#yearsStatus.index].yr"/>
					<input class="years" type="hidden" value="<s:property value="listYears[#yearsStatus.index].yr"/>">
				</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td><input type="submit" class="addYear" value="add"> 
					<td><input id="textYear" type="text" name="newYr"/>
				</tr>
			</tfoot>
		</table>
		<input id="yrId" type="hidden" name="yearVo.yr" value="">
		</s:form>
	</div>
</div>
</body>
</html>