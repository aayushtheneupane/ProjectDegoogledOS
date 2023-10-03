package p000;

import android.content.ContentResolver;

/* renamed from: dgt */
/* compiled from: PG */
public final class dgt {

    /* renamed from: a */
    public static final String[] f6517a = {"_data"};

    /* renamed from: b */
    public final ContentResolver f6518b;

    public dgt(ContentResolver contentResolver) {
        this.f6518b = contentResolver;
    }

    /* renamed from: a */
    public static String m6096a(cxh cxh) {
        cxh cxh2 = cxh.UNKNOWN_MEDIA_TYPE;
        int ordinal = cxh.ordinal();
        if (ordinal == 0) {
            cwn.m5510a("MimeTypeHelper: Getting MIME type for unknown media type", new Object[0]);
            return "*/*";
        } else if (ordinal == 1) {
            return "image/*";
        } else {
            if (ordinal == 2) {
                return "video/*";
            }
            String valueOf = String.valueOf(cxh);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51);
            sb.append("Tried to get MIME type for unimplemented MediaType ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: b */
    public static boolean m6097b(String str) {
        return str.startsWith("image/");
    }

    /* renamed from: c */
    public static boolean m6098c(String str) {
        return str.startsWith("video/");
    }

    /* renamed from: a */
    public static cxh m6095a(String str) {
        if (str == null) {
            return cxh.UNKNOWN_MEDIA_TYPE;
        }
        if (!m6097b(str)) {
            return m6098c(str) ? cxh.VIDEO : cxh.UNKNOWN_MEDIA_TYPE;
        }
        return cxh.IMAGE;
    }
}
