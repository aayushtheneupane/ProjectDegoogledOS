package com.android.contacts.editor;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.android.contacts.R;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.util.MaterialColorMapUtils;
import com.android.contacts.util.SchedulingUtils;
import com.android.contacts.widget.QuickContactImageView;

public class PhotoEditorView extends RelativeLayout implements View.OnClickListener {
    private boolean mIsNonDefaultPhotoBound;
    /* access modifiers changed from: private */
    public final boolean mIsTwoPanel;
    /* access modifiers changed from: private */
    public final float mLandscapePhotoRatio;
    private Listener mListener;
    private MaterialColorMapUtils.MaterialPalette mMaterialPalette;
    private View mPhotoIcon;
    private View mPhotoIconOverlay;
    private QuickContactImageView mPhotoImageView;
    private View mPhotoTouchInterceptOverlay;
    /* access modifiers changed from: private */
    public final float mPortraitPhotoRatio;
    private boolean mReadOnly;

    public interface Listener {
        void onPhotoEditorViewClicked();
    }

    public PhotoEditorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PhotoEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLandscapePhotoRatio = getTypedFloat(R.dimen.quickcontact_landscape_photo_ratio);
        this.mPortraitPhotoRatio = getTypedFloat(R.dimen.editor_portrait_photo_ratio);
        this.mIsTwoPanel = getResources().getBoolean(R.bool.contacteditor_two_panel);
    }

    private float getTypedFloat(int i) {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(i, typedValue, true);
        return typedValue.getFloat();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mPhotoImageView = (QuickContactImageView) findViewById(R.id.photo);
        this.mPhotoIcon = findViewById(R.id.photo_icon);
        this.mPhotoIconOverlay = findViewById(R.id.photo_icon_overlay);
        this.mPhotoTouchInterceptOverlay = findViewById(R.id.photo_touch_intercept_overlay);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setReadOnly(boolean z) {
        this.mReadOnly = z;
        if (this.mReadOnly) {
            this.mPhotoIcon.setVisibility(8);
            this.mPhotoIconOverlay.setVisibility(8);
            this.mPhotoTouchInterceptOverlay.setClickable(false);
            this.mPhotoTouchInterceptOverlay.setContentDescription(getContext().getString(R.string.editor_contact_photo_content_description));
            return;
        }
        this.mPhotoIcon.setVisibility(0);
        this.mPhotoIconOverlay.setVisibility(0);
        this.mPhotoTouchInterceptOverlay.setOnClickListener(this);
        updatePhotoDescription();
    }

    public void setPalette(MaterialColorMapUtils.MaterialPalette materialPalette) {
        this.mMaterialPalette = materialPalette;
    }

    public void setPhoto(ValuesDelta valuesDelta) {
        Long photoFileId = EditorUiUtils.getPhotoFileId(valuesDelta);
        if (photoFileId != null) {
            setFullSizedPhoto(ContactsContract.DisplayPhoto.CONTENT_URI.buildUpon().appendPath(photoFileId.toString()).build());
            adjustDimensions();
            return;
        }
        Bitmap photoBitmap = EditorUiUtils.getPhotoBitmap(valuesDelta);
        if (photoBitmap != null) {
            setPhoto(photoBitmap);
            adjustDimensions();
            return;
        }
        setDefaultPhoto(this.mMaterialPalette);
        adjustDimensions();
    }

    private void adjustDimensions() {
        SchedulingUtils.doOnPreDraw(this, false, new Runnable() {
            public void run() {
                int i;
                int i2;
                if (PhotoEditorView.this.mIsTwoPanel) {
                    i2 = PhotoEditorView.this.getHeight();
                    i = (int) (((float) i2) * PhotoEditorView.this.mLandscapePhotoRatio);
                } else {
                    i = PhotoEditorView.this.getWidth();
                    i2 = (int) (((float) i) / PhotoEditorView.this.mPortraitPhotoRatio);
                }
                ViewGroup.LayoutParams layoutParams = PhotoEditorView.this.getLayoutParams();
                layoutParams.height = i2;
                layoutParams.width = i;
                PhotoEditorView.this.setLayoutParams(layoutParams);
            }
        });
    }

    public boolean isWritablePhotoSet() {
        return !this.mReadOnly && this.mIsNonDefaultPhotoBound;
    }

    private void setPhoto(Bitmap bitmap) {
        this.mPhotoImageView.setImageBitmap(bitmap);
        this.mIsNonDefaultPhotoBound = true;
        updatePhotoDescription();
    }

    private void setDefaultPhoto(MaterialColorMapUtils.MaterialPalette materialPalette) {
        this.mIsNonDefaultPhotoBound = false;
        updatePhotoDescription();
        EditorUiUtils.setDefaultPhoto(this.mPhotoImageView, getResources(), materialPalette);
    }

    private void updatePhotoDescription() {
        this.mPhotoTouchInterceptOverlay.setContentDescription(getContext().getString(this.mIsNonDefaultPhotoBound ? R.string.editor_change_photo_content_description : R.string.editor_add_photo_content_description));
    }

    public void setFullSizedPhoto(Uri uri) {
        this.mPhotoImageView.setImageURI(uri);
        this.mIsNonDefaultPhotoBound = true;
        updatePhotoDescription();
    }

    public void removePhoto() {
        setDefaultPhoto(this.mMaterialPalette);
    }

    public void onClick(View view) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onPhotoEditorViewClicked();
        }
    }
}
