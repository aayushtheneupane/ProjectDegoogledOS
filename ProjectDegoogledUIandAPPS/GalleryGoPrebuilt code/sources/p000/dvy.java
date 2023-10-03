package p000;

import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dvy */
/* compiled from: PG */
public final class dvy extends dya implements hbf {

    /* renamed from: a */
    private dwa f7466a;

    public dvy(hbl hbl) {
        super(hbl);
        m6776c();
    }

    /* renamed from: c */
    private final void m6776c() {
        if (this.f7466a == null) {
            try {
                this.f7466a = ((dwb) mo2453b()).mo2528z();
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
        m6776c();
    }

    /* renamed from: a */
    public final dwa mo2635n() {
        dwa dwa = this.f7466a;
        if (dwa != null) {
            return dwa;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
