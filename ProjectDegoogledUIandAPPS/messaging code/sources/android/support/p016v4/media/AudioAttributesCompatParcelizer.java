package android.support.p016v4.media;

import androidx.media.AudioAttributesCompat;
import androidx.versionedparcelable.C0613c;
import androidx.versionedparcelable.C0615e;

/* renamed from: android.support.v4.media.AudioAttributesCompatParcelizer */
public final class AudioAttributesCompatParcelizer extends androidx.media.AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(C0613c cVar) {
        return androidx.media.AudioAttributesCompatParcelizer.read(cVar);
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, C0613c cVar) {
        cVar.mo5311h(false, false);
        cVar.mo5304b((C0615e) audioAttributesCompat.mImpl, 1);
    }
}
