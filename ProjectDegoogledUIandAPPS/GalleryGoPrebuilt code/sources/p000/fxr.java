package p000;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* renamed from: fxr */
/* compiled from: PG */
public final class fxr {

    /* renamed from: a */
    private final Map f10681a = new HashMap();

    /* renamed from: b */
    private final Map f10682b = new HashMap();

    /* renamed from: c */
    private final List f10683c = new ArrayList();

    public fxr(List list) {
        List<fyw> emptyList = Collections.emptyList();
        List emptyList2 = Collections.emptyList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            fyr fyr = (fyr) list.get(i);
            if (TextUtils.isEmpty(fyr.mo6322b())) {
                Log.w("MobStore.FileStorage", "Cannot register backend, name empty");
            } else {
                fyr fyr2 = (fyr) this.f10681a.put(fyr.mo6322b(), fyr);
                if (fyr2 != null) {
                    String canonicalName = fyr2.getClass().getCanonicalName();
                    String canonicalName2 = fyr.getClass().getCanonicalName();
                    StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 30 + String.valueOf(canonicalName2).length());
                    sb.append("Cannot override Backend ");
                    sb.append(canonicalName);
                    sb.append(" with ");
                    sb.append(canonicalName2);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        for (fyw fyw : emptyList) {
            if (TextUtils.isEmpty(fyw.mo6345a())) {
                Log.w("MobStore.FileStorage", "Cannot register transform, name empty");
            } else {
                fyw fyw2 = (fyw) this.f10682b.put(fyw.mo6345a(), fyw);
                if (fyw2 != null) {
                    String canonicalName3 = fyw2.getClass().getCanonicalName();
                    String canonicalName4 = fyw.getClass().getCanonicalName();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(canonicalName3).length() + 35 + String.valueOf(canonicalName4).length());
                    sb2.append("Cannot to override Transform ");
                    sb2.append(canonicalName3);
                    sb2.append(" with ");
                    sb2.append(canonicalName4);
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
        }
        this.f10683c.addAll(emptyList2);
    }

    /* renamed from: a */
    public final void mo6317a(Uri uri) {
        fxp a = m9845a(uri, new fxl[0]);
        a.f10676a.mo6327e(a.f10679d);
    }

    /* renamed from: b */
    public final boolean mo6319b(Uri uri) {
        fxp a = m9845a(uri, new fxl[0]);
        return a.f10676a.mo6323b(a.f10679d);
    }

    /* renamed from: a */
    private final fxp m9845a(Uri uri, fxl... fxlArr) {
        hsj j = hso.m12048j();
        hvs i = fyl.m9876a(uri).listIterator();
        while (i.hasNext()) {
            String str = (String) i.next();
            fyw fyw = (fyw) this.f10682b.get(str);
            if (fyw != null) {
                j.mo7908c(fyw);
            } else {
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(valueOf).length());
                sb.append("No such transform: ");
                sb.append(str);
                sb.append(": ");
                sb.append(valueOf);
                throw new fye(sb.toString());
            }
        }
        hso e = j.mo7905a().mo7910e();
        fxo fxo = new fxo((byte[]) null);
        String scheme = uri.getScheme();
        fyr fyr = (fyr) this.f10681a.get(scheme);
        if (fyr != null) {
            fxo.f10670a = fyr;
            fxo.f10672c = this.f10683c;
            fxo.f10671b = e;
            fxo.f10673d = uri;
            if (!e.isEmpty()) {
                ArrayList arrayList = new ArrayList(uri.getPathSegments());
                if (!arrayList.isEmpty() && !uri.getPath().endsWith("/")) {
                    String str2 = (String) arrayList.get(arrayList.size() - 1);
                    ListIterator listIterator = e.listIterator(e.size());
                    while (listIterator.hasPrevious()) {
                        str2 = ((fyw) listIterator.previous()).mo6346b();
                    }
                    arrayList.set(arrayList.size() - 1, str2);
                    uri = uri.buildUpon().path(TextUtils.join("/", arrayList)).encodedFragment((String) null).build();
                }
            }
            fxo.f10674e = uri;
            fxo.f10675f = Arrays.asList(fxlArr);
            return new fxp(fxo);
        }
        throw new fye(String.format("Cannot open, unregistered backend: %s", new Object[]{scheme}));
    }

    /* renamed from: a */
    public final Object mo6316a(Uri uri, fxq fxq, fxl... fxlArr) {
        return fxq.mo6315a(m9845a(uri, fxlArr));
    }

    /* renamed from: a */
    public final void mo6318a(Uri uri, Uri uri2) {
        fxp a = m9845a(uri, new fxl[0]);
        fxp a2 = m9845a(uri2, new fxl[0]);
        fyr fyr = a.f10676a;
        if (fyr == a2.f10676a) {
            fyr.mo6326a(a.f10679d, a2.f10679d);
            return;
        }
        throw new fye("Cannot rename file across backends");
    }
}
