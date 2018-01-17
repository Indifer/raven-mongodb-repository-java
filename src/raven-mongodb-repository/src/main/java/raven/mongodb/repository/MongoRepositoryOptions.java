package raven.mongodb.repository;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public class MongoRepositoryOptions {

    /**
     * 数据库连接节点
     */
    public String connString;

    /**
     * @return 数据库连接节点
     */
    public String getConnString() {
        return connString;
    }

    /**
     * @param connString 数据库连接节点
     */
    public MongoRepositoryOptions connString(String connString) {
        this.connString = connString;
        return this;
    }

    /**
     * 数据库名称
     */
    public String dbName;

    public String getDbName() {
        return dbName;
    }

    /**
     * 设置数据库名称
     *
     * @param dbName 数据库名称
     */
    public MongoRepositoryOptions dbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    /**
     * 数据库集合名称(非必须)
     */
    public String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public MongoRepositoryOptions collectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    /**
     * WriteConcern(非必须)
     */
    public WriteConcern writeConcern;

    public WriteConcern getWriteConcern() {
        return writeConcern;
    }

    public MongoRepositoryOptions writeConcern(WriteConcern writeConcern) {
        this.writeConcern = writeConcern;
        return this;
    }

    /**
     * ReadPreference(非必须)
     */
    public ReadPreference readPreference;

    public ReadPreference getReadPreference() {
        return readPreference;
    }

    public MongoRepositoryOptions readPreference(ReadPreference readPreference) {
        this.readPreference = readPreference;
        return this;
    }

    /**
     * Mongo自增长ID数据序列对象(非必须)
     */
    public MongoSequence sequence;

    public MongoSequence getSequence() {
        return sequence;
    }

    public MongoRepositoryOptions sequence(MongoSequence sequence) {
        this.sequence = sequence;
        return this;
    }

    /**
     * 构造函数
     */
    public MongoRepositoryOptions() {
    }

    /**
     * 构造函数
     *
     * @param connString     数据库连接节点(必须)
     * @param dbName         数据库连接节点(必须)
     * @param collectionName 数据库集合名称(非必须)
     * @param writeConcern   WriteConcern(非必须)
     * @param readPreference ReadPreference(非必须)
     * @param sequence       Mongo自增长ID数据序列对象(非必须)
     */
    public MongoRepositoryOptions(final String connString, final String dbName, final String collectionName, final WriteConcern writeConcern, final ReadPreference readPreference, final MongoSequence sequence) {
        this.connString = connString;
        this.dbName = dbName;
        this.collectionName = collectionName;
        this.writeConcern = writeConcern;
        this.readPreference = readPreference;
        this.sequence = sequence;
    }
}
