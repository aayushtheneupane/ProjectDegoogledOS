package p000;

import java.util.Collections;
import java.util.List;

/* renamed from: ch */
/* compiled from: PG */
public final class C0064ch {

    /* renamed from: a */
    private final String f4372a;

    /* renamed from: b */
    private final String f4373b;

    /* renamed from: c */
    private final String f4374c;

    /* renamed from: d */
    private final List f4375d;

    /* renamed from: e */
    private final List f4376e;

    public C0064ch(String str, String str2, String str3, List list, List list2) {
        this.f4372a = str;
        this.f4373b = str2;
        this.f4374c = str3;
        this.f4375d = Collections.unmodifiableList(list);
        this.f4376e = Collections.unmodifiableList(list2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0064ch) {
            C0064ch chVar = (C0064ch) obj;
            if (this.f4372a.equals(chVar.f4372a) && this.f4373b.equals(chVar.f4373b) && this.f4374c.equals(chVar.f4374c) && this.f4375d.equals(chVar.f4375d)) {
                return this.f4376e.equals(chVar.f4376e);
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((((((this.f4372a.hashCode() * 31) + this.f4373b.hashCode()) * 31) + this.f4374c.hashCode()) * 31) + this.f4375d.hashCode()) * 31) + this.f4376e.hashCode();
    }

    public final String toString() {
        return "ForeignKey{referenceTable='" + this.f4372a + "', onDelete='" + this.f4373b + "', onUpdate='" + this.f4374c + "', columnNames=" + this.f4375d + ", referenceColumnNames=" + this.f4376e + '}';
    }
}
