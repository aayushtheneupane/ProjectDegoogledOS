package p000;

import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;

/* renamed from: bbe */
/* compiled from: PG */
public final class bbe implements bbd {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1776a(MediaMetadataRetriever mediaMetadataRetriever, Object obj) {
        mediaMetadataRetriever.setDataSource(((ParcelFileDescriptor) obj).getFileDescriptor());
    }
}
