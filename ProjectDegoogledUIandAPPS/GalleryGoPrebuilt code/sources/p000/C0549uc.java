package p000;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;
import com.google.android.apps.photosgo.R;

/* renamed from: uc */
/* compiled from: PG */
final class C0549uc extends C0616wp implements C0552uf {

    /* renamed from: a */
    public CharSequence f15975a;

    /* renamed from: b */
    public ListAdapter f15976b;

    /* renamed from: c */
    public final Rect f15977c = new Rect();

    /* renamed from: d */
    public final /* synthetic */ C0553ug f15978d;

    /* renamed from: r */
    private int f15979r;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0549uc(C0553ug ugVar, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle);
        this.f15978d = ugVar;
        this.f16254l = ugVar;
        mo10509k();
        this.f16255m = new C0545tz(this);
    }

    /* renamed from: a */
    public final CharSequence mo10180a() {
        return this.f15975a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final void mo10210g() {
        int i;
        Drawable b = mo10501b();
        int i2 = 0;
        if (b != null) {
            b.getPadding(this.f15978d.f15985d);
            i2 = !C0703zv.m16280a(this.f15978d) ? -this.f15978d.f15985d.left : this.f15978d.f15985d.right;
        } else {
            Rect rect = this.f15978d.f15985d;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = this.f15978d.getPaddingLeft();
        int paddingRight = this.f15978d.getPaddingRight();
        int width = this.f15978d.getWidth();
        C0553ug ugVar = this.f15978d;
        int i3 = ugVar.f15984c;
        if (i3 == -2) {
            int a = ugVar.mo10214a((SpinnerAdapter) this.f15976b, mo10501b());
            int i4 = (this.f15978d.getContext().getResources().getDisplayMetrics().widthPixels - this.f15978d.f15985d.left) - this.f15978d.f15985d.right;
            if (a > i4) {
                a = i4;
            }
            mo10504d(Math.max(a, (width - paddingLeft) - paddingRight));
        } else if (i3 == -1) {
            mo10504d((width - paddingLeft) - paddingRight);
        } else {
            mo10504d(i3);
        }
        if (C0703zv.m16280a(this.f15978d)) {
            i = i2 + (((width - paddingRight) - this.f16248f) - this.f15979r);
        } else {
            i = i2 + paddingLeft + this.f15979r;
        }
        this.f16249g = i;
    }

    /* renamed from: a */
    public final void mo10184a(ListAdapter listAdapter) {
        super.mo10184a(listAdapter);
        this.f15976b = listAdapter;
    }

    /* renamed from: c */
    public final void mo10189c(int i) {
        this.f15979r = i;
    }

    /* renamed from: a */
    public final void mo10185a(CharSequence charSequence) {
        this.f15975a = charSequence;
    }

    /* renamed from: a */
    public final void mo10182a(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        boolean e = mo9811e();
        mo10210g();
        mo10508j();
        super.mo9805ab();
        C0582vi viVar = this.f16247e;
        viVar.setChoiceMode(1);
        int i3 = Build.VERSION.SDK_INT;
        viVar.setTextDirection(i);
        viVar.setTextAlignment(i2);
        int selectedItemPosition = this.f15978d.getSelectedItemPosition();
        C0582vi viVar2 = this.f16247e;
        if (mo9811e() && viVar2 != null) {
            viVar2.f16091a = false;
            viVar2.setSelection(selectedItemPosition);
            if (viVar2.getChoiceMode() != 0) {
                viVar2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (!e && (viewTreeObserver = this.f15978d.getViewTreeObserver()) != null) {
            C0547ua uaVar = new C0547ua(this);
            viewTreeObserver.addOnGlobalLayoutListener(uaVar);
            mo10500a((PopupWindow.OnDismissListener) new C0548ub(this, uaVar));
        }
    }
}
