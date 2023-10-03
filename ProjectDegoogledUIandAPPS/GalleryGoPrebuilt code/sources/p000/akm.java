package p000;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* renamed from: akm */
/* compiled from: PG */
public final class akm implements akk {

    /* renamed from: a */
    private final C0053bx f690a;

    /* renamed from: b */
    private final C0047br f691b;

    public akm(C0053bx bxVar) {
        this.f690a = bxVar;
        this.f691b = new akl(bxVar);
    }

    /* renamed from: b */
    public final List mo577b(String str) {
        C0057ca a = C0057ca.m3923a("SELECT work_spec_id FROM dependency WHERE prerequisite_id=?", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f690a.mo2844d();
        Cursor a2 = this.f690a.mo2840a((C0036bg) a);
        try {
            ArrayList arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: a */
    public final boolean mo576a(String str) {
        boolean z = true;
        C0057ca a = C0057ca.m3923a("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f690a.mo2844d();
        Cursor a2 = this.f690a.mo2840a((C0036bg) a);
        try {
            if (!a2.moveToFirst() || a2.getInt(0) == 0) {
                z = false;
            }
            return z;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: a */
    public final void mo575a(akj akj) {
        this.f690a.mo2844d();
        this.f690a.mo2845e();
        try {
            this.f691b.mo2686a(akj);
            this.f690a.mo2847g();
        } finally {
            this.f690a.mo2846f();
        }
    }
}
