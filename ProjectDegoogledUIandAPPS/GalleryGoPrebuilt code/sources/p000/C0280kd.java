package p000;

import android.os.Handler;
import android.os.HandlerThread;

/* renamed from: kd */
/* compiled from: PG */
public final class C0280kd {

    /* renamed from: a */
    public final Object f15120a = new Object();

    /* renamed from: b */
    public HandlerThread f15121b;

    /* renamed from: c */
    public Handler f15122c;

    /* renamed from: d */
    public final int f15123d;

    /* renamed from: e */
    private int f15124e;

    /* renamed from: f */
    private final Handler.Callback f15125f = new C0274jy(this);

    /* renamed from: g */
    private final int f15126g;

    /* renamed from: h */
    private final String f15127h;

    public C0280kd(String str) {
        this.f15127h = str;
        this.f15126g = 10;
        this.f15123d = 10000;
        this.f15124e = 0;
    }

    /* renamed from: a */
    public final void mo9182a(Runnable runnable) {
        synchronized (this.f15120a) {
            if (this.f15121b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f15127h, this.f15126g);
                this.f15121b = handlerThread;
                handlerThread.start();
                this.f15122c = new Handler(this.f15121b.getLooper(), this.f15125f);
                this.f15124e++;
            }
            this.f15122c.removeMessages(0);
            Handler handler = this.f15122c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }
}
