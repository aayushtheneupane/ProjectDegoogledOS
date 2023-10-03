package com.google.android.libraries.stitch.sslguard;

import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* compiled from: PG */
public class SslGuardContextImpl extends SSLContextSpi {
    static {
        SslGuardContextImpl.class.getSimpleName();
    }

    public final SSLEngine engineCreateSSLEngine() {
        return null;
    }

    public final SSLEngine engineCreateSSLEngine(String str, int i) {
        return null;
    }

    public final /* bridge */ /* synthetic */ SSLSessionContext engineGetClientSessionContext() {
        return null;
    }

    public final /* bridge */ /* synthetic */ SSLSessionContext engineGetServerSessionContext() {
        return null;
    }

    public final SSLServerSocketFactory engineGetServerSocketFactory() {
        return null;
    }

    public final void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) {
    }

    public final SSLSocketFactory engineGetSocketFactory() {
        return new SslGuardSocketFactory();
    }
}
