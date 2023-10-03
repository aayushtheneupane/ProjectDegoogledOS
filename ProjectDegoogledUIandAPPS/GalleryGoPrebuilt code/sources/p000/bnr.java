package p000;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: bnr */
/* compiled from: PG */
public final class bnr extends C0607wg {

    /* renamed from: x */
    private static final hso f3219x = hso.m12039a(Integer.valueOf(R.string.category_people), Integer.valueOf(R.string.category_animals), Integer.valueOf(R.string.category_documents), Integer.valueOf(R.string.category_nature), Integer.valueOf(R.string.category_screenshots), Integer.valueOf(R.string.category_selfies), Integer.valueOf(R.string.category_videos), Integer.valueOf(R.string.category_movies));

    /* renamed from: a */
    public boolean f3220a = true;

    /* renamed from: b */
    private final Context f3221b;

    /* renamed from: w */
    private final int f3222w;

    /* renamed from: i */
    public final boolean mo2620i() {
        return this.f3220a;
    }

    public bnr(Context context) {
        super(0);
        this.f3221b = context;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.category_min_width);
        TextPaint paint = ((TextView) LayoutInflater.from(this.f3221b).inflate(R.layout.category_name_textview, (ViewGroup) null, false)).getPaint();
        Rect rect = new Rect();
        hvs i = f3219x.listIterator();
        int i2 = 0;
        while (i.hasNext()) {
            String string = this.f3221b.getString(((Integer) i.next()).intValue());
            paint.getTextBounds(string, 0, string.length(), rect);
            i2 = Math.max(i2, rect.width());
        }
        int dimensionPixelSize2 = this.f3221b.getResources().getDimensionPixelSize(R.dimen.category_padding);
        this.f3222w = Math.max(dimensionPixelSize, i2 + dimensionPixelSize2 + dimensionPixelSize2);
    }

    /* renamed from: a */
    public final C0648xu mo2617a() {
        return m3257b(super.mo2617a());
    }

    /* renamed from: a */
    public final C0648xu mo2618a(Context context, AttributeSet attributeSet) {
        return m3257b(super.mo2618a(context, attributeSet));
    }

    /* renamed from: a */
    public final C0648xu mo2619a(ViewGroup.LayoutParams layoutParams) {
        return m3257b(super.mo2619a(layoutParams));
    }

    /* renamed from: b */
    private final C0648xu m3257b(C0648xu xuVar) {
        xuVar.width = this.f3222w;
        return xuVar;
    }
}
