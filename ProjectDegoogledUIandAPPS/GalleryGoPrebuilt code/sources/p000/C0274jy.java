package p000;

import android.os.Handler;
import android.os.Message;

/* renamed from: jy */
/* compiled from: PG */
final class C0274jy implements Handler.Callback {

    /* renamed from: a */
    private final /* synthetic */ C0280kd f15109a;

    public C0274jy(C0280kd kdVar) {
        this.f15109a = kdVar;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            C0280kd kdVar = this.f15109a;
            synchronized (kdVar.f15120a) {
                if (!kdVar.f15122c.hasMessages(1)) {
                    kdVar.f15121b.quit();
                    kdVar.f15121b = null;
                    kdVar.f15122c = null;
                }
            }
            return true;
        } else if (i != 1) {
            return true;
        } else {
            C0280kd kdVar2 = this.f15109a;
            ((Runnable) message.obj).run();
            synchronized (kdVar2.f15120a) {
                kdVar2.f15122c.removeMessages(0);
                Handler handler = kdVar2.f15122c;
                handler.sendMessageDelayed(handler.obtainMessage(0), (long) kdVar2.f15123d);
            }
            return true;
        }
    }
}
