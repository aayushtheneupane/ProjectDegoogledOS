package p000;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjn */
/* compiled from: PG */
public final class gjn extends gjo {

    /* renamed from: j */
    public static /* synthetic */ int f11483j;

    /* renamed from: a */
    public final TextWatcher f11484a = new gje(this);

    /* renamed from: b */
    public final gkc f11485b = new gjf(this, this.f11495k);

    /* renamed from: c */
    public boolean f11486c = false;

    /* renamed from: d */
    public boolean f11487d = false;

    /* renamed from: e */
    public long f11488e = RecyclerView.FOREVER_NS;

    /* renamed from: f */
    public StateListDrawable f11489f;

    /* renamed from: g */
    public ggu f11490g;

    /* renamed from: h */
    public AccessibilityManager f11491h;

    /* renamed from: i */
    public ValueAnimator f11492i;

    /* renamed from: n */
    private final gkd f11493n = new gjg(this);

    /* renamed from: o */
    private ValueAnimator f11494o;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public final boolean mo6760a(int i) {
        return i != 0;
    }

    /* renamed from: b */
    public final boolean mo6762b() {
        return true;
    }

    public gjn(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* renamed from: a */
    public final AutoCompleteTextView mo6758a(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    /* renamed from: a */
    private final ValueAnimator m10398a(int i, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(gci.f10936a);
        ofFloat.setDuration((long) i);
        ofFloat.addUpdateListener(new gjm(this));
        return ofFloat;
    }

    /* renamed from: a */
    private final ggu m10399a(float f, float f2, float f3, int i) {
        ggy a = gha.m10328a();
        a.mo6663c(f);
        a.mo6664d(f);
        a.mo6661a(f2);
        a.mo6662b(f2);
        gha a2 = a.mo6660a();
        ggu a3 = ggu.m10287a(this.f11496l, f3);
        a3.mo3619a(a2);
        ggt ggt = a3.f11293a;
        if (ggt.f11281h == null) {
            ggt.f11281h = new Rect();
        }
        a3.f11293a.f11281h.set(0, i, 0, i);
        a3.f11297e = a3.f11293a.f11281h;
        a3.invalidateSelf();
        return a3;
    }

    /* renamed from: a */
    public final void mo6743a() {
        float dimensionPixelOffset = (float) this.f11496l.getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = (float) this.f11496l.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f11496l.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        ggu a = m10399a(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        ggu a2 = m10399a(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.f11490g = a;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.f11489f = stateListDrawable;
        stateListDrawable.addState(new int[]{16842922}, a);
        this.f11489f.addState(new int[0], a2);
        this.f11495k.mo3691b(C0436py.m15105b(this.f11496l, R.drawable.mtrl_dropdown_arrow));
        TextInputLayout textInputLayout = this.f11495k;
        textInputLayout.mo3692b(textInputLayout.getResources().getText(R.string.exposed_dropdown_menu_content_description));
        this.f11495k.mo3683a((View.OnClickListener) new gjh(this));
        this.f11495k.mo3686a(this.f11493n);
        this.f11492i = m10398a(67, 0.0f, 1.0f);
        ValueAnimator a3 = m10398a(50, 1.0f, 0.0f);
        this.f11494o = a3;
        a3.addListener(new gjl(this));
        C0340mj.m14689a((View) this.f11497m, 2);
        this.f11491h = (AccessibilityManager) this.f11496l.getSystemService("accessibility");
    }

    /* renamed from: c */
    public final boolean mo6763c() {
        long currentTimeMillis = System.currentTimeMillis() - this.f11488e;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }

    /* renamed from: b */
    public final void mo6761b(boolean z) {
        if (this.f11487d != z) {
            this.f11487d = z;
            this.f11492i.cancel();
            this.f11494o.start();
        }
    }

    /* renamed from: a */
    public final void mo6759a(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView != null) {
            if (mo6763c()) {
                this.f11486c = false;
            }
            if (!this.f11486c) {
                mo6761b(!this.f11487d);
                if (this.f11487d) {
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.showDropDown();
                    return;
                }
                autoCompleteTextView.dismissDropDown();
                return;
            }
            this.f11486c = false;
        }
    }
}
