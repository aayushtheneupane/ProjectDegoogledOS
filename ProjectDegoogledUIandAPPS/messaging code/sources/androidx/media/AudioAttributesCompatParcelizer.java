package androidx.media;

import androidx.versionedparcelable.C0613c;
import androidx.versionedparcelable.C0615e;

public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(C0613c cVar) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.mImpl = (C0496c) cVar.mo5298a((C0615e) audioAttributesCompat.mImpl, 1);
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, C0613c cVar) {
        cVar.mo5311h(false, false);
        cVar.mo5304b((C0615e) audioAttributesCompat.mImpl, 1);
    }
}
