package raven.mongodb.repository;

import org.bson.codecs.pojo.annotations.BsonId;
import raven.data.entity.*;
import raven.data.entity.annotations.*;

import java.time.LocalDateTime;

@BsonPropertyFormat(BsonPropertyFormatType.PascalCase)
public class User2 implements Entity<Long>, AutoIncr {

    @BsonId()
    private Long id;

    private String name;

    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User2() {
        id = 0L;
    }

}
