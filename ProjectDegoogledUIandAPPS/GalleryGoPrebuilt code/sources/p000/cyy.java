package p000;

import java.io.File;
import p003j$.util.Optional;

/* renamed from: cyy */
/* compiled from: PG */
final class cyy extends czt {

    /* renamed from: a */
    private final File f6074a;

    /* renamed from: b */
    private final Optional f6075b;

    public cyy(File file, Optional optional) {
        if (file != null) {
            this.f6074a = file;
            if (optional != null) {
                this.f6075b = optional;
                return;
            }
            throw new NullPointerException("Null documentFile");
        }
        throw new NullPointerException("Null systemFile");
    }

    /* renamed from: a */
    public final File mo4002a() {
        return this.f6074a;
    }

    /* renamed from: b */
    public final Optional mo4003b() {
        return this.f6075b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof czt) {
            czt czt = (czt) obj;
            return this.f6074a.equals(czt.mo4002a()) && this.f6075b.equals(czt.mo4003b());
        }
    }

    public final int hashCode() {
        return ((this.f6074a.hashCode() ^ 1000003) * 1000003) ^ this.f6075b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f6074a);
        String valueOf2 = String.valueOf(this.f6075b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 53 + String.valueOf(valueOf2).length());
        sb.append("SystemFileAndDocumentFile{systemFile=");
        sb.append(valueOf);
        sb.append(", documentFile=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
