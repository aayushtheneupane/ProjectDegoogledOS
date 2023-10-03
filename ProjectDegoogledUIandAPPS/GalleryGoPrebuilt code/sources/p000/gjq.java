package p000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

/* renamed from: gjq */
/* compiled from: PG */
public final class gjq {

    /* renamed from: a */
    public final Context f11503a;

    /* renamed from: b */
    public final TextInputLayout f11504b;

    /* renamed from: c */
    public Animator f11505c;

    /* renamed from: d */
    public int f11506d;

    /* renamed from: e */
    public int f11507e;

    /* renamed from: f */
    public CharSequence f11508f;

    /* renamed from: g */
    public boolean f11509g;

    /* renamed from: h */
    public TextView f11510h;

    /* renamed from: i */
    public CharSequence f11511i;

    /* renamed from: j */
    public int f11512j;

    /* renamed from: k */
    public ColorStateList f11513k;

    /* renamed from: l */
    public CharSequence f11514l;

    /* renamed from: m */
    public boolean f11515m;

    /* renamed from: n */
    public TextView f11516n;

    /* renamed from: o */
    public int f11517o;

    /* renamed from: p */
    public ColorStateList f11518p;

    /* renamed from: q */
    private LinearLayout f11519q;

    /* renamed from: r */
    private int f11520r;

    /* renamed from: s */
    private FrameLayout f11521s;

    /* renamed from: t */
    private int f11522t;

    /* renamed from: u */
    private final float f11523u;

    public gjq(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.f11503a = context;
        this.f11504b = textInputLayout;
        this.f11523u = (float) context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    /* renamed from: c */
    private final TextView m10413c(int i) {
        if (i == 1) {
            return this.f11510h;
        }
        if (i != 2) {
            return null;
        }
        return this.f11516n;
    }

    /* renamed from: d */
    private static final boolean m10414d(int i) {
        return i == 0 || i == 1;
    }

    /* renamed from: a */
    public final void mo6770a(TextView textView, int i) {
        if (this.f11519q == null && this.f11521s == null) {
            LinearLayout linearLayout = new LinearLayout(this.f11503a);
            this.f11519q = linearLayout;
            linearLayout.setOrientation(0);
            this.f11504b.addView(this.f11519q, -1, -2);
            FrameLayout frameLayout = new FrameLayout(this.f11503a);
            this.f11521s = frameLayout;
            this.f11519q.addView(frameLayout, -1, new FrameLayout.LayoutParams(-2, -2));
            this.f11519q.addView(new Space(this.f11503a), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.f11504b.f5254a != null) {
                mo6777c();
            }
        }
        if (m10414d(i)) {
            this.f11521s.setVisibility(0);
            this.f11521s.addView(textView);
            this.f11522t++;
        } else {
            this.f11519q.addView(textView, i);
        }
        this.f11519q.setVisibility(0);
        this.f11520r++;
    }

    /* renamed from: c */
    public final void mo6777c() {
        EditText editText;
        LinearLayout linearLayout = this.f11519q;
        if (linearLayout != null && (editText = this.f11504b.f5254a) != null) {
            C0340mj.m14690a(linearLayout, C0340mj.m14716g(editText), 0, C0340mj.m14717h(this.f11504b.f5254a), 0);
        }
    }

    /* renamed from: b */
    public final void mo6773b() {
        Animator animator = this.f11505c;
        if (animator != null) {
            animator.cancel();
        }
    }

    /* renamed from: a */
    private final void m10412a(List list, boolean z, TextView textView, int i, int i2, int i3) {
        float f;
        if (textView != null && z) {
            if (i == i3 || i == i2) {
                if (i3 != i) {
                    f = 0.0f;
                } else {
                    f = 1.0f;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, new float[]{f});
                ofFloat.setDuration(167);
                ofFloat.setInterpolator(gci.f10936a);
                list.add(ofFloat);
                if (i3 == i) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, new float[]{-this.f11523u, 0.0f});
                    ofFloat2.setDuration(217);
                    ofFloat2.setInterpolator(gci.f10939d);
                    list.add(ofFloat2);
                }
            }
        }
    }

    /* renamed from: d */
    public final boolean mo6778d() {
        return this.f11507e == 1 && this.f11510h != null && !TextUtils.isEmpty(this.f11508f);
    }

    /* renamed from: e */
    public final int mo6779e() {
        TextView textView = this.f11510h;
        if (textView == null) {
            return -1;
        }
        return textView.getCurrentTextColor();
    }

    /* renamed from: a */
    public final void mo6766a() {
        this.f11508f = null;
        mo6773b();
        if (this.f11506d == 1) {
            if (!this.f11515m || TextUtils.isEmpty(this.f11514l)) {
                this.f11507e = 0;
            } else {
                this.f11507e = 2;
            }
        }
        mo6768a(this.f11506d, this.f11507e, mo6772a(this.f11510h, (CharSequence) null));
    }

    /* renamed from: b */
    public final void mo6776b(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.f11519q != null) {
            if (!m10414d(i) || (frameLayout = this.f11521s) == null) {
                this.f11519q.removeView(textView);
            } else {
                int i2 = this.f11522t - 1;
                this.f11522t = i2;
                m10411a((ViewGroup) frameLayout, i2);
                this.f11521s.removeView(textView);
            }
            int i3 = this.f11520r - 1;
            this.f11520r = i3;
            m10411a((ViewGroup) this.f11519q, i3);
        }
    }

    /* renamed from: a */
    public final void mo6771a(CharSequence charSequence) {
        this.f11511i = charSequence;
        TextView textView = this.f11510h;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    /* renamed from: a */
    public final void mo6767a(int i) {
        this.f11512j = i;
        TextView textView = this.f11510h;
        if (textView != null) {
            this.f11504b.mo3684a(textView, i);
        }
    }

    /* renamed from: a */
    public final void mo6769a(ColorStateList colorStateList) {
        this.f11513k = colorStateList;
        TextView textView = this.f11510h;
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    /* renamed from: b */
    public final void mo6774b(int i) {
        this.f11517o = i;
        TextView textView = this.f11516n;
        if (textView != null) {
            dcm.m5901a(textView, i);
        }
    }

    /* renamed from: b */
    public final void mo6775b(ColorStateList colorStateList) {
        this.f11518p = colorStateList;
        TextView textView = this.f11516n;
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    /* renamed from: a */
    private static final void m10411a(ViewGroup viewGroup, int i) {
        if (i == 0) {
            viewGroup.setVisibility(8);
        }
    }

    /* renamed from: a */
    public final boolean mo6772a(TextView textView, CharSequence charSequence) {
        if (!C0340mj.m14732w(this.f11504b) || !this.f11504b.isEnabled() || (this.f11507e == this.f11506d && textView != null && TextUtils.equals(textView.getText(), charSequence))) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public final void mo6768a(int i, int i2, boolean z) {
        TextView c;
        TextView c2;
        int i3 = i;
        int i4 = i2;
        boolean z2 = z;
        if (i3 != i4) {
            if (z2) {
                AnimatorSet animatorSet = new AnimatorSet();
                this.f11505c = animatorSet;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = arrayList;
                int i5 = i;
                int i6 = i2;
                m10412a(arrayList2, this.f11515m, this.f11516n, 2, i5, i6);
                m10412a(arrayList2, this.f11509g, this.f11510h, 1, i5, i6);
                int size = arrayList.size();
                long j = 0;
                for (int i7 = 0; i7 < size; i7++) {
                    Animator animator = (Animator) arrayList.get(i7);
                    j = Math.max(j, animator.getStartDelay() + animator.getDuration());
                }
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 0});
                ofInt.setDuration(j);
                arrayList.add(0, ofInt);
                animatorSet.playTogether(arrayList);
                animatorSet.addListener(new gjp(this, i2, m10413c(i), i, m10413c(i4)));
                animatorSet.start();
            } else if (i3 != i4) {
                if (!(i4 == 0 || (c2 = m10413c(i4)) == null)) {
                    c2.setVisibility(0);
                    c2.setAlpha(1.0f);
                }
                if (!(i3 == 0 || (c = m10413c(i)) == null)) {
                    c.setVisibility(4);
                    if (i3 == 1) {
                        c.setText((CharSequence) null);
                    }
                }
                this.f11506d = i4;
            }
            this.f11504b.mo3690b();
            this.f11504b.mo3688a(z2);
            this.f11504b.mo3702e();
        }
    }
}
