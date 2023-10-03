package p000;

import android.net.Uri;

/* renamed from: dhm */
/* compiled from: PG */
final class dhm extends dhq {

    /* renamed from: a */
    private final Uri f6552a;

    /* renamed from: b */
    private final String f6553b;

    /* renamed from: c */
    private final String f6554c;

    public /* synthetic */ dhm(Uri uri, String str, String str2) {
        this.f6552a = uri;
        this.f6553b = str;
        this.f6554c = str2;
    }

    /* renamed from: a */
    public final Uri mo4137a() {
        return this.f6552a;
    }

    /* renamed from: b */
    public final String mo4138b() {
        return this.f6553b;
    }

    /* renamed from: c */
    public final String mo4139c() {
        return this.f6554c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dhq) {
            dhq dhq = (dhq) obj;
            return this.f6552a.equals(dhq.mo4137a()) && this.f6553b.equals(dhq.mo4138b()) && this.f6554c.equals(dhq.mo4139c());
        }
    }

    public final int hashCode() {
        return ((((this.f6552a.hashCode() ^ 1000003) * 1000003) ^ this.f6553b.hashCode()) * 1000003) ^ this.f6554c.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f6552a);
        String str = this.f6553b;
        String str2 = this.f6554c;
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 60 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("PreferredEditorData{icon=");
        sb.append(valueOf);
        sb.append(", activityPackage=");
        sb.append(str);
        sb.append(", activityClass=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
