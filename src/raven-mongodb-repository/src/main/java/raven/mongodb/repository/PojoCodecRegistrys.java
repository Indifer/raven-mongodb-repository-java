package raven.mongodb.repository;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.PropertyCodecProvider;
import raven.mongodb.repository.conventions.CustomConventions;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public class PojoCodecRegistrys {

    /**
     * @param pojoCodecRegistry
     * @param entityClazz
     * @return
     */
    public static CodecRegistry registry(CodecRegistry pojoCodecRegistry, Class<?> entityClazz) {
        //registry ValueEnumType CodecProvider
        PropertyCodecProvider propertyCodecProvider = new raven.mongodb.repository.ValueEnumPropertyCodecProvider(pojoCodecRegistry);

        //registry conventions
        ClassModel<?> classModel = ClassModel.builder(entityClazz).conventions(CustomConventions.DEFAULT_CONVENTIONS).build();
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().register(classModel).register(propertyCodecProvider).build();
        pojoCodecRegistry = fromRegistries(pojoCodecRegistry, fromProviders(pojoCodecProvider));

        return pojoCodecRegistry;
    }
}
