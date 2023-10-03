package p000;

import android.content.Intent;
import android.net.Uri;
import p003j$.util.function.Supplier;

/* renamed from: bwf */
/* compiled from: PG */
public final /* synthetic */ class bwf implements Supplier {

    /* renamed from: a */
    private final Intent f3763a;

    public bwf(Intent intent) {
        this.f3763a = intent;
    }

    public final Object get() {
        Intent intent = this.f3763a;
        String[] strArr = bwh.f3765a;
        iir g = bul.f3628j.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        bul bul = (bul) g.f14318b;
        bul.f3630a |= 4;
        bul.f3633d = true;
        iir g2 = ceq.f4197g.mo8793g();
        String uri = ((Uri) ife.m12898e((Object) intent.getData())).toString();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        ceq ceq = (ceq) g2.f14318b;
        uri.getClass();
        ceq.f4199a |= 1;
        ceq.f4200b = uri;
        String b = hpz.m11900b(intent.getType());
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        ceq ceq2 = (ceq) g2.f14318b;
        b.getClass();
        ceq2.f4199a |= 2;
        ceq2.f4201c = b;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        bul bul2 = (bul) g.f14318b;
        ceq ceq3 = (ceq) g2.mo8770g();
        ceq3.getClass();
        bul2.f3632c = ceq3;
        bul2.f3631b = 2;
        String a = bwh.m3685a(intent);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        bul bul3 = (bul) g.f14318b;
        a.getClass();
        bul3.f3630a |= 8;
        bul3.f3634e = a;
        if (intent.hasExtra("outputX") && intent.hasExtra("outputY")) {
            int intExtra = intent.getIntExtra("outputX", 0);
            int intExtra2 = intent.getIntExtra("outputY", 0);
            if (intExtra > 0 && intExtra2 > 0) {
                iir g3 = bwy.f3795d.mo8793g();
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                bwy bwy = (bwy) g3.f14318b;
                int i = bwy.f3797a | 1;
                bwy.f3797a = i;
                bwy.f3798b = intExtra;
                bwy.f3797a = 2 | i;
                bwy.f3799c = intExtra2;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                bul bul4 = (bul) g.f14318b;
                bwy bwy2 = (bwy) g3.mo8770g();
                bwy2.getClass();
                bul4.f3635f = bwy2;
                bul4.f3630a |= 16;
                float f = ((float) intExtra) / ((float) intExtra2);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                bul bul5 = (bul) g.f14318b;
                bul5.f3630a |= 32;
                bul5.f3636g = f;
            }
        }
        if (intent.hasExtra("aspectX") && intent.hasExtra("aspectY")) {
            int intExtra3 = intent.getIntExtra("aspectX", 0);
            int intExtra4 = intent.getIntExtra("aspectY", 0);
            if (intExtra3 > 0 && intExtra4 > 0) {
                float f2 = ((float) intExtra3) / ((float) intExtra4);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                bul bul6 = (bul) g.f14318b;
                bul6.f3630a |= 32;
                bul6.f3636g = f2;
            }
        }
        if (ife.m12854a((CharSequence) "com.android.camera.action.CROP", (CharSequence) ((String) ife.m12869b((Object) intent.getAction(), (Object) "Intent action cannot be null")).toLowerCase())) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            bul bul7 = (bul) g.f14318b;
            int i2 = bul7.f3630a | 64;
            bul7.f3630a = i2;
            bul7.f3637h = true;
            bul7.f3630a = i2 | 128;
            bul7.f3638i = false;
        }
        return bvv.m3658a((bul) g.mo8770g());
    }
}
