package android.support.p002v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: android.support.v7.view.menu.ActionMenuItemView */
/* compiled from: PG */
public class ActionMenuItemView extends C0558ul implements View.OnClickListener, C0487rv, C0513su {

    /* renamed from: b */
    public C0475rj f858b;

    /* renamed from: c */
    public C0471rf f859c;

    /* renamed from: d */
    public C0457qs f860d;

    /* renamed from: e */
    private CharSequence f861e;

    /* renamed from: f */
    private Drawable f862f;

    /* renamed from: g */
    private C0590vq f863g;

    /* renamed from: h */
    private boolean f864h;

    /* renamed from: i */
    private int f865i;

    /* renamed from: j */
    private int f866j;

    /* renamed from: k */
    private int f867k;

    public ActionMenuItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: a */
    public final C0475rj mo762a() {
        return this.f858b;
    }

    /* renamed from: b */
    public final boolean mo764b() {
        return true;
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.f864h = m844f();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15575c, i, 0);
        this.f865i = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        this.f867k = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f866j = -1;
        setSaveEnabled(false);
    }

    /* renamed from: c */
    public final boolean mo765c() {
        return !TextUtils.isEmpty(getText());
    }

    /* renamed from: a */
    public final void mo763a(C0475rj rjVar) {
        this.f858b = rjVar;
        Drawable icon = rjVar.getIcon();
        this.f862f = icon;
        int i = 0;
        if (icon != null) {
            int intrinsicWidth = icon.getIntrinsicWidth();
            int intrinsicHeight = icon.getIntrinsicHeight();
            int i2 = this.f867k;
            if (intrinsicWidth > i2) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i2) / ((float) intrinsicWidth)));
                intrinsicWidth = i2;
            }
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i2) / ((float) intrinsicHeight)));
            } else {
                i2 = intrinsicHeight;
            }
            icon.setBounds(0, 0, intrinsicWidth, i2);
        }
        setCompoundDrawables(icon, (Drawable) null, (Drawable) null, (Drawable) null);
        m845g();
        this.f861e = rjVar.mo9885a((C0487rv) this);
        m845g();
        setId(rjVar.f15780a);
        if (!rjVar.isVisible()) {
            i = 8;
        }
        setVisibility(i);
        setEnabled(rjVar.isEnabled());
        if (rjVar.hasSubMenu() && this.f863g == null) {
            this.f863g = new C0456qr(this);
        }
    }

    /* renamed from: e */
    public final boolean mo767e() {
        return mo765c();
    }

    /* renamed from: d */
    public final boolean mo766d() {
        return mo765c() && this.f858b.getIcon() == null;
    }

    public final void onClick(View view) {
        C0471rf rfVar = this.f859c;
        if (rfVar != null) {
            rfVar.mo775a(this.f858b);
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f864h = m844f();
        m845g();
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        boolean c = mo765c();
        if (c && (i4 = this.f866j) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            i3 = Math.min(size, this.f865i);
        } else {
            i3 = this.f865i;
        }
        if (mode != 1073741824 && this.f865i > 0 && measuredWidth < i3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        }
        if (!c && this.f862f != null) {
            super.setPadding((getMeasuredWidth() - this.f862f.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable) null);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        C0590vq vqVar;
        if (!this.f858b.hasSubMenu() || (vqVar = this.f863g) == null || !vqVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        this.f866j = i;
        super.setPadding(i, i2, i3, i4);
    }

    /* renamed from: f */
    private final boolean m844f() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        return i >= 480 || configuration.orientation == 2;
    }

    /* renamed from: g */
    private final void m845g() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f861e);
        if (this.f862f != null && ((this.f858b.f15793n & 4) != 4 || !this.f864h)) {
            z = false;
        }
        boolean z3 = z2 & z;
        CharSequence charSequence = null;
        setText(z3 ? this.f861e : null);
        CharSequence charSequence2 = this.f858b.f15791l;
        if (!TextUtils.isEmpty(charSequence2)) {
            setContentDescription(charSequence2);
        } else {
            setContentDescription(!z3 ? this.f858b.f15783d : null);
        }
        CharSequence charSequence3 = this.f858b.f15792m;
        if (TextUtils.isEmpty(charSequence3)) {
            if (!z3) {
                charSequence = this.f858b.f15783d;
            }
            C0637xj.m15898a((View) this, charSequence);
            return;
        }
        C0637xj.m15898a((View) this, charSequence3);
    }
}
