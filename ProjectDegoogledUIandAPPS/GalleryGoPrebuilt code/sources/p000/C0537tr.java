package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.google.android.apps.photosgo.R;

/* renamed from: tr */
/* compiled from: PG */
public class C0537tr extends RadioButton {

    /* renamed from: a */
    private final C0527th f15951a;

    /* renamed from: b */
    private final C0523td f15952b;

    /* renamed from: c */
    private final C0557uk f15953c;

    public C0537tr(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (byte[]) null);
    }

    public C0537tr(Context context, AttributeSet attributeSet, byte[] bArr) {
        super(C0680yz.m16188a(context), attributeSet, R.attr.radioButtonStyle);
        C0527th thVar = new C0527th(this);
        this.f15951a = thVar;
        thVar.mo10130a(attributeSet, R.attr.radioButtonStyle);
        C0523td tdVar = new C0523td(this);
        this.f15952b = tdVar;
        tdVar.mo10104a(attributeSet, R.attr.radioButtonStyle);
        C0557uk ukVar = new C0557uk(this);
        this.f15953c = ukVar;
        ukVar.mo10246a(attributeSet, (int) R.attr.radioButtonStyle);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15952b;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0557uk ukVar = this.f15953c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        if (this.f15951a != null) {
            int i = Build.VERSION.SDK_INT;
        }
        return compoundPaddingLeft;
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15952b;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15952b;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setButtonDrawable(int i) {
        setButtonDrawable(C0436py.m15105b(getContext(), i));
    }

    public final void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0527th thVar = this.f15951a;
        if (thVar != null) {
            thVar.mo10129a();
        }
    }
}
