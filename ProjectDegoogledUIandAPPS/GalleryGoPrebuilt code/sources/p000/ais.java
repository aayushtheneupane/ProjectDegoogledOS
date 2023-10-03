package p000;

import android.content.Context;
import androidx.work.impl.WorkDatabase;
import java.util.List;

/* renamed from: ais */
/* compiled from: PG */
public final class ais {

    /* renamed from: a */
    public final Context f566a;

    /* renamed from: b */
    public final amz f567b;

    /* renamed from: c */
    public final agz f568c;

    /* renamed from: d */
    public final WorkDatabase f569d;

    /* renamed from: e */
    public final String f570e;

    /* renamed from: f */
    public List f571f;

    /* renamed from: g */
    public ckx f572g = new ckx();

    public ais(Context context, agz agz, amz amz, WorkDatabase workDatabase, String str) {
        this.f566a = context.getApplicationContext();
        this.f567b = amz;
        this.f568c = agz;
        this.f569d = workDatabase;
        this.f570e = str;
    }
}
