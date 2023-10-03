package androidx.media;

import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.versionedparcelable.C0613c;

public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(C0613c cVar) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.mAudioAttributes = (AudioAttributes) cVar.mo5297a((Parcelable) audioAttributesImplApi21.mAudioAttributes, 1);
        audioAttributesImplApi21.f460uq = cVar.readInt(audioAttributesImplApi21.f460uq, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, C0613c cVar) {
        cVar.mo5311h(false, false);
        cVar.writeParcelable(audioAttributesImplApi21.mAudioAttributes, 1);
        cVar.mo5314q(audioAttributesImplApi21.f460uq, 2);
    }
}
