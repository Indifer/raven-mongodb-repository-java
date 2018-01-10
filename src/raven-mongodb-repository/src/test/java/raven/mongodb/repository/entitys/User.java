package raven.mongodb.repository.entitys;

import org.bson.codecs.pojo.annotations.BsonId;
import raven.data.entity.*;
import raven.data.entity.annotations.*;

import java.util.Date;

@BsonPropertyFormat(BsonPropertyFormatType.PascalCase)
public final class User implements AutoIncr<Long>, VirtualDelete {
    @BsonId()
    private Long id;

    private String name;

    private int age;

    private Status status;

    private boolean isDel;

    private Date createDate;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean getIsDel() {
        return isDel;
    }

    @Override
    public void setIsDel(boolean del) {
        isDel = del;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User(){
        id = 0L;
        status = Status.Normal;
        createDate = new Date();
    }
}