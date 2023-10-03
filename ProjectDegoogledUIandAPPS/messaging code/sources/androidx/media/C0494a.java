package androidx.media;

import android.os.Build;

/* renamed from: androidx.media.a */
public class C0494a {

    /* renamed from: rq */
    final C0495b f479rq;

    public C0494a() {
        if (AudioAttributesCompat.f459sq) {
            this.f479rq = new C0499f();
            return;
        }
        int i = Build.VERSION.SDK_INT;
        this.f479rq = new C0498e();
    }

    public AudioAttributesCompat build() {
        return new AudioAttributesCompat(this.f479rq.build());
    }

    public C0494a setLegacyStreamType(int i) {
        this.f479rq.setLegacyStreamType(i);
        return this;
    }
}
