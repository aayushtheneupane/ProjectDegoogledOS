package p000;

import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;

/* renamed from: bbc */
/* compiled from: PG */
public final class bbc implements bbd {
    private bbc() {
    }

    public /* synthetic */ bbc(byte[] bArr) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1776a(MediaMetadataRetriever mediaMetadataRetriever, Object obj) {
        AssetFileDescriptor assetFileDescriptor = (AssetFileDescriptor) obj;
        mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
    }
}
