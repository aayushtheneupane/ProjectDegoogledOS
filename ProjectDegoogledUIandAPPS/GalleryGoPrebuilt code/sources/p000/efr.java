package p000;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: efr */
/* compiled from: PG */
public final class efr {

    /* renamed from: a */
    public final Context f8168a;

    /* renamed from: b */
    public final efu f8169b;

    /* renamed from: c */
    private final egb f8170c;

    /* renamed from: d */
    private final iel f8171d;

    /* renamed from: e */
    private final cjr f8172e;

    public efr(Context context, egb egb, iel iel, cjr cjr, efu efu) {
        this.f8168a = context;
        this.f8170c = egb;
        this.f8171d = iel;
        this.f8172e = cjr;
        this.f8169b = efu;
    }

    /* renamed from: a */
    public static abt m7378a(String str, List list, abt abt, boolean z) {
        int i = 0;
        while (i < list.size()) {
            String str2 = (String) list.get(i);
            abt a = abt.mo121a(str2);
            if (a != null) {
                abt = a;
            } else if (!z) {
                throw new IOException("Document file does not exist");
            } else if (i == list.size() - 1) {
                String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
                if (mimeTypeFromExtension == null) {
                    mimeTypeFromExtension = "";
                }
                Uri a2 = abt.m165a(abt.f105a, abt.f106b, mimeTypeFromExtension, str2);
                abt = a2 != null ? new abt(abt.f105a, a2) : null;
            } else {
                abt = abt.mo123b(str2);
            }
            if (abt != null) {
                i++;
            } else {
                throw new IOException("Unable to create intermediate document file");
            }
        }
        if (abt != null) {
            return abt;
        }
        throw new IOException("Unable to find or create document file");
    }

    /* renamed from: a */
    public final ieh mo4787a(File file, boolean z) {
        if (this.f8172e.mo3175a()) {
            return this.f8171d.mo5933a(hmq.m11749a((Callable) new efn(this, file, z)));
        }
        return gte.m10771a(mo4786a(file), (icf) new efo(file, z), (Executor) this.f8171d);
    }

    /* renamed from: a */
    public final ieh mo4786a(File file) {
        return gte.m10771a(gte.m10770a(this.f8170c.f8189a.mo6359a(), efz.f8181a, (Executor) idh.f13918a), (icf) new efp(this, file), (Executor) this.f8171d);
    }
}
