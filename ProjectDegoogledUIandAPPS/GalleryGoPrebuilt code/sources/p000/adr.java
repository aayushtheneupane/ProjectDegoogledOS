package p000;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: adr */
/* compiled from: PG */
public final class adr extends C0634xg implements acy {

    /* renamed from: c */
    public List f229c;

    /* renamed from: d */
    private final PreferenceGroup f230d;

    /* renamed from: e */
    private List f231e;

    /* renamed from: f */
    private final List f232f;

    /* renamed from: g */
    private final Handler f233g;

    /* renamed from: h */
    private final Runnable f234h = new ado(this);

    public adr(PreferenceGroup preferenceGroup) {
        this.f230d = preferenceGroup;
        this.f233g = new Handler();
        this.f230d.f1088A = this;
        this.f231e = new ArrayList();
        this.f229c = new ArrayList();
        this.f232f = new ArrayList();
        PreferenceGroup preferenceGroup2 = this.f230d;
        if (preferenceGroup2 instanceof PreferenceScreen) {
            mo10536a(((PreferenceScreen) preferenceGroup2).f1134d);
        } else {
            mo10536a(true);
        }
        mo225c();
    }

    /* renamed from: b */
    private static final boolean m255b(PreferenceGroup preferenceGroup) {
        return preferenceGroup.f1130c != Integer.MAX_VALUE;
    }

    /* renamed from: a */
    private final List m253a(PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int g = preferenceGroup.mo1203g();
        int i = 0;
        for (int i2 = 0; i2 < g; i2++) {
            Preference g2 = preferenceGroup.mo1204g(i2);
            if (g2.f1124w) {
                if (!m255b(preferenceGroup) || i < preferenceGroup.f1130c) {
                    arrayList.add(g2);
                } else {
                    arrayList2.add(g2);
                }
                if (g2 instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) g2;
                    if (!preferenceGroup2.mo1205r()) {
                        continue;
                    } else if (!m255b(preferenceGroup) || !m255b(preferenceGroup2)) {
                        List a = m253a(preferenceGroup2);
                        int size = a.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            Preference preference = (Preference) a.get(i3);
                            if (m255b(preferenceGroup) && i >= preferenceGroup.f1130c) {
                                arrayList2.add(preference);
                            } else {
                                arrayList.add(preference);
                            }
                            i++;
                        }
                    } else {
                        throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                    }
                } else {
                    i++;
                }
            }
        }
        if (m255b(preferenceGroup) && i > preferenceGroup.f1130c) {
            acl acl = new acl(preferenceGroup.f1111j, arrayList2, preferenceGroup.mo170e());
            acl.f1116o = new adp(this, preferenceGroup);
            arrayList.add(acl);
        }
        return arrayList;
    }

    /* renamed from: a */
    private final void m254a(List list, PreferenceGroup preferenceGroup) {
        synchronized (preferenceGroup) {
            Collections.sort(preferenceGroup.f1129b);
        }
        int g = preferenceGroup.mo1203g();
        for (int i = 0; i < g; i++) {
            Preference g2 = preferenceGroup.mo1204g(i);
            list.add(g2);
            adq adq = new adq(g2);
            if (!this.f232f.contains(adq)) {
                this.f232f.add(adq);
            }
            if (g2 instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) g2;
                if (preferenceGroup2.mo1205r()) {
                    m254a(list, preferenceGroup2);
                }
            }
            g2.f1088A = this;
        }
    }

    /* renamed from: f */
    public final Preference mo226f(int i) {
        if (i < 0 || i >= mo220a()) {
            return null;
        }
        return (Preference) this.f229c.get(i);
    }

    /* renamed from: a */
    public final int mo220a() {
        return this.f229c.size();
    }

    /* renamed from: b */
    public final long mo224b(int i) {
        if (this.f16288b) {
            return mo226f(i).mo170e();
        }
        return -1;
    }

    /* renamed from: a */
    public final int mo221a(int i) {
        adq adq = new adq(mo226f(i));
        int indexOf = this.f232f.indexOf(adq);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.f232f.size();
        this.f232f.add(adq);
        return size;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo223a(C0667ym ymVar, int i) {
        mo226f(i).mo169a((ady) ymVar);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C0667ym mo222a(ViewGroup viewGroup, int i) {
        adq adq = (adq) this.f232f.get(i);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, adz.f250a);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            drawable = C0436py.m15105b(viewGroup.getContext(), 17301602);
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(adq.f226a, viewGroup, false);
        if (inflate.getBackground() == null) {
            C0340mj.m14694a(inflate, drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(16908312);
        if (viewGroup2 != null) {
            int i2 = adq.f227b;
            if (i2 != 0) {
                from.inflate(i2, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new ady(inflate);
    }

    /* renamed from: b */
    public final void mo183b() {
        this.f233g.removeCallbacks(this.f234h);
        this.f233g.post(this.f234h);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo225c() {
        List list = this.f231e;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((Preference) list.get(i)).f1088A = null;
        }
        ArrayList arrayList = new ArrayList(this.f231e.size());
        this.f231e = arrayList;
        m254a((List) arrayList, this.f230d);
        this.f229c = m253a(this.f230d);
        mo10540d();
        List list2 = this.f231e;
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Preference preference = (Preference) list2.get(i2);
        }
    }
}
