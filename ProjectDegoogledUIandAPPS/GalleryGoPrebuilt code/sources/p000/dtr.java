package p000;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.video.VideoControlsView;
import java.util.concurrent.TimeUnit;

/* renamed from: dtr */
/* compiled from: PG */
public final class dtr implements dtj {

    /* renamed from: a */
    public final hlz f7357a;

    /* renamed from: b */
    public final ImageView f7358b;

    /* renamed from: c */
    public final ImageView f7359c;

    /* renamed from: d */
    public final ViewGroup f7360d;

    /* renamed from: e */
    public final SeekBar f7361e;

    /* renamed from: f */
    public final int f7362f;

    /* renamed from: g */
    private final dql f7363g;

    /* renamed from: h */
    private final TextView f7364h;

    /* renamed from: i */
    private final TextView f7365i;

    public dtr(VideoControlsView videoControlsView, hbl hbl, hlz hlz, dql dql) {
        this.f7357a = hlz;
        this.f7363g = dql;
        LayoutInflater.from(hbl).inflate(R.layout.oneup_video_controls_contents, videoControlsView, true);
        this.f7358b = (ImageView) videoControlsView.findViewById(R.id.play_button);
        this.f7359c = (ImageView) videoControlsView.findViewById(R.id.pause_button);
        this.f7360d = (ViewGroup) videoControlsView.findViewById(R.id.progress_controls);
        this.f7364h = (TextView) videoControlsView.findViewById(R.id.time_current);
        this.f7365i = (TextView) videoControlsView.findViewById(R.id.time_total);
        this.f7361e = (SeekBar) videoControlsView.findViewById(R.id.video_progress);
        this.f7362f = videoControlsView.getResources().getDimensionPixelOffset(R.dimen.design_bottom_navigation_height);
        this.f7361e.setOnTouchListener(this.f7357a.mo7576a(dtp.f7355a, "touch seekbar"));
    }

    /* renamed from: b */
    public final void mo3045b() {
        this.f7358b.setVisibility(0);
        this.f7359c.setVisibility(8);
        this.f7363g.mo4342a(false);
    }

    /* renamed from: a */
    public final void mo3043a() {
        this.f7358b.setVisibility(8);
        this.f7359c.setVisibility(0);
        this.f7363g.mo4342a(true);
    }

    /* renamed from: a */
    public final void mo3044a(int i, int i2, boolean z) {
        this.f7364h.setText(DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds((long) i)));
        this.f7365i.setText(DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds((long) i2)));
        this.f7361e.setMax(i2);
        if (!z || i != this.f7361e.getProgress()) {
            this.f7361e.setProgress(i);
        }
    }
}
