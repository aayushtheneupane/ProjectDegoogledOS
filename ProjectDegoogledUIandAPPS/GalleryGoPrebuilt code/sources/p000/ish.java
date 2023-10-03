package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* renamed from: ish */
/* compiled from: PG */
public class ish extends ImageView implements isg {

    /* renamed from: a */
    public isn f15001a;

    /* renamed from: b */
    private ImageView.ScaleType f15002b;

    public ish(Context context) {
        this(context, (AttributeSet) null);
    }

    public final ImageView.ScaleType getScaleType() {
        return this.f15001a.f15028n;
    }

    public ish(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ish(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        m14383a();
    }

    /* renamed from: a */
    private final void m14383a() {
        isn isn = this.f15001a;
        if (isn == null || isn.mo9095c() == null) {
            this.f15001a = new isn(this);
        }
        ImageView.ScaleType scaleType = this.f15002b;
        if (scaleType != null) {
            setScaleType(scaleType);
            this.f15002b = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        m14383a();
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        this.f15001a.mo9089a();
        super.onDetachedFromWindow();
    }

    public final void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        isn isn = this.f15001a;
        if (isn != null) {
            isn.mo9097e();
        }
    }

    public final void setImageResource(int i) {
        super.setImageResource(i);
        isn isn = this.f15001a;
        if (isn != null) {
            isn.mo9097e();
        }
    }

    public final void setImageURI(Uri uri) {
        super.setImageURI(uri);
        isn isn = this.f15001a;
        if (isn != null) {
            isn.mo9097e();
        }
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f15001a.f15025k = onLongClickListener;
    }

    public final void setScaleType(ImageView.ScaleType scaleType) {
        isn isn = this.f15001a;
        if (isn == null) {
            this.f15002b = scaleType;
        } else if (scaleType == null) {
        } else {
            if (isj.f15004a[scaleType.ordinal()] == 1) {
                throw new IllegalArgumentException(String.valueOf(scaleType.name()).concat(" is not supported in PhotoView"));
            } else if (scaleType != isn.f15028n) {
                isn.f15028n = scaleType;
                isn.mo9097e();
            }
        }
    }
}
