package p000;

import java.io.IOException;

/* renamed from: ijh */
/* compiled from: PG */
public class ijh extends IOException {
    public static final long serialVersionUID = -1616151763072450476L;

    public ijh(String str) {
        super(str);
    }

    /* renamed from: e */
    public static ijh m13658e() {
        return new ijh("Protocol message end-group tag did not match expected tag.");
    }

    /* renamed from: d */
    static ijh m13657d() {
        return new ijh("Protocol message contained an invalid tag (zero).");
    }

    /* renamed from: i */
    public static ijh m13662i() {
        return new ijh("Protocol message had invalid UTF-8.");
    }

    /* renamed from: f */
    public static ijg m13659f() {
        return new ijg("Protocol message tag had invalid wire type.");
    }

    /* renamed from: c */
    static ijh m13656c() {
        return new ijh("CodedInputStream encountered a malformed varint.");
    }

    /* renamed from: b */
    static ijh m13655b() {
        return new ijh("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* renamed from: h */
    static ijh m13661h() {
        return new ijh("Failed to parse the message.");
    }

    /* renamed from: g */
    static ijh m13660g() {
        return new ijh("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    /* renamed from: a */
    static ijh m13654a() {
        return new ijh("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
