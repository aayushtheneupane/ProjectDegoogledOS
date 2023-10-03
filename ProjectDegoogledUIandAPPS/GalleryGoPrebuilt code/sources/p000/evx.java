package p000;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.RemoteException;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: evx */
/* compiled from: PG */
public final class evx {

    /* renamed from: a */
    private static final Object f9107a = new Object();

    /* renamed from: b */
    private static Method f9108b = null;

    static {
        int i = ejx.f8457c;
    }

    /* renamed from: a */
    public static void m8266a(Context context) {
        Context context2;
        Cursor cursor;
        erq erq;
        ThreadLocal threadLocal;
        Boolean bool;
        erf erf;
        eru eru;
        Boolean valueOf;
        erf erf2;
        Context context3 = context;
        abj.m86a((Object) context3, (Object) "Context must not be null");
        ekh.m7671b(context);
        try {
            erp erp = erq.f8879f;
            erk erk = (erk) erq.f8877d.get();
            erk erk2 = new erk((byte[]) null);
            erq.f8877d.set(erk2);
            try {
                ero a = erp.mo5180a(context3, "providerinstaller", erq.f8878e);
                int i = a.f8871a;
                int i2 = a.f8872b;
                StringBuilder sb = new StringBuilder(102);
                sb.append("Considering local module ");
                sb.append("providerinstaller");
                sb.append(":");
                sb.append(i);
                sb.append(" and remote module ");
                sb.append("providerinstaller");
                sb.append(":");
                sb.append(i2);
                sb.toString();
                int i3 = a.f8873c;
                if (i3 == 0 || ((i3 == -1 && a.f8871a == 0) || (i3 == 1 && a.f8872b == 0))) {
                    int i4 = a.f8871a;
                    int i5 = a.f8872b;
                    StringBuilder sb2 = new StringBuilder(91);
                    sb2.append("No acceptable module found. Local version is ");
                    sb2.append(i4);
                    sb2.append(" and remote version is ");
                    sb2.append(i5);
                    sb2.append(".");
                    throw new erm(sb2.toString());
                }
                if (i3 == -1) {
                    erq = erq.m8066b(context3, "providerinstaller");
                    if (cursor != null) {
                        cursor.close();
                    }
                } else if (i3 == 1) {
                    try {
                        int i6 = a.f8872b;
                        try {
                            synchronized (erq.class) {
                                bool = erq.f8874a;
                            }
                            if (bool != null) {
                                if (bool.booleanValue()) {
                                    StringBuilder sb3 = new StringBuilder(68);
                                    sb3.append("Selected remote version of ");
                                    sb3.append("providerinstaller");
                                    sb3.append(", version >= ");
                                    sb3.append(i6);
                                    sb3.toString();
                                    synchronized (erq.class) {
                                        eru = erq.f8875b;
                                    }
                                    if (eru != null) {
                                        erk erk3 = (erk) erq.f8877d.get();
                                        if (erk3 == null || erk3.f8869a == null) {
                                            throw new erm("No result cursor");
                                        }
                                        Context applicationContext = context.getApplicationContext();
                                        Cursor cursor2 = erk3.f8869a;
                                        erg.m8051a((Object) null);
                                        synchronized (erq.class) {
                                            valueOf = Boolean.valueOf(erq.f8876c >= 2);
                                        }
                                        if (!valueOf.booleanValue()) {
                                            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                            erf2 = eru.mo5187a(erg.m8051a((Object) applicationContext), "providerinstaller", i6, erg.m8051a((Object) cursor2));
                                        } else {
                                            erf2 = eru.mo5188b(erg.m8051a((Object) applicationContext), "providerinstaller", i6, erg.m8051a((Object) cursor2));
                                        }
                                        Context context4 = (Context) erg.m8052a(erf2);
                                        if (context4 != null) {
                                            erq = new erq(context4);
                                        } else {
                                            throw new erm("Failed to get module context");
                                        }
                                    } else {
                                        throw new erm("DynamiteLoaderV2 was not cached.");
                                    }
                                } else {
                                    StringBuilder sb4 = new StringBuilder(68);
                                    sb4.append("Selected remote version of ");
                                    sb4.append("providerinstaller");
                                    sb4.append(", version >= ");
                                    sb4.append(i6);
                                    sb4.toString();
                                    ers a2 = erq.m8063a(context);
                                    if (a2 != null) {
                                        if (a2.mo5184b() < 2) {
                                            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                            erf = a2.mo5183a(erg.m8051a((Object) context), "providerinstaller", i6);
                                        } else {
                                            erf = a2.mo5186b(erg.m8051a((Object) context), "providerinstaller", i6);
                                        }
                                        if (erg.m8052a(erf) != null) {
                                            erq = new erq((Context) erg.m8052a(erf));
                                        } else {
                                            throw new erm("Failed to load remote module.");
                                        }
                                    } else {
                                        throw new erm("Failed to create IDynamiteLoader.");
                                    }
                                }
                                Cursor cursor3 = erk2.f8869a;
                                if (cursor3 != null) {
                                    cursor3.close();
                                }
                                threadLocal = erq.f8877d;
                                threadLocal.set(erk);
                            } else {
                                throw new erm("Failed to determine which loading route to use.");
                            }
                        } catch (RemoteException e) {
                            throw new erm("Failed to load remote module.", e);
                        } catch (erm e2) {
                            throw e2;
                        } catch (Throwable th) {
                            abj.m89a(context3, th);
                            throw new erm("Failed to load remote module.", th);
                        }
                    } catch (erm e3) {
                        String valueOf2 = String.valueOf(e3.getMessage());
                        Log.w("DynamiteModule", valueOf2.length() == 0 ? new String("Failed to load remote module: ") : "Failed to load remote module: ".concat(valueOf2));
                        int i7 = a.f8871a;
                        if (i7 == 0 || erp.mo5180a(context3, "providerinstaller", new erl(i7)).f8873c != -1) {
                            throw new erm("Remote load failed. No local fallback found.", e3);
                        }
                        erq = erq.m8066b(context3, "providerinstaller");
                        Cursor cursor4 = erk2.f8869a;
                        if (cursor4 != null) {
                            cursor4.close();
                        }
                        threadLocal = erq.f8877d;
                    }
                } else {
                    StringBuilder sb5 = new StringBuilder(47);
                    sb5.append("VersionPolicy returned invalid code:");
                    sb5.append(0);
                    throw new erm(sb5.toString());
                }
                context2 = erq.f8882g;
                if (context2 == null) {
                    context2 = m8267b(context);
                }
                if (context2 != null) {
                    synchronized (f9107a) {
                        try {
                            if (f9108b == null) {
                                f9108b = context2.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[]{Context.class});
                            }
                            f9108b.invoke((Object) null, new Object[]{context2});
                        } catch (Exception e4) {
                            Throwable cause = e4.getCause();
                            if (Log.isLoggable("ProviderInstaller", 6)) {
                                String valueOf3 = String.valueOf(cause == null ? e4.getMessage() : cause.getMessage());
                                Log.e("ProviderInstaller", valueOf3.length() == 0 ? new String("Failed to install provider: ") : "Failed to install provider: ".concat(valueOf3));
                            }
                            throw new ekf(8);
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                    return;
                }
                Log.e("ProviderInstaller", "Failed to get remote context");
                throw new ekf(8);
            } finally {
                cursor = erk2.f8869a;
                if (cursor != null) {
                    cursor.close();
                }
                erq.f8877d.set(erk);
            }
        } catch (erm e5) {
            String valueOf4 = String.valueOf(e5.getMessage());
            Log.w("ProviderInstaller", valueOf4.length() == 0 ? new String("Failed to load providerinstaller module: ") : "Failed to load providerinstaller module: ".concat(valueOf4));
            context2 = null;
        }
    }

    /* renamed from: b */
    private static Context m8267b(Context context) {
        try {
            return ekh.m7668a(context);
        } catch (Resources.NotFoundException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("ProviderInstaller", valueOf.length() == 0 ? new String("Failed to load GMS Core context for providerinstaller: ") : "Failed to load GMS Core context for providerinstaller: ".concat(valueOf));
            abj.m89a(context, (Throwable) e);
            return null;
        }
    }
}
