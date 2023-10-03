package p009io.grpc;

import com.google.common.base.MoreObjects;
import java.util.HashMap;

/* renamed from: io.grpc.Attributes */
public final class Attributes {
    public static final Attributes EMPTY = new Attributes();
    /* access modifiers changed from: private */
    public final HashMap<Key<?>, Object> data = new HashMap<>();

    /* renamed from: io.grpc.Attributes$Builder */
    public static final class Builder {
        private Attributes product = new Attributes((C09301) null);

        /* synthetic */ Builder(C09301 r2) {
        }

        public Attributes build() {
            MoreObjects.checkState(this.product != null, "Already built");
            Attributes attributes = this.product;
            this.product = null;
            return attributes;
        }

        public <T> Builder set(Key<T> key, T t) {
            this.product.data.put(key, t);
            return this;
        }
    }

    /* renamed from: io.grpc.Attributes$Key */
    public static final class Key<T> {
        private final String name;

        private Key(String str) {
            this.name = str;
        }

        /* renamed from: of */
        public static <T> Key<T> m92of(String str) {
            return new Key<>(str);
        }

        public String toString() {
            return this.name;
        }
    }

    private Attributes() {
    }

    public static Builder newBuilder() {
        return new Builder((C09301) null);
    }

    public String toString() {
        return this.data.toString();
    }

    /* synthetic */ Attributes(C09301 r1) {
    }
}
