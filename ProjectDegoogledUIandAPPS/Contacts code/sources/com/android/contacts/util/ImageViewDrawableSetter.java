package com.android.contacts.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ThumbnailUtils;
import android.text.TextUtils;
import android.widget.ImageView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.lettertiles.LetterTileDrawable;
import com.android.contacts.model.Contact;
import java.util.Arrays;

public class ImageViewDrawableSetter {
    private byte[] mCompressed;
    private Contact mContact;
    private int mDurationInMillis = 0;
    private Drawable mPreviousDrawable;
    private ImageView mTarget;

    public Bitmap setupContactPhoto(Contact contact, ImageView imageView) {
        this.mContact = contact;
        setTarget(imageView);
        return setCompressedImage(contact.getPhotoBinaryData());
    }

    /* access modifiers changed from: protected */
    public void setTarget(ImageView imageView) {
        if (this.mTarget != imageView) {
            this.mTarget = imageView;
            this.mCompressed = null;
            this.mPreviousDrawable = null;
        }
    }

    /* access modifiers changed from: protected */
    public Bitmap setCompressedImage(byte[] bArr) {
        Drawable drawable = this.mPreviousDrawable;
        if (drawable != null && drawable != null && (drawable instanceof BitmapDrawable) && Arrays.equals(this.mCompressed, bArr)) {
            return previousBitmap();
        }
        Drawable decodedBitmapDrawable = decodedBitmapDrawable(bArr);
        if (decodedBitmapDrawable == null) {
            decodedBitmapDrawable = defaultDrawable();
        }
        this.mCompressed = bArr;
        if (decodedBitmapDrawable == null) {
            return previousBitmap();
        }
        Drawable drawable2 = this.mPreviousDrawable;
        if (drawable2 == null || this.mDurationInMillis == 0) {
            this.mTarget.setImageDrawable(decodedBitmapDrawable);
        } else {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{drawable2, decodedBitmapDrawable});
            this.mTarget.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(this.mDurationInMillis);
        }
        this.mPreviousDrawable = decodedBitmapDrawable;
        return previousBitmap();
    }

    private Bitmap previousBitmap() {
        Drawable drawable = this.mPreviousDrawable;
        if (drawable != null && !(drawable instanceof LetterTileDrawable)) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }

    private Drawable defaultDrawable() {
        ContactPhotoManager.DefaultImageRequest defaultImageRequest;
        Resources resources = this.mTarget.getResources();
        int i = this.mContact.isDisplayNameFromOrganization() ? 2 : 1;
        if (TextUtils.isEmpty(this.mContact.getLookupKey())) {
            defaultImageRequest = new ContactPhotoManager.DefaultImageRequest((String) null, this.mContact.getDisplayName(), i, false);
        } else {
            defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(this.mContact.getDisplayName(), this.mContact.getLookupKey(), i, false);
        }
        return ContactPhotoManager.getDefaultAvatarDrawableForContact(resources, true, defaultImageRequest);
    }

    private BitmapDrawable decodedBitmapDrawable(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        Resources resources = this.mTarget.getResources();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray == null) {
            return null;
        }
        if (decodeByteArray.getHeight() != decodeByteArray.getWidth()) {
            int min = Math.min(decodeByteArray.getWidth(), decodeByteArray.getHeight());
            decodeByteArray = ThumbnailUtils.extractThumbnail(decodeByteArray, min, min);
        }
        return new BitmapDrawable(resources, decodeByteArray);
    }
}
