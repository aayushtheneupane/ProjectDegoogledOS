package androidx.media;

import android.media.AudioAttributes;
import p026b.p027a.p030b.p031a.C0632a;

public class AudioAttributesImplApi21 implements C0496c {
    public AudioAttributes mAudioAttributes;

    /* renamed from: uq */
    public int f460uq = -1;

    AudioAttributesImplApi21() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("AudioAttributesCompat: audioattributes=");
        Pa.append(this.mAudioAttributes);
        return Pa.toString();
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.mAudioAttributes = audioAttributes;
        this.f460uq = i;
    }
}
