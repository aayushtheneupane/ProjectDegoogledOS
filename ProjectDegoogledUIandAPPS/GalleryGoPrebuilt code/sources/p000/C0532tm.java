package p000;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: tm */
/* compiled from: PG */
public final class C0532tm {

    /* renamed from: a */
    private final ImageView f15942a;

    public C0532tm(ImageView imageView) {
        this.f15942a = imageView;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10154b() {
        Drawable drawable = this.f15942a.getDrawable();
        if (drawable != null) {
            C0579vf.m15605a();
        }
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10153a() {
        Drawable background = this.f15942a.getBackground();
        int i = Build.VERSION.SDK_INT;
        return !(background instanceof RippleDrawable);
    }

    /* renamed from: a */
    public final void mo10152a(AttributeSet attributeSet, int i) {
        int f;
        C0684zc a = C0684zc.m16192a(this.f15942a.getContext(), attributeSet, C0435px.f15578f, i, 0);
        try {
            Drawable drawable = this.f15942a.getDrawable();
            if (!(drawable != null || (f = a.mo10734f(1, -1)) == -1 || (drawable = C0436py.m15105b(this.f15942a.getContext(), f)) == null)) {
                this.f15942a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                C0579vf.m15605a();
            }
            if (a.mo10735f(2)) {
                ImageView imageView = this.f15942a;
                ColorStateList e = a.mo10733e(2);
                int i2 = Build.VERSION.SDK_INT;
                imageView.setImageTintList(e);
                int i3 = Build.VERSION.SDK_INT;
            }
            if (a.mo10735f(3)) {
                ImageView imageView2 = this.f15942a;
                PorterDuff.Mode a2 = C0579vf.m15603a(a.mo10722a(3, -1), (PorterDuff.Mode) null);
                int i4 = Build.VERSION.SDK_INT;
                imageView2.setImageTintMode(a2);
                int i5 = Build.VERSION.SDK_INT;
            }
        } finally {
            a.mo10724a();
        }
    }

    /* renamed from: a */
    public final void mo10151a(int i) {
        if (i != 0) {
            Drawable b = C0436py.m15105b(this.f15942a.getContext(), i);
            if (b != null) {
                C0579vf.m15605a();
            }
            this.f15942a.setImageDrawable(b);
        } else {
            this.f15942a.setImageDrawable((Drawable) null);
        }
        mo10154b();
    }
}
