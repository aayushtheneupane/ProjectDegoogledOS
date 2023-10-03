package p009io.grpc;

import p009io.grpc.LoadBalancer;

/* renamed from: io.grpc.DummyLoadBalancerFactory */
public final class DummyLoadBalancerFactory extends LoadBalancer.Factory {
    private static final DummyLoadBalancerFactory instance = new DummyLoadBalancerFactory();

    private DummyLoadBalancerFactory() {
    }

    public static DummyLoadBalancerFactory getInstance() {
        return instance;
    }
}
