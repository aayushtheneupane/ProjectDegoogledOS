package p000;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: hlw */
/* compiled from: PG */
public final class hlw implements TextWatcher {

    /* renamed from: a */
    private final /* synthetic */ TextWatcher f13008a;

    /* renamed from: b */
    private final /* synthetic */ String f13009b;

    /* renamed from: c */
    private final /* synthetic */ hlz f13010c;

    public hlw(hlz hlz, TextWatcher textWatcher, String str) {
        this.f13010c = hlz;
        this.f13008a = textWatcher;
        this.f13009b = str;
    }

    public final void afterTextChanged(Editable editable) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13008a.afterTextChanged(editable);
            return;
        }
        hlp a = this.f13010c.mo7577a(this.f13009b);
        try {
            this.f13008a.afterTextChanged(editable);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        hlp a;
        if (!hnb.m11774a(hnf.f13084a) && (a = this.f13010c.mo7577a(this.f13009b)) != null) {
            a.close();
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        hlp a;
        if (!hnb.m11774a(hnf.f13084a) && (a = this.f13010c.mo7577a(this.f13009b)) != null) {
            a.close();
        }
    }
}
