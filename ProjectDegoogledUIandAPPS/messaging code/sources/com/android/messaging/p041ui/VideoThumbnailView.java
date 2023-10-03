package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;
import com.android.messaging.C0970i;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.p038b.C0842E;
import com.android.messaging.datamodel.p038b.C0880t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.VideoThumbnailView */
public class VideoThumbnailView extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: Af */
    public Uri f1682Af;
    /* access modifiers changed from: private */

    /* renamed from: Bf */
    public boolean f1683Bf;
    /* access modifiers changed from: private */

    /* renamed from: jf */
    public final ImageButton f1684jf;
    private boolean mAnimating;
    /* access modifiers changed from: private */
    public final int mMode;

    /* renamed from: uf */
    private final boolean f1685uf;

    /* renamed from: vf */
    private final boolean f1686vf;
    /* access modifiers changed from: private */

    /* renamed from: wf */
    public final VideoView f1687wf;

    /* renamed from: xf */
    private final AsyncImageView f1688xf;
    /* access modifiers changed from: private */

    /* renamed from: yf */
    public int f1689yf = -1;
    /* access modifiers changed from: private */

    /* renamed from: zf */
    public int f1690zf = -1;

    public VideoThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0970i.VideoThumbnailView);
        LayoutInflater.from(context).inflate(R.layout.video_thumbnail_view, this, true);
        this.f1685uf = obtainStyledAttributes.getBoolean(4, false);
        boolean z = obtainStyledAttributes.getBoolean(2, false);
        this.mMode = obtainStyledAttributes.getInt(3, 0);
        this.f1686vf = obtainStyledAttributes.getBoolean(1, false);
        if (this.mMode == 1) {
            this.f1687wf = new VideoView(context);
            this.f1687wf.setFocusable(false);
            this.f1687wf.setFocusableInTouchMode(false);
            this.f1687wf.clearFocus();
            addView(this.f1687wf, 0, new ViewGroup.LayoutParams(-2, -2));
            this.f1687wf.setOnPreparedListener(new C1050Ja(this, z));
            this.f1687wf.setOnCompletionListener(new C1052Ka(this));
            this.f1687wf.setOnErrorListener(new C1054La(this));
        } else {
            this.f1687wf = null;
        }
        this.f1684jf = (ImageButton) findViewById(R.id.video_thumbnail_play_button);
        if (z) {
            this.f1684jf.setVisibility(8);
        } else {
            this.f1684jf.setOnClickListener(new C1056Ma(this));
            this.f1684jf.setOnLongClickListener(new C1058Na(this));
        }
        this.f1688xf = (AsyncImageView) findViewById(R.id.video_thumbnail_image);
        if (this.f1686vf) {
            this.f1688xf.getLayoutParams().width = -1;
            this.f1688xf.getLayoutParams().height = -1;
            this.f1688xf.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        if (dimensionPixelSize != -1) {
            this.f1688xf.setMaxHeight(dimensionPixelSize);
            this.f1688xf.setAdjustViewBounds(true);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: private */
    /* renamed from: Mm */
    public void m2654Mm() {
        if (this.mAnimating || !this.f1683Bf) {
            return;
        }
        if (this.f1685uf) {
            start();
        } else {
            this.f1687wf.seekTo(0);
        }
    }

    public void clearColorFilter() {
        this.f1688xf.clearColorFilter();
        this.f1684jf.clearColorFilter();
    }

    /* access modifiers changed from: protected */
    public void onAnimationEnd() {
        super.onAnimationEnd();
        this.mAnimating = false;
        m2654Mm();
    }

    /* access modifiers changed from: protected */
    public void onAnimationStart() {
        super.onAnimationStart();
        this.mAnimating = true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(0, 0, i3 - i, i4 - i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (this.f1686vf) {
            super.onMeasure(i, i2);
            return;
        }
        VideoView videoView = this.f1687wf;
        if (videoView != null) {
            videoView.measure(i, i2);
        }
        this.f1688xf.measure(i, i2);
        if ((this.f1689yf == -1 || this.f1690zf == -1) ? false : true) {
            i4 = this.f1689yf;
            i3 = this.f1690zf;
        } else {
            i4 = this.f1688xf.getMeasuredWidth();
            i3 = this.f1688xf.getMeasuredHeight();
        }
        float f = (float) i4;
        float f2 = (float) i3;
        float min = Math.min(Math.max(((float) View.MeasureSpec.getSize(i)) / f, ((float) View.MeasureSpec.getSize(i2)) / f2), Math.max(Math.max(1.0f, ((float) getMinimumWidth()) / f), Math.max(1.0f, ((float) getMinimumHeight()) / f2)));
        setMeasuredDimension((int) (f * min), (int) (f2 * min));
    }

    public void setColorFilter(int i) {
        this.f1688xf.setColorFilter(i);
        this.f1684jf.setColorFilter(i);
    }

    public void setMinimumHeight(int i) {
        super.setMinimumHeight(i);
        VideoView videoView = this.f1687wf;
        if (videoView != null) {
            videoView.setMinimumHeight(i);
        }
    }

    public void setMinimumWidth(int i) {
        super.setMinimumWidth(i);
        VideoView videoView = this.f1687wf;
        if (videoView != null) {
            videoView.setMinimumWidth(i);
        }
    }

    public void start() {
        C1424b.equals(1, this.mMode);
        this.f1684jf.setVisibility(8);
        this.f1688xf.setVisibility(8);
        this.f1687wf.start();
    }

    /* renamed from: b */
    public void mo7096b(MessagePartData messagePartData, boolean z) {
        if (messagePartData == null) {
            this.f1682Af = null;
            this.f1688xf.mo6858a((C0880t) null);
            this.f1689yf = -1;
            this.f1690zf = -1;
            VideoView videoView = this.f1687wf;
            if (videoView != null) {
                videoView.setVideoURI((Uri) null);
                return;
            }
            return;
        }
        this.f1682Af = messagePartData.getContentUri();
        if (z && !C1464na.m3760_j()) {
            this.f1688xf.setImageResource(R.drawable.generic_video_icon);
            this.f1689yf = -1;
            this.f1690zf = -1;
            return;
        }
        this.f1688xf.mo6858a((C0880t) new C0842E(messagePartData));
        VideoView videoView2 = this.f1687wf;
        if (videoView2 != null) {
            videoView2.setVideoURI(this.f1682Af);
        }
        this.f1689yf = messagePartData.getWidth();
        this.f1690zf = messagePartData.getHeight();
    }
}
