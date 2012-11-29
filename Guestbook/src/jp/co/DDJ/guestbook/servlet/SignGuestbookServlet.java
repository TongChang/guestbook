package jp.co.DDJ.guestbook.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.DDJ.guestbook.datastore.dao.GreetingDAO;
import jp.co.DDJ.guestbook.util.Request;
import jp.co.DDJ.guestbook.util.UserInfo;

/**
 *
 * <p>
 * ゲストブック登録用サーブレットクラス。
 * </p>
 *
 * <pre>
 * ゲストブックにデータを登録するための業務ロジッククラスです。
 * </pre>
 *
 * @author としふに
 *
 */
public class SignGuestbookServlet extends HttpServlet {

	/**
	 *
	 * <p>
	 * doPostメソッド。
	 * </p>
	 *
	 * <pre>
	 * postリクエスト時に呼び出されるメソッドです。
	 * 渡されたリクエストを使用して、ゲストブックにデータを登録します。
	 * </pre>
	 *
	 * @param req HTTPレスポンス
	 * @param resp HHTPレスポンス
	 */
	public final void doPost(HttpServletRequest req, HttpServletResponse resp) {
		// ユーザ情報を取得
		UserInfo ui = new UserInfo(req);

		// ログイン情報取得精査
		if (ui.getUser() == null) {
			try {
				// 未ログインの場合はログインページへ遷移
				resp.sendRedirect(ui.getLoginURL());
			} catch (IOException ioe) {
				// エラーページに飛べたらいいなぁ
				ioe.printStackTrace();
			}
		}

		// リクエスト情報からGreetingへ登録
		GreetingDAO.insert(ui.getUser(),
				req.getParameter("content"),
				new Date());

		// トップにリダイレクト
		Request.doRedirect(resp, "/guestbook");
	}

}
