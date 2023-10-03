package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MediaPickerMessagePartData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1448fa;
import com.android.messaging.util.C1452ha;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1480va;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.AudioRecordView */
public class AudioRecordView extends FrameLayout implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {

    /* renamed from: Cg */
    private ImageView f1989Cg;

    /* renamed from: Dg */
    private View f1990Dg;

    /* renamed from: Eg */
    private SoundLevels f1991Eg;

    /* renamed from: Fg */
    private TextView f1992Fg;

    /* renamed from: Gg */
    private PausableChronometer f1993Gg;
    /* access modifiers changed from: private */

    /* renamed from: Hg */
    public C1303ca f1994Hg = new C1303ca();

    /* renamed from: Ig */
    private long f1995Ig;
    /* access modifiers changed from: private */

    /* renamed from: dd */
    public int f1996dd = 1;
    /* access modifiers changed from: private */
    public C1326g mHostInterface;

    /* renamed from: ob */
    private int f1997ob;

    public AudioRecordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Q */
    private void m3190Q(int i, int i2) {
        C1430e.m3622e("MessagingApp", "Error occurred during audio recording what=" + i + ", extra=" + i2);
        C1486ya.m3848Pa(R.string.audio_recording_error);
        setMode(1);
        stopRecording();
    }

    /* access modifiers changed from: private */
    /* renamed from: Sm */
    public void m3191Sm() {
        Uri stopRecording = stopRecording();
        if (stopRecording != null) {
            Rect rect = new Rect();
            this.f1989Cg.getGlobalVisibleRect(rect);
            ((C1300b) this.mHostInterface).f2118Dj.mo7896a((MessagePartData) new MediaPickerMessagePartData(rect, "audio/3gpp", stopRecording, 0, 0), true);
        }
        C1452ha.get().mo8183a(getContext(), R.raw.audio_end, (C1448fa) null);
        setMode(1);
    }

    /* renamed from: Tm */
    private void m3192Tm() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_mp_audio_mic);
        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.audio_record_control_button_background);
        if (m3198qj()) {
            drawable.setColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
            gradientDrawable.setColor(this.f1997ob);
        } else {
            drawable.setColorFilter(this.f1997ob, PorterDuff.Mode.SRC_ATOP);
            gradientDrawable.setColor(-1);
        }
        this.f1989Cg.setImageDrawable(drawable);
        this.f1989Cg.setBackground(gradientDrawable);
    }

    /* renamed from: qj */
    private boolean m3198qj() {
        return this.f1994Hg.mo7777qj() && this.f1996dd == 3;
    }

    /* access modifiers changed from: private */
    public void setMode(int i) {
        if (this.f1996dd != i) {
            this.f1996dd = i;
            int i2 = this.f1996dd;
            if (i2 == 1) {
                this.f1992Fg.setVisibility(0);
                this.f1992Fg.setTypeface((Typeface) null, 0);
                this.f1993Gg.setVisibility(8);
                this.f1991Eg.setEnabled(false);
                this.f1993Gg.stop();
            } else if (i2 != 2) {
                if (i2 == 3 || i2 == 4) {
                    this.f1992Fg.setVisibility(8);
                    this.f1993Gg.setVisibility(0);
                    this.f1991Eg.setEnabled(true);
                    this.f1993Gg.restart();
                } else {
                    C1424b.fail("invalid mode for AudioRecordView!");
                }
            }
            m3192Tm();
        }
    }

    private Uri stopRecording() {
        if (this.f1994Hg.mo7777qj()) {
            return this.f1994Hg.stopRecording();
        }
        return null;
    }

    /* renamed from: Jb */
    public boolean mo7655Jb() {
        return this.f1996dd != 1;
    }

    /* renamed from: Kb */
    public void mo7656Kb() {
        setMode(1);
        stopRecording();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRecording();
    }

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        m3190Q(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1991Eg = (SoundLevels) findViewById(R.id.sound_levels);
        this.f1989Cg = (ImageView) findViewById(R.id.record_button_visual);
        this.f1990Dg = findViewById(R.id.record_button);
        this.f1992Fg = (TextView) findViewById(R.id.hint_text);
        this.f1993Gg = (PausableChronometer) findViewById(R.id.timer_text);
        this.f1991Eg.mo7748a(this.f1994Hg.mo7779xj());
        this.f1990Dg.setOnTouchListener(new C1302c(this));
    }

    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        if (i == 801) {
            C1430e.m3625i("MessagingApp", "Max size reached while recording audio");
            m3191Sm();
            return;
        }
        m3190Q(i, i2);
    }

    public void onPause() {
        stopRecording();
    }

    /* access modifiers changed from: package-private */
    public boolean onRecordButtonTouchDown() {
        if (this.f1994Hg.mo7777qj() || this.f1996dd != 1) {
            return false;
        }
        setMode(2);
        C1452ha.get().mo8183a(getContext(), R.raw.audio_initiate, new C1320d(this));
        this.f1995Ig = System.currentTimeMillis();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onRecordButtonTouchUp() {
        if (System.currentTimeMillis() - this.f1995Ig < 300) {
            Uri stopRecording = stopRecording();
            if (stopRecording != null) {
                C1478ua.m3823a((Runnable) new C1322e(this, stopRecording));
            }
            setMode(1);
            this.f1992Fg.setTypeface((Typeface) null, 1);
        } else if (m3198qj()) {
            setMode(4);
            C1480va.getMainThreadHandler().postDelayed(new C1324f(this), 500);
        } else {
            setMode(1);
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            return mo7655Jb();
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                return true;
            }
            if (actionMasked != 3) {
                return super.onTouchEvent(motionEvent);
            }
        }
        return onRecordButtonTouchUp();
    }

    public void testSetMediaRecorder(C1303ca caVar) {
        this.f1994Hg = caVar;
    }

    /* renamed from: u */
    public void mo7667u(int i) {
        this.f1997ob = i;
        m3192Tm();
    }

    /* renamed from: a */
    public void mo7657a(C1326g gVar) {
        this.mHostInterface = gVar;
    }
}
