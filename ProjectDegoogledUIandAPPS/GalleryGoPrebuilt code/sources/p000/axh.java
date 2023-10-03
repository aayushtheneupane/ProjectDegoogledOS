package p000;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;

/* renamed from: axh */
/* compiled from: PG */
final class axh implements ari {

    /* renamed from: a */
    private static final String[] f1822a = {"_data"};

    /* renamed from: b */
    private final Context f1823b;

    /* renamed from: c */
    private final Uri f1824c;

    /* renamed from: a */
    public final Class mo1510a() {
        return File.class;
    }

    /* renamed from: b */
    public final void mo1516b() {
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    public axh(Context context, Uri uri) {
        this.f1823b = context;
        this.f1824c = uri;
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        Cursor query = this.f1823b.getContentResolver().query(this.f1824c, f1822a, (String) null, (String[]) null, (String) null);
        String str = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    str = query.getString(query.getColumnIndexOrThrow("_data"));
                }
            } finally {
                query.close();
            }
        }
        if (TextUtils.isEmpty(str)) {
            String valueOf = String.valueOf(this.f1824c);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
            sb.append("Failed to find file path for: ");
            sb.append(valueOf);
            arh.mo1524a((Exception) new FileNotFoundException(sb.toString()));
            return;
        }
        arh.mo1525a((Object) new File(str));
    }
}
