package p000;

@Deprecated
/* renamed from: dc */
/* compiled from: PG */
public final class C0088dc {
    @Deprecated

    /* renamed from: a */
    private final C0087db f6226a;
    @Deprecated

    /* renamed from: b */
    private final C0087db f6227b;

    @Deprecated
    public C0088dc(C0087db dbVar, C0087db dbVar2) {
        if (dbVar.f6160b == dbVar2.f6160b) {
            this.f6226a = dbVar;
            this.f6227b = dbVar2;
            return;
        }
        String valueOf = String.valueOf(dbVar);
        String valueOf2 = String.valueOf(dbVar2);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 55 + String.valueOf(valueOf2).length());
        sb.append("Ranges must have the same number of visible decimals: ");
        sb.append(valueOf);
        sb.append("~");
        sb.append(valueOf2);
        throw new IllegalArgumentException(sb.toString());
    }

    @Deprecated
    public final String toString() {
        String str;
        String valueOf = String.valueOf(this.f6226a);
        C0087db dbVar = this.f6227b;
        if (dbVar != this.f6226a) {
            String valueOf2 = String.valueOf(dbVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 1);
            sb.append("~");
            sb.append(valueOf2);
            str = sb.toString();
        } else {
            str = "";
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + String.valueOf(str).length());
        sb2.append(valueOf);
        sb2.append(str);
        return sb2.toString();
    }
}
