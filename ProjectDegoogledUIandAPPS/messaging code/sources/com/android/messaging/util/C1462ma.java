package com.android.messaging.util;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import java.lang.Thread;
import java.util.LinkedList;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.ma */
public class C1462ma implements MediaPlayer.OnCompletionListener {

    /* renamed from: AK */
    private C1460la f2317AK;

    /* renamed from: BK */
    private final Object f2318BK = new Object();
    /* access modifiers changed from: private */

    /* renamed from: DK */
    public MediaPlayer f2319DK;

    /* renamed from: EK */
    private PowerManager.WakeLock f2320EK;
    /* access modifiers changed from: private */
    public AudioManager mAudioManager;
    /* access modifiers changed from: private */
    public Looper mLooper;
    private int mState = 2;
    /* access modifiers changed from: private */
    public String mTag;
    /* access modifiers changed from: private */
    public C1456ja mThread;
    /* access modifiers changed from: private */

    /* renamed from: zK */
    public final LinkedList f2321zK = new LinkedList();

    public C1462ma(String str) {
        if (str != null) {
            this.mTag = str;
        } else {
            this.mTag = "NotificationPlayer";
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m3748d(C1462ma maVar) {
        PowerManager.WakeLock wakeLock = maVar.f2320EK;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
        synchronized (this.f2321zK) {
            if (this.f2321zK.size() == 0) {
                synchronized (this.f2318BK) {
                    if (this.mLooper != null) {
                        this.mLooper.quit();
                    }
                    this.f2317AK = null;
                }
            }
        }
    }

    public void stop(boolean z) {
        synchronized (this.f2321zK) {
            if (this.mState != 2) {
                C1458ka kaVar = new C1458ka((C1454ia) null);
                kaVar.requestTime = SystemClock.elapsedRealtime();
                kaVar.code = 2;
                kaVar.f2311yK = z;
                m3742a(kaVar);
                this.mState = 2;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m3745b(C1458ka kaVar) {
        try {
            synchronized (this.f2318BK) {
                if (!(this.mLooper == null || this.mLooper.getThread().getState() == Thread.State.TERMINATED)) {
                    this.mLooper.quit();
                }
                this.f2317AK = new C1460la(this, kaVar);
                synchronized (this.f2317AK) {
                    this.f2317AK.start();
                    this.f2317AK.wait();
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - kaVar.requestTime;
            if (elapsedRealtime > 1000) {
                String str = this.mTag;
                C1430e.m3630w(str, "Notification sound delayed by " + elapsedRealtime + "msecs");
            }
        } catch (Exception e) {
            String str2 = this.mTag;
            StringBuilder Pa = C0632a.m1011Pa("error loading sound for ");
            Pa.append(kaVar.uri);
            C1430e.m3631w(str2, Pa.toString(), e);
        }
    }

    /* renamed from: a */
    public void mo8194a(Uri uri, boolean z, int i, float f) {
        C1458ka kaVar = new C1458ka((C1454ia) null);
        kaVar.requestTime = SystemClock.elapsedRealtime();
        kaVar.code = 1;
        kaVar.uri = uri;
        kaVar.looping = z;
        kaVar.f2310xK = i;
        kaVar.volume = f;
        synchronized (this.f2321zK) {
            m3742a(kaVar);
            this.mState = 1;
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m3746b(C1462ma maVar, C1458ka kaVar) {
        AudioManager audioManager;
        if (maVar.f2319DK != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - kaVar.requestTime;
            if (elapsedRealtime > 1000) {
                String str = maVar.mTag;
                C1430e.m3630w(str, "Notification stop delayed by " + elapsedRealtime + "msecs");
            }
            maVar.f2319DK.stop();
            maVar.f2319DK.release();
            maVar.f2319DK = null;
            if (kaVar.f2311yK && (audioManager = maVar.mAudioManager) != null) {
                audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
            }
            maVar.mAudioManager = null;
            Looper looper = maVar.mLooper;
            if (looper != null && looper.getThread().getState() != Thread.State.TERMINATED) {
                maVar.mLooper.quit();
            }
        }
    }

    /* renamed from: a */
    private void m3742a(C1458ka kaVar) {
        this.f2321zK.add(kaVar);
        if (this.mThread == null) {
            PowerManager.WakeLock wakeLock = this.f2320EK;
            if (wakeLock != null) {
                wakeLock.acquire();
            }
            this.mThread = new C1456ja(this);
            this.mThread.start();
        }
    }
}
