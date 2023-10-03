package p000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gja */
/* compiled from: PG */
public final class gja extends gjo {

    /* renamed from: a */
    public final TextWatcher f11464a = new gis(this);

    /* renamed from: b */
    private final gkd f11465b = new giu(this);

    /* renamed from: c */
    private AnimatorSet f11466c;

    /* renamed from: d */
    private ValueAnimator f11467d;

    public gja(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* renamed from: b */
    public final void mo6745b(boolean z) {
        boolean d = this.f11495k.mo3697d();
        if (z) {
            this.f11467d.cancel();
            this.f11466c.start();
            if (d) {
                this.f11466c.end();
                return;
            }
            return;
        }
        this.f11466c.cancel();
        this.f11467d.start();
        if (!d) {
            this.f11467d.end();
        }
    }

    /* renamed from: a */
    private final ValueAnimator m10388a(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(gci.f10936a);
        ofFloat.setDuration(100);
        ofFloat.addUpdateListener(new giy(this));
        return ofFloat;
    }

    /* renamed from: a */
    public static boolean m10389a(Editable editable) {
        return editable.length() > 0;
    }

    /* renamed from: a */
    public final void mo6743a() {
        this.f11495k.mo3691b(C0436py.m15105b(this.f11496l, R.drawable.mtrl_ic_cancel));
        TextInputLayout textInputLayout = this.f11495k;
        textInputLayout.mo3692b(textInputLayout.getResources().getText(R.string.clear_text_end_icon_content_description));
        this.f11495k.mo3683a((View.OnClickListener) new giv(this));
        this.f11495k.mo3686a(this.f11465b);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.8f, 1.0f});
        ofFloat.setInterpolator(gci.f10939d);
        ofFloat.setDuration(150);
        ofFloat.addUpdateListener(new giz(this));
        ValueAnimator a = m10388a(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f11466c = animatorSet;
        animatorSet.playTogether(new Animator[]{ofFloat, a});
        this.f11466c.addListener(new giw(this));
        ValueAnimator a2 = m10388a(1.0f, 0.0f);
        this.f11467d = a2;
        a2.addListener(new gix(this));
    }

    /* renamed from: a */
    public final void mo6744a(boolean z) {
        if (this.f11495k.f5285g != null) {
            mo6745b(z);
        }
    }
}
