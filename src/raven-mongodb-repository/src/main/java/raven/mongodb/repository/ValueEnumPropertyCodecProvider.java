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
import raven.data.entity.ValueEnum;
import raven.data.entity.ValueEnumHelper;

import java.util.HashMap;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public final class ValueEnumPropertyCodecProvider implements PropertyCodecProvider {

    private final CodecRegistry codecRegistry;
    private final static Class<ValueEnum> valueEnumTypeClass = ValueEnum.class;

    /**
     * @param codecRegistry
     */
    public ValueEnumPropertyCodecProvider(final CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    /**
     * @param type
     * @param propertyCodecRegistry
     * @param <T>
     * @return
     */
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

    /**
     * @param <T>
     */
    private static class EnumCodec<T extends ValueEnum> implements Codec<T> {

        private final Class<T> clazz;
        private HashMap<Integer, ValueEnum> valueMap;

        /**
         *
         * @param clazz
         */
        EnumCodec(final Class<T> clazz) {
            this.clazz = clazz;
            valueMap = ValueEnumHelper.getValueMap(clazz);
        }

        /**
         *
         * @param writer
         * @param value
         * @param encoderContext
         */
        @Override
        public void encode(final BsonWriter writer, final T value, final EncoderContext encoderContext) {

            writer.writeInt32(value.getValue());
        }

        /**
         *
         * @return
         */
        @Override
        public Class<T> getEncoderClass() {
            return clazz;
        }

        /**
         *
         * @param reader
         * @param decoderContext
         * @return
         */
        @Override
        public T decode(final BsonReader reader, final DecoderContext decoderContext) {

            return (T) valueMap.get(reader.readInt32());
        }
    }

}