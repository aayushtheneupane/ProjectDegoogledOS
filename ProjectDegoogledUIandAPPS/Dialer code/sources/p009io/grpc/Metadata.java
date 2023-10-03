package p009io.grpc;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* renamed from: io.grpc.Metadata */
public final class Metadata {
    public static final AsciiMarshaller<String> ASCII_STRING_MARSHALLER = new AsciiMarshaller<String>() {
        public String toAsciiString(Object obj) {
            return (String) obj;
        }
    };
    private final Map<String, List<MetadataEntry>> store = new LinkedHashMap();
    private int storeCount;

    /* renamed from: io.grpc.Metadata$AsciiKey */
    private static class AsciiKey<T> extends Key<T> {
        private final AsciiMarshaller<T> marshaller;

        /* synthetic */ AsciiKey(String str, AsciiMarshaller asciiMarshaller, C09321 r7) {
            super(str, (C09321) null);
            MoreObjects.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s. It must not end with %s", str, "-bin");
            if (asciiMarshaller != null) {
                this.marshaller = asciiMarshaller;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t).getBytes(Charsets.US_ASCII);
        }
    }

    /* renamed from: io.grpc.Metadata$AsciiMarshaller */
    public interface AsciiMarshaller<T> {
        String toAsciiString(T t);
    }

    /* renamed from: io.grpc.Metadata$BinaryKey */
    private static class BinaryKey<T> extends Key<T> {
        private final BinaryMarshaller<T> marshaller;

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T t) {
            return ((C09321) this.marshaller).toBytes(t);
        }
    }

    /* renamed from: io.grpc.Metadata$BinaryMarshaller */
    public interface BinaryMarshaller<T> {
    }

    /* renamed from: io.grpc.Metadata$Key */
    public static abstract class Key<T> {
        private static final BitSet VALID_T_CHARS;
        /* access modifiers changed from: private */
        public final String name;
        private final byte[] nameBytes;
        private final String originalName;

        static {
            BitSet bitSet = new BitSet(127);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                bitSet.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            VALID_T_CHARS = bitSet;
        }

        /* synthetic */ Key(String str, C09321 r7) {
            if (str != null) {
                this.originalName = str;
                String lowerCase = this.originalName.toLowerCase(Locale.ROOT);
                if (lowerCase != null) {
                    MoreObjects.checkArgument(lowerCase.length() != 0, "token must have at least 1 tchar");
                    for (int i = 0; i < lowerCase.length(); i++) {
                        char charAt = lowerCase.charAt(i);
                        if (charAt != ':' || i != 0) {
                            MoreObjects.checkArgument(VALID_T_CHARS.get(charAt), "Invalid character '%s' in key name '%s'", Character.valueOf(charAt), lowerCase);
                        }
                    }
                    this.name = lowerCase.intern();
                    this.nameBytes = this.name.getBytes(Charsets.US_ASCII);
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }

        /* renamed from: of */
        public static <T> Key<T> m93of(String str, AsciiMarshaller<T> asciiMarshaller) {
            return new AsciiKey(str, asciiMarshaller, (C09321) null);
        }

        /* access modifiers changed from: package-private */
        public byte[] asciiName() {
            return this.nameBytes;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.name.equals(((Key) obj).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        /* access modifiers changed from: package-private */
        public abstract byte[] toBytes(T t);

        public String toString() {
            return GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("Key{name='"), this.name, "'}");
        }
    }

    /* renamed from: io.grpc.Metadata$MetadataEntry */
    private static class MetadataEntry {
        boolean isBinary;
        Key key;
        Object parsed;
        byte[] serializedBinary;

        /* synthetic */ MetadataEntry(Key key2, Object obj, C09321 r3) {
            if (obj != null) {
                this.parsed = obj;
                if (key2 != null) {
                    this.key = key2;
                    this.isBinary = key2 instanceof BinaryKey;
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }

        public String toString() {
            if (!this.isBinary) {
                byte[] bArr = this.serializedBinary;
                if (bArr == null) {
                    bArr = this.key.toBytes(this.parsed);
                }
                this.serializedBinary = bArr;
                return new String(bArr, Charsets.US_ASCII);
            } else if (this.parsed == null) {
                return Arrays.toString(this.serializedBinary);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("");
                outline13.append(this.parsed);
                return outline13.toString();
            }
        }
    }

    private void storeAdd(String str, MetadataEntry metadataEntry) {
        List list = this.store.get(str);
        if (list == null) {
            list = new ArrayList(1);
            this.store.put(str, list);
        }
        this.storeCount++;
        list.add(metadataEntry);
    }

    public <T> void put(Key<T> key, T t) {
        MoreObjects.checkNotNull(key, "key");
        MoreObjects.checkNotNull(t, "value");
        storeAdd(key.name, new MetadataEntry(key, t, (C09321) null));
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Metadata(");
        outline13.append(this.store.toString());
        outline13.append(")");
        return outline13.toString();
    }
}
