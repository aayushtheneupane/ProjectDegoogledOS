package com.android.dialer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.glidephotomanager.GlidePhotoManager;
import com.android.dialer.glidephotomanager.GlidePhotoManagerComponent;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl;

public final class ContactPhotoView extends FrameLayout {
    private final FrameLayout contactBadgeContainer;
    private final QuickContactBadge contactPhoto;
    private final GlidePhotoManager glidePhotoManager;
    private final ImageView rttCallBadge;
    private final ImageView videoCallBadge;

    public ContactPhotoView(Context context) {
        this(context, (AttributeSet) null, 0, 0);
    }

    private void hideBadge() {
        this.contactBadgeContainer.setVisibility(8);
        this.videoCallBadge.setVisibility(8);
        this.rttCallBadge.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Assert.isNotNull(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        boolean z = true;
        Assert.checkState(layoutParams2.height == -2, "The layout height must be WRAP_CONTENT!", new Object[0]);
        if (layoutParams2.width != -2) {
            z = false;
        }
        Assert.checkState(z, "The layout width must be WRAP_CONTENT!", new Object[0]);
    }

    public void setPhoto(PhotoInfo photoInfo) {
        ((GlidePhotoManagerImpl) this.glidePhotoManager).loadQuickContactBadge(this.contactPhoto, photoInfo);
        if (photoInfo.getIsSpam()) {
            hideBadge();
        } else if (photoInfo.getIsVideo()) {
            this.contactBadgeContainer.setVisibility(0);
            this.videoCallBadge.setVisibility(0);
            this.rttCallBadge.setVisibility(8);
        } else if (photoInfo.getIsRtt()) {
            this.contactBadgeContainer.setVisibility(0);
            this.videoCallBadge.setVisibility(8);
            this.rttCallBadge.setVisibility(0);
        } else {
            hideBadge();
        }
    }

    public ContactPhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public ContactPhotoView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ContactPhotoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(LayoutInflater.class);
        Assert.isNotNull(layoutInflater);
        layoutInflater.inflate(R.layout.contact_photo_view, this);
        this.contactPhoto = (QuickContactBadge) findViewById(R.id.quick_contact_photo);
        this.contactBadgeContainer = (FrameLayout) findViewById(R.id.contact_badge_container);
        this.videoCallBadge = (ImageView) findViewById(R.id.video_call_badge);
        this.rttCallBadge = (ImageView) findViewById(R.id.rtt_call_badge);
        this.glidePhotoManager = GlidePhotoManagerComponent.get(context).glidePhotoManager();
        hideBadge();
    }
}
