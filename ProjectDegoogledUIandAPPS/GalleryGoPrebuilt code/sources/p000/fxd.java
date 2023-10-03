package p000;

import android.os.Build;
import com.google.android.libraries.stitch.sslguard.SslGuardServerSocketFactory;
import com.google.android.libraries.stitch.sslguard.SslGuardSocketFactory;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: fxd */
/* compiled from: PG */
public final class fxd {

    /* renamed from: a */
    public static final Object f10654a = new Object();

    /* renamed from: b */
    public static fxh f10655b;

    /* renamed from: d */
    private static final String f10656d = SslGuardSocketFactory.class.getName();

    /* renamed from: e */
    private static final String f10657e = SslGuardServerSocketFactory.class.getName();

    /* renamed from: c */
    public fxg f10658c;

    static {
        fxd.class.getSimpleName();
    }

    private fxd() {
    }

    public fxd(fxg fxg) {
        this.f10658c = fxg;
    }

    /* renamed from: a */
    public static final void m9811a() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{new fxi()}, (SecureRandom) null);
            SSLContext.setDefault(instance);
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public static final void m9812b() {
        if (Build.VERSION.SDK_INT < 28) {
            try {
                SSLContext instance = SSLContext.getInstance("Default");
                Field declaredField = SSLSocketFactory.class.getDeclaredField("defaultSocketFactory");
                declaredField.setAccessible(true);
                declaredField.set((Object) null, instance.getSocketFactory());
                Field declaredField2 = SSLServerSocketFactory.class.getDeclaredField("defaultServerSocketFactory");
                declaredField2.setAccessible(true);
                declaredField2.set((Object) null, instance.getServerSocketFactory());
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        Security.setProperty("ssl.SocketFactory.provider", f10656d);
        Security.setProperty("ssl.ServerSocketFactory.provider", f10657e);
    }
}
