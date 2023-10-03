package p009io.grpc.internal;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import p009io.grpc.Attributes;
import p009io.grpc.CallOptions;
import p009io.grpc.Channel;
import p009io.grpc.ClientCall;
import p009io.grpc.ClientInterceptor;
import p009io.grpc.CompressorRegistry;
import p009io.grpc.DecompressorRegistry;
import p009io.grpc.LoadBalancer;
import p009io.grpc.ManagedChannel;
import p009io.grpc.MethodDescriptor;
import p009io.grpc.NameResolver;
import p009io.grpc.Status;
import p009io.grpc.TransportManager;
import p009io.grpc.internal.SharedResourceHolder;

/* renamed from: io.grpc.internal.ManagedChannelImpl */
public final class ManagedChannelImpl extends ManagedChannel implements WithLogId {
    static final ClientTransport IDLE_MODE_TRANSPORT = new FailingClientTransport(Status.INTERNAL.withDescription("Channel is in idle mode"));
    private static final ClientTransport SHUTDOWN_TRANSPORT = new FailingClientTransport(Status.UNAVAILABLE.withDescription("Channel is shutdown"));
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    private static final Logger log = Logger.getLogger(ManagedChannelImpl.class.getName());
    private final HashSet<Object> decommissionedTransports = new HashSet<>();
    private final HashSet<Object> delayedTransports = new HashSet<>();
    private final Executor executor;
    private IdleModeTimer idleModeTimer;
    private ScheduledFuture<?> idleModeTimerFuture;
    private final long idleTimeoutMillis;
    final InUseStateAggregator<Object> inUseStateAggregator = new InUseStateAggregator<Object>() {
        /* access modifiers changed from: package-private */
        public Object getLock() {
            return ManagedChannelImpl.this.lock;
        }
    };
    private final Channel interceptorChannel;
    /* access modifiers changed from: private */
    public LoadBalancer<ClientTransport> loadBalancer;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public final NameResolver.Factory nameResolverFactory;
    /* access modifiers changed from: private */
    public final Attributes nameResolverParams;
    private final HashSet<Object> oobTransports = new HashSet<>();
    private ScheduledExecutorService scheduledExecutor;
    private boolean shutdown;
    /* access modifiers changed from: private */
    public final String target;
    private boolean terminated;
    private final SharedResourceHolder.Resource<ScheduledExecutorService> timerService;

    /* renamed from: tm */
    final TransportManager<ClientTransport> f81tm = new TransportManager<ClientTransport>() {
    };
    private final ClientTransportFactory transportFactory;
    private final ClientCallImpl$ClientTransportProvider transportProvider = new ClientCallImpl$ClientTransportProvider() {
    };
    private final Map<Object, Object> transports = new HashMap();
    private final boolean usingSharedExecutor;

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeTimer */
    private class IdleModeTimer implements Runnable {
        boolean cancelled;

        /* synthetic */ IdleModeTimer(C09461 r2) {
        }

        public void run() {
            new ArrayList();
            synchronized (ManagedChannelImpl.this.lock) {
                if (!this.cancelled) {
                    LoadBalancer unused = ManagedChannelImpl.this.loadBalancer;
                    LoadBalancer unused2 = ManagedChannelImpl.this.loadBalancer = null;
                    ManagedChannelImpl.getNameResolver(ManagedChannelImpl.this.target, ManagedChannelImpl.this.nameResolverFactory, ManagedChannelImpl.this.nameResolverParams);
                    throw null;
                }
            }
        }
    }

    ManagedChannelImpl(String str, BackoffPolicy$Provider backoffPolicy$Provider, NameResolver.Factory factory, Attributes attributes, LoadBalancer.Factory factory2, ClientTransportFactory clientTransportFactory, DecompressorRegistry decompressorRegistry, CompressorRegistry compressorRegistry, SharedResourceHolder.Resource<ScheduledExecutorService> resource, Supplier<Stopwatch> supplier, long j, Executor executor2, String str2, List<ClientInterceptor> list) {
        MoreObjects.checkNotNull(str, "target");
        this.target = str;
        MoreObjects.checkNotNull(factory, "nameResolverFactory");
        this.nameResolverFactory = factory;
        MoreObjects.checkNotNull(attributes, "nameResolverParams");
        this.nameResolverParams = attributes;
        getNameResolver(str, factory, attributes);
        throw null;
    }

    private void cancelIdleTimer() {
        ScheduledFuture<?> scheduledFuture = this.idleModeTimerFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.idleModeTimer.cancelled = true;
            this.idleModeTimerFuture = null;
            this.idleModeTimer = null;
        }
    }

    private Runnable exitIdleMode() {
        if (this.shutdown) {
            return null;
        }
        if (this.inUseStateAggregator.isInUse()) {
            cancelIdleTimer();
        } else {
            rescheduleIdleTimer();
        }
        if (this.loadBalancer != null) {
            return null;
        }
        throw null;
    }

    static NameResolver getNameResolver(String str, NameResolver.Factory factory, Attributes attributes) {
        URI uri;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri != null) {
            factory.newNameResolver(uri, attributes);
        }
        String str2 = "";
        if (!URI_PATTERN.matcher(str).matches()) {
            try {
                factory.newNameResolver(new URI(factory.getDefaultScheme(), str2, "/" + str, (String) null), attributes);
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (sb.length() > 0) {
            str2 = GeneratedOutlineSupport.outline7(" (", sb, ")");
        }
        objArr[1] = str2;
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
    }

    private void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown && this.transports.isEmpty() && this.decommissionedTransports.isEmpty() && this.delayedTransports.isEmpty() && this.oobTransports.isEmpty()) {
            if (log.isLoggable(Level.INFO)) {
                log.log(Level.INFO, "[{0}] Terminated", getLogId());
            }
            this.terminated = true;
            this.lock.notifyAll();
            if (this.usingSharedExecutor) {
                SharedResourceHolder.release(GrpcUtil.SHARED_CHANNEL_EXECUTOR, (ExecutorService) this.executor);
            }
            this.transportFactory.close();
        }
    }

    private void rescheduleIdleTimer() {
        if (this.idleTimeoutMillis != -1) {
            cancelIdleTimer();
            this.idleModeTimer = new IdleModeTimer((C09461) null);
            this.idleModeTimerFuture = this.scheduledExecutor.schedule(new LogExceptionRunnable(this.idleModeTimer), this.idleTimeoutMillis, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    public LoadBalancer<ClientTransport> exitIdleModeAndGetLb() {
        LoadBalancer<ClientTransport> loadBalancer2;
        synchronized (this.lock) {
            exitIdleMode();
            loadBalancer2 = this.loadBalancer;
        }
        return loadBalancer2;
    }

    public String getLogId() {
        return GrpcUtil.getLogId(this);
    }

    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r0 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        r0.shutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        throw null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p009io.grpc.internal.ManagedChannelImpl shutdown() {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.Object r3 = r6.lock
            monitor-enter(r3)
            boolean r4 = r6.shutdown     // Catch:{ all -> 0x004b }
            if (r4 == 0) goto L_0x0018
            monitor-exit(r3)     // Catch:{ all -> 0x004b }
            return r6
        L_0x0018:
            r4 = 1
            r6.shutdown = r4     // Catch:{ all -> 0x004b }
            io.grpc.internal.SharedResourceHolder$Resource<java.util.concurrent.ScheduledExecutorService> r4 = r6.timerService     // Catch:{ all -> 0x004b }
            java.util.concurrent.ScheduledExecutorService r5 = r6.scheduledExecutor     // Catch:{ all -> 0x004b }
            p009io.grpc.internal.SharedResourceHolder.release(r4, r5)     // Catch:{ all -> 0x004b }
            r4 = 0
            r6.scheduledExecutor = r4     // Catch:{ all -> 0x004b }
            r6.maybeTerminateChannel()     // Catch:{ all -> 0x004b }
            boolean r5 = r6.terminated     // Catch:{ all -> 0x004b }
            if (r5 != 0) goto L_0x003f
            java.util.Map<java.lang.Object, java.lang.Object> r5 = r6.transports     // Catch:{ all -> 0x004b }
            java.util.Collection r5 = r5.values()     // Catch:{ all -> 0x004b }
            r0.addAll(r5)     // Catch:{ all -> 0x004b }
            java.util.HashSet<java.lang.Object> r0 = r6.delayedTransports     // Catch:{ all -> 0x004b }
            r1.addAll(r0)     // Catch:{ all -> 0x004b }
            java.util.HashSet<java.lang.Object> r0 = r6.oobTransports     // Catch:{ all -> 0x004b }
            r2.addAll(r0)     // Catch:{ all -> 0x004b }
        L_0x003f:
            io.grpc.LoadBalancer<io.grpc.internal.ClientTransport> r0 = r6.loadBalancer     // Catch:{ all -> 0x004b }
            r6.cancelIdleTimer()     // Catch:{ all -> 0x004b }
            monitor-exit(r3)     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x004a
            r0.shutdown()
        L_0x004a:
            throw r4
        L_0x004b:
            r6 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x004b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.internal.ManagedChannelImpl.shutdown():io.grpc.internal.ManagedChannelImpl");
    }
}
