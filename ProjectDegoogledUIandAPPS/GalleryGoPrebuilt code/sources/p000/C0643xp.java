package p000;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.view.View;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: xp */
/* compiled from: PG */
public class C0643xp {
    /* renamed from: b */
    public static /* synthetic */ String m15945b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "MONTH" : "DAY" : "NONE";
    }

    /* renamed from: a */
    public void mo198a(Canvas canvas, RecyclerView recyclerView) {
    }

    /* renamed from: a */
    public void mo6514a(RecyclerView recyclerView) {
    }

    /* renamed from: a */
    public void mo199a(Rect rect, View view, RecyclerView recyclerView, C0664yj yjVar) {
        ((C0648xu) view.getLayoutParams()).mo10596c();
        rect.set(0, 0, 0, 0);
    }

    /* renamed from: a */
    public static ieh m15939a(abc abc) {
        aba aba = new aba();
        abe abe = new abe(aba);
        aba.f70b = abe;
        aba.f69a = abc.getClass();
        try {
            Object a = abc.mo71a(aba);
            if (a != null) {
                aba.f69a = a;
            }
        } catch (Exception e) {
            abe.mo72a(e);
        }
        return abe;
    }

    /* renamed from: a */
    public static aoj m15938a(String str, String str2) {
        aok aok;
        String str3 = str;
        String str4 = str2;
        if (str3 == null || str4 == null) {
            throw new ang("Parameter must not be null", 4);
        }
        aoj aoj = new aoj();
        aoi aoi = new aoi();
        aoi.f1272a = str4;
        while (aoi.f1276e < aoi.f1272a.length() && "/[*".indexOf(aoi.f1272a.charAt(aoi.f1276e)) < 0) {
            aoi.f1276e++;
        }
        int i = aoi.f1276e;
        int i2 = aoi.f1275d;
        if (i != i2) {
            String b = m15946b(str3, aoi.f1272a.substring(i2, i));
            aor c = ank.f1195a.mo1273c(b);
            if (c == null) {
                aoj.mo1348a(new aok(str3, RecyclerView.UNDEFINED_DURATION));
                aoj.mo1348a(new aok(b, 1));
            } else {
                aoj.mo1348a(new aok(c.mo1331a(), RecyclerView.UNDEFINED_DURATION));
                aok aok2 = new aok(m15946b(c.mo1331a(), c.mo1333c()), 1);
                aok2.mo1350a();
                aok2.f1281d = c.mo1334d().f1282a;
                aoj.mo1348a(aok2);
                if (c.mo1334d().mo1353b()) {
                    aok aok3 = new aok("[?xml:lang='x-default']", 5);
                    aok3.mo1350a();
                    aok3.f1281d = c.mo1334d().f1282a;
                    aoj.mo1348a(aok3);
                } else if (c.mo1334d().mo1359a(512)) {
                    aok aok4 = new aok("[1]", 3);
                    aok4.mo1350a();
                    aok4.f1281d = c.mo1334d().f1282a;
                    aoj.mo1348a(aok4);
                }
            }
            while (aoi.f1276e < str2.length()) {
                int i3 = aoi.f1276e;
                aoi.f1275d = i3;
                if (str4.charAt(i3) == '/') {
                    int i4 = aoi.f1275d + 1;
                    aoi.f1275d = i4;
                    if (i4 >= str2.length()) {
                        throw new ang("Empty XMPPath segment", 102);
                    }
                }
                if (str4.charAt(aoi.f1275d) == '*') {
                    int i5 = aoi.f1275d + 1;
                    aoi.f1275d = i5;
                    if (i5 >= str2.length() || str4.charAt(aoi.f1275d) != '[') {
                        throw new ang("Missing '[' after '*'", 102);
                    }
                }
                int i6 = aoi.f1275d;
                aoi.f1276e = i6;
                if (str4.charAt(i6) != '[') {
                    aoi.f1273b = aoi.f1275d;
                    while (aoi.f1276e < aoi.f1272a.length() && "/[*".indexOf(aoi.f1272a.charAt(aoi.f1276e)) < 0) {
                        aoi.f1276e++;
                    }
                    int i7 = aoi.f1276e;
                    aoi.f1274c = i7;
                    int i8 = aoi.f1275d;
                    if (i7 != i8) {
                        aok = new aok(aoi.f1272a.substring(i8, i7), 1);
                    } else {
                        throw new ang("Empty XMPPath segment", 102);
                    }
                } else {
                    int i9 = aoi.f1276e + 1;
                    aoi.f1276e = i9;
                    if (aoi.f1272a.charAt(i9) >= '0' && aoi.f1272a.charAt(aoi.f1276e) <= '9') {
                        while (aoi.f1276e < aoi.f1272a.length() && aoi.f1272a.charAt(aoi.f1276e) >= '0' && aoi.f1272a.charAt(aoi.f1276e) <= '9') {
                            aoi.f1276e++;
                        }
                        aok = new aok((String) null, 3);
                    } else {
                        while (aoi.f1276e < aoi.f1272a.length() && aoi.f1272a.charAt(aoi.f1276e) != ']' && aoi.f1272a.charAt(aoi.f1276e) != '=') {
                            aoi.f1276e++;
                        }
                        if (aoi.f1276e >= aoi.f1272a.length()) {
                            throw new ang("Missing ']' or '=' for array index", 102);
                        } else if (aoi.f1272a.charAt(aoi.f1276e) != ']') {
                            aoi.f1273b = aoi.f1275d + 1;
                            int i10 = aoi.f1276e;
                            aoi.f1274c = i10;
                            int i11 = i10 + 1;
                            aoi.f1276e = i11;
                            char charAt = aoi.f1272a.charAt(i11);
                            if (charAt == '\'' || charAt == '\"') {
                                aoi.f1276e++;
                                while (aoi.f1276e < aoi.f1272a.length()) {
                                    if (aoi.f1272a.charAt(aoi.f1276e) == charAt) {
                                        if (aoi.f1276e + 1 >= aoi.f1272a.length() || aoi.f1272a.charAt(aoi.f1276e + 1) != charAt) {
                                            break;
                                        }
                                        aoi.f1276e++;
                                    }
                                    aoi.f1276e++;
                                }
                                if (aoi.f1276e < aoi.f1272a.length()) {
                                    aoi.f1276e++;
                                    aok = new aok((String) null, 6);
                                } else {
                                    throw new ang("No terminating quote for array selector", 102);
                                }
                            } else {
                                throw new ang("Invalid quote in array selector", 102);
                            }
                        } else if ("[last()".equals(aoi.f1272a.substring(aoi.f1275d, aoi.f1276e))) {
                            aok = new aok((String) null, 4);
                        } else {
                            throw new ang("Invalid non-numeric array index", 102);
                        }
                    }
                    if (aoi.f1276e >= aoi.f1272a.length() || aoi.f1272a.charAt(aoi.f1276e) != ']') {
                        throw new ang("Missing ']' for array index", 102);
                    }
                    int i12 = aoi.f1276e + 1;
                    aoi.f1276e = i12;
                    aok.f1278a = aoi.f1272a.substring(aoi.f1275d, i12);
                }
                int i13 = aok.f1279b;
                if (i13 == 1) {
                    if (aok.f1278a.charAt(0) == '@') {
                        String valueOf = String.valueOf(aok.f1278a.substring(1));
                        aok.f1278a = valueOf.length() == 0 ? new String("?") : "?".concat(valueOf);
                        if (!"?xml:lang".equals(aok.f1278a)) {
                            throw new ang("Only xml:lang allowed with '@'", 102);
                        }
                    }
                    if (aok.f1278a.charAt(0) == '?') {
                        aoi.f1273b++;
                        aok.f1279b = 2;
                    }
                    m15941a(aoi.f1272a.substring(aoi.f1273b, aoi.f1274c));
                } else if (i13 != 6) {
                    continue;
                } else {
                    if (aok.f1278a.charAt(1) == '@') {
                        String valueOf2 = String.valueOf(aok.f1278a.substring(2));
                        aok.f1278a = valueOf2.length() == 0 ? new String("[?") : "[?".concat(valueOf2);
                        if (!aok.f1278a.startsWith("[?xml:lang=")) {
                            throw new ang("Only xml:lang allowed with '@'", 102);
                        }
                    }
                    if (aok.f1278a.charAt(1) == '?') {
                        int i14 = aoi.f1273b + 1;
                        aoi.f1273b = i14;
                        aok.f1279b = 5;
                        m15941a(aoi.f1272a.substring(i14, aoi.f1274c));
                    }
                }
                aoj.mo1348a(aok);
            }
            return aoj;
        }
        throw new ang("Empty initial XMPPath step", 102);
    }

    /* renamed from: a */
    private static void m15941a(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if (ant.m1199e(substring)) {
                if (ank.f1195a.mo1272b(substring) == null) {
                    throw new ang("Unknown namespace prefix for qualified name", 102);
                }
                return;
            }
        }
        throw new ang("Ill-formed qualified name", 102);
    }

    /* renamed from: b */
    private static void m15947b(String str) {
        if (!ant.m1198d(str)) {
            throw new ang("Bad XML name", 102);
        }
    }

    /* renamed from: b */
    private static String m15946b(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new ang("Schema namespace URI is required", 101);
        } else if (str2.charAt(0) == '?' || str2.charAt(0) == '@') {
            throw new ang("Top level name must not be a qualifier", 102);
        } else if (str2.indexOf(47) >= 0 || str2.indexOf(91) >= 0) {
            throw new ang("Top level name must be simple", 102);
        } else {
            String a = ank.f1195a.mo1270a(str);
            if (a != null) {
                int indexOf = str2.indexOf(58);
                if (indexOf < 0) {
                    m15947b(str2);
                    String valueOf = String.valueOf(str2);
                    return valueOf.length() == 0 ? new String(a) : a.concat(valueOf);
                }
                m15947b(str2.substring(0, indexOf));
                m15947b(str2.substring(indexOf));
                String substring = str2.substring(0, indexOf + 1);
                String a2 = ank.f1195a.mo1270a(str);
                if (a2 == null) {
                    throw new ang("Unknown schema namespace prefix", 101);
                } else if (substring.equals(a2)) {
                    return str2;
                } else {
                    throw new ang("Schema namespace URI and prefix mismatch", 101);
                }
            } else {
                throw new ang("Unregistered schema namespace URI", 101);
            }
        }
    }

    /* renamed from: a */
    public static String m15940a(Context context, int i, Object... objArr) {
        return C0081cw.m5488a(Locale.getDefault(), context.getResources().getString(i), objArr);
    }

    /* renamed from: c */
    public static ieh m15948c() {
        return ife.m12820a((Object) null);
    }

    /* renamed from: a */
    public static boolean m15943a(Throwable th) {
        if (th instanceof ErrnoException) {
            return m15942a((Exception) th);
        }
        Throwable cause = th.getCause();
        while (cause != null && !(cause instanceof ErrnoException)) {
            cause = cause.getCause();
        }
        return cause != null && m15942a((Exception) cause);
    }

    /* renamed from: a */
    private static boolean m15942a(Exception exc) {
        if (!(exc instanceof ErrnoException) || ((ErrnoException) exc).errno != OsConstants.ENOSPC) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static int m15944b(Calendar calendar, Calendar calendar2) {
        int a = m15937a(calendar, calendar2);
        return a == 0 ? Integer.compare(calendar.get(6), calendar2.get(6)) : a;
    }

    /* renamed from: a */
    public static int m15937a(Calendar calendar, Calendar calendar2) {
        int compare = Integer.compare(calendar.get(0), calendar2.get(0));
        return compare == 0 ? Integer.compare(calendar.get(1), calendar2.get(1)) : compare;
    }

    /* renamed from: c */
    public static boolean m15949c(Calendar calendar, Calendar calendar2) {
        return m15937a(calendar, calendar2) == 0;
    }
}
