package p009io.grpc.okhttp.internal;

import java.lang.reflect.Method;
import java.net.Socket;
import java.security.Provider;
import java.util.logging.Logger;

/* renamed from: io.grpc.okhttp.internal.Platform */
public class Platform {
    private static final Platform PLATFORM;
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    private final Provider sslProvider;

    /* renamed from: io.grpc.okhttp.internal.Platform$Android */
    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;

        public Android(OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4, Provider provider) {
            super(provider);
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
        }
    }

    /* renamed from: io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform */
    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2, Provider provider) {
            super(provider);
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls;
            this.serverProviderClass = cls2;
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [io.grpc.okhttp.internal.Platform] */
    /* JADX WARNING: type inference failed for: r0v29 */
    /* JADX WARNING: type inference failed for: r6v20, types: [io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform] */
    /* JADX WARNING: type inference failed for: r6v21, types: [io.grpc.okhttp.internal.Platform$Android] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e1  */
    static {
        /*
            java.lang.Class<io.grpc.okhttp.internal.Platform> r0 = p009io.grpc.okhttp.internal.Platform.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            java.lang.String r0 = "Unable to create conscrypt provider "
            r1 = 0
            java.lang.String r2 = "org.conscrypt.OpenSSLProvider"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x0016 }
            goto L_0x0025
        L_0x0016:
            java.lang.String r2 = "com.android.org.conscrypt.OpenSSLProvider"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x001d }
            goto L_0x0025
        L_0x001d:
            java.lang.String r2 = "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x0024 }
            goto L_0x0025
        L_0x0024:
            r2 = r1
        L_0x0025:
            javax.net.ssl.SSLContext r3 = javax.net.ssl.SSLContext.getDefault()     // Catch:{ NoSuchAlgorithmException -> 0x002e }
            java.security.Provider r3 = r3.getProvider()     // Catch:{ NoSuchAlgorithmException -> 0x002e }
            goto L_0x002f
        L_0x002e:
            r3 = r1
        L_0x002f:
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x007f
            if (r3 == 0) goto L_0x0041
            java.lang.Class r6 = r3.getClass()
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x0041
            r0 = r4
            goto L_0x0080
        L_0x0041:
            java.lang.Class<java.security.Provider> r6 = java.security.Provider.class
            java.lang.Class r6 = r2.asSubclass(r6)     // Catch:{ InstantiationException -> 0x0068, IllegalAccessException -> 0x0050 }
            java.lang.Object r6 = r6.newInstance()     // Catch:{ InstantiationException -> 0x0068, IllegalAccessException -> 0x0050 }
            java.security.Provider r6 = (java.security.Provider) r6     // Catch:{ InstantiationException -> 0x0068, IllegalAccessException -> 0x0050 }
            r0 = r4
            r3 = r6
            goto L_0x0080
        L_0x0050:
            r6 = move-exception
            java.util.logging.Logger r7 = logger
            java.util.logging.Level r8 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r7.log(r8, r0, r6)
            goto L_0x007f
        L_0x0068:
            r6 = move-exception
            java.util.logging.Logger r7 = logger
            java.util.logging.Level r8 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r7.log(r8, r0, r6)
        L_0x007f:
            r0 = r5
        L_0x0080:
            if (r0 == 0) goto L_0x00e1
            io.grpc.okhttp.internal.OptionalMethod r7 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r0 = new java.lang.Class[r4]
            java.lang.Class r2 = java.lang.Boolean.TYPE
            r0[r5] = r2
            java.lang.String r2 = "setUseSessionTickets"
            r7.<init>(r1, r2, r0)
            io.grpc.okhttp.internal.OptionalMethod r8 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r0 = new java.lang.Class[r4]
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            r0[r5] = r2
            java.lang.String r2 = "setHostname"
            r8.<init>(r1, r2, r0)
            io.grpc.okhttp.internal.OptionalMethod r11 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class<byte[]> r0 = byte[].class
            java.lang.Class[] r2 = new java.lang.Class[r5]
            java.lang.String r6 = "getAlpnSelectedProtocol"
            r11.<init>(r0, r6, r2)
            io.grpc.okhttp.internal.OptionalMethod r12 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r0 = new java.lang.Class[r4]
            java.lang.Class<byte[]> r2 = byte[].class
            r0[r5] = r2
            java.lang.String r2 = "setAlpnProtocols"
            r12.<init>(r1, r2, r0)
            java.lang.String r0 = "android.net.TrafficStats"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d6 }
            java.lang.String r2 = "tagSocket"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d6 }
            java.lang.Class<java.net.Socket> r9 = java.net.Socket.class
            r6[r5] = r9     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d6 }
            java.lang.reflect.Method r2 = r0.getMethod(r2, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d6 }
            java.lang.String r6 = "untagSocket"
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d3 }
            java.lang.Class<java.net.Socket> r9 = java.net.Socket.class
            r4[r5] = r9     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d3 }
            java.lang.reflect.Method r0 = r0.getMethod(r6, r4)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00d3 }
            r1 = r0
        L_0x00d3:
            r10 = r1
            r9 = r2
            goto L_0x00d8
        L_0x00d6:
            r9 = r1
            r10 = r9
        L_0x00d8:
            io.grpc.okhttp.internal.Platform$Android r0 = new io.grpc.okhttp.internal.Platform$Android
            r6 = r0
            r13 = r3
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            goto L_0x015a
        L_0x00e1:
            java.lang.String r0 = "org.eclipse.jetty.alpn.ALPN"
            java.lang.Class r1 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r2.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r2.append(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r6 = "$Provider"
            r2.append(r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r2 = r2.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r6.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r6.append(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r7 = "$ClientProvider"
            r6.append(r7)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r6 = r6.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.Class r10 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r6.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r6.append(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r0 = "$ServerProvider"
            r6.append(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r0 = r6.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.Class r11 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r0 = "put"
            r6 = 2
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r5] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r6[r4] = r2     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.reflect.Method r7 = r1.getMethod(r0, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r0 = "get"
            java.lang.Class[] r2 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.Class<javax.net.ssl.SSLSocket> r6 = javax.net.ssl.SSLSocket.class
            r2[r5] = r6     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.reflect.Method r8 = r1.getMethod(r0, r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.String r0 = "remove"
            java.lang.Class[] r2 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.Class<javax.net.ssl.SSLSocket> r4 = javax.net.ssl.SSLSocket.class
            r2[r5] = r4     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            java.lang.reflect.Method r9 = r1.getMethod(r0, r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform r0 = new io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            r6 = r0
            r12 = r3
            r6.<init>(r7, r8, r9, r10, r11, r12)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0155 }
            goto L_0x015a
        L_0x0155:
            io.grpc.okhttp.internal.Platform r0 = new io.grpc.okhttp.internal.Platform
            r0.<init>(r3)
        L_0x015a:
            PLATFORM = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.okhttp.internal.Platform.<clinit>():void");
    }

    public Platform(Provider provider) {
        this.sslProvider = provider;
    }

    public static Platform get() {
        return PLATFORM;
    }

    public Provider getProvider() {
        return this.sslProvider;
    }
}
