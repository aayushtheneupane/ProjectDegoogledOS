package p000;

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.MessageQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

/* renamed from: hmq */
/* compiled from: PG */
public final class hmq {

    /* renamed from: a */
    public static /* synthetic */ int f13047a;

    /* renamed from: b */
    private static final Map f13048b = new HashMap();

    static {
        Math.abs((long) new Random().nextInt());
    }

    /* renamed from: a */
    public static hlp m11741a(Intent intent, hnf hnf) {
        hlp hlp;
        ife.m12898e((Object) hnf);
        try {
            if (!intent.hasExtra("tracing_intent_id")) {
                return null;
            }
            long longExtra = intent.getLongExtra("tracing_intent_id", -1);
            synchronized (f13048b) {
                hlp = (hlp) f13048b.remove(Long.valueOf(longExtra));
            }
            return hlp;
        } catch (BadParcelableException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Runnable m11747a(hlp hlp, Runnable runnable) {
        return new hmk(hlp, runnable);
    }

    /* renamed from: a */
    public static ice m11743a(ice ice) {
        ife.m12898e((Object) ice);
        return new hmn(hnb.m11775b(), ice);
    }

    /* renamed from: a */
    public static ico m11745a(ico ico) {
        return new hmj(hnb.m11775b(), ico);
    }

    /* renamed from: a */
    public static icf m11744a(icf icf) {
        return new hmp(hnb.m11775b(), icf);
    }

    /* renamed from: a */
    public static Callable m11749a(Callable callable) {
        return new hmm(hnb.m11775b(), callable);
    }

    /* renamed from: a */
    public static hpr m11742a(hpr hpr) {
        return new hmo(hnb.m11775b(), hpr);
    }

    /* renamed from: a */
    public static idw m11746a(idw idw) {
        return new hml(hnb.m11775b(), idw);
    }

    /* renamed from: a */
    public static MessageQueue.IdleHandler m11740a(MessageQueue.IdleHandler idleHandler) {
        return new hmi(hnb.m11775b(), idleHandler);
    }

    /* renamed from: a */
    public static Runnable m11748a(Runnable runnable) {
        return m11747a(hnb.m11775b(), runnable);
    }
}
