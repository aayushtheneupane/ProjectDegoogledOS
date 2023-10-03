package p009io.grpc;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import p009io.grpc.Codec;

/* renamed from: io.grpc.DecompressorRegistry */
public final class DecompressorRegistry {
    static final Joiner ACCEPT_ENCODING_JOINER = Joiner.m65on(',');
    private static final DecompressorRegistry DEFAULT_INSTANCE = new DecompressorRegistry(Codec.Identity.NONE, false, new DecompressorRegistry(new Codec.Gzip(), true, new DecompressorRegistry()));
    private final String advertisedDecompressors;
    private final Map<String, DecompressorInfo> decompressors;

    /* renamed from: io.grpc.DecompressorRegistry$DecompressorInfo */
    private static final class DecompressorInfo {
        final boolean advertised;
        final Decompressor decompressor;

        DecompressorInfo(Decompressor decompressor2, boolean z) {
            if (decompressor2 != null) {
                this.decompressor = decompressor2;
                this.advertised = z;
                return;
            }
            throw new NullPointerException();
        }
    }

    private DecompressorRegistry(Decompressor decompressor, boolean z, DecompressorRegistry decompressorRegistry) {
        String messageEncoding = decompressor.getMessageEncoding();
        MoreObjects.checkArgument(!messageEncoding.contains(","), "Comma is currently not allowed in message encoding");
        int size = decompressorRegistry.decompressors.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap(!decompressorRegistry.decompressors.containsKey(decompressor.getMessageEncoding()) ? size + 1 : size);
        for (DecompressorInfo next : decompressorRegistry.decompressors.values()) {
            String messageEncoding2 = next.decompressor.getMessageEncoding();
            if (!messageEncoding2.equals(messageEncoding)) {
                linkedHashMap.put(messageEncoding2, new DecompressorInfo(next.decompressor, next.advertised));
            }
        }
        linkedHashMap.put(messageEncoding, new DecompressorInfo(decompressor, z));
        this.decompressors = Collections.unmodifiableMap(linkedHashMap);
        Joiner joiner = ACCEPT_ENCODING_JOINER;
        HashSet hashSet = new HashSet(this.decompressors.size());
        for (Map.Entry next2 : this.decompressors.entrySet()) {
            if (((DecompressorInfo) next2.getValue()).advertised) {
                hashSet.add(next2.getKey());
            }
        }
        this.advertisedDecompressors = joiner.join(Collections.unmodifiableSet(hashSet));
    }

    public static DecompressorRegistry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private DecompressorRegistry() {
        this.decompressors = new LinkedHashMap(0);
        this.advertisedDecompressors = "";
    }
}
