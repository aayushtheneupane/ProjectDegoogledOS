package p009io.grpc.stub;

import p009io.grpc.CallOptions;
import p009io.grpc.Channel;
import p009io.grpc.stub.AbstractStub;

/* renamed from: io.grpc.stub.AbstractStub */
public abstract class AbstractStub<S extends AbstractStub<S>> {
    private final CallOptions callOptions;
    private final Channel channel;

    protected AbstractStub(Channel channel2) {
        CallOptions callOptions2 = CallOptions.DEFAULT;
        if (channel2 != null) {
            this.channel = channel2;
            if (callOptions2 != null) {
                this.callOptions = callOptions2;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public final CallOptions getCallOptions() {
        return this.callOptions;
    }

    public final Channel getChannel() {
        return this.channel;
    }
}
