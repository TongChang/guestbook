<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="jp.co.DDJ.guestbook.jdovo.Greeting" %>
<%@ page import="jp.co.DDJ.guestbook.jdoutil.PMF" %>

<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>
  <body>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
%>
<p>こんにちは！コメントしてね(UωU)ﾉｼ (<%= user.getNickname() %>でログイン中。
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">ログアウト</a>)</p>
    <form action="/sign" method="post">
      <div><textarea name="content" rows="3" cols="60"></textarea></div>
      <div><input type="submit" value="Post Greeting" /></div>
    </form>
<%
    } else {
%>
<p>こんにちは！
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">ログイン</a>
してコメントしてね(UωU))ﾉｼ</p>
<%
    }
%>


<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
    String query = "select from " + Greeting.class.getName() + " order by date desc range 0,5";
    List<Greeting> greetings = (List<Greeting>) pm.newQuery(query).execute();
    if (greetings.isEmpty()) {
%>
<p>まだメッセージはありません</p>
<%
    } else {
        for (Greeting g : greetings) {
            if (g.getAuthor() == null) {
%>
<p>誰かさんのコメント</p>
<%
            } else {
%>
<p><b><%= g.getAuthor().getNickname() %></b>さんのコメント</p>
<%
            }
%>
|￣￣￣￣￣￣￣￣￣＼<br>
<blockquote><%= g.getContent() %></blockquote>
|　　　　　　　　　　　　　　／<br>
￣￣∨￣￣￣￣￣￣<br>
`∩＿∩ D<br>
( ´∀`)○　　 __<br>
(　　　)D……／◎＼<br>
====================<br>
<blockquote> at <b><%= g.getDate() %></b></blockquote>
<%
        }
    }
    pm.close();
%>


  </body>
</html>