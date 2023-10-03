package p026b.p027a.p030b.p031a;

import android.content.ContentResolver;
import android.content.res.Resources;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: b.a.b.a.a */
/* compiled from: outline */
public class C0632a {
    /* renamed from: Pa */
    public static StringBuilder m1011Pa(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    /* renamed from: Pk */
    public static ContentResolver m1012Pk() {
        return C0967f.get().getApplicationContext().getContentResolver();
    }

    /* renamed from: a */
    public static Character m1013a(char c, HashMap hashMap, Object obj, char c2) {
        hashMap.put(obj, Character.valueOf(c));
        return Character.valueOf(c2);
    }

    /* renamed from: a */
    public static String m1014a(String str, Object obj) {
        return str + obj;
    }

    /* renamed from: a */
    public static String m1015a(String str, Object obj, String str2) {
        return str + obj + str2;
    }

    /* renamed from: a */
    public static String m1016a(XmlPullParser xmlPullParser, StringBuilder sb, String str) {
        sb.append(xmlPullParser.getPositionDescription());
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: a */
    public static ArrayList m1017a(int i, Map map, Object obj, int i2, Object obj2) {
        map.put(Integer.valueOf(i), obj);
        ArrayList arrayList = new ArrayList(i2);
        arrayList.add(obj2);
        return arrayList;
    }

    /* renamed from: a */
    public static ArrayList m1018a(ArrayList arrayList, Object obj, int i, Map map, Object obj2, int i2) {
        arrayList.add(obj);
        map.put(Integer.valueOf(i), obj2);
        return new ArrayList(i2);
    }

    /* renamed from: a */
    public static void m1019a(Resources resources, int i, StringBuilder sb, String str) {
        sb.append(resources.getString(i));
        sb.append(str);
    }

    /* renamed from: a */
    public static void m1020a(String str, Object obj, String str2, Throwable th) {
        C1430e.m3623e(str2, str + obj, th);
    }

    /* renamed from: a */
    public static void m1021a(StringBuilder sb, String str, String str2) {
        sb.append(str);
        C1430e.m3630w(str2, sb.toString());
    }

    /* renamed from: a */
    public static void m1022a(Set set, Object obj, Object obj2, Object obj3, Object obj4) {
        set.add(obj);
        set.add(obj2);
        set.add(obj3);
        set.add(obj4);
    }

    /* renamed from: d */
    public static String m1023d(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    /* renamed from: d */
    public static void m1024d(int i, String str) {
        i + str;
    }

    /* renamed from: k */
    public static String m1025k(String str, String str2) {
        return str + str2;
    }
}
