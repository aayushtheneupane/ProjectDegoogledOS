package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.util.Arrays;
import java.util.List;

public final class ConnectionSpec {
    private static final CipherSuite[] APPROVED_CIPHER_SUITES = {CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
    public static final ConnectionSpec MODERN_TLS;
    /* access modifiers changed from: private */
    public final String[] cipherSuites;
    /* access modifiers changed from: private */
    public final boolean supportsTlsExtensions;
    /* access modifiers changed from: private */
    public final boolean tls;
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

    /* synthetic */ ConnectionSpec(Builder builder, C09291 r2) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public List<CipherSuite> cipherSuites() {
        String[] strArr = this.cipherSuites;
        if (strArr == null) {
            return null;
        }
        CipherSuite[] cipherSuiteArr = new CipherSuite[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.cipherSuites;
            if (i >= strArr2.length) {
                return Util.immutableList(cipherSuiteArr);
            }
            cipherSuiteArr[i] = CipherSuite.forJavaName(strArr2[i]);
            i++;
        }
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

    public List<TlsVersion> tlsVersions() {
        String[] strArr = this.tlsVersions;
        if (strArr == null) {
            return null;
        }
        TlsVersion[] tlsVersionArr = new TlsVersion[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.tlsVersions;
            if (i >= strArr2.length) {
                return Util.immutableList(tlsVersionArr);
            }
            tlsVersionArr[i] = TlsVersion.forJavaName(strArr2[i]);
            i++;
        }
    }

    public String toString() {
        if (!this.tls) {
            return "ConnectionSpec()";
        }
        String str = "[all enabled]";
        String obj = this.cipherSuites != null ? cipherSuites().toString() : str;
        if (this.tlsVersions != null) {
            str = tlsVersions().toString();
        }
        return "ConnectionSpec(cipherSuites=" + obj + ", tlsVersions=" + str + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public String[] cipherSuites;
        /* access modifiers changed from: private */
        public boolean supportsTlsExtensions;
        /* access modifiers changed from: private */
        public boolean tls;
        /* access modifiers changed from: private */
        public String[] tlsVersions;

        Builder(boolean z) {
            this.tls = z;
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(this, (C09291) null);
        }

        public Builder cipherSuites(CipherSuite... cipherSuiteArr) {
            if (this.tls) {
                String[] strArr = new String[cipherSuiteArr.length];
                for (int i = 0; i < cipherSuiteArr.length; i++) {
                    strArr[i] = cipherSuiteArr[i].javaName;
                }
                if (!this.tls) {
                    throw new IllegalStateException("no cipher suites for cleartext connections");
                } else if (strArr.length != 0) {
                    this.cipherSuites = (String[]) strArr.clone();
                    return this;
                } else {
                    throw new IllegalArgumentException("At least one cipher suite is required");
                }
            } else {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
        }

        public Builder supportsTlsExtensions(boolean z) {
            if (this.tls) {
                this.supportsTlsExtensions = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public Builder tlsVersions(TlsVersion... tlsVersionArr) {
            if (this.tls) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                if (!this.tls) {
                    throw new IllegalStateException("no TLS versions for cleartext connections");
                } else if (strArr.length != 0) {
                    this.tlsVersions = (String[]) strArr.clone();
                    return this;
                } else {
                    throw new IllegalArgumentException("At least one TLS version is required");
                }
            } else {
                throw new IllegalStateException("no TLS versions for cleartext connections");
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
