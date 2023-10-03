package p000;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.apps.photosgo.R;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: fsw */
/* compiled from: PG */
public final class fsw extends C0147fh implements C0204hi {

    /* renamed from: a */
    public ArrayAdapter f10549a;

    /* renamed from: b */
    public fsv f10550b;

    /* renamed from: a */
    public final void mo1832a(Context context) {
        super.mo1832a(context);
        C0147fh fhVar = this.f9607z;
        if (fhVar instanceof fsv) {
            this.f10550b = (fsv) fhVar;
            return;
        }
        C0149fj m = mo5653m();
        if (m instanceof fsv) {
            this.f10550b = (fsv) m;
        }
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.libraries_social_licenses_license_menu_fragment, viewGroup, false);
    }

    /* renamed from: x */
    public final void mo1836x() {
        super.mo1836x();
        C0205hj a = C0205hj.m11569a(mo5653m());
        C0210ho hoVar = (C0210ho) a;
        if (hoVar.f13136b.f13071d) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            if (C0210ho.m11828a(2)) {
                "destroyLoader in " + a + " of 54321";
            }
            C0206hk c = hoVar.f13136b.mo7598c();
            if (c != null) {
                c.mo7503d();
                hoVar.f13136b.f13070c.mo9339b(54321);
            }
        } else {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
    }

    /* renamed from: c */
    public final void mo1834c() {
        super.mo1834c();
        this.f10550b = null;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final void mo2632a(View view, Bundle bundle) {
        C0149fj m = mo5653m();
        this.f10549a = new ArrayAdapter(m, R.layout.libraries_social_licenses_license, R.id.license, new ArrayList());
        C0205hj a = C0205hj.m11569a(m);
        C0210ho hoVar = (C0210ho) a;
        if (hoVar.f13136b.f13071d) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            C0206hk c = hoVar.f13136b.mo7598c();
            if (C0210ho.m11828a(2)) {
                "initLoader in " + a + ": args=" + null;
            }
            if (c == null) {
                try {
                    ((C0210ho) a).f13136b.f13071d = true;
                    fst fst = new fst(mo5653m());
                    if (fst.getClass().isMemberClass()) {
                        if (!Modifier.isStatic(fst.getClass().getModifiers())) {
                            throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + fst);
                        }
                    }
                    C0206hk hkVar = new C0206hk(fst);
                    if (C0210ho.m11828a(3)) {
                        "  Created new loader " + hkVar;
                    }
                    ((C0210ho) a).f13136b.f13070c.mo9337a(54321, hkVar);
                    hoVar.f13136b.mo7597b();
                    hkVar.mo7501a(hoVar.f13135a, this);
                } catch (Throwable th) {
                    hoVar.f13136b.mo7597b();
                    throw th;
                }
            } else {
                if (C0210ho.m11828a(3)) {
                    "  Re-using existing loader " + c;
                }
                c.mo7501a(hoVar.f13135a, this);
            }
            ListView listView = (ListView) view.findViewById(R.id.license_list);
            listView.setAdapter(this.f10549a);
            listView.setOnItemClickListener(new fsu(this));
        } else {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
    }
}
