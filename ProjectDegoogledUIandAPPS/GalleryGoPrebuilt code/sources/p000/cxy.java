package p000;

import android.content.Context;
import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.InputStream;
import p003j$.util.Optional;

/* renamed from: cxy */
/* compiled from: PG */
public final class cxy {

    /* renamed from: g */
    public static final String[] f5974g = {"_data", "bucket_display_name", "_display_name", "datetaken", "width", "height", "orientation", "latitude", "longitude", "_size", "mime_type"};

    /* renamed from: a */
    public final Context f5975a;

    /* renamed from: b */
    public final ebi f5976b;

    /* renamed from: c */
    public final gtt f5977c;

    /* renamed from: d */
    public final guj f5978d;

    /* renamed from: e */
    public final iel f5979e;

    /* renamed from: f */
    public final iel f5980f;

    public cxy(Context context, ebi ebi, gtt gtt, guj guj, iel iel, iel iel2) {
        this.f5975a = context;
        this.f5976b = ebi;
        this.f5977c = gtt;
        this.f5978d = guj;
        this.f5979e = iel;
        this.f5980f = iel2;
    }

    /* renamed from: a */
    public static void m5628a(cxp cxp, InputStream inputStream) {
        int[] iArr;
        double[] c;
        Object obj;
        fsc fsc = new fsc();
        fsc.mo6106a((InputStream) new BufferedInputStream(inputStream));
        fsp c2 = fsc.mo6111c(fsc.f10459l);
        fsp c3 = fsc.mo6111c(fsc.f10458k);
        fsp c4 = fsc.mo6111c(fsc.f10462o);
        int i = fsc.f10460m;
        fsl a = fsc.mo6102a(i, fsc.mo6113d(i));
        Double d = null;
        if (a == null || (obj = a.f10529f) == null || !(obj instanceof long[])) {
            iArr = null;
        } else {
            long[] jArr = (long[]) obj;
            iArr = new int[jArr.length];
            for (int i2 = 0; i2 < jArr.length; i2++) {
                iArr[i2] = (int) jArr[i2];
            }
        }
        Integer valueOf = (iArr == null || iArr.length <= 0) ? null : Integer.valueOf(iArr[0]);
        cxp.f5951f = Optional.ofNullable(fsc.mo6109b(fsc.f10374a));
        cxp.f5952g = Optional.ofNullable(c2 != null ? Double.valueOf(c2.mo6158a()) : null);
        cxp.f5953h = Optional.ofNullable(c3 != null ? Double.valueOf(c3.mo6158a()) : null);
        if (c4 != null) {
            d = Double.valueOf(c4.mo6158a());
        }
        cxp.f5954i = Optional.ofNullable(d);
        cxp.f5955j = Optional.ofNullable(valueOf);
        cwx cwx = (cwx) cxp.mo3943a();
        if (!cwx.f5825a.isPresent() && !cwx.f5826b.isPresent() && (c = fsc.mo6112c()) != null) {
            cxp.mo3945a(Double.valueOf(c[0]));
            cxp.mo3947b(Double.valueOf(c[1]));
        }
    }

    /* renamed from: a */
    public static ieh m5627a(Uri uri) {
        cxp q = cxq.m5606q();
        q.mo3946a(uri.getLastPathSegment());
        q.mo3948b(uri.toString());
        return ife.m12820a((Object) q.mo3943a());
    }
}
