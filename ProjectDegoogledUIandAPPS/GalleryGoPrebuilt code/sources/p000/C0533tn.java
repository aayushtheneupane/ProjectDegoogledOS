package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: tn */
/* compiled from: PG */
public class C0533tn extends ImageView {

    /* renamed from: a */
    private final C0523td f15943a;

    /* renamed from: b */
    private final C0532tm f15944b;

    public C0533tn(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0533tn(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C0533tn(Context context, AttributeSet attributeSet, int i) {
        super(C0680yz.m16188a(context), attributeSet, i);
        C0523td tdVar = new C0523td(this);
        this.f15943a = tdVar;
        tdVar.mo10104a(attributeSet, i);
        C0532tm tmVar = new C0532tm(this);
        this.f15944b = tmVar;
        tmVar.mo10152a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15943a;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0532tm tmVar = this.f15944b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }

    public final boolean hasOverlappingRendering() {
        return this.f15944b.mo10153a() && super.hasOverlappingRendering();
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15943a;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15943a;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0532tm tmVar = this.f15944b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        C0532tm tmVar = this.f15944b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }

    public final void setImageResource(int i) {
        C0532tm tmVar = this.f15944b;
        if (tmVar != null) {
            tmVar.mo10151a(i);
        }
    }

    public final void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C0532tm tmVar = this.f15944b;
        if (tmVar != null) {
            tmVar.mo10154b();
        }
    }
}
