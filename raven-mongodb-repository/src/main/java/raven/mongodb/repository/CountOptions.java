package raven.mongodb.repository;

import com.mongodb.ReadPreference;
import org.bson.conversions.Bson;

public class CountOptions extends com.mongodb.client.model.CountOptions {
    private Bson filter;
    private ReadPreference readPreference;

    public Bson getFilter() {
        return filter;
    }

    /**
     *
     * @return
     */
    public ReadPreference getReadPreference() {
        return readPreference;
    }

    /**
     *
     * @param readPreference
     * @return
     */
    public CountOptions readPreference(ReadPreference readPreference) {
        this.readPreference = readPreference;
        return this;
    }

    public CountOptions filter(Bson filter) {
        this.filter = filter;
        return this;
    }

    /**
     *
     * @return
     */
    public static CountOptions Empty() {
        return new CountOptions();
    }
}
