package com.android.messaging.util;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.messaging.C0967f;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/* renamed from: com.android.messaging.util.na */
public class C1464na {

    /* renamed from: FK */
    private static boolean f2322FK;

    /* renamed from: GK */
    private static boolean f2323GK;

    /* renamed from: HK */
    private static boolean f2324HK;

    /* renamed from: JK */
    private static boolean f2325JK;

    /* renamed from: KK */
    private static boolean f2326KK;

    /* renamed from: LK */
    private static boolean f2327LK;

    /* renamed from: MK */
    private static boolean f2328MK;

    /* renamed from: NK */
    private static boolean f2329NK = true;

    /* renamed from: PK */
    private static Boolean f2330PK;

    /* renamed from: QK */
    private static Hashtable f2331QK = new Hashtable();

    /* renamed from: RK */
    private static String[] f2332RK = {"android.permission.READ_SMS", "android.permission.READ_PHONE_STATE", "android.permission.READ_CONTACTS"};

    static {
        int i = Build.VERSION.SDK_INT;
        boolean z = false;
        f2322FK = i >= 16;
        f2323GK = i >= 17;
        f2324HK = i >= 18;
        f2325JK = i >= 19;
        f2326KK = i >= 21;
        f2327LK = i >= 22;
        if (i >= 23) {
            z = true;
        }
        f2328MK = z;
        int i2 = Build.VERSION.SDK_INT;
    }

    /* renamed from: Ha */
    public static boolean m3750Ha(String str) {
        if (!f2328MK) {
            return true;
        }
        if (!f2331QK.containsKey(str) || ((Integer) f2331QK.get(str)).intValue() == -1) {
            f2331QK.put(str, Integer.valueOf(C0967f.get().getApplicationContext().checkSelfPermission(str)));
        }
        if (((Integer) f2331QK.get(str)).intValue() == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: Rj */
    public static String[] m3751Rj() {
        String[] strArr = f2332RK;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!m3750Ha(str)) {
                arrayList.add(str);
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        return strArr2;
    }

    /* renamed from: Sj */
    public static boolean m3752Sj() {
        for (String Ha : f2332RK) {
            if (!m3750Ha(Ha)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: Tj */
    public static boolean m3753Tj() {
        return m3750Ha("android.permission.READ_EXTERNAL_STORAGE");
    }

    /* renamed from: Uj */
    public static boolean m3754Uj() {
        return f2322FK;
    }

    /* renamed from: Vj */
    public static boolean m3755Vj() {
        return f2323GK;
    }

    /* renamed from: Wj */
    public static boolean m3756Wj() {
        return f2324HK;
    }

    /* renamed from: Xj */
    public static boolean m3757Xj() {
        return f2325JK;
    }

    /* renamed from: Yj */
    public static boolean m3758Yj() {
        return f2326KK;
    }

    /* renamed from: Zj */
    public static boolean m3759Zj() {
        return f2327LK;
    }

    /* renamed from: _j */
    public static boolean m3760_j() {
        return f2328MK;
    }

    /* renamed from: a */
    public static String m3761a(Set set, String str) {
        if (set == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    /* renamed from: ak */
    public static boolean m3762ak() {
        if (f2330PK == null) {
            Context applicationContext = C0967f.get().getApplicationContext();
            boolean z = false;
            if (f2323GK && !"Nexus 10".equals(Build.MODEL)) {
                UserHandle myUserHandle = Process.myUserHandle();
                UserManager userManager = (UserManager) applicationContext.getSystemService("user");
                if (!(userManager == null || 0 == userManager.getSerialNumberForUser(myUserHandle))) {
                    z = true;
                }
            }
            f2330PK = Boolean.valueOf(z);
        }
        return f2330PK.booleanValue();
    }

    public static boolean isAtLeastN() {
        return f2329NK;
    }
}
