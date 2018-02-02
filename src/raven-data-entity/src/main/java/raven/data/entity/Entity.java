package raven.data.entity;

/**
 * @param <TKey>
 * @author yi.liang
 * @since JDK1.8
 */
public interface Entity<TKey> {

    /**
     * 获取主键
     *
     * @return primary key
     */
    TKey getId();

    /**
     * 设置主键
     *
     * @param key primary key
     */
    void setId(TKey key);

}
