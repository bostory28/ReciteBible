<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="icon" type="image/gif" href="img/churchlogo.png">
<style type="text/css"">
	body{background:#ccc}
	.layer{position:absolute;display:table;top:0;left:0;width:100%;height:80%}
	.layer .layer_inner{display:table-cell;text-align:center;vertical-align:middle}
	.layer .content{display:inline-block;}
</style>
<title>ReciteBibleServer</title>
</head>
<body>
	<div class="layer">
  		<div class="layer_inner">
  		<div class="content">
			<h2>ReciteBibleServer LOGIN</h2>
			<s:form method="post" action="login.do">
				<table>
					<tr>
						<td>USERNAME :
						<td><input type="text" name="adminsVo.id">
					<tr>
						<td>PASSWORD :
						<td><input type="password" name="adminsVo.pw">
					<tr>
						<td colspan="2" style="text-align: right;">
						<input type="submit" value="Login">
				</table>
			</s:form>
		</div>
    </div>
</div>
</body>
</html>