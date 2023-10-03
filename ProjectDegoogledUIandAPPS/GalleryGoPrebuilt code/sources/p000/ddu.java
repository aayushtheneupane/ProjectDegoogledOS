package p000;

/* renamed from: ddu */
/* compiled from: PG */
public final class ddu implements dds {

    /* renamed from: a */
    private boolean f6382a;

    /* renamed from: b */
    private boolean f6383b;

    /* renamed from: b */
    public final synchronized void mo4081b() {
        if (!this.f6383b) {
            try {
                ank.f1195a.mo1271a("http://ns.google.com/photos/1.0/container/", "Container");
                ank.f1195a.mo1271a("http://ns.google.com/photos/1.0/container/item", "Item");
            } catch (ang e) {
                new Object[1][0] = "http://ns.google.com/photos/1.0/container/";
            }
            this.f6383b = true;
        }
    }

    /* renamed from: a */
    public final synchronized void mo4080a() {
        if (!this.f6382a) {
            try {
                ank.f1195a.mo1271a("http://ns.google.com/photos/1.0/camera/", "GCamera");
            } catch (ang e) {
                cwn.m5515b((Throwable) e, "Failed to register namespace", new Object[0]);
            }
            this.f6382a = true;
        }
    }

    /* renamed from: c */
    public static Integer m5973c(ani ani, String str, String str2) {
        try {
            if (ani.mo1264c(str, str2)) {
                return ani.mo1265d(str, str2);
            }
            return null;
        } catch (ang e) {
            cwn.m5511a((Throwable) e, "Error looking up XMP property. xmpMeta: %s; namespace: %s; property: %s", ani, str, str2);
            return null;
        }
    }

    /* renamed from: b */
    public static Boolean m5972b(ani ani, String str, String str2) {
        try {
            if (!ani.mo1264c(str, str2)) {
                return null;
            }
            return Boolean.valueOf(ani.mo1265d(str, str2).intValue() == 1);
        } catch (ang e) {
            cwn.m5511a((Throwable) e, "Error looking up XMP property. xmpMeta: %s; namespace: %s; property: %s", ani, str, str2);
            return false;
        }
    }

    /* renamed from: d */
    public static Long m5974d(ani ani, String str, String str2) {
        try {
            if (ani.mo1264c(str, str2)) {
                return ani.mo1266e(str, str2);
            }
            return null;
        } catch (ang e) {
            cwn.m5511a((Throwable) e, "Error looking up XMP property. xmpMeta: %s; namespace: %s; property: %s", ani, str, str2);
            return null;
        }
    }

    /* renamed from: a */
    public static String m5970a(ani ani, String str, String str2) {
        try {
            if (ani.mo1264c(str, str2)) {
                return ani.mo1267f(str, str2);
            }
            return null;
        } catch (ang e) {
            cwn.m5511a((Throwable) e, "Error looking up XMP property. xmpMeta: %s; namespace: %s; property: %s", ani, str, str2);
            return null;
        }
    }

    /* renamed from: a */
    public static String m5971a(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }
}
