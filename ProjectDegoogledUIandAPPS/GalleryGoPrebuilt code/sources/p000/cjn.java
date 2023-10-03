package p000;

import java.util.regex.Pattern;

/* renamed from: cjn */
/* compiled from: PG */
public final class cjn {

    /* renamed from: a */
    public static final Pattern f4503a = Pattern.compile("(?i)^/storage/([^/]+)/(?:[0-9]+/)?(?:Android/sandbox/[^/]+/)?((?:[^/]+/)*)([^/]+)$");

    /* renamed from: b */
    public final String f4504b;

    /* renamed from: c */
    public final String f4505c;

    /* renamed from: d */
    public final String f4506d;

    public cjn(String str, String str2, String str3) {
        this.f4504b = str;
        this.f4505c = str2;
        this.f4506d = str3;
    }

    /* renamed from: a */
    public static boolean m4401a(String str, String str2) {
        return str.equals(str2) || str.startsWith(String.valueOf(str2).concat("/"));
    }
}
