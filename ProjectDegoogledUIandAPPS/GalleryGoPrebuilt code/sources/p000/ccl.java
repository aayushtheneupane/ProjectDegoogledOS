package p000;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView;

/* renamed from: ccl */
/* compiled from: PG */
public final class ccl extends ViewOutlineProvider {

    /* renamed from: a */
    private final /* synthetic */ TrimHandleView f4060a;

    public ccl(TrimHandleView trimHandleView) {
        this.f4060a = trimHandleView;
    }

    public final void getOutline(View view, Outline outline) {
        Outline outline2 = outline;
        outline2.setRoundRect(0, 0, this.f4060a.getWidth(), this.f4060a.getHeight(), this.f4060a.f4845a.getStrokeWidth() / 2.0f);
    }
}
