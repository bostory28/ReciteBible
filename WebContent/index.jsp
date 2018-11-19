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
		$('.goAddVerseForm').click(function() {
			location.href="goAddVerseForm.do";
		});
		
		$('.updateVerse').click(function() {
			var verses_sq = $(this).attr('verses_sq');
			$('#verseSqId').attr('value', verses_sq)
		});
		$('#push').click(function() {
			var result = confirm('Are you sure?');
			if(result == true) {
				$('this').submit();
			} else {
				return false;
			}
		});
	});
</script>
<title>ReciteBibleServer</title>
</head>
<body>
	<div style="margin-top: 1%">
		<h2 style="display: inline;">ReciteBibleServer ADMIN PAGE</h2>
		<s:if test="statusServer == true">
			<h2 style="display: inline; margin-left: 1%">(ONLINE NOW </h2>
			<h2 style="display: inline;"><a href="serverOff.do">SERVER OFF</a>)</h2>
		</s:if>
		<s:elseif test="statusServer == false">
			<h2 style="display: inline; margin-left: 1%">(OFFLINE NOW </h2>
			<h2 style="display: inline;"><a href="serverOn.do">SERVER ON</a>)</h2>
		</s:elseif>
		<form action="push.do" method="post" style="display: inline;">
			<input type="submit" id="push" name="push" value="Push" style="font-size: 25px; margin-left: 30px">
		</form>
	</div>
	
<div style="clear: both;">
	<h3>
		<s:property value="userId"/>
		<s:if test="#session.userId == userId">
				<a href="logout.do">logout</a>
		</s:if>
		<a href="backup.do">Backup</a>
	</h3>
	<div style="float: left; clear: both;">
		<a href="index.do" style="font-weight: bold;">List Verse</a><br>
		<a href="year.do">List Year</a><br>
		<a href="update.do">List Update</a><br>
		<a href="admin.do">List Admin</a><br>
	</div>
	<div style="margin-left: 8em;">
		<s:form name="listVerses" method="post" onsubmit="return true" action="goUpdateVerseForm.do">
		<table border="1" style="text-align: center;">
			<thead>
				<tr>
					<th>
					<th>YEAR
					<th>MONTH
					<th>SECTION
					<th>TITLE_KR
					<th>TITLE_EN
					<th>VERSE_KR
					<th>VERSE_EN
					<th>VERSION
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listVerses" status="versesStatus">
				<tr style="text-align: left;">
					<td><input type="submit" value="update" class="updateVerse" verses_sq="<s:property value="listVerses[#versesStatus.index].verses_sq"/>" style="width: 100%">
					<td><s:property value="listVerses[#versesStatus.index].yr"/>
					<td><s:property value="listVerses[#versesStatus.index].mon"/>
					<s:if test="listVerses[#versesStatus.index].verse_section == 1">
						<td>Old Testament	
					</s:if>
					<s:else>
						<td>New Testament	
					</s:else>
					<td><s:property value="listVerses[#versesStatus.index].verse_title_kr"/>
					<td><s:property value="listVerses[#versesStatus.index].verse_title_en"/>
					<td style="width: 30em"><s:property value="listVerses[#versesStatus.index].verse_kr"/>
					<td style="width: 30em"><s:property value="listVerses[#versesStatus.index].verse_en"/>
					<td><s:property value="listVerses[#versesStatus.index].version"/>
				</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr style="text-align: left;">
					<td><input type="button" class="goAddVerseForm" value="add" style="width: 100%"/>
					<td colspan="8">
				</tr>
			</tfoot>
		</table>
		<div>
			<s:iterator var="counter" begin="pagingProcess.firstPagingNumber" end="pagingProcess.lastPagingNumber">
				<s:if test="#counter == pagingProcess.firstPagingNumber">
					<s:if test="#counter == 1">
						◀&nbsp;
					</s:if>
					<s:else>
						<a href="./index.do?page=<s:property value="#counter - 1"/>">◀&nbsp;</a>
					</s:else>
				</s:if>
				
				<s:if test="#counter == page">
					<b><s:property value="#counter"/>&nbsp;</b>
				</s:if>
				<s:else>
					<a href="./index.do?page=<s:property value="#counter"/>"><s:property value="#counter"/>&nbsp;</a> 
				</s:else>
				
				<s:if test="#counter == pagingProcess.lastPagingNumber">
					<s:if test="#counter == pagingProcess.allPagingNumber">
						▶
					</s:if>
					<s:else>
						<a href="./index.do?page=<s:property value="#counter + 1"/>">▶</a>
					</s:else>
				</s:if>
			</s:iterator>
		</div>
		<input id="adminSqId" type="hidden" name="adminsVo.admins_sq" value="">
		<input id="verseSqId" type="hidden" name="versesVo.verses_sq" value="">
		</s:form>
	</div>
</div>
</body>
</html>