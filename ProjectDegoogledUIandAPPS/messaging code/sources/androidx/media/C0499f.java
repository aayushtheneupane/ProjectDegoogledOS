package androidx.media;

/* renamed from: androidx.media.f */
class C0499f implements C0495b {
    private int mContentType = 0;
    private int mFlags = 0;
    private int mUsage = 0;

    /* renamed from: vq */
    private int f481vq = -1;

    C0499f() {
    }

    public C0496c build() {
        return new AudioAttributesImplBase(this.mContentType, this.mFlags, this.mUsage, this.f481vq);
    }

    public C0495b setLegacyStreamType(int i) {
        if (i != 10) {
            this.f481vq = i;
            return this;
        }
        throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
    }
}
