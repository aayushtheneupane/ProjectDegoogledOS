package p000;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.IOException;
import java.util.concurrent.Callable;
import p003j$.util.Optional;

/* renamed from: dbb */
/* compiled from: PG */
final /* synthetic */ class dbb implements Callable {

    /* renamed from: a */
    private final cjn f6168a;

    /* renamed from: b */
    private final boolean f6169b;

    /* renamed from: c */
    private final ContentResolver f6170c;

    /* renamed from: d */
    private final dbo f6171d;

    /* renamed from: e */
    private final Optional f6172e;

    public dbb(cjn cjn, boolean z, ContentResolver contentResolver, dbo dbo, Optional optional) {
        this.f6168a = cjn;
        this.f6169b = z;
        this.f6170c = contentResolver;
        this.f6171d = dbo;
        this.f6172e = optional;
    }

    public final Object call() {
        String str;
        Uri uri;
        cjn cjn = this.f6168a;
        boolean z = this.f6169b;
        ContentResolver contentResolver = this.f6170c;
        dbo dbo = this.f6171d;
        Optional optional = this.f6172e;
        if (!z) {
            if (!cjn.f4504b.equals("emulated")) {
                str = cjn.f4504b;
                int length = str.length();
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (!ife.m12880b(str.charAt(i))) {
                        i++;
                    } else {
                        char[] charArray = str.toCharArray();
                        while (i < length) {
                            char c = charArray[i];
                            if (ife.m12880b(c)) {
                                charArray[i] = (char) (c ^ ' ');
                            }
                            i++;
                        }
                        str = String.valueOf(charArray);
                    }
                }
            } else {
                str = "external_primary";
            }
            String b = dbo.mo3205b();
            if (dgt.m6097b(b)) {
                uri = MediaStore.Images.Media.getContentUri(str);
            } else if (dgt.m6098c(b)) {
                uri = MediaStore.Video.Media.getContentUri(str);
            } else {
                throw new IllegalArgumentException("Media is not an image or video.");
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("relative_path", cjn.f4505c);
            contentValues.put("_display_name", cjn.f4506d);
            contentValues.put("is_pending", 1);
            Uri insert = contentResolver.insert(uri, contentValues);
            if (insert != null) {
                return Optional.m16285of(insert);
            }
            throw new IOException("Failed to insert media into MediaStore.");
        } else if (!optional.isPresent()) {
            return Optional.empty();
        } else {
            cyd cyd = (cyd) optional.get();
            long j = cyd.f5989c;
            cxh a = cxh.m5592a(cyd.f5992f);
            if (a == null) {
                a = cxh.UNKNOWN_MEDIA_TYPE;
            }
            return Optional.m16285of(cyc.m5647b(a, j));
        }
    }
}
