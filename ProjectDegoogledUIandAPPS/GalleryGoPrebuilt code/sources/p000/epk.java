package p000;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: epk */
/* compiled from: PG */
public final class epk {

    /* renamed from: a */
    public final Set f8776a;

    /* renamed from: b */
    public final Set f8777b;

    /* renamed from: c */
    public final Map f8778c;

    /* renamed from: d */
    public final String f8779d;

    /* renamed from: e */
    public final String f8780e;

    /* renamed from: f */
    public final ewd f8781f;

    /* renamed from: g */
    public Integer f8782g;

    public epk(Set set, Map map, String str, String str2, ewd ewd) {
        this.f8776a = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.f8778c = map == null ? Collections.emptyMap() : map;
        this.f8779d = str;
        this.f8780e = str2;
        this.f8781f = ewd;
        HashSet hashSet = new HashSet(this.f8776a);
        for (C0652xy xyVar : this.f8778c.values()) {
            hashSet.addAll((Collection) null);
        }
        this.f8777b = Collections.unmodifiableSet(hashSet);
    }
}
