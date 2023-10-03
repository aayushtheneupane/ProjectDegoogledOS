package com.android.messaging.p041ui.mediapicker;

import android.media.MediaRecorder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.ca */
public class C1303ca {

    /* renamed from: Rd */
    private Uri f2058Rd;
    /* access modifiers changed from: private */

    /* renamed from: Tk */
    public final C1298a f2059Tk = new C1298a();

    /* renamed from: nI */
    private Thread f2060nI;
    /* access modifiers changed from: private */

    /* renamed from: oI */
    public MediaRecorder f2061oI;

    /* renamed from: pI */
    private ParcelFileDescriptor f2062pI;

    /* renamed from: Ho */
    private void m3302Ho() {
        Thread thread = this.f2060nI;
        if (thread != null && thread.isAlive()) {
            this.f2060nI.interrupt();
            this.f2060nI = null;
        }
    }

    /* access modifiers changed from: private */
    public int getAmplitude() {
        synchronized (C1303ca.class) {
            if (this.f2061oI == null) {
                return 0;
            }
            int min = Math.min(this.f2061oI.getMaxAmplitude() / 327, 100);
            return min;
        }
    }

    /* renamed from: qj */
    public boolean mo7777qj() {
        return this.f2061oI != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        r0 = r6.f2062pI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        if (r0 == null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri stopRecording() {
        /*
            r6 = this;
            java.lang.Class<com.android.messaging.ui.mediapicker.ca> r0 = com.android.messaging.p041ui.mediapicker.C1303ca.class
            monitor-enter(r0)
            android.media.MediaRecorder r1 = r6.f2061oI     // Catch:{ all -> 0x006e }
            r2 = 0
            if (r1 == 0) goto L_0x0067
            android.media.MediaRecorder r1 = r6.f2061oI     // Catch:{ RuntimeException -> 0x0015 }
            r1.stop()     // Catch:{ RuntimeException -> 0x0015 }
            android.media.MediaRecorder r1 = r6.f2061oI     // Catch:{ all -> 0x006e }
        L_0x000f:
            r1.release()     // Catch:{ all -> 0x006e }
            goto L_0x003f
        L_0x0013:
            r1 = move-exception
            goto L_0x005f
        L_0x0015:
            r1 = move-exception
            java.lang.String r3 = "MessagingApp"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0013 }
            r4.<init>()     // Catch:{ all -> 0x0013 }
            java.lang.String r5 = "Something went wrong when stopping media recorder. "
            r4.append(r5)     // Catch:{ all -> 0x0013 }
            r4.append(r1)     // Catch:{ all -> 0x0013 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0013 }
            com.android.messaging.util.C1430e.m3630w(r3, r1)     // Catch:{ all -> 0x0013 }
            android.net.Uri r1 = r6.f2058Rd     // Catch:{ all -> 0x0013 }
            if (r1 == 0) goto L_0x003c
            android.net.Uri r1 = r6.f2058Rd     // Catch:{ all -> 0x0013 }
            com.android.messaging.ui.mediapicker.aa r3 = new com.android.messaging.ui.mediapicker.aa     // Catch:{ all -> 0x0013 }
            r3.<init>(r6, r1)     // Catch:{ all -> 0x0013 }
            com.android.messaging.util.C1478ua.m3823a((java.lang.Runnable) r3)     // Catch:{ all -> 0x0013 }
            r6.f2058Rd = r2     // Catch:{ all -> 0x0013 }
        L_0x003c:
            android.media.MediaRecorder r1 = r6.f2061oI     // Catch:{ all -> 0x006e }
            goto L_0x000f
        L_0x003f:
            r6.f2061oI = r2     // Catch:{ all -> 0x006e }
            monitor-exit(r0)     // Catch:{ all -> 0x006e }
            android.os.ParcelFileDescriptor r0 = r6.f2062pI
            if (r0 == 0) goto L_0x004b
            r0.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            r6.f2062pI = r2
        L_0x004b:
            java.lang.Thread r0 = r6.f2060nI
            if (r0 == 0) goto L_0x005c
            boolean r0 = r0.isAlive()
            if (r0 == 0) goto L_0x005c
            java.lang.Thread r0 = r6.f2060nI
            r0.interrupt()
            r6.f2060nI = r2
        L_0x005c:
            android.net.Uri r6 = r6.f2058Rd
            return r6
        L_0x005f:
            android.media.MediaRecorder r3 = r6.f2061oI     // Catch:{ all -> 0x006e }
            r3.release()     // Catch:{ all -> 0x006e }
            r6.f2061oI = r2     // Catch:{ all -> 0x006e }
            throw r1     // Catch:{ all -> 0x006e }
        L_0x0067:
            java.lang.String r6 = "Not currently recording!"
            com.android.messaging.util.C1424b.fail(r6)     // Catch:{ all -> 0x006e }
            monitor-exit(r0)     // Catch:{ all -> 0x006e }
            return r2
        L_0x006e:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1303ca.stopRecording():android.net.Uri");
    }

    /* renamed from: xj */
    public C1298a mo7779xj() {
        return this.f2059Tk;
    }

    /* renamed from: a */
    public boolean mo7776a(MediaRecorder.OnErrorListener onErrorListener, MediaRecorder.OnInfoListener onInfoListener, int i) {
        synchronized (C1303ca.class) {
            if (this.f2061oI == null) {
                this.f2058Rd = MediaScratchFileProvider.m1259k("3gp");
                this.f2061oI = new MediaRecorder();
                int i2 = (int) (((float) i) * 0.8f);
                try {
                    this.f2062pI = C0967f.get().getApplicationContext().getContentResolver().openFileDescriptor(this.f2058Rd, "w");
                    this.f2061oI.setAudioSource(1);
                    this.f2061oI.setOutputFormat(1);
                    this.f2061oI.setAudioEncoder(1);
                    this.f2061oI.setOutputFile(this.f2062pI.getFileDescriptor());
                    this.f2061oI.setMaxFileSize((long) i2);
                    this.f2061oI.setOnErrorListener(onErrorListener);
                    this.f2061oI.setOnInfoListener(onInfoListener);
                    this.f2061oI.prepare();
                    this.f2061oI.start();
                    m3302Ho();
                    this.f2060nI = new C1301ba(this);
                    this.f2060nI.start();
                    return true;
                } catch (Exception e) {
                    C1430e.m3622e("MessagingApp", "Something went wrong when starting media recorder. " + e);
                    C1486ya.m3848Pa(R.string.audio_recording_start_failed);
                    stopRecording();
                }
            } else {
                C1424b.fail("Trying to start a new recording session while already recording!");
                return false;
            }
        }
    }
}
