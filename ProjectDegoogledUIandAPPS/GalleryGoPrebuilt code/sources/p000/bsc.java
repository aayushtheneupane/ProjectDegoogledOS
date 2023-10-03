package p000;

import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: bsc */
/* compiled from: PG */
public final class bsc extends bss implements hbf {

    /* renamed from: a */
    private bse f3478a;

    public bsc(hbl hbl) {
        super(hbl);
        m3512c();
    }

    /* renamed from: c */
    private final void m3512c() {
        if (this.f3478a == null) {
            try {
                this.f3478a = ((bsf) mo2453b()).mo2508f();
                Context context = getContext();
                while ((context instanceof ContextWrapper) && !(context instanceof ioe) && !(context instanceof fts) && !(context instanceof hcf)) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (!(context instanceof hbs)) {
                    String cls = getClass().toString();
                    StringBuilder sb = new StringBuilder(String.valueOf(cls).length() + 57);
                    sb.append("TikTok View ");
                    sb.append(cls);
                    sb.append(", cannot be attached to a non-TikTok Fragment");
                    throw new IllegalStateException(sb.toString());
                }
            } catch (ClassCastException e) {
                throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m3512c();
    }

    /* renamed from: a */
    public final bse mo2635n() {
        bse bse = this.f3478a;
        if (bse != null) {
            return bse;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
