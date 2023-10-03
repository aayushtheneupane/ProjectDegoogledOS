package p009io.grpc.okhttp;

import com.google.common.base.MoreObjects;
import java.net.Socket;
import java.security.Security;
import p009io.grpc.okhttp.internal.OptionalMethod;
import p009io.grpc.okhttp.internal.Platform;

/* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator */
class OkHttpProtocolNegotiator {
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR = createNegotiator(OkHttpProtocolNegotiator.class.getClassLoader());
    private final Platform platform;

    /* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator */
    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        /* access modifiers changed from: private */
        public static final TlsExtensionType DEFAULT_TLS_EXTENSION_TYPE = pickTlsExtensionType(AndroidNegotiator.class.getClassLoader());
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getNpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setAlpnProtocols", byte[].class);
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>((Class<?>) null, "setHostname", String.class);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setNpnProtocols", byte[].class);
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>((Class<?>) null, "setUseSessionTickets", Boolean.TYPE);
        private final TlsExtensionType tlsExtensionType;

        /* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator$TlsExtensionType */
        enum TlsExtensionType {
            ALPN_AND_NPN,
            NPN
        }

        AndroidNegotiator(Platform platform, TlsExtensionType tlsExtensionType2) {
            super(platform);
            MoreObjects.checkNotNull(tlsExtensionType2, "Unable to pick a TLS extension");
            this.tlsExtensionType = tlsExtensionType2;
        }

        static TlsExtensionType pickTlsExtensionType(ClassLoader classLoader) {
            if (Security.getProvider("GmsCore_OpenSSL") != null) {
                return TlsExtensionType.ALPN_AND_NPN;
            }
            try {
                classLoader.loadClass("android.net.Network");
                return TlsExtensionType.ALPN_AND_NPN;
            } catch (ClassNotFoundException unused) {
                try {
                    classLoader.loadClass("android.app.ActivityOptions");
                    return TlsExtensionType.NPN;
                } catch (ClassNotFoundException unused2) {
                    return null;
                }
            }
        }
    }

    OkHttpProtocolNegotiator(Platform platform2) {
        if (platform2 != null) {
            this.platform = platform2;
            return;
        }
        throw new NullPointerException();
    }

    static OkHttpProtocolNegotiator createNegotiator(ClassLoader classLoader) {
        boolean z;
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException unused) {
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException unused2) {
                z = false;
            }
        }
        z = true;
        return z ? new AndroidNegotiator(DEFAULT_PLATFORM, AndroidNegotiator.DEFAULT_TLS_EXTENSION_TYPE) : new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
    }
}
