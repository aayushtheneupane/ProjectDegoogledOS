package android.support.p016v4.media.session;

import android.os.Bundle;
import android.support.p016v4.media.MediaMetadataCompat;
import androidx.media.C0494a;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: android.support.v4.media.session.g */
class C0097g extends C0091a {
    private final WeakReference mCallback;

    C0097g(C0098h hVar) {
        this.mCallback = new WeakReference(hVar);
    }

    /* renamed from: a */
    public void mo560a(PlaybackStateCompat playbackStateCompat) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(2, playbackStateCompat, (Bundle) null);
        }
    }

    /* renamed from: l */
    public void mo561l(boolean z) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(11, Boolean.valueOf(z), (Bundle) null);
        }
    }

    public void onEvent(String str, Bundle bundle) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(1, str, bundle);
        }
    }

    public void onExtrasChanged(Bundle bundle) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(7, bundle, (Bundle) null);
        }
    }

    public void onQueueChanged(List list) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(5, list, (Bundle) null);
        }
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(6, charSequence, (Bundle) null);
        }
    }

    public void onSessionDestroyed() {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(8, (Object) null, (Bundle) null);
        }
    }

    /* renamed from: q */
    public void mo563q(int i) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(9, Integer.valueOf(i), (Bundle) null);
        }
    }

    /* renamed from: r */
    public void mo564r(int i) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(12, Integer.valueOf(i), (Bundle) null);
        }
    }

    /* renamed from: wb */
    public void mo565wb() {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(13, (Object) null, (Bundle) null);
        }
    }

    /* renamed from: a */
    public void mo544a(MediaMetadataCompat mediaMetadataCompat) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo566a(3, mediaMetadataCompat, (Bundle) null);
        }
    }

    /* renamed from: a */
    public void mo545a(ParcelableVolumeInfo parcelableVolumeInfo) {
        C0102l lVar;
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            if (parcelableVolumeInfo != null) {
                int i = parcelableVolumeInfo.f105Ae;
                int i2 = parcelableVolumeInfo.f106Be;
                int i3 = parcelableVolumeInfo.f107Ce;
                int i4 = parcelableVolumeInfo.f108De;
                int i5 = parcelableVolumeInfo.f109Ee;
                C0494a aVar = new C0494a();
                aVar.setLegacyStreamType(i2);
                lVar = new C0102l(i, aVar.build(), i3, i4, i5);
            } else {
                lVar = null;
            }
            hVar.mo566a(4, lVar, (Bundle) null);
        }
    }
}
