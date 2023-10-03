package p000;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* renamed from: dek */
/* compiled from: PG */
public final class dek {
    /* renamed from: a */
    public static long m5998a(long j) {
        return j < 100000000000L ? j * 1000 : j;
    }

    @Deprecated
    /* renamed from: a */
    public static String m6001a(String str) {
        if (Build.VERSION.SDK_INT >= 29 || Build.VERSION.SDK_INT >= 29) {
            return str;
        }
        Locale locale = Locale.US;
        int length = str.length();
        int length2 = str.length();
        int length3 = str.length();
        int length4 = str.length();
        int length5 = str.length();
        int length6 = str.length();
        int length7 = str.length();
        StringBuilder sb = new StringBuilder(length + 131 + length2 + length3 + length4 + length5 + length6 + length7 + str.length() + str.length());
        sb.append("case when (");
        sb.append(str);
        sb.append(" >= %1$d and ");
        sb.append(str);
        sb.append(" < %2$d) then ");
        sb.append(str);
        sb.append(" * 1000 when (");
        sb.append(str);
        sb.append(" >= %3$d and ");
        sb.append(str);
        sb.append(" < %4$d) then ");
        sb.append(str);
        sb.append(" when (");
        sb.append(str);
        sb.append(" >= %5$d and ");
        sb.append(str);
        sb.append(" < %6$d) then ");
        sb.append(str);
        sb.append(" / 1000 else 0 end");
        return String.format(locale, sb.toString(), new Object[]{157680000L, 1892160000L, 157680000000L, 1892160000000L, 157680000000000L, 1892160000000000L});
    }

    /* renamed from: a */
    public static Uri m5999a(ContentResolver contentResolver, ContentValues contentValues, boolean z) {
        Uri uri;
        Uri uri2;
        Uri uri3;
        Uri uri4;
        if (!z) {
            try {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } catch (Exception e) {
                if (z) {
                    try {
                        uri3 = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
                    } catch (Exception e2) {
                        if (!z) {
                            try {
                                uri4 = dei.f6388b;
                            } catch (Exception e3) {
                                throw new IOException("Failed to insert in Media Store", e3);
                            }
                        } else {
                            uri4 = dei.f6387a;
                        }
                        uri = contentResolver.insert(uri4, contentValues);
                    }
                } else {
                    uri3 = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
                }
                uri = contentResolver.insert(uri3, contentValues);
            }
        } else {
            uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        uri = contentResolver.insert(uri2, contentValues);
        if (uri != null) {
            return uri;
        }
        throw new IOException("Content provider returned null on Media Store insert");
    }

    /* renamed from: a */
    public static String m6000a(long j, TimeZone timeZone) {
        String str;
        int offset = timeZone.getOffset(j);
        int abs = Math.abs(offset);
        StringBuilder sb = new StringBuilder();
        if (offset >= 0) {
            str = "+";
        } else {
            str = "-";
        }
        sb.append(str);
        long j2 = (long) abs;
        sb.append(m6002b(TimeUnit.MILLISECONDS.toHours(j2)));
        sb.append(":");
        sb.append(m6002b(TimeUnit.MILLISECONDS.toMinutes(j2) % 60));
        return sb.toString();
    }

    /* renamed from: b */
    private static String m6002b(long j) {
        String l = Long.toString(j);
        ife.m12898e((Object) l);
        if (l.length() >= 2) {
            return l;
        }
        StringBuilder sb = new StringBuilder(2);
        for (int length = l.length(); length < 2; length++) {
            sb.append('0');
        }
        sb.append(l);
        return sb.toString();
    }
}
