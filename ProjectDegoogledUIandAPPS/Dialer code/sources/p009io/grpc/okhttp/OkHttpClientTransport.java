package p009io.grpc.okhttp;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Ticker;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import p009io.grpc.Metadata;
import p009io.grpc.Status;
import p009io.grpc.StatusException;
import p009io.grpc.internal.ConnectionClientTransport;
import p009io.grpc.internal.GrpcUtil;
import p009io.grpc.internal.Http2Ping;
import p009io.grpc.internal.KeepAliveManager;
import p009io.grpc.internal.ManagedClientTransport;
import p009io.grpc.internal.SerializingExecutor;
import p009io.grpc.okhttp.internal.ConnectionSpec;
import p009io.grpc.okhttp.internal.framed.ErrorCode;
import p009io.grpc.okhttp.internal.framed.FrameReader;
import p009io.grpc.okhttp.internal.framed.FrameWriter;

/* renamed from: io.grpc.okhttp.OkHttpClientTransport */
class OkHttpClientTransport implements ConnectionClientTransport {
    private static final OkHttpClientStream[] EMPTY_STREAM_ARRAY = new OkHttpClientStream[0];
    private static final Map<ErrorCode, Status> ERROR_CODE_TO_STATUS;
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final InetSocketAddress address = null;
    private ClientFrameHandler clientFrameHandler;
    SettableFuture<Void> connectedFuture;
    Runnable connectingCallback;
    private final ConnectionSpec connectionSpec;
    private final String defaultAuthority;
    private final Executor executor;
    private AsyncFrameWriter frameWriter;
    private boolean goAwaySent;
    private Status goAwayStatus;
    private boolean inUse;
    /* access modifiers changed from: private */
    public KeepAliveManager keepAliveManager;
    /* access modifiers changed from: private */
    public ManagedClientTransport.Listener listener;
    private final Object lock = new Object();
    private int maxConcurrentStreams = 0;
    private final int maxMessageSize;
    private int nextStreamId;
    private LinkedList<OkHttpClientStream> pendingStreams = new LinkedList<>();
    private Http2Ping ping;
    private final InetSocketAddress proxyAddress;
    private final String proxyPassword;
    private final String proxyUsername;
    private final Random random = new Random();
    private ScheduledExecutorService scheduler;
    private final SerializingExecutor serializingExecutor;
    private Socket socket;
    private boolean stopped;
    private final Map<Integer, OkHttpClientStream> streams = new HashMap();
    private FrameReader testFrameReader;
    private FrameWriter testFrameWriter;
    private final Ticker ticker;
    private final String userAgent;

    /* renamed from: io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler */
    class ClientFrameHandler implements FrameReader.Handler, Runnable {
        FrameReader frameReader;
        final /* synthetic */ OkHttpClientTransport this$0;

        public void run() {
            String name = Thread.currentThread().getName();
            if (!GrpcUtil.IS_RESTRICTED_APPENGINE) {
                Thread.currentThread().setName("OkHttpClientTransport");
            }
            while (this.frameReader.nextFrame(this)) {
                try {
                    if (this.this$0.keepAliveManager != null) {
                        this.this$0.keepAliveManager.onDataReceived();
                    }
                } catch (Exception e) {
                    this.this$0.startGoAway(0, ErrorCode.PROTOCOL_ERROR, Status.UNAVAILABLE.withCause(e));
                    try {
                        this.frameReader.close();
                    } catch (IOException e2) {
                        OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", e2);
                    }
                    this.this$0.listener.transportTerminated();
                    if (GrpcUtil.IS_RESTRICTED_APPENGINE) {
                        return;
                    }
                } catch (Throwable th) {
                    try {
                        this.frameReader.close();
                    } catch (IOException e3) {
                        OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", e3);
                    }
                    this.this$0.listener.transportTerminated();
                    if (!GrpcUtil.IS_RESTRICTED_APPENGINE) {
                        Thread.currentThread().setName(name);
                    }
                    throw th;
                }
            }
            this.this$0.startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withDescription("End of stream or IOException"));
            try {
                this.frameReader.close();
            } catch (IOException e4) {
                OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", e4);
            }
            this.this$0.listener.transportTerminated();
            if (GrpcUtil.IS_RESTRICTED_APPENGINE) {
                return;
            }
            Thread.currentThread().setName(name);
        }
    }

    static {
        EnumMap enumMap = new EnumMap(ErrorCode.class);
        enumMap.put(ErrorCode.NO_ERROR, Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        enumMap.put(ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("Protocol error"));
        enumMap.put(ErrorCode.INTERNAL_ERROR, Status.INTERNAL.withDescription("Internal error"));
        enumMap.put(ErrorCode.FLOW_CONTROL_ERROR, Status.INTERNAL.withDescription("Flow control error"));
        enumMap.put(ErrorCode.STREAM_CLOSED, Status.INTERNAL.withDescription("Stream closed"));
        enumMap.put(ErrorCode.FRAME_TOO_LARGE, Status.INTERNAL.withDescription("Frame too large"));
        enumMap.put(ErrorCode.REFUSED_STREAM, Status.UNAVAILABLE.withDescription("Refused stream"));
        enumMap.put(ErrorCode.CANCEL, Status.CANCELLED.withDescription("Cancelled"));
        enumMap.put(ErrorCode.COMPRESSION_ERROR, Status.INTERNAL.withDescription("Compression error"));
        enumMap.put(ErrorCode.CONNECT_ERROR, Status.INTERNAL.withDescription("Connect error"));
        enumMap.put(ErrorCode.ENHANCE_YOUR_CALM, Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        enumMap.put(ErrorCode.INADEQUATE_SECURITY, Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        ERROR_CODE_TO_STATUS = Collections.unmodifiableMap(enumMap);
    }

    OkHttpClientTransport(String str, Executor executor2, FrameReader frameReader, FrameWriter frameWriter2, int i, Socket socket2, Ticker ticker2, Runnable runnable, SettableFuture<Void> settableFuture, int i2) {
        this.maxMessageSize = i2;
        this.defaultAuthority = "notarealauthority:80";
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", str);
        if (executor2 != null) {
            this.executor = executor2;
            this.serializingExecutor = new SerializingExecutor(executor2);
            if (frameReader != null) {
                this.testFrameReader = frameReader;
                if (frameWriter2 != null) {
                    this.testFrameWriter = frameWriter2;
                    if (socket2 != null) {
                        this.socket = socket2;
                        this.nextStreamId = i;
                        this.ticker = ticker2;
                        this.connectionSpec = null;
                        this.connectingCallback = runnable;
                        if (settableFuture != null) {
                            this.connectedFuture = settableFuture;
                            this.proxyAddress = null;
                            this.proxyUsername = null;
                            this.proxyPassword = null;
                            return;
                        }
                        throw new NullPointerException();
                    }
                    throw new NullPointerException();
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    private Throwable getPingFailure() {
        synchronized (this.lock) {
            if (this.goAwayStatus != null) {
                StatusException asException = this.goAwayStatus.asException();
                return asException;
            }
            StatusException statusException = new StatusException(Status.UNAVAILABLE.withDescription("Connection closed"));
            return statusException;
        }
    }

    private void maybeClearInUse() {
        if (this.inUse && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.inUse = false;
            this.listener.transportInUse(false);
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportIdle();
            }
        }
    }

    /* access modifiers changed from: private */
    public void startGoAway(int i, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, errorCode, new byte[0]);
            }
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                if (((Integer) next.getKey()).intValue() > i) {
                    it.remove();
                    ((OkHttpClientStream) next.getValue()).transportReportStatus(status, false, new Metadata());
                }
            }
            Iterator it2 = this.pendingStreams.iterator();
            while (it2.hasNext()) {
                ((OkHttpClientStream) it2.next()).transportReportStatus(status, true, new Metadata());
            }
            this.pendingStreams.clear();
            maybeClearInUse();
            stopIfNecessary();
        }
    }

    static Status toGrpcStatus(ErrorCode errorCode) {
        Status status = ERROR_CODE_TO_STATUS.get(errorCode);
        if (status != null) {
            return status;
        }
        Status status2 = Status.UNKNOWN;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown http2 error code: ");
        outline13.append(errorCode.httpCode);
        return status2.withDescription(outline13.toString());
    }

    /* access modifiers changed from: package-private */
    public ClientFrameHandler getHandler() {
        return this.clientFrameHandler;
    }

    public String getLogId() {
        return GrpcUtil.getLogId(this);
    }

    /* access modifiers changed from: package-private */
    public String getOverridenHost() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getHost() != null) {
            return authorityToUri.getHost();
        }
        return this.defaultAuthority;
    }

    /* access modifiers changed from: package-private */
    public int getOverridenPort() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getPort() != -1) {
            return authorityToUri.getPort();
        }
        return this.address.getPort();
    }

    /* access modifiers changed from: package-private */
    public int getPendingStreamSize() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        if (r1 == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        r7.frameWriter.ping(false, (int) (r3 >>> 32), (int) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        r5.addCallback(r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ping(p009io.grpc.internal.ClientTransport.PingCallback r8, java.util.concurrent.Executor r9) {
        /*
            r7 = this;
            io.grpc.okhttp.AsyncFrameWriter r0 = r7.frameWriter
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0008
            r0 = r1
            goto L_0x0009
        L_0x0008:
            r0 = r2
        L_0x0009:
            com.google.common.base.MoreObjects.checkState(r0)
            r3 = 0
            java.lang.Object r0 = r7.lock
            monitor-enter(r0)
            boolean r5 = r7.stopped     // Catch:{ all -> 0x004c }
            if (r5 == 0) goto L_0x001e
            java.lang.Throwable r7 = r7.getPingFailure()     // Catch:{ all -> 0x004c }
            p009io.grpc.internal.Http2Ping.notifyFailed(r8, r9, r7)     // Catch:{ all -> 0x004c }
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            return
        L_0x001e:
            io.grpc.internal.Http2Ping r5 = r7.ping     // Catch:{ all -> 0x004c }
            if (r5 == 0) goto L_0x0027
            io.grpc.internal.Http2Ping r1 = r7.ping     // Catch:{ all -> 0x004c }
            r5 = r1
            r1 = r2
            goto L_0x003a
        L_0x0027:
            java.util.Random r3 = r7.random     // Catch:{ all -> 0x004c }
            long r3 = r3.nextLong()     // Catch:{ all -> 0x004c }
            io.grpc.internal.Http2Ping r5 = new io.grpc.internal.Http2Ping     // Catch:{ all -> 0x004c }
            com.google.common.base.Ticker r6 = r7.ticker     // Catch:{ all -> 0x004c }
            com.google.common.base.Stopwatch r6 = com.google.common.base.Stopwatch.createStarted(r6)     // Catch:{ all -> 0x004c }
            r5.<init>(r3, r6)     // Catch:{ all -> 0x004c }
            r7.ping = r5     // Catch:{ all -> 0x004c }
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x0048
            io.grpc.okhttp.AsyncFrameWriter r7 = r7.frameWriter
            r0 = 32
            long r0 = r3 >>> r0
            int r0 = (int) r0
            int r1 = (int) r3
            r7.ping(r2, r0, r1)
        L_0x0048:
            r5.addCallback(r8, r9)
            return
        L_0x004c:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.okhttp.OkHttpClientTransport.ping(io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void shutdown() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            io.grpc.Status r1 = r3.goAwayStatus     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0009:
            io.grpc.Status r1 = p009io.grpc.Status.UNAVAILABLE     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "Transport stopped"
            io.grpc.Status r1 = r1.withDescription(r2)     // Catch:{ all -> 0x0032 }
            r3.goAwayStatus = r1     // Catch:{ all -> 0x0032 }
            io.grpc.internal.ManagedClientTransport$Listener r1 = r3.listener     // Catch:{ all -> 0x0032 }
            io.grpc.Status r2 = r3.goAwayStatus     // Catch:{ all -> 0x0032 }
            r1.transportShutdown(r2)     // Catch:{ all -> 0x0032 }
            r3.stopIfNecessary()     // Catch:{ all -> 0x0032 }
            io.grpc.internal.KeepAliveManager r1 = r3.keepAliveManager     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0030
            io.grpc.internal.KeepAliveManager r1 = r3.keepAliveManager     // Catch:{ all -> 0x0032 }
            r1.onTransportShutdown()     // Catch:{ all -> 0x0032 }
            io.grpc.internal.SharedResourceHolder$Resource<java.util.concurrent.ScheduledExecutorService> r1 = p009io.grpc.internal.GrpcUtil.TIMER_SERVICE     // Catch:{ all -> 0x0032 }
            java.util.concurrent.ScheduledExecutorService r2 = r3.scheduler     // Catch:{ all -> 0x0032 }
            p009io.grpc.internal.SharedResourceHolder.release(r1, r2)     // Catch:{ all -> 0x0032 }
            r1 = 0
            r3.scheduler = r1     // Catch:{ all -> 0x0032 }
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.okhttp.OkHttpClientTransport.shutdown():void");
    }

    public void shutdownNow(Status status) {
        shutdown();
        synchronized (this.lock) {
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                it.remove();
                ((OkHttpClientStream) it.next().getValue()).transportReportStatus(status, false, new Metadata());
            }
            Iterator it2 = this.pendingStreams.iterator();
            while (it2.hasNext()) {
                ((OkHttpClientStream) it2.next()).transportReportStatus(status, true, new Metadata());
            }
            this.pendingStreams.clear();
            maybeClearInUse();
            stopIfNecessary();
        }
    }

    /* access modifiers changed from: package-private */
    public void stopIfNecessary() {
        if (this.goAwayStatus != null && this.streams.isEmpty() && this.pendingStreams.isEmpty() && !this.stopped) {
            this.stopped = true;
            Http2Ping http2Ping = this.ping;
            if (http2Ping != null) {
                http2Ping.failed(getPingFailure());
                this.ping = null;
            }
            if (!this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, ErrorCode.NO_ERROR, new byte[0]);
            }
            this.frameWriter.close();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getLogId());
        sb.append("(");
        return GeneratedOutlineSupport.outline11(sb, this.address, ")");
    }
}
