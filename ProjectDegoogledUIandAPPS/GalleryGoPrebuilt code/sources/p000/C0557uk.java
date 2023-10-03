package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.util.Arrays;

/* renamed from: uk */
/* compiled from: PG */
public final class C0557uk {

    /* renamed from: a */
    public final TextView f15997a;

    /* renamed from: b */
    public Typeface f15998b;

    /* renamed from: c */
    public boolean f15999c;

    /* renamed from: d */
    private C0682za f16000d;

    /* renamed from: e */
    private C0682za f16001e;

    /* renamed from: f */
    private C0682za f16002f;

    /* renamed from: g */
    private C0682za f16003g;

    /* renamed from: h */
    private C0682za f16004h;

    /* renamed from: i */
    private C0682za f16005i;

    /* renamed from: j */
    private final C0559um f16006j;

    /* renamed from: k */
    private int f16007k = 0;

    /* renamed from: l */
    private int f16008l = -1;

    public C0557uk(TextView textView) {
        this.f15997a = textView;
        this.f16006j = new C0559um(this.f15997a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final int mo10250d() {
        return this.f16006j.f16014a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final int[] mo10254h() {
        return this.f16006j.f16019f;
    }

    /* renamed from: a */
    private final void m15495a(Drawable drawable, C0682za zaVar) {
        if (drawable != null && zaVar != null) {
            C0529tj.m15439a(drawable, zaVar, this.f15997a.getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10241a() {
        if (!(this.f16000d == null && this.f16001e == null && this.f16002f == null && this.f16003g == null)) {
            Drawable[] compoundDrawables = this.f15997a.getCompoundDrawables();
            m15495a(compoundDrawables[0], this.f16000d);
            m15495a(compoundDrawables[1], this.f16001e);
            m15495a(compoundDrawables[2], this.f16002f);
            m15495a(compoundDrawables[3], this.f16003g);
        }
        int i = Build.VERSION.SDK_INT;
        if (this.f16004h != null || this.f16005i != null) {
            Drawable[] compoundDrawablesRelative = this.f15997a.getCompoundDrawablesRelative();
            m15495a(compoundDrawablesRelative[0], this.f16004h);
            m15495a(compoundDrawablesRelative[2], this.f16005i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10248b() {
        this.f16006j.mo10293f();
    }

    /* renamed from: a */
    private static C0682za m15493a(Context context, C0529tj tjVar, int i) {
        ColorStateList b = tjVar.mo10133b(context, i);
        if (b == null) {
            return null;
        }
        C0682za zaVar = new C0682za();
        zaVar.f16434d = true;
        zaVar.f16431a = b;
        return zaVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final int mo10253g() {
        return this.f16006j.mo10290c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final int mo10252f() {
        return this.f16006j.mo10289b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final int mo10251e() {
        return this.f16006j.mo10286a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final boolean mo10249c() {
        return this.f16006j.mo10294g();
    }

    /* renamed from: a */
    public final void mo10246a(AttributeSet attributeSet, int i) {
        String str;
        String str2;
        boolean z;
        boolean z2;
        String str3;
        Drawable drawable;
        int resourceId;
        AttributeSet attributeSet2 = attributeSet;
        int i2 = i;
        Context context = this.f15997a.getContext();
        C0529tj b = C0529tj.m15440b();
        C0684zc a = C0684zc.m16192a(context, attributeSet2, C0435px.f15580h, i2, 0);
        int f = a.mo10734f(0, -1);
        if (a.mo10735f(3)) {
            this.f16000d = m15493a(context, b, a.mo10734f(3, 0));
        }
        if (a.mo10735f(1)) {
            this.f16001e = m15493a(context, b, a.mo10734f(1, 0));
        }
        if (a.mo10735f(4)) {
            this.f16002f = m15493a(context, b, a.mo10734f(4, 0));
        }
        if (a.mo10735f(2)) {
            this.f16003g = m15493a(context, b, a.mo10734f(2, 0));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (a.mo10735f(5)) {
            this.f16004h = m15493a(context, b, a.mo10734f(5, 0));
        }
        if (a.mo10735f(6)) {
            this.f16005i = m15493a(context, b, a.mo10734f(6, 0));
        }
        a.mo10724a();
        boolean z3 = this.f15997a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (f == -1) {
            z2 = false;
            z = false;
            str2 = null;
            str = null;
        } else {
            C0684zc a2 = C0684zc.m16190a(context, f, C0435px.f15595w);
            if (!z3 && a2.mo10735f(14)) {
                z2 = a2.mo10725a(14, false);
                z = true;
            } else {
                z2 = false;
                z = false;
            }
            m15494a(context, a2);
            int i4 = Build.VERSION.SDK_INT;
            if (a2.mo10735f(15)) {
                str2 = a2.mo10731d(15);
            } else {
                str2 = null;
            }
            int i5 = Build.VERSION.SDK_INT;
            str = a2.mo10735f(13) ? a2.mo10731d(13) : null;
            a2.mo10724a();
        }
        C0684zc a3 = C0684zc.m16192a(context, attributeSet2, C0435px.f15595w, i2, 0);
        if (!z3 && a3.mo10735f(14)) {
            z2 = a3.mo10725a(14, false);
            z = true;
        }
        int i6 = Build.VERSION.SDK_INT;
        if (a3.mo10735f(15)) {
            str2 = a3.mo10731d(15);
        }
        int i7 = Build.VERSION.SDK_INT;
        if (a3.mo10735f(13)) {
            str3 = a3.mo10731d(13);
        } else {
            str3 = str;
        }
        if (Build.VERSION.SDK_INT >= 28 && a3.mo10735f(0) && a3.mo10730d(0, -1) == 0) {
            this.f15997a.setTextSize(0, 0.0f);
        }
        m15494a(context, a3);
        a3.mo10724a();
        if (!z3 && z) {
            m15496a(z2);
        }
        Typeface typeface = this.f15998b;
        if (typeface != null) {
            if (this.f16008l == -1) {
                this.f15997a.setTypeface(typeface, this.f16007k);
            } else {
                this.f15997a.setTypeface(typeface);
            }
        }
        if (str3 != null) {
            this.f15997a.setFontVariationSettings(str3);
        }
        if (str2 != null) {
            int i8 = Build.VERSION.SDK_INT;
            this.f15997a.setTextLocales(LocaleList.forLanguageTags(str2));
        }
        C0559um umVar = this.f16006j;
        TypedArray obtainStyledAttributes = umVar.f16021h.obtainStyledAttributes(attributeSet2, C0435px.f15581i, i2, 0);
        if (obtainStyledAttributes.hasValue(5)) {
            umVar.f16014a = obtainStyledAttributes.getInt(5, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(4) ? obtainStyledAttributes.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(2) ? obtainStyledAttributes.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(1) ? obtainStyledAttributes.getDimension(1, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(3) && (resourceId = obtainStyledAttributes.getResourceId(3, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = obtainTypedArray.length();
            int[] iArr = new int[length];
            if (length > 0) {
                for (int i9 = 0; i9 < length; i9++) {
                    iArr[i9] = obtainTypedArray.getDimensionPixelSize(i9, -1);
                }
                umVar.f16019f = C0559um.m15514a(iArr);
                umVar.mo10291d();
            }
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!umVar.mo10295h()) {
            umVar.f16014a = 0;
        } else if (umVar.f16014a == 1) {
            if (!umVar.f16020g) {
                DisplayMetrics displayMetrics = umVar.f16021h.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                umVar.mo10287a(dimension2, dimension3, dimension);
            }
            umVar.mo10292e();
        }
        if (C0370nm.f15293a) {
            C0559um umVar2 = this.f16006j;
            if (umVar2.f16014a != 0) {
                int[] iArr2 = umVar2.f16019f;
                if (iArr2.length > 0) {
                    if (((float) this.f15997a.getAutoSizeStepGranularity()) != -1.0f) {
                        this.f15997a.setAutoSizeTextTypeUniformWithConfiguration(this.f16006j.mo10289b(), this.f16006j.mo10290c(), this.f16006j.mo10286a(), 0);
                    } else {
                        this.f15997a.setAutoSizeTextTypeUniformWithPresetSizes(iArr2, 0);
                    }
                }
            }
        }
        C0684zc a4 = C0684zc.m16191a(context, attributeSet2, C0435px.f15581i);
        int f2 = a4.mo10734f(8, -1);
        Drawable a5 = f2 != -1 ? b.mo10131a(context, f2) : null;
        int f3 = a4.mo10734f(13, -1);
        Drawable a6 = f3 != -1 ? b.mo10131a(context, f3) : null;
        int f4 = a4.mo10734f(9, -1);
        Drawable a7 = f4 != -1 ? b.mo10131a(context, f4) : null;
        int f5 = a4.mo10734f(6, -1);
        Drawable a8 = f5 != -1 ? b.mo10131a(context, f5) : null;
        int f6 = a4.mo10734f(10, -1);
        Drawable a9 = f6 != -1 ? b.mo10131a(context, f6) : null;
        int f7 = a4.mo10734f(7, -1);
        if (f7 != -1) {
            drawable = b.mo10131a(context, f7);
        } else {
            drawable = null;
        }
        int i10 = Build.VERSION.SDK_INT;
        if (a9 != null || drawable != null) {
            Drawable[] compoundDrawablesRelative = this.f15997a.getCompoundDrawablesRelative();
            TextView textView = this.f15997a;
            if (a9 == null) {
                a9 = compoundDrawablesRelative[0];
            }
            if (a6 == null) {
                a6 = compoundDrawablesRelative[1];
            }
            if (drawable == null) {
                drawable = compoundDrawablesRelative[2];
            }
            if (a8 == null) {
                a8 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(a9, a6, drawable, a8);
        } else if (!(a5 == null && a6 == null && a7 == null && a8 == null)) {
            int i11 = Build.VERSION.SDK_INT;
            Drawable[] compoundDrawablesRelative2 = this.f15997a.getCompoundDrawablesRelative();
            Drawable drawable2 = compoundDrawablesRelative2[0];
            if (drawable2 == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = this.f15997a.getCompoundDrawables();
                TextView textView2 = this.f15997a;
                if (a5 == null) {
                    a5 = compoundDrawables[0];
                }
                if (a6 == null) {
                    a6 = compoundDrawables[1];
                }
                if (a7 == null) {
                    a7 = compoundDrawables[2];
                }
                if (a8 == null) {
                    a8 = compoundDrawables[3];
                }
                textView2.setCompoundDrawablesWithIntrinsicBounds(a5, a6, a7, a8);
            } else {
                TextView textView3 = this.f15997a;
                if (a6 == null) {
                    a6 = compoundDrawablesRelative2[1];
                }
                Drawable drawable3 = compoundDrawablesRelative2[2];
                if (a8 == null) {
                    a8 = compoundDrawablesRelative2[3];
                }
                textView3.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable2, a6, drawable3, a8);
            }
        }
        if (a4.mo10735f(11)) {
            ColorStateList e = a4.mo10733e(11);
            TextView textView4 = this.f15997a;
            C0321lr.m14624a((Object) textView4);
            int i12 = Build.VERSION.SDK_INT;
            textView4.setCompoundDrawableTintList(e);
        }
        if (a4.mo10735f(12)) {
            PorterDuff.Mode a10 = C0579vf.m15603a(a4.mo10722a(12, -1), (PorterDuff.Mode) null);
            TextView textView5 = this.f15997a;
            C0321lr.m14624a((Object) textView5);
            int i13 = Build.VERSION.SDK_INT;
            textView5.setCompoundDrawableTintMode(a10);
        }
        int d = a4.mo10730d(14, -1);
        int d2 = a4.mo10730d(17, -1);
        int d3 = a4.mo10730d(18, -1);
        a4.mo10724a();
        if (d != -1) {
            dcm.m5905b(this.f15997a, d);
        }
        if (d2 != -1) {
            dcm.m5906c(this.f15997a, d2);
        }
        if (d3 != -1) {
            dcm.m5907d(this.f15997a, d3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public final void mo10255i() {
        if (!C0370nm.f15293a) {
            mo10248b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10245a(Context context, int i) {
        String d;
        C0684zc a = C0684zc.m16190a(context, i, C0435px.f15595w);
        if (a.mo10735f(14)) {
            m15496a(a.mo10725a(14, false));
        }
        int i2 = Build.VERSION.SDK_INT;
        if (a.mo10735f(0) && a.mo10730d(0, -1) == 0) {
            this.f15997a.setTextSize(0, 0.0f);
        }
        m15494a(context, a);
        int i3 = Build.VERSION.SDK_INT;
        if (a.mo10735f(13) && (d = a.mo10731d(13)) != null) {
            this.f15997a.setFontVariationSettings(d);
        }
        a.mo10724a();
        Typeface typeface = this.f15998b;
        if (typeface != null) {
            this.f15997a.setTypeface(typeface, this.f16007k);
        }
    }

    /* renamed from: a */
    private final void m15496a(boolean z) {
        this.f15997a.setAllCaps(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10244a(int i, int i2, int i3, int i4) {
        C0559um umVar = this.f16006j;
        if (umVar.mo10295h()) {
            DisplayMetrics displayMetrics = umVar.f16021h.getResources().getDisplayMetrics();
            umVar.mo10287a(TypedValue.applyDimension(i4, (float) i, displayMetrics), TypedValue.applyDimension(i4, (float) i2, displayMetrics), TypedValue.applyDimension(i4, (float) i3, displayMetrics));
            if (umVar.mo10292e()) {
                umVar.mo10293f();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10247a(int[] iArr, int i) {
        C0559um umVar = this.f16006j;
        if (umVar.mo10295h()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = umVar.f16021h.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, (float) iArr[i2], displayMetrics));
                    }
                }
                umVar.f16019f = C0559um.m15514a(iArr2);
                if (!umVar.mo10291d()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                umVar.f16020g = false;
            }
            if (umVar.mo10292e()) {
                umVar.mo10293f();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10242a(int i) {
        C0559um umVar = this.f16006j;
        if (!umVar.mo10295h()) {
            return;
        }
        if (i == 0) {
            umVar.f16014a = 0;
            umVar.f16017d = -1.0f;
            umVar.f16018e = -1.0f;
            umVar.f16016c = -1.0f;
            umVar.f16019f = new int[0];
            umVar.f16015b = false;
        } else if (i == 1) {
            DisplayMetrics displayMetrics = umVar.f16021h.getResources().getDisplayMetrics();
            umVar.mo10287a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (umVar.mo10292e()) {
                umVar.mo10293f();
            }
        } else {
            throw new IllegalArgumentException("Unknown auto-size text type: " + i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10243a(int i, float f) {
        if (!C0370nm.f15293a && !mo10249c()) {
            this.f16006j.mo10288a(i, f);
        }
    }

    /* renamed from: a */
    private final void m15494a(Context context, C0684zc zcVar) {
        String d;
        boolean z;
        C0684zc zcVar2 = zcVar;
        int[] iArr = C0435px.f15573a;
        this.f16007k = zcVar2.mo10722a(2, this.f16007k);
        if (Build.VERSION.SDK_INT >= 28) {
            int a = zcVar2.mo10722a(11, -1);
            this.f16008l = a;
            if (a != -1) {
                this.f16007k &= 2;
            }
        }
        int i = 12;
        boolean z2 = false;
        if (zcVar2.mo10735f(10) || zcVar2.mo10735f(12)) {
            Typeface typeface = null;
            this.f15998b = null;
            if (!zcVar2.mo10735f(12)) {
                i = 10;
            }
            int i2 = this.f16008l;
            int i3 = this.f16007k;
            if (!context.isRestricted()) {
                C0556uj ujVar = new C0556uj(this, i2, i3);
                try {
                    int i4 = this.f16007k;
                    int resourceId = zcVar2.f16436b.getResourceId(i, 0);
                    if (resourceId != 0) {
                        if (zcVar2.f16437c == null) {
                            zcVar2.f16437c = new TypedValue();
                        }
                        Context context2 = zcVar2.f16435a;
                        TypedValue typedValue = zcVar2.f16437c;
                        if (!context2.isRestricted()) {
                            typeface = C0071co.m4658a(context2, resourceId, typedValue, i4, ujVar, true);
                        }
                    }
                    if (typeface != null) {
                        if (Build.VERSION.SDK_INT < 28 || this.f16008l == -1) {
                            this.f15998b = typeface;
                        } else {
                            Typeface create = Typeface.create(typeface, 0);
                            int i5 = this.f16008l;
                            if ((this.f16007k & 2) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            this.f15998b = Typeface.create(create, i5, z);
                        }
                    }
                    this.f15999c = this.f15998b == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException e) {
                }
            }
            if (this.f15998b == null && (d = zcVar2.mo10731d(i)) != null) {
                if (Build.VERSION.SDK_INT >= 28 && this.f16008l != -1) {
                    Typeface create2 = Typeface.create(d, 0);
                    int i6 = this.f16008l;
                    if ((2 & this.f16007k) != 0) {
                        z2 = true;
                    }
                    this.f15998b = Typeface.create(create2, i6, z2);
                    return;
                }
                this.f15998b = Typeface.create(d, this.f16007k);
            }
        } else if (zcVar2.mo10735f(1)) {
            this.f15999c = false;
            int a2 = zcVar2.mo10722a(1, 1);
            if (a2 == 1) {
                this.f15998b = Typeface.SANS_SERIF;
            } else if (a2 == 2) {
                this.f15998b = Typeface.SERIF;
            } else if (a2 == 3) {
                this.f15998b = Typeface.MONOSPACE;
            }
        }
    }
}
