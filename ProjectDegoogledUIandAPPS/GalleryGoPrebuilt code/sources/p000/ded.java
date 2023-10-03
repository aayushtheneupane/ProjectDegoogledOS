package p000;

import android.content.Context;
import android.media.MediaScannerConnection;

/* renamed from: ded */
/* compiled from: PG */
public final /* synthetic */ class ded implements dee {

    /* renamed from: a */
    public static final dee f6384a = new ded();

    private ded() {
    }

    /* renamed from: a */
    public final void mo4083a(Context context, String[] strArr, String[] strArr2, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        MediaScannerConnection.scanFile(context, strArr, strArr2, onScanCompletedListener);
    }
}
