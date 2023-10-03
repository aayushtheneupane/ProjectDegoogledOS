package p009io.grpc;

/* renamed from: io.grpc.Codec */
public interface Codec extends Compressor, Decompressor {

    /* renamed from: io.grpc.Codec$Gzip */
    public static final class Gzip implements Codec {
        public String getMessageEncoding() {
            return "gzip";
        }
    }

    /* renamed from: io.grpc.Codec$Identity */
    public static final class Identity implements Codec {
        public static final Codec NONE = new Identity();

        private Identity() {
        }

        public String getMessageEncoding() {
            return "identity";
        }
    }
}
