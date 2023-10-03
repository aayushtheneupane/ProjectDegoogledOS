package p000;

import android.content.Context;
import android.net.Uri;
import android.support.p001v4.content.FileProvider;
import java.io.File;

/* renamed from: cjo */
/* compiled from: PG */
public final class cjo {

    /* renamed from: a */
    private final Context f4507a;

    /* renamed from: b */
    private final String f4508b;

    public cjo(Context context, String str) {
        this.f4507a = context;
        this.f4508b = str;
    }

    /* renamed from: a */
    public final Uri mo3173a(Uri uri) {
        if (Uri.EMPTY.equals(uri) || uri.getPath() == null) {
            return Uri.EMPTY;
        }
        if (!"file".equals(uri.getScheme())) {
            return uri;
        }
        String concat = String.valueOf(this.f4508b).concat(".fileprovider");
        try {
            Context context = this.f4507a;
            return FileProvider.m807a(context, concat).mo8260a(new File((String) ife.m12898e((Object) uri.getPath())));
        } catch (IllegalArgumentException e) {
            return Uri.EMPTY;
        }
    }
}
