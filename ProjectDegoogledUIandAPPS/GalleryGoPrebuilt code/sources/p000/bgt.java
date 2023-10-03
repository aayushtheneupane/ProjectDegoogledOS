package p000;

import android.app.ActivityManager;
import com.davemorrissey.labs.subscaleview.decoder.SkiaPooledImageRegionDecoder;

/* renamed from: bgt */
/* compiled from: PG */
public final class bgt extends Thread {

    /* renamed from: a */
    private final /* synthetic */ SkiaPooledImageRegionDecoder f2352a;

    public bgt(SkiaPooledImageRegionDecoder skiaPooledImageRegionDecoder) {
        this.f2352a = skiaPooledImageRegionDecoder;
    }

    public final void run() {
        while (true) {
            SkiaPooledImageRegionDecoder skiaPooledImageRegionDecoder = this.f2352a;
            int i = SkiaPooledImageRegionDecoder.f4786d;
            bgu bgu = skiaPooledImageRegionDecoder.f4787a;
            if (bgu != null) {
                int b = bgu.mo2023b();
                long j = this.f2352a.f4789c;
                if (b < 4) {
                    long j2 = ((long) b) * j;
                    if (j2 > 20971520) {
                        return;
                    }
                    if (b < skiaPooledImageRegionDecoder.mo3309d()) {
                        ActivityManager activityManager = (ActivityManager) skiaPooledImageRegionDecoder.f4788b.getSystemService("activity");
                        if (activityManager != null) {
                            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                            activityManager.getMemoryInfo(memoryInfo);
                            if (!memoryInfo.lowMemory) {
                                StringBuilder sb = new StringBuilder(104);
                                sb.append("Additional decoder allowed, current count is ");
                                sb.append(b);
                                sb.append(", estimated native memory ");
                                sb.append(j2 / 1048576);
                                sb.append("Mb");
                                sb.toString();
                                try {
                                    if (this.f2352a.f4787a != null) {
                                        long currentTimeMillis = System.currentTimeMillis();
                                        this.f2352a.mo3308c();
                                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                                        StringBuilder sb2 = new StringBuilder(44);
                                        sb2.append("Started decoder, took ");
                                        sb2.append(currentTimeMillis2);
                                        sb2.append("ms");
                                        sb2.toString();
                                    }
                                } catch (Exception e) {
                                    String valueOf = String.valueOf(e.getMessage());
                                    if (valueOf.length() != 0) {
                                        "Failed to start decoder: ".concat(valueOf);
                                    } else {
                                        new String("Failed to start decoder: ");
                                    }
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        int d = skiaPooledImageRegionDecoder.mo3309d();
                        StringBuilder sb3 = new StringBuilder(66);
                        sb3.append("No additional encoders allowed, limited by CPU cores (");
                        sb3.append(d);
                        sb3.append(")");
                        sb3.toString();
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
