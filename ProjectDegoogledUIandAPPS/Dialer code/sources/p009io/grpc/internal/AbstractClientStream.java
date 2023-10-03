package p009io.grpc.internal;

import com.google.common.base.MoreObjects;
import java.util.logging.Logger;
import p009io.grpc.Metadata;
import p009io.grpc.Status;
import p009io.grpc.internal.AbstractStream;

/* renamed from: io.grpc.internal.AbstractClientStream */
public abstract class AbstractClientStream<IdT> extends AbstractStream<IdT> implements ClientStream {
    private static final Logger log = Logger.getLogger(AbstractClientStream.class.getName());
    private volatile boolean cancelled;
    private Runnable closeListenerTask;
    private ClientStreamListener listener;
    private boolean listenerClosed;
    private Status status;

    /* access modifiers changed from: private */
    public void closeListener(Status status2, Metadata metadata) {
        MoreObjects.checkState(this.listener != null, "stream not started");
        if (!this.listenerClosed) {
            this.listenerClosed = true;
            closeDeframer();
            this.listener.closed(status2, metadata);
        }
    }

    public boolean isClosed() {
        return super.isClosed() || this.listenerClosed;
    }

    public final boolean isReady() {
        return !this.cancelled && super.isReady();
    }

    /* access modifiers changed from: protected */
    public MoreObjects.ToStringHelper toStringHelper() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.add("id", mo12018id());
        stringHelper.add("inboundPhase", (Object) inboundPhase().name());
        stringHelper.add("outboundPhase", (Object) outboundPhase().name());
        Status status2 = this.status;
        if (status2 != null) {
            stringHelper.add("status", (Object) status2);
        }
        return stringHelper;
    }

    public void transportReportStatus(final Status status2, boolean z, final Metadata metadata) {
        MoreObjects.checkNotNull(status2, "newStatus");
        boolean z2 = this.closeListenerTask != null && !z;
        if (!this.listenerClosed && !z2) {
            inboundPhase(AbstractStream.Phase.STATUS);
            this.status = status2;
            this.closeListenerTask = null;
            boolean isDeframerStalled = isDeframerStalled();
            if (z || isDeframerStalled) {
                closeListener(status2, metadata);
            } else {
                this.closeListenerTask = new Runnable() {
                    public void run() {
                        AbstractClientStream.this.closeListener(status2, metadata);
                    }
                };
            }
        }
    }

    /* access modifiers changed from: protected */
    public final ClientStreamListener listener() {
        return this.listener;
    }
}
