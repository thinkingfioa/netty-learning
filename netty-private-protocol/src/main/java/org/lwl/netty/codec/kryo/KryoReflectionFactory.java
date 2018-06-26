package org.lwl.netty.codec.kryo;

import com.esotericsoftware.kryo.Serializer;
import de.javakaffee.kryoserializers.*;
import org.lwl.netty.codec.kryo.serialize.HeaderKryoSerializer;
import org.lwl.netty.codec.kryo.serialize.ProtocolMessageKryoSerializer;
import org.lwl.netty.codec.kryo.serialize.TailKryoSerializer;
import org.lwl.netty.codec.kryo.serialize.body.*;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.Header;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.Tail;
import org.lwl.netty.message.body.*;

import java.lang.reflect.InvocationHandler;
import java.net.URI;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author thinking_fioa
 * @createTime 2018/5/17
 * @description
 */


public class KryoReflectionFactory extends KryoReflectionFactorySupport{

    public KryoReflectionFactory() {
        setRegistrationRequired(false);
        setReferences(true);
        // register serializer
        register(ProtocolMessage.class, new ProtocolMessageKryoSerializer());
        register(Header.class, new HeaderKryoSerializer());
        register(Body.class, new DefaultBodyKryoSerializer());
        register(Tail.class, new TailKryoSerializer());
        register(ProtocolSubBody.class, new ProtocolSubBodyKryoSerializer());
        register(ProtocolDataBody.class, new ProtocolDataBodyKryoSerializer());
        register(LogoutBody.class, new LogoutBodyKryoSerializer());
        register(LoginRespBody.class, new LoginRespBodyKryoSerializer());
        register(LoginReqBody.class, new LoginReqBodyKryoSerializer());
        register(HeartbeatRespBody.class, new HtRespBodyKryoSerializer());
        register(HeartbeatReqBody.class, new HtReqBodyKryoSerializer());
        register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
        register(Collections.EMPTY_LIST.getClass(), new CollectionsEmptyListSerializer());
        register(Collections.EMPTY_MAP.getClass(), new CollectionsEmptyMapSerializer());
        register(Collections.EMPTY_SET.getClass(), new CollectionsEmptySetSerializer());
        register(Collections.singletonList("").getClass(), new CollectionsSingletonListSerializer());
        register(Collections.singleton("").getClass(), new CollectionsSingletonSetSerializer());
        register(Collections.singletonMap("", "").getClass(), new CollectionsSingletonMapSerializer());
        register(Pattern.class, new RegexSerializer());
        register(BitSet.class, new BitSetSerializer());
        register(URI.class, new URISerializer());
        register(UUID.class, new UUIDSerializer());
        register(GregorianCalendar.class, new GregorianCalendarSerializer());
        register(InvocationHandler.class, new JdkProxySerializer());
        UnmodifiableCollectionsSerializer.registerSerializers(this);
        SynchronizedCollectionsSerializer.registerSerializers(this);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Serializer<?> getDefaultSerializer(Class clazz)
    {
        if(EnumSet.class.isAssignableFrom(clazz)) {
            return new EnumSetSerializer();
        }

        if(EnumMap.class.isAssignableFrom(clazz)) {
            return new EnumMapSerializer();
        }

        if(Collection.class.isAssignableFrom(clazz)) {
            return new CopyForIterateCollectionSerializer();
        }

        if(Map.class.isAssignableFrom(clazz)) {
            return new CopyForIterateMapSerializer();
        }

        if(Date.class.isAssignableFrom(clazz)) {
            return new DateSerializer(clazz);
        }

        if (SubListSerializers.ArrayListSubListSerializer.canSerialize(clazz)
                || SubListSerializers.JavaUtilSubListSerializer.canSerialize(clazz)) {
            return SubListSerializers.createFor(clazz);
        }

        return super.getDefaultSerializer(clazz);
    }
}
