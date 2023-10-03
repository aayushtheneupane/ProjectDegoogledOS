package p000;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjg */
/* compiled from: PG */
final class gjg implements gkd {

    /* renamed from: a */
    private final /* synthetic */ gjn f11475a;

    public gjg(gjn gjn) {
        this.f11475a = gjn;
    }

    /* renamed from: a */
    public final void mo6734a(TextInputLayout textInputLayout) {
        AutoCompleteTextView a = this.f11475a.mo6758a(textInputLayout.f5254a);
        gjn gjn = this.f11475a;
        int i = gjn.f11495k.f5288j;
        if (i == 2) {
            a.setDropDownBackgroundDrawable(gjn.f11490g);
        } else if (i == 1) {
            a.setDropDownBackgroundDrawable(gjn.f11489f);
        }
        gjn gjn2 = this.f11475a;
        if (a.getKeyListener() == null) {
            TextInputLayout textInputLayout2 = gjn2.f11495k;
            int i2 = textInputLayout2.f5288j;
            if (i2 == 1 || i2 == 2) {
                ggu ggu = textInputLayout2.f5287i;
                int a2 = ggf.m10246a((View) a, (int) R.attr.colorControlHighlight);
                int[][] iArr = {new int[]{16842919}, new int[0]};
                if (i2 != 2) {
                    int i3 = gjn2.f11495k.f5289k;
                    C0340mj.m14694a((View) a, (Drawable) new RippleDrawable(new ColorStateList(iArr, new int[]{ggf.m10243a(a2, i3, 0.1f), i3}), ggu, ggu));
                } else {
                    int a3 = ggf.m10246a((View) a, (int) R.attr.colorSurface);
                    ggu ggu2 = new ggu(ggu.mo6630a());
                    int a4 = ggf.m10243a(a2, a3, 0.1f);
                    ggu2.mo6635a(new ColorStateList(iArr, new int[]{a4, 0}));
                    ggu2.setTint(a3);
                    ColorStateList colorStateList = new ColorStateList(iArr, new int[]{a4, a3});
                    ggu ggu3 = new ggu(ggu.mo6630a());
                    ggu3.setTint(-1);
                    C0340mj.m14694a((View) a, (Drawable) new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, ggu2, ggu3), ggu}));
                }
            } else {
                throw new IllegalStateException();
            }
        }
        gjn gjn3 = this.f11475a;
        a.setOnTouchListener(new gji(gjn3, a));
        a.setOnFocusChangeListener(new gjj(gjn3));
        a.setOnDismissListener(new gjk(gjn3));
        a.setThreshold(0);
        a.removeTextChangedListener(this.f11475a.f11484a);
        a.addTextChangedListener(this.f11475a.f11484a);
        textInputLayout.mo3682a((Drawable) null);
        textInputLayout.mo3685a(this.f11475a.f11485b);
        textInputLayout.mo3695c(true);
    }
}
