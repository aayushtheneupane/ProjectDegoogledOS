package com.google.android.libraries.stitch.sslguard;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;

/* compiled from: PG */
public final class SslGuardServerSocketFactory extends SSLServerSocketFactory {

    /* renamed from: a */
    private static final String f5119a = SslGuardSocketFactory.class.getSimpleName();

    /* renamed from: b */
    private static fxg f5120b;

    public final ServerSocket createServerSocket(int i) {
        return m4983b().createServerSocket(i);
    }

    public final ServerSocket createServerSocket(int i, int i2) {
        return m4983b().createServerSocket(i, i2);
    }

    public final ServerSocket createServerSocket(int i, int i2, InetAddress inetAddress) {
        return m4983b().createServerSocket(i, i2, inetAddress);
    }

    public final String[] getDefaultCipherSuites() {
        try {
            return m4983b().getDefaultCipherSuites();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    private static final SSLServerSocketFactory m4981a() {
        try {
            return SSLContext.getInstance("Default").getServerSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    private static final SSLServerSocketFactory m4983b() {
        f5120b.mo6301a(fxe.f10659a);
        SSLServerSocketFactory a = m4981a();
        if (!(a instanceof SslGuardServerSocketFactory)) {
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
        Log.e(f5119a, String.valueOf(str).concat("]"));
        int i = Build.VERSION.SDK_INT;
        throw new RuntimeException("Unable to find a default SSL provider.");
    }

    public final String[] getSupportedCipherSuites() {
        try {
            return m4983b().getSupportedCipherSuites();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static void m4982a(fxg fxg) {
        if (fxg != null) {
            f5120b = fxg;
            return;
        }
        throw new AssertionError("Cannot initialize SslGuardSocketFactory will null");
    }
}
