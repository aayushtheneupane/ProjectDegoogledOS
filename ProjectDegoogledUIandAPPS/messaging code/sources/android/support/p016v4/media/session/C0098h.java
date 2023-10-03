package android.support.p016v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.p016v4.media.MediaMetadataCompat;
import java.util.List;

/* renamed from: android.support.v4.media.session.h */
public abstract class C0098h implements IBinder.DeathRecipient {

    /* renamed from: te */
    C0092b f113te;

    public C0098h() {
        int i = Build.VERSION.SDK_INT;
        new C0096f(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo566a(int i, Object obj, Bundle bundle) {
    }

    /* renamed from: a */
    public void mo567a(MediaMetadataCompat mediaMetadataCompat) {
    }

    /* renamed from: a */
    public void mo568a(PlaybackStateCompat playbackStateCompat) {
    }

    /* renamed from: a */
    public void mo569a(C0102l lVar) {
    }

    public void binderDied() {
        mo566a(8, (Object) null, (Bundle) null);
    }

    public void onExtrasChanged(Bundle bundle) {
    }

    public void onQueueChanged(List list) {
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
    }

    public void onSessionDestroyed() {
    }

    public void onSessionEvent(String str, Bundle bundle) {
    }
}
