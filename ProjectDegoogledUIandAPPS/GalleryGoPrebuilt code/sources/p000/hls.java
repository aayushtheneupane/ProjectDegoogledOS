package p000;

import android.content.DialogInterface;
import android.view.KeyEvent;

/* renamed from: hls */
/* compiled from: PG */
public final /* synthetic */ class hls implements DialogInterface.OnKeyListener {

    /* renamed from: a */
    private final hlz f12996a;

    /* renamed from: b */
    private final String f12997b;

    /* renamed from: c */
    private final DialogInterface.OnKeyListener f12998c;

    public hls(hlz hlz, String str, DialogInterface.OnKeyListener onKeyListener) {
        this.f12996a = hlz;
        this.f12997b = str;
        this.f12998c = onKeyListener;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        hlz hlz = this.f12996a;
        String str = this.f12997b;
        DialogInterface.OnKeyListener onKeyListener = this.f12998c;
        hlp a = hlz.mo7577a(str);
        try {
            boolean onKey = onKeyListener.onKey(dialogInterface, i, keyEvent);
            if (a != null) {
                a.close();
            }
            return onKey;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
