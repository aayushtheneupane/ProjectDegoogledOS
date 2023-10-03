package p000;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p003j$.util.Optional;

/* renamed from: dch */
/* compiled from: PG */
final /* synthetic */ class dch implements Callable {

    /* renamed from: a */
    private final dej f6247a;

    /* renamed from: b */
    private final cyd f6248b;

    /* renamed from: c */
    private final Optional f6249c;

    /* renamed from: d */
    private final boolean f6250d;

    /* renamed from: e */
    private final ddl f6251e;

    /* renamed from: f */
    private final double[] f6252f;

    public dch(dej dej, cyd cyd, Optional optional, boolean z, ddl ddl, double[] dArr) {
        this.f6247a = dej;
        this.f6248b = cyd;
        this.f6249c = optional;
        this.f6250d = z;
        this.f6251e = ddl;
        this.f6252f = dArr;
    }

    /* JADX INFO: finally extract failed */
    public final Object call() {
        long j;
        MediaMetadataRetriever mediaMetadataRetriever;
        String extractMetadata;
        String extractMetadata2;
        String extractMetadata3;
        String str;
        dej dej = this.f6247a;
        cyd cyd = this.f6248b;
        Optional optional = this.f6249c;
        boolean z = this.f6250d;
        ddl ddl = this.f6251e;
        double[] dArr = this.f6252f;
        Uri parse = Uri.parse(cyd.f5988b);
        String str2 = (String) optional.get();
        String str3 = cyd.f5993g;
        cxh cxh = cxh.IMAGE;
        cxh a = cxh.m5592a(cyd.f5992f);
        if (a == null) {
            a = cxh.UNKNOWN_MEDIA_TYPE;
        }
        boolean equals = cxh.equals(a);
        if (z) {
            ife.m12845a(fxk.m9827a(parse), (Object) "mediaStoreUri must be a MediaStore Uri");
            long seconds = TimeUnit.MILLISECONDS.toSeconds(dej.f6390b.mo5370a());
            ContentResolver contentResolver = dej.f6389a;
            if (fxk.m9827a(parse)) {
                if (fxk.m9833b(parse)) {
                    str = dek.m6001a("datetaken");
                } else {
                    str = dek.m6001a("datetaken");
                }
                Uri uri = parse;
                deh a2 = deh.m5988a(equals).mo4089c(dej.m5996a(str2)).mo4090d(seconds).mo4084a(fsa.m9481a(contentResolver, parse, str, seconds)).mo4088a(str3);
                if (equals) {
                    ddi ddi = (ddi) ddl;
                    a2.mo4086a(ddi.f6338a, ddi.f6339b);
                } else {
                    MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                    try {
                        mediaMetadataRetriever2.setDataSource(str2);
                        dej.m5997a(a2, mediaMetadataRetriever2);
                        mediaMetadataRetriever2.release();
                        a2.mo4088a("video/mpeg");
                    } catch (Throwable th) {
                        mediaMetadataRetriever2.release();
                        throw th;
                    }
                }
                ContentResolver contentResolver2 = dej.f6389a;
                ContentValues contentValues = a2.f6385a;
                Uri uri2 = uri;
                contentResolver2.update(uri2, contentValues, (String) null, (String[]) null);
                return uri2;
            }
            throw new IllegalArgumentException("Only media store uris are handled");
        }
        Uri uri3 = parse;
        deh a3 = deh.m5988a(equals);
        a3.mo4088a(str3);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        ContentResolver contentResolver3 = dej.f6389a;
        exm exm = dej.f6390b;
        long a4 = fsa.m9481a(contentResolver3, uri3, "datetaken", 0);
        if (a4 > 0) {
            j = dek.m5998a(a4);
        } else {
            long a5 = fsa.m9481a(contentResolver3, uri3, "date_added", 0);
            if (a5 <= 0) {
                long a6 = fsa.m9481a(contentResolver3, uri3, "date_modified", 0);
                j = a6 > 0 ? dek.m5998a(a6) : dek.m5998a(exm.mo5370a());
            } else {
                j = dek.m5998a(a5);
            }
        }
        long seconds2 = timeUnit.toSeconds(j);
        long seconds3 = TimeUnit.MILLISECONDS.toSeconds(dej.f6390b.mo5370a());
        long millis = TimeUnit.SECONDS.toMillis(seconds2);
        a3.f6385a.put("date_added", Long.valueOf(seconds2));
        a3.mo4090d(seconds3).mo4084a(5 + millis);
        File file = new File(str2);
        String name = file.getName();
        String name2 = file.getName();
        int lastIndexOf = name2.lastIndexOf(46);
        if (lastIndexOf != -1) {
            name2 = name2.substring(0, lastIndexOf);
        }
        deh c = a3.mo4089c(dej.m5996a(str2));
        c.f6385a.put("title", name2);
        c.f6385a.put("_display_name", name);
        c.f6385a.put("_data", str2);
        if (!equals) {
            if (uri3 != null) {
                String a7 = fsa.m9482a(dej.f6389a, uri3, "latitude");
                String a8 = fsa.m9482a(dej.f6389a, uri3, "longitude");
                if (!(a7 == null || a8 == null)) {
                    try {
                        a3.mo4085a(Double.parseDouble(a7), Double.parseDouble(a8));
                    } catch (NumberFormatException e) {
                        Object[] objArr = {a7, a8};
                    }
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(str2);
                    dej.m5997a(a3, mediaMetadataRetriever);
                    extractMetadata = mediaMetadataRetriever.extractMetadata(18);
                    extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                    a3.mo4086a(Integer.parseInt(extractMetadata), Integer.parseInt(extractMetadata2));
                    mediaMetadataRetriever.release();
                    a3.mo4088a("video/mpeg");
                    return dek.m5999a(dej.f6389a, a3.f6385a, true);
                }
            }
            MediaMetadataRetriever mediaMetadataRetriever3 = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever3.setDataSource(str2);
                extractMetadata3 = mediaMetadataRetriever3.extractMetadata(23);
                if (extractMetadata3 != null) {
                    Matcher matcher = Pattern.compile("(-?[0-9]+\\.[0-9]+)\\+(-?[0-9]+\\.[0-9]+)").matcher(extractMetadata3);
                    if (matcher.matches()) {
                        a3.mo4085a(Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(1)));
                    }
                }
            } catch (RuntimeException e2) {
                throw new IOException("failed to set data source", e2);
            } catch (NumberFormatException e3) {
                new Object[1][0] = extractMetadata3;
            } catch (Throwable th2) {
                mediaMetadataRetriever3.release();
                throw th2;
            }
            mediaMetadataRetriever3.release();
            mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str2);
                dej.m5997a(a3, mediaMetadataRetriever);
                extractMetadata = mediaMetadataRetriever.extractMetadata(18);
                extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                a3.mo4086a(Integer.parseInt(extractMetadata), Integer.parseInt(extractMetadata2));
            } catch (RuntimeException e4) {
                throw new IOException("failed to set data source", e4);
            } catch (NumberFormatException e5) {
                Object[] objArr2 = {extractMetadata, extractMetadata2};
            } catch (Throwable th3) {
                mediaMetadataRetriever.release();
                throw th3;
            }
            mediaMetadataRetriever.release();
            a3.mo4088a("video/mpeg");
            return dek.m5999a(dej.f6389a, a3.f6385a, true);
        }
        if (dArr != null && dArr.length == 2) {
            a3.mo4085a(dArr[0], dArr[1]);
        }
        ife.m12876b(true, (Object) "must set image size");
        ddi ddi2 = (ddi) ddl;
        a3.mo4086a(ddi2.f6338a, ddi2.f6339b);
        return dek.m5999a(dej.f6389a, a3.f6385a, false);
    }
}
