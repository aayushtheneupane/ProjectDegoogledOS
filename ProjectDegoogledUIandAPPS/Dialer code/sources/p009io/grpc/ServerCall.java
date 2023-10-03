package p009io.grpc;

import p009io.grpc.Attributes;

/* renamed from: io.grpc.ServerCall */
public abstract class ServerCall<ReqT, RespT> {
    static {
        Attributes.Key.m92of("remote-addr");
        Attributes.Key.m92of("ssl-session");
    }
}
