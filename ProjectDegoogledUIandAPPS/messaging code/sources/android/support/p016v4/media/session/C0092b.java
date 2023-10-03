package android.support.p016v4.media.session;

import android.os.Bundle;
import android.os.IInterface;
import android.support.p016v4.media.MediaMetadataCompat;
import java.util.List;

/* renamed from: android.support.v4.media.session.b */
public interface C0092b extends IInterface {
    /* renamed from: a */
    void mo544a(MediaMetadataCompat mediaMetadataCompat);

    /* renamed from: a */
    void mo545a(ParcelableVolumeInfo parcelableVolumeInfo);

    void onExtrasChanged(Bundle bundle);

    void onQueueChanged(List list);

    void onQueueTitleChanged(CharSequence charSequence);

    void onSessionDestroyed();
}
