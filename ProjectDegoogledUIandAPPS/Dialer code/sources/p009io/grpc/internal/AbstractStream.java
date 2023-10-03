package p009io.grpc.internal;

import com.google.common.base.MoreObjects;
import p009io.grpc.internal.MessageDeframer;
import p009io.grpc.internal.MessageFramer;

/* renamed from: io.grpc.internal.AbstractStream */
public abstract class AbstractStream<IdT> implements Stream {
    private boolean allocated;
    private final MessageDeframer deframer;
    private final MessageFramer framer;
    private Phase inboundPhase;
    private int numSentBytesQueued;
    private final Object onReadyLock = new Object();
    private int onReadyThreshold = 32768;
    private Phase outboundPhase;

    /* renamed from: io.grpc.internal.AbstractStream$DeframerListener */
    class DeframerListener implements MessageDeframer.Listener {
    }

    /* renamed from: io.grpc.internal.AbstractStream$FramerSink */
    class FramerSink implements MessageFramer.Sink {
    }

    /* renamed from: io.grpc.internal.AbstractStream$Phase */
    protected enum Phase {
        HEADERS,
        MESSAGE,
        STATUS
    }

    AbstractStream(MessageFramer messageFramer, MessageDeframer messageDeframer) {
        Phase phase = Phase.HEADERS;
        this.inboundPhase = phase;
        this.outboundPhase = phase;
        this.framer = messageFramer;
        this.deframer = messageDeframer;
    }

    /* access modifiers changed from: protected */
    public final void closeDeframer() {
        this.deframer.close();
    }

    /* renamed from: id */
    public abstract IdT mo12018id();

    /* access modifiers changed from: package-private */
    public final Phase inboundPhase() {
        return this.inboundPhase;
    }

    public boolean isClosed() {
        return inboundPhase() == Phase.STATUS && outboundPhase() == Phase.STATUS;
    }

    /* access modifiers changed from: protected */
    public final boolean isDeframerStalled() {
        return this.deframer.isStalled();
    }

    public boolean isReady() {
        boolean z = false;
        if (listener() == null || outboundPhase() == Phase.STATUS) {
            return false;
        }
        synchronized (this.onReadyLock) {
            if (this.allocated && this.numSentBytesQueued < this.onReadyThreshold) {
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public abstract StreamListener listener();

    /* access modifiers changed from: package-private */
    public final void notifyIfReady() {
        boolean isReady;
        synchronized (this.onReadyLock) {
            isReady = isReady();
        }
        if (isReady) {
            listener().onReady();
        }
    }

    /* access modifiers changed from: package-private */
    public final Phase outboundPhase() {
        return this.outboundPhase;
    }

    public String toString() {
        return toStringHelper().toString();
    }

    /* access modifiers changed from: protected */
    public MoreObjects.ToStringHelper toStringHelper() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.add("id", mo12018id());
        stringHelper.add("inboundPhase", (Object) inboundPhase().name());
        stringHelper.add("outboundPhase", (Object) outboundPhase().name());
        return stringHelper;
    }

    /* access modifiers changed from: package-private */
    public Phase verifyNextPhase(Phase phase, Phase phase2) {
        if (phase2.ordinal() >= phase.ordinal()) {
            return phase2;
        }
        throw new IllegalStateException(String.format("Cannot transition phase from %s to %s", new Object[]{phase, phase2}));
    }

    /* access modifiers changed from: package-private */
    public final Phase inboundPhase(Phase phase) {
        Phase phase2 = this.inboundPhase;
        this.inboundPhase = verifyNextPhase(phase2, phase);
        return phase2;
    }
}
