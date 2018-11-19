<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/gif" href="img/churchlogo.png">
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
		<a href="update.do"  style="font-weight: bold;">List Update</a><br>
		<a href="admin.do">List Admin</a><br>
	</div>
	<div style="margin-left: 8em;">
			<table border="1" style="text-align: center;">
			<thead>
				<tr>
					<th>DATE
					<th>UPDATE_TYPE
					<th>YR
					<th>MON
					<th>TITLE_KR
					<th>TITLE_EN
					<th>PUSHED
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listUpdate2" status="updateStatus" >
					<tr style="text-align: left;">
						<td><s:property value="listUpdate2[#updateStatus.index].lastdate"/>
						<s:if test="listUpdate2[#updateStatus.index].update_type == 1">
							<td>ADD
						</s:if>
						<s:elseif test="listUpdate2[#updateStatus.index].update_type == 2">
							<td>UPDATE
						</s:elseif>
						<s:elseif test="listUpdate2[#updateStatus.index].update_type == 3">
							<td>DELETE
						</s:elseif>
						<td><s:property value="listUpdate2[#updateStatus.index].yr"/>
						<td><s:property value="listUpdate2[#updateStatus.index].mon"/>
						<td><s:property value="listUpdate2[#updateStatus.index].verse_title_kr"/>
						<td><s:property value="listUpdate2[#updateStatus.index].verse_title_en"/>
						<s:if test="listUpdate2[#updateStatus.index].ispushed == 1">
							<td>YES
						</s:if>
						<s:else>
							<td>NO
						</s:else>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<div>
			<s:iterator var="counter" begin="pagingProcess.firstPagingNumber" end="pagingProcess.lastPagingNumber">
				<s:if test="#counter == pagingProcess.firstPagingNumber">
					<s:if test="#counter == 1">
						◀&nbsp;
					</s:if>
					<s:else>
						<a href="update.do?page=<s:property value="#counter - 1"/>">◀&nbsp;</a>
					</s:else>
				</s:if>
				
				<s:if test="#counter == page">
					<b><s:property value="#counter"/>&nbsp;</b>
				</s:if>
				<s:else>
					<a href="update.do?page=<s:property value="#counter"/>"><s:property value="#counter"/>&nbsp;</a> 
				</s:else>
				
				<s:if test="#counter == pagingProcess.lastPagingNumber">
					<s:if test="#counter == pagingProcess.allPagingNumber">
						▶
					</s:if>
					<s:else>
						<a href="update.do?page=<s:property value="#counter + 1"/>">▶</a>
					</s:else>
				</s:if>
			</s:iterator>
		</div>
	</div>
</div>

</body>
</html>