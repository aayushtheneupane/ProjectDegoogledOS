package p000;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: afu */
/* compiled from: PG */
public final class afu {

    /* renamed from: a */
    public final Map f355a = new HashMap();

    /* renamed from: b */
    public View f356b;

    /* renamed from: c */
    public final ArrayList f357c = new ArrayList();

    @Deprecated
    public afu() {
    }

    public afu(View view) {
        this.f356b = view;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof afu)) {
            return false;
        }
        afu afu = (afu) obj;
        return this.f356b == afu.f356b && this.f355a.equals(afu.f355a);
    }

    public final int hashCode() {
        return (this.f356b.hashCode() * 31) + this.f355a.hashCode();
    }

    public final String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f356b + "\n") + "    values:";
        for (String str2 : this.f355a.keySet()) {
            str = str + "    " + str2 + ": " + this.f355a.get(str2) + "\n";
        }
        return str;
    }
}
