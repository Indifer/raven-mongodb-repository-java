package raven.mongodb.repository.conventions;

import org.bson.codecs.pojo.ClassModelBuilder;
import org.bson.codecs.pojo.Convention;
import org.bson.codecs.pojo.PropertyModelBuilder;

import org.bson.codecs.pojo.annotations.BsonProperty;
import raven.data.entity.annotations.*;

import java.lang.annotation.Annotation;

/**
 * @author yi.liang
 * @since JDK1.8
 */
final class ConventioinFormatPropertyImpl implements Convention {

    /**
     * @param classModelBuilder
     */
    @Override
    public void apply(final ClassModelBuilder<?> classModelBuilder) {

        BsonPropertyFormatType formatType = BsonPropertyFormatType.CamelCase;
        for (final Annotation annotation : classModelBuilder.getAnnotations()) {
            if (annotation instanceof BsonPropertyFormat) {
                formatType = ((BsonPropertyFormat) annotation).value();
                break;
            }
        }

        if (formatType == BsonPropertyFormatType.CamelCase)
            return;

        for (PropertyModelBuilder<?> propertyModelBuilder : classModelBuilder.getPropertyModelBuilders()) {
            formatPropertyAnnotations(classModelBuilder, propertyModelBuilder);
        }
        //processCreatorAnnotation(classModelBuilder);
        //cleanPropertyBuilders(classModelBuilder);
    }


    /**
     * @param classModelBuilder
     * @param propertyModelBuilder
     */
    private void formatPropertyAnnotations(final ClassModelBuilder<?> classModelBuilder,
                                           final PropertyModelBuilder<?> propertyModelBuilder) {

        for (Annotation annotation : propertyModelBuilder.getReadAnnotations()) {
            if (annotation instanceof BsonProperty) {
                return;
            }
        }

        for (Annotation annotation : propertyModelBuilder.getWriteAnnotations()) {
            if (annotation instanceof BsonProperty) {
                return;
            }
        }

        String name = propertyModelBuilder.getName();
        char[] _temp = name.toCharArray();
        _temp[0] = Character.toUpperCase(_temp[0]);
        name = new String(_temp);

        propertyModelBuilder.writeName(name);
        propertyModelBuilder.readName(name);
    }
}
