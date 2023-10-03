package p009io.grpc.protobuf.lite;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import p009io.grpc.MethodDescriptor;

/* renamed from: io.grpc.protobuf.lite.ProtoLiteUtils */
public class ProtoLiteUtils {
    private static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshaller(T t) {
        final Parser parserForType = ((GeneratedMessageLite) t).getParserForType();
        return new MethodDescriptor.Marshaller<T>() {
        };
    }
}
