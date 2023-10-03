package p000;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* renamed from: gmw */
/* compiled from: PG */
final /* synthetic */ class gmw implements Callable {

    /* renamed from: a */
    private final gmy f11638a;

    public gmw(gmy gmy) {
        this.f11638a = gmy;
    }

    public final Object call() {
        gna gna = (gna) this.f11638a.f11640a.get();
        SharedPreferences.Editor edit = gna.f11660a.edit();
        ArrayList arrayList = gna.f11661b;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            edit.remove((String) arrayList.get(i));
        }
        gna.f11661b.clear();
        return Boolean.valueOf(edit.commit());
    }
}
