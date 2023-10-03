package p009io.grpc;

import java.net.URI;
import p009io.grpc.Attributes;

/* renamed from: io.grpc.NameResolver */
public abstract class NameResolver {

    /* renamed from: io.grpc.NameResolver$Factory */
    public static abstract class Factory {
        public static final Attributes.Key<Integer> PARAMS_DEFAULT_PORT = Attributes.Key.m92of("params-default-port");

        public abstract String getDefaultScheme();

        public abstract void newNameResolver(URI uri, Attributes attributes);
    }
}
