package androidx.media;

import java.util.Arrays;

public class AudioAttributesImplBase implements C0496c {
    public int mContentType = 0;
    public int mFlags = 0;
    public int mUsage = 0;

    /* renamed from: vq */
    public int f461vq = -1;

    AudioAttributesImplBase() {
    }

    /* renamed from: Mc */
    public int mo4537Mc() {
        int i = this.f461vq;
        if (i != -1) {
            return i;
        }
        return AudioAttributesCompat.m504b(false, this.mFlags, this.mUsage);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.mContentType == audioAttributesImplBase.getContentType() && this.mFlags == audioAttributesImplBase.getFlags() && this.mUsage == audioAttributesImplBase.getUsage() && this.f461vq == audioAttributesImplBase.f461vq) {
            return true;
        }
        return false;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public int getFlags() {
        int i = this.mFlags;
        int Mc = mo4537Mc();
        if (Mc == 6) {
            i |= 4;
        } else if (Mc == 7) {
            i |= 1;
        }
        return i & 273;
    }

    public int getUsage() {
        return this.mUsage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.f461vq)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f461vq != -1) {
            sb.append(" stream=");
            sb.append(this.f461vq);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.usageToString(this.mUsage));
        sb.append(" content=");
        sb.append(this.mContentType);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb.toString();
    }

    AudioAttributesImplBase(int i, int i2, int i3, int i4) {
        this.mContentType = i;
        this.mFlags = i2;
        this.mUsage = i3;
        this.f461vq = i4;
    }
}
