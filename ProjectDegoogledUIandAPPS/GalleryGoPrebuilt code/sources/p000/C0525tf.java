package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.google.android.apps.photosgo.R;

/* renamed from: tf */
/* compiled from: PG */
public class C0525tf extends CheckBox {

    /* renamed from: a */
    private final C0527th f15921a;

    /* renamed from: b */
    private final C0523td f15922b;

    /* renamed from: c */
    private final C0557uk f15923c;

    public C0525tf(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (byte[]) null);
    }

    public C0525tf(Context context, AttributeSet attributeSet, byte[] bArr) {
        super(C0680yz.m16188a(context), attributeSet, R.attr.checkboxStyle);
        C0527th thVar = new C0527th(this);
        this.f15921a = thVar;
        thVar.mo10130a(attributeSet, R.attr.checkboxStyle);
        C0523td tdVar = new C0523td(this);
        this.f15922b = tdVar;
        tdVar.mo10104a(attributeSet, R.attr.checkboxStyle);
        C0557uk ukVar = new C0557uk(this);
        this.f15923c = ukVar;
        ukVar.mo10246a(attributeSet, (int) R.attr.checkboxStyle);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15922b;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0557uk ukVar = this.f15923c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        if (this.f15921a != null) {
            int i = Build.VERSION.SDK_INT;
        }
        return compoundPaddingLeft;
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15922b;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15922b;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setButtonDrawable(int i) {
        setButtonDrawable(C0436py.m15105b(getContext(), i));
    }

    public final void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0527th thVar = this.f15921a;
        if (thVar != null) {
            thVar.mo10129a();
        }
    }
}
