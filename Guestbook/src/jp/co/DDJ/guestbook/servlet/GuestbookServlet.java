package jp.co.DDJ.guestbook.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// googleユーザ情報の取得
		UserService userservice = UserServiceFactory.getUserService();
		User user = userservice.getCurrentUser();

		// ユーザ情報保持の有無を精査
		if ( user != null ) {
			// 挨拶を表示
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello," + user.getNickname());
		} else {
			// ログイン画面へリダイレクト
			resp.sendRedirect(userservice.createLoginURL(req.getRequestURI()));
		}
	}
}
