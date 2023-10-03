package p000;

import android.os.MessageQueue;

/* renamed from: hmi */
/* compiled from: PG */
final /* synthetic */ class hmi implements MessageQueue.IdleHandler {

    /* renamed from: a */
    private final hlp f13031a;

    /* renamed from: b */
    private final MessageQueue.IdleHandler f13032b;

    public hmi(hlp hlp, MessageQueue.IdleHandler idleHandler) {
        this.f13031a = hlp;
        this.f13032b = idleHandler;
    }

    public final boolean queueIdle() {
        hlp hlp = this.f13031a;
        MessageQueue.IdleHandler idleHandler = this.f13032b;
        int i = hmq.f13047a;
        hlp b = hnb.m11776b(hlp);
        try {
            return idleHandler.queueIdle();
        } finally {
            hnb.m11776b(b);
        }
    }
}
