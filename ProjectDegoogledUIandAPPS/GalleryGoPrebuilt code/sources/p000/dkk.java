package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.ViewGroup;

/* renamed from: dkk */
/* compiled from: PG */
public final class dkk extends dkq implements hbf {

    /* renamed from: a */
    private dkm f6727a;

    public dkk(hbl hbl) {
        super(hbl);
        m6246d();
    }

    /* renamed from: d */
    private final void m6246d() {
        if (this.f6727a == null) {
            try {
                this.f6727a = ((dkn) mo2453b()).mo2517o();
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
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m802a();
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m6246d();
    }

    /* renamed from: c */
    public final dkm mo2635n() {
        dkm dkm = this.f6727a;
        if (dkm != null) {
            return dkm;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
