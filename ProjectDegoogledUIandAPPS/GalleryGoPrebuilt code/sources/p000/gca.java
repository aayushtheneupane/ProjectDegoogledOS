package p000;

/* renamed from: gca */
/* compiled from: PG */
public final class gca {

    /* renamed from: a */
    public static final gca f10904a = new gca("VisionKit");

    /* renamed from: b */
    public final String f10905b;

    private gca(String str) {
        boolean z;
        if (str.length() < 23) {
            z = true;
        } else {
            z = false;
        }
        ife.m12845a(z, (Object) "Android Logging mandates tags be less than 23 characters.");
        this.f10905b = str;
    }
}
