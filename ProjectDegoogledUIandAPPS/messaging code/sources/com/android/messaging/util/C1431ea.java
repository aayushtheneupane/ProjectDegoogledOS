package com.android.messaging.util;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import java.io.IOException;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.ea */
public class C1431ea {

    /* renamed from: vK */
    private final MediaMetadataRetriever f2245vK = new MediaMetadataRetriever();

    /* renamed from: H */
    public int mo8058H(int i, int i2) {
        String extractMetadata = this.f2245vK.extractMetadata(i);
        if (TextUtils.isEmpty(extractMetadata)) {
            return i2;
        }
        return Integer.parseInt(extractMetadata);
    }

    public String extractMetadata(int i) {
        return this.f2245vK.extractMetadata(i);
    }

    public Bitmap getFrameAtTime() {
        return this.f2245vK.getFrameAtTime();
    }

    public void release() {
        try {
            this.f2245vK.release();
        } catch (RuntimeException e) {
            C1430e.m3623e("MessagingApp", "MediaMetadataRetriever.release failed", e);
        }
    }

    /* renamed from: v */
    public void mo8063v(Uri uri) {
        AssetFileDescriptor openAssetFileDescriptor = C0967f.get().getApplicationContext().getContentResolver().openAssetFileDescriptor(uri, "r");
        if (openAssetFileDescriptor != null) {
            try {
                this.f2245vK.setDataSource(openAssetFileDescriptor.getFileDescriptor());
                openAssetFileDescriptor.close();
            } catch (RuntimeException e) {
                release();
                throw new IOException(e);
            } catch (Throwable th) {
                openAssetFileDescriptor.close();
                throw th;
            }
        } else {
            throw new IOException(C0632a.m1014a("openAssetFileDescriptor returned null for ", uri));
        }
    }

    public Bitmap getFrameAtTime(long j) {
        return this.f2245vK.getFrameAtTime(j);
    }
}
