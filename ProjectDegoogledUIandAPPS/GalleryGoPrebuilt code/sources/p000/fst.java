package p000;

import android.content.Context;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/* renamed from: fst */
/* compiled from: PG */
public final class fst extends C0219hx {

    /* renamed from: j */
    public List f10547j;

    public fst(Context context) {
        super(context.getApplicationContext());
    }

    /* renamed from: a */
    public final void mo6169a(List list) {
        this.f10547j = list;
        C0223ia iaVar = this.f13817d;
        if (iaVar != null) {
            if (C0210ho.m11828a(2)) {
                "onLoadComplete: " + iaVar;
            }
            if (Looper.myLooper() != Looper.getMainLooper()) {
                ((C0010aj) iaVar).mo535b(list);
            } else {
                ((C0010aj) iaVar).mo512a(list);
            }
        }
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo6171b() {
        TreeSet treeSet = new TreeSet();
        String[] split = frz.m9474a(this.f13818e.getApplicationContext(), "third_party_license_metadata", 0, -1).split("\n");
        int length = split.length;
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            String str = split[i];
            int indexOf = str.indexOf(32);
            String[] split2 = str.substring(0, indexOf).split(":");
            boolean z = split2.length == 2 && indexOf > 0;
            String valueOf = String.valueOf(str);
            String str2 = valueOf.length() == 0 ? new String("Invalid license meta-data line:\n") : "Invalid license meta-data line:\n".concat(valueOf);
            if (z) {
                arrayList.add(new fsr(str.substring(indexOf + 1), Long.parseLong(split2[0]), Integer.parseInt(split2[1]), ""));
                i++;
            } else {
                throw new IllegalStateException(String.valueOf(str2));
            }
        }
        Collections.sort(arrayList);
        treeSet.addAll(arrayList);
        return Collections.unmodifiableList(new ArrayList(treeSet));
    }
}
