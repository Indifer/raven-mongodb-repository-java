package raven.mongodb.repository;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @param <TEntity>
 * @author yi.liang
 * @since JDK1.8
 */
public interface MongoBaseRepository<TEntity> {

    /**
     * @return
     */
    MongoDatabase getDatabase();

    /**
     * 根据数据类型得到集合
     *
     * @return
     */
    MongoCollection<TEntity> getCollection();

    /**
     * 根据数据类型得到集合
     *
     * @param writeConcern WriteConcern
     * @return
     * @see WriteConcern
     */
    MongoCollection<TEntity> getCollection(WriteConcern writeConcern);

    /**
     * 根据数据类型得到集合
     *
     * @param readPreference ReadPreference
     * @return
     * @see ReadPreference
     */
    MongoCollection<TEntity> getCollection(ReadPreference readPreference);
}
