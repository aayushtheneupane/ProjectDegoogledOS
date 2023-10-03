package androidx.core.util;

import p026b.p027a.p030b.p031a.C0632a;

public class Pair {
    public final Object first;
    public final Object second;

    public Pair(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }

    public static Pair create(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (!ObjectsCompat.equals(pair.first, this.first) || !ObjectsCompat.equals(pair.second, this.second)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object obj = this.first;
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.second;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Pair{");
        Pa.append(String.valueOf(this.first));
        Pa.append(" ");
        Pa.append(String.valueOf(this.second));
        Pa.append("}");
        return Pa.toString();
    }
}
