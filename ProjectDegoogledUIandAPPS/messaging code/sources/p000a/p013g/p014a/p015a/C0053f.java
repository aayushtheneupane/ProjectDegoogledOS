package p000a.p013g.p014a.p015a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.core.content.p022a.C0308a;
import androidx.core.graphics.drawable.C0322a;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import p000a.p005b.C0015b;

/* renamed from: a.g.a.a.f */
public class C0053f extends C0054g implements C0049b {

    /* renamed from: jd */
    private C0051d f38jd;

    /* renamed from: kd */
    private ArgbEvaluator f39kd;
    final Drawable.Callback mCallback;
    private Context mContext;

    C0053f() {
        this((Context) null, (C0051d) null, (Resources) null);
    }

    /* renamed from: a */
    public static C0053f m60a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        C0053f fVar = new C0053f(context, (C0051d) null, (Resources) null);
        fVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return fVar;
    }

    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.applyTheme(theme);
        }
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.canApplyTheme();
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f38jd.mVectorDrawable.draw(canvas);
        if (this.f38jd.f36vc.isStarted()) {
            invalidateSelf();
        }
    }

    public int getAlpha() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return this.f38jd.mVectorDrawable.getAlpha();
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.f38jd.mChangingConfigurations | super.getChangingConfigurations();
    }

    public ColorFilter getColorFilter() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return this.f38jd.mVectorDrawable.getColorFilter();
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        return new C0052e(drawable.getConstantState());
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return this.f38jd.mVectorDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return this.f38jd.mVectorDrawable.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        Drawable drawable2 = this.f38jd.mVectorDrawable.f40hd;
        if (drawable2 != null) {
            return drawable2.getOpacity();
        }
        return -3;
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray a = C0308a.m241a(resources, theme, attributeSet, C0048a.f28Qt);
                    int resourceId = a.getResourceId(0, 0);
                    if (resourceId != 0) {
                        C0064q a2 = C0064q.m81a(resources, resourceId, theme);
                        a2.setAllowCaching(false);
                        a2.setCallback(this.mCallback);
                        C0064q qVar = this.f38jd.mVectorDrawable;
                        if (qVar != null) {
                            qVar.setCallback((Drawable.Callback) null);
                        }
                        this.f38jd.mVectorDrawable = a2;
                    }
                    a.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, C0048a.f29Rt);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.mContext;
                        if (context != null) {
                            int i2 = Build.VERSION.SDK_INT;
                            Animator loadAnimator = AnimatorInflater.loadAnimator(context, resourceId2);
                            loadAnimator.setTarget(this.f38jd.mVectorDrawable.getTargetByName(string));
                            int i3 = Build.VERSION.SDK_INT;
                            C0051d dVar = this.f38jd;
                            if (dVar.mAnimators == null) {
                                dVar.mAnimators = new ArrayList();
                                this.f38jd.mTargetNameMap = new C0015b();
                            }
                            this.f38jd.mAnimators.add(loadAnimator);
                            this.f38jd.mTargetNameMap.put(loadAnimator, string);
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        C0051d dVar2 = this.f38jd;
        if (dVar2.f36vc == null) {
            dVar2.f36vc = new AnimatorSet();
        }
        dVar2.f36vc.playTogether(dVar2.mAnimators);
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return C0322a.m263c(drawable);
        }
        return this.f38jd.mVectorDrawable.isAutoMirrored();
    }

    public boolean isRunning() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return ((AnimatedVectorDrawable) drawable).isRunning();
        }
        return this.f38jd.f36vc.isRunning();
    }

    public boolean isStateful() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return this.f38jd.mVectorDrawable.isStateful();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f38jd.mVectorDrawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        return this.f38jd.mVectorDrawable.setLevel(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        return this.f38jd.mVectorDrawable.setState(iArr);
    }

    public void setAlpha(int i) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f38jd.mVectorDrawable.setAlpha(i);
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setAutoMirrored(z);
            return;
        }
        this.f38jd.mVectorDrawable.setAutoMirrored(z);
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setFilterBitmap(boolean z) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setFilterBitmap(z);
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setHotspot(f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i5 = Build.VERSION.SDK_INT;
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    public void setTint(int i) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable.setTint(i);
            return;
        }
        C0064q qVar = this.f38jd.mVectorDrawable;
        Drawable drawable2 = qVar.f40hd;
        if (drawable2 != null) {
            int i3 = Build.VERSION.SDK_INT;
            drawable2.setTint(i);
            return;
        }
        qVar.setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintList(colorStateList);
            return;
        }
        this.f38jd.mVectorDrawable.setTintList(colorStateList);
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
            return;
        }
        this.f38jd.mVectorDrawable.setTintMode(mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f38jd.mVectorDrawable.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public void start() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else if (!this.f38jd.f36vc.isStarted()) {
            this.f38jd.f36vc.start();
            invalidateSelf();
        }
    }

    public void stop() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f38jd.f36vc.end();
        }
    }

    private C0053f(Context context, C0051d dVar, Resources resources) {
        this.f39kd = null;
        this.mCallback = new C0050c(this);
        this.mContext = context;
        if (dVar != null) {
            this.f38jd = dVar;
        } else {
            this.f38jd = new C0051d(dVar, this.mCallback, resources);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f38jd.mVectorDrawable.setColorFilter(colorFilter);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
    }
}
