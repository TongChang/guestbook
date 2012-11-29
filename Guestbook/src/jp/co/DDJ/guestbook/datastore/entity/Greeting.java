package jp.co.DDJ.guestbook.datastore.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

/**
 *
 * <p>
 * Greetingエンティティクラス。
 * </p>
 *
 * <pre>
 * Greetingのエンティティ保持を行います。
 * 永続性アノテーション付与。
 * </pre>
 *
 * @author としふに
 *
 */
@PersistenceCapable
public class Greeting {
	/**
	 * 一意キーアノテーション付与。
	 * 永続化アノテーション付与。
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	/**
	 * キー。
	 */
	private Key _key;

	/**
	 * 永続化アノテーション付与。
	 */
	@Persistent
	/**
	 * 投稿ユーザ情報。
	 */
	private User _author;

	/**
	 * 永続化アノテーション付与。
	 */
	@Persistent
	/**
	 * コメントコンテンツ。
	 */
	private String _content;

	/**
	 * 永続化アノテーション付与。
	 */
	@Persistent
	/**
	 * 投稿日時。
	 */
	private Date _date;

	/**
	 *
	 * <p>
	 * コンストラクタ
	 * </p>
	 *
	 * <pre>
	 * 引数にて、Greetingエンティティを生成します。
	 * </pre>
	 *
	 * @param user ユーザ情報
	 * @param content コメントコンテンツ
	 * @param date 投稿日時
	 */
	public Greeting(User user, String content, Date date) {
		this._author = user;
		this._content = content;
		this._date = date;
	}

	/**
	 *
	 * <p>
	 * コメント作成者の設定。
	 * </p>
	 *
	 * <pre>
	 * コメント作成者を設定します。
	 * </pre>
	 *
	 * @return User ユーザ情報
	 */
	public final User getAuthor() {
		return _author;
	}

	/**
	 *
	 * <p>
	 * コメントコンテンツの設定。
	 * </p>
	 *
	 * <pre>
	 * コメントコンテンツを設定します。
	 * </pre>
	 *
	 * @return String コメントコンテンツ
	 */
	public final String getContent() {
		return _content;
	}

	/**
	 *
	 * <p>
	 * 投稿日時の設定。
	 * </p>
	 *
	 * <pre>
	 * 投稿日時を設定します。
	 * </pre>
	 *
	 * @return Date 投稿日時
	 */
	public final Date getDate() {
		return _date;
	}

}
