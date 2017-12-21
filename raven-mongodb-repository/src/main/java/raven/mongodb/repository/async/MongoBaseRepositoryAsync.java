package raven.mongodb.repository.async;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.async.client.*;

public interface MongoBaseRepositoryAsync<TEntity> {

    /**
     *
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
