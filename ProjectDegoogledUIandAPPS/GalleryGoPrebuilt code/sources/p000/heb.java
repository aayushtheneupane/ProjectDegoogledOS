package p000;

import android.net.Uri;
import java.util.List;

/* renamed from: heb */
/* compiled from: PG */
public final class heb extends hew {

    /* renamed from: a */
    public final String f12568a;

    /* renamed from: b */
    public final Uri f12569b;

    /* renamed from: c */
    public final hso f12570c;

    public /* synthetic */ heb(String str, Uri uri, hso hso) {
        this.f12568a = str;
        this.f12569b = uri;
        this.f12570c = hso;
    }

    /* renamed from: a */
    public final String mo7330a() {
        return this.f12568a;
    }

    /* renamed from: b */
    public final Uri mo7331b() {
        return this.f12569b;
    }

    /* renamed from: c */
    public final hso mo7332c() {
        return this.f12570c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hew) {
            hew hew = (hew) obj;
            return this.f12568a.equals(hew.mo7330a()) && this.f12569b.equals(hew.mo7331b()) && ife.m12856a((List) this.f12570c, (Object) hew.mo7332c());
        }
    }

    public final int hashCode() {
        return ((((this.f12568a.hashCode() ^ 1000003) * 1000003) ^ this.f12569b.hashCode()) * 1000003) ^ this.f12570c.hashCode();
    }

    public final String toString() {
        String str = this.f12568a;
        String valueOf = String.valueOf(this.f12569b);
        String valueOf2 = String.valueOf(this.f12570c);
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 72 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append("HelpOptions{helpCenterContext=");
        sb.append(str);
        sb.append(", fallbackSupportUri=");
        sb.append(valueOf);
        sb.append(", overflowMenuItems=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
