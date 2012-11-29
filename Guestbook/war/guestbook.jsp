<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="jp.co.DDJ.guestbook.datastore.entity.Greeting" %>
<%@ page import="jp.co.DDJ.guestbook.datastore.util.PMF" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="userName" value="${USER_NAME}" scope="request"/>
<c:set var="logoutUrl" value="${LOGOUT_URL}" scope="request"/>
<c:set var="greetings" value="${GREETING_LIST}" scope="request"/>

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
	</head>
	<body>
		<div id="header">
		</div>
		<div id="form">
			<p>こんにちは！コメントしてね(UωU)ﾉｼ <c:out value="${userName}" />でログイン中。
			<a href="<c:out value="${logoutUrl}" />">ログアウト</a>)</p>
			<form action="/sign" method="post">
				<div><textarea name="content" rows="3" cols="60"></textarea></div>
				<div><input type="submit" value="Post Greeting" /></div>
			</form>
		</div>
		<c:if test="${!empty greetings }">
			<c:forEach var="greeting" items="${greetings}"  varStatus="status">
				<div id="greeting_list">
					<c:out value="${status.count}"/>:<c:out value="${greeting.author}"/>さん <c:out value="${greeting.date}"/><br/>
					<c:out value="${greeting.content}"/><br/>
					<hr>
				</div>
			</c:forEach>
		</c:if>

	</body>
</html>
