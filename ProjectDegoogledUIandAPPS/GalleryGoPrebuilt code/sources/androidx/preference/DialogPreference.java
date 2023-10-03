package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public abstract class DialogPreference extends Preference {

    /* renamed from: a */
    public CharSequence f1069a;

    /* renamed from: b */
    public CharSequence f1070b;

    /* renamed from: c */
    public Drawable f1071c;

    /* renamed from: d */
    public CharSequence f1072d;

    /* renamed from: e */
    public CharSequence f1073e;

    /* renamed from: f */
    public int f1074f;

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0071co.m4652a(context, (int) R.attr.dialogPreferenceStyle, 16842897));
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f252c, i, 0);
        String a = C0071co.m4663a(obtainStyledAttributes, 9, 0);
        this.f1069a = a;
        if (a == null) {
            this.f1069a = this.f1118q;
        }
        this.f1070b = C0071co.m4663a(obtainStyledAttributes, 8, 1);
        Drawable drawable = obtainStyledAttributes.getDrawable(6);
        this.f1071c = drawable == null ? obtainStyledAttributes.getDrawable(2) : drawable;
        this.f1072d = C0071co.m4663a(obtainStyledAttributes, 11, 3);
        this.f1073e = C0071co.m4663a(obtainStyledAttributes, 10, 4);
        this.f1074f = C0071co.m4654a(obtainStyledAttributes, 7, 5, 0);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1156a() {
        ads ads = this.f1112k.f238d;
        if (ads != null) {
            ads.mo209b(this);
        }
    }
}
