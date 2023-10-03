package p000;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import p003j$.util.Optional;
import p003j$.util.function.Supplier;

/* renamed from: dkv */
/* compiled from: PG */
public final /* synthetic */ class dkv implements Supplier {

    /* renamed from: a */
    private final dlc f6751a;

    public dkv(dlc dlc) {
        this.f6751a = dlc;
    }

    public final Object get() {
        Uri uri;
        dlc dlc = this.f6751a;
        Intent intent = dlc.f6766a.getIntent();
        Optional ofNullable = Optional.ofNullable(intent.getData());
        Optional ofNullable2 = Optional.ofNullable(intent.getType());
        Optional ofNullable3 = Optional.ofNullable(intent.getAction());
        if (dlc.f6770e.mo3175a()) {
            uri = (Uri) intent.getParcelableExtra("processing_uri_intent_extra");
        } else {
            uri = null;
        }
        Optional ofNullable4 = Optional.ofNullable(uri);
        Object[] objArr = {ofNullable, ofNullable2, ofNullable3, ofNullable4};
        iir g = ceq.f4197g.mo8793g();
        Optional map = ofNullable.map(dkw.f6752a);
        g.getClass();
        map.ifPresent(new dkx(g));
        g.getClass();
        ofNullable2.ifPresent(new dky(g));
        g.getClass();
        ofNullable3.ifPresent(new dkz(g));
        Optional map2 = ofNullable4.map(dla.f6764a);
        g.getClass();
        map2.ifPresent(new dlb(g));
        long[] longArrayExtra = intent.getLongArrayExtra("com.google.android.apps.photos.api.secure_mode_ids");
        if (longArrayExtra != null) {
            for (long j : longArrayExtra) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                ceq ceq = (ceq) g.f14318b;
                if (!ceq.f4203e.mo8521a()) {
                    ceq.f4203e = iix.m13607a(ceq.f4203e);
                }
                ceq.f4203e.mo8805a(j);
            }
        }
        iir g2 = dme.f6823k.mo8793g();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        dme dme = (dme) g2.f14318b;
        ceq ceq2 = (ceq) g.mo8770g();
        ceq2.getClass();
        dme.f6827c = ceq2;
        dme.f6826b = 2;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        dme dme2 = (dme) g2.f14318b;
        dme2.f6825a |= 128;
        dme2.f6833i = true;
        if ("com.android.camera.action.REVIEW".equals(intent.getAction())) {
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            dme dme3 = (dme) g2.f14318b;
            int i = dme3.f6825a | 16;
            dme3.f6825a = i;
            dme3.f6830f = true;
            dme3.f6825a = i | 256;
            dme3.f6834j = true;
        }
        if (!ofNullable.isPresent() || !fxk.m9827a((Uri) ofNullable.get())) {
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            dme dme4 = (dme) g2.f14318b;
            int i2 = dme4.f6825a | 32;
            dme4.f6825a = i2;
            dme4.f6831g = false;
            dme4.f6825a = i2 | 64;
            dme4.f6832h = false;
        }
        ComponentName callingActivity = dlc.f6766a.getCallingActivity();
        if (callingActivity != null && dlc.f6766a.getPackageName().equals(callingActivity.getPackageName()) && intent.hasExtra("internal_extra_stacked_behavior")) {
            dmd a = dmd.m6351a(intent.getIntExtra("internal_extra_stacked_behavior", dmd.UNKNOWN.f6822e));
            new Object[1][0] = a;
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            dme dme5 = (dme) g2.f14318b;
            dme5.f6829e = a.f6822e;
            dme5.f6825a |= 8;
        }
        return dnn.m6369a((dme) g2.mo8770g());
    }
}
