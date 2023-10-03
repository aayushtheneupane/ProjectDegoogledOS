package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: fua */
/* compiled from: PG */
public final class fua {

    /* renamed from: d */
    private static final Object f10596d = new Object();

    /* renamed from: e */
    private static final fue f10597e = new fue(new fuh());

    /* renamed from: k */
    private static final fuk f10598k = new fuk("debug.binder.verification", (short[]) null);

    /* renamed from: a */
    public final CopyOnWriteArrayList f10599a;

    /* renamed from: b */
    public volatile boolean f10600b;

    /* renamed from: c */
    public volatile fuc f10601c;

    /* renamed from: f */
    private Context f10602f;

    /* renamed from: g */
    private String f10603g;

    /* renamed from: h */
    private final Map f10604h = Collections.synchronizedMap(new HashMap());

    /* renamed from: i */
    private final Map f10605i = Collections.synchronizedMap(new HashMap());

    /* renamed from: j */
    private final ThreadLocal f10606j;

    static {
        foj.m9315b(new fuk("debug.binder.strict_mode", (short[]) null));
        new fuk("test.binder.trace", (char[]) null);
        new fuk("test.binder.detail_trace", (char[]) null);
    }

    public fua() {
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedSet(new HashSet());
        this.f10599a = new CopyOnWriteArrayList();
        this.f10606j = new ThreadLocal();
        this.f10601c = new fuj();
    }

    private fua(Context context) {
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedSet(new HashSet());
        this.f10599a = new CopyOnWriteArrayList();
        this.f10606j = new ThreadLocal();
        this.f10601c = new fuj();
        this.f10602f = context;
        this.f10603g = context.getClass().getName();
    }

    /* renamed from: a */
    public static Object m9628a(Context context, Class cls) {
        fua fua;
        Context applicationContext = context.getApplicationContext();
        boolean z = false;
        while (true) {
            if (context instanceof fub) {
                fua = ((fub) context).mo6185a();
                if (fua == null) {
                    String valueOf = String.valueOf(context);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43);
                    sb.append("BinderContext must not return null Binder: ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                fua = null;
            }
            if (fua != null) {
                break;
            }
            z |= context == applicationContext;
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
                if (context == null) {
                    throw new IllegalStateException("Invalid ContextWrapper -- If this is a Robolectric test, have you called ActivityController.create()?");
                }
            } else if (!z) {
                context = applicationContext;
                continue;
            } else {
                context = null;
                continue;
            }
            if (context == null) {
                fue fue = f10597e;
                Context applicationContext2 = applicationContext.getApplicationContext();
                if (fue.f10607a == null) {
                    synchronized (fue.f10608b) {
                        if (fue.f10607a == null) {
                            fua fua2 = new fua(applicationContext2);
                            fue.f10609c.mo6187a(fua2);
                            fue.f10607a = fua2;
                        }
                    }
                }
                fua = fue.f10607a;
            }
        }
        return fua.mo6183a(cls);
    }

    /* renamed from: a */
    public final Object mo6183a(Class cls) {
        fxk.m9821a((Object) cls);
        Object c = m9629c(cls);
        if (c != null) {
            return c;
        }
        String str = "Unbound type: " + cls.getName() + "\nSearched binders:\n" + this.f10603g;
        IllegalStateException illegalStateException = new IllegalStateException(str);
        Log.e("Binder", str, illegalStateException);
        throw illegalStateException;
    }

    /* renamed from: b */
    public final Object mo6184b(Class cls) {
        return m9629c(cls);
    }

    /* renamed from: c */
    private final Object m9629c(Class cls) {
        Object obj;
        fxk.m9821a((Object) cls);
        fxk.m9821a((Object) cls);
        if (this.f10602f != null) {
            synchronized (this.f10601c.mo6186a(cls)) {
                Object obj2 = this.f10604h.get(cls);
                if (obj2 == null) {
                    Boolean bool = (Boolean) this.f10606j.get();
                    boolean z = bool != null && bool.booleanValue();
                    if (!z) {
                        this.f10606j.set(true);
                    }
                    try {
                        int size = this.f10599a.size();
                        for (int i = 0; i < size; i++) {
                            fug fug = (fug) this.f10599a.get(i);
                            Object[] objArr = {fug, cls};
                            fug.mo6188a();
                            if (!foj.m9315b(f10598k)) {
                                obj = this.f10604h.get(cls);
                                if (obj != null && obj != f10596d) {
                                    if (!z) {
                                        this.f10606j.set(false);
                                    }
                                }
                            }
                        }
                        if (!z) {
                            this.f10606j.set(false);
                        }
                        obj = this.f10604h.get(cls);
                        if (obj == null) {
                            if (foj.m9315b(f10598k)) {
                                if (this.f10605i.containsKey(cls)) {
                                    String valueOf = String.valueOf(cls);
                                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                                    sb.append("get() called for multibound object: ");
                                    sb.append(valueOf);
                                    throw new IllegalStateException(sb.toString());
                                }
                            }
                            this.f10604h.put(cls, f10596d);
                        }
                    } catch (Throwable th) {
                        if (!z) {
                            this.f10606j.set(false);
                        }
                        throw th;
                    }
                } else {
                    if (obj2 == f10596d) {
                        obj2 = null;
                    }
                    obj = obj2;
                }
            }
            if (obj != null) {
                return obj;
            }
            return null;
        }
        throw new IllegalStateException("Binder not initialized yet.");
    }
}
