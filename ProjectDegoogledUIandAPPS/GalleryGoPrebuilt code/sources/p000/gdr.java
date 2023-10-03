package p000;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import com.google.android.material.button.MaterialButton;

/* renamed from: gdr */
/* compiled from: PG */
public final class gdr {

    /* renamed from: a */
    public final MaterialButton f11037a;

    /* renamed from: b */
    public gha f11038b;

    /* renamed from: c */
    public int f11039c;

    /* renamed from: d */
    public int f11040d;

    /* renamed from: e */
    public int f11041e;

    /* renamed from: f */
    public int f11042f;

    /* renamed from: g */
    public int f11043g;

    /* renamed from: h */
    public int f11044h;

    /* renamed from: i */
    public PorterDuff.Mode f11045i;

    /* renamed from: j */
    public ColorStateList f11046j;

    /* renamed from: k */
    public ColorStateList f11047k;

    /* renamed from: l */
    public ColorStateList f11048l;

    /* renamed from: m */
    public Drawable f11049m;

    /* renamed from: n */
    public boolean f11050n = false;

    /* renamed from: o */
    public boolean f11051o;

    /* renamed from: p */
    public LayerDrawable f11052p;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public gdr(MaterialButton materialButton, gha gha) {
        this.f11037a = materialButton;
        this.f11038b = gha;
    }

    /* renamed from: c */
    private final ghm m10135c() {
        LayerDrawable layerDrawable = this.f11052p;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        if (this.f11052p.getNumberOfLayers() > 2) {
            return (ghm) this.f11052p.getDrawable(2);
        }
        return (ghm) this.f11052p.getDrawable(1);
    }

    /* renamed from: a */
    public final ggu mo6481a() {
        return m10133a(false);
    }

    /* renamed from: a */
    private final ggu m10133a(boolean z) {
        LayerDrawable layerDrawable = this.f11052p;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (ggu) ((LayerDrawable) ((InsetDrawable) this.f11052p.getDrawable(0)).getDrawable()).getDrawable(z ^ true ? 1 : 0);
    }

    /* renamed from: b */
    private final ggu m10134b() {
        return m10133a(true);
    }

    /* renamed from: a */
    public final void mo6482a(gha gha) {
        this.f11038b = gha;
        if (mo6481a() != null) {
            mo6481a().mo3619a(gha);
        }
        if (m10134b() != null) {
            m10134b().mo3619a(gha);
        }
        if (m10135c() != null) {
            m10135c().mo3619a(gha);
        }
    }
}
