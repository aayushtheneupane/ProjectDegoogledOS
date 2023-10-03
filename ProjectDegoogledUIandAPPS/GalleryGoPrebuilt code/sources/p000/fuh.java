package p000;

import android.util.Log;

/* renamed from: fuh */
/* compiled from: PG */
public final class fuh implements fud {
    /* renamed from: a */
    public final void mo6187a(fua fua) {
        try {
            fui fui = (fui) Class.forName("gen_binder.root.RootModule$Generated").newInstance();
            boolean z = fua.f10600b;
            fua.f10599a.add(fui);
            fui.mo6189b();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to instantiate root module gen_binder.root.RootModule$Generated", e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException("Failed to instantiate root module gen_binder.root.RootModule$Generated", e2);
        } catch (ClassNotFoundException e3) {
            if (Log.isLoggable("Binder", 5)) {
                Log.w("Binder", "To use Binder more efficiently, your android_binary target should include \"//java/com/google/android/libraries/stitch/binder:rootmodule\" in srcs.");
            }
        }
        synchronized (fua) {
            if (fua.f10601c instanceof fuj) {
                fua.f10601c = new fuf();
            }
        }
    }
}
