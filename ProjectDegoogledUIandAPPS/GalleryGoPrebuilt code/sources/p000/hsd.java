package p000;

import java.util.Arrays;
import java.util.Collection;

/* renamed from: hsd */
/* compiled from: PG */
class hsd extends hse {

    /* renamed from: a */
    public Object[] f13338a;

    /* renamed from: b */
    public int f13339b = 0;

    /* renamed from: c */
    public boolean f13340c;

    public hsd(int i) {
        ife.m12839a(i, "initialCapacity");
        this.f13338a = new Object[i];
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ void mo7874b(Object obj) {
        throw null;
    }

    /* renamed from: a */
    public final void mo7873a(Object obj) {
        ife.m12898e(obj);
        mo7871a(this.f13339b + 1);
        Object[] objArr = this.f13338a;
        int i = this.f13339b;
        this.f13339b = i + 1;
        objArr[i] = obj;
    }

    /* renamed from: a */
    public final void mo7872a(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            mo7871a(this.f13339b + collection.size());
            if (collection instanceof hsf) {
                this.f13339b = ((hsf) collection).mo7875a(this.f13338a, this.f13339b);
                return;
            }
        }
        super.mo7872a(iterable);
    }

    /* renamed from: a */
    public final void mo7871a(int i) {
        Object[] objArr = this.f13338a;
        int length = objArr.length;
        if (length < i) {
            this.f13338a = Arrays.copyOf(objArr, m12003a(length, i));
            this.f13340c = false;
        } else if (this.f13340c) {
            this.f13338a = (Object[]) objArr.clone();
            this.f13340c = false;
        }
    }
}
