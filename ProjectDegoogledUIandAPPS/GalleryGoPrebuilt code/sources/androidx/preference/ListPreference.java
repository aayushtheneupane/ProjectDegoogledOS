package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class ListPreference extends DialogPreference {

    /* renamed from: D */
    private String f1080D;

    /* renamed from: E */
    private boolean f1081E;

    /* renamed from: g */
    public CharSequence[] f1082g;

    /* renamed from: h */
    public CharSequence[] f1083h;

    /* renamed from: i */
    public String f1084i;

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0071co.m4652a(context, (int) R.attr.dialogPreferenceStyle, 16842897));
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f254e, i, 0);
        this.f1082g = C0071co.m4674c(obtainStyledAttributes, 2, 0);
        this.f1083h = C0071co.m4674c(obtainStyledAttributes, 3, 1);
        if (C0071co.m4666a(obtainStyledAttributes, 4, 4, false)) {
            if (aco.f185a == null) {
                aco.f185a = new aco();
            }
            mo1170a((adc) aco.f185a);
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, adz.f256g, i, 0);
        this.f1080D = C0071co.m4663a(obtainStyledAttributes2, 33, 7);
        obtainStyledAttributes2.recycle();
    }

    /* renamed from: b */
    public final int mo1165b(String str) {
        CharSequence[] charSequenceArr;
        if (!(str == null || (charSequenceArr = this.f1083h) == null)) {
            for (int length = charSequenceArr.length - 1; length >= 0; length--) {
                if (this.f1083h[length].equals(str)) {
                    return length;
                }
            }
        }
        return -1;
    }

    /* renamed from: g */
    public final CharSequence mo1167g() {
        CharSequence[] charSequenceArr;
        int b = mo1165b(this.f1084i);
        if (b < 0 || (charSequenceArr = this.f1082g) == null) {
            return null;
        }
        return charSequenceArr[b];
    }

    /* renamed from: f */
    public final CharSequence mo1166f() {
        adc adc = this.f1090C;
        if (adc != null) {
            return adc.mo163a(this);
        }
        Object g = mo1167g();
        CharSequence f = super.mo1166f();
        String str = this.f1080D;
        if (str != null) {
            Object[] objArr = new Object[1];
            if (g == null) {
                g = "";
            }
            objArr[0] = g;
            String format = String.format(str, objArr);
            if (!TextUtils.equals(format, f)) {
                Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
                return format;
            }
        }
        return f;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo1158a(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1159a(Parcelable parcelable) {
        if (!parcelable.getClass().equals(acn.class)) {
            super.mo1159a(parcelable);
            return;
        }
        acn acn = (acn) parcelable;
        super.mo1159a(acn.getSuperState());
        mo1161a(acn.f184a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final Parcelable mo1163d() {
        Parcelable d = super.mo1163d();
        if (this.f1123v) {
            return d;
        }
        acn acn = new acn(d);
        acn.f184a = this.f1084i;
        return acn;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1160a(Object obj) {
        mo1161a(mo1185d((String) obj));
    }

    /* renamed from: a */
    public final void mo1164a(CharSequence charSequence) {
        super.mo1164a(charSequence);
        if (charSequence == null && this.f1080D != null) {
            this.f1080D = null;
        } else if (charSequence != null && !charSequence.equals(this.f1080D)) {
            this.f1080D = charSequence.toString();
        }
    }

    /* renamed from: a */
    public final void mo1161a(String str) {
        boolean z = !TextUtils.equals(this.f1084i, str);
        if (z || !this.f1081E) {
            this.f1084i = str;
            this.f1081E = true;
            mo1188e(str);
            if (z) {
                mo1157b();
            }
        }
    }
}
