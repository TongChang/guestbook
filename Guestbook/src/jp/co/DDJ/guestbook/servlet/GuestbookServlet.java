package jp.co.DDJ.guestbook.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.DDJ.guestbook.datastore.dao.GreetingDAO;
import jp.co.DDJ.guestbook.util.Request;
import jp.co.DDJ.guestbook.util.UserInfo;

/**
 *
 * <p>
 * ゲストブックページ表示用サーブレットクラス。
 * </p>
 *
 * <pre>
 * ゲストブックページを表示するための業務ロジッククラスです。
 * </pre>
 *
 * @author としふに
 *
 */
public class GuestbookServlet extends HttpServlet {
	/**
	 *
	 * <p>
	 * doGetメソッド。
	 * </p>
	 *
	 * <pre>
	 * getリクエスト時に呼び出されるメソッドです。
	 * ユーザ情報とGreetungリストを設定し、
	 * トップJSPに遷移します。
	 * </pre>
	 *
	 * @param req HTTPリクエスト
	 * @param resp HTTPレスポンス
	 */
	public final void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// googleユーザ情報の取得
		UserInfo ui = new UserInfo(req);

		// ログイン情報取得精査
		if (ui.getUser() != null) {
			// 取得有りの場合

			// JSPへの返却値を設定
			req.setAttribute("USER_NAME", ui.getUser().getNickname());
			req.setAttribute("LOGOUT_URL", ui.getLogoutURL());
			req.setAttribute("GREETING_LIST", GreetingDAO.selectListOfGreeting());

			// JSPへフォワード
			Request.doFoward(req, resp, "/guestbook.jsp");
		} else {
			// 取得無しの場合

			try {
				// 未ログインの場合はログインページへ遷移
				resp.sendRedirect(ui.getLoginURL());
			} catch (IOException ioe) {
				// エラーページに飛べたらいいなぁ
				ioe.printStackTrace();
			}
		}
	}
}
