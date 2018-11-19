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
		var now = new Date();
		var year= now.getFullYear();
		var mon = now.getMonth()+1;

		$('#month').val(mon);
		$('#year').val(year);
		
		$('#addButton').click(function() {
			var verse_en = $('#verse_en').val().trim();
			var verse_kr = $('#verse_kr').val().trim();
			var title_en = $('#title_en').val().trim();
			var title_kr = $('#title_kr').val().trim();
			var section = $('#section').val();
			
			var index_kr = verse_kr.indexOf('. ');
			var index_en = verse_en.indexOf('. ');
			
			var checkBeginingWord_kr = verse_kr.substring(0, 3);
			var checkBeginingWord_en = verse_en.substring(0, 3);
			
			var countOfWord_kr = (verse_kr.match(/ /g) || []).length;
			var countOfWord_en = (verse_en.match(/ /g) || []).length;

			if (section == 0) {
				alert("Please add contents in SECTION");
				return false;
			}
			if (title_kr == '') {
				alert("Please add contents in TITLE_KR");
				return false;
			}
			if (title_en == '') {
				alert("Please add contents in TITLE_EN");
				return false;
			}
			if (index_kr > 7 || index_kr == -1) {
				alert("Please add like '1. 'type in the begining of VERSE_KR.(0~999)");
				return false;
			}
			if (countOfWord_kr < 2) {
				alert("Please put minimun three words in VERSE_KR.");
				return false;
			}
			if (index_en > 7 || index_en == -1) {
				alert("Please add like '1. 'type in the begining of VERSE_EN.(0~999)");
				return false;
			}
			if (countOfWord_en < 2) {
				alert("Please put minimun three words in VERSE_EN.");
				return false;
			}
			$('#title_kr').val(title_kr);
			$('#title_en').val(title_en);
			$('#verse_kr').val(verse_kr);
			$('#verse_en').val(verse_en);
			return true;
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
		<a href="index.do" style="font-weight: bold;">List Verse</a><br>
		<a href="year.do">List Year</a><br>
		<a href="update.do">List Update</a><br>
		<a href="admin.do">List Admin</a><br>
	</div>
	<div style="margin-left: 8em;">
		<s:form method="post" onsubmit="return true" action="addVerse.do">
			<table border="1">
				<tr>
					<td>YEAR
					<td><select style="width: 100%" id="year" name="versesVo.yr">
							<s:iterator value="listYears" status="yearsStatus">
								<option value="<s:property value="listYears[#yearsStatus.index].yr"/>">
									<s:property value="listYears[#yearsStatus.index].yr"/>
								</option>
							</s:iterator>
						</select>
					<td>MONTH
					<td><select style="width: 100%" id="month" name="versesVo.mon">
							<s:iterator value="listMonths" status="monthsStatus">
								<option value="<s:property value="listMonths[#monthsStatus.index].mon"/>">
									<s:property value="listMonths[#monthsStatus.index].mon"/>
								</option>
							</s:iterator>
						</select>
				</tr>
				<tr>
					<td>SECTION
					<td colspan="3"><select id="section" name="versesVo.verse_section">
										<option value="0">Please Select
										<option value="1">Old Testament
										<option value="2">New Testament
									</select>
				</tr>
				<tr>
					<td>TITLE_KR
					<td colspan="3"><input type="text" name="versesVo.verse_title_kr" id="title_kr" style="width: 100%">
				</tr>
				<tr>
					<td>TITLE_EN
					<td colspan="3"><input type="text" name="versesVo.verse_title_en" id="title_en" style="width: 100%">
				</tr>
				<tr>
					<td>VERSE_KR
					<td colspan="3"><textarea id="verse_kr" name="versesVo.verse_kr" cols="45" rows="10"></textarea>
				</tr>
				<tr>
					<td>VERSE_EN
					<td colspan="3"><textarea id="verse_en" name="versesVo.verse_en" cols="45" rows="10"></textarea>
				</tr>
				<tr>
					<td>VERSION
					<td colspan="3"><input type="text" name="versesVo.version" style="width: 100%" readonly="readonly" value="1">
				</tr>
				<tr style="text-align: right;">
					<td colspan="4"><input type="submit" id="addButton" value="add" style="width: 15%">
				</tr>
			</table>
		</s:form>
	</div>
</div>

</body>
</html>