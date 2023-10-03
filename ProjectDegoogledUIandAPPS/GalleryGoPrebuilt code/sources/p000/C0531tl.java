package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.google.android.apps.photosgo.R;

/* renamed from: tl */
/* compiled from: PG */
public class C0531tl extends ImageButton {

    /* renamed from: a */
    private final C0523td f15940a;

    /* renamed from: b */
    private final C0532tm f15941b;

    public C0531tl(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0531tl(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    public C0531tl(Context context, AttributeSet attributeSet, int i) {
        super(C0680yz.m16188a(context), attributeSet, i);
        C0523td tdVar = new C0523td(this);
        this.f15940a = tdVar;
        tdVar.mo10104a(attributeSet, i);
        C0532tm tmVar = new C0532tm(this);
        this.f15941b = tmVar;
        tmVar.mo10152a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15940a;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0532tm tmVar = this.f15941b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }

    public final boolean hasOverlappingRendering() {
        return this.f15941b.mo10153a() && super.hasOverlappingRendering();
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15940a;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15940a;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0532tm tmVar = this.f15941b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }

    public final void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        C0532tm tmVar = this.f15941b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }

    public final void setImageResource(int i) {
        this.f15941b.mo10151a(i);
    }

    public final void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C0532tm tmVar = this.f15941b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }
}
