package p009io.grpc.internal;

import com.google.common.base.MoreObjects;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import p009io.grpc.Attributes;
import p009io.grpc.ClientInterceptor;
import p009io.grpc.CompressorRegistry;
import p009io.grpc.DecompressorRegistry;
import p009io.grpc.DummyLoadBalancerFactory;
import p009io.grpc.LoadBalancer;
import p009io.grpc.ManagedChannelBuilder;
import p009io.grpc.NameResolver;
import p009io.grpc.NameResolverProvider;
import p009io.grpc.internal.AbstractManagedChannelImplBuilder;

/* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder */
public abstract class AbstractManagedChannelImplBuilder<T extends AbstractManagedChannelImplBuilder<T>> extends ManagedChannelBuilder<T> {
    static final long IDLE_MODE_MAX_TIMEOUT_DAYS = 30;
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private String authorityOverride;
    private CompressorRegistry compressorRegistry;
    private DecompressorRegistry decompressorRegistry;
    private Executor executor;
    private long idleTimeoutMillis = -1;
    private final List<ClientInterceptor> interceptors = new ArrayList();
    private LoadBalancer.Factory loadBalancerFactory;
    private NameResolver.Factory nameResolverFactory;
    private final String target;
    private String userAgent;

    /* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder$AuthorityOverridingTransportFactory */
    private static class AuthorityOverridingTransportFactory implements ClientTransportFactory {
        final String authorityOverride;
        final ClientTransportFactory factory;

        AuthorityOverridingTransportFactory(ClientTransportFactory clientTransportFactory, String str) {
            MoreObjects.checkNotNull(clientTransportFactory, "factory should not be null");
            this.factory = clientTransportFactory;
            MoreObjects.checkNotNull(str, "authorityOverride should not be null");
            this.authorityOverride = str;
        }

        public void close() {
            this.factory.close();
        }
    }

    protected AbstractManagedChannelImplBuilder(String str) {
        if (str != null) {
            this.target = str;
            return;
        }
        throw new NullPointerException();
    }

    static String makeTargetStringForDirectAddress(SocketAddress socketAddress) {
        try {
            return new URI("directaddress", "", "/" + socketAddress, (String) null).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract ClientTransportFactory buildTransportFactory();

    /* access modifiers changed from: package-private */
    public final long getIdleTimeoutMillis() {
        return this.idleTimeoutMillis;
    }

    /* access modifiers changed from: protected */
    public Attributes getNameResolverParams() {
        return Attributes.EMPTY;
    }

    public ManagedChannelImpl build() {
        ClientTransportFactory buildTransportFactory = buildTransportFactory();
        String str = this.authorityOverride;
        AuthorityOverridingTransportFactory authorityOverridingTransportFactory = str != null ? new AuthorityOverridingTransportFactory(buildTransportFactory, str) : buildTransportFactory;
        NameResolver.Factory factory = this.nameResolverFactory;
        if (factory == null) {
            factory = NameResolverProvider.asFactory();
        }
        new ManagedChannelImpl(this.target, new ExponentialBackoffPolicy$Provider(), factory, getNameResolverParams(), (LoadBalancer.Factory) MoreObjects.firstNonNull(this.loadBalancerFactory, DummyLoadBalancerFactory.getInstance()), authorityOverridingTransportFactory, (DecompressorRegistry) MoreObjects.firstNonNull(this.decompressorRegistry, DecompressorRegistry.getDefaultInstance()), (CompressorRegistry) MoreObjects.firstNonNull(this.compressorRegistry, CompressorRegistry.getDefaultInstance()), GrpcUtil.TIMER_SERVICE, GrpcUtil.STOPWATCH_SUPPLIER, this.idleTimeoutMillis, this.executor, this.userAgent, this.interceptors);
        throw null;
    }
}
