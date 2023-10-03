package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.VideoView;
import com.google.android.apps.photosgo.environment.BuildType;
import com.google.android.apps.photosgo.oneup.photo.PhotoView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import p003j$.util.Optional;

/* renamed from: dtl */
/* compiled from: PG */
public final class dtl implements C0438q {

    /* renamed from: a */
    public int f7326a = -1;

    /* renamed from: b */
    public boolean f7327b = false;

    /* renamed from: c */
    public dtk f7328c = dtk.UNINITIALIZED;

    /* renamed from: d */
    private final Context f7329d;

    /* renamed from: e */
    private final AudioManager f7330e;

    /* renamed from: f */
    private final cny f7331f;

    /* renamed from: g */
    private final Optional f7332g;

    /* renamed from: h */
    private final VideoView f7333h;

    /* renamed from: i */
    private final iel f7334i;

    /* renamed from: j */
    private final exm f7335j;

    /* renamed from: k */
    private final BuildType f7336k;

    /* renamed from: l */
    private final Set f7337l = new HashSet();

    /* renamed from: m */
    private final bee f7338m;

    /* renamed from: n */
    private final AudioFocusRequest f7339n = new AudioFocusRequest.Builder(2).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build()).setOnAudioFocusChangeListener(new dtg(this)).build();

    /* renamed from: o */
    private final BroadcastReceiver f7340o = new dth(this);

    /* renamed from: p */
    private Optional f7341p = Optional.empty();

    /* renamed from: q */
    private Optional f7342q = Optional.empty();

    /* renamed from: r */
    private ieh f7343r = null;

    /* renamed from: s */
    private int f7344s = -1;

    /* renamed from: t */
    private boolean f7345t;

    /* renamed from: u */
    private boolean f7346u;

    public dtl(VideoView videoView, dsw dsw, PhotoView photoView, hdt hdt, Context context, C0147fh fhVar, cny cny, iel iel, exm exm, BuildType buildType) {
        this.f7329d = context;
        this.f7341p = Optional.ofNullable(hdt);
        this.f7331f = cny;
        this.f7333h = videoView;
        this.f7332g = Optional.ofNullable(photoView);
        this.f7334i = iel;
        this.f7335j = exm;
        this.f7336k = buildType;
        this.f7330e = (AudioManager) context.getSystemService("audio");
        this.f7338m = new dti(dsw);
        videoView.setAudioFocusRequest(0);
        videoView.setOnPreparedListener(new dtb(this));
        videoView.setOnCompletionListener(new dtc(this));
        fhVar.mo5ad().mo64a(this);
    }

    /* renamed from: a */
    public final void mo2556a() {
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
    }

    /* renamed from: d */
    public final boolean mo4409d() {
        return this.f7328c != dtk.UNINITIALIZED;
    }

    /* renamed from: h */
    public final boolean mo4413h() {
        return this.f7328c == dtk.PLAY || this.f7328c == dtk.PAUSE;
    }

    /* renamed from: j */
    public final boolean mo4415j() {
        return this.f7328c == dtk.PLAY;
    }

    /* renamed from: a */
    public final void mo4405a(dtj dtj) {
        this.f7337l.add(dtj);
    }

    /* renamed from: r */
    private final void m6621r() {
        ieh ieh = this.f7343r;
        if (ieh != null) {
            ieh.cancel(false);
            this.f7343r = null;
        }
    }

    /* renamed from: a */
    public final boolean mo4407a(dtk... dtkArr) {
        int length = dtkArr.length;
        int i = 0;
        boolean z = false;
        while (true) {
            boolean z2 = true;
            if (i >= length) {
                break;
            }
            if (this.f7328c != dtkArr[i]) {
                z2 = false;
            }
            z |= z2;
            i++;
        }
        if (!z) {
            BuildType buildType = BuildType.DEV;
            dtk dtk = dtk.UNINITIALIZED;
            int ordinal = this.f7336k.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                ife.m12879b(false, "Unexpected state in VideoController: actual = [%s], expected = %s", this.f7328c.toString(), Arrays.toString(dtkArr));
                return false;
            } else if (ordinal == 2 || ordinal == 3) {
                cwn.m5515b((Throwable) new Exception(), "Unexpected state in VideoController: actual = [%s], expected = %s", this.f7328c.toString(), Arrays.toString(dtkArr));
                return false;
            }
        }
        return z;
    }

    /* renamed from: p */
    public final int mo4421p() {
        int currentPosition = this.f7333h.getCurrentPosition();
        int i = this.f7326a;
        if (currentPosition < i) {
            return i;
        }
        this.f7326a = currentPosition;
        return currentPosition;
    }

    /* renamed from: q */
    public final int mo4422q() {
        return (!(this.f7328c == dtk.PLAY || this.f7328c == dtk.PAUSE) || this.f7333h.getDuration() == -1) ? this.f7344s : this.f7333h.getDuration();
    }

    /* renamed from: a */
    public final void mo4404a(cxi cxi) {
        if (mo4407a(dtk.UNINITIALIZED)) {
            this.f7342q = Optional.m16285of(Uri.parse(cxi.f5910b));
            if (this.f7332g.isPresent() && this.f7341p.isPresent()) {
                this.f7333h.setVisibility(8);
                ((apj) ((hdt) this.f7341p.get()).mo7308a((Uri) this.f7342q.get()).mo1854a((aqu) new bfa(Long.valueOf(cxi.f5918j)))).mo1426b(this.f7331f.mo3299c()).mo1418a(this.f7338m).mo1422a((ImageView) this.f7332g.get());
            }
            this.f7328c = dtk.SETUP;
            mo4419n();
            this.f7344s = (int) cxi.f5915g;
            mo4420o();
        }
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
        zVar.mo5ad().mo65b(this);
        mo4418m();
    }

    /* renamed from: b */
    public final void mo2558b() {
        this.f7329d.unregisterReceiver(this.f7340o);
    }

    /* renamed from: c */
    public final void mo2560c() {
        this.f7329d.registerReceiver(this.f7340o, new IntentFilter("android.intent.action.HEADSET_PLUG"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final void mo4412g() {
        this.f7332g.ifPresent(dte.f7315a);
        mo4420o();
    }

    /* renamed from: k */
    public final void mo4416k() {
        if (mo4407a(dtk.PLAY, dtk.PAUSE)) {
            this.f7328c = dtk.PAUSE;
            this.f7333h.pause();
            this.f7330e.abandonAudioFocusRequest(this.f7339n);
            mo4419n();
            m6621r();
        }
    }

    /* renamed from: i */
    public final void mo4414i() {
        if (mo4407a(dtk.PLAY, dtk.PAUSE)) {
            if (this.f7332g.isPresent()) {
                this.f7333h.setVisibility(0);
            }
            this.f7330e.requestAudioFocus(this.f7339n);
            this.f7328c = dtk.PLAY;
            this.f7333h.start();
            if (this.f7327b) {
                this.f7326a = -1;
            }
            this.f7327b = false;
            if (this.f7343r == null) {
                this.f7343r = ife.m12821a((Runnable) new dtf(this), 16, TimeUnit.MILLISECONDS, this.f7335j, this.f7334i);
            }
            mo4419n();
        }
    }

    /* renamed from: b */
    public final void mo4408b(dtj dtj) {
        this.f7337l.remove(dtj);
    }

    /* renamed from: m */
    public final void mo4418m() {
        if (this.f7332g.isPresent() && this.f7341p.isPresent()) {
            ((hdt) this.f7341p.get()).mo7311a((ImageView) this.f7332g.get());
        }
        mo4417l();
        this.f7328c = dtk.UNINITIALIZED;
    }

    /* renamed from: a */
    public final void mo4403a(int i) {
        if (mo4407a(dtk.PLAY, dtk.PAUSE)) {
            this.f7333h.seekTo(i);
            this.f7326a = i;
            mo4412g();
            this.f7327b = false;
            mo4420o();
        }
    }

    /* renamed from: a */
    public final void mo4406a(boolean z) {
        if (mo4407a(dtk.PLAY, dtk.PAUSE)) {
            this.f7345t = z;
            if (!z) {
                if (this.f7346u) {
                    mo4414i();
                }
                this.f7346u = false;
            } else if (this.f7328c == dtk.PLAY) {
                this.f7346u = true;
                mo4416k();
            }
        }
    }

    /* renamed from: e */
    public final void mo4410e() {
        if (mo4407a(dtk.SETUP)) {
            if (this.f7332g.isPresent()) {
                this.f7333h.setVisibility(0);
            }
            this.f7333h.setOnInfoListener(new dtd(this));
            this.f7333h.setVideoURI((Uri) this.f7342q.get());
            this.f7328c = dtk.PAUSE;
            this.f7326a = -1;
            mo4420o();
            mo4419n();
        }
    }

    /* renamed from: f */
    public final void mo4411f() {
        if (!mo4413h()) {
            mo4410e();
        }
    }

    /* renamed from: l */
    public final void mo4417l() {
        m6621r();
        if (this.f7328c == dtk.PLAY || this.f7328c == dtk.PAUSE) {
            mo4416k();
            this.f7333h.stopPlayback();
            this.f7333h.seekTo(1);
            this.f7326a = -1;
            this.f7327b = false;
            mo4420o();
            this.f7333h.setVideoURI((Uri) null);
        }
        if (this.f7332g.isPresent()) {
            this.f7333h.setVisibility(8);
            ((PhotoView) this.f7332g.get()).setVisibility(0);
        }
    }

    /* renamed from: o */
    public final void mo4420o() {
        for (dtj a : this.f7337l) {
            a.mo3044a(mo4421p(), mo4422q(), this.f7345t);
        }
    }

    /* renamed from: n */
    public final void mo4419n() {
        for (dtj dtj : this.f7337l) {
            BuildType buildType = BuildType.DEV;
            dtk dtk = dtk.UNINITIALIZED;
            int ordinal = this.f7328c.ordinal();
            if (ordinal == 1 || ordinal == 2) {
                dtj.mo3045b();
            } else if (ordinal == 3) {
                dtj.mo3043a();
            }
        }
    }
}
