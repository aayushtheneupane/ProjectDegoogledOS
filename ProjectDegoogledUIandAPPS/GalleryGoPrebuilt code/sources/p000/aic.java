package p000;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: aic */
/* compiled from: PG */
public final class aic extends cjk {

    /* renamed from: g */
    private static final String f529g = iol.m14236b("WorkContinuationImpl");

    /* renamed from: a */
    public final aip f530a;

    /* renamed from: b */
    public final String f531b;

    /* renamed from: c */
    public final List f532c;

    /* renamed from: d */
    public final List f533d;

    /* renamed from: e */
    public boolean f534e;

    /* renamed from: f */
    public final int f535f;

    /* renamed from: h */
    private final List f536h;

    /* renamed from: i */
    private aho f537i;

    public aic(aip aip, String str, List list) {
        this(aip, str, 1, list);
    }

    private aic(aip aip, String str, int i, List list) {
        this.f530a = aip;
        this.f531b = str;
        this.f535f = i;
        this.f532c = list;
        this.f533d = new ArrayList(this.f532c.size());
        this.f536h = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String a = ((ahs) list.get(i2)).mo499a();
            this.f533d.add(a);
            this.f536h.add(a);
        }
    }

    public aic(aip aip, List list) {
        this(aip, (String) null, 2, list);
    }

    /* renamed from: a */
    public final aho mo517a() {
        if (this.f534e) {
            iol.m14231a();
            Log.w(f529g, String.format("Already enqueued work ids (%s)", new Object[]{TextUtils.join(", ", this.f533d)}));
        } else {
            alz alz = new alz(this);
            this.f530a.f555d.mo668a(alz);
            this.f537i = alz.f749a;
        }
        return this.f537i;
    }

    /* renamed from: b */
    public static Set m534b() {
        return new HashSet();
    }
}
