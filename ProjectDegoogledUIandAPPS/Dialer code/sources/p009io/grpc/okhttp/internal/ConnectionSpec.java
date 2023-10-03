package p009io.grpc.okhttp.internal;

import java.util.Arrays;
import java.util.List;

/* renamed from: io.grpc.okhttp.internal.ConnectionSpec */
public final class ConnectionSpec {
    private static final CipherSuite[] APPROVED_CIPHER_SUITES = {CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
    public static final ConnectionSpec MODERN_TLS;
    /* access modifiers changed from: private */
    public final String[] cipherSuites;
    final boolean supportsTlsExtensions;
    final boolean tls;
    /* access modifiers changed from: private */
    public final String[] tlsVersions;

    static {
        Builder builder = new Builder(true);
        builder.cipherSuites(APPROVED_CIPHER_SUITES);
        builder.tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        builder.supportsTlsExtensions(true);
        MODERN_TLS = builder.build();
        Builder builder2 = new Builder(MODERN_TLS);
        builder2.tlsVersions(TlsVersion.TLS_1_0);
        builder2.supportsTlsExtensions(true);
        builder2.build();
        new Builder(false).build();
    }

    /* synthetic */ ConnectionSpec(Builder builder, C09531 r2) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec connectionSpec = (ConnectionSpec) obj;
        boolean z = this.tls;
        if (z != connectionSpec.tls) {
            return false;
        }
        return !z || (Arrays.equals(this.cipherSuites, connectionSpec.cipherSuites) && Arrays.equals(this.tlsVersions, connectionSpec.tlsVersions) && this.supportsTlsExtensions == connectionSpec.supportsTlsExtensions);
    }

    public int hashCode() {
        if (!this.tls) {
            return 17;
        }
        return ((Arrays.hashCode(this.tlsVersions) + ((Arrays.hashCode(this.cipherSuites) + 527) * 31)) * 31) + (this.supportsTlsExtensions ^ true ? 1 : 0);
    }

    public String toString() {
        List list;
        String str;
        if (!this.tls) {
            return "ConnectionSpec()";
        }
        String[] strArr = this.cipherSuites;
        int i = 0;
        if (strArr == null) {
            list = null;
        } else {
            CipherSuite[] cipherSuiteArr = new CipherSuite[strArr.length];
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.cipherSuites;
                if (i2 >= strArr2.length) {
                    break;
                }
                cipherSuiteArr[i2] = CipherSuite.forJavaName(strArr2[i2]);
                i2++;
            }
            list = Util.immutableList(cipherSuiteArr);
        }
        if (list == null) {
            str = "[use default]";
        } else {
            str = list.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ConnectionSpec(cipherSuites=");
        sb.append(str);
        sb.append(", tlsVersions=");
        TlsVersion[] tlsVersionArr = new TlsVersion[this.tlsVersions.length];
        while (true) {
            String[] strArr3 = this.tlsVersions;
            if (i < strArr3.length) {
                tlsVersionArr[i] = TlsVersion.forJavaName(strArr3[i]);
                i++;
            } else {
                sb.append(Util.immutableList(tlsVersionArr));
                sb.append(", supportsTlsExtensions=");
                sb.append(this.supportsTlsExtensions);
                sb.append(")");
                return sb.toString();
            }
        }
    }

    /* renamed from: io.grpc.okhttp.internal.ConnectionSpec$Builder */
    public static final class Builder {
        /* access modifiers changed from: private */
        public String[] cipherSuites;
        /* access modifiers changed from: private */
        public boolean supportsTlsExtensions;
        /* access modifiers changed from: private */
        public boolean tls;
        /* access modifiers changed from: private */
        public String[] tlsVersions;

        public Builder(boolean z) {
            this.tls = z;
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(this, (C09531) null);
        }

        public Builder cipherSuites(CipherSuite... cipherSuiteArr) {
            if (this.tls) {
                String[] strArr = new String[cipherSuiteArr.length];
                for (int i = 0; i < cipherSuiteArr.length; i++) {
                    strArr[i] = cipherSuiteArr[i].javaName;
                }
                this.cipherSuites = strArr;
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder supportsTlsExtensions(boolean z) {
            if (this.tls) {
                this.supportsTlsExtensions = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public Builder tlsVersions(TlsVersion... tlsVersionArr) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (tlsVersionArr.length != 0) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                this.tlsVersions = strArr;
                return this;
            } else {
                throw new IllegalArgumentException("At least one TlsVersion is required");
            }
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }
    }
}
