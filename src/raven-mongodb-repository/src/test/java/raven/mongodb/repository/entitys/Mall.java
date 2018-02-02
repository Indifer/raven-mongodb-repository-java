package raven.mongodb.repository.entitys;

import org.bson.codecs.pojo.annotations.BsonId;
import raven.data.entity.Entity;
import raven.data.entity.annotation.PropertyFormat;
import raven.data.entity.annotation.PropertyFormatType;

@PropertyFormat(PropertyFormatType.PascalCase)
public class Mall implements Entity<String>{

    @BsonId()
    private String id;

    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
