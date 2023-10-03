package p000;

/* renamed from: foh */
/* compiled from: PG */
public final class foh {

    /* renamed from: a */
    public final String f10141a;

    /* renamed from: b */
    public final int f10142b;

    /* renamed from: c */
    public final /* synthetic */ foi f10143c;

    public /* synthetic */ foh(foi foi) {
        this.f10143c = foi;
        this.f10141a = "";
        this.f10142b = 0;
    }

    public /* synthetic */ foh(foi foi, foh foh, String str) {
        this.f10143c = foi;
        if (foh.f10142b != 0) {
            String str2 = foh.f10141a;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
            sb.append(str2);
            sb.append('/');
            sb.append(str);
            str = sb.toString();
        }
        this.f10141a = str;
        this.f10142b = foh.f10142b + 1;
    }
}
