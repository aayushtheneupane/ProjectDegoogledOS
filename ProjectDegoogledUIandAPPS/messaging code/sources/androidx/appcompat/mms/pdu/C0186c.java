package androidx.appcompat.mms.pdu;

import androidx.core.view.PointerIconCompat;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/* renamed from: androidx.appcompat.mms.pdu.c */
public class C0186c {

    /* renamed from: Ul */
    private static final int[] f170Ul = {0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 106, 2026, 1000, PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW};

    /* renamed from: Vl */
    private static final String[] f171Vl = {DefaultApnSettingsLoader.APN_TYPE_ALL, "us-ascii", "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4", "iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "iso-8859-9", "shift_JIS", "utf-8", "big5", "iso-10646-ucs-2", "utf-16"};

    /* renamed from: Wl */
    private static final HashMap f172Wl = new HashMap();

    /* renamed from: Xl */
    private static final HashMap f173Xl = new HashMap();

    static {
        int length = f170Ul.length - 1;
        for (int i = 0; i <= length; i++) {
            f172Wl.put(Integer.valueOf(f170Ul[i]), f171Vl[i]);
            f173Xl.put(f171Vl[i], Integer.valueOf(f170Ul[i]));
        }
    }

    /* renamed from: x */
    public static int m152x(String str) {
        if (str == null) {
            return -1;
        }
        Integer num = (Integer) f173Xl.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new UnsupportedEncodingException();
    }
}
