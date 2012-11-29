package jp.co.DDJ.guestbook.datastore.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 *
 * <p>
 * 永続性マネージャファクトリのシングルトンラッパークラス。
 * </p>
 *
 * <pre>
 * 永続性マネージャファクトリ(PersistenceManagerFactory)を
 * 静的インスタンスとしてシングルトンに管理します。
 * </pre>
 *
 * @author としふに
 *
 */
public final class PMF {
	/**
	 * 永続性マネージャファクトリ。
	 */
    private static final PersistenceManagerFactory PMF_INSTANCE =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

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
    private PMF() { }

    /**
     *
     * <p>
     * 永続性マネージャ取得。
     * </p>
     *
     * <pre>
     * 永続性マネージャを返却します。
     * </pre>
     *
     * @return PersistenceManagerFactory 永続性マネージャ
     */
    public static PersistenceManagerFactory get() {
        return PMF_INSTANCE;
    }
}