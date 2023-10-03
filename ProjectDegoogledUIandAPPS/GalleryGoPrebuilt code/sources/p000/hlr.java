package p000;

import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: hlr */
/* compiled from: PG */
public final /* synthetic */ class hlr implements TextView.OnEditorActionListener {

    /* renamed from: a */
    private final hlz f12993a;

    /* renamed from: b */
    private final TextView.OnEditorActionListener f12994b;

    /* renamed from: c */
    private final String f12995c;

    public hlr(hlz hlz, TextView.OnEditorActionListener onEditorActionListener, String str) {
        this.f12993a = hlz;
        this.f12994b = onEditorActionListener;
        this.f12995c = str;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        hlz hlz = this.f12993a;
        TextView.OnEditorActionListener onEditorActionListener = this.f12994b;
        String str = this.f12995c;
        if (hnb.m11774a(hnf.f13084a)) {
            return onEditorActionListener.onEditorAction(textView, i, keyEvent);
        }
        hlp a = hlz.mo7577a(str);
        try {
            boolean onEditorAction = onEditorActionListener.onEditorAction(textView, i, keyEvent);
            if (a == null) {
                return onEditorAction;
            }
            a.close();
            return onEditorAction;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
