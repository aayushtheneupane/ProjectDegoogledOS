package androidx.media;

import android.media.AudioAttributes;

/* renamed from: androidx.media.d */
class C0497d implements C0495b {

    /* renamed from: tq */
    final AudioAttributes.Builder f480tq = new AudioAttributes.Builder();

    C0497d() {
    }

    public C0495b setLegacyStreamType(int i) {
        this.f480tq.setLegacyStreamType(i);
        return this;
    }
}
