package p000;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* renamed from: imi */
/* compiled from: PG */
public final class imi {
    imi() {
    }

    /* renamed from: a */
    public static boolean m14113a(byte b) {
        return b >= 0;
    }

    /* renamed from: b */
    public static boolean m14116b(byte b) {
        return b < -32;
    }

    /* renamed from: c */
    public static boolean m14119c(byte b) {
        return b < -16;
    }

    /* renamed from: d */
    private static boolean m14120d(byte b) {
        return b > -65;
    }

    /* renamed from: e */
    private static int m14121e(byte b) {
        return b & 63;
    }

    public imi(byte[] bArr) {
    }

    /* renamed from: a */
    public static int m14097a(Map.Entry entry) {
        return ((iiw) entry.getKey()).f14322a;
    }

    /* renamed from: a */
    public static iim m14098a(Object obj) {
        return ((iiu) obj).f14321j;
    }

    /* renamed from: b */
    public static iim m14114b(Object obj) {
        return ((iiu) obj).mo8785a();
    }

    /* renamed from: c */
    public static void m14118c(Object obj) {
        m14098a(obj).mo8729b();
    }

    /* renamed from: a */
    public static void m14110a(iks iks, Object obj, iij iij, iim iim) {
        iih iih = (iih) obj;
        iim.mo8723a(iih.f14244d, iks.mo8543a((Class) iih.f14243c.getClass(), iij));
    }

    /* renamed from: a */
    public static void m14111a(ime ime, Map.Entry entry) {
        iiw iiw = (iiw) entry.getKey();
        imb imb = imb.DOUBLE;
        switch (iiw.f14323b.ordinal()) {
            case 0:
                ime.mo8687a(iiw.f14322a, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                ime.mo8688a(iiw.f14322a, ((Float) entry.getValue()).floatValue());
                return;
            case RecyclerView.SCROLL_STATE_SETTLING:
                ime.mo8690a(iiw.f14322a, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                ime.mo8700c(iiw.f14322a, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                ime.mo8699c(iiw.f14322a, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                ime.mo8702d(iiw.f14322a, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                ime.mo8701d(iiw.f14322a, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                ime.mo8695a(iiw.f14322a, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                ime.mo8694a(iiw.f14322a, (String) entry.getValue());
                return;
            case 9:
                ime.mo8698b(iiw.f14322a, entry.getValue(), ikp.f14397a.mo8875a((Class) entry.getValue().getClass()));
                return;
            case 10:
                ime.mo8693a(iiw.f14322a, entry.getValue(), ikp.f14397a.mo8875a((Class) entry.getValue().getClass()));
                return;
            case 11:
                ime.mo8691a(iiw.f14322a, (ihw) entry.getValue());
                return;
            case 12:
                ime.mo8703e(iiw.f14322a, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                ime.mo8699c(iiw.f14322a, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                ime.mo8689a(iiw.f14322a, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                ime.mo8697b(iiw.f14322a, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                ime.mo8705f(iiw.f14322a, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                ime.mo8704e(iiw.f14322a, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private static String m14102a(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static void m14112a(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object a : (List) obj) {
                m14112a(sb, i, str, a);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry a2 : ((Map) obj).entrySet()) {
                m14112a(sb, i, str, (Object) a2);
            }
        } else {
            sb.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(imu.m14136a(ihw.m13160a((String) obj)));
                sb.append('\"');
            } else if (obj instanceof ihw) {
                sb.append(": \"");
                sb.append(imu.m14136a((ihw) obj));
                sb.append('\"');
            } else if (obj instanceof iix) {
                sb.append(" {");
                m14109a((ikf) (iix) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i4 = i + 2;
                m14112a(sb, i4, "key", entry.getKey());
                m14112a(sb, i4, "value", entry.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    /* renamed from: a */
    public static void m14109a(ikf ikf, StringBuilder sb, int i) {
        boolean z;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : ikf.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith("get") ? str.substring(3) : str;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String str2 = valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    m14112a(sb, i, m14102a(str2), iix.m13610a(method2, (Object) ikf, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String str3 = valueOf4.length() == 0 ? new String(valueOf3) : valueOf3.concat(valueOf4);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    m14112a(sb, i, m14102a(str3), iix.m13610a(method3, (Object) ikf, new Object[0]));
                }
            }
            String valueOf5 = String.valueOf(substring);
            if (((Method) hashMap2.get(valueOf5.length() == 0 ? new String("set") : "set".concat(valueOf5))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (hashMap.containsKey(valueOf6.length() == 0 ? new String("get") : "get".concat(valueOf6))) {
                    }
                }
                String valueOf7 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(substring.substring(1));
                String str4 = valueOf8.length() == 0 ? new String(valueOf7) : valueOf7.concat(valueOf8);
                String valueOf9 = String.valueOf(substring);
                Method method4 = (Method) hashMap.get(valueOf9.length() == 0 ? new String("get") : "get".concat(valueOf9));
                String valueOf10 = String.valueOf(substring);
                Method method5 = (Method) hashMap.get(valueOf10.length() == 0 ? new String("has") : "has".concat(valueOf10));
                if (method4 != null) {
                    Object a = iix.m13610a(method4, (Object) ikf, new Object[0]);
                    if (method5 == null) {
                        if (a instanceof Boolean) {
                            z = !((Boolean) a).booleanValue();
                        } else if (a instanceof Integer) {
                            if (((Integer) a).intValue() == 0) {
                            }
                        } else if (a instanceof Float) {
                            if (((Float) a).floatValue() == 0.0f) {
                            }
                        } else if (a instanceof Double) {
                            if (((Double) a).doubleValue() == 0.0d) {
                            }
                        } else if (a instanceof String) {
                            z = a.equals("");
                        } else if (a instanceof ihw) {
                            z = a.equals(ihw.f14203b);
                        } else if (a instanceof ikf) {
                            if (a == ((ikf) a).mo8774h()) {
                            }
                        } else if ((a instanceof Enum) && ((Enum) a).ordinal() == 0) {
                        }
                        if (z) {
                        }
                    } else if (!((Boolean) iix.m13610a(method5, (Object) ikf, new Object[0])).booleanValue()) {
                    }
                    m14112a(sb, i, m14102a(str4), a);
                }
            }
        }
        if (ikf instanceof iiu) {
            Iterator d = ((iiu) ikf).f14321j.mo8732d();
            while (d.hasNext()) {
                Map.Entry entry = (Map.Entry) d.next();
                int i2 = ((iiw) entry.getKey()).f14322a;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i2);
                sb2.append("]");
                m14112a(sb, i, sb2.toString(), entry.getValue());
            }
        }
        ilm ilm = ((iix) ikf).f14326z;
        if (ilm != null) {
            for (int i3 = 0; i3 < ilm.f14450b; i3++) {
                m14112a(sb, i, String.valueOf(imd.m14074b(ilm.f14451c[i3])), ilm.f14452d[i3]);
            }
        }
    }

    /* renamed from: a */
    public static void m14103a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (m14120d(b2) || (((b << 28) + (b2 + 112)) >> 30) != 0 || m14120d(b3) || m14120d(b4)) {
            throw ijh.m13662i();
        }
        int e = ((b & 7) << 18) | (m14121e(b2) << 12) | (m14121e(b3) << 6) | m14121e(b4);
        cArr[i] = (char) ((e >>> 10) + 55232);
        cArr[i + 1] = (char) ((e & 1023) + 56320);
    }

    /* renamed from: a */
    public static void m14106a(byte b, char[] cArr, int i) {
        cArr[i] = (char) b;
    }

    /* renamed from: a */
    public static void m14104a(byte b, byte b2, byte b3, char[] cArr, int i) {
        if (m14120d(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || m14120d(b3)))) {
            throw ijh.m13662i();
        }
        cArr[i] = (char) (((b & 15) << 12) | (m14121e(b2) << 6) | m14121e(b3));
    }

    /* renamed from: a */
    public static void m14105a(byte b, byte b2, char[] cArr, int i) {
        if (b < -62 || m14120d(b2)) {
            throw ijh.m13662i();
        }
        cArr[i] = (char) (((b & 31) << 6) | m14121e(b2));
    }

    /* renamed from: a */
    public static imh m14101a(ikf ikf) {
        return new img((byte[]) null, ikf);
    }

    /* renamed from: a */
    public static ikf m14099a(Bundle bundle, String str, ikf ikf, iij iij) {
        img img;
        Parcelable parcelable = bundle.getParcelable(str);
        if (parcelable instanceof Bundle) {
            Bundle bundle2 = (Bundle) parcelable;
            bundle2.setClassLoader(img.class.getClassLoader());
            img = (img) bundle2.getParcelable("protoparsers");
        } else {
            img = (img) parcelable;
        }
        return m14100a(img, ikf, iij);
    }

    /* renamed from: a */
    private static ikf m14100a(img img, ikf ikf, iij iij) {
        return img.mo8996b(ikf.mo8774h(), iij);
    }

    /* renamed from: b */
    public static List m14115b(Bundle bundle, String str, ikf ikf, iij iij) {
        ArrayList arrayList;
        Parcelable parcelable = bundle.getParcelable(str);
        if (parcelable instanceof Bundle) {
            Bundle bundle2 = (Bundle) parcelable;
            bundle2.setClassLoader(img.class.getClassLoader());
            arrayList = bundle2.getParcelableArrayList("protoparsers");
        } else {
            arrayList = (ArrayList) parcelable;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add(m14100a((img) arrayList.get(i), ikf, iij));
        }
        return arrayList2;
    }

    /* renamed from: c */
    public static ikf m14117c(Bundle bundle, String str, ikf ikf, iij iij) {
        try {
            return m14099a(bundle, str, ikf, iij);
        } catch (ijh e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static void m14107a(Bundle bundle, String str, ikf ikf) {
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("protoparsers", new img((byte[]) null, ikf));
        bundle.putParcelable(str, bundle2);
    }

    /* renamed from: a */
    public static void m14108a(Bundle bundle, String str, List list) {
        Bundle bundle2 = new Bundle();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(m14101a((ikf) it.next()));
        }
        bundle2.putParcelableArrayList("protoparsers", arrayList);
        bundle.putParcelable(str, bundle2);
    }
}
