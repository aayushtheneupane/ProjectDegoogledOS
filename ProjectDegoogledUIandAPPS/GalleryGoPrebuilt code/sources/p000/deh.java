package p000;

import android.content.ContentValues;

/* renamed from: deh */
/* compiled from: PG */
public abstract class deh {

    /* renamed from: a */
    public final ContentValues f6385a = new ContentValues();

    /* renamed from: b */
    private final cxh f6386b;

    public /* synthetic */ deh(cxh cxh) {
        this.f6386b = cxh;
    }

    /* renamed from: a */
    public abstract deh mo4084a(long j);

    /* renamed from: a */
    public abstract void mo4085a(double d, double d2);

    /* renamed from: a */
    public static deh m5988a(boolean z) {
        if (z) {
            return new def();
        }
        return new deg();
    }

    /* renamed from: d */
    public final deh mo4090d(long j) {
        this.f6385a.put("date_modified", Long.valueOf(j));
        return this;
    }

    /* renamed from: a */
    public void mo4086a(int i, int i2) {
        this.f6385a.put("width", Integer.valueOf(i));
        this.f6385a.put("height", Integer.valueOf(i2));
    }

    /* renamed from: b */
    public void mo4087b(long j) {
        int i = this.f6386b.f5906d;
        StringBuilder sb = new StringBuilder(41);
        sb.append("setDuration not supported for ");
        sb.append(i);
        throw new UnsupportedOperationException(sb.toString());
    }

    /* renamed from: c */
    public final deh mo4089c(long j) {
        this.f6385a.put("_size", Long.valueOf(j));
        return this;
    }

    /* renamed from: a */
    public final deh mo4088a(String str) {
        this.f6385a.put("mime_type", str);
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaStoreContentValuesBuilder {");
        for (String next : this.f6385a.keySet()) {
            String valueOf = String.valueOf(this.f6385a.get(next));
            StringBuilder sb2 = new StringBuilder(String.valueOf(next).length() + 4 + String.valueOf(valueOf).length());
            sb2.append(next);
            sb2.append(": ");
            sb2.append(valueOf);
            sb2.append(", ");
            sb.append(sb2.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
