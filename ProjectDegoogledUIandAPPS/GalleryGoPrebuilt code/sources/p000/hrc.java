package p000;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* renamed from: hrc */
/* compiled from: PG */
public abstract class hrc extends hri implements Serializable {
    public static final long serialVersionUID = 2447537837011683357L;

    /* renamed from: a */
    public transient Map f13294a;

    /* renamed from: b */
    public transient int f13295b;

    protected hrc(Map map) {
        ife.m12890c(map.isEmpty());
        this.f13294a = map;
    }

    /* renamed from: a */
    public abstract Collection mo7691a();

    /* renamed from: a */
    public Collection mo7692a(Object obj, Collection collection) {
        throw null;
    }

    /* renamed from: a */
    static /* synthetic */ void m11942a(hrc hrc) {
        hrc.f13295b++;
    }

    /* renamed from: b */
    static /* synthetic */ void m11944b(hrc hrc) {
        hrc.f13295b--;
    }

    /* renamed from: a */
    static /* synthetic */ void m11943a(hrc hrc, int i) {
        hrc.f13295b += i;
    }

    /* renamed from: b */
    static /* synthetic */ void m11945b(hrc hrc, int i) {
        hrc.f13295b -= i;
    }

    /* renamed from: c */
    public final Map mo7764c() {
        return new hqu(this, this.f13294a);
    }

    /* renamed from: b */
    public final Set mo7763b() {
        return new hqw(this, this.f13294a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final List mo7762a(Object obj, List list, hqz hqz) {
        if (list instanceof RandomAccess) {
            return new hqx(this, obj, list, hqz);
        }
        return new hrb(this, obj, list, hqz);
    }
}
