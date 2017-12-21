package raven.mongodb.repository.async;

import com.mongodb.WriteConcern;
import raven.mongodb.repository.entitys.Mall;

public class MallRepositoryAsyncImpl extends MongoReaderRepositoryAsyncImpl<Mall, String> {
    public MallRepositoryAsyncImpl() {
        super("mongodb://127.0.0.1:27017/"
                , "RepositoryTest2", null, WriteConcern.ACKNOWLEDGED, null, null);

    }

    public static class MallRepositoryImpl2 extends MongoReaderRepositoryAsyncImpl<Mall, String> {
        public MallRepositoryImpl2() {
            super("mongodb://127.0.0.1:27017/"
                    , "RepositoryTest2", null, WriteConcern.ACKNOWLEDGED, null, null);

        }
    }

}
