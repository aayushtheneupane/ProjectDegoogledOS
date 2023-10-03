package p000;

/* renamed from: aof */
/* compiled from: PG */
final class aof implements aor {

    /* renamed from: a */
    private final /* synthetic */ String f1257a;

    /* renamed from: b */
    private final /* synthetic */ String f1258b;

    /* renamed from: c */
    private final /* synthetic */ String f1259c;

    /* renamed from: d */
    private final /* synthetic */ aol f1260d;

    public aof(String str, String str2, String str3, aol aol) {
        this.f1257a = str;
        this.f1258b = str2;
        this.f1259c = str3;
        this.f1260d = aol;
    }

    /* renamed from: a */
    public final String mo1331a() {
        return this.f1257a;
    }

    /* renamed from: b */
    public final String mo1332b() {
        return this.f1258b;
    }

    /* renamed from: c */
    public final String mo1333c() {
        return this.f1259c;
    }

    /* renamed from: d */
    public final aol mo1334d() {
        return this.f1260d;
    }

    public final String toString() {
        String str = this.f1258b;
        String str2 = this.f1259c;
        String str3 = this.f1257a;
        String valueOf = String.valueOf(this.f1260d);
        int length = str.length();
        int length2 = str2.length();
        StringBuilder sb = new StringBuilder(length + 14 + length2 + str3.length() + String.valueOf(valueOf).length());
        sb.append(str);
        sb.append(str2);
        sb.append(" NS(");
        sb.append(str3);
        sb.append("), FORM (");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
