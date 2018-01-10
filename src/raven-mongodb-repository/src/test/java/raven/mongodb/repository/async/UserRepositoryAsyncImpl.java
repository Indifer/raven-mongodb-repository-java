package raven.mongodb.repository.async;

import com.mongodb.WriteConcern;
import raven.mongodb.repository.entitys.User;

public class UserRepositoryAsyncImpl  extends MongoRepositoryAsyncImpl<User, Long>{
    public UserRepositoryAsyncImpl() {
        super("mongodb://127.0.0.1:27017/"
                , "RepositoryTest2", null, WriteConcern.ACKNOWLEDGED, null, null);

    }
}
