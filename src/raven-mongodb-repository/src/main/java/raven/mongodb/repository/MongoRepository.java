package raven.mongodb.repository;

import com.mongodb.WriteConcern;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import raven.mongodb.repository.exceptions.FailedException;

import java.util.List;

/**
 * @param <TEntity>
 * @param <TKey>
 * @author yi.liang
 * @since JDK1.8
 */
public interface MongoRepository<TEntity, TKey>
        extends MongoReaderRepository<TEntity, TKey> {

    /**
     * @return
     * @throws FailedException
     */
    long createIncID() throws FailedException;

    /**
     * @param inc
     * @return
     * @throws FailedException
     */
    long createIncID(long inc) throws FailedException;

    /**
     * @param inc
     * @param iteration
     * @return
     * @throws FailedException
     */
    long createIncID(long inc, int iteration) throws FailedException;

    //#region get

    /**
     * 创建自增ID
     *
     * @param entity
     * @throws FailedException
     */
    void createIncID(TEntity entity)
            throws FailedException;

    /**
     * 创建ObjectID
     *
     * @param entity
     */
    void createObjectID(TEntity entity);

    /**
     * @param entity
     * @throws FailedException
     */
    void insert(TEntity entity)
            throws FailedException;

    /**
     * @param entity
     * @param writeConcern
     * @throws FailedException
     */
    void insert(TEntity entity, WriteConcern writeConcern)
            throws FailedException;

    /**
     * @param entitys
     * @throws FailedException
     */
    void insertBatch(List<TEntity> entitys)
            throws FailedException;

    /**
     * @param entitys
     * @param writeConcern
     * @throws FailedException
     */
    void insertBatch(List<TEntity> entitys, WriteConcern writeConcern)
            throws FailedException;


    /**
     * 修改单条数据
     *
     * @param filter
     * @param updateEntity
     * @return
     */
    UpdateResult updateOne(Bson filter, TEntity updateEntity)
            throws FailedException;

    /**
     * 修改单条数据
     *
     * @param filter
     * @param updateEntity
     * @param isUpsert
     * @return
     */
    UpdateResult updateOne(Bson filter, TEntity updateEntity, Boolean isUpsert)
            throws FailedException;

    /**
     * 修改单条数据
     *
     * @param filter
     * @param updateEntity
     * @param isUpsert
     * @param writeConcern
     * @return
     */
    UpdateResult updateOne(Bson filter, TEntity updateEntity, Boolean isUpsert, WriteConcern writeConcern)
            throws FailedException;

    /**
     * 修改单条数据
     *
     * @param filter
     * @param update
     * @return
     */
    UpdateResult updateOne(Bson filter, Bson update);

    /**
     * 修改单条数据
     *
     * @param filter
     * @param update
     * @param isUpsert
     * @return
     */
    UpdateResult updateOne(Bson filter, Bson update, Boolean isUpsert);

    /**
     * 修改单条数据
     *
     * @param filter
     * @param update
     * @param isUpsert
     * @param writeConcern
     * @return
     */
    UpdateResult updateOne(Bson filter, Bson update, Boolean isUpsert, WriteConcern writeConcern);

    /**
     * 修改多条数据
     *
     * @param filter
     * @param update
     * @return
     */
    UpdateResult updateMany(Bson filter, Bson update);

    /**
     * 修改多条数据
     *
     * @param filter
     * @param update
     * @param writeConcern
     * @return
     */
    UpdateResult updateMany(Bson filter, Bson update, WriteConcern writeConcern);

    /**
     * @param filter
     * @param update
     * @return
     */
    TEntity findOneAndUpdate(Bson filter, Bson update);

    /**
     * @param filter
     * @param update
     * @param isUpsert default false
     * @param sort
     * @return
     */
    TEntity findOneAndUpdate(Bson filter, Bson update, Boolean isUpsert, Bson sort);

    /**
     * @param filter
     * @param entity
     * @return
     * @throws FailedException
     */
    TEntity findOneAndUpdate(Bson filter, TEntity entity)
            throws FailedException;

    /**
     * @param filter
     * @param entity
     * @param isUpsert default false
     * @param sort
     * @return
     * @throws FailedException
     */
    TEntity findOneAndUpdate(Bson filter, TEntity entity, Boolean isUpsert, Bson sort)
            throws FailedException;

    /**
     * @param filter
     * @return
     */
    TEntity findOneAndDelete(Bson filter);

    /**
     * @param filter
     * @param sort
     * @return
     */
    TEntity findOneAndDelete(Bson filter, Bson sort);

    /**
     * @param id
     * @return
     */
    DeleteResult deleteOne(TKey id);

    /**
     * @param id
     * @param writeConcern
     * @return
     */
    DeleteResult deleteOne(TKey id, WriteConcern writeConcern);

    /**
     * @param filter
     * @return
     */
    DeleteResult deleteOne(Bson filter);

    /**
     * @param filter
     * @param writeConcern WriteConcern
     * @return
     */
    DeleteResult deleteOne(Bson filter, WriteConcern writeConcern);

    /**
     * @param filter
     * @return
     */
    DeleteResult deleteMany(Bson filter);

    /**
     * @param filter
     * @param writeConcern WriteConcern
     * @return
     */
    DeleteResult deleteMany(Bson filter, WriteConcern writeConcern);

}
