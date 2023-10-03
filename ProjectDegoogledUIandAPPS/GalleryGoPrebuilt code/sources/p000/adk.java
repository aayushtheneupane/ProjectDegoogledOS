package p000;

import android.os.Bundle;
import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.google.android.apps.photosgo.R;

/* renamed from: adk */
/* compiled from: PG */
public abstract class adk extends C0147fh implements adu, ads, adt, acf {

    /* renamed from: Z */
    public boolean f213Z;

    /* renamed from: a */
    public final adg f214a = new adg(this);

    /* renamed from: aa */
    public int f215aa = R.layout.preference_list_fragment;

    /* renamed from: ab */
    public final Handler f216ab = new ade(this);

    /* renamed from: ac */
    public final Runnable f217ac = new adf(this);

    /* renamed from: b */
    public adv f218b;

    /* renamed from: c */
    public RecyclerView f219c;

    /* renamed from: d */
    public boolean f220d;

    /* renamed from: R */
    public abstract void mo204R();

    /* renamed from: am */
    public final PreferenceScreen mo208am() {
        return this.f218b.f236b;
    }

    /* renamed from: Q */
    public final void mo203Q() {
        PreferenceScreen am = mo208am();
        if (am != null) {
            this.f219c.setAdapter(new adr(am));
            am.mo1194m();
        }
    }

    /* renamed from: a */
    public final Preference mo157a(CharSequence charSequence) {
        adv adv = this.f218b;
        if (adv != null) {
            return adv.mo228a(charSequence);
        }
        return null;
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        TypedValue typedValue = new TypedValue();
        mo5653m().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R.style.PreferenceThemeOverlay;
        }
        mo5653m().getTheme().applyStyle(i, false);
        adv adv = new adv(mo2634k());
        this.f218b = adv;
        adv.f239e = this;
        Bundle bundle2 = this.f9592k;
        if (bundle2 != null) {
            bundle2.getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT");
        }
        mo204R();
    }

    /* renamed from: f */
    public void mo212f() {
        this.f216ab.removeCallbacks(this.f217ac);
        this.f216ab.removeMessages(1);
        if (this.f220d) {
            this.f219c.setAdapter((C0634xg) null);
            PreferenceScreen am = mo208am();
            if (am != null) {
                am.mo1195n();
            }
        }
        this.f219c = null;
        super.mo212f();
    }

    /* renamed from: b */
    public final void mo209b(Preference preference) {
        C0140fa faVar;
        if ((!(mo5653m() instanceof adh) || !((adh) mo5653m()).mo200a()) && this.f9604w.mo6418a("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                String str = preference.f1119r;
                faVar = new ack();
                Bundle bundle = new Bundle(1);
                bundle.putString("key", str);
                faVar.mo5646f(bundle);
            } else if (preference instanceof ListPreference) {
                String str2 = preference.f1119r;
                faVar = new acq();
                Bundle bundle2 = new Bundle(1);
                bundle2.putString("key", str2);
                faVar.mo5646f(bundle2);
            } else if (preference instanceof MultiSelectListPreference) {
                String str3 = preference.f1119r;
                faVar = new acu();
                Bundle bundle3 = new Bundle(1);
                bundle3.putString("key", str3);
                faVar.mo5646f(bundle3);
            } else {
                throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
            }
            faVar.mo5638a((C0147fh) this);
            faVar.mo5427a(this.f9604w, "androidx.preference.PreferenceFragment.DIALOG");
        }
    }

    /* renamed from: S */
    public final void mo205S() {
        if (mo5653m() instanceof adj) {
            ((adj) mo5653m()).mo202a();
        }
    }

    /* renamed from: a */
    public final boolean mo207a(Preference preference) {
        if (preference.f1121t == null) {
            return false;
        }
        if (!(mo5653m() instanceof adi) || !((adi) mo5653m()).mo201a()) {
            Log.w("PreferenceFragment", "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
            C0171gd d = mo5641aj().mo5851d();
            if (preference.f1122u == null) {
                preference.f1122u = new Bundle();
            }
            Bundle bundle = preference.f1122u;
            C0147fh c = d.mo6455m().mo6175c(mo5641aj().getClassLoader(), preference.f1121t);
            c.mo5646f(bundle);
            c.mo5638a((C0147fh) this);
            C0182gn a = d.mo6419a().mo6849a(((View) this.f9573L.getParent()).getId(), c);
            if (a.f11651j) {
                a.f11650i = true;
                a.f11652k = null;
                a.mo5251b();
            } else {
                throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
            }
        }
        return true;
    }

    /* renamed from: d */
    public void mo210d() {
        super.mo210d();
        adv adv = this.f218b;
        adv.f237c = this;
        adv.f238d = this;
    }

    /* renamed from: e */
    public void mo211e() {
        super.mo211e();
        adv adv = this.f218b;
        adv.f237c = null;
        adv.f238d = null;
    }

    /* renamed from: a */
    public final void mo206a(PreferenceScreen preferenceScreen) {
        adv adv = this.f218b;
        PreferenceScreen preferenceScreen2 = adv.f236b;
        if (preferenceScreen != preferenceScreen2) {
            if (preferenceScreen2 != null) {
                preferenceScreen2.mo1195n();
            }
            adv.f236b = preferenceScreen;
            this.f220d = true;
            if (this.f213Z && !this.f216ab.hasMessages(1)) {
                this.f216ab.obtainMessage(1).sendToTarget();
            }
        }
    }
}
