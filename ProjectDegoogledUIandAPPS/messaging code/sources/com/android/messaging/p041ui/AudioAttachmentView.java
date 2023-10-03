package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.messaging.C0967f;
import com.android.messaging.C0970i;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.mediapicker.PausableChronometer;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.AudioAttachmentView */
public class AudioAttachmentView extends LinearLayout {

    /* renamed from: bh */
    private AudioAttachmentPlayPauseButton f1580bh;
    /* access modifiers changed from: private */

    /* renamed from: ch */
    public MediaPlayer f1581ch;

    /* renamed from: dh */
    private Uri f1582dh;

    /* renamed from: eh */
    private final Path f1583eh;

    /* renamed from: fh */
    private int f1584fh;

    /* renamed from: gh */
    private int f1585gh;

    /* renamed from: hh */
    private boolean f1586hh;
    /* access modifiers changed from: private */

    /* renamed from: ih */
    public boolean f1587ih;

    /* renamed from: jh */
    private boolean f1588jh;
    /* access modifiers changed from: private */

    /* renamed from: kh */
    public boolean f1589kh;
    /* access modifiers changed from: private */

    /* renamed from: lh */
    public boolean f1590lh;
    /* access modifiers changed from: private */
    public PausableChronometer mChronometer;
    private final int mCornerRadius;
    private final int mMode;
    /* access modifiers changed from: private */
    public AudioPlaybackProgressBar mProgressBar;

    /* renamed from: ob */
    private int f1591ob;

    public AudioAttachmentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0970i.AudioAttachmentView);
        boolean z = false;
        this.mMode = obtainStyledAttributes.getInt(0, 0);
        LayoutInflater.from(getContext()).inflate(R.layout.audio_attachment_view, this, true);
        obtainStyledAttributes.recycle();
        setWillNotDraw(this.mMode != 2 ? true : z);
        this.f1583eh = new Path();
        this.mCornerRadius = context.getResources().getDimensionPixelSize(R.dimen.conversation_list_image_preview_corner_radius);
        setContentDescription(context.getString(R.string.audio_attachment_content_description));
    }

    /* renamed from: Vm */
    private void m2478Vm() {
        MediaPlayer mediaPlayer = this.f1581ch;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.f1581ch = null;
            this.f1589kh = false;
            this.f1587ih = false;
            this.f1590lh = false;
            this.mChronometer.reset();
            this.mProgressBar.reset();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: Wm */
    public void m2479Wm() {
        C1424b.m3594t(this.f1582dh);
        if (this.f1581ch == null) {
            C1424b.m3592ia(!this.f1589kh);
            this.f1581ch = new MediaPlayer();
            try {
                this.f1581ch.setAudioStreamType(3);
                this.f1581ch.setDataSource(C0967f.get().getApplicationContext(), this.f1582dh);
                this.f1581ch.setOnCompletionListener(new C1366n(this));
                this.f1581ch.setOnPreparedListener(new C1368o(this));
                this.f1581ch.setOnErrorListener(new C1370p(this));
                this.f1581ch.prepareAsync();
            } catch (Exception e) {
                m2483a(0, 0, e);
                m2478Vm();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: Xm */
    public void m2480Xm() {
        MediaPlayer mediaPlayer = this.f1581ch;
        boolean z = mediaPlayer != null && mediaPlayer.isPlaying();
        m2496ta(z);
        if (this.f1587ih || z) {
            this.f1580bh.setDisplayedChild(1);
        } else {
            this.f1580bh.setDisplayedChild(0);
        }
    }

    /* renamed from: Ym */
    private void m2481Ym() {
        if (this.mMode != 2) {
            if (this.f1586hh) {
                this.mChronometer.setTextColor(getResources().getColor(R.color.message_text_color_incoming));
            } else {
                this.mChronometer.setTextColor(getResources().getColor(R.color.message_text_color_outgoing));
            }
            this.mProgressBar.mo6882m(this.f1586hh);
            this.f1580bh.mo6875m(this.f1586hh);
            m2480Xm();
        }
    }

    /* renamed from: e */
    static /* synthetic */ void m2492e(AudioAttachmentView audioAttachmentView) {
        C1424b.m3594t(audioAttachmentView.f1581ch);
        if (audioAttachmentView.f1590lh) {
            audioAttachmentView.f1581ch.seekTo(0);
            audioAttachmentView.mChronometer.restart();
            audioAttachmentView.mProgressBar.restart();
            audioAttachmentView.f1590lh = false;
        } else {
            audioAttachmentView.mChronometer.resume();
            audioAttachmentView.mProgressBar.resume();
        }
        audioAttachmentView.f1581ch.start();
    }

    /* access modifiers changed from: private */
    /* renamed from: ta */
    public void m2496ta(boolean z) {
        if (this.mChronometer.getVisibility() == 8) {
            C1424b.equals(2, this.mMode);
            return;
        }
        int i = 0;
        if (this.f1588jh) {
            PausableChronometer pausableChronometer = this.mChronometer;
            if (!z) {
                i = 4;
            }
            pausableChronometer.setVisibility(i);
            return;
        }
        this.mChronometer.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m2478Vm();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mMode == 2) {
            int width = getWidth();
            int height = getHeight();
            if (!(this.f1584fh == width && this.f1585gh == height)) {
                RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
                this.f1583eh.reset();
                Path path = this.f1583eh;
                int i = this.mCornerRadius;
                path.addRoundRect(rectF, (float) i, (float) i, Path.Direction.CW);
                this.f1584fh = width;
                this.f1585gh = height;
            }
            canvas.clipPath(this.f1583eh);
            super.onDraw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1580bh = (AudioAttachmentPlayPauseButton) findViewById(R.id.play_pause_button);
        this.mChronometer = (PausableChronometer) findViewById(R.id.timer);
        this.mProgressBar = (AudioPlaybackProgressBar) findViewById(R.id.progress);
        this.f1580bh.setOnClickListener(new C1271m(this));
        m2480Xm();
        int i = this.mMode;
        if (i == 0) {
            setOrientation(0);
            this.mProgressBar.setVisibility(0);
        } else if (i == 1) {
            setOrientation(1);
            this.mProgressBar.setVisibility(8);
            ((ViewGroup.MarginLayoutParams) this.f1580bh.getLayoutParams()).setMargins(0, 0, 0, 0);
            ((ViewGroup.MarginLayoutParams) this.mChronometer.getLayoutParams()).setMargins(0, 0, 0, 0);
        } else if (i != 2) {
            C1424b.fail("Unsupported mode for AudioAttachmentView!");
        } else {
            setOrientation(1);
            this.mProgressBar.setVisibility(8);
            this.mChronometer.setVisibility(8);
            ((ViewGroup.MarginLayoutParams) this.f1580bh.getLayoutParams()).setMargins(0, 0, 0, 0);
            ((ImageView) findViewById(R.id.play_button)).setImageDrawable(getResources().getDrawable(R.drawable.ic_preview_play));
            ((ImageView) findViewById(R.id.pause_button)).setImageDrawable(getResources().getDrawable(R.drawable.ic_preview_pause));
        }
    }

    /* renamed from: a */
    public void mo6878a(MessagePartData messagePartData, boolean z, boolean z2) {
        Uri uri;
        C1424b.m3592ia(messagePartData == null || C1481w.m3831za(messagePartData.getContentType()));
        if (messagePartData == null) {
            uri = null;
        } else {
            uri = messagePartData.getContentUri();
        }
        mo6877a(uri, z, z2);
    }

    /* renamed from: a */
    public void mo6877a(Uri uri, boolean z, boolean z2) {
        Uri uri2 = this.f1582dh;
        String str = "";
        String uri3 = uri2 == null ? str : uri2.toString();
        if (uri != null) {
            str = uri.toString();
        }
        int Ea = C1037D.get().mo6936Ea();
        boolean z3 = true;
        boolean z4 = z || z2;
        boolean z5 = (this.f1591ob == Ea && this.f1586hh == z4) ? false : true;
        this.f1586hh = z4;
        this.f1591ob = Ea;
        if (!z || C1464na.m3760_j()) {
            z3 = false;
        }
        this.f1588jh = z3;
        if (!TextUtils.equals(uri3, str)) {
            this.f1582dh = uri;
            m2478Vm();
            m2481Ym();
            m2496ta(false);
            if (this.f1582dh != null && !this.f1588jh) {
                m2479Wm();
            }
        } else if (z5) {
            m2481Ym();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2483a(int i, int i2, Exception exc) {
        if (exc == null) {
            C1430e.m3622e("MessagingApp", "audio replay failed, what=" + i + ", extra=" + i2);
        } else {
            C1430e.m3622e("MessagingApp", "audio replay failed, exception=" + exc);
        }
        C1486ya.m3848Pa(R.string.audio_recording_replay_failed);
        m2478Vm();
    }
}
