package p000;

import android.content.res.Resources;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.PlayheadView;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.TimelineGreyOverlayView;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoThumbnailView;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView;
import p003j$.util.Optional;

/* renamed from: cef */
/* compiled from: PG */
public final class cef {

    /* renamed from: a */
    public final VideoTrimView f4166a;

    /* renamed from: b */
    public final View f4167b;

    /* renamed from: c */
    public final FrameLayout f4168c;

    /* renamed from: d */
    public final VideoThumbnailView f4169d;

    /* renamed from: e */
    public final PlayheadView f4170e;

    /* renamed from: f */
    public final TrimHandleView f4171f;

    /* renamed from: g */
    public final TrimHandleView f4172g;

    /* renamed from: h */
    public final dtj f4173h;

    /* renamed from: i */
    public final cea f4174i;

    /* renamed from: j */
    public final int f4175j;

    /* renamed from: k */
    public final int f4176k;

    /* renamed from: l */
    public cxi f4177l = cxi.f5908x;

    /* renamed from: m */
    public dtl f4178m;

    /* renamed from: n */
    public Optional f4179n = Optional.empty();

    /* renamed from: o */
    public Optional f4180o = Optional.empty();

    /* renamed from: p */
    public cee f4181p = null;

    /* renamed from: q */
    public final RectF f4182q = new RectF();

    /* renamed from: r */
    public final RectF f4183r = new RectF();

    /* renamed from: s */
    public final RectF f4184s = new RectF();

    /* renamed from: t */
    public final RectF f4185t = new RectF();

    /* renamed from: u */
    public final RectF f4186u = new RectF();

    /* renamed from: v */
    private final TimelineGreyOverlayView f4187v;

    /* renamed from: w */
    private final int f4188w;

    public cef(VideoTrimView videoTrimView, hbl hbl) {
        this.f4166a = videoTrimView;
        LayoutInflater.from(hbl).inflate(R.layout.video_trim_view_contents, videoTrimView, true);
        this.f4170e = (PlayheadView) videoTrimView.findViewById(R.id.playhead);
        int i = R.id.left_trim_handle;
        this.f4171f = (TrimHandleView) videoTrimView.findViewById(R.id.left_trim_handle);
        this.f4172g = (TrimHandleView) videoTrimView.findViewById(R.id.right_trim_handle);
        this.f4167b = videoTrimView.findViewById(R.id.inner_view);
        this.f4168c = (FrameLayout) videoTrimView.findViewById(R.id.video_thumbnail_view_container);
        this.f4169d = (VideoThumbnailView) videoTrimView.findViewById(R.id.video_thumbnail_view);
        this.f4187v = (TimelineGreyOverlayView) videoTrimView.findViewById(R.id.greyoverlay);
        this.f4173h = new ceb(this);
        Resources resources = hbl.getResources();
        this.f4175j = resources.getDimensionPixelSize(R.dimen.photosgo_videotrimming_trimview_handle_halfsize);
        this.f4176k = resources.getDimensionPixelSize(R.dimen.photosgo_videotrimming_trimview_view_playhead_width);
        this.f4188w = resources.getDimensionPixelSize(R.dimen.photosgo_videotrimming_tap_target_width);
        videoTrimView.setNextFocusForwardId(!mo3072b() ? R.id.left_trim_handle : R.id.right_trim_handle);
        this.f4170e.setNextFocusForwardId(!mo3072b() ? R.id.right_trim_handle : i);
        if (mo3072b()) {
            this.f4172g.setNextFocusForwardId(R.id.playhead);
        } else {
            this.f4171f.setNextFocusForwardId(R.id.playhead);
        }
        cea cea = new cea(this);
        this.f4174i = cea;
        this.f4171f.setAccessibilityDelegate(cea);
        this.f4172g.setAccessibilityDelegate(this.f4174i);
        this.f4170e.setAccessibilityDelegate(this.f4174i);
    }

    /* renamed from: a */
    public final void mo3066a(View view, float f, float f2, float f3) {
        if (view == this.f4171f) {
            Float valueOf = Float.valueOf(f);
            Object[] objArr = {valueOf, Float.valueOf(f2), Float.valueOf(f3)};
            if (((float) this.f4171f.getWidth()) + f >= this.f4172g.getX()) {
                new Object[1][0] = Float.valueOf(this.f4172g.getX() - ((float) this.f4172g.getWidth()));
                view.setTranslationX(this.f4172g.getX() - ((float) this.f4172g.getWidth()));
            } else if (f >= 0.0f) {
                new Object[1][0] = valueOf;
                view.setTranslationX(f);
            } else {
                view.setTranslationX(0.0f);
            }
            if (this.f4170e.getX() < this.f4171f.getX() + ((float) this.f4175j)) {
                this.f4170e.setTranslationX(this.f4171f.getX());
                new Object[1][0] = Float.valueOf(this.f4171f.getX());
            }
        } else if (view == this.f4172g) {
            Object[] objArr2 = {Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)};
            if (f - ((float) this.f4171f.getWidth()) <= this.f4171f.getX()) {
                new Object[1][0] = Float.valueOf((this.f4171f.getX() + ((float) this.f4171f.getWidth())) - f2);
                view.setTranslationX((this.f4171f.getX() + ((float) this.f4171f.getWidth())) - f2);
            } else if (f <= f2) {
                float f4 = f - f2;
                new Object[1][0] = Float.valueOf(f4);
                view.setTranslationX(f4);
            } else {
                view.setTranslationX(0.0f);
            }
            if (this.f4170e.getX() > this.f4172g.getX() + ((float) this.f4175j)) {
                this.f4170e.setTranslationX(this.f4172g.getX());
                new Object[1][0] = Float.valueOf(this.f4172g.getX());
            }
        } else {
            Object[] objArr3 = {Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)};
            float f5 = f - f3;
            if (f5 > this.f4172g.getX()) {
                new Object[1][0] = Float.valueOf(this.f4172g.getX());
                this.f4170e.setTranslationX(this.f4172g.getX());
            } else if (f5 < this.f4171f.getX()) {
                new Object[1][0] = Float.valueOf(this.f4171f.getX());
                this.f4170e.setTranslationX(this.f4171f.getX());
            } else {
                new Object[1][0] = Float.valueOf(f5);
                this.f4170e.setTranslationX(f5);
            }
        }
        mo3065a();
    }

    /* renamed from: b */
    public final void mo3071b(View view, RectF rectF) {
        mo3067a(view, rectF);
        float width = rectF.width();
        float f = (float) this.f4188w;
        if (width < f) {
            rectF.inset(-(((f - rectF.width()) + 1.0f) / 2.0f), 0.0f);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final int mo3073c() {
        return mo3064a(mo3072b() ? m4169g() : m4168f());
    }

    /* renamed from: a */
    public final int mo3064a(float f) {
        float f2 = (float) this.f4177l.f5915g;
        if (mo3072b()) {
            f = 1.0f - f;
        }
        return Math.round(f2 * f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final int mo3075d() {
        return mo3064a(mo3072b() ? m4168f() : m4169g());
    }

    /* renamed from: f */
    private final float m4168f() {
        return mo3062a((View) this.f4171f);
    }

    /* renamed from: a */
    public final float mo3061a(int i) {
        float f = ((float) i) / ((float) this.f4177l.f5915g);
        return mo3072b() ? 1.0f - f : f;
    }

    /* renamed from: a */
    public final float mo3063a(View view, float f) {
        if (view == this.f4172g) {
            return (view.getTranslationX() / f) + 1.0f;
        }
        return view.getTranslationX() / f;
    }

    /* renamed from: a */
    public final float mo3062a(View view) {
        return view.getX() / ((float) this.f4168c.getWidth());
    }

    /* renamed from: g */
    private final float m4169g() {
        return mo3062a((View) this.f4172g);
    }

    /* renamed from: b */
    public final boolean mo3072b() {
        return C0340mj.m14714f(this.f4166a) == 1;
    }

    /* renamed from: e */
    public final void mo3076e() {
        dtl dtl = this.f4178m;
        if (dtl != null && dtl.mo4415j()) {
            this.f4178m.mo4416k();
        }
    }

    /* renamed from: a */
    public final void mo3067a(View view, RectF rectF) {
        float x = this.f4185t.left + view.getX();
        float y = this.f4185t.top + view.getY();
        rectF.set(x, y, ((float) view.getWidth()) + x, ((float) view.getHeight()) + y);
    }

    /* renamed from: b */
    public final void mo3070b(int i) {
        dtl dtl = this.f4178m;
        if (dtl != null && dtl.mo4413h()) {
            this.f4178m.mo4403a(i);
        }
    }

    /* renamed from: b */
    public final void mo3069b(float f) {
        m4167b((View) this.f4171f, f);
    }

    /* renamed from: b */
    private final void m4167b(View view, float f) {
        view.setX(f * ((float) this.f4168c.getWidth()));
    }

    /* renamed from: c */
    public final void mo3074c(float f) {
        m4167b((View) this.f4172g, f);
    }

    /* renamed from: a */
    public final void mo3068a(boolean z) {
        dtl dtl = this.f4178m;
        if (dtl != null && dtl.mo4409d()) {
            this.f4178m.mo4411f();
            if (this.f4178m.mo4413h()) {
                this.f4178m.mo4406a(z);
            }
        }
    }

    /* renamed from: a */
    public final void mo3065a() {
        TimelineGreyOverlayView timelineGreyOverlayView = this.f4187v;
        int round = Math.round(this.f4169d.getX());
        int round2 = Math.round(this.f4171f.getX());
        timelineGreyOverlayView.f4842a.left = (float) round;
        timelineGreyOverlayView.f4842a.right = (float) round2;
        timelineGreyOverlayView.invalidate();
        TimelineGreyOverlayView timelineGreyOverlayView2 = this.f4187v;
        int round3 = Math.round(this.f4172g.getX());
        int round4 = Math.round(this.f4169d.getX() + ((float) this.f4169d.getWidth()));
        timelineGreyOverlayView2.f4843b.left = (float) round3;
        timelineGreyOverlayView2.f4843b.right = (float) round4;
        timelineGreyOverlayView2.invalidate();
    }
}
