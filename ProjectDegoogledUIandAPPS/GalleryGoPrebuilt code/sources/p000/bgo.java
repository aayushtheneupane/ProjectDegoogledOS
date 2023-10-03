package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewParent;
import com.davemorrissey.labs.subscaleview.decoder.SkiaImageDecoder;
import com.davemorrissey.labs.subscaleview.decoder.SkiaImageRegionDecoder;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: bgo */
/* compiled from: PG */
public class bgo extends View {

    /* renamed from: H */
    public static Bitmap.Config f2285H;

    /* renamed from: I */
    private static final List f2286I = Arrays.asList(new Integer[]{0, 90, 180, 270, -1});

    /* renamed from: a */
    public static final String f2287a = bgo.class.getSimpleName();

    /* renamed from: b */
    public static final List f2288b = Arrays.asList(new Integer[]{1, 2, 3});

    /* renamed from: c */
    public static final List f2289c = Arrays.asList(new Integer[]{2, 1});

    /* renamed from: A */
    public PointF f2290A;

    /* renamed from: B */
    public bge f2291B;

    /* renamed from: C */
    public boolean f2292C;

    /* renamed from: D */
    public bgj f2293D;

    /* renamed from: E */
    public View.OnLongClickListener f2294E;

    /* renamed from: F */
    public Paint f2295F;

    /* renamed from: G */
    public Paint f2296G;

    /* renamed from: J */
    private Bitmap f2297J;

    /* renamed from: K */
    private boolean f2298K;

    /* renamed from: L */
    private Uri f2299L;

    /* renamed from: M */
    private int f2300M;

    /* renamed from: N */
    private Map f2301N;

    /* renamed from: O */
    private int f2302O;

    /* renamed from: P */
    private float f2303P;

    /* renamed from: Q */
    private int f2304Q;

    /* renamed from: R */
    private int f2305R;

    /* renamed from: S */
    private int f2306S;

    /* renamed from: T */
    private Executor f2307T;

    /* renamed from: U */
    private boolean f2308U;

    /* renamed from: V */
    private int f2309V;

    /* renamed from: W */
    private PointF f2310W;

    /* renamed from: aa */
    private int f2311aa;

    /* renamed from: ab */
    private boolean f2312ab;

    /* renamed from: ac */
    private GestureDetector f2313ac;

    /* renamed from: ad */
    private GestureDetector f2314ad;

    /* renamed from: ae */
    private bgs f2315ae;

    /* renamed from: af */
    private bgq f2316af;

    /* renamed from: ag */
    private bgq f2317ag;

    /* renamed from: ah */
    private float f2318ah;

    /* renamed from: ai */
    private final float f2319ai;

    /* renamed from: aj */
    private boolean f2320aj;

    /* renamed from: ak */
    private final Handler f2321ak;

    /* renamed from: al */
    private bgk f2322al;

    /* renamed from: am */
    private Matrix f2323am;

    /* renamed from: an */
    private RectF f2324an;

    /* renamed from: ao */
    private final float[] f2325ao;

    /* renamed from: ap */
    private final float[] f2326ap;

    /* renamed from: aq */
    private final float f2327aq;

    /* renamed from: d */
    public float f2328d;

    /* renamed from: e */
    public boolean f2329e;

    /* renamed from: f */
    public boolean f2330f;

    /* renamed from: g */
    public boolean f2331g;

    /* renamed from: h */
    public float f2332h;

    /* renamed from: i */
    public int f2333i;

    /* renamed from: j */
    public float f2334j;

    /* renamed from: k */
    public float f2335k;

    /* renamed from: l */
    public PointF f2336l;

    /* renamed from: m */
    public PointF f2337m;

    /* renamed from: n */
    public Float f2338n;

    /* renamed from: o */
    public PointF f2339o;

    /* renamed from: p */
    public int f2340p;

    /* renamed from: q */
    public int f2341q;

    /* renamed from: r */
    public boolean f2342r;

    /* renamed from: s */
    public boolean f2343s;

    /* renamed from: t */
    public int f2344t;

    /* renamed from: u */
    public final ReadWriteLock f2345u;

    /* renamed from: v */
    public PointF f2346v;

    /* renamed from: w */
    public float f2347w;

    /* renamed from: x */
    public boolean f2348x;

    /* renamed from: y */
    public PointF f2349y;

    /* renamed from: z */
    public PointF f2350z;

    static {
        Arrays.asList(new Integer[]{1, 2, 3});
        Arrays.asList(new Integer[]{2, 1, 3, 4});
    }

    /* renamed from: d */
    public final int mo2006d() {
        int i = this.f2302O;
        return i == -1 ? this.f2311aa : i;
    }

    public bgo(Context context) {
        this(context, (AttributeSet) null);
    }

    public bgo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        PointF pointF;
        int resourceId;
        String string;
        this.f2302O = 0;
        this.f2328d = 2.0f;
        this.f2303P = mo2007e();
        this.f2304Q = -1;
        this.f2305R = Integer.MAX_VALUE;
        this.f2306S = Integer.MAX_VALUE;
        this.f2307T = AsyncTask.THREAD_POOL_EXECUTOR;
        this.f2308U = true;
        this.f2329e = true;
        this.f2330f = true;
        this.f2331g = true;
        this.f2332h = 1.0f;
        this.f2333i = 1;
        this.f2309V = 500;
        this.f2345u = new ReentrantReadWriteLock(true);
        this.f2316af = new bgp(SkiaImageDecoder.class);
        this.f2317ag = new bgp(SkiaImageRegionDecoder.class);
        this.f2325ao = new float[8];
        this.f2326ap = new float[8];
        this.f2327aq = getResources().getDisplayMetrics().density;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f2328d = ((displayMetrics.xdpi + displayMetrics.ydpi) / 2.0f) / 160.0f;
        DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
        this.f2332h = ((displayMetrics2.xdpi + displayMetrics2.ydpi) / 2.0f) / 160.0f;
        DisplayMetrics displayMetrics3 = getResources().getDisplayMetrics();
        this.f2304Q = (int) Math.min((displayMetrics3.xdpi + displayMetrics3.ydpi) / 2.0f, 320.0f);
        if (this.f2292C) {
            mo2001a(false);
            invalidate();
        }
        mo1994a(context);
        this.f2321ak = new Handler(new bgb(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, bga.f2232a);
            if (obtainStyledAttributes.hasValue(0) && (string = obtainStyledAttributes.getString(0)) != null && string.length() > 0) {
                String str = string.length() == 0 ? new String("file:///android_asset/") : "file:///android_asset/".concat(string);
                if (str != null) {
                    if (!str.contains("://")) {
                        String valueOf = String.valueOf(str.startsWith("/") ? str.substring(1) : str);
                        str = valueOf.length() == 0 ? new String("file:///") : "file:///".concat(valueOf);
                    }
                    Uri parse = Uri.parse(str);
                    if (parse != null) {
                        String uri = parse.toString();
                        if (uri.startsWith("file:///") && !new File(uri.substring(7)).exists()) {
                            try {
                                parse = Uri.parse(URLDecoder.decode(uri, "UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                            }
                        }
                        bfz bfz = new bfz(parse);
                        bfz.mo1974a();
                        mo1998a(bfz);
                    } else {
                        throw new NullPointerException("Uri must not be null");
                    }
                } else {
                    throw new NullPointerException("Uri must not be null");
                }
            }
            if (obtainStyledAttributes.hasValue(3) && (resourceId = obtainStyledAttributes.getResourceId(3, 0)) > 0) {
                bfz bfz2 = new bfz(resourceId);
                bfz2.mo1974a();
                mo1998a(bfz2);
            }
            if (obtainStyledAttributes.hasValue(1)) {
                boolean z = obtainStyledAttributes.getBoolean(1, true);
                this.f2329e = z;
                if (!z && (pointF = this.f2336l) != null) {
                    pointF.x = ((float) (getWidth() / 2)) - (this.f2334j * ((float) (mo2003b() / 2)));
                    this.f2336l.y = ((float) (getHeight() / 2)) - (this.f2334j * ((float) (mo2005c() / 2)));
                    if (this.f2292C) {
                        m2475c(true);
                        invalidate();
                    }
                }
            }
            if (obtainStyledAttributes.hasValue(5)) {
                this.f2330f = obtainStyledAttributes.getBoolean(5, true);
            }
            if (obtainStyledAttributes.hasValue(2)) {
                this.f2331g = obtainStyledAttributes.getBoolean(2, true);
            }
            if (obtainStyledAttributes.hasValue(4)) {
                int color = obtainStyledAttributes.getColor(4, Color.argb(0, 0, 0, 0));
                if (Color.alpha(color) == 0) {
                    this.f2296G = null;
                } else {
                    Paint paint = new Paint();
                    this.f2296G = paint;
                    paint.setStyle(Paint.Style.FILL);
                    this.f2296G.setColor(color);
                }
                invalidate();
            }
            obtainStyledAttributes.recycle();
        }
        this.f2319ai = TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: b */
    private final int m2472b(float f) {
        int i;
        if (this.f2304Q > 0) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            f *= ((float) this.f2304Q) / ((displayMetrics.xdpi + displayMetrics.ydpi) / 2.0f);
        }
        int b = (int) (((float) mo2003b()) * f);
        int c = (int) (((float) mo2005c()) * f);
        if (b == 0 || c == 0) {
            return 32;
        }
        int i2 = 1;
        if (mo2005c() > c || mo2003b() > b) {
            i = Math.round(((float) mo2005c()) / ((float) c));
            int round = Math.round(((float) mo2003b()) / ((float) b));
            if (i >= round) {
                i = round;
            }
        } else {
            i = 1;
        }
        while (true) {
            int i3 = i2 + i2;
            if (i3 >= i) {
                return i2;
            }
            i2 = i3;
        }
    }

    /* renamed from: i */
    private final boolean m2482i() {
        boolean g = m2480g();
        if (!this.f2320aj && g) {
            m2483j();
            this.f2320aj = true;
            if (this.f2293D != null) {
                return true;
            }
        }
        return g;
    }

    /* renamed from: h */
    private final boolean m2481h() {
        boolean z = getWidth() > 0 && getHeight() > 0 && this.f2340p > 0 && this.f2341q > 0 && (this.f2297J != null || m2480g());
        if (!this.f2292C && z) {
            m2483j();
            this.f2292C = true;
            bgj bgj = this.f2293D;
            if (bgj != null) {
                drd drd = (drd) bgj;
                drd.f7204a.f7213e.mo3407b(false);
                drd.f7204a.mo4369j();
                return true;
            }
        }
        return z;
    }

    /* renamed from: a */
    private static final float m2463a(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    /* renamed from: a */
    public final void mo1997a(PointF pointF, PointF pointF2) {
        if (!this.f2329e) {
            pointF.x = (float) (mo2003b() / 2);
            pointF.y = (float) (mo2005c() / 2);
        }
        float min = Math.min(this.f2328d, this.f2332h);
        float f = this.f2334j;
        double d = (double) min;
        Double.isNaN(d);
        boolean z = ((double) f) <= d * 0.9d || f == this.f2303P;
        if (!z) {
            min = mo2007e();
        }
        int i = this.f2333i;
        if (i == 2 || !z || !this.f2329e) {
            bgf bgf = new bgf(this, min, pointF);
            bgf.mo1981b();
            bgf.f2250a = (long) this.f2309V;
            bgf.f2252c = 4;
            bgf.mo1980a();
        } else if (i == 1) {
            bgf bgf2 = new bgf(this, min, pointF, pointF2);
            bgf2.mo1981b();
            bgf2.f2250a = (long) this.f2309V;
            bgf2.f2252c = 4;
            bgf2.mo1980a();
        }
        invalidate();
    }

    /* renamed from: a */
    private static final float m2464a(int i, long j, float f, float f2, long j2) {
        float f3;
        if (i == 1) {
            float f4 = ((float) j) / ((float) j2);
            return ((-f2) * f4 * (f4 - 2.0f)) + f;
        } else if (i == 2) {
            float f5 = ((float) j) / (((float) j2) / 2.0f);
            if (f5 >= 1.0f) {
                float f6 = f5 - 4.0f;
                f3 = (-f2) / 2.0f;
                f5 = (f6 * (-2.0f + f6)) - 4.0f;
            } else {
                f3 = (f2 / 2.0f) * f5;
            }
            return (f3 * f5) + f;
        } else {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Unexpected easing type: ");
            sb.append(i);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* renamed from: a */
    private final void m2468a(AsyncTask asyncTask) {
        asyncTask.executeOnExecutor(this.f2307T, new Void[0]);
    }

    /* renamed from: d */
    private final void m2477d(boolean z) {
        boolean z2;
        if (this.f2336l == null) {
            this.f2336l = new PointF(0.0f, 0.0f);
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.f2322al == null) {
            this.f2322al = new bgk(0.0f, new PointF(0.0f, 0.0f));
        }
        bgk bgk = this.f2322al;
        bgk.f2266a = this.f2334j;
        bgk.f2267b.set(this.f2336l);
        mo2002a(z, this.f2322al);
        bgk bgk2 = this.f2322al;
        this.f2334j = bgk2.f2266a;
        this.f2336l.set(bgk2.f2267b);
        if (z2) {
            this.f2336l.set(mo1990a((float) (mo2003b() / 2), (float) (mo2005c() / 2), this.f2334j));
        }
    }

    /* renamed from: a */
    public final void mo2002a(boolean z, bgk bgk) {
        float f;
        float f2;
        PointF pointF = bgk.f2267b;
        float a = mo1989a(bgk.f2266a);
        float b = ((float) mo2003b()) * a;
        float c = ((float) mo2005c()) * a;
        if (z) {
            pointF.x = Math.max(pointF.x, ((float) getWidth()) - b);
            pointF.y = Math.max(pointF.y, ((float) getHeight()) - c);
        } else {
            pointF.x = Math.max(pointF.x, -b);
            pointF.y = Math.max(pointF.y, -c);
        }
        float f3 = 0.5f;
        float paddingLeft = (getPaddingLeft() <= 0 && getPaddingRight() <= 0) ? 0.5f : ((float) getPaddingLeft()) / ((float) (getPaddingLeft() + getPaddingRight()));
        if (getPaddingTop() > 0 || getPaddingBottom() > 0) {
            f3 = ((float) getPaddingTop()) / ((float) (getPaddingTop() + getPaddingBottom()));
        }
        if (z) {
            f = Math.max(0.0f, (((float) getWidth()) - b) * paddingLeft);
            f2 = Math.max(0.0f, (((float) getHeight()) - c) * f3);
        } else {
            f = (float) Math.max(0, getWidth());
            f2 = (float) Math.max(0, getHeight());
        }
        pointF.x = Math.min(pointF.x, f);
        pointF.y = Math.min(pointF.y, f2);
        bgk.f2266a = a;
    }

    /* renamed from: f */
    public final PointF mo2008f() {
        int width = getWidth();
        return m2466a((float) (width / 2), (float) (getHeight() / 2), new PointF());
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int m2465a(android.content.Context r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "content"
            boolean r0 = r10.startsWith(r0)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x006d
            java.lang.String r9 = "file:///"
            boolean r9 = r10.startsWith(r9)
            if (r9 == 0) goto L_0x006b
            java.lang.String r9 = "file:///android_asset/"
            boolean r9 = r10.startsWith(r9)
            if (r9 != 0) goto L_0x006b
            android.media.ExifInterface r9 = new android.media.ExifInterface     // Catch:{ Exception -> 0x0060 }
            r0 = 7
            java.lang.String r10 = r10.substring(r0)     // Catch:{ Exception -> 0x0060 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0060 }
            java.lang.String r10 = "Orientation"
            int r9 = r9.getAttributeInt(r10, r1)     // Catch:{ Exception -> 0x0060 }
            if (r9 != r1) goto L_0x002d
            goto L_0x006b
        L_0x002d:
            if (r9 != 0) goto L_0x0030
            goto L_0x006b
        L_0x0030:
            r10 = 6
            if (r9 != r10) goto L_0x0037
            r2 = 90
            goto L_0x00d3
        L_0x0037:
            r10 = 3
            if (r9 != r10) goto L_0x003e
            r2 = 180(0xb4, float:2.52E-43)
            goto L_0x00d3
        L_0x003e:
            r10 = 8
            if (r9 == r10) goto L_0x005d
            java.lang.String r10 = f2287a     // Catch:{ Exception -> 0x0060 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0060 }
            r1 = 41
            r0.<init>(r1)     // Catch:{ Exception -> 0x0060 }
            java.lang.String r1 = "Unsupported EXIF orientation: "
            r0.append(r1)     // Catch:{ Exception -> 0x0060 }
            r0.append(r9)     // Catch:{ Exception -> 0x0060 }
            java.lang.String r9 = r0.toString()     // Catch:{ Exception -> 0x0060 }
            android.util.Log.w(r10, r9)     // Catch:{ Exception -> 0x0060 }
            goto L_0x00d3
        L_0x005d:
            r2 = 270(0x10e, float:3.78E-43)
            goto L_0x006b
        L_0x0060:
            r9 = move-exception
            java.lang.String r9 = f2287a
            java.lang.String r10 = "Could not get EXIF orientation of image"
            android.util.Log.w(r9, r10)
            goto L_0x00d3
        L_0x006b:
            goto L_0x00d3
        L_0x006d:
            r0 = 0
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00c8, all -> 0x00c6 }
            java.lang.String r1 = "orientation"
            r5[r2] = r1     // Catch:{ Exception -> 0x00c8, all -> 0x00c6 }
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch:{ Exception -> 0x00c8, all -> 0x00c6 }
            android.net.Uri r4 = android.net.Uri.parse(r10)     // Catch:{ Exception -> 0x00c8, all -> 0x00c6 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00c8, all -> 0x00c6 }
            if (r0 == 0) goto L_0x00bf
            boolean r9 = r0.moveToFirst()     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            if (r9 == 0) goto L_0x00ba
            int r9 = r0.getInt(r2)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            java.util.List r10 = f2286I     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            boolean r10 = r10.contains(r1)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            if (r10 != 0) goto L_0x009c
            goto L_0x00a1
        L_0x009c:
            r10 = -1
            if (r9 == r10) goto L_0x00a1
            r2 = r9
            goto L_0x00c0
        L_0x00a1:
            java.lang.String r10 = f2287a     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            r3 = 36
            r1.<init>(r3)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            java.lang.String r3 = "Unsupported orientation: "
            r1.append(r3)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            r1.append(r9)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            java.lang.String r9 = r1.toString()     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            android.util.Log.w(r10, r9)     // Catch:{ Exception -> 0x00bd, all -> 0x00bb }
            goto L_0x00bf
        L_0x00ba:
            goto L_0x00bf
        L_0x00bb:
            r9 = move-exception
            goto L_0x00d6
        L_0x00bd:
            r9 = move-exception
            goto L_0x00c9
        L_0x00bf:
        L_0x00c0:
            if (r0 == 0) goto L_0x00d3
        L_0x00c2:
            r0.close()
            return r2
        L_0x00c6:
            r9 = move-exception
            goto L_0x00d7
        L_0x00c8:
            r9 = move-exception
        L_0x00c9:
            java.lang.String r9 = f2287a     // Catch:{ all -> 0x00d4 }
            java.lang.String r10 = "Could not get orientation of image from media store"
            android.util.Log.w(r9, r10)     // Catch:{ all -> 0x00d4 }
            if (r0 != 0) goto L_0x00c2
            goto L_0x006b
        L_0x00d3:
            return r2
        L_0x00d4:
            r9 = move-exception
        L_0x00d6:
        L_0x00d7:
            if (r0 == 0) goto L_0x00dc
            r0.close()
        L_0x00dc:
            goto L_0x00de
        L_0x00dd:
            throw r9
        L_0x00de:
            goto L_0x00dd
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bgo.m2465a(android.content.Context, java.lang.String):int");
    }

    /* renamed from: a */
    private final synchronized void m2467a(Point point) {
        int i;
        int i2;
        Point point2 = point;
        synchronized (this) {
            boolean z = true;
            Object[] objArr = {Integer.valueOf(point2.x), Integer.valueOf(point2.y)};
            bgk bgk = new bgk(0.0f, new PointF(0.0f, 0.0f));
            this.f2322al = bgk;
            mo2002a(true, bgk);
            int b = m2472b(this.f2322al.f2266a);
            this.f2300M = b;
            if (b > 1) {
                b >>= 1;
                this.f2300M = b;
            }
            byte[] bArr = null;
            if (b == 1) {
                if (mo2003b() < point2.x && mo2005c() < point2.y) {
                    this.f2315ae.mo2019b();
                    this.f2315ae = null;
                    m2468a((AsyncTask) new bgg(this, getContext(), this.f2316af, this.f2299L, false));
                    return;
                }
            }
            Object[] objArr2 = {Integer.valueOf(point2.x), Integer.valueOf(point2.y)};
            this.f2301N = new LinkedHashMap();
            int i3 = this.f2300M;
            int i4 = 1;
            int i5 = 1;
            while (true) {
                int b2 = mo2003b() / i4;
                int c = mo2005c() / i5;
                int i6 = b2 / i3;
                int i7 = c / i3;
                while (true) {
                    if (i6 + i4 + (z ? 1 : 0) <= point2.x) {
                        double d = (double) i6;
                        double width = (double) getWidth();
                        Double.isNaN(width);
                        if (d > width * 1.25d) {
                            if (i3 >= this.f2300M) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    i4++;
                    b2 = mo2003b() / i4;
                    i6 = b2 / i3;
                    bArr = null;
                    z = true;
                }
                while (true) {
                    if (i7 + i5 + (z ? 1 : 0) <= point2.y) {
                        double d2 = (double) i7;
                        double height = (double) getHeight();
                        Double.isNaN(height);
                        if (d2 > height * 1.25d) {
                            if (i3 >= this.f2300M) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    i5++;
                    c = mo2005c() / i5;
                    i7 = c / i3;
                    bArr = null;
                    z = true;
                }
                ArrayList arrayList = new ArrayList(i4 * i5);
                int i8 = 0;
                while (i8 < i4) {
                    int i9 = 0;
                    while (i9 < i5) {
                        bgl bgl = new bgl(bArr);
                        bgl.f2269b = i3;
                        bgl.f2272e = i3 == this.f2300M;
                        int i10 = i8 * b2;
                        int i11 = i9 * c;
                        if (i8 != i4 - 1) {
                            i = (i8 + 1) * b2;
                        } else {
                            i = mo2003b();
                        }
                        if (i9 != i5 - 1) {
                            i2 = (i9 + 1) * c;
                        } else {
                            i2 = mo2005c();
                        }
                        bgl.f2268a = new Rect(i10, i11, i, i2);
                        bgl.f2273f = new Rect(0, 0, 0, 0);
                        bgl.f2274g = new Rect(bgl.f2268a);
                        arrayList.add(bgl);
                        i9++;
                        bArr = null;
                    }
                    i8++;
                    bArr = null;
                    z = true;
                }
                this.f2301N.put(Integer.valueOf(i3), arrayList);
                if (i3 == z) {
                    for (bgl bgm : (List) this.f2301N.get(Integer.valueOf(this.f2300M))) {
                        m2468a((AsyncTask) new bgm(this, this.f2315ae, bgm));
                    }
                    m2475c(z);
                    return;
                }
                i3 /= 2;
            }
        }
    }

    /* renamed from: g */
    private final boolean m2480g() {
        boolean z = true;
        if (this.f2297J != null && !this.f2298K) {
            return true;
        }
        Map map = this.f2301N;
        if (map == null) {
            return false;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (((Integer) entry.getKey()).intValue() == this.f2300M) {
                for (bgl bgl : (List) entry.getValue()) {
                    if (bgl.f2271d || bgl.f2270c == null) {
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public final float mo1989a(float f) {
        return Math.min(this.f2328d, Math.max(mo2007e(), f));
    }

    /* renamed from: e */
    public final float mo2007e() {
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        return Math.min(((float) (getWidth() - (getPaddingLeft() + getPaddingRight()))) / ((float) mo2003b()), ((float) (getHeight() - (paddingBottom + paddingTop))) / ((float) mo2005c()));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0128  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onDraw(android.graphics.Canvas r30) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            super.onDraw(r30)
            android.graphics.Paint r2 = r0.f2295F
            r3 = 1
            if (r2 != 0) goto L_0x0020
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>()
            r0.f2295F = r2
            r2.setAntiAlias(r3)
            android.graphics.Paint r2 = r0.f2295F
            r2.setFilterBitmap(r3)
            android.graphics.Paint r2 = r0.f2295F
            r2.setDither(r3)
        L_0x0020:
            int r2 = r0.f2340p
            if (r2 == 0) goto L_0x0461
            int r2 = r0.f2341q
            if (r2 == 0) goto L_0x0461
            int r2 = r29.getWidth()
            if (r2 == 0) goto L_0x0461
            int r2 = r29.getHeight()
            if (r2 == 0) goto L_0x0461
            java.util.Map r2 = r0.f2301N
            if (r2 != 0) goto L_0x0058
            bgs r2 = r0.f2315ae
            if (r2 == 0) goto L_0x0058
            android.graphics.Point r2 = new android.graphics.Point
            int r4 = r30.getMaximumBitmapWidth()
            int r5 = r0.f2305R
            int r4 = java.lang.Math.min(r4, r5)
            int r5 = r30.getMaximumBitmapHeight()
            int r6 = r0.f2306S
            int r5 = java.lang.Math.min(r5, r6)
            r2.<init>(r4, r5)
            r0.m2467a((android.graphics.Point) r2)
        L_0x0058:
            boolean r2 = r29.m2481h()
            if (r2 == 0) goto L_0x0461
            r29.m2483j()
            bge r2 = r0.f2291B
            r4 = 0
            r5 = 0
            if (r2 != 0) goto L_0x0069
            goto L_0x012e
        L_0x0069:
            android.graphics.PointF r2 = r2.f2242f
            if (r2 == 0) goto L_0x012e
            android.graphics.PointF r2 = r0.f2310W
            if (r2 == 0) goto L_0x0072
            goto L_0x0079
        L_0x0072:
            android.graphics.PointF r2 = new android.graphics.PointF
            r2.<init>(r5, r5)
            r0.f2310W = r2
        L_0x0079:
            android.graphics.PointF r2 = r0.f2310W
            android.graphics.PointF r6 = r0.f2336l
            r2.set(r6)
            long r6 = java.lang.System.currentTimeMillis()
            bge r2 = r0.f2291B
            long r8 = r2.f2248l
            long r6 = r6 - r8
            long r8 = r2.f2244h
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x0091
            r2 = 1
            goto L_0x0093
        L_0x0091:
            r2 = 0
        L_0x0093:
            long r6 = java.lang.Math.min(r6, r8)
            bge r8 = r0.f2291B
            int r10 = r8.f2246j
            float r13 = r8.f2237a
            float r9 = r8.f2238b
            float r14 = r9 - r13
            long r8 = r8.f2244h
            r11 = r6
            r15 = r8
            float r8 = m2464a(r10, r11, r13, r14, r15)
            r0.f2334j = r8
            bge r8 = r0.f2291B
            int r10 = r8.f2246j
            android.graphics.PointF r8 = r8.f2242f
            float r13 = r8.x
            bge r8 = r0.f2291B
            android.graphics.PointF r8 = r8.f2243g
            float r8 = r8.x
            bge r9 = r0.f2291B
            android.graphics.PointF r9 = r9.f2242f
            float r9 = r9.x
            bge r11 = r0.f2291B
            long r14 = r11.f2244h
            float r8 = r8 - r9
            r11 = r6
            r15 = r14
            r14 = r8
            float r8 = m2464a(r10, r11, r13, r14, r15)
            bge r9 = r0.f2291B
            int r10 = r9.f2246j
            android.graphics.PointF r9 = r9.f2242f
            float r13 = r9.y
            bge r9 = r0.f2291B
            android.graphics.PointF r9 = r9.f2243g
            float r9 = r9.y
            bge r11 = r0.f2291B
            android.graphics.PointF r11 = r11.f2242f
            float r11 = r11.y
            bge r12 = r0.f2291B
            long r14 = r12.f2244h
            float r9 = r9 - r11
            r11 = r6
            r6 = r14
            r14 = r9
            r15 = r6
            float r6 = m2464a(r10, r11, r13, r14, r15)
            android.graphics.PointF r7 = r0.f2336l
            float r9 = r7.x
            bge r10 = r0.f2291B
            android.graphics.PointF r10 = r10.f2240d
            float r10 = r10.x
            float r10 = r0.m2478e(r10)
            float r10 = r10 - r8
            float r9 = r9 - r10
            r7.x = r9
            android.graphics.PointF r7 = r0.f2336l
            float r8 = r7.y
            bge r9 = r0.f2291B
            android.graphics.PointF r9 = r9.f2240d
            float r9 = r9.y
            float r9 = r0.m2479f(r9)
            float r9 = r9 - r6
            float r8 = r8 - r9
            r7.y = r8
            if (r2 != 0) goto L_0x011f
            bge r6 = r0.f2291B
            float r7 = r6.f2237a
            float r6 = r6.f2238b
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x011e
            r6 = 0
            goto L_0x0120
        L_0x011e:
        L_0x011f:
            r6 = 1
        L_0x0120:
            r0.m2477d((boolean) r6)
            r0.m2475c((boolean) r2)
            if (r2 == 0) goto L_0x012b
            r2 = 0
            r0.f2291B = r2
        L_0x012b:
            r29.invalidate()
        L_0x012e:
            java.util.Map r2 = r0.f2301N
            r7 = 90
            if (r2 != 0) goto L_0x0136
            goto L_0x037f
        L_0x0136:
            boolean r2 = r29.m2480g()
            if (r2 == 0) goto L_0x037f
            int r2 = r0.f2300M
            float r5 = r0.f2334j
            int r5 = r0.m2472b((float) r5)
            int r2 = java.lang.Math.min(r2, r5)
            java.util.Map r5 = r0.f2301N
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0152:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x018e
            java.lang.Object r9 = r5.next()
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9
            java.lang.Object r10 = r9.getKey()
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            if (r10 != r2) goto L_0x0152
            java.lang.Object r9 = r9.getValue()
            java.util.List r9 = (java.util.List) r9
            java.util.Iterator r9 = r9.iterator()
        L_0x0174:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0152
            java.lang.Object r10 = r9.next()
            bgl r10 = (p000.bgl) r10
            boolean r11 = r10.f2272e
            if (r11 == 0) goto L_0x0174
            boolean r11 = r10.f2271d
            if (r11 != 0) goto L_0x018c
            android.graphics.Bitmap r10 = r10.f2270c
            if (r10 != 0) goto L_0x0174
        L_0x018c:
            r4 = 1
            goto L_0x0174
        L_0x018e:
            java.util.Map r3 = r0.f2301N
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0198:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0460
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r9 = r5.getKey()
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            if (r9 != r2) goto L_0x01b1
            goto L_0x01b3
        L_0x01b1:
            if (r4 == 0) goto L_0x037b
        L_0x01b3:
            java.lang.Object r5 = r5.getValue()
            java.util.List r5 = (java.util.List) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x01bd:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x0377
            java.lang.Object r9 = r5.next()
            bgl r9 = (p000.bgl) r9
            android.graphics.Rect r10 = r9.f2268a
            android.graphics.Rect r11 = r9.f2273f
            int r12 = r10.left
            float r12 = (float) r12
            float r12 = r0.m2478e(r12)
            int r12 = (int) r12
            int r13 = r10.top
            float r13 = (float) r13
            float r13 = r0.m2479f(r13)
            int r13 = (int) r13
            int r14 = r10.right
            float r14 = (float) r14
            float r14 = r0.m2478e(r14)
            int r14 = (int) r14
            int r10 = r10.bottom
            float r10 = (float) r10
            float r10 = r0.m2479f(r10)
            int r10 = (int) r10
            r11.set(r12, r13, r14, r10)
            boolean r10 = r9.f2271d
            if (r10 != 0) goto L_0x0373
            android.graphics.Bitmap r10 = r9.f2270c
            if (r10 == 0) goto L_0x0373
            android.graphics.Paint r10 = r0.f2296G
            if (r10 == 0) goto L_0x0201
            android.graphics.Rect r11 = r9.f2273f
            r1.drawRect(r11, r10)
        L_0x0201:
            android.graphics.Matrix r10 = r0.f2323am
            if (r10 == 0) goto L_0x0206
            goto L_0x020d
        L_0x0206:
            android.graphics.Matrix r10 = new android.graphics.Matrix
            r10.<init>()
            r0.f2323am = r10
        L_0x020d:
            android.graphics.Matrix r10 = r0.f2323am
            r10.reset()
            float[] r11 = r0.f2325ao
            r12 = 0
            r13 = 0
            android.graphics.Bitmap r10 = r9.f2270c
            int r10 = r10.getWidth()
            float r14 = (float) r10
            r15 = 0
            android.graphics.Bitmap r10 = r9.f2270c
            int r10 = r10.getWidth()
            float r10 = (float) r10
            android.graphics.Bitmap r6 = r9.f2270c
            int r6 = r6.getHeight()
            float r6 = (float) r6
            r18 = 0
            android.graphics.Bitmap r8 = r9.f2270c
            int r8 = r8.getHeight()
            float r8 = (float) r8
            r16 = r10
            r17 = r6
            r19 = r8
            m2471a(r11, r12, r13, r14, r15, r16, r17, r18, r19)
            int r6 = r29.mo2006d()
            if (r6 == 0) goto L_0x0312
            int r6 = r29.mo2006d()
            if (r6 == r7) goto L_0x02d2
            int r6 = r29.mo2006d()
            r8 = 180(0xb4, float:2.52E-43)
            if (r6 == r8) goto L_0x0291
            int r6 = r29.mo2006d()
            r8 = 270(0x10e, float:3.78E-43)
            if (r6 == r8) goto L_0x025c
            goto L_0x0351
        L_0x025c:
            float[] r10 = r0.f2326ap
            android.graphics.Rect r6 = r9.f2273f
            int r6 = r6.left
            float r11 = (float) r6
            android.graphics.Rect r6 = r9.f2273f
            int r6 = r6.bottom
            float r12 = (float) r6
            android.graphics.Rect r6 = r9.f2273f
            int r6 = r6.left
            float r13 = (float) r6
            android.graphics.Rect r6 = r9.f2273f
            int r6 = r6.top
            float r14 = (float) r6
            android.graphics.Rect r6 = r9.f2273f
            int r6 = r6.right
            float r15 = (float) r6
            android.graphics.Rect r6 = r9.f2273f
            int r6 = r6.top
            float r6 = (float) r6
            android.graphics.Rect r8 = r9.f2273f
            int r8 = r8.right
            float r8 = (float) r8
            android.graphics.Rect r7 = r9.f2273f
            int r7 = r7.bottom
            float r7 = (float) r7
            r16 = r6
            r17 = r8
            r18 = r7
            m2471a(r10, r11, r12, r13, r14, r15, r16, r17, r18)
            goto L_0x0351
        L_0x0291:
            float[] r6 = r0.f2326ap
            android.graphics.Rect r7 = r9.f2273f
            int r7 = r7.right
            float r7 = (float) r7
            android.graphics.Rect r8 = r9.f2273f
            int r8 = r8.bottom
            float r8 = (float) r8
            android.graphics.Rect r10 = r9.f2273f
            int r10 = r10.left
            float r10 = (float) r10
            android.graphics.Rect r11 = r9.f2273f
            int r11 = r11.bottom
            float r11 = (float) r11
            android.graphics.Rect r12 = r9.f2273f
            int r12 = r12.left
            float r12 = (float) r12
            android.graphics.Rect r13 = r9.f2273f
            int r13 = r13.top
            float r13 = (float) r13
            android.graphics.Rect r14 = r9.f2273f
            int r14 = r14.right
            float r14 = (float) r14
            android.graphics.Rect r15 = r9.f2273f
            int r15 = r15.top
            float r15 = (float) r15
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r10
            r24 = r11
            r25 = r12
            r26 = r13
            r27 = r14
            r28 = r15
            m2471a(r20, r21, r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0351
        L_0x02d2:
            float[] r6 = r0.f2326ap
            android.graphics.Rect r7 = r9.f2273f
            int r7 = r7.right
            float r7 = (float) r7
            android.graphics.Rect r8 = r9.f2273f
            int r8 = r8.top
            float r8 = (float) r8
            android.graphics.Rect r10 = r9.f2273f
            int r10 = r10.right
            float r10 = (float) r10
            android.graphics.Rect r11 = r9.f2273f
            int r11 = r11.bottom
            float r11 = (float) r11
            android.graphics.Rect r12 = r9.f2273f
            int r12 = r12.left
            float r12 = (float) r12
            android.graphics.Rect r13 = r9.f2273f
            int r13 = r13.bottom
            float r13 = (float) r13
            android.graphics.Rect r14 = r9.f2273f
            int r14 = r14.left
            float r14 = (float) r14
            android.graphics.Rect r15 = r9.f2273f
            int r15 = r15.top
            float r15 = (float) r15
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r10
            r24 = r11
            r25 = r12
            r26 = r13
            r27 = r14
            r28 = r15
            m2471a(r20, r21, r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0351
        L_0x0312:
            float[] r6 = r0.f2326ap
            android.graphics.Rect r7 = r9.f2273f
            int r7 = r7.left
            float r7 = (float) r7
            android.graphics.Rect r8 = r9.f2273f
            int r8 = r8.top
            float r8 = (float) r8
            android.graphics.Rect r10 = r9.f2273f
            int r10 = r10.right
            float r10 = (float) r10
            android.graphics.Rect r11 = r9.f2273f
            int r11 = r11.top
            float r11 = (float) r11
            android.graphics.Rect r12 = r9.f2273f
            int r12 = r12.right
            float r12 = (float) r12
            android.graphics.Rect r13 = r9.f2273f
            int r13 = r13.bottom
            float r13 = (float) r13
            android.graphics.Rect r14 = r9.f2273f
            int r14 = r14.left
            float r14 = (float) r14
            android.graphics.Rect r15 = r9.f2273f
            int r15 = r15.bottom
            float r15 = (float) r15
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r10
            r24 = r11
            r25 = r12
            r26 = r13
            r27 = r14
            r28 = r15
            m2471a(r20, r21, r22, r23, r24, r25, r26, r27, r28)
        L_0x0351:
            android.graphics.Matrix r6 = r0.f2323am
            float[] r7 = r0.f2325ao
            r22 = 0
            float[] r8 = r0.f2326ap
            r24 = 0
            r25 = 4
            r20 = r6
            r21 = r7
            r23 = r8
            r20.setPolyToPoly(r21, r22, r23, r24, r25)
            android.graphics.Bitmap r6 = r9.f2270c
            android.graphics.Matrix r7 = r0.f2323am
            android.graphics.Paint r8 = r0.f2295F
            r1.drawBitmap(r6, r7, r8)
            r7 = 90
            goto L_0x01bd
        L_0x0373:
            r7 = 90
            goto L_0x01bd
        L_0x0377:
            r7 = 90
            goto L_0x0198
        L_0x037b:
            r7 = 90
            goto L_0x0198
        L_0x037f:
            android.graphics.Bitmap r2 = r0.f2297J
            if (r2 == 0) goto L_0x0460
            float r3 = r0.f2334j
            boolean r4 = r0.f2298K
            if (r4 == 0) goto L_0x03a4
            int r4 = r0.f2340p
            float r4 = (float) r4
            int r2 = r2.getWidth()
            float r2 = (float) r2
            float r4 = r4 / r2
            float r3 = r3 * r4
            float r2 = r0.f2334j
            int r4 = r0.f2341q
            float r4 = (float) r4
            android.graphics.Bitmap r6 = r0.f2297J
            int r6 = r6.getHeight()
            float r6 = (float) r6
            float r4 = r4 / r6
            float r2 = r2 * r4
            goto L_0x03a5
        L_0x03a4:
            r2 = r3
        L_0x03a5:
            android.graphics.Matrix r4 = r0.f2323am
            if (r4 == 0) goto L_0x03aa
            goto L_0x03b1
        L_0x03aa:
            android.graphics.Matrix r4 = new android.graphics.Matrix
            r4.<init>()
            r0.f2323am = r4
        L_0x03b1:
            android.graphics.Matrix r4 = r0.f2323am
            r4.reset()
            android.graphics.Matrix r4 = r0.f2323am
            r4.postScale(r3, r2)
            android.graphics.Matrix r2 = r0.f2323am
            int r3 = r29.mo2006d()
            float r3 = (float) r3
            r2.postRotate(r3)
            android.graphics.Matrix r2 = r0.f2323am
            android.graphics.PointF r3 = r0.f2336l
            float r3 = r3.x
            android.graphics.PointF r4 = r0.f2336l
            float r4 = r4.y
            r2.postTranslate(r3, r4)
            int r2 = r29.mo2006d()
            r3 = 180(0xb4, float:2.52E-43)
            if (r2 != r3) goto L_0x03ec
            android.graphics.Matrix r2 = r0.f2323am
            float r3 = r0.f2334j
            int r4 = r0.f2340p
            float r4 = (float) r4
            float r4 = r4 * r3
            int r6 = r0.f2341q
            float r6 = (float) r6
            float r3 = r3 * r6
            r2.postTranslate(r4, r3)
            goto L_0x0415
        L_0x03ec:
            int r2 = r29.mo2006d()
            r3 = 90
            if (r2 != r3) goto L_0x0401
            android.graphics.Matrix r2 = r0.f2323am
            float r3 = r0.f2334j
            int r4 = r0.f2341q
            float r4 = (float) r4
            float r3 = r3 * r4
            r2.postTranslate(r3, r5)
            goto L_0x0415
        L_0x0401:
            int r2 = r29.mo2006d()
            r3 = 270(0x10e, float:3.78E-43)
            if (r2 != r3) goto L_0x0415
            android.graphics.Matrix r2 = r0.f2323am
            float r3 = r0.f2334j
            int r4 = r0.f2340p
            float r4 = (float) r4
            float r3 = r3 * r4
            r2.postTranslate(r5, r3)
        L_0x0415:
            android.graphics.Paint r2 = r0.f2296G
            if (r2 != 0) goto L_0x041a
            goto L_0x0456
        L_0x041a:
            android.graphics.RectF r2 = r0.f2324an
            if (r2 != 0) goto L_0x0425
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r0.f2324an = r2
        L_0x0425:
            android.graphics.RectF r2 = r0.f2324an
            boolean r3 = r0.f2298K
            if (r3 == 0) goto L_0x0433
            android.graphics.Bitmap r3 = r0.f2297J
            int r3 = r3.getWidth()
            float r3 = (float) r3
            goto L_0x0436
        L_0x0433:
            int r3 = r0.f2340p
            float r3 = (float) r3
        L_0x0436:
            boolean r4 = r0.f2298K
            if (r4 == 0) goto L_0x0442
            android.graphics.Bitmap r4 = r0.f2297J
            int r4 = r4.getHeight()
            float r4 = (float) r4
            goto L_0x0445
        L_0x0442:
            int r4 = r0.f2341q
            float r4 = (float) r4
        L_0x0445:
            r2.set(r5, r5, r3, r4)
            android.graphics.Matrix r2 = r0.f2323am
            android.graphics.RectF r3 = r0.f2324an
            r2.mapRect(r3)
            android.graphics.RectF r2 = r0.f2324an
            android.graphics.Paint r3 = r0.f2296G
            r1.drawRect(r2, r3)
        L_0x0456:
            android.graphics.Bitmap r2 = r0.f2297J
            android.graphics.Matrix r3 = r0.f2323am
            android.graphics.Paint r4 = r0.f2295F
            r1.drawBitmap(r2, r3, r4)
            return
        L_0x0460:
            return
        L_0x0461:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bgo.onDraw(android.graphics.Canvas):void");
    }

    /* renamed from: a */
    public final synchronized void mo1996a(Bitmap bitmap, int i) {
        int i2 = this.f2340p;
        if (i2 > 0) {
            if (this.f2341q > 0 && !(i2 == bitmap.getWidth() && this.f2341q == bitmap.getHeight())) {
                mo2001a(false);
            }
        }
        Bitmap bitmap2 = this.f2297J;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.f2298K = false;
        this.f2297J = bitmap;
        this.f2340p = bitmap.getWidth();
        this.f2341q = bitmap.getHeight();
        this.f2311aa = i;
        boolean h = m2481h();
        boolean i3 = m2482i();
        if (h || i3) {
            invalidate();
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.f2340p > 0 && this.f2341q > 0) {
            if (mode != 1073741824 && mode2 != 1073741824) {
                size = mo2003b();
                size2 = mo2005c();
            } else if (mode2 != 1073741824) {
                double c = (double) mo2005c();
                double b = (double) mo2003b();
                Double.isNaN(c);
                Double.isNaN(b);
                double d = c / b;
                double d2 = (double) size;
                Double.isNaN(d2);
                size2 = (int) (d * d2);
            } else if (mode != 1073741824) {
                double b2 = (double) mo2003b();
                double c2 = (double) mo2005c();
                Double.isNaN(b2);
                Double.isNaN(c2);
                double d3 = b2 / c2;
                double d4 = (double) size2;
                Double.isNaN(d4);
                size = (int) (d3 * d4);
            }
        }
        setMeasuredDimension(Math.max(size, getSuggestedMinimumWidth()), Math.max(size2, getSuggestedMinimumHeight()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo1995a(android.graphics.Bitmap r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.graphics.Bitmap r0 = r1.f2297J     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x001c
            boolean r0 = r1.f2320aj     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x001c
            r1.f2297J = r2     // Catch:{ all -> 0x0021 }
            r2 = 1
            r1.f2298K = r2     // Catch:{ all -> 0x0021 }
            boolean r2 = r1.m2481h()     // Catch:{ all -> 0x0021 }
            if (r2 == 0) goto L_0x001a
            r1.invalidate()     // Catch:{ all -> 0x0021 }
            r1.requestLayout()     // Catch:{ all -> 0x0021 }
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            r2.recycle()     // Catch:{ all -> 0x0021 }
            monitor-exit(r1)
            return
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bgo.mo1995a(android.graphics.Bitmap):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        Object[] objArr = {Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i2)};
        PointF f = mo2008f();
        if (this.f2292C && f != null) {
            this.f2291B = null;
            this.f2338n = Float.valueOf(this.f2334j);
            this.f2339o = f;
        }
    }

    /* renamed from: a */
    public final synchronized void mo1992a() {
        Bitmap bitmap;
        m2481h();
        m2482i();
        if (m2480g() && (bitmap = this.f2297J) != null) {
            bitmap.recycle();
            this.f2297J = null;
            this.f2298K = false;
        }
        invalidate();
    }

    /* renamed from: a */
    public final synchronized void mo2000a(bgs bgs, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.f2302O)};
        int i7 = this.f2340p;
        if (i7 > 0 && (i6 = this.f2341q) > 0 && !(i7 == i && i6 == i2)) {
            mo2001a(false);
            Bitmap bitmap = this.f2297J;
            if (bitmap != null) {
                bitmap.recycle();
                this.f2297J = null;
                this.f2298K = false;
            }
        }
        this.f2315ae = bgs;
        this.f2340p = i;
        this.f2341q = i2;
        this.f2311aa = i3;
        m2481h();
        if (!m2482i() && (i4 = this.f2305R) > 0 && i4 != Integer.MAX_VALUE && (i5 = this.f2306S) > 0 && i5 != Integer.MAX_VALUE && getWidth() > 0 && getHeight() > 0) {
            m2467a(new Point(this.f2305R, this.f2306S));
        }
        invalidate();
        requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:161:0x042e, code lost:
        if (r12.f2312ab != false) goto L_0x0463;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007c, code lost:
        if (r5 != 262) goto L_0x0407;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0183, code lost:
        if ((r12.f2334j * ((float) mo2003b())) >= ((float) getWidth())) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02c7, code lost:
        if ((r12.f2334j * ((float) mo2003b())) >= ((float) getWidth())) goto L_0x02c9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r13) {
        /*
            r12 = this;
            bge r0 = r12.f2291B
            r1 = 1
            if (r0 == 0) goto L_0x000e
            boolean r0 = r0.f2245i
            if (r0 == 0) goto L_0x000a
            goto L_0x000e
        L_0x000a:
            r12.mo3407b((boolean) r1)
            return r1
        L_0x000e:
            r0 = 0
            r12.f2291B = r0
            android.graphics.PointF r2 = r12.f2336l
            if (r2 != 0) goto L_0x001e
            android.view.GestureDetector r0 = r12.f2314ad
            if (r0 == 0) goto L_0x001d
            r0.onTouchEvent(r13)
        L_0x001d:
            return r1
        L_0x001e:
            boolean r2 = r12.f2343s
            r3 = 0
            if (r2 != 0) goto L_0x0035
            android.view.GestureDetector r2 = r12.f2313ac
            if (r2 == 0) goto L_0x002d
            boolean r2 = r2.onTouchEvent(r13)
            if (r2 == 0) goto L_0x0035
        L_0x002d:
            r12.f2342r = r3
            r12.f2312ab = r3
            r12.f2344t = r3
            return r1
        L_0x0035:
            android.graphics.PointF r2 = r12.f2337m
            r4 = 0
            if (r2 != 0) goto L_0x0041
            android.graphics.PointF r2 = new android.graphics.PointF
            r2.<init>(r4, r4)
            r12.f2337m = r2
        L_0x0041:
            android.graphics.PointF r2 = r12.f2310W
            if (r2 != 0) goto L_0x004c
            android.graphics.PointF r2 = new android.graphics.PointF
            r2.<init>(r4, r4)
            r12.f2310W = r2
        L_0x004c:
            android.graphics.PointF r2 = r12.f2346v
            if (r2 == 0) goto L_0x0051
            goto L_0x0058
        L_0x0051:
            android.graphics.PointF r2 = new android.graphics.PointF
            r2.<init>(r4, r4)
            r12.f2346v = r2
        L_0x0058:
            android.graphics.PointF r2 = r12.f2310W
            android.graphics.PointF r5 = r12.f2336l
            r2.set(r5)
            int r2 = r13.getPointerCount()
            int r5 = r13.getAction()
            r6 = 1073741824(0x40000000, float:2.0)
            r7 = 2
            if (r5 == 0) goto L_0x047e
            if (r5 == r1) goto L_0x040e
            if (r5 == r7) goto L_0x0080
            r4 = 5
            if (r5 == r4) goto L_0x047e
            r4 = 6
            if (r5 == r4) goto L_0x040e
            r4 = 261(0x105, float:3.66E-43)
            if (r5 == r4) goto L_0x047e
            r0 = 262(0x106, float:3.67E-43)
            if (r5 == r0) goto L_0x040e
            goto L_0x0407
        L_0x0080:
            int r0 = r12.f2344t
            if (r0 <= 0) goto L_0x0407
            r0 = 1084227584(0x40a00000, float:5.0)
            if (r2 < r7) goto L_0x01d0
            float r2 = r13.getX(r3)
            float r4 = r13.getX(r1)
            float r5 = r13.getY(r3)
            float r8 = r13.getY(r1)
            float r2 = m2463a((float) r2, (float) r4, (float) r5, (float) r8)
            float r4 = r13.getX(r3)
            float r5 = r13.getX(r1)
            float r4 = r4 + r5
            float r4 = r4 / r6
            float r5 = r13.getY(r3)
            float r8 = r13.getY(r1)
            float r5 = r5 + r8
            float r5 = r5 / r6
            boolean r6 = r12.f2330f
            if (r6 == 0) goto L_0x0407
            android.graphics.PointF r6 = r12.f2346v
            float r6 = r6.x
            android.graphics.PointF r8 = r12.f2346v
            float r8 = r8.y
            float r6 = m2463a((float) r6, (float) r4, (float) r8, (float) r5)
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 > 0) goto L_0x00d4
            float r6 = r12.f2318ah
            float r6 = r2 - r6
            float r6 = java.lang.Math.abs(r6)
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x00d4
            boolean r0 = r12.f2312ab
            if (r0 == 0) goto L_0x0407
        L_0x00d4:
            r12.f2342r = r1
            r12.f2312ab = r1
            float r13 = r12.f2334j
            double r8 = (double) r13
            float r13 = r12.f2328d
            float r0 = r12.f2318ah
            float r0 = r2 / r0
            float r3 = r12.f2335k
            float r0 = r0 * r3
            float r13 = java.lang.Math.min(r13, r0)
            r12.f2334j = r13
            float r0 = r12.mo2007e()
            int r13 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r13 > 0) goto L_0x010a
            r12.f2318ah = r2
            float r13 = r12.mo2007e()
            r12.f2335k = r13
            android.graphics.PointF r13 = r12.f2346v
            r13.set(r4, r5)
            android.graphics.PointF r13 = r12.f2337m
            android.graphics.PointF r0 = r12.f2336l
            r13.set(r0)
            goto L_0x01c5
        L_0x010a:
            boolean r13 = r12.f2329e
            if (r13 == 0) goto L_0x019b
            android.graphics.PointF r13 = r12.f2346v
            float r13 = r13.x
            android.graphics.PointF r0 = r12.f2337m
            float r0 = r0.x
            android.graphics.PointF r3 = r12.f2346v
            float r3 = r3.y
            android.graphics.PointF r6 = r12.f2337m
            float r6 = r6.y
            float r7 = r12.f2334j
            float r10 = r12.f2335k
            float r7 = r7 / r10
            android.graphics.PointF r10 = r12.f2336l
            float r13 = r13 - r0
            float r13 = r13 * r7
            float r13 = r4 - r13
            r10.x = r13
            android.graphics.PointF r13 = r12.f2336l
            float r3 = r3 - r6
            float r3 = r3 * r7
            float r0 = r5 - r3
            r13.y = r0
            int r13 = r12.mo2005c()
            double r6 = (double) r13
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r8
            int r13 = r12.getHeight()
            double r10 = (double) r13
            int r13 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r13 >= 0) goto L_0x015d
            float r13 = r12.f2334j
            int r0 = r12.mo2005c()
            float r0 = (float) r0
            float r13 = r13 * r0
            int r0 = r12.getHeight()
            float r0 = (float) r0
            int r13 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r13 >= 0) goto L_0x0185
        L_0x015d:
            int r13 = r12.mo2003b()
            double r6 = (double) r13
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r6)
            double r8 = r8 * r6
            int r13 = r12.getWidth()
            double r6 = (double) r13
            int r13 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r13 >= 0) goto L_0x01c5
            float r13 = r12.f2334j
            int r0 = r12.mo2003b()
            float r0 = (float) r0
            float r13 = r13 * r0
            int r0 = r12.getWidth()
            float r0 = (float) r0
            int r13 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r13 < 0) goto L_0x01c5
        L_0x0185:
            r12.m2477d((boolean) r1)
            android.graphics.PointF r13 = r12.f2346v
            r13.set(r4, r5)
            android.graphics.PointF r13 = r12.f2337m
            android.graphics.PointF r0 = r12.f2336l
            r13.set(r0)
            float r13 = r12.f2334j
            r12.f2335k = r13
            r12.f2318ah = r2
            goto L_0x01c5
        L_0x019b:
            android.graphics.PointF r13 = r12.f2336l
            int r0 = r12.getWidth()
            int r0 = r0 / r7
            float r0 = (float) r0
            float r2 = r12.f2334j
            int r3 = r12.mo2003b()
            int r3 = r3 / r7
            float r3 = (float) r3
            float r2 = r2 * r3
            float r0 = r0 - r2
            r13.x = r0
            android.graphics.PointF r13 = r12.f2336l
            int r0 = r12.getHeight()
            int r0 = r0 / r7
            float r0 = (float) r0
            float r2 = r12.f2334j
            int r3 = r12.mo2005c()
            int r3 = r3 / r7
            float r3 = (float) r3
            float r2 = r2 * r3
            float r0 = r0 - r2
            r13.y = r0
        L_0x01c5:
            r12.m2477d((boolean) r1)
            boolean r13 = r12.f2308U
            r12.m2475c((boolean) r13)
            goto L_0x03fd
        L_0x01d0:
            boolean r2 = r12.f2343s
            if (r2 == 0) goto L_0x031c
            android.graphics.PointF r0 = r12.f2290A
            float r0 = r0.y
            float r2 = r13.getY()
            float r0 = r0 - r2
            float r0 = java.lang.Math.abs(r0)
            float r0 = r0 + r0
            float r2 = r12.f2319ai
            float r0 = r0 + r2
            float r2 = r12.f2347w
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x01ef
            r12.f2347w = r0
        L_0x01ef:
            float r2 = r13.getY()
            android.graphics.PointF r3 = r12.f2349y
            float r3 = r3.y
            android.graphics.PointF r5 = r12.f2349y
            float r13 = r13.getY()
            r5.set(r4, r13)
            float r13 = r12.f2347w
            float r13 = r0 / r13
            r5 = 1065353216(0x3f800000, float:1.0)
            float r13 = r5 - r13
            float r13 = java.lang.Math.abs(r13)
            r6 = 1056964608(0x3f000000, float:0.5)
            float r13 = r13 * r6
            r6 = 1022739087(0x3cf5c28f, float:0.03)
            int r6 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x021e
            boolean r6 = r12.f2348x
            if (r6 == 0) goto L_0x021c
            goto L_0x021e
        L_0x021c:
            goto L_0x030f
        L_0x021e:
            r12.f2348x = r1
            float r6 = r12.f2347w
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x022f
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x022d
            float r5 = r5 - r13
            goto L_0x0231
        L_0x022d:
            float r5 = r5 + r13
            goto L_0x0231
        L_0x022f:
        L_0x0231:
            float r13 = r12.f2334j
            double r2 = (double) r13
            float r13 = r12.mo2007e()
            float r6 = r12.f2328d
            float r8 = r12.f2334j
            float r8 = r8 * r5
            float r5 = java.lang.Math.min(r6, r8)
            float r13 = java.lang.Math.max(r13, r5)
            r12.f2334j = r13
            boolean r13 = r12.f2329e
            if (r13 == 0) goto L_0x02e3
            android.graphics.PointF r13 = r12.f2346v
            float r13 = r13.x
            android.graphics.PointF r5 = r12.f2337m
            float r5 = r5.x
            android.graphics.PointF r6 = r12.f2346v
            float r6 = r6.y
            android.graphics.PointF r7 = r12.f2337m
            float r7 = r7.y
            float r8 = r12.f2334j
            float r9 = r12.f2335k
            float r8 = r8 / r9
            android.graphics.PointF r9 = r12.f2336l
            android.graphics.PointF r10 = r12.f2346v
            float r10 = r10.x
            float r13 = r13 - r5
            float r13 = r13 * r8
            float r10 = r10 - r13
            r9.x = r10
            android.graphics.PointF r13 = r12.f2336l
            android.graphics.PointF r5 = r12.f2346v
            float r5 = r5.y
            float r6 = r6 - r7
            float r6 = r6 * r8
            float r5 = r5 - r6
            r13.y = r5
            int r13 = r12.mo2005c()
            double r5 = (double) r13
            java.lang.Double.isNaN(r2)
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r2
            int r13 = r12.getHeight()
            double r7 = (double) r13
            int r13 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r13 >= 0) goto L_0x02a1
            float r13 = r12.f2334j
            int r5 = r12.mo2005c()
            float r5 = (float) r5
            float r13 = r13 * r5
            int r5 = r12.getHeight()
            float r5 = (float) r5
            int r13 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r13 >= 0) goto L_0x02c9
        L_0x02a1:
            int r13 = r12.mo2003b()
            double r5 = (double) r13
            java.lang.Double.isNaN(r2)
            java.lang.Double.isNaN(r5)
            double r2 = r2 * r5
            int r13 = r12.getWidth()
            double r5 = (double) r13
            int r13 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r13 >= 0) goto L_0x021c
            float r13 = r12.f2334j
            int r2 = r12.mo2003b()
            float r2 = (float) r2
            float r13 = r13 * r2
            int r2 = r12.getWidth()
            float r2 = (float) r2
            int r13 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r13 < 0) goto L_0x021c
        L_0x02c9:
            r12.m2477d((boolean) r1)
            android.graphics.PointF r13 = r12.f2346v
            android.graphics.PointF r0 = r12.f2350z
            android.graphics.PointF r0 = r12.mo2004b((android.graphics.PointF) r0)
            r13.set(r0)
            android.graphics.PointF r13 = r12.f2337m
            android.graphics.PointF r0 = r12.f2336l
            r13.set(r0)
            float r13 = r12.f2334j
            r12.f2335k = r13
            goto L_0x0310
        L_0x02e3:
            android.graphics.PointF r13 = r12.f2336l
            int r2 = r12.getWidth()
            int r2 = r2 / r7
            float r2 = (float) r2
            float r3 = r12.f2334j
            int r4 = r12.mo2003b()
            int r4 = r4 / r7
            float r4 = (float) r4
            float r3 = r3 * r4
            float r2 = r2 - r3
            r13.x = r2
            android.graphics.PointF r13 = r12.f2336l
            int r2 = r12.getHeight()
            int r2 = r2 / r7
            float r2 = (float) r2
            float r3 = r12.f2334j
            int r4 = r12.mo2005c()
            int r4 = r4 / r7
            float r4 = (float) r4
            float r3 = r3 * r4
            float r2 = r2 - r3
            r13.y = r2
        L_0x030f:
            r4 = r0
        L_0x0310:
            r12.f2347w = r4
            r12.m2477d((boolean) r1)
            boolean r13 = r12.f2308U
            r12.m2475c((boolean) r13)
            goto L_0x03fd
        L_0x031c:
            boolean r2 = r12.f2342r
            if (r2 != 0) goto L_0x0407
            float r2 = r13.getX()
            android.graphics.PointF r4 = r12.f2346v
            float r4 = r4.x
            float r2 = r2 - r4
            float r2 = java.lang.Math.abs(r2)
            float r4 = r13.getY()
            android.graphics.PointF r5 = r12.f2346v
            float r5 = r5.y
            float r4 = r4 - r5
            float r4 = java.lang.Math.abs(r4)
            float r5 = r12.f2327aq
            float r5 = r5 * r0
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0343
            goto L_0x034b
        L_0x0343:
            int r0 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r0 > 0) goto L_0x034b
            boolean r0 = r12.f2312ab
            if (r0 == 0) goto L_0x0407
        L_0x034b:
            android.graphics.PointF r0 = r12.f2336l
            android.graphics.PointF r6 = r12.f2337m
            float r6 = r6.x
            float r7 = r13.getX()
            android.graphics.PointF r8 = r12.f2346v
            float r8 = r8.x
            float r7 = r7 - r8
            float r6 = r6 + r7
            r0.x = r6
            android.graphics.PointF r0 = r12.f2336l
            android.graphics.PointF r6 = r12.f2337m
            float r6 = r6.y
            float r13 = r13.getY()
            android.graphics.PointF r7 = r12.f2346v
            float r7 = r7.y
            float r13 = r13 - r7
            float r6 = r6 + r13
            r0.y = r6
            android.graphics.PointF r13 = r12.f2336l
            float r13 = r13.x
            android.graphics.PointF r0 = r12.f2336l
            float r0 = r0.y
            r12.m2477d((boolean) r1)
            android.graphics.PointF r6 = r12.f2336l
            float r6 = r6.x
            android.graphics.PointF r7 = r12.f2336l
            float r7 = r7.y
            int r8 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0390
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x0390
            boolean r8 = r12.f2312ab
            if (r8 != 0) goto L_0x0390
            r8 = 1
            goto L_0x0391
        L_0x0390:
            r8 = 0
        L_0x0391:
            int r9 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x03a0
            int r9 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r9 <= 0) goto L_0x03a0
            boolean r9 = r12.f2312ab
            if (r9 != 0) goto L_0x039f
            r9 = 1
            goto L_0x03a1
        L_0x039f:
        L_0x03a0:
            r9 = 0
        L_0x03a1:
            android.graphics.PointF r10 = r12.f2336l
            float r10 = r10.y
            int r10 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r10 != 0) goto L_0x03b5
            r10 = 1077936128(0x40400000, float:3.0)
            float r10 = r10 * r5
            int r10 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r10 <= 0) goto L_0x03b3
            r10 = 1
            goto L_0x03b6
        L_0x03b3:
        L_0x03b5:
            r10 = 0
        L_0x03b6:
            if (r8 == 0) goto L_0x03b9
            goto L_0x03ce
        L_0x03b9:
            if (r9 != 0) goto L_0x03ce
            int r13 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r13 == 0) goto L_0x03ca
            int r13 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r13 != 0) goto L_0x03c4
            goto L_0x03ca
        L_0x03c4:
            if (r10 != 0) goto L_0x03ca
            boolean r13 = r12.f2312ab
            if (r13 == 0) goto L_0x03ce
        L_0x03ca:
            r12.f2312ab = r1
            goto L_0x03e1
        L_0x03ce:
            int r13 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r13 > 0) goto L_0x03d6
            int r13 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r13 <= 0) goto L_0x03e1
        L_0x03d6:
            r12.f2344t = r3
            android.os.Handler r13 = r12.f2321ak
            r13.removeMessages(r1)
            r12.mo3407b((boolean) r3)
        L_0x03e1:
            boolean r13 = r12.f2329e
            if (r13 != 0) goto L_0x03f8
            android.graphics.PointF r13 = r12.f2336l
            android.graphics.PointF r0 = r12.f2337m
            float r0 = r0.x
            r13.x = r0
            android.graphics.PointF r13 = r12.f2336l
            android.graphics.PointF r0 = r12.f2337m
            float r0 = r0.y
            r13.y = r0
            r12.mo3407b((boolean) r3)
        L_0x03f8:
            boolean r13 = r12.f2308U
            r12.m2475c((boolean) r13)
        L_0x03fd:
            android.os.Handler r13 = r12.f2321ak
            r13.removeMessages(r1)
            r12.invalidate()
            goto L_0x0501
        L_0x0407:
            boolean r13 = super.onTouchEvent(r13)
            if (r13 != 0) goto L_0x0501
            return r3
        L_0x040e:
            android.os.Handler r0 = r12.f2321ak
            r0.removeMessages(r1)
            boolean r0 = r12.f2343s
            if (r0 == 0) goto L_0x0424
            r12.f2343s = r3
            boolean r0 = r12.f2348x
            if (r0 != 0) goto L_0x0424
            android.graphics.PointF r0 = r12.f2350z
            android.graphics.PointF r4 = r12.f2346v
            r12.mo1997a((android.graphics.PointF) r0, (android.graphics.PointF) r4)
        L_0x0424:
            int r0 = r12.f2344t
            if (r0 <= 0) goto L_0x0474
            boolean r0 = r12.f2342r
            if (r0 != 0) goto L_0x0431
            boolean r13 = r12.f2312ab
            if (r13 == 0) goto L_0x0474
            goto L_0x0463
        L_0x0431:
            if (r2 != r7) goto L_0x0463
            r12.f2312ab = r1
            android.graphics.PointF r0 = r12.f2337m
            android.graphics.PointF r4 = r12.f2336l
            float r4 = r4.x
            android.graphics.PointF r5 = r12.f2336l
            float r5 = r5.y
            r0.set(r4, r5)
            int r0 = r13.getActionIndex()
            if (r0 != r1) goto L_0x0456
            android.graphics.PointF r0 = r12.f2346v
            float r4 = r13.getX(r3)
            float r13 = r13.getY(r3)
            r0.set(r4, r13)
            goto L_0x0463
        L_0x0456:
            android.graphics.PointF r0 = r12.f2346v
            float r4 = r13.getX(r1)
            float r13 = r13.getY(r1)
            r0.set(r4, r13)
        L_0x0463:
            r13 = 3
            if (r2 >= r13) goto L_0x0468
            r12.f2342r = r3
        L_0x0468:
            if (r2 >= r7) goto L_0x046e
            r12.f2312ab = r3
            r12.f2344t = r3
        L_0x046e:
            r12.m2475c((boolean) r1)
            goto L_0x0501
        L_0x0474:
            if (r2 != r1) goto L_0x0501
            r12.f2342r = r3
            r12.f2312ab = r3
            r12.f2344t = r3
            goto L_0x0501
        L_0x047e:
            r12.f2291B = r0
            r12.mo3407b((boolean) r1)
            int r0 = r12.f2344t
            int r0 = java.lang.Math.max(r0, r2)
            r12.f2344t = r0
            if (r2 >= r7) goto L_0x04b4
            boolean r0 = r12.f2343s
            if (r0 != 0) goto L_0x0501
            android.graphics.PointF r0 = r12.f2337m
            android.graphics.PointF r2 = r12.f2336l
            float r2 = r2.x
            android.graphics.PointF r3 = r12.f2336l
            float r3 = r3.y
            r0.set(r2, r3)
            android.graphics.PointF r0 = r12.f2346v
            float r2 = r13.getX()
            float r13 = r13.getY()
            r0.set(r2, r13)
            android.os.Handler r13 = r12.f2321ak
            r2 = 600(0x258, double:2.964E-321)
            r13.sendEmptyMessageDelayed(r1, r2)
            goto L_0x0501
        L_0x04b4:
            boolean r0 = r12.f2330f
            if (r0 == 0) goto L_0x04f9
            float r0 = r13.getX(r3)
            float r2 = r13.getX(r1)
            float r4 = r13.getY(r3)
            float r5 = r13.getY(r1)
            float r0 = m2463a((float) r0, (float) r2, (float) r4, (float) r5)
            float r2 = r12.f2334j
            r12.f2335k = r2
            r12.f2318ah = r0
            android.graphics.PointF r0 = r12.f2337m
            android.graphics.PointF r2 = r12.f2336l
            float r2 = r2.x
            android.graphics.PointF r4 = r12.f2336l
            float r4 = r4.y
            r0.set(r2, r4)
            android.graphics.PointF r0 = r12.f2346v
            float r2 = r13.getX(r3)
            float r4 = r13.getX(r1)
            float r2 = r2 + r4
            float r2 = r2 / r6
            float r3 = r13.getY(r3)
            float r13 = r13.getY(r1)
            float r3 = r3 + r13
            float r3 = r3 / r6
            r0.set(r2, r3)
            goto L_0x04fc
        L_0x04f9:
            r12.f2344t = r3
        L_0x04fc:
            android.os.Handler r13 = r12.f2321ak
            r13.removeMessages(r1)
        L_0x0501:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bgo.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* renamed from: j */
    private final void m2483j() {
        Float f;
        if (getWidth() != 0 && getHeight() != 0 && this.f2340p > 0 && this.f2341q > 0) {
            if (!(this.f2339o == null || (f = this.f2338n) == null)) {
                this.f2334j = f.floatValue();
                if (this.f2336l == null) {
                    this.f2336l = new PointF();
                }
                this.f2336l.x = ((float) (getWidth() / 2)) - (this.f2334j * this.f2339o.x);
                this.f2336l.y = ((float) (getHeight() / 2)) - (this.f2334j * this.f2339o.y);
                this.f2339o = null;
                this.f2338n = null;
                m2477d(true);
                m2475c(true);
            }
            m2477d(false);
        }
    }

    /* renamed from: c */
    private final void m2475c(boolean z) {
        if (this.f2315ae != null && this.f2301N != null) {
            int min = Math.min(this.f2300M, m2472b(this.f2334j));
            for (Map.Entry value : this.f2301N.entrySet()) {
                for (bgl bgl : (List) value.getValue()) {
                    int i = bgl.f2269b;
                    if (i < min || (i > min && i != this.f2300M)) {
                        bgl.f2272e = false;
                        Bitmap bitmap = bgl.f2270c;
                        if (bitmap != null) {
                            bitmap.recycle();
                            bgl.f2270c = null;
                        }
                    }
                    int i2 = bgl.f2269b;
                    if (i2 == min) {
                        float c = m2474c(0.0f);
                        float c2 = m2474c((float) getWidth());
                        float d = m2476d(0.0f);
                        float d2 = m2476d((float) getHeight());
                        if (c <= ((float) bgl.f2268a.right) && ((float) bgl.f2268a.left) <= c2 && d <= ((float) bgl.f2268a.bottom) && ((float) bgl.f2268a.top) <= d2) {
                            bgl.f2272e = true;
                            if (!bgl.f2271d && bgl.f2270c == null && z) {
                                m2468a((AsyncTask) new bgm(this, this.f2315ae, bgl));
                            }
                        } else if (bgl.f2269b != this.f2300M) {
                            bgl.f2272e = false;
                            Bitmap bitmap2 = bgl.f2270c;
                            if (bitmap2 != null) {
                                bitmap2.recycle();
                                bgl.f2270c = null;
                            }
                        }
                    } else if (i2 == this.f2300M) {
                        bgl.f2272e = true;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private final void mo3407b(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final void mo2001a(boolean z) {
        StringBuilder sb = new StringBuilder(20);
        sb.append("reset newImage=");
        sb.append(z);
        sb.toString();
        this.f2334j = 0.0f;
        this.f2335k = 0.0f;
        this.f2336l = null;
        this.f2337m = null;
        this.f2310W = null;
        this.f2338n = Float.valueOf(0.0f);
        this.f2339o = null;
        this.f2342r = false;
        this.f2312ab = false;
        this.f2343s = false;
        this.f2344t = 0;
        this.f2300M = 0;
        this.f2346v = null;
        this.f2318ah = 0.0f;
        this.f2347w = 0.0f;
        this.f2348x = false;
        this.f2350z = null;
        this.f2349y = null;
        this.f2290A = null;
        this.f2291B = null;
        this.f2322al = null;
        this.f2323am = null;
        this.f2324an = null;
        if (z) {
            this.f2299L = null;
            this.f2345u.writeLock().lock();
            try {
                bgs bgs = this.f2315ae;
                if (bgs != null) {
                    bgs.mo2019b();
                    this.f2315ae = null;
                }
                this.f2345u.writeLock().unlock();
                Bitmap bitmap = this.f2297J;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.f2340p = 0;
                this.f2341q = 0;
                this.f2311aa = 0;
                this.f2292C = false;
                this.f2320aj = false;
                this.f2297J = null;
                this.f2298K = false;
            } catch (Throwable th) {
                this.f2345u.writeLock().unlock();
                throw th;
            }
        }
        Map map = this.f2301N;
        if (map != null) {
            for (Map.Entry value : map.entrySet()) {
                for (bgl bgl : (List) value.getValue()) {
                    bgl.f2272e = false;
                    Bitmap bitmap2 = bgl.f2270c;
                    if (bitmap2 != null) {
                        bitmap2.recycle();
                        bgl.f2270c = null;
                    }
                }
            }
            this.f2301N = null;
        }
        mo1994a(getContext());
    }

    /* renamed from: c */
    public final int mo2005c() {
        int d = mo2006d();
        return (d == 90 || d == 270) ? this.f2340p : this.f2341q;
    }

    /* renamed from: b */
    public final int mo2003b() {
        int d = mo2006d();
        return (d == 90 || d == 270) ? this.f2341q : this.f2340p;
    }

    /* renamed from: a */
    public final void mo1994a(Context context) {
        this.f2313ac = new GestureDetector(context, new bgc(this, context));
        this.f2314ad = new GestureDetector(context, new bgd(this));
    }

    /* renamed from: a */
    public final void mo1998a(bfz bfz) {
        mo1999a(bfz, (bfz) null);
    }

    /* renamed from: a */
    public final void mo1999a(bfz bfz, bfz bfz2) {
        int i;
        mo2001a(true);
        if (bfz2 != null) {
            if (bfz.f2227b == null) {
                int i2 = bfz.f2230e;
                if (i2 <= 0 || (i = bfz.f2231f) <= 0) {
                    throw new IllegalArgumentException("Preview image cannot be used unless dimensions are provided for the main image");
                }
                this.f2340p = i2;
                this.f2341q = i;
                Bitmap bitmap = bfz2.f2227b;
                if (bitmap == null) {
                    Uri uri = bfz2.f2226a;
                    if (uri == null && bfz2.f2228c != null) {
                        String packageName = getContext().getPackageName();
                        String valueOf = String.valueOf(bfz2.f2228c);
                        StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 20 + String.valueOf(valueOf).length());
                        sb.append("android.resource://");
                        sb.append(packageName);
                        sb.append("/");
                        sb.append(valueOf);
                        uri = Uri.parse(sb.toString());
                    }
                    m2468a((AsyncTask) new bgg(this, getContext(), this.f2316af, uri, true));
                } else {
                    mo1995a(bitmap);
                }
            } else {
                throw new IllegalArgumentException("Preview image cannot be used when a bitmap is provided for the main image");
            }
        }
        Bitmap bitmap2 = bfz.f2227b;
        if (bitmap2 == null) {
            Uri uri2 = bfz.f2226a;
            this.f2299L = uri2;
            if (uri2 == null && bfz.f2228c != null) {
                String packageName2 = getContext().getPackageName();
                String valueOf2 = String.valueOf(bfz.f2228c);
                StringBuilder sb2 = new StringBuilder(String.valueOf(packageName2).length() + 20 + String.valueOf(valueOf2).length());
                sb2.append("android.resource://");
                sb2.append(packageName2);
                sb2.append("/");
                sb2.append(valueOf2);
                this.f2299L = Uri.parse(sb2.toString());
            }
            if (bfz.f2229d) {
                m2468a((AsyncTask) new bgn(this, getContext(), this.f2317ag, this.f2299L));
                return;
            }
            m2468a((AsyncTask) new bgg(this, getContext(), this.f2316af, this.f2299L, false));
            return;
        }
        mo1996a(bitmap2, 0);
    }

    /* renamed from: a */
    private static final void m2471a(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        fArr[6] = f7;
        fArr[7] = f8;
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f2294E = onLongClickListener;
    }

    /* renamed from: a */
    public final void mo1993a(int i) {
        if (f2286I.contains(Integer.valueOf(i))) {
            this.f2302O = i;
            mo2001a(false);
            invalidate();
            requestLayout();
            return;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("Invalid orientation: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: b */
    public final PointF mo2004b(PointF pointF) {
        float f = pointF.x;
        float f2 = pointF.y;
        PointF pointF2 = new PointF();
        if (this.f2336l == null) {
            return null;
        }
        pointF2.set(m2478e(f), m2479f(f2));
        return pointF2;
    }

    /* renamed from: e */
    private final float m2478e(float f) {
        PointF pointF = this.f2336l;
        if (pointF != null) {
            return (f * this.f2334j) + pointF.x;
        }
        return Float.NaN;
    }

    /* renamed from: f */
    private final float m2479f(float f) {
        PointF pointF = this.f2336l;
        if (pointF != null) {
            return (f * this.f2334j) + pointF.y;
        }
        return Float.NaN;
    }

    /* renamed from: a */
    public final PointF mo1990a(float f, float f2, float f3) {
        int paddingLeft = getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2);
        int paddingTop = getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        if (this.f2322al == null) {
            this.f2322al = new bgk(0.0f, new PointF(0.0f, 0.0f));
        }
        bgk bgk = this.f2322al;
        bgk.f2266a = f3;
        bgk.f2267b.set(((float) paddingLeft) - (f * f3), ((float) paddingTop) - (f2 * f3));
        mo2002a(true, this.f2322al);
        return this.f2322al.f2267b;
    }

    /* renamed from: a */
    private final PointF m2466a(float f, float f2, PointF pointF) {
        if (this.f2336l == null) {
            return null;
        }
        pointF.set(m2474c(f), m2476d(f2));
        return pointF;
    }

    /* renamed from: a */
    public final PointF mo1991a(PointF pointF) {
        return m2466a(pointF.x, pointF.y, new PointF());
    }

    /* renamed from: c */
    private final float m2474c(float f) {
        PointF pointF = this.f2336l;
        if (pointF != null) {
            return (f - pointF.x) / this.f2334j;
        }
        return Float.NaN;
    }

    /* renamed from: d */
    private final float m2476d(float f) {
        PointF pointF = this.f2336l;
        if (pointF != null) {
            return (f - pointF.y) / this.f2334j;
        }
        return Float.NaN;
    }
}
