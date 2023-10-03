package p009io.grpc;

import com.google.common.base.MoreObjects;
import java.util.List;

/* renamed from: io.grpc.ClientInterceptors */
public class ClientInterceptors {

    /* renamed from: io.grpc.ClientInterceptors$1 */
    static class C09311 extends ClientCall<Object, Object> {
    }

    /* renamed from: io.grpc.ClientInterceptors$InterceptorChannel */
    private static class InterceptorChannel extends Channel {
        private final Channel channel;
        private final ClientInterceptor interceptor;

        /* synthetic */ InterceptorChannel(Channel channel2, ClientInterceptor clientInterceptor, C09311 r3) {
            this.channel = channel2;
            MoreObjects.checkNotNull(clientInterceptor, "interceptor");
            this.interceptor = clientInterceptor;
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            return this.interceptor.interceptCall(methodDescriptor, callOptions, this.channel);
        }
    }

    public static Channel intercept(Channel channel, List<? extends ClientInterceptor> list) {
        if (channel != null) {
            for (ClientInterceptor interceptorChannel : list) {
                channel = new InterceptorChannel(channel, interceptorChannel, (C09311) null);
            }
            return channel;
        }
        throw new NullPointerException();
    }
}
