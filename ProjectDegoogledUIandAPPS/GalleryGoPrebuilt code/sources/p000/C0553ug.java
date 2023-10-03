package p000;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/* renamed from: ug */
/* compiled from: PG */
public final class C0553ug extends Spinner {

    /* renamed from: e */
    private static final int[] f15981e = {16843505};

    /* renamed from: a */
    public final Context f15982a;

    /* renamed from: b */
    public C0552uf f15983b;

    /* renamed from: c */
    public int f15984c;

    /* renamed from: d */
    public final Rect f15985d = new Rect();

    /* renamed from: f */
    private final C0523td f15986f;

    /* renamed from: g */
    private C0590vq f15987g;

    /* renamed from: h */
    private SpinnerAdapter f15988h;

    /* renamed from: i */
    private final boolean f15989i;

    public final Context getPopupContext() {
        return this.f15982a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0553ug(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = 2130969251(0x7f0402a3, float:1.7547179E38)
            r10.<init>(r11, r12, r0)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r10.f15985d = r1
            int[] r1 = p000.C0435px.f15593u
            r2 = 0
            zc r1 = p000.C0684zc.m16192a(r11, r12, r1, r0, r2)
            td r3 = new td
            r3.<init>(r10)
            r10.f15986f = r3
            r3 = 4
            int r3 = r1.mo10734f(r3, r2)
            if (r3 == 0) goto L_0x002a
            qg r4 = new qg
            r4.<init>((android.content.Context) r11, (int) r3)
            r10.f15982a = r4
            goto L_0x002c
        L_0x002a:
            r10.f15982a = r11
        L_0x002c:
            r3 = -1
            r4 = 0
            int[] r5 = f15981e     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            android.content.res.TypedArray r5 = r11.obtainStyledAttributes(r12, r5, r0, r2)     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            boolean r6 = r5.hasValue(r2)     // Catch:{ Exception -> 0x004b, all -> 0x0047 }
            if (r6 == 0) goto L_0x003f
            int r3 = r5.getInt(r2, r2)     // Catch:{ Exception -> 0x004b, all -> 0x0047 }
            goto L_0x0040
        L_0x003f:
        L_0x0040:
            if (r5 != 0) goto L_0x0043
            goto L_0x005e
        L_0x0043:
            r5.recycle()
            goto L_0x005f
        L_0x0047:
            r11 = move-exception
            r4 = r5
            goto L_0x004e
        L_0x004b:
            r6 = move-exception
            goto L_0x0056
        L_0x004d:
            r11 = move-exception
        L_0x004e:
            if (r4 == 0) goto L_0x0053
            r4.recycle()
        L_0x0053:
            throw r11
        L_0x0054:
            r5 = move-exception
            r5 = r4
        L_0x0056:
            if (r5 == 0) goto L_0x005d
            r5.recycle()
            goto L_0x005f
        L_0x005d:
        L_0x005e:
        L_0x005f:
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x0097
            if (r3 == r6) goto L_0x0066
            goto L_0x00a5
        L_0x0066:
            uc r3 = new uc
            android.content.Context r7 = r10.f15982a
            r3.<init>(r10, r7, r12)
            android.content.Context r7 = r10.f15982a
            int[] r8 = p000.C0435px.f15593u
            zc r7 = p000.C0684zc.m16192a(r7, r12, r8, r0, r2)
            r8 = 3
            r9 = -2
            int r8 = r7.mo10732e(r8, r9)
            r10.f15984c = r8
            android.graphics.drawable.Drawable r8 = r7.mo10723a(r6)
            r3.mo10499a((android.graphics.drawable.Drawable) r8)
            java.lang.String r5 = r1.mo10731d(r5)
            r3.f15975a = r5
            r7.mo10724a()
            r10.f15983b = r3
            tv r5 = new tv
            r5.<init>(r10, r10, r3)
            r10.f15987g = r5
            goto L_0x00a5
        L_0x0097:
            tx r3 = new tx
            r3.<init>(r10)
            r10.f15983b = r3
            java.lang.String r5 = r1.mo10731d(r5)
            r3.mo10185a((java.lang.CharSequence) r5)
        L_0x00a5:
            android.content.res.TypedArray r3 = r1.f16436b
            java.lang.CharSequence[] r2 = r3.getTextArray(r2)
            if (r2 == 0) goto L_0x00be
            android.widget.ArrayAdapter r3 = new android.widget.ArrayAdapter
            r5 = 17367048(0x1090008, float:2.5162948E-38)
            r3.<init>(r11, r5, r2)
            r11 = 2131558588(0x7f0d00bc, float:1.8742496E38)
            r3.setDropDownViewResource(r11)
            r10.setAdapter((android.widget.SpinnerAdapter) r3)
        L_0x00be:
            r1.mo10724a()
            r10.f15989i = r6
            android.widget.SpinnerAdapter r11 = r10.f15988h
            if (r11 == 0) goto L_0x00cc
            r10.setAdapter((android.widget.SpinnerAdapter) r11)
            r10.f15988h = r4
        L_0x00cc:
            td r11 = r10.f15986f
            r11.mo10104a(r12, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0553ug.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo10214a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int i2 = 0;
        while (max2 < min) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            int i3 = itemViewType == i ? i : itemViewType;
            if (itemViewType != i) {
                view = null;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
            max2++;
            i = i3;
        }
        if (drawable == null) {
            return i2;
        }
        drawable.getPadding(this.f15985d);
        return i2 + this.f15985d.left + this.f15985d.right;
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15986f;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
    }

    public final int getDropDownHorizontalOffset() {
        C0552uf ufVar = this.f15983b;
        if (ufVar != null) {
            return ufVar.mo10192f();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownHorizontalOffset();
    }

    public final int getDropDownVerticalOffset() {
        C0552uf ufVar = this.f15983b;
        if (ufVar != null) {
            return ufVar.mo10188c();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownVerticalOffset();
    }

    public final int getDropDownWidth() {
        if (this.f15983b != null) {
            return this.f15984c;
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownWidth();
    }

    public final Drawable getPopupBackground() {
        C0552uf ufVar = this.f15983b;
        if (ufVar != null) {
            return ufVar.mo10186b();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getPopupBackground();
    }

    public final CharSequence getPrompt() {
        C0552uf ufVar = this.f15983b;
        return ufVar != null ? ufVar.mo10180a() : super.getPrompt();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0552uf ufVar = this.f15983b;
        if (ufVar != null && ufVar.mo10191e()) {
            this.f15983b.mo10190d();
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f15983b != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), mo10214a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        C0551ue ueVar = (C0551ue) parcelable;
        super.onRestoreInstanceState(ueVar.getSuperState());
        if (ueVar.f15980a && (viewTreeObserver = getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new C0542tw(this));
        }
    }

    public final Parcelable onSaveInstanceState() {
        C0551ue ueVar = new C0551ue(super.onSaveInstanceState());
        C0552uf ufVar = this.f15983b;
        boolean z = false;
        if (ufVar != null && ufVar.mo10191e()) {
            z = true;
        }
        ueVar.f15980a = z;
        return ueVar;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        C0590vq vqVar = this.f15987g;
        if (vqVar == null || !vqVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public final boolean performClick() {
        C0552uf ufVar = this.f15983b;
        if (ufVar == null) {
            return super.performClick();
        }
        if (ufVar.mo10191e()) {
            return true;
        }
        mo10215a();
        return true;
    }

    public final void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.f15989i) {
            super.setAdapter(spinnerAdapter);
            if (this.f15983b != null) {
                Context context = this.f15982a;
                if (context == null) {
                    context = getContext();
                }
                this.f15983b.mo10184a((ListAdapter) new C0544ty(spinnerAdapter, context.getTheme()));
                return;
            }
            return;
        }
        this.f15988h = spinnerAdapter;
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15986f;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15986f;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setDropDownHorizontalOffset(int i) {
        C0552uf ufVar = this.f15983b;
        if (ufVar != null) {
            ufVar.mo10189c(i);
            this.f15983b.mo10187b(i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownHorizontalOffset(i);
    }

    public final void setDropDownVerticalOffset(int i) {
        C0552uf ufVar = this.f15983b;
        if (ufVar == null) {
            int i2 = Build.VERSION.SDK_INT;
            super.setDropDownVerticalOffset(i);
            return;
        }
        ufVar.mo10181a(i);
    }

    public final void setDropDownWidth(int i) {
        if (this.f15983b == null) {
            int i2 = Build.VERSION.SDK_INT;
            super.setDropDownWidth(i);
            return;
        }
        this.f15984c = i;
    }

    public final void setPopupBackgroundDrawable(Drawable drawable) {
        C0552uf ufVar = this.f15983b;
        if (ufVar == null) {
            int i = Build.VERSION.SDK_INT;
            super.setPopupBackgroundDrawable(drawable);
            return;
        }
        ufVar.mo10183a(drawable);
    }

    public final void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(C0436py.m15105b(this.f15982a, i));
    }

    public final void setPrompt(CharSequence charSequence) {
        C0552uf ufVar = this.f15983b;
        if (ufVar != null) {
            ufVar.mo10185a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10215a() {
        int i = Build.VERSION.SDK_INT;
        this.f15983b.mo10182a(getTextDirection(), getTextAlignment());
    }
}
