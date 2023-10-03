package p000;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.apps.photosgo.R;
import java.util.List;

/* renamed from: bxf */
/* compiled from: PG */
public final class bxf extends C0372no {

    /* renamed from: g */
    public final /* synthetic */ bxg f3819g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ bxf(bxg bxg, View view) {
        super(view);
        this.f3819g = bxg;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2873a() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo2872a(List list) {
        if (!this.f3819g.mo2874a().isEmpty()) {
            boolean equals = ((byc) this.f3819g.f3822c).f3888c.f4837b.equals(byh.f3903a);
            list.add(3);
            if (equals) {
                list.add(2);
            }
            list.add(6);
            if (equals) {
                list.add(1);
            }
            list.add(15);
            if (equals) {
                list.add(4);
            }
            list.add(9);
            if (equals) {
                list.add(8);
            }
            list.add(12);
        }
    }

    /* renamed from: b */
    private final String m3723b() {
        bxg bxg = this.f3819g;
        RectF userFriendlyCropCoordinates = bxg.f3823d.getUserFriendlyCropCoordinates(((byc) bxg.f3822c).f3888c);
        if (userFriendlyCropCoordinates == null) {
            return "";
        }
        return this.f3819g.f3821b.getString(R.string.editor_crop_a11y_handle_whole_area, new Object[]{Integer.valueOf(Math.round(userFriendlyCropCoordinates.left * 100.0f)), Integer.valueOf(Math.round(userFriendlyCropCoordinates.top * 100.0f)), Integer.valueOf(Math.round(userFriendlyCropCoordinates.right * 100.0f)), Integer.valueOf(Math.round(userFriendlyCropCoordinates.bottom * 100.0f))});
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo2871a(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setContentDescription(m3723b());
        accessibilityEvent.setClassName(bxl.class.getName());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo2870a(int i, C0354mx mxVar) {
        RectF rectF = new RectF();
        RectF a = this.f3819g.mo2874a();
        if (i == 1) {
            rectF.set(a.left - ((float) this.f3819g.f3826g), a.top, a.left + ((float) this.f3819g.f3826g), a.bottom);
        } else if (i == 2) {
            rectF.set(a.left, a.top - ((float) this.f3819g.f3826g), a.right, a.top + ((float) this.f3819g.f3826g));
        } else if (i == 3) {
            rectF.set(a.left - ((float) this.f3819g.f3826g), a.top - ((float) this.f3819g.f3826g), a.left + ((float) this.f3819g.f3826g), a.top + ((float) this.f3819g.f3826g));
        } else if (i == 4) {
            rectF.set(a.right - ((float) this.f3819g.f3826g), a.top, a.right + ((float) this.f3819g.f3826g), a.bottom);
        } else if (i == 6) {
            rectF.set(a.right - ((float) this.f3819g.f3826g), a.top - ((float) this.f3819g.f3826g), a.right + ((float) this.f3819g.f3826g), a.top + ((float) this.f3819g.f3826g));
        } else if (i == 12) {
            rectF.set(a.right - ((float) this.f3819g.f3826g), a.bottom - ((float) this.f3819g.f3826g), a.right + ((float) this.f3819g.f3826g), a.bottom + ((float) this.f3819g.f3826g));
        } else if (i == 15) {
            rectF.set(a.left, a.top, a.right, a.bottom);
        } else if (i == 8) {
            rectF.set(a.left, a.bottom - ((float) this.f3819g.f3826g), a.right, a.bottom + ((float) this.f3819g.f3826g));
        } else if (i == 9) {
            rectF.set(a.left - ((float) this.f3819g.f3826g), a.bottom - ((float) this.f3819g.f3826g), a.left + ((float) this.f3819g.f3826g), a.bottom + ((float) this.f3819g.f3826g));
        } else {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Unrecognized viewId: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        Rect rect = new Rect();
        rectF.round(rect);
        mxVar.mo9427b(rect);
        mxVar.f15257a.setContentDescription(m3723b());
        mxVar.mo9420a(16);
    }
}
