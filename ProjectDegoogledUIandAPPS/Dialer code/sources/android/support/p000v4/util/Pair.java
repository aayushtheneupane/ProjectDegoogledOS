package android.support.p000v4.util;

import android.support.design.R$dimen;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v4.util.Pair */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (!R$dimen.equals(pair.first, this.first) || !R$dimen.equals(pair.second, this.second)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        F f = this.first;
        int i = 0;
        int hashCode = f == null ? 0 : f.hashCode();
        S s = this.second;
        if (s != null) {
            i = s.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Pair{");
        outline13.append(String.valueOf(this.first));
        outline13.append(" ");
        outline13.append(String.valueOf(this.second));
        outline13.append("}");
        return outline13.toString();
    }
}
