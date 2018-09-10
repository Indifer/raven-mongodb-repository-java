package raven.mongodb.repository;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import org.bson.types.ObjectId;
import raven.data.entity.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @param <TEntity>
 * @param <TKey>
 * @author yi.liang
 * @since JDK1.8
 */
public abstract class AbstractMongoBaseRepository<TEntity extends Entity<TKey>, TKey>
        implements MongoBaseRepository<TEntity> {
    protected Class<TEntity> entityClazz;
    protected Class<TKey> keyClazz;
    protected Boolean isAutoIncrClass;

    /**
     * Mongo自增长ID数据序列
     */
    protected MongoSequence _sequence;

    protected MongoSession _mongoSession;

    private String collectionName;

    private CodecRegistry pojoCodecRegistry;

    /**
     * 集合名称
     *
     * @return
     */
    protected String getCollectionName() {
        return collectionName;
    }

    /**
     * @return
     */
    @Override
    public MongoDatabase getDatabase() {

        return _mongoSession.getDatabase().withCodecRegistry(pojoCodecRegistry);
    }

    //#region 构造函数

    private AbstractMongoBaseRepository() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClazz = (Class) params[0];
        keyClazz = (Class) params[1];
        isAutoIncrClass = DocumentUtil.AUTO_INCR_CLASS.isAssignableFrom(entityClazz);

        pojoCodecRegistry = PojoCodecRegistrys.registry(entityClazz);
    }

    /**
     * 构造函数
     *
     * @param connString     数据库连接节点
     * @param dbName         数据库名称
     * @param collectionName 集合名称
     * @param writeConcern   WriteConcern
     * @param readPreference ReadPreference
     * @param sequence       Mongo自增长ID数据序列对象
     * @see WriteConcern
     * @see ReadPreference
     */
    public AbstractMongoBaseRepository(final String connString, final String dbName, final String collectionName, final WriteConcern writeConcern, final ReadPreference readPreference, final MongoSequence sequence) {
        this();
        this._sequence = sequence != null ? sequence : new MongoSequence();
        this._mongoSession = new MongoSession(connString, dbName, writeConcern, false, readPreference);
        this.collectionName = collectionName;
        if (this.collectionName == null || this.collectionName.isEmpty()) {
            //String[] arr = entityClazz.getName().split("\\.");
            this.collectionName = entityClazz.getSimpleName();
        }
    }

    /**
     * 构造函数
     *
     * @param connString 数据库连接节点
     * @param dbName     数据库名称
     */
    public AbstractMongoBaseRepository(final String connString, final String dbName) {
        this(connString, dbName, null, null, null, null);
    }

    /**
     * 构造函数
     *
     * @param options
     * @see MongoRepositoryOptions
     */
    public AbstractMongoBaseRepository(final MongoRepositoryOptions options) {
        this(options.connString, options.dbName, options.collectionName, options.writeConcern, options.readPreference, options.sequence);
    }

    //#endregion


    //#region getCollection

    /**
     * 根据数据类型得到集合
     *
     * @return
     */
    @Override
    public MongoCollection<TEntity> getCollection() {
        return getDatabase().getCollection(getCollectionName(), entityClazz);
    }

    /**
     * 根据数据类型得到集合
     *
     * @param writeConcern WriteConcern
     * @return
     * @see WriteConcern
     */
    @Override
    public MongoCollection<TEntity> getCollection(final WriteConcern writeConcern) {
        MongoCollection<TEntity> collection = this.getCollection();
        if (writeConcern != null) {
            collection = collection.withWriteConcern(writeConcern);
        }
        return collection;
    }

    /**
     * 根据数据类型得到集合
     *
     * @param readPreference ReadPreference
     * @return
     * @see ReadPreference
     */
    @Override
    public MongoCollection<TEntity> getCollection(final ReadPreference readPreference) {
        MongoCollection<TEntity> collection = this.getCollection();
        if (readPreference != null) {
            collection = collection.withReadPreference(readPreference);
        }
        return collection;
    }

    //#endregion

    /**
     * @param entity
     * @return
     */
    protected BsonDocument toBsonDocument(final TEntity entity) {

        return DocumentUtil.convertToBsonDocument(entity, pojoCodecRegistry.get(entityClazz));
    }

    /**
     * @param includeFields
     * @return
     */
    protected Bson includeFields(final List<String> includeFields) {
        return DocumentUtil.includeFields(includeFields);
    }


    /**
     * @param findIterable
     * @param projection
     * @param sort
     * @param limit
     * @param skip
     * @param hint
     * @return
     */
    protected FindIterable<TEntity> findOptions(final FindIterable<TEntity> findIterable, final Bson projection, final Bson sort
            , final int limit, final int skip, final BsonValue hint) {

        return DocumentUtil.findOptions(findIterable, projection, sort, limit, skip, hint);

    }

    /**
     * ID赋值
     *
     * @param entity
     * @param id
     */
    protected void assignmentEntityID(final TEntity entity, final long id) {
        DocumentUtil.assignmentEntityID(keyClazz, entity, id);
    }

    /**
     * ID赋值
     *
     * @param entity
     * @param id
     */
    protected void assignmentEntityID(final TEntity entity, final ObjectId id) {
        DocumentUtil.assignmentEntityID(entity, id);

    }

}
