package com.android.contacts.common.list;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.widget.BidiTextView;

public abstract class ContactTileView extends FrameLayout {
    protected Listener mListener;
    private Uri mLookupUri;
    private BidiTextView mName;
    private ImageView mPhoto;
    private ContactPhotoManager mPhotoManager = null;

    public interface Listener {
    }

    public ContactTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public abstract void configureViewForImage(boolean z);

    /* access modifiers changed from: protected */
    public abstract View.OnClickListener createClickListener();

    /* access modifiers changed from: protected */
    public abstract int getApproximateImageSize();

    /* access modifiers changed from: protected */
    public abstract ContactPhotoManager.DefaultImageRequest getDefaultImageRequest(String str, String str2);

    public Uri getLookupUri() {
        return this.mLookupUri;
    }

    /* access modifiers changed from: protected */
    public String getNameForView(ContactEntry contactEntry) {
        return contactEntry.namePrimary;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isContactPhotoCircular();

    /* access modifiers changed from: protected */
    public abstract boolean isDarkTheme();

    public void loadFromContact(ContactEntry contactEntry) {
        if (contactEntry != null) {
            this.mName.setText(getNameForView(contactEntry));
            this.mLookupUri = contactEntry.lookupUri;
            boolean z = false;
            setVisibility(0);
            if (this.mPhotoManager != null) {
                ContactPhotoManager.DefaultImageRequest defaultImageRequest = getDefaultImageRequest(contactEntry.namePrimary, contactEntry.lookupKey);
                if (contactEntry.photoUri == null) {
                    z = true;
                }
                configureViewForImage(z);
                ImageView imageView = this.mPhoto;
                if (imageView != null) {
                    this.mPhotoManager.loadPhoto(imageView, contactEntry.photoUri, getApproximateImageSize(), isDarkTheme(), isContactPhotoCircular(), defaultImageRequest);
                    return;
                }
                return;
            }
            LogUtil.m10w("ContactTileView", "contactPhotoManager not set", new Object[0]);
            return;
        }
        setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mName = (BidiTextView) findViewById(R.id.contact_tile_name);
        this.mPhoto = (ImageView) findViewById(R.id.contact_tile_image);
        setOnClickListener(createClickListener());
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setPhotoManager(ContactPhotoManager contactPhotoManager) {
        this.mPhotoManager = contactPhotoManager;
    }
}
