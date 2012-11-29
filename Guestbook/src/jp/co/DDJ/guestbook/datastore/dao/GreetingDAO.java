package jp.co.DDJ.guestbook.datastore.dao;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import jp.co.DDJ.guestbook.datastore.entity.Greeting;
import jp.co.DDJ.guestbook.datastore.util.PMF;

import com.google.appengine.api.users.User;

/**
 *
 * <p>
 * Greetingに対するDAOクラス。
 * </p>
 *
 * <pre>
 * Greetingに対して、select・insert・deleteを行います。
 * </pre>
 *
 * @author としふに
 *
 */
public final class GreetingDAO {
	/**
	 *
	 * <p>
	 * コンストラクタ。
	 * </p>
	 *
	 * <pre>
	 * 処理なし。
	 * </pre>
	 *
	 */
	private GreetingDAO() { }

	/**
	 *
	 * <p>
	 * インサートメソッド。
	 * </p>
	 *
	 * <pre>
	 * 引数のユーザ情報、メッセージコンテンツ、登録日時にて
	 * Greetingへ登録します。
	 * </pre>
	 *
	 * @param user ユーザ情報
	 * @param messageContents メッセージコンテンツ
	 * @param date 登録日時
	 */
	public static void insert(User user, String messageContents, Date date) {
		// 永続性マネージャの生成
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			// Greetingを生成し、登録する
			pm.makePersistent(
					new Greeting(user, messageContents, date));
		} finally {
			// 永続性マネージャーを閉じる。
			pm.close();
		}

	}


	/**
	 *
	 * <p>
	 * Greetingリストの取得。
	 * </p>
	 *
	 * <pre>
	 * Greetingより、エンティティを抽出します。
	 * </pre>
	 *
	 * @return List Greetingリスト
	 */
	@SuppressWarnings("unchecked")
	public static List<Greeting> selectListOfGreeting() {
		List<Greeting> ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Greeting.class.getName() + " order by _date desc";
		ret = (List<Greeting>) pm.newQuery(query).execute();
		return ret;
	}

}
