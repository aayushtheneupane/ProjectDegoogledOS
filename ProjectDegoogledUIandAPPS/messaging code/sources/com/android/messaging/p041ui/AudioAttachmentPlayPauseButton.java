package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.AudioAttachmentPlayPauseButton */
public class AudioAttachmentPlayPauseButton extends ViewSwitcher {

    /* renamed from: jf */
    private ImageView f1577jf;

    /* renamed from: kf */
    private ImageView f1578kf;

    /* renamed from: lf */
    private boolean f1579lf;

    public AudioAttachmentPlayPauseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void updateAppearance() {
        this.f1577jf.setImageDrawable(C1037D.get().mo6943Y(this.f1579lf));
        this.f1578kf.setImageDrawable(C1037D.get().mo6942X(this.f1579lf));
    }

    /* renamed from: m */
    public void mo6875m(boolean z) {
        if (this.f1579lf != z) {
            this.f1579lf = z;
            updateAppearance();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1577jf = (ImageView) findViewById(R.id.play_button);
        this.f1578kf = (ImageView) findViewById(R.id.pause_button);
        updateAppearance();
    }
}
