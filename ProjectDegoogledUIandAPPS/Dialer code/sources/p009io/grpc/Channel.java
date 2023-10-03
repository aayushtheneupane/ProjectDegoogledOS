package p009io.grpc;

/* renamed from: io.grpc.Channel */
public abstract class Channel {
    public abstract <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions);
}
