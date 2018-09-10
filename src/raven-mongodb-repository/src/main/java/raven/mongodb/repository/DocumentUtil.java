package raven.mongodb.repository;

import com.mongodb.client.model.Projections;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.BsonValue;
import org.bson.codecs.Encoder;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import raven.data.entity.AutoIncr;
import raven.data.entity.Entity;

import java.util.List;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public abstract class DocumentUtil {

    public static final String PRIMARY_KEY_NAME = "_id";

    public static final Class<AutoIncr> AUTO_INCR_CLASS = AutoIncr.class;

    public static final Class<ObjectId> OBJECT_ID_CLASS = ObjectId.class;

    public static final String CREATE_INSTANCE_METHOD = "createInstance";

    /**
     * @param findIterable
     * @param projection
     * @param sort
     * @param limit
     * @param skip
     * @param hint
     * @return
     */
    public static  <TEntity> com.mongodb.async.client.FindIterable<TEntity> findOptions(final com.mongodb.async.client.FindIterable<TEntity> findIterable, final Bson projection, final Bson sort
            , final int limit, final int skip, final BsonValue hint) {

        com.mongodb.async.client.FindIterable<TEntity> filter = findIterable;
        if (projection != null) {
            filter = findIterable.projection(projection);
        }

        if (limit > 0) {
            filter = findIterable.limit(limit);
        }

        if (skip > 0) {
            filter = findIterable.skip(skip);
        }

        if (sort != null) {
            filter = findIterable.sort(sort);
        }

        if (hint != null) {
            Bson hintBson = new BsonDocument("$hint", hint);
            filter = findIterable.hint(hintBson);
        }

        return filter;

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
    public static  <TEntity> com.mongodb.client.FindIterable<TEntity> findOptions(final com.mongodb.client.FindIterable<TEntity> findIterable, final Bson projection, final Bson sort
            , final int limit, final int skip, final BsonValue hint) {

        com.mongodb.client.FindIterable<TEntity> filter = findIterable;
        if (projection != null) {
            filter = findIterable.projection(projection);
        }

        if (limit > 0) {
            filter = findIterable.limit(limit);
        }

        if (skip > 0) {
            filter = findIterable.skip(skip);
        }

        if (sort != null) {
            filter = findIterable.sort(sort);
        }

        if (hint != null) {
            Bson hintBson = new BsonDocument("$hint", hint);
            filter = findIterable.hint(hintBson);
        }

        return filter;

    }

    /**
     * @param entity
     * @return
     */
    public static <TEntity> BsonDocument convertToBsonDocument(final TEntity entity, final Encoder<TEntity> encoder) {

        return new BsonDocumentWrapper(entity, encoder);
    }

    /**
     * @param includeFields
     * @return
     */
    public static Bson includeFields(final List<String> includeFields) {

        Bson projection = null;
        if (includeFields != null && includeFields.size() > 0) {
            projection = Projections.include(includeFields);
        }

        return projection;
    }

    /**
     * ID赋值
     *
     * @param keyClazz
     * @param entity
     * @param id
     * @param <TEntity>
     * @param <TKey>
     */
    public static <TEntity extends Entity<TKey>, TKey> void assignmentEntityID(final Class<TKey> keyClazz, final TEntity entity, final long id) {
        Entity<TKey> tEntity = entity;

        if (keyClazz.equals(Integer.class)) {
            ((Entity<Integer>) tEntity).setId((int) id);
        } else if (keyClazz.equals(Long.class)) {
            ((Entity<Long>) tEntity).setId(id);
        } else if (keyClazz.equals(Short.class)) {
            ((Entity<Short>) tEntity).setId((short) id);
        }

    }

    /**
     * ID赋值
     *
     * @param entity
     * @param id
     * @param <TEntity>
     * @param <TKey>
     */
    public static <TEntity extends Entity<TKey>, TKey> void assignmentEntityID(final TEntity entity, final ObjectId id) {
        Entity<ObjectId> tEntity = (Entity<ObjectId>) entity;
        tEntity.setId(id);

    }
}
