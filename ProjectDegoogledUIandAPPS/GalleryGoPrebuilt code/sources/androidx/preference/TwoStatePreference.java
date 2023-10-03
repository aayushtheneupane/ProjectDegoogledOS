package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/* compiled from: PG */
public abstract class TwoStatePreference extends Preference {

    /* renamed from: a */
    public boolean f1153a;

    /* renamed from: b */
    public boolean f1154b;

    /* renamed from: c */
    private CharSequence f1155c;

    /* renamed from: d */
    private CharSequence f1156d;

    /* renamed from: e */
    private boolean f1157e;

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1156a() {
        boolean z = !this.f1153a;
        if (mo1179b((Object) Boolean.valueOf(z))) {
            mo1212d(z);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo1158a(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1159a(Parcelable parcelable) {
        if (!parcelable.getClass().equals(aeh.class)) {
            super.mo1159a(parcelable);
            return;
        }
        aeh aeh = (aeh) parcelable;
        super.mo1159a(aeh.getSuperState());
        mo1212d(aeh.f272a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final Parcelable mo1163d() {
        Parcelable d = super.mo1163d();
        if (this.f1123v) {
            return d;
        }
        aeh aeh = new aeh(d);
        aeh.f272a = this.f1153a;
        return aeh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1160a(Object obj) {
        if (obj == null) {
            obj = false;
        }
        mo1212d(mo1180b(((Boolean) obj).booleanValue()));
    }

    /* renamed from: d */
    public final void mo1212d(boolean z) {
        boolean z2 = this.f1153a;
        if (z2 != z || !this.f1157e) {
            this.f1153a = z;
            this.f1157e = true;
            if (mo1191j() && z != mo1180b(!z)) {
                SharedPreferences.Editor c = this.f1112k.mo231c();
                c.putBoolean(this.f1119r, z);
                Preference.m1014a(c);
            }
            if (z2 != z) {
                mo1174a(mo1162c());
                mo1157b();
            }
        }
    }

    /* renamed from: d */
    public final void mo1211d(CharSequence charSequence) {
        this.f1156d = charSequence;
        if (!this.f1153a) {
            mo1157b();
        }
    }

    /* renamed from: c */
    public final void mo1210c(CharSequence charSequence) {
        this.f1155c = charSequence;
        if (this.f1153a) {
            mo1157b();
        }
    }

    /* renamed from: c */
    public final boolean mo1162c() {
        if (this.f1154b) {
            if (this.f1153a) {
                return true;
            }
        } else if (!this.f1153a) {
            return true;
        }
        return super.mo1162c();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo1209b(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            int i = 0;
            if (this.f1153a && !TextUtils.isEmpty(this.f1155c)) {
                textView.setText(this.f1155c);
            } else if (this.f1153a || TextUtils.isEmpty(this.f1156d)) {
                CharSequence f = mo1166f();
                if (!TextUtils.isEmpty(f)) {
                    textView.setText(f);
                } else {
                    i = 8;
                }
            } else {
                textView.setText(this.f1156d);
            }
            if (i != textView.getVisibility()) {
                textView.setVisibility(i);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo1208b(ady ady) {
        mo1209b(ady.mo235c(16908304));
    }
}
