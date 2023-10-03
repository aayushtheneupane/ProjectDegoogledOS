package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: um */
/* compiled from: PG */
final class C0559um {

    /* renamed from: i */
    private static final RectF f16012i = new RectF();

    /* renamed from: j */
    private static final ConcurrentHashMap f16013j = new ConcurrentHashMap();

    /* renamed from: a */
    public int f16014a = 0;

    /* renamed from: b */
    public boolean f16015b = false;

    /* renamed from: c */
    public float f16016c = -1.0f;

    /* renamed from: d */
    public float f16017d = -1.0f;

    /* renamed from: e */
    public float f16018e = -1.0f;

    /* renamed from: f */
    public int[] f16019f = new int[0];

    /* renamed from: g */
    public boolean f16020g = false;

    /* renamed from: h */
    public final Context f16021h;

    /* renamed from: k */
    private TextPaint f16022k;

    /* renamed from: l */
    private final TextView f16023l;

    static {
        new ConcurrentHashMap();
    }

    public C0559um(TextView textView) {
        this.f16023l = textView;
        this.f16021h = textView.getContext();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo10293f() {
        boolean z;
        int i;
        TextDirectionHeuristic textDirectionHeuristic;
        if (mo10294g()) {
            if (this.f16015b) {
                if (this.f16023l.getMeasuredHeight() > 0 && this.f16023l.getMeasuredWidth() > 0) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        z = this.f16023l.isHorizontallyScrollable();
                    } else {
                        z = ((Boolean) m15512a((Object) this.f16023l, "getHorizontallyScrolling", (Object) false)).booleanValue();
                    }
                    if (!z) {
                        i = (this.f16023l.getMeasuredWidth() - this.f16023l.getTotalPaddingLeft()) - this.f16023l.getTotalPaddingRight();
                    } else {
                        i = 1048576;
                    }
                    int height = (this.f16023l.getHeight() - this.f16023l.getCompoundPaddingBottom()) - this.f16023l.getCompoundPaddingTop();
                    if (i > 0 && height > 0) {
                        synchronized (f16012i) {
                            f16012i.setEmpty();
                            f16012i.right = (float) i;
                            f16012i.bottom = (float) height;
                            RectF rectF = f16012i;
                            int length = this.f16019f.length;
                            if (length != 0) {
                                int i2 = length - 1;
                                int i3 = 0;
                                int i4 = 1;
                                while (i4 <= i2) {
                                    int i5 = (i4 + i2) / 2;
                                    int i6 = this.f16019f[i5];
                                    CharSequence text = this.f16023l.getText();
                                    TransformationMethod transformationMethod = this.f16023l.getTransformationMethod();
                                    if (transformationMethod != null) {
                                        CharSequence transformation = transformationMethod.getTransformation(text, this.f16023l);
                                        if (transformation != null) {
                                            text = transformation;
                                        }
                                    }
                                    int i7 = Build.VERSION.SDK_INT;
                                    int maxLines = this.f16023l.getMaxLines();
                                    TextPaint textPaint = this.f16022k;
                                    if (textPaint == null) {
                                        this.f16022k = new TextPaint();
                                    } else {
                                        textPaint.reset();
                                    }
                                    this.f16022k.set(this.f16023l.getPaint());
                                    this.f16022k.setTextSize((float) i6);
                                    int round = Math.round(rectF.right);
                                    int i8 = Build.VERSION.SDK_INT;
                                    StaticLayout.Builder obtain = StaticLayout.Builder.obtain(text, 0, text.length(), this.f16022k, round);
                                    obtain.setAlignment((Layout.Alignment) m15512a((Object) this.f16023l, "getLayoutAlignment", (Object) Layout.Alignment.ALIGN_NORMAL)).setLineSpacing(this.f16023l.getLineSpacingExtra(), this.f16023l.getLineSpacingMultiplier()).setIncludePad(this.f16023l.getIncludeFontPadding()).setBreakStrategy(this.f16023l.getBreakStrategy()).setHyphenationFrequency(this.f16023l.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines);
                                    try {
                                        if (Build.VERSION.SDK_INT < 29) {
                                            textDirectionHeuristic = (TextDirectionHeuristic) m15512a((Object) this.f16023l, "getTextDirectionHeuristic", (Object) TextDirectionHeuristics.FIRSTSTRONG_LTR);
                                        } else {
                                            textDirectionHeuristic = this.f16023l.getTextDirectionHeuristic();
                                        }
                                        obtain.setTextDirection(textDirectionHeuristic);
                                    } catch (ClassCastException e) {
                                        Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
                                    }
                                    StaticLayout build = obtain.build();
                                    if (maxLines != -1) {
                                        if (build.getLineCount() <= maxLines) {
                                            if (build.getLineEnd(build.getLineCount() - 1) != text.length()) {
                                            }
                                        }
                                        i3 = i5 - 1;
                                        i2 = i3;
                                    }
                                    if (((float) build.getHeight()) <= rectF.bottom) {
                                        i3 = i4;
                                        i4 = i5 + 1;
                                    }
                                    i3 = i5 - 1;
                                    i2 = i3;
                                }
                                float f = (float) this.f16019f[i3];
                                if (f != this.f16023l.getTextSize()) {
                                    mo10288a(0, f);
                                }
                            } else {
                                throw new IllegalStateException("No available text sizes to choose from.");
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.f16015b = true;
        }
    }

    /* renamed from: a */
    public static final int[] m15514a(int[] iArr) {
        if (r0 != 0) {
            Arrays.sort(iArr);
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                if (i > 0) {
                    Integer valueOf = Integer.valueOf(i);
                    if (Collections.binarySearch(arrayList, valueOf) < 0) {
                        arrayList.add(valueOf);
                    }
                }
            }
            if (r0 != arrayList.size()) {
                int size = arrayList.size();
                int[] iArr2 = new int[size];
                for (int i2 = 0; i2 < size; i2++) {
                    iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
                }
                return iArr2;
            }
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final int mo10290c() {
        return Math.round(this.f16018e);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo10289b() {
        return Math.round(this.f16017d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo10286a() {
        return Math.round(this.f16016c);
    }

    /* renamed from: a */
    private static Method m15513a(String str) {
        try {
            Method method = (Method) f16013j.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                f16013j.put(str, method);
            }
            return method;
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e);
            return null;
        }
    }

    /* renamed from: a */
    private static Object m15512a(Object obj, String str, Object obj2) {
        try {
            obj2 = m15513a(str).invoke(obj, new Object[0]);
            if (obj2 != null) {
                return obj2;
            }
            return null;
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo10294g() {
        return mo10295h() && this.f16014a != 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10288a(int i, float f) {
        Resources resources;
        Context context = this.f16021h;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        float applyDimension = TypedValue.applyDimension(i, f, resources.getDisplayMetrics());
        if (applyDimension != this.f16023l.getPaint().getTextSize()) {
            this.f16023l.getPaint().setTextSize(applyDimension);
            int i2 = Build.VERSION.SDK_INT;
            boolean isInLayout = this.f16023l.isInLayout();
            if (this.f16023l.getLayout() != null) {
                this.f16015b = false;
                try {
                    Method a = m15513a("nullLayouts");
                    if (a != null) {
                        a.invoke(this.f16023l, new Object[0]);
                    }
                } catch (Exception e) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
                }
                if (isInLayout) {
                    this.f16023l.forceLayout();
                } else {
                    this.f16023l.requestLayout();
                }
                this.f16023l.invalidate();
            }
        }
    }

    /* renamed from: e */
    public final boolean mo10292e() {
        if (!mo10295h() || this.f16014a != 1) {
            this.f16015b = false;
            return false;
        }
        if (!this.f16020g || this.f16019f.length == 0) {
            int floor = ((int) Math.floor((double) ((this.f16018e - this.f16017d) / this.f16016c))) + 1;
            int[] iArr = new int[floor];
            for (int i = 0; i < floor; i++) {
                iArr[i] = Math.round(this.f16017d + (((float) i) * this.f16016c));
            }
            this.f16019f = m15514a(iArr);
        }
        this.f16015b = true;
        return true;
    }

    /* renamed from: d */
    public final boolean mo10291d() {
        int[] iArr = this.f16019f;
        int length = iArr.length;
        boolean z = length > 0;
        this.f16020g = z;
        if (z) {
            this.f16014a = 1;
            this.f16017d = (float) iArr[0];
            this.f16018e = (float) iArr[length - 1];
            this.f16016c = -1.0f;
        }
        return z;
    }

    /* renamed from: h */
    public final boolean mo10295h() {
        return !(this.f16023l instanceof C0530tk);
    }

    /* renamed from: a */
    public final void mo10287a(float f, float f2, float f3) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        } else if (f2 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        } else if (f3 > 0.0f) {
            this.f16014a = 1;
            this.f16017d = f;
            this.f16018e = f2;
            this.f16016c = f3;
            this.f16020g = false;
        } else {
            throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
        }
    }
}
