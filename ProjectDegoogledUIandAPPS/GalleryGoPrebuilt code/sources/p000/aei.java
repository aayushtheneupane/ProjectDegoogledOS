package p000;

import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/* renamed from: aei */
/* compiled from: PG */
final class aei implements C0627x {

    /* renamed from: a */
    private final aeo f273a;

    public aei(aeo aeo) {
        this.f273a = aeo;
    }

    /* renamed from: a */
    public final void mo3a(C0681z zVar, C0546u uVar) {
        if (uVar == C0546u.ON_CREATE) {
            zVar.mo5ad().mo65b(this);
            aem ai = this.f273a.mo6ai();
            if (ai.f276c) {
                Bundle bundle = ai.f275b;
                Bundle bundle2 = null;
                if (bundle != null) {
                    Bundle bundle3 = bundle.getBundle("androidx.savedstate.Restarter");
                    ai.f275b.remove("androidx.savedstate.Restarter");
                    if (ai.f275b.isEmpty()) {
                        ai.f275b = null;
                    }
                    bundle2 = bundle3;
                }
                if (bundle2 != null) {
                    ArrayList<String> stringArrayList = bundle2.getStringArrayList("classes_to_restore");
                    if (stringArrayList != null) {
                        int size = stringArrayList.size();
                        int i = 0;
                        while (i < size) {
                            String str = stringArrayList.get(i);
                            try {
                                Class<? extends U> asSubclass = Class.forName(str, false, aei.class.getClassLoader()).asSubclass(aek.class);
                                try {
                                    Constructor<? extends U> declaredConstructor = asSubclass.getDeclaredConstructor(new Class[0]);
                                    declaredConstructor.setAccessible(true);
                                    try {
                                        ((aek) declaredConstructor.newInstance(new Object[0])).mo248a();
                                        i++;
                                    } catch (Exception e) {
                                        throw new RuntimeException("Failed to instantiate " + str, e);
                                    }
                                } catch (NoSuchMethodException e2) {
                                    throw new IllegalStateException("Class" + asSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                                }
                            } catch (ClassNotFoundException e3) {
                                throw new RuntimeException("Class " + str + " wasn't found", e3);
                            }
                        }
                        return;
                    }
                    throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                return;
            }
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        throw new AssertionError("Next event must be ON_CREATE");
    }
}
