package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
public abstract class PreferenceGroup extends Preference {

    /* renamed from: a */
    public final C0309lf f1128a;

    /* renamed from: b */
    public List f1129b;

    /* renamed from: c */
    public int f1130c;

    /* renamed from: d */
    private boolean f1131d;

    /* renamed from: e */
    private int f1132e;

    /* renamed from: f */
    private boolean f1133f;

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: r */
    public boolean mo1205r() {
        return true;
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, (byte[]) null);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i, byte[] bArr) {
        super(context, attributeSet, i);
        this.f1128a = new C0309lf();
        new Handler();
        this.f1131d = true;
        this.f1132e = 0;
        this.f1133f = false;
        this.f1130c = Integer.MAX_VALUE;
        new adl(this);
        this.f1129b = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f258i, i, 0);
        this.f1131d = C0071co.m4666a(obtainStyledAttributes, 2, 2, true);
        if (obtainStyledAttributes.hasValue(1)) {
            mo1202f(C0071co.m4675d(obtainStyledAttributes, 1, 1));
        }
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final void mo1200a(Preference preference) {
        long j;
        if (!this.f1129b.contains(preference)) {
            if (preference.f1119r != null) {
                PreferenceGroup preferenceGroup = this;
                while (true) {
                    PreferenceGroup preferenceGroup2 = preferenceGroup.f1089B;
                    if (preferenceGroup2 == null) {
                        break;
                    }
                    preferenceGroup = preferenceGroup2;
                }
                String str = preference.f1119r;
                if (preferenceGroup.mo1201c(str) != null) {
                    Log.e("PreferenceGroup", "Found duplicated key: \"" + str + "\". This can cause unintended behaviour, please use unique keys for every preference.");
                }
            }
            if (preference.f1117p == Integer.MAX_VALUE) {
                if (this.f1131d) {
                    int i = this.f1132e;
                    this.f1132e = i + 1;
                    preference.mo1169a(i);
                }
                if (preference instanceof PreferenceGroup) {
                    ((PreferenceGroup) preference).f1131d = this.f1131d;
                }
            }
            int binarySearch = Collections.binarySearch(this.f1129b, preference);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 1;
            }
            preference.mo1183c(mo1162c());
            synchronized (this) {
                this.f1129b.add(binarySearch, preference);
            }
            adv adv = this.f1112k;
            String str2 = preference.f1119r;
            if (str2 == null || !this.f1128a.containsKey(str2)) {
                j = adv.mo227a();
            } else {
                j = ((Long) this.f1128a.get(str2)).longValue();
                this.f1128a.remove(str2);
            }
            preference.f1113l = j;
            preference.f1114m = true;
            try {
                preference.mo1171a(adv);
                preference.f1114m = false;
                if (preference.f1089B == null) {
                    preference.f1089B = this;
                    if (this.f1133f) {
                        preference.mo1194m();
                    }
                    mo1193l();
                    return;
                }
                throw new IllegalStateException("This preference already has a parent. You must remove the existing parent before assigning a new one.");
            } catch (Throwable th) {
                preference.f1114m = false;
                throw th;
            }
        }
    }

    /* renamed from: b */
    public final void mo1177b(Bundle bundle) {
        super.mo1177b(bundle);
        int g = mo1203g();
        for (int i = 0; i < g; i++) {
            mo1204g(i).mo1177b(bundle);
        }
    }

    /* renamed from: a */
    public final void mo1172a(Bundle bundle) {
        super.mo1172a(bundle);
        int g = mo1203g();
        for (int i = 0; i < g; i++) {
            mo1204g(i).mo1172a(bundle);
        }
    }

    /* renamed from: c */
    public final Preference mo1201c(CharSequence charSequence) {
        Preference c;
        if (charSequence == null) {
            throw new IllegalArgumentException("Key cannot be null");
        } else if (TextUtils.equals(this.f1119r, charSequence)) {
            return this;
        } else {
            int g = mo1203g();
            for (int i = 0; i < g; i++) {
                Preference g2 = mo1204g(i);
                if (TextUtils.equals(g2.f1119r, charSequence)) {
                    return g2;
                }
                if ((g2 instanceof PreferenceGroup) && (c = ((PreferenceGroup) g2).mo1201c(charSequence)) != null) {
                    return c;
                }
            }
            return null;
        }
    }

    /* renamed from: g */
    public final Preference mo1204g(int i) {
        return (Preference) this.f1129b.get(i);
    }

    /* renamed from: g */
    public final int mo1203g() {
        return this.f1129b.size();
    }

    /* renamed from: a */
    public final void mo1174a(boolean z) {
        super.mo1174a(z);
        int g = mo1203g();
        for (int i = 0; i < g; i++) {
            mo1204g(i).mo1183c(z);
        }
    }

    /* renamed from: m */
    public final void mo1194m() {
        super.mo1196o();
        this.f1133f = true;
        int g = mo1203g();
        for (int i = 0; i < g; i++) {
            mo1204g(i).mo1194m();
        }
    }

    /* renamed from: n */
    public final void mo1195n() {
        super.mo1195n();
        this.f1133f = false;
        int g = mo1203g();
        for (int i = 0; i < g; i++) {
            mo1204g(i).mo1195n();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1159a(Parcelable parcelable) {
        if (!parcelable.getClass().equals(adn.class)) {
            super.mo1159a(parcelable);
            return;
        }
        adn adn = (adn) parcelable;
        this.f1130c = adn.f222a;
        super.mo1159a(adn.getSuperState());
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final Parcelable mo1163d() {
        return new adn(super.mo1163d(), this.f1130c);
    }

    /* renamed from: f */
    public final void mo1202f(int i) {
        if (i != Integer.MAX_VALUE && !mo1190i()) {
            Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.f1130c = i;
    }
}
