package raven.mongodb.repository;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PropertyCodecProvider;
import org.bson.codecs.pojo.PropertyCodecRegistry;
import org.bson.codecs.pojo.TypeWithTypeParameters;
import raven.data.entity.ValueEnumType;
import raven.data.entity.ValueEnumTypes;

final public class ValueEnumPropertyCodecProvider implements PropertyCodecProvider {
    private final CodecRegistry codecRegistry;

    private final Class<ValueEnumType> valueEnumTypeClass = ValueEnumType.class;

    public ValueEnumPropertyCodecProvider(final CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> Codec<T> get(final TypeWithTypeParameters<T> type, final PropertyCodecRegistry propertyCodecRegistry) {
        Class<T> clazz = type.getType();
        if (valueEnumTypeClass.isAssignableFrom(clazz)) {
            try {
                return codecRegistry.get(clazz);
            } catch (CodecConfigurationException e) {
                return (Codec<T>) new ValueEnumPropertyCodecProvider.EnumCodec(clazz);
            }
        }
        return null;
    }

    private static class EnumCodec<T extends ValueEnumType> implements Codec<T> {
        private final Class<T> clazz;

        EnumCodec(final Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void encode(final BsonWriter writer, final T value, final EncoderContext encoderContext) {
//            if (clazz.isAssignableFrom(valueEnumTypeClass)) {
//                writer.writeInt32(((ValueEnum) value).encode());
//            } else {
//                writer.writeString(value.name());
//            }
            writer.writeInt32(value.getValue());

        }

        @Override
        public Class<T> getEncoderClass() {
            return clazz;
        }

        @Override
        public T decode(final BsonReader reader, final DecoderContext decoderContext) {
//            if (clazz.isAssignableFrom(valueEnumTypeClass)) {
//                return null;
//            } else {
//                return Enum.valueOf(clazz, reader.readString());
//            }

            return ValueEnumTypes.valueOf(clazz, reader.readInt32());
        }
    }

}