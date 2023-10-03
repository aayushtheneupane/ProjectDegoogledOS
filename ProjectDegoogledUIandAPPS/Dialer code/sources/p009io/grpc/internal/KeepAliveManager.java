package p009io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import p009io.grpc.Status;
import p009io.grpc.internal.ClientTransport;

/* renamed from: io.grpc.internal.KeepAliveManager */
public class KeepAliveManager {
    private static final long MIN_KEEPALIVE_DELAY_NANOS = TimeUnit.MINUTES.toNanos(1);
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker((C09441) null);
    /* access modifiers changed from: private */
    public long keepAliveDelayInNanos;
    /* access modifiers changed from: private */
    public long keepAliveTimeoutInNanos;
    /* access modifiers changed from: private */
    public long nextKeepaliveTime;
    /* access modifiers changed from: private */
    public final KeepAlivePingCallback pingCallback = new KeepAlivePingCallback((C09441) null);
    /* access modifiers changed from: private */
    public ScheduledFuture<?> pingFuture;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public final Runnable sendPing = new Runnable() {
        public void run() {
            boolean z;
            synchronized (KeepAliveManager.this) {
                if (KeepAliveManager.this.state == State.PING_SCHEDULED) {
                    z = true;
                    State unused = KeepAliveManager.this.state = State.PING_SENT;
                    ScheduledFuture unused2 = KeepAliveManager.this.shutdownFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.shutdown, KeepAliveManager.this.keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
                } else {
                    if (KeepAliveManager.this.state == State.PING_DELAYED) {
                        ScheduledFuture unused3 = KeepAliveManager.this.pingFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.sendPing, KeepAliveManager.this.nextKeepaliveTime - KeepAliveManager.this.ticker.read(), TimeUnit.NANOSECONDS);
                        State unused4 = KeepAliveManager.this.state = State.PING_SCHEDULED;
                    }
                    z = false;
                }
            }
            if (z) {
                KeepAliveManager.this.transport.ping(KeepAliveManager.this.pingCallback, MoreExecutors.directExecutor());
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable shutdown = new Runnable() {
        public void run() {
            boolean z;
            synchronized (KeepAliveManager.this) {
                if (KeepAliveManager.this.state != State.DISCONNECTED) {
                    State unused = KeepAliveManager.this.state = State.DISCONNECTED;
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                KeepAliveManager.this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
            }
        }
    };
    /* access modifiers changed from: private */
    public ScheduledFuture<?> shutdownFuture;
    /* access modifiers changed from: private */
    public State state = State.IDLE;
    /* access modifiers changed from: private */
    public final Ticker ticker;
    /* access modifiers changed from: private */
    public final ManagedClientTransport transport;

    /* renamed from: io.grpc.internal.KeepAliveManager$KeepAlivePingCallback */
    private class KeepAlivePingCallback implements ClientTransport.PingCallback {
        /* synthetic */ KeepAlivePingCallback(C09441 r2) {
        }

        public void onFailure(Throwable th) {
            synchronized (KeepAliveManager.this) {
                KeepAliveManager.this.shutdownFuture.cancel(false);
            }
            KeepAliveManager.this.shutdown.run();
        }

        public void onSuccess(long j) {
            synchronized (KeepAliveManager.this) {
                KeepAliveManager.this.shutdownFuture.cancel(false);
                long unused = KeepAliveManager.this.nextKeepaliveTime = KeepAliveManager.this.ticker.read() + KeepAliveManager.this.keepAliveDelayInNanos;
                if (KeepAliveManager.this.state == State.PING_SENT) {
                    ScheduledFuture unused2 = KeepAliveManager.this.pingFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.sendPing, KeepAliveManager.this.keepAliveDelayInNanos, TimeUnit.NANOSECONDS);
                    State unused3 = KeepAliveManager.this.state = State.PING_SCHEDULED;
                }
                if (KeepAliveManager.this.state == State.IDLE_AND_PING_SENT) {
                    State unused4 = KeepAliveManager.this.state = State.IDLE;
                }
            }
        }
    }

    /* renamed from: io.grpc.internal.KeepAliveManager$State */
    private enum State {
        IDLE,
        PING_SCHEDULED,
        PING_DELAYED,
        PING_SENT,
        IDLE_AND_PING_SENT,
        DISCONNECTED
    }

    /* renamed from: io.grpc.internal.KeepAliveManager$SystemTicker */
    private static class SystemTicker extends Ticker {
        private SystemTicker() {
        }

        public long read() {
            return System.nanoTime();
        }

        /* synthetic */ SystemTicker(C09441 r1) {
        }
    }

    /* renamed from: io.grpc.internal.KeepAliveManager$Ticker */
    static abstract class Ticker {
        Ticker() {
        }

        public abstract long read();
    }

    KeepAliveManager(ManagedClientTransport managedClientTransport, ScheduledExecutorService scheduledExecutorService, Ticker ticker2, long j, long j2) {
        MoreObjects.checkNotNull(managedClientTransport, "transport");
        this.transport = managedClientTransport;
        MoreObjects.checkNotNull(scheduledExecutorService, "scheduler");
        this.scheduler = scheduledExecutorService;
        MoreObjects.checkNotNull(ticker2, "ticker");
        this.ticker = ticker2;
        this.keepAliveDelayInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        SystemTicker systemTicker = (SystemTicker) ticker2;
        this.nextKeepaliveTime = System.nanoTime() + j;
    }

    public synchronized void onDataReceived() {
        this.nextKeepaliveTime = this.ticker.read() + this.keepAliveDelayInNanos;
        if (this.state == State.PING_SCHEDULED) {
            this.state = State.PING_DELAYED;
        }
    }

    public synchronized void onTransportIdle() {
        if (this.state == State.PING_SCHEDULED || this.state == State.PING_DELAYED) {
            this.state = State.IDLE;
        }
        if (this.state == State.PING_SENT) {
            this.state = State.IDLE_AND_PING_SENT;
        }
    }

    public synchronized void onTransportShutdown() {
        if (this.state != State.DISCONNECTED) {
            this.state = State.DISCONNECTED;
            if (this.shutdownFuture != null) {
                this.shutdownFuture.cancel(false);
            }
            if (this.pingFuture != null) {
                this.pingFuture.cancel(false);
            }
        }
    }
}
