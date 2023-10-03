package p000;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

@Deprecated
/* renamed from: dd */
/* compiled from: PG */
public final class C0089dd {
    @Deprecated

    /* renamed from: a */
    public final int f6317a;
    @Deprecated

    /* renamed from: b */
    private final Set f6318b;
    @Deprecated

    /* renamed from: c */
    private final boolean f6319c;

    private C0089dd(int i, Set set, boolean z) {
        this.f6317a = i;
        this.f6318b = set;
        this.f6319c = z;
    }

    /* renamed from: a */
    private static void m5939a(int i, C0087db dbVar) {
        boolean z = false;
        boolean z2 = i == 1;
        if (dbVar.f6160b == 0) {
            z = true;
        }
        if (z2 != z) {
            String valueOf = String.valueOf(dbVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("Ill-formed number range: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: a */
    static C0089dd m5938a(String str) {
        int i;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (str.startsWith("integer")) {
            i = 1;
        } else if (str.startsWith("decimal")) {
            i = 2;
        } else {
            throw new IllegalArgumentException("Samples must start with 'integer' or 'decimal'");
        }
        boolean z = true;
        boolean z2 = false;
        for (String str2 : C0094di.f6577b.split(str.substring(7).trim())) {
            if (str2.equals("…") || str2.equals("...")) {
                z = false;
                z2 = true;
            } else if (z2) {
                String valueOf = String.valueOf(str2);
                throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Can only have … at the end of samples: ") : "Can only have … at the end of samples: ".concat(valueOf));
            } else {
                String[] split = C0094di.f6578c.split(str2);
                int length = split.length;
                if (length == 1) {
                    C0087db dbVar = new C0087db(split[0]);
                    m5939a(i, dbVar);
                    linkedHashSet.add(new C0088dc(dbVar, dbVar));
                } else if (length != 2) {
                    String valueOf2 = String.valueOf(str2);
                    throw new IllegalArgumentException(valueOf2.length() == 0 ? new String("Ill-formed number range: ") : "Ill-formed number range: ".concat(valueOf2));
                } else {
                    C0087db dbVar2 = new C0087db(split[0]);
                    C0087db dbVar3 = new C0087db(split[1]);
                    m5939a(i, dbVar2);
                    m5939a(i, dbVar3);
                    linkedHashSet.add(new C0088dc(dbVar2, dbVar3));
                }
            }
        }
        return new C0089dd(i, Collections.unmodifiableSet(linkedHashSet), z);
    }

    @Deprecated
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("@");
        boolean z = true;
        if (this.f6317a != 1) {
            str = "DECIMAL";
        } else {
            str = "INTEGER";
        }
        sb.append(str.toLowerCase(Locale.ENGLISH));
        for (C0088dc dcVar : this.f6318b) {
            if (!z) {
                sb.append(",");
            }
            sb.append(' ');
            sb.append(dcVar);
            z = false;
        }
        if (!this.f6319c) {
            sb.append(", …");
        }
        return sb.toString();
    }
}
