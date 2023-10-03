package com.google.android.libraries.stitch.sslguard;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: PG */
public final class SslGuardSocketFactory extends SSLSocketFactory {

    /* renamed from: a */
    private static final String f5121a = SslGuardSocketFactory.class.getSimpleName();

    /* renamed from: b */
    private static fxg f5122b;

    public final Socket createSocket() {
        return m4986b().createSocket();
    }

    public final Socket createSocket(String str, int i) {
        return m4986b().createSocket(str, i);
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return m4986b().createSocket(str, i, inetAddress, i2);
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return m4986b().createSocket(inetAddress, i);
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return m4986b().createSocket(inetAddress, i, inetAddress2, i2);
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return m4986b().createSocket(socket, str, i, z);
    }

    public final String[] getDefaultCipherSuites() {
        try {
            return m4986b().getDefaultCipherSuites();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    private static final SSLSocketFactory m4984a() {
        try {
            return SSLContext.getInstance("Default").getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    private static final SSLSocketFactory m4986b() {
        f5122b.mo6301a(fxe.f10659a);
        SSLSocketFactory a = m4984a();
        if (!(a instanceof SslGuardSocketFactory)) {
            return a;
        }
        String str = "[";
        for (Provider provider : Security.getProviders()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(provider.toString());
            String str2 = valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
            if (provider.stringPropertyNames().contains("SSLContext.Default")) {
                str = String.valueOf(str2).concat("(true), ");
            } else {
                str = String.valueOf(str2).concat("(false), ");
            }
        }
        Log.e(f5121a, String.valueOf(str).concat("]"));
        int i = Build.VERSION.SDK_INT;
        throw new RuntimeException("Unable to find a default SSL provider.");
    }

    public final String[] getSupportedCipherSuites() {
        try {
            return m4986b().getSupportedCipherSuites();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static void m4985a(fxg fxg) {
        if (fxg != null) {
            f5122b = fxg;
            return;
        }
        throw new AssertionError("Cannot initialize SslGuardSocketFactory will null");
    }
}
