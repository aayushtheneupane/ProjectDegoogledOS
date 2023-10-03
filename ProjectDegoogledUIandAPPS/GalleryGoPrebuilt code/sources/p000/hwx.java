package p000;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* renamed from: hwx */
/* compiled from: PG */
public final class hwx implements hwy {

    /* renamed from: a */
    private static final Set f13563a = new HashSet(Arrays.asList(new Class[]{Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class}));

    /* renamed from: b */
    private final String f13564b;

    /* renamed from: c */
    private final String f13565c;

    /* renamed from: d */
    private final StringBuilder f13566d;

    /* renamed from: e */
    private boolean f13567e = false;

    public hwx(String str, String str2, StringBuilder sb) {
        this.f13564b = str;
        this.f13565c = str2;
        this.f13566d = sb;
    }

    /* renamed from: a */
    public final void mo8238a() {
        if (this.f13567e) {
            this.f13566d.append(this.f13565c);
        }
    }

    /* renamed from: a */
    public final void mo8239a(String str, Object obj) {
        char c = ' ';
        if (this.f13567e) {
            this.f13566d.append(' ');
        } else {
            if (this.f13566d.length() > 0) {
                StringBuilder sb = this.f13566d;
                if (sb.length() > 1000 || this.f13566d.indexOf("\n") != -1) {
                    c = 10;
                }
                sb.append(c);
            }
            this.f13566d.append(this.f13564b);
            this.f13567e = true;
        }
        StringBuilder sb2 = this.f13566d;
        sb2.append(str);
        sb2.append('=');
        if (obj == null) {
            this.f13566d.append(true);
        } else if (f13563a.contains(obj.getClass())) {
            this.f13566d.append(obj);
        } else {
            this.f13566d.append('\"');
            StringBuilder sb3 = this.f13566d;
            String obj2 = obj.toString();
            int i = 0;
            while (true) {
                int a = m12347a(obj2, i);
                if (a != -1) {
                    sb3.append(obj2, i, a);
                    i = a + 1;
                    char charAt = obj2.charAt(a);
                    if (charAt == 9) {
                        charAt = 't';
                    } else if (charAt == 10) {
                        charAt = 'n';
                    } else if (charAt == 13) {
                        charAt = 'r';
                    } else if (!(charAt == '\"' || charAt == '\\')) {
                        sb3.append(65533);
                    }
                    sb3.append("\\");
                    sb3.append(charAt);
                } else {
                    sb3.append(obj2, i, obj2.length());
                    this.f13566d.append('\"');
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private static int m12347a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt == '\"' || charAt == '\\') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
