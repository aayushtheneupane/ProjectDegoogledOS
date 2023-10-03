package p000;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: d */
/* compiled from: PG */
public final class C0085d extends C0321lr {

    /* renamed from: a */
    public final Object f6130a = new Object();

    /* renamed from: b */
    public final ExecutorService f6131b = Executors.newFixedThreadPool(4, new C0056c());

    /* renamed from: c */
    public volatile Handler f6132c;

    /* renamed from: a */
    public static Handler m5793a(Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Handler.createAsync(looper);
        }
        int i = Build.VERSION.SDK_INT;
        Class<Handler> cls = Handler.class;
        try {
            return cls.getDeclaredConstructor(new Class[]{Looper.class, Handler.Callback.class, Boolean.TYPE}).newInstance(new Object[]{looper, null, true});
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            return new Handler(looper);
        } catch (InvocationTargetException e2) {
            return new Handler(looper);
        }
    }
}
