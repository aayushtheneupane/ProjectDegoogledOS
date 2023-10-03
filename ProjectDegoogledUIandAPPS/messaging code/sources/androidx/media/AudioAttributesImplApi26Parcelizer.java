package androidx.media;

import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.versionedparcelable.C0613c;

public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(C0613c cVar) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.mAudioAttributes = (AudioAttributes) cVar.mo5297a((Parcelable) audioAttributesImplApi26.mAudioAttributes, 1);
        audioAttributesImplApi26.f460uq = cVar.readInt(audioAttributesImplApi26.f460uq, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, C0613c cVar) {
        cVar.mo5311h(false, false);
        cVar.writeParcelable(audioAttributesImplApi26.mAudioAttributes, 1);
        cVar.mo5314q(audioAttributesImplApi26.f460uq, 2);
    }
}
