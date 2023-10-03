package p000;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;

/* renamed from: dvg */
/* compiled from: PG */
public final class dvg {
    /* renamed from: a */
    public static /* synthetic */ String m6743a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "PLACE_HOLDER" : "FOOTER" : "PERSON";
    }

    /* renamed from: a */
    public static boolean m6744a(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardLocked();
    }

    /* renamed from: a */
    public static Intent m6742a(Context context, Intent intent) {
        return m6744a(context) ? dsp.m6593a(context, intent) : intent;
    }

    /* renamed from: a */
    public static boolean m6746a(String[] strArr, String[] strArr2, int[] iArr) {
        if (!Arrays.equals(strArr, strArr2)) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m6745a(Context context, String[] strArr) {
        for (String checkSelfPermission : strArr) {
            if (context.checkSelfPermission(checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }
}
