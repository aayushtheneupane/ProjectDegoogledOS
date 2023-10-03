package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.util.C1478ua;
import java.io.IOException;

/* renamed from: com.android.messaging.ui.mediapicker.wa */
class C1359wa extends MediaRecorder {

    /* renamed from: qd */
    private Uri f2177qd;

    /* renamed from: rd */
    private ParcelFileDescriptor f2178rd;

    /* renamed from: sd */
    private final CamcorderProfile f2179sd;

    public C1359wa(Camera camera, int i, int i2, int i3) {
        String str;
        this.f2179sd = CamcorderProfile.get(i, 0);
        String contentType = getContentType();
        if ("video/mp4".equals(contentType)) {
            str = "mp4";
        } else {
            str = "video/3gpp".equals(contentType) ? "3gp" : "dat";
        }
        this.f2177qd = MediaScratchFileProvider.m1259k(str);
        long j = (long) (((float) i3) * 0.85f);
        CamcorderProfile camcorderProfile = this.f2179sd;
        int i4 = camcorderProfile.audioBitRate;
        int i5 = camcorderProfile.videoBitRate;
        double d = ((double) (8 * j)) / ((double) (i4 + i5));
        if (d < 25.0d) {
            double d2 = d / 25.0d;
            i4 = (int) (((double) i4) * d2);
            i5 = (int) (((double) i5) * d2);
        }
        setCamera(camera);
        setOrientationHint(i2);
        setAudioSource(5);
        setVideoSource(1);
        setOutputFormat(this.f2179sd.fileFormat);
        this.f2178rd = C0967f.get().getApplicationContext().getContentResolver().openFileDescriptor(this.f2177qd, "w");
        setOutputFile(this.f2178rd.getFileDescriptor());
        setAudioEncodingBitRate(i4);
        setAudioChannels(this.f2179sd.audioChannels);
        setAudioEncoder(this.f2179sd.audioCodec);
        setAudioSamplingRate(this.f2179sd.audioSampleRate);
        setVideoEncodingBitRate(i5);
        setVideoEncoder(this.f2179sd.videoCodec);
        setVideoFrameRate(this.f2179sd.videoFrameRate);
        CamcorderProfile camcorderProfile2 = this.f2179sd;
        setVideoSize(camcorderProfile2.videoFrameWidth, camcorderProfile2.videoFrameHeight);
        setMaxFileSize(j);
    }

    /* access modifiers changed from: package-private */
    public String getContentType() {
        return this.f2179sd.fileFormat == 2 ? "video/mp4" : "video/3gpp";
    }

    /* access modifiers changed from: package-private */
    public int getVideoHeight() {
        return this.f2179sd.videoFrameHeight;
    }

    /* access modifiers changed from: package-private */
    public int getVideoWidth() {
        return this.f2179sd.videoFrameWidth;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: sb */
    public void mo7964sb() {
        C1478ua.m3823a((Runnable) new C1357va(this, this.f2177qd));
        this.f2177qd = null;
    }

    /* renamed from: tb */
    public void mo7965tb() {
        ParcelFileDescriptor parcelFileDescriptor = this.f2178rd;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException unused) {
            }
            this.f2178rd = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ub */
    public Uri mo7966ub() {
        return this.f2177qd;
    }
}
