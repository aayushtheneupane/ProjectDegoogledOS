package p000;

import android.content.Context;
import com.google.android.apps.photosgo.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: bkt */
/* compiled from: PG */
public final class bkt {

    /* renamed from: a */
    public final Context f3075a;

    /* renamed from: b */
    private final DateFormat f3076b;

    /* renamed from: c */
    private final cjr f3077c;

    public bkt(Context context, cjr cjr) {
        this.f3075a = context;
        this.f3077c = cjr;
        DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
        this.f3076b = dateTimeInstance;
        dateTimeInstance.setTimeZone(ehe.f8281a);
    }

    /* renamed from: a */
    private final String m3179a(cxi cxi) {
        ehf ehf = cxi.f5917i;
        if (ehf == null) {
            ehf = ehf.f8283d;
        }
        String format = this.f3076b.format(new Date(ehf.f8286b + ehf.f8287c));
        cxh cxh = cxh.UNKNOWN_MEDIA_TYPE;
        cxh a = cxh.m5592a(cxi.f5913e);
        if (a == null) {
            a = cxh.UNKNOWN_MEDIA_TYPE;
        }
        int ordinal = a.ordinal();
        if (ordinal == 0) {
            throw new IllegalArgumentException("ContentDescription#forMedia doesn't support UNKNOWN_MEDIA_TYPE.");
        } else if (ordinal != 1) {
            if (ordinal != 2) {
                cxh a2 = cxh.m5592a(cxi.f5913e);
                if (a2 == null) {
                    a2 = cxh.UNKNOWN_MEDIA_TYPE;
                }
                String valueOf = String.valueOf(a2.name());
                throw new IllegalArgumentException(valueOf.length() == 0 ? new String("ContentDescription#forMedia doesn't support ") : "ContentDescription#forMedia doesn't support ".concat(valueOf));
            }
            return this.f3075a.getString(R.string.video_content_description, new Object[]{format});
        } else if (!this.f3077c.mo3175a() || !flw.m9194a(cxi)) {
            return this.f3075a.getString(R.string.photo_content_description, new Object[]{format});
        } else {
            return this.f3075a.getString(R.string.burst_content_description, new Object[]{Integer.valueOf(cxi.f5929u.size()), format});
        }
    }

    /* renamed from: a */
    public final String mo2529a(cxi cxi, String str) {
        if (str == null) {
            return m3179a(cxi);
        }
        String a = m3179a(cxi);
        StringBuilder sb = new StringBuilder(String.valueOf(a).length() + 1 + str.length());
        sb.append(a);
        sb.append(str);
        sb.append(".");
        return sb.toString();
    }
}
