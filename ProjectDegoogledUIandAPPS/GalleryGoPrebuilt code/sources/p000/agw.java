package p000;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: agw */
/* compiled from: PG */
public final class agw extends ago {

    /* renamed from: b */
    public static final PorterDuff.Mode f451b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c */
    private agu f452c;

    /* renamed from: d */
    private PorterDuffColorFilter f453d;

    /* renamed from: e */
    private ColorFilter f454e;

    /* renamed from: f */
    private boolean f455f;

    /* renamed from: g */
    private final boolean f456g;

    /* renamed from: h */
    private final float[] f457h;

    /* renamed from: i */
    private final Matrix f458i;

    /* renamed from: j */
    private final Rect f459j;

    public agw() {
        this.f456g = true;
        this.f457h = new float[9];
        this.f458i = new Matrix();
        this.f459j = new Rect();
        this.f452c = new agu();
    }

    public agw(agu agu) {
        this.f456g = true;
        this.f457h = new float[9];
        this.f458i = new Matrix();
        this.f459j = new Rect();
        this.f452c = agu;
        this.f453d = m467a(agu.f440c, agu.f441d);
    }

    /* renamed from: a */
    static int m466a(int i, float f) {
        return (((int) (((float) Color.alpha(i)) * f)) << 24) | (16777215 & i);
    }

    public final boolean canApplyTheme() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        drawable.canApplyTheme();
        return false;
    }

    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.f396a;
        if (drawable == null) {
            copyBounds(this.f459j);
            if (this.f459j.width() > 0 && this.f459j.height() > 0) {
                ColorFilter colorFilter = this.f454e;
                if (colorFilter == null) {
                    colorFilter = this.f453d;
                }
                canvas.getMatrix(this.f458i);
                this.f458i.getValues(this.f457h);
                float abs = Math.abs(this.f457h[0]);
                float abs2 = Math.abs(this.f457h[4]);
                float abs3 = Math.abs(this.f457h[1]);
                float abs4 = Math.abs(this.f457h[3]);
                if (!(abs3 == 0.0f && abs4 == 0.0f)) {
                    abs = 1.0f;
                    abs2 = 1.0f;
                }
                int width = this.f459j.width();
                int height = this.f459j.height();
                int min = Math.min(2048, (int) (((float) width) * abs));
                int min2 = Math.min(2048, (int) (((float) height) * abs2));
                if (min > 0 && min2 > 0) {
                    int save = canvas.save();
                    canvas.translate((float) this.f459j.left, (float) this.f459j.top);
                    int i = Build.VERSION.SDK_INT;
                    if (isAutoMirrored()) {
                        int i2 = Build.VERSION.SDK_INT;
                        if (getLayoutDirection() == 1) {
                            canvas.translate((float) this.f459j.width(), 0.0f);
                            canvas.scale(-1.0f, 1.0f);
                        }
                    }
                    this.f459j.offsetTo(0, 0);
                    agu agu = this.f452c;
                    Bitmap bitmap = agu.f443f;
                    if (!(bitmap != null && min == bitmap.getWidth() && min2 == agu.f443f.getHeight())) {
                        agu.f443f = Bitmap.createBitmap(min, min2, Bitmap.Config.ARGB_8888);
                        agu.f448k = true;
                    }
                    if (this.f456g) {
                        agu agu2 = this.f452c;
                        if (!(!agu2.f448k && agu2.f444g == agu2.f440c && agu2.f445h == agu2.f441d && agu2.f447j == agu2.f442e && agu2.f446i == agu2.f439b.getRootAlpha())) {
                            this.f452c.mo420a(min, min2);
                            agu agu3 = this.f452c;
                            agu3.f444g = agu3.f440c;
                            agu3.f445h = agu3.f441d;
                            agu3.f446i = agu3.f439b.getRootAlpha();
                            agu3.f447j = agu3.f442e;
                            agu3.f448k = false;
                        }
                    } else {
                        this.f452c.mo420a(min, min2);
                    }
                    agu agu4 = this.f452c;
                    Rect rect = this.f459j;
                    if (agu4.f439b.getRootAlpha() >= 255 && colorFilter == null) {
                        paint = null;
                    } else {
                        if (agu4.f449l == null) {
                            agu4.f449l = new Paint();
                            agu4.f449l.setFilterBitmap(true);
                        }
                        agu4.f449l.setAlpha(agu4.f439b.getRootAlpha());
                        agu4.f449l.setColorFilter(colorFilter);
                        paint = agu4.f449l;
                    }
                    canvas.drawBitmap(agu4.f443f, (Rect) null, rect, paint);
                    canvas.restoreToCount(save);
                    return;
                }
                return;
            }
            return;
        }
        drawable.draw(canvas);
    }

    public final int getAlpha() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return this.f452c.f439b.getRootAlpha();
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getAlpha();
    }

    public final int getChangingConfigurations() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return super.getChangingConfigurations() | this.f452c.getChangingConfigurations();
        }
        return drawable.getChangingConfigurations();
    }

    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return this.f454e;
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getColorFilter();
    }

    public final Drawable.ConstantState getConstantState() {
        if (this.f396a != null) {
            int i = Build.VERSION.SDK_INT;
            return new agv(this.f396a.getConstantState());
        }
        this.f452c.f438a = getChangingConfigurations();
        return this.f452c;
    }

    public final int getIntrinsicHeight() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return (int) this.f452c.f439b.f427f;
        }
        return drawable.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return (int) this.f452c.f439b.f426e;
        }
        return drawable.getIntrinsicWidth();
    }

    public final int getOpacity() {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
        }
    }

    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        ColorStateList colorStateList;
        Resources resources2 = resources;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        AttributeSet attributeSet2 = attributeSet;
        Resources.Theme theme2 = theme;
        Drawable drawable = this.f396a;
        if (drawable == null) {
            agu agu = this.f452c;
            agu.f439b = new agt();
            TypedArray a = C0071co.m4657a(resources2, theme2, attributeSet2, agn.f392a);
            agu agu2 = this.f452c;
            agt agt = agu2.f439b;
            int a2 = C0071co.m4656a(a, xmlPullParser2, "tintMode", 6, -1);
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (a2 == 3) {
                mode = PorterDuff.Mode.SRC_OVER;
            } else if (a2 == 5) {
                mode = PorterDuff.Mode.SRC_IN;
            } else if (a2 != 9) {
                switch (a2) {
                    case 14:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case 15:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
            agu2.f441d = mode;
            int i = 2;
            if (C0071co.m4667a(xmlPullParser2, "tint")) {
                TypedValue typedValue = new TypedValue();
                a.getValue(1, typedValue);
                if (typedValue.type == 2) {
                    throw new UnsupportedOperationException("Failed to resolve attribute at index 1: " + typedValue);
                } else if (typedValue.type < 28 || typedValue.type > 31) {
                    Resources resources3 = a.getResources();
                    try {
                        XmlResourceParser xml = resources3.getXml(a.getResourceId(1, 0));
                        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                        while (true) {
                            int next = xml.next();
                            if (next == 2) {
                                colorStateList = cun.m5439a(resources3, xml, asAttributeSet, theme2);
                            } else if (next == 1) {
                                throw new XmlPullParserException("No start tag found");
                            }
                        }
                    } catch (Exception e) {
                        Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
                        colorStateList = null;
                    }
                } else {
                    colorStateList = ColorStateList.valueOf(typedValue.data);
                }
            } else {
                colorStateList = null;
            }
            if (colorStateList != null) {
                agu2.f440c = colorStateList;
            }
            boolean z = agu2.f442e;
            if (C0071co.m4667a(xmlPullParser2, "autoMirrored")) {
                z = a.getBoolean(5, z);
            }
            agu2.f442e = z;
            agt.f428g = C0071co.m4651a(a, xmlPullParser2, "viewportWidth", 7, agt.f428g);
            float a3 = C0071co.m4651a(a, xmlPullParser2, "viewportHeight", 8, agt.f429h);
            agt.f429h = a3;
            if (agt.f428g <= 0.0f) {
                throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
            } else if (a3 > 0.0f) {
                agt.f426e = a.getDimension(3, agt.f426e);
                float dimension = a.getDimension(2, agt.f427f);
                agt.f427f = dimension;
                if (agt.f426e <= 0.0f) {
                    throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires width > 0");
                } else if (dimension > 0.0f) {
                    agt.setAlpha(C0071co.m4651a(a, xmlPullParser2, "alpha", 4, agt.getAlpha()));
                    String string = a.getString(0);
                    if (string != null) {
                        agt.f430i = string;
                        agt.f432k.put(string, agt);
                    }
                    a.recycle();
                    agu.f438a = getChangingConfigurations();
                    agu.f448k = true;
                    agt agt2 = this.f452c.f439b;
                    ArrayDeque arrayDeque = new ArrayDeque();
                    arrayDeque.push(agt2.f425d);
                    int eventType = xmlPullParser.getEventType();
                    int depth = xmlPullParser.getDepth() + 1;
                    boolean z2 = true;
                    for (int i2 = 1; eventType != i2 && (xmlPullParser.getDepth() >= depth || eventType != 3); i2 = 1) {
                        if (eventType == i) {
                            String name = xmlPullParser.getName();
                            agr agr = (agr) arrayDeque.peek();
                            if ("path".equals(name)) {
                                agq agq = new agq();
                                TypedArray a4 = C0071co.m4657a(resources2, theme2, attributeSet2, agn.f394c);
                                if (C0071co.m4667a(xmlPullParser2, "pathData")) {
                                    String string2 = a4.getString(0);
                                    if (string2 != null) {
                                        agq.f420m = string2;
                                    }
                                    String string3 = a4.getString(i);
                                    if (string3 != null) {
                                        agq.f419l = C0257jh.m14484a(string3);
                                    }
                                    agq.f399c = C0071co.m4661a(a4, xmlPullParser2, theme2, "fillColor", 1);
                                    agq.f401e = C0071co.m4651a(a4, xmlPullParser2, "fillAlpha", 12, agq.f401e);
                                    int a5 = C0071co.m4656a(a4, xmlPullParser2, "strokeLineCap", 8, -1);
                                    Paint.Cap cap = agq.f405i;
                                    if (a5 == 0) {
                                        cap = Paint.Cap.BUTT;
                                    } else if (a5 == 1) {
                                        cap = Paint.Cap.ROUND;
                                    } else if (a5 == 2) {
                                        cap = Paint.Cap.SQUARE;
                                    }
                                    agq.f405i = cap;
                                    int a6 = C0071co.m4656a(a4, xmlPullParser2, "strokeLineJoin", 9, -1);
                                    Paint.Join join = agq.f406j;
                                    if (a6 == 0) {
                                        join = Paint.Join.MITER;
                                    } else if (a6 == 1) {
                                        join = Paint.Join.ROUND;
                                    } else if (a6 == 2) {
                                        join = Paint.Join.BEVEL;
                                    }
                                    agq.f406j = join;
                                    agq.f407k = C0071co.m4651a(a4, xmlPullParser2, "strokeMiterLimit", 10, agq.f407k);
                                    agq.f397a = C0071co.m4661a(a4, xmlPullParser2, theme2, "strokeColor", 3);
                                    agq.f400d = C0071co.m4651a(a4, xmlPullParser2, "strokeAlpha", 11, agq.f400d);
                                    agq.f398b = C0071co.m4651a(a4, xmlPullParser2, "strokeWidth", 4, agq.f398b);
                                    agq.f403g = C0071co.m4651a(a4, xmlPullParser2, "trimPathEnd", 6, agq.f403g);
                                    agq.f404h = C0071co.m4651a(a4, xmlPullParser2, "trimPathOffset", 7, agq.f404h);
                                    agq.f402f = C0071co.m4651a(a4, xmlPullParser2, "trimPathStart", 5, agq.f402f);
                                    agq.f421n = C0071co.m4656a(a4, xmlPullParser2, "fillType", 13, agq.f421n);
                                }
                                a4.recycle();
                                agr.f409b.add(agq);
                                if (agq.getPathName() != null) {
                                    agt2.f432k.put(agq.getPathName(), agq);
                                }
                                z2 = false;
                            } else if ("clip-path".equals(name)) {
                                agp agp = new agp();
                                if (C0071co.m4667a(xmlPullParser2, "pathData")) {
                                    TypedArray a7 = C0071co.m4657a(resources2, theme2, attributeSet2, agn.f395d);
                                    String string4 = a7.getString(0);
                                    if (string4 != null) {
                                        agp.f420m = string4;
                                    }
                                    String string5 = a7.getString(1);
                                    if (string5 != null) {
                                        agp.f419l = C0257jh.m14484a(string5);
                                    }
                                    agp.f421n = C0071co.m4656a(a7, xmlPullParser2, "fillType", 2, 0);
                                    a7.recycle();
                                }
                                agr.f409b.add(agp);
                                if (agp.getPathName() != null) {
                                    agt2.f432k.put(agp.getPathName(), agp);
                                }
                            } else if ("group".equals(name)) {
                                agr agr2 = new agr();
                                TypedArray a8 = C0071co.m4657a(resources2, theme2, attributeSet2, agn.f393b);
                                agr2.f410c = C0071co.m4651a(a8, xmlPullParser2, "rotation", 5, agr2.f410c);
                                agr2.f411d = a8.getFloat(1, agr2.f411d);
                                agr2.f412e = a8.getFloat(2, agr2.f412e);
                                agr2.f413f = C0071co.m4651a(a8, xmlPullParser2, "scaleX", 3, agr2.f413f);
                                agr2.f414g = C0071co.m4651a(a8, xmlPullParser2, "scaleY", 4, agr2.f414g);
                                agr2.f415h = C0071co.m4651a(a8, xmlPullParser2, "translateX", 6, agr2.f415h);
                                agr2.f416i = C0071co.m4651a(a8, xmlPullParser2, "translateY", 7, agr2.f416i);
                                String string6 = a8.getString(0);
                                if (string6 != null) {
                                    agr2.f418k = string6;
                                }
                                agr2.mo395a();
                                a8.recycle();
                                agr.f409b.add(agr2);
                                arrayDeque.push(agr2);
                                if (agr2.getGroupName() != null) {
                                    agt2.f432k.put(agr2.getGroupName(), agr2);
                                }
                            }
                        } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                            arrayDeque.pop();
                        }
                        eventType = xmlPullParser.next();
                        i = 2;
                    }
                    if (!z2) {
                        this.f453d = m467a(agu.f440c, agu.f441d);
                        return;
                    }
                    throw new XmlPullParserException("no path defined");
                } else {
                    throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires height > 0");
                }
            } else {
                throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
            }
        } else {
            int i3 = Build.VERSION.SDK_INT;
            drawable.inflate(resources2, xmlPullParser2, attributeSet2, theme2);
        }
    }

    public final void invalidateSelf() {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public final boolean isAutoMirrored() {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            return this.f452c.f442e;
        }
        return C0257jh.m14480a(drawable);
    }

    public final boolean isStateful() {
        ColorStateList colorStateList;
        Drawable drawable = this.f396a;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (!super.isStateful()) {
            agu agu = this.f452c;
            if (agu == null) {
                return false;
            }
            if (agu.mo421a() || ((colorStateList = this.f452c.f440c) != null && colorStateList.isStateful())) {
                return true;
            }
            return false;
        }
        return true;
    }

    public final Drawable mutate() {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f455f && super.mutate() == this) {
            this.f452c = new agu(this.f452c);
            this.f455f = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f396a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        agu agu = this.f452c;
        ColorStateList colorStateList = agu.f440c;
        boolean z = false;
        if (!(colorStateList == null || (mode = agu.f441d) == null)) {
            this.f453d = m467a(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (agu.mo421a()) {
            boolean a = agu.f439b.f425d.mo377a(iArr);
            agu.f448k |= a;
            if (a) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    public final void setAlpha(int i) {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f452c.f439b.getRootAlpha() != i) {
            this.f452c.f439b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            this.f452c.f442e = z;
        } else {
            C0257jh.m14477a(drawable, z);
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            this.f454e = colorFilter;
            invalidateSelf();
            return;
        }
        drawable.setColorFilter(colorFilter);
    }

    public final void setTint(int i) {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            setTintList(ColorStateList.valueOf(i));
        } else {
            C0257jh.m14473a(drawable, i);
        }
    }

    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            agu agu = this.f452c;
            if (agu.f440c != colorStateList) {
                agu.f440c = colorStateList;
                this.f453d = m467a(colorStateList, agu.f441d);
                invalidateSelf();
                return;
            }
            return;
        }
        C0257jh.m14475a(drawable, colorStateList);
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f396a;
        if (drawable == null) {
            agu agu = this.f452c;
            if (agu.f441d != mode) {
                agu.f441d = mode;
                this.f453d = m467a(agu.f440c, mode);
                invalidateSelf();
                return;
            }
            return;
        }
        C0257jh.m14476a(drawable, mode);
    }

    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f396a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* renamed from: a */
    private final PorterDuffColorFilter m467a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }
}
