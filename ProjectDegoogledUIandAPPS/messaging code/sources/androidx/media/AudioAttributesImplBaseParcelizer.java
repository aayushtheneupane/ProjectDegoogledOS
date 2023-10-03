package androidx.media;

import androidx.versionedparcelable.C0613c;

public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(C0613c cVar) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.mUsage = cVar.readInt(audioAttributesImplBase.mUsage, 1);
        audioAttributesImplBase.mContentType = cVar.readInt(audioAttributesImplBase.mContentType, 2);
        audioAttributesImplBase.mFlags = cVar.readInt(audioAttributesImplBase.mFlags, 3);
        audioAttributesImplBase.f461vq = cVar.readInt(audioAttributesImplBase.f461vq, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, C0613c cVar) {
        cVar.mo5311h(false, false);
        cVar.mo5314q(audioAttributesImplBase.mUsage, 1);
        cVar.mo5314q(audioAttributesImplBase.mContentType, 2);
        cVar.mo5314q(audioAttributesImplBase.mFlags, 3);
        cVar.mo5314q(audioAttributesImplBase.f461vq, 4);
    }
}
