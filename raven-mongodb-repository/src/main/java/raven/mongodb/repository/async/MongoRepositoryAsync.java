package raven.mongodb.repository.async;

import com.mongodb.WriteConcern;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import raven.mongodb.repository.exceptions.FailedException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MongoRepositoryAsync<TEntity, TKey>
        extends MongoReaderRepositoryAsync<TEntity, TKey> {

    /**
     * @return
     * @throws FailedException
     */
    CompletableFuture<Long> createIncIDAsync() throws FailedException;

    /**
     * @param inc
     * @return
     * @throws FailedException
     */
    CompletableFuture<Long> createIncIDAsync(long inc) throws FailedException;

    //#region get

    /**
     * 创建自增ID
     *
     * @param entity
     * @throws FailedException
     */
    CompletableFuture createIncIDAsync(TEntity entity)
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
    CompletableFuture insertAsync(TEntity entity)
            throws FailedException;

    /**
     * @param entity
     * @param writeConcern
     * @throws FailedException
     */
    CompletableFuture insertAsync(TEntity entity, WriteConcern writeConcern)
            throws FailedException;

    /**
     * @param entitys
     * @throws FailedException
     */
    CompletableFuture insertBatchAsync(List<TEntity> entitys)
            throws FailedException;

    /**
     * @param entitys
     * @param writeConcern
     * @throws FailedException
     */
    CompletableFuture insertBatchAsync(List<TEntity> entitys, WriteConcern writeConcern)
            throws FailedException;


    /**
     * 修改单条数据
     *
     * @param filter
     * @param updateEntity
     * @return
     */
    CompletableFuture<UpdateResult> updateOneAsync(Bson filter, TEntity updateEntity)
            throws FailedException;

    /**
     * 修改单条数据
     *
     * @param filter
     * @param updateEntity
     * @param isUpsert
     * @return
     */
    CompletableFuture<UpdateResult> updateOneAsync(Bson filter, TEntity updateEntity, Boolean isUpsert)
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
    CompletableFuture<UpdateResult> updateOneAsync(Bson filter, TEntity updateEntity, Boolean isUpsert, WriteConcern writeConcern)
            throws FailedException;

    /**
     * 修改单条数据
     *
     * @param filter
     * @param update
     * @return
     */
    CompletableFuture<UpdateResult> updateOneAsync(Bson filter, Bson update);

    /**
     * 修改单条数据
     *
     * @param filter
     * @param update
     * @param isUpsert
     * @return
     */
    CompletableFuture<UpdateResult> updateOneAsync(Bson filter, Bson update, Boolean isUpsert);

    /**
     * 修改单条数据
     *
     * @param filter
     * @param update
     * @param isUpsert
     * @param writeConcern
     * @return
     */
    CompletableFuture<UpdateResult> updateOneAsync(Bson filter, Bson update, Boolean isUpsert, WriteConcern writeConcern);

    /**
     * 修改多条数据
     *
     * @param filter
     * @param update
     * @return
     */
    CompletableFuture<UpdateResult> updateManyAsync(Bson filter, Bson update);

    /**
     * 修改多条数据
     *
     * @param filter
     * @param update
     * @param writeConcern
     * @return
     */
    CompletableFuture<UpdateResult> updateManyAsync(Bson filter, Bson update, WriteConcern writeConcern);

    /**
     * @param filter
     * @param update
     * @return
     */
    CompletableFuture<TEntity> findOneAndUpdateAsync(Bson filter, Bson update);

    /**
     * @param filter
     * @param update
     * @param isUpsert default false
     * @param sort
     * @return
     */
    CompletableFuture<TEntity> findOneAndUpdateAsync(Bson filter, Bson update, Boolean isUpsert, Bson sort);

    /**
     * @param filter
     * @param entity
     * @return
     * @throws FailedException
     */
    CompletableFuture<TEntity> findOneAndUpdateAsync(Bson filter, TEntity entity)
            throws FailedException;

    /**
     * @param filter
     * @param entity
     * @param isUpsert default false
     * @param sort
     * @return
     * @throws FailedException
     */
    CompletableFuture<TEntity> findOneAndUpdateAsync(Bson filter, TEntity entity, Boolean isUpsert, Bson sort)
            throws FailedException;

    /**
     * @param filter
     * @return
     */
    CompletableFuture<TEntity> findOneAndDeleteAsync(Bson filter);

    /**
     * @param filter
     * @param sort
     * @return
     */
    CompletableFuture<TEntity> findOneAndDeleteAsync(Bson filter, Bson sort);

    /**
     * @param id
     * @return
     */
    CompletableFuture<DeleteResult> deleteOneAsync(TKey id);

    /**
     * @param id
     * @param writeConcern
     * @return
     */
    CompletableFuture<DeleteResult> deleteOneAsync(TKey id, WriteConcern writeConcern);

    /**
     * @param filter
     * @return
     */
    CompletableFuture<DeleteResult> deleteOneAsync(Bson filter);

    /**
     * @param filter
     * @param writeConcern WriteConcern
     * @return
     */
    CompletableFuture<DeleteResult> deleteOneAsync(Bson filter, WriteConcern writeConcern);

    /**
     * @param filter
     * @return
     */
    CompletableFuture<DeleteResult> deleteManyAsync(Bson filter);

    /**
     * @param filter
     * @param writeConcern WriteConcern
     * @return
     */
    CompletableFuture<DeleteResult> deleteManyAsync(Bson filter, WriteConcern writeConcern);
}
