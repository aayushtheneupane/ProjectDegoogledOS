package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;

/* renamed from: aak */
/* compiled from: PG */
public class aak extends FrameLayout {

    /* renamed from: a */
    public static final aan f22a = new aal();

    /* renamed from: g */
    private static final int[] f23g = {16842801};

    /* renamed from: b */
    public boolean f24b;

    /* renamed from: c */
    public boolean f25c;

    /* renamed from: d */
    public final Rect f26d;

    /* renamed from: e */
    public final Rect f27e;

    /* renamed from: f */
    public final aam f28f;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
    }

    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public aak(Context context) {
        this(context, (AttributeSet) null);
    }

    public aak(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.cardViewStyle);
    }

    public aak(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ColorStateList colorStateList;
        int i2;
        this.f26d = new Rect();
        this.f27e = new Rect();
        this.f28f = new aaj(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, aai.f19a, i, R.style.CardView);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, aai.f19a, attributeSet, obtainStyledAttributes, i, R.style.CardView);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            colorStateList = obtainStyledAttributes.getColorStateList(2);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(f23g);
            int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            if (fArr[2] > 0.5f) {
                i2 = getResources().getColor(R.color.cardview_light_background);
            } else {
                i2 = getResources().getColor(R.color.cardview_dark_background);
            }
            colorStateList = ColorStateList.valueOf(i2);
        }
        float dimension = obtainStyledAttributes.getDimension(3, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(4, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(5, 0.0f);
        this.f24b = obtainStyledAttributes.getBoolean(7, false);
        this.f25c = obtainStyledAttributes.getBoolean(6, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
        this.f26d.left = obtainStyledAttributes.getDimensionPixelSize(10, dimensionPixelSize);
        this.f26d.top = obtainStyledAttributes.getDimensionPixelSize(12, dimensionPixelSize);
        this.f26d.right = obtainStyledAttributes.getDimensionPixelSize(11, dimensionPixelSize);
        this.f26d.bottom = obtainStyledAttributes.getDimensionPixelSize(9, dimensionPixelSize);
        dimension3 = dimension2 > dimension3 ? dimension2 : dimension3;
        obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        aan aan = f22a;
        aam aam = this.f28f;
        aao aao = new aao(colorStateList, dimension);
        aaj aaj = (aaj) aam;
        aaj.f20a = aao;
        aaj.f21b.setBackgroundDrawable(aao);
        aak aak = aaj.f21b;
        aak.setClipToOutline(true);
        aak.setElevation(dimension2);
        Drawable drawable = aaj.f20a;
        boolean a = aam.mo17a();
        boolean b = aam.mo18b();
        aao aao2 = (aao) drawable;
        if (!(dimension3 == aao2.f30b && aao2.f31c == a && aao2.f32d == b)) {
            aao2.f30b = dimension3;
            aao2.f31c = a;
            aao2.f32d = b;
            aao2.mo25a((Rect) null);
            aao2.invalidateSelf();
        }
        ((aal) aan).mo24c(aam);
    }

    /* renamed from: a */
    public final float mo19a() {
        return f22a.mo22a(this.f28f);
    }
}
