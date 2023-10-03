package com.bumptech.glide.load;

/* compiled from: PG */
public enum ImageHeaderParser$ImageType {
    GIF(true),
    JPEG(false),
    RAW(false),
    PNG_A(true),
    PNG(false),
    WEBP_A(true),
    WEBP(false),
    UNKNOWN(false);
    

    /* renamed from: a */
    private final boolean f4781a;

    public boolean hasAlpha() {
        return this.f4781a;
    }

    private ImageHeaderParser$ImageType(boolean z) {
        this.f4781a = z;
    }
}
