package jp.co.DDJ.guestbook.servlet;


import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.DDJ.guestbook.jdoutil.PMF;
import jp.co.DDJ.guestbook.jdovo.Greeting;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SignGuestbookServlet extends HttpServlet {
	// ロガーの定義
//	private static final Logger log = Logger.getLogger(SignGuestbookServlet.class.getName());

	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		// ユーザ情報を取得
		UserService userservice = UserServiceFactory.getUserService();
		User user = userservice.getCurrentUser();

		// リクエスト情報からGreetingを生成
		Greeting greeting = new Greeting(user,req.getParameter("content"),new Date());

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(greeting);
		} finally {
			pm.close();
		}



		// ゲストブックjspに戻る
		resp.sendRedirect("/");

	}
}
