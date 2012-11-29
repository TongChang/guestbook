package jp.co.DDJ.guestbook.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * <p>
 * Request関連処理の共通クラス。
 * </p>
 *
 * <pre>
 * URL間の遷移機能を持ちます。
 * </pre>
 *
 * @author としふに
 *
 */
public final class Request {

	/**
	*
	* <p>
	* コンストラクタ(アクセス不可)。
	* </p>
	*
	* <pre>
	* プライベートコンストラクタ。
	* </pre>
	*
	*/
	private Request() { }

	/**
	 *
	 * <p>
	 * フォワード処理。
	 * </p>
	 *
	 * <pre>
	 * 引数のHTTPリクエスト、HTTPレスポンス、遷移先URLより
	 * 遷移先URLへフォワードします。
	 * </pre>
	 *
	 * @param req HTTPリクエスト
	 * @param resp HTTPレスポンス
	 * @param url 遷移先URL
	 */
	public static void doFoward(HttpServletRequest req, HttpServletResponse resp, String url) {
		// URLへ遷移
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		try {
			dispatcher.forward(req, resp);
		} catch (IOException ioe) {
			// TODO ログ吐いてエラーページに遷移
			ioe.printStackTrace();
		} catch (ServletException se) {
			// TODO ログ吐いてエラーページに遷移
			se.printStackTrace();
		}

	}

	/**
	 *
	 * <p>
	 * リダイレクト処理。
	 * </p>
	 *
	 * <pre>
	 * 引数のHTTPレスポンス、遷移先URLより
	 * 遷移先URLへリダイレクトします。
	 * </pre>
	 *
	 * @param resp HTTPレスポンス
	 * @param url 遷移先URL
	 */
	public static void doRedirect(HttpServletResponse resp, String url) {
		try {
			// URLへ遷移
			resp.sendRedirect(url);
		} catch (IOException ioe) {
			// TODO ログ吐いてエラーページに遷移
			ioe.printStackTrace();
		}

	}

}
