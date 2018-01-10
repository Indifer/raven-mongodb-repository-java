package raven.mongodb.repository.entitys;

//import raven.data.entity.ValueEnum;
//
//public class Status extends ValueEnum {
//
//    public final static Status Normal = new Status(0);
//    public final static Status Delete = new Status(-1);
//
//    public Status(int value, String name) {
//        super(value, name);
//    }
//
//    public Status(int value) {
//        super(value, "");
//    }
//}

import raven.data.entity.ValueEnumType;

public enum Status implements ValueEnumType {
    Normal(1), Delete(-1);

    private int value;

    Status(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

//    @Override
//    public String getName() {
//        return this.name();
//    }
}