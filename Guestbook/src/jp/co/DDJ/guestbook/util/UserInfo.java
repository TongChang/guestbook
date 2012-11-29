package jp.co.DDJ.guestbook.util;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 *
 * <p>
 * ユーザ情報管理クラス。
 * </p>
 *
 * <pre>
 * ユーザ情報を管理します。
 * </pre>
 *
 * @author としふに
 *
 */
public final class UserInfo {
	/**
	 * ユーザサービス情報。
	 */
	private UserService _userservice;

	/**
	 * ユーザ情報。
	 */
	private User _user;

	/**
	 * HTTPリクエスト。
	 */
	private HttpServletRequest _req;

	/**
	 *
	 * <p>
	 * コンストラクタ。
	 * </p>
	 *
	 * <pre>
	 * コンストラクタにて、ユーザ情報の設定を行います。
	 * ユーザの取得可否については、サーブレット側で精査してください。
	 * </pre>
	 *
	 * @param req HTTPリクエスト
	 */
	public UserInfo(HttpServletRequest req) {
		// ユーザーサービスの初期化
		this._userservice = UserServiceFactory.getUserService();
		this._user = _userservice.getCurrentUser();
		this._req = req;
	}

	/**
	 *
	 * <p>
	 * ユーザ情報取得。
	 * </p>
	 *
	 * <pre>
	 * ユーザ情報を返却します。
	 * </pre>
	 *
	 * @return User ユーザ情報
	 */
	public User getUser() {
		return this._user;
	}

	/**
	 *
	 * <p>
	 * ユーザログアウトURL取得。
	 * </p>
	 *
	 * <pre>
	 * ユーザのログアウト用URLを返却します。
	 * </pre>
	 *
	 * @return String ログアウト用URL
	 */
	public String getLogoutURL() {
		return _userservice.createLogoutURL(this._req.getRequestURI());
	}

	/**
	 *
	 * <p>
	 * ユーザログインURL取得。
	 * </p>
	 *
	 * <pre>
	 * ユーザのログイン用URLを返却します。
	 * </pre>
	 *
	 * @return String ログアウト用URL
	 */
	public String getLoginURL() {
		return _userservice.createLoginURL(this._req.getRequestURI());
	}

}
