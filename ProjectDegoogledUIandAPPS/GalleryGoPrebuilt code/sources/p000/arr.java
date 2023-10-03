package p000;

import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/* renamed from: arr */
/* compiled from: PG */
public final class arr implements ari {

    /* renamed from: a */
    private final axa f1492a;

    /* renamed from: b */
    private final int f1493b;

    /* renamed from: c */
    private HttpURLConnection f1494c;

    /* renamed from: d */
    private InputStream f1495d;

    /* renamed from: e */
    private volatile boolean f1496e;

    public arr(axa axa, int i) {
        this.f1492a = axa;
        this.f1493b = i;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 2;
    }

    /* renamed from: c */
    public final void mo1517c() {
        this.f1496e = true;
    }

    /* renamed from: b */
    public final void mo1516b() {
        InputStream inputStream = this.f1495d;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
        HttpURLConnection httpURLConnection = this.f1494c;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.f1494c = null;
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        bfk.m2412a();
        try {
            axa axa = this.f1492a;
            if (axa.f1811f == null) {
                if (TextUtils.isEmpty(axa.f1810e)) {
                    String str = axa.f1809d;
                    if (TextUtils.isEmpty(str)) {
                        str = ((URL) cns.m4632a((Object) axa.f1808c)).toString();
                    }
                    axa.f1810e = Uri.encode(str, "@#&=*+-_.,:!?()/~'%;$");
                }
                axa.f1811f = new URL(axa.f1810e);
            }
            arh.mo1525a((Object) m1516a(axa.f1811f, 0, (URL) null, this.f1492a.f1807b.mo1705a()));
        } catch (IOException e) {
            arh.mo1524a((Exception) e);
        }
    }

    /* renamed from: a */
    private final InputStream m1516a(URL url, int i, URL url2, Map map) {
        if (i < 5) {
            if (url2 != null) {
                try {
                    if (url.toURI().equals(url2.toURI())) {
                        throw new aql("In re-direct loop");
                    }
                } catch (URISyntaxException e) {
                }
            }
            this.f1494c = (HttpURLConnection) url.openConnection();
            for (Map.Entry entry : map.entrySet()) {
                this.f1494c.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            this.f1494c.setConnectTimeout(this.f1493b);
            this.f1494c.setReadTimeout(this.f1493b);
            this.f1494c.setUseCaches(false);
            this.f1494c.setDoInput(true);
            this.f1494c.setInstanceFollowRedirects(false);
            this.f1494c.connect();
            this.f1495d = this.f1494c.getInputStream();
            if (this.f1496e) {
                return null;
            }
            int responseCode = this.f1494c.getResponseCode();
            int i2 = responseCode / 100;
            if (i2 == 2) {
                HttpURLConnection httpURLConnection = this.f1494c;
                if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
                    this.f1495d = new bff(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength());
                } else {
                    this.f1495d = httpURLConnection.getInputStream();
                }
                return this.f1495d;
            } else if (i2 == 3) {
                String headerField = this.f1494c.getHeaderField("Location");
                if (!TextUtils.isEmpty(headerField)) {
                    URL url3 = new URL(url, headerField);
                    mo1516b();
                    return m1516a(url3, i + 1, url, map);
                }
                throw new aql("Received empty or null redirect url");
            } else if (responseCode != -1) {
                throw new aql(this.f1494c.getResponseMessage(), (byte[]) null);
            } else {
                throw new aql();
            }
        } else {
            throw new aql("Too many (> 5) redirects!");
        }
    }
}
