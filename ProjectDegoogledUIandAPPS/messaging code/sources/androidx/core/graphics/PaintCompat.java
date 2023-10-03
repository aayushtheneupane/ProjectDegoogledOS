package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Build;
import androidx.core.util.Pair;

public final class PaintCompat {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "󟿽";
    private static final ThreadLocal sRectThreadLocal = new ThreadLocal();

    /* renamed from: androidx.core.graphics.PaintCompat$1 */
    /* synthetic */ class C03191 {
        static final /* synthetic */ int[] $SwitchMap$androidx$core$graphics$BlendModeCompat = new int[BlendModeCompat.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(58:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|(3:57|58|60)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(60:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|60) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x010a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0116 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x012e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x013a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0146 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0152 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                androidx.core.graphics.BlendModeCompat[] r0 = androidx.core.graphics.BlendModeCompat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$core$graphics$BlendModeCompat = r0
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0014 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.CLEAR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x001f }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SRC     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x002a }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DST     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0035 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SRC_OVER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0040 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DST_OVER     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x004b }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SRC_IN     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0056 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DST_IN     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0062 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SRC_OUT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x006e }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DST_OUT     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x007a }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SRC_ATOP     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0086 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DST_ATOP     // Catch:{ NoSuchFieldError -> 0x0086 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0086 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0086 }
            L_0x0086:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0092 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.XOR     // Catch:{ NoSuchFieldError -> 0x0092 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0092 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0092 }
            L_0x0092:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x009e }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.PLUS     // Catch:{ NoSuchFieldError -> 0x009e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009e }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009e }
            L_0x009e:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00aa }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.MODULATE     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00b6 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SCREEN     // Catch:{ NoSuchFieldError -> 0x00b6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b6 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b6 }
            L_0x00b6:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00c2 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.OVERLAY     // Catch:{ NoSuchFieldError -> 0x00c2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c2 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c2 }
            L_0x00c2:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00ce }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DARKEN     // Catch:{ NoSuchFieldError -> 0x00ce }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ce }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ce }
            L_0x00ce:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00da }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.LIGHTEN     // Catch:{ NoSuchFieldError -> 0x00da }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00da }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00da }
            L_0x00da:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00e6 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.COLOR_DODGE     // Catch:{ NoSuchFieldError -> 0x00e6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e6 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e6 }
            L_0x00e6:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00f2 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.COLOR_BURN     // Catch:{ NoSuchFieldError -> 0x00f2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f2 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f2 }
            L_0x00f2:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x00fe }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.HARD_LIGHT     // Catch:{ NoSuchFieldError -> 0x00fe }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fe }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fe }
            L_0x00fe:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x010a }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SOFT_LIGHT     // Catch:{ NoSuchFieldError -> 0x010a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x010a }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x010a }
            L_0x010a:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0116 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.DIFFERENCE     // Catch:{ NoSuchFieldError -> 0x0116 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0116 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0116 }
            L_0x0116:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0122 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.EXCLUSION     // Catch:{ NoSuchFieldError -> 0x0122 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0122 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0122 }
            L_0x0122:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x012e }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.MULTIPLY     // Catch:{ NoSuchFieldError -> 0x012e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012e }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012e }
            L_0x012e:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x013a }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.HUE     // Catch:{ NoSuchFieldError -> 0x013a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x013a }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x013a }
            L_0x013a:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0146 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.SATURATION     // Catch:{ NoSuchFieldError -> 0x0146 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0146 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0146 }
            L_0x0146:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x0152 }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.COLOR     // Catch:{ NoSuchFieldError -> 0x0152 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0152 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0152 }
            L_0x0152:
                int[] r0 = $SwitchMap$androidx$core$graphics$BlendModeCompat     // Catch:{ NoSuchFieldError -> 0x015e }
                androidx.core.graphics.BlendModeCompat r1 = androidx.core.graphics.BlendModeCompat.LUMINOSITY     // Catch:{ NoSuchFieldError -> 0x015e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015e }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015e }
            L_0x015e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PaintCompat.C03191.<clinit>():void");
        }
    }

    private PaintCompat() {
    }

    public static boolean hasGlyph(Paint paint, String str) {
        int i = Build.VERSION.SDK_INT;
        return paint.hasGlyph(str);
    }

    static BlendMode obtainBlendModeFromCompat(BlendModeCompat blendModeCompat) {
        switch (blendModeCompat.ordinal()) {
            case 0:
                return BlendMode.CLEAR;
            case 1:
                return BlendMode.SRC;
            case 2:
                return BlendMode.DST;
            case 3:
                return BlendMode.SRC_OVER;
            case 4:
                return BlendMode.SRC_OVER;
            case 5:
                return BlendMode.SRC_IN;
            case 6:
                return BlendMode.DST_IN;
            case 7:
                return BlendMode.SRC_OUT;
            case 8:
                return BlendMode.DST_OUT;
            case 9:
                return BlendMode.SRC_ATOP;
            case 10:
                return BlendMode.DST_ATOP;
            case 11:
                return BlendMode.XOR;
            case 12:
                return BlendMode.PLUS;
            case 13:
                return BlendMode.MODULATE;
            case 14:
                return BlendMode.SCREEN;
            case 15:
                return BlendMode.OVERLAY;
            case 16:
                return BlendMode.DARKEN;
            case 17:
                return BlendMode.LIGHTEN;
            case 18:
                return BlendMode.COLOR_DODGE;
            case 19:
                return BlendMode.COLOR_BURN;
            case 20:
                return BlendMode.HARD_LIGHT;
            case 21:
                return BlendMode.SOFT_LIGHT;
            case 22:
                return BlendMode.DIFFERENCE;
            case 23:
                return BlendMode.EXCLUSION;
            case 24:
                return BlendMode.MULTIPLY;
            case 25:
                return BlendMode.HUE;
            case 26:
                return BlendMode.SATURATION;
            case 27:
                return BlendMode.COLOR;
            case 28:
                return BlendMode.LUMINOSITY;
            default:
                return null;
        }
    }

    private static Pair obtainEmptyRects() {
        Pair pair = (Pair) sRectThreadLocal.get();
        if (pair == null) {
            Pair pair2 = new Pair(new Rect(), new Rect());
            sRectThreadLocal.set(pair2);
            return pair2;
        }
        ((Rect) pair.first).setEmpty();
        ((Rect) pair.second).setEmpty();
        return pair;
    }

    static PorterDuff.Mode obtainPorterDuffFromCompat(BlendModeCompat blendModeCompat) {
        if (blendModeCompat == null) {
            return null;
        }
        switch (blendModeCompat.ordinal()) {
            case 0:
                return PorterDuff.Mode.CLEAR;
            case 1:
                return PorterDuff.Mode.SRC;
            case 2:
                return PorterDuff.Mode.DST;
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 4:
                return PorterDuff.Mode.DST_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 6:
                return PorterDuff.Mode.DST_IN;
            case 7:
                return PorterDuff.Mode.SRC_OUT;
            case 8:
                return PorterDuff.Mode.DST_OUT;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 10:
                return PorterDuff.Mode.DST_ATOP;
            case 11:
                return PorterDuff.Mode.XOR;
            case 12:
                return PorterDuff.Mode.ADD;
            case 13:
                return PorterDuff.Mode.MULTIPLY;
            case 14:
                return PorterDuff.Mode.SCREEN;
            case 15:
                return PorterDuff.Mode.OVERLAY;
            case 16:
                return PorterDuff.Mode.DARKEN;
            case 17:
                return PorterDuff.Mode.LIGHTEN;
            default:
                return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.graphics.BlendMode} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.graphics.Xfermode] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean setBlendMode(android.graphics.Paint r4, androidx.core.graphics.BlendModeCompat r5) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 0
            r3 = 29
            if (r0 < r3) goto L_0x0012
            if (r5 == 0) goto L_0x000e
            android.graphics.BlendMode r2 = obtainBlendModeFromCompat(r5)
        L_0x000e:
            r4.setBlendMode(r2)
            return r1
        L_0x0012:
            if (r5 == 0) goto L_0x0027
            android.graphics.PorterDuff$Mode r5 = obtainPorterDuffFromCompat(r5)
            if (r5 == 0) goto L_0x001f
            android.graphics.PorterDuffXfermode r2 = new android.graphics.PorterDuffXfermode
            r2.<init>(r5)
        L_0x001f:
            r4.setXfermode(r2)
            if (r5 == 0) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            r1 = 0
        L_0x0026:
            return r1
        L_0x0027:
            r4.setXfermode(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PaintCompat.setBlendMode(android.graphics.Paint, androidx.core.graphics.BlendModeCompat):boolean");
    }

    public static boolean setBlendModeColorFilter(Paint paint, int i, BlendModeCompat blendModeCompat) {
        PorterDuffColorFilter porterDuffColorFilter = null;
        if (Build.VERSION.SDK_INT >= 29) {
            BlendMode obtainBlendModeFromCompat = obtainBlendModeFromCompat(blendModeCompat);
            if (obtainBlendModeFromCompat != null) {
                paint.setColorFilter(new BlendModeColorFilter(i, obtainBlendModeFromCompat));
            } else {
                paint.setColorFilter((ColorFilter) null);
            }
            return true;
        } else if (blendModeCompat != null) {
            PorterDuff.Mode obtainPorterDuffFromCompat = obtainPorterDuffFromCompat(blendModeCompat);
            if (obtainPorterDuffFromCompat != null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i, obtainPorterDuffFromCompat);
            }
            paint.setColorFilter(porterDuffColorFilter);
            if (obtainPorterDuffFromCompat != null) {
                return true;
            }
            return false;
        } else {
            paint.setColorFilter((ColorFilter) null);
            return true;
        }
    }
}
