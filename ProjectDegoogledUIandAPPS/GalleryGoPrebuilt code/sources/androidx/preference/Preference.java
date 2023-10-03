package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
public class Preference implements Comparable {

    /* renamed from: A */
    public acy f1088A;

    /* renamed from: B */
    public PreferenceGroup f1089B;

    /* renamed from: C */
    public adc f1090C;

    /* renamed from: D */
    private boolean f1091D;

    /* renamed from: E */
    private boolean f1092E;

    /* renamed from: F */
    private boolean f1093F;

    /* renamed from: G */
    private boolean f1094G;

    /* renamed from: H */
    private boolean f1095H;

    /* renamed from: I */
    private boolean f1096I;

    /* renamed from: J */
    private boolean f1097J;

    /* renamed from: K */
    private List f1098K;

    /* renamed from: L */
    private boolean f1099L;

    /* renamed from: M */
    private adb f1100M;

    /* renamed from: N */
    private final View.OnClickListener f1101N;

    /* renamed from: a */
    private CharSequence f1102a;

    /* renamed from: b */
    private int f1103b;

    /* renamed from: c */
    private Drawable f1104c;

    /* renamed from: d */
    private boolean f1105d;

    /* renamed from: e */
    private boolean f1106e;

    /* renamed from: f */
    private boolean f1107f;

    /* renamed from: g */
    private String f1108g;

    /* renamed from: h */
    private Object f1109h;

    /* renamed from: i */
    private boolean f1110i;

    /* renamed from: j */
    public Context f1111j;

    /* renamed from: k */
    public adv f1112k;

    /* renamed from: l */
    public long f1113l;

    /* renamed from: m */
    public boolean f1114m;

    /* renamed from: n */
    public acz f1115n;

    /* renamed from: o */
    public ada f1116o;

    /* renamed from: p */
    public int f1117p;

    /* renamed from: q */
    public CharSequence f1118q;

    /* renamed from: r */
    public String f1119r;

    /* renamed from: s */
    public Intent f1120s;

    /* renamed from: t */
    public String f1121t;

    /* renamed from: u */
    public Bundle f1122u;

    /* renamed from: v */
    public boolean f1123v;

    /* renamed from: w */
    public boolean f1124w;

    /* renamed from: x */
    public boolean f1125x;

    /* renamed from: y */
    public int f1126y;

    /* renamed from: z */
    public int f1127z;

    public Preference(Context context) {
        this(context, (AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo1158a(TypedArray typedArray, int i) {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1156a() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1160a(Object obj) {
    }

    @Deprecated
    /* renamed from: a */
    public void mo1173a(C0354mx mxVar) {
    }

    /* renamed from: e */
    public long mo170e() {
        return this.f1113l;
    }

    /* renamed from: h */
    public boolean mo1189h() {
        return this.f1105d && this.f1110i && this.f1091D;
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0071co.m4652a(context, (int) R.attr.preferenceStyle, 16842894));
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this.f1117p = Integer.MAX_VALUE;
        this.f1105d = true;
        this.f1106e = true;
        this.f1123v = true;
        this.f1110i = true;
        this.f1091D = true;
        this.f1124w = true;
        this.f1092E = true;
        this.f1093F = true;
        this.f1095H = true;
        this.f1097J = true;
        this.f1126y = R.layout.preference;
        this.f1101N = new acv(this);
        this.f1111j = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f256g, i, 0);
        this.f1103b = C0071co.m4654a(obtainStyledAttributes, 23, 0, 0);
        this.f1119r = C0071co.m4663a(obtainStyledAttributes, 26, 6);
        this.f1118q = C0071co.m4670b(obtainStyledAttributes, 34, 4);
        this.f1102a = C0071co.m4670b(obtainStyledAttributes, 33, 7);
        this.f1117p = C0071co.m4675d(obtainStyledAttributes, 28, 8);
        this.f1121t = C0071co.m4663a(obtainStyledAttributes, 22, 13);
        this.f1126y = C0071co.m4654a(obtainStyledAttributes, 27, 3, (int) R.layout.preference);
        this.f1127z = C0071co.m4654a(obtainStyledAttributes, 35, 9, 0);
        this.f1105d = C0071co.m4666a(obtainStyledAttributes, 21, 2, true);
        this.f1106e = C0071co.m4666a(obtainStyledAttributes, 30, 5, true);
        this.f1123v = C0071co.m4666a(obtainStyledAttributes, 29, 1, true);
        this.f1108g = C0071co.m4663a(obtainStyledAttributes, 19, 10);
        this.f1092E = C0071co.m4666a(obtainStyledAttributes, 16, 16, this.f1106e);
        this.f1093F = C0071co.m4666a(obtainStyledAttributes, 17, 17, this.f1106e);
        if (obtainStyledAttributes.hasValue(18)) {
            this.f1109h = mo1158a(obtainStyledAttributes, 18);
        } else if (obtainStyledAttributes.hasValue(11)) {
            this.f1109h = mo1158a(obtainStyledAttributes, 11);
        }
        this.f1097J = C0071co.m4666a(obtainStyledAttributes, 31, 12, true);
        boolean hasValue = obtainStyledAttributes.hasValue(32);
        this.f1094G = hasValue;
        if (hasValue) {
            this.f1095H = C0071co.m4666a(obtainStyledAttributes, 32, 14, true);
        }
        this.f1096I = C0071co.m4666a(obtainStyledAttributes, 24, 15, false);
        this.f1124w = C0071co.m4666a(obtainStyledAttributes, 25, 25, true);
        this.f1125x = C0071co.m4666a(obtainStyledAttributes, 20, 20, false);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: b */
    public final boolean mo1179b(Object obj) {
        acz acz = this.f1115n;
        return acz == null || acz.mo184a(this, obj);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        Preference preference = (Preference) obj;
        int i = this.f1117p;
        int i2 = preference.f1117p;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.f1118q;
        CharSequence charSequence2 = preference.f1118q;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.f1118q.toString());
    }

    /* renamed from: b */
    public void mo1177b(Bundle bundle) {
        Parcelable parcelable;
        if (mo1190i() && (parcelable = bundle.getParcelable(this.f1119r)) != null) {
            this.f1099L = false;
            mo1159a(parcelable);
            if (!this.f1099L) {
                throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
            }
        }
    }

    /* renamed from: a */
    public void mo1172a(Bundle bundle) {
        if (mo1190i()) {
            this.f1099L = false;
            Parcelable d = mo1163d();
            if (!this.f1099L) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            } else if (d != null) {
                bundle.putParcelable(this.f1119r, d);
            }
        }
    }

    /* renamed from: a */
    private final Preference mo1161a(String str) {
        adv adv = this.f1112k;
        if (adv != null) {
            return adv.mo228a((CharSequence) str);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo1180b(boolean z) {
        return mo1191j() ? this.f1112k.mo230b().getBoolean(this.f1119r, z) : z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public final int mo1187e(int i) {
        return mo1191j() ? this.f1112k.mo230b().getInt(this.f1119r, i) : i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final String mo1185d(String str) {
        return mo1191j() ? this.f1112k.mo230b().getString(this.f1119r, str) : str;
    }

    /* renamed from: b */
    public final Set mo1175b(Set set) {
        return mo1191j() ? this.f1112k.mo230b().getStringSet(this.f1119r, set) : set;
    }

    /* renamed from: f */
    public CharSequence mo1166f() {
        adc adc = this.f1090C;
        return adc != null ? adc.mo163a(this) : this.f1102a;
    }

    /* renamed from: i */
    public final boolean mo1190i() {
        return !TextUtils.isEmpty(this.f1119r);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo1157b() {
        int indexOf;
        acy acy = this.f1088A;
        if (acy != null && (indexOf = ((adr) acy).f229c.indexOf(this)) != -1) {
            ((C0634xg) acy).mo10535a(indexOf, (Object) this);
        }
    }

    /* renamed from: a */
    public void mo1174a(boolean z) {
        List list = this.f1098K;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Preference) list.get(i)).mo1212d(z);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public final void mo1193l() {
        acy acy = this.f1088A;
        if (acy != null) {
            acy.mo183b();
        }
    }

    /* renamed from: m */
    public void mo1194m() {
        mo1196o();
    }

    /* renamed from: a */
    public final void mo1171a(adv adv) {
        this.f1112k = adv;
        if (!this.f1114m) {
            this.f1113l = adv.mo227a();
        }
        if (mo1191j()) {
            adv adv2 = this.f1112k;
            if ((adv2 != null ? adv2.mo230b() : null).contains(this.f1119r)) {
                mo1160a((Object) null);
                return;
            }
        }
        Object obj = this.f1109h;
        if (obj != null) {
            mo1160a(obj);
        }
    }

    /* renamed from: a */
    public void mo169a(ady ady) {
        Integer num;
        int i;
        View view = ady.f16382a;
        view.setOnClickListener(this.f1101N);
        view.setId(0);
        TextView textView = (TextView) ady.mo235c(16908304);
        int i2 = 8;
        if (textView != null) {
            CharSequence f = mo1166f();
            if (!TextUtils.isEmpty(f)) {
                textView.setText(f);
                textView.setVisibility(0);
                num = Integer.valueOf(textView.getCurrentTextColor());
            } else {
                textView.setVisibility(8);
                num = null;
            }
        } else {
            num = null;
        }
        TextView textView2 = (TextView) ady.mo235c(16908310);
        if (textView2 != null) {
            CharSequence charSequence = this.f1118q;
            if (!TextUtils.isEmpty(charSequence)) {
                textView2.setText(charSequence);
                textView2.setVisibility(0);
                if (this.f1094G) {
                    textView2.setSingleLine(this.f1095H);
                }
                if (!this.f1106e && mo1189h() && num != null) {
                    textView2.setTextColor(num.intValue());
                }
            } else {
                textView2.setVisibility(8);
            }
        }
        ImageView imageView = (ImageView) ady.mo235c(16908294);
        if (imageView != null) {
            int i3 = this.f1103b;
            if (!(i3 == 0 && this.f1104c == null)) {
                if (this.f1104c == null) {
                    this.f1104c = C0436py.m15105b(this.f1111j, i3);
                }
                Drawable drawable = this.f1104c;
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                }
            }
            if (this.f1104c == null) {
                if (!this.f1096I) {
                    i = 8;
                } else {
                    i = 4;
                }
                imageView.setVisibility(i);
            } else {
                imageView.setVisibility(0);
            }
        }
        View c = ady.mo235c(R.id.icon_frame);
        if (c == null) {
            c = ady.mo235c(16908350);
        }
        if (c != null) {
            if (this.f1104c == null) {
                if (this.f1096I) {
                    i2 = 4;
                }
                c.setVisibility(i2);
            } else {
                c.setVisibility(0);
            }
        }
        if (!this.f1097J) {
            m1015a(view, true);
        } else {
            m1015a(view, mo1189h());
        }
        boolean z = this.f1106e;
        view.setFocusable(z);
        view.setClickable(z);
        ady.f247p = this.f1092E;
        ady.f248q = this.f1093F;
        boolean z2 = this.f1125x;
        if (z2 && this.f1100M == null) {
            this.f1100M = new adb(this);
        }
        view.setOnCreateContextMenuListener(z2 ? this.f1100M : null);
        view.setLongClickable(z2);
        if (z2 && !z) {
            C0340mj.m14694a(view, (Drawable) null);
        }
    }

    /* renamed from: d */
    private final void mo1212d(boolean z) {
        if (this.f1110i == z) {
            this.f1110i = !z;
            mo1174a(mo1162c());
            mo1157b();
        }
    }

    /* renamed from: n */
    public void mo1195n() {
        Preference a;
        List list;
        String str = this.f1108g;
        if (str != null && (a = mo1161a(str)) != null && (list = a.f1098K) != null) {
            list.remove(this);
        }
    }

    /* renamed from: c */
    public final void mo1183c(boolean z) {
        if (this.f1091D == z) {
            this.f1091D = !z;
            mo1174a(mo1162c());
            mo1157b();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1159a(Parcelable parcelable) {
        this.f1099L = true;
        if (parcelable != acx.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Parcelable mo1163d() {
        this.f1099L = true;
        return acx.EMPTY_STATE;
    }

    /* renamed from: k */
    public final void mo1192k() {
        Intent intent;
        adu adu;
        if (mo1189h() && this.f1106e) {
            mo1156a();
            ada ada = this.f1116o;
            if (ada == null || !ada.mo189a(this)) {
                adv adv = this.f1112k;
                if ((adv == null || (adu = adv.f237c) == null || !adu.mo207a(this)) && (intent = this.f1120s) != null) {
                    this.f1111j.startActivity(intent);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo1155a(View view) {
        mo1192k();
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public final void mo1188e(String str) {
        if (mo1191j() && !TextUtils.equals(str, mo1185d((String) null))) {
            SharedPreferences.Editor c = this.f1112k.mo231c();
            c.putString(this.f1119r, str);
            m1014a(c);
        }
    }

    /* renamed from: o */
    public final void mo1196o() {
        if (!TextUtils.isEmpty(this.f1108g)) {
            Preference a = mo1161a(this.f1108g);
            if (a != null) {
                if (a.f1098K == null) {
                    a.f1098K = new ArrayList();
                }
                a.f1098K.add(this);
                mo1212d(a.mo1162c());
                return;
            }
            throw new IllegalStateException("Dependency \"" + this.f1108g + "\" not found for preference \"" + this.f1119r + "\" (title: \"" + this.f1118q + "\"");
        }
    }

    /* renamed from: a */
    private final void m1015a(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                m1015a(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    /* renamed from: c */
    public final void mo1181c(int i) {
        Drawable b = C0436py.m15105b(this.f1111j, i);
        if (this.f1104c != b) {
            this.f1104c = b;
            this.f1103b = 0;
            mo1157b();
        }
        this.f1103b = i;
    }

    /* renamed from: p */
    public final void mo1197p() {
        if (!this.f1096I) {
            this.f1096I = true;
            mo1157b();
        }
    }

    /* renamed from: c */
    public final void mo1182c(String str) {
        this.f1119r = str;
        if (this.f1107f && !mo1190i()) {
            if (!TextUtils.isEmpty(this.f1119r)) {
                this.f1107f = true;
                return;
            }
            throw new IllegalStateException("Preference does not have a key assigned.");
        }
    }

    /* renamed from: a */
    public final void mo1169a(int i) {
        if (i != this.f1117p) {
            this.f1117p = i;
            mo1193l();
        }
    }

    /* renamed from: q */
    public final void mo1198q() {
        this.f1123v = false;
    }

    /* renamed from: d */
    public final void mo1186d(int i) {
        mo1164a((CharSequence) this.f1111j.getString(i));
    }

    /* renamed from: a */
    public void mo1164a(CharSequence charSequence) {
        if (this.f1090C != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        } else if (!TextUtils.equals(this.f1102a, charSequence)) {
            this.f1102a = charSequence;
            mo1157b();
        }
    }

    /* renamed from: a */
    public final void mo1170a(adc adc) {
        this.f1090C = adc;
        mo1157b();
    }

    /* renamed from: b */
    public final void mo1176b(int i) {
        mo1178b((CharSequence) this.f1111j.getString(i));
    }

    /* renamed from: b */
    public final void mo1178b(CharSequence charSequence) {
        if ((charSequence == null && this.f1118q != null) || (charSequence != null && !charSequence.equals(this.f1118q))) {
            this.f1118q = charSequence;
            mo1157b();
        }
    }

    /* renamed from: c */
    public boolean mo1162c() {
        return !mo1189h();
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public final boolean mo1191j() {
        return this.f1112k != null && this.f1123v && mo1190i();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequence = this.f1118q;
        if (!TextUtils.isEmpty(charSequence)) {
            sb.append(charSequence);
            sb.append(' ');
        }
        CharSequence f = mo1166f();
        if (!TextUtils.isEmpty(f)) {
            sb.append(f);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static final void m1014a(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
