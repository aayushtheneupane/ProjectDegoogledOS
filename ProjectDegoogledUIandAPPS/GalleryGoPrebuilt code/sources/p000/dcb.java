package p000;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

/* renamed from: dcb */
/* compiled from: PG */
final /* synthetic */ class dcb implements Runnable {

    /* renamed from: a */
    private final Context f6229a;

    /* renamed from: b */
    private final Uri f6230b;

    /* renamed from: c */
    private final aba f6231c;

    public dcb(Context context, Uri uri, aba aba) {
        this.f6229a = context;
        this.f6230b = uri;
        this.f6231c = aba;
    }

    public final void run() {
        Uri uri;
        Context context = this.f6229a;
        Uri uri2 = this.f6230b;
        aba aba = this.f6231c;
        try {
            uri = MediaStore.getMediaUri(context, uri2);
        } catch (RuntimeException e) {
            uri = null;
        }
        if (uri != null) {
            aba.mo66a((Object) uri);
        }
    }
}
