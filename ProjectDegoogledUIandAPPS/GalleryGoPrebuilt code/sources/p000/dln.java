package p000;

import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dln */
/* compiled from: PG */
public final class dln extends dol implements hbf {

    /* renamed from: a */
    private dlw f6792a;

    public dln(hbl hbl) {
        super(hbl);
        m6306c();
    }

    /* renamed from: c */
    private final void m6306c() {
        if (this.f6792a == null) {
            try {
                this.f6792a = ((dlx) mo2453b()).mo2518p();
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
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        m6306c();
        dln dln = this.f6792a.f6805a;
        dln.dispatchApplyWindowInsets(dln.getRootWindowInsets());
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m6306c();
    }

    /* renamed from: a */
    public final dlw mo2635n() {
        dlw dlw = this.f6792a;
        if (dlw != null) {
            return dlw;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
