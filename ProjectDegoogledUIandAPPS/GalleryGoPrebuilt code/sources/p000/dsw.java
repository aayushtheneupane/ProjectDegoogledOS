package p000;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.VideoView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.photo.PhotoView;
import com.google.android.apps.photosgo.oneup.video.OneUpVideoView;
import com.google.android.apps.photosgo.oneup.video.VideoControlsView;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: dsw */
/* compiled from: PG */
public final class dsw implements dqw, dlr {

    /* renamed from: a */
    public final VideoControlsView f7295a;

    /* renamed from: b */
    public final dtl f7296b;

    /* renamed from: c */
    public final dql f7297c;

    /* renamed from: d */
    public boolean f7298d = false;

    /* renamed from: e */
    public Optional f7299e = Optional.empty();

    /* renamed from: f */
    public View f7300f;

    /* renamed from: g */
    private final View f7301g;

    /* renamed from: h */
    private final VideoView f7302h;

    /* renamed from: i */
    private final PhotoView f7303i;

    /* renamed from: j */
    private final View f7304j;

    /* renamed from: k */
    private final hlz f7305k;

    /* renamed from: l */
    private final ism f7306l = new dsv(this);

    /* renamed from: m */
    private int f7307m = 1;

    public dsw(OneUpVideoView oneUpVideoView, hbl hbl, dtm dtm, hdt hdt, hlz hlz, dqx dqx, dql dql) {
        this.f7301g = oneUpVideoView;
        this.f7305k = hlz;
        this.f7297c = dql;
        LayoutInflater.from(hbl).inflate(R.layout.oneup_video_view_contents, oneUpVideoView, true);
        oneUpVideoView.setOnClickListener(new dss(this));
        PhotoView photoView = (PhotoView) oneUpVideoView.findViewById(R.id.video_view_placeholder);
        this.f7303i = photoView;
        photoView.mo3404a(this.f7306l);
        this.f7303i.mo3405a(false);
        VideoView videoView = (VideoView) oneUpVideoView.findViewById(R.id.video_view);
        this.f7302h = videoView;
        videoView.setOnClickListener(new dst(this));
        this.f7302h.setOnErrorListener(new dsu(this));
        this.f7295a = (VideoControlsView) oneUpVideoView.findViewById(R.id.video_controls_view);
        this.f7304j = oneUpVideoView.findViewById(R.id.oneup_bottom_gradient);
        dqx.mo4354a((dqw) this);
        dqx.mo4356b((dqw) this);
        this.f7296b = dtm.mo4423a(this.f7302h, this, this.f7303i, hdt);
        dtr a = this.f7295a.mo2635n();
        dtl dtl = this.f7296b;
        a.f7358b.setOnClickListener(a.f7357a.mo7575a((View.OnClickListener) new dtn(dtl), "Clicked Play"));
        a.f7359c.setOnClickListener(a.f7357a.mo7575a((View.OnClickListener) new dto(dtl), "Clicked Pause"));
        a.f7361e.setOnSeekBarChangeListener(new hly(a.f7357a, new dtq(dtl), "Video Progress Bar"));
        dtl.mo4405a((dtj) a);
        a.mo3045b();
    }

    /* renamed from: a */
    public final boolean mo4224a() {
        return true;
    }

    /* renamed from: e */
    public final void mo4228e() {
    }

    /* renamed from: b */
    public final void mo4225b() {
        if (this.f7296b.mo4413h()) {
            dtl dtl = this.f7296b;
            dtl.mo4403a(dtl.mo4421p());
        }
    }

    /* renamed from: c */
    public final void mo4226c() {
        if (this.f7296b.mo4415j()) {
            this.f7296b.mo4416k();
        }
    }

    /* renamed from: k */
    public final /* bridge */ /* synthetic */ List mo4262k() {
        if (!this.f7298d) {
            return hso.m12034a((Object) this.f7295a, (Object) this.f7304j);
        }
        return hso.m12033a((Object) this.f7304j);
    }

    /* renamed from: a */
    public final void mo4222a(Bundle bundle) {
        if (bundle.containsKey("elapsed_ms")) {
            this.f7296b.mo4411f();
            this.f7296b.mo4403a(bundle.getInt("elapsed_ms"));
        }
    }

    /* renamed from: d */
    public final Bundle mo4227d() {
        Bundle bundle = new Bundle();
        bundle.putInt("elapsed_ms", this.f7296b.mo4421p());
        return bundle;
    }

    /* renamed from: a */
    public final void mo4223a(boolean z) {
        if (z && this.f7296b.mo4415j()) {
            this.f7296b.mo4416k();
        }
    }

    /* renamed from: a */
    public final void mo4221a(int i) {
        if (i != this.f7307m) {
            this.f7307m = i;
            int i2 = i - 1;
            if (i == 0) {
                throw null;
            } else if (i2 != 0) {
                if (i2 == 2 && !this.f7296b.mo4413h()) {
                    this.f7296b.mo4410e();
                    this.f7296b.mo4414i();
                    this.f7302h.requestFocus();
                    this.f7297c.mo4342a(true);
                }
            } else if (this.f7296b.mo4413h()) {
                dtl dtl = this.f7296b;
                if (dtl.mo4407a(dtk.PLAY, dtk.PAUSE)) {
                    dtl.mo4417l();
                    dtl.f7328c = dtk.SETUP;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final void mo4395g() {
        if (this.f7300f == null) {
            this.f7300f = ((ViewStub) this.f7301g.findViewById(R.id.video_error_view_stub)).inflate();
        }
        this.f7300f.setVisibility(0);
        this.f7295a.setVisibility(8);
        this.f7298d = true;
    }

    /* renamed from: f */
    public final void mo4394f() {
        hlp a = this.f7305k.mo7577a("onOneUpViewPhotoTap");
        try {
            this.f7297c.mo4341a();
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
