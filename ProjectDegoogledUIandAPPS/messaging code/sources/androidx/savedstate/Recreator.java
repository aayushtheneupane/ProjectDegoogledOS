package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.C0449f;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.Lifecycle$Event;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import p026b.p027a.p030b.p031a.C0632a;

@SuppressLint({"RestrictedApi"})
final class Recreator implements C0449f {
    private final C0609e mOwner;

    Recreator(C0609e eVar) {
        this.mOwner = eVar;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_CREATE) {
            jVar.getLifecycle().mo4461b(this);
            Bundle B = this.mOwner.getSavedStateRegistry().mo5282B("androidx.savedstate.Restarter");
            if (B != null) {
                ArrayList<String> stringArrayList = B.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            Class<? extends U> asSubclass = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(C0605a.class);
                            try {
                                Constructor<? extends U> declaredConstructor = asSubclass.getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                                try {
                                    ((C0605a) declaredConstructor.newInstance(new Object[0])).mo5280a(this.mOwner);
                                } catch (Exception e) {
                                    throw new RuntimeException(C0632a.m1025k("Failed to instantiate ", next), e);
                                }
                            } catch (NoSuchMethodException e2) {
                                StringBuilder Pa = C0632a.m1011Pa("Class");
                                Pa.append(asSubclass.getSimpleName());
                                Pa.append(" must have default constructor in order to be automatically recreated");
                                throw new IllegalStateException(Pa.toString(), e2);
                            }
                        } catch (ClassNotFoundException e3) {
                            throw new RuntimeException(C0632a.m1023d("Class ", next, " wasn't found"), e3);
                        }
                    }
                    return;
                }
                throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
            }
            return;
        }
        throw new AssertionError("Next event must be ON_CREATE");
    }
}
