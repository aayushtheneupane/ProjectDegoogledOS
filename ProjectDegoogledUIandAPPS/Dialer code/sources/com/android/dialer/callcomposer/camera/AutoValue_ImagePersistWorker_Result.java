package com.android.dialer.callcomposer.camera;

import android.net.Uri;
import com.android.dialer.callcomposer.camera.ImagePersistWorker;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_ImagePersistWorker_Result extends ImagePersistWorker.Result {
    private final int height;
    private final Uri uri;
    private final int width;

    static final class Builder extends ImagePersistWorker.Result.Builder {
        private Integer height;
        private Uri uri;
        private Integer width;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public ImagePersistWorker.Result build() {
            String str = "";
            if (this.uri == null) {
                str = GeneratedOutlineSupport.outline8(str, " uri");
            }
            if (this.width == null) {
                str = GeneratedOutlineSupport.outline8(str, " width");
            }
            if (this.height == null) {
                str = GeneratedOutlineSupport.outline8(str, " height");
            }
            if (str.isEmpty()) {
                return new AutoValue_ImagePersistWorker_Result(this.uri, this.width.intValue(), this.height.intValue(), (C03991) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public ImagePersistWorker.Result.Builder setHeight(int i) {
            this.height = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ImagePersistWorker.Result.Builder setUri(Uri uri2) {
            if (uri2 != null) {
                this.uri = uri2;
                return this;
            }
            throw new NullPointerException("Null uri");
        }

        /* access modifiers changed from: package-private */
        public ImagePersistWorker.Result.Builder setWidth(int i) {
            this.width = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_ImagePersistWorker_Result(Uri uri2, int i, int i2, C03991 r4) {
        this.uri = uri2;
        this.width = i;
        this.height = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImagePersistWorker.Result)) {
            return false;
        }
        ImagePersistWorker.Result result = (ImagePersistWorker.Result) obj;
        if (this.uri.equals(result.getUri()) && this.width == result.getWidth() && this.height == result.getHeight()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return this.height;
    }

    /* access modifiers changed from: package-private */
    public Uri getUri() {
        return this.uri;
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return this.height ^ ((((this.uri.hashCode() ^ 1000003) * 1000003) ^ this.width) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Result{uri=");
        outline13.append(this.uri);
        outline13.append(", width=");
        outline13.append(this.width);
        outline13.append(", height=");
        outline13.append(this.height);
        outline13.append("}");
        return outline13.toString();
    }
}
