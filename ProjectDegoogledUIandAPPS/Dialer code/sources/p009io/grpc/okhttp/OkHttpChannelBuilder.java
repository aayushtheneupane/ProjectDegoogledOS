package p009io.grpc.okhttp;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.squareup.okhttp.CipherSuite;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.TlsVersion;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import p009io.grpc.Attributes;
import p009io.grpc.NameResolver;
import p009io.grpc.internal.AbstractManagedChannelImplBuilder;
import p009io.grpc.internal.ClientTransportFactory;
import p009io.grpc.internal.GrpcUtil;
import p009io.grpc.internal.SharedResourceHolder;
import p009io.grpc.okhttp.internal.Platform;

/* renamed from: io.grpc.okhttp.OkHttpChannelBuilder */
public class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    public static final ConnectionSpec DEFAULT_CONNECTION_SPEC;
    /* access modifiers changed from: private */
    public static final SharedResourceHolder.Resource<ExecutorService> SHARED_EXECUTOR = new SharedResourceHolder.Resource<ExecutorService>() {
        public void close(Object obj) {
            ((ExecutorService) obj).shutdown();
        }

        public Object create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }
    };
    private ConnectionSpec connectionSpec = DEFAULT_CONNECTION_SPEC;
    private boolean enableKeepAlive;
    private long keepAliveDelayNanos;
    private long keepAliveTimeoutNanos;
    private int maxMessageSize = 4194304;
    private NegotiationType negotiationType = NegotiationType.TLS;
    private SSLSocketFactory sslSocketFactory;
    private Executor transportExecutor;

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$OkHttpTransportFactory */
    static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final ConnectionSpec connectionSpec;
        private boolean enableKeepAlive;
        private final Executor executor;
        private long keepAliveDelayNanos;
        private long keepAliveTimeoutNanos;
        private final int maxMessageSize;
        private final SSLSocketFactory socketFactory;
        private final boolean usingSharedExecutor;

        /* synthetic */ OkHttpTransportFactory(Executor executor2, SSLSocketFactory sSLSocketFactory, ConnectionSpec connectionSpec2, int i, boolean z, long j, long j2, C09521 r10) {
            this.socketFactory = sSLSocketFactory;
            this.connectionSpec = connectionSpec2;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveDelayNanos = j;
            this.keepAliveTimeoutNanos = j2;
            this.usingSharedExecutor = executor2 == null;
            if (this.usingSharedExecutor) {
                this.executor = (Executor) SharedResourceHolder.get(OkHttpChannelBuilder.SHARED_EXECUTOR);
            } else {
                this.executor = executor2;
            }
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.usingSharedExecutor) {
                    SharedResourceHolder.release(OkHttpChannelBuilder.SHARED_EXECUTOR, (ExecutorService) this.executor);
                }
            }
        }
    }

    static {
        ConnectionSpec.Builder builder = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
        builder.cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384);
        builder.tlsVersions(TlsVersion.TLS_1_2);
        builder.supportsTlsExtensions(true);
        DEFAULT_CONNECTION_SPEC = builder.build();
    }

    private OkHttpChannelBuilder(String str) {
        super(str);
    }

    public static OkHttpChannelBuilder forTarget(String str) {
        return new OkHttpChannelBuilder(str);
    }

    /* access modifiers changed from: protected */
    public final ClientTransportFactory buildTransportFactory() {
        return new OkHttpTransportFactory(this.transportExecutor, createSocketFactory(), this.connectionSpec, this.maxMessageSize, this.enableKeepAlive, this.keepAliveDelayNanos, this.keepAliveTimeoutNanos, (C09521) null);
    }

    /* access modifiers changed from: package-private */
    public SSLSocketFactory createSocketFactory() {
        int ordinal = this.negotiationType.ordinal();
        if (ordinal == 0) {
            try {
                if (this.sslSocketFactory == null) {
                    SSLContext instance = SSLContext.getInstance("TLS", Platform.get().getProvider());
                    instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                    this.sslSocketFactory = instance.getSocketFactory();
                }
                return this.sslSocketFactory;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("TLS Provider failure", e);
            }
        } else if (ordinal == 1) {
            return null;
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown negotiation type: ");
            outline13.append(this.negotiationType);
            throw new RuntimeException(outline13.toString());
        }
    }

    /* access modifiers changed from: protected */
    public Attributes getNameResolverParams() {
        int i;
        int ordinal = this.negotiationType.ordinal();
        if (ordinal == 0) {
            i = 443;
        } else if (ordinal == 1) {
            i = 80;
        } else {
            throw new AssertionError(GeneratedOutlineSupport.outline11(new StringBuilder(), this.negotiationType, " not handled"));
        }
        Attributes.Builder newBuilder = Attributes.newBuilder();
        newBuilder.set(NameResolver.Factory.PARAMS_DEFAULT_PORT, Integer.valueOf(i));
        return newBuilder.build();
    }

    public final OkHttpChannelBuilder negotiationType(NegotiationType negotiationType2) {
        if (negotiationType2 != null) {
            this.negotiationType = negotiationType2;
            return this;
        }
        throw new NullPointerException();
    }

    public final OkHttpChannelBuilder usePlaintext(boolean z) {
        if (z) {
            negotiationType(NegotiationType.PLAINTEXT);
            return this;
        }
        throw new IllegalArgumentException("Plaintext negotiation not currently supported");
    }
}
