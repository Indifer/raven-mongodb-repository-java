package raven.mongodb.repository.entitys;

import org.bson.codecs.pojo.annotations.BsonId;
import raven.data.entity.*;
import raven.data.entity.annotation.*;

import java.util.Date;

@PropertyFormat(PropertyFormatType.PascalCase)
public final class User implements AutoIncr<Long>, Delible {
    @BsonId()
    private Long id;

    private String name;

    private int age;

    private Status status;

    private boolean del;

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
    public boolean isDel() {
        return del;
    }

    @Override
    public void setDel(boolean del) {
        this.del = del;
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