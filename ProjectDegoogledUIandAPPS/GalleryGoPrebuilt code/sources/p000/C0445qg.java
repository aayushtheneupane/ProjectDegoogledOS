package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* renamed from: qg */
/* compiled from: PG */
public final class C0445qg extends ContextWrapper {

    /* renamed from: a */
    public int f15605a;

    /* renamed from: b */
    private Resources.Theme f15606b;

    /* renamed from: c */
    private LayoutInflater f15607c;

    /* renamed from: d */
    private Resources f15608d;

    public C0445qg() {
        super((Context) null);
    }

    public C0445qg(Context context, int i) {
        super(context);
        this.f15605a = i;
    }

    public C0445qg(Context context, Resources.Theme theme) {
        super(context);
        this.f15606b = theme;
    }

    public final AssetManager getAssets() {
        return m15132a().getAssets();
    }

    public final Resources getResources() {
        return m15132a();
    }

    /* renamed from: a */
    private final Resources m15132a() {
        if (this.f15608d == null) {
            this.f15608d = super.getResources();
        }
        return this.f15608d;
    }

    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f15607c == null) {
            this.f15607c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f15607c;
    }

    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f15606b;
        if (theme != null) {
            return theme;
        }
        if (this.f15605a == 0) {
            this.f15605a = 2131952155;
        }
        m15133b();
        return this.f15606b;
    }

    /* renamed from: b */
    private final void m15133b() {
        if (this.f15606b == null) {
            this.f15606b = m15132a().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f15606b.setTo(theme);
            }
        }
        this.f15606b.applyStyle(this.f15605a, true);
    }

    public final void setTheme(int i) {
        if (this.f15605a != i) {
            this.f15605a = i;
            m15133b();
        }
    }
}
