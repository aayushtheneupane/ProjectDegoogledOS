package p000;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: frm */
/* compiled from: PG */
public final class frm implements fwt, fwp, fwm, frn {

    /* renamed from: a */
    private final fru f10323a;

    /* renamed from: b */
    private final SparseArray f10324b = new SparseArray();

    /* renamed from: c */
    private final fro f10325c;

    public frm(fwc fwc, fro fro, fru fru) {
        this.f10325c = fro;
        this.f10323a = fru;
        fwc.mo6246a((fwt) this);
    }

    /* renamed from: a */
    private final void m9453a(int i, fri fri) {
        frj frj = (frj) this.f10324b.get(i);
        if (frj != null) {
            frj.mo4183a(fri.f10318b, fri.f10319c);
        }
    }

    /* renamed from: a */
    public final boolean mo6069a(fri fri) {
        fru fru = this.f10323a;
        int i = fri.f10317a;
        frl frl = new frl(this, fri);
        for (Integer num : fru.f10333b.mo6081a()) {
            if (fru.f10333b.mo6080a(num).intValue() == i) {
                frl.f10322b.m9453a(num.intValue(), frl.f10321a);
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public final void mo6070b() {
        this.f10325c.f10327b.remove(this);
    }

    /* renamed from: a */
    public final void mo6066a() {
        this.f10325c.f10327b.add(this);
        fru fru = this.f10323a;
        frk frk = new frk(this);
        ArrayList arrayList = new ArrayList(fru.f10333b.mo6081a());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Integer num = (Integer) arrayList.get(i);
            Integer a = fru.f10333b.mo6080a(num);
            int intValue = num.intValue();
            int intValue2 = a.intValue();
            frq frq = frk.f10320a.f10325c.f10328c;
            List<fri> list = (List) frq.f10329a.remove(Integer.valueOf(intValue2));
            if (list == null) {
                list = Collections.emptyList();
            }
            if (!list.isEmpty()) {
                for (fri a2 : list) {
                    frk.f10320a.m9453a(intValue, a2);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo6068a(int i, frj frj) {
        if (this.f10324b.get(i) == null) {
            this.f10324b.put(i, frj);
            return;
        }
        StringBuilder sb = new StringBuilder(66);
        sb.append("Cannot register more than one handler for a given  id: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public final void mo6067a(int i, Intent intent) {
        if (TextUtils.isEmpty(this.f10323a.f10334c.mo6084a(i))) {
            throw new IllegalArgumentException("You must use a resource id as the request code to guarantee overlap does not occur");
        } else if (intent == null) {
            throw new NullPointerException("Intent must not be null!");
        } else if (((frj) this.f10324b.get(i)) != null) {
            fru fru = this.f10323a;
            frt frt = fru.f10333b;
            Integer valueOf = Integer.valueOf(i);
            Integer a = frt.mo6080a(valueOf);
            if (a == null) {
                frw frw = fru.f10332a;
                int i2 = frw.f10336a;
                frw.f10336a = i2 + 1;
                a = Integer.valueOf(i2);
                fru.f10333b.f10331a.put(valueOf, a);
            }
            int intValue = a.intValue();
            fro fro = this.f10325c;
            int i3 = Build.VERSION.SDK_INT;
            fro.f10326a.startActivityForResult(intent, intValue, (Bundle) null);
        } else {
            StringBuilder sb = new StringBuilder(125);
            sb.append("You must register a result handler for request code ");
            sb.append(i);
            sb.append(" before starting an activity for result with that request code");
            throw new IllegalStateException(sb.toString());
        }
    }
}
