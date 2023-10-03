package p000;

import android.util.Log;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* renamed from: fxi */
/* compiled from: PG */
final class fxi implements TrustManager, X509TrustManager {

    /* renamed from: a */
    private static final String f10664a = fxi.class.getSimpleName();

    public final X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        m9815a();
    }

    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        m9815a();
    }

    /* renamed from: a */
    private static final void m9815a() {
        Log.e(f10664a, "App is using openSSL stack, but is not using ProviderInstaller. Please Fix.");
        throw new RuntimeException("Unsafe use of platform SSL stack.");
    }
}
