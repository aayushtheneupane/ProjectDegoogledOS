package p000;

import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dum */
/* compiled from: PG */
public final class dum extends dvb implements hbf {

    /* renamed from: a */
    private dup f7411a;

    public dum(hbl hbl) {
        super(hbl);
        m6704c();
    }

    /* renamed from: c */
    private final void m6704c() {
        if (this.f7411a == null) {
            try {
                this.f7411a = ((duq) mo2453b()).mo2525w();
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
        m6704c();
    }

    /* renamed from: a */
    public final dup mo2635n() {
        dup dup = this.f7411a;
        if (dup != null) {
            return dup;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
