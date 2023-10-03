package p000;

import android.media.MediaScannerConnection;
import android.net.Uri;

/* renamed from: dca */
/* compiled from: PG */
final /* synthetic */ class dca implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a */
    private final aba f6228a;

    public dca(aba aba) {
        this.f6228a = aba;
    }

    public final void onScanCompleted(String str, Uri uri) {
        aba aba = this.f6228a;
        Object[] objArr = {str, uri};
        if (uri == null) {
            uri = Uri.EMPTY;
        }
        aba.mo66a((Object) uri);
    }
}
