package p000;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gsg */
/* compiled from: PG */
public final class gsg extends C0147fh implements gsj {

    /* renamed from: a */
    public Executor f11956a;

    /* renamed from: b */
    public final grs f11957b = new grs("FuturesMixinRF");

    /* renamed from: c */
    public Set f11958c;

    /* renamed from: d */
    public boolean f11959d = false;

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        mo5630K();
        if (bundle != null) {
            this.f11958c = new HashSet(r4);
            for (Parcelable parcelable : bundle.getParcelableArray("future_wrappers")) {
                this.f11958c.add((gsk) parcelable);
            }
        } else {
            this.f11958c = new HashSet(1);
        }
        grs grs = this.f11957b;
        fxk.m9830b();
        if (bundle != null) {
            String str = grs.f11927c;
            ife.m12876b(bundle.containsKey(str.length() == 0 ? new String("CallbackIdMap.classes") : "CallbackIdMap.classes".concat(str)), (Object) "CallbackIdMap writes its keys unconditionally. It did not find its state on restore, which suggests state loss.");
            String str2 = grs.f11927c;
            String[] stringArray = bundle.getStringArray(str2.length() == 0 ? new String("CallbackIdMap.classes") : "CallbackIdMap.classes".concat(str2));
            String str3 = grs.f11927c;
            int[] intArray = bundle.getIntArray(str3.length() == 0 ? new String("CallbackIdMap.class_ids") : "CallbackIdMap.class_ids".concat(str3));
            if (stringArray == null && intArray == null) {
                ((hvv) ((hvv) ((hvv) grs.f11925a.mo8178a()).mo8200a(hwo.FULL)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/CallbackIdMap", "restore", 214, "CallbackIdMap.java")).mo8203a("Detected b/38213128 (all state)");
            } else if (stringArray == null) {
                ((hvv) ((hvv) ((hvv) grs.f11925a.mo8178a()).mo8200a(hwo.FULL)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/CallbackIdMap", "restore", 219, "CallbackIdMap.java")).mo8203a("Detected b/38213128 (callbacks)");
            } else if (intArray != null) {
                int i = 0;
                while (i < stringArray.length) {
                    try {
                        Integer num = (Integer) grs.f11929e.put(Class.forName(stringArray[i]), Integer.valueOf(intArray[i]));
                        if (num != null) {
                            int intValue = num.intValue();
                            int i2 = intArray[i];
                            String str4 = stringArray[i];
                            Integer valueOf = Integer.valueOf(i2);
                            if (intValue != i2) {
                                throw new IllegalStateException(ife.m12834a("Callback ID for class %s was restored with ID %s, but had an existing mapping of %s. Always register for FuturesMixin callbacks and subscribe to SubscriptionMixin callbacks in onCreate()! Do *not* subscribe in a Peer's constructor", str4, valueOf, num));
                            }
                        }
                        i++;
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                ((hvv) ((hvv) ((hvv) grs.f11925a.mo8178a()).mo8200a(hwo.FULL)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/CallbackIdMap", "restore", 224, "CallbackIdMap.java")).mo8203a("Detected b/38213128 (callback ids)");
            }
        }
    }

    /* renamed from: c */
    public final void mo1834c() {
        super.mo1834c();
        ife.m12876b(!this.f11959d, (Object) "FuturesMixinRetainedFragment.stopCallbacks() must be called before it becomes detached from its parent.");
        this.f11957b.mo6985b();
        this.f11956a = null;
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        Set set = this.f11958c;
        bundle.putParcelableArray("future_wrappers", (Parcelable[]) set.toArray(new gsk[set.size()]));
        grs grs = this.f11957b;
        fxk.m9830b();
        String str = grs.f11927c;
        boolean containsKey = bundle.containsKey(str.length() == 0 ? new String("CallbackIdMap.classes") : "CallbackIdMap.classes".concat(str));
        String str2 = grs.f11927c;
        StringBuilder sb = new StringBuilder(str2.length() + 300);
        sb.append("Bundle already contains key CallbackIdMap.classes");
        sb.append(str2);
        sb.append(". This suggests that two instances of CallbackIdMap were created with the same key in the same Fragment or Activity. This creates state store/restore collisions. Check for bugs where the same mixin is created for a Fragment twice during one lifecycle.");
        ife.m12876b(!containsKey, (Object) sb.toString());
        C0290kn knVar = grs.f11929e;
        int i = knVar.f15193b;
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        int i2 = 0;
        for (Map.Entry entry : knVar.entrySet()) {
            strArr[i2] = ((Class) entry.getKey()).getName();
            iArr[i2] = ((Integer) entry.getValue()).intValue();
            i2++;
        }
        String str3 = grs.f11927c;
        bundle.putStringArray(str3.length() == 0 ? new String("CallbackIdMap.classes") : "CallbackIdMap.classes".concat(str3), strArr);
        String str4 = grs.f11927c;
        bundle.putIntArray(str4.length() == 0 ? new String("CallbackIdMap.class_ids") : "CallbackIdMap.class_ids".concat(str4), iArr);
    }
}
