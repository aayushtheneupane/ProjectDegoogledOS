package com.android.dialer.contactphoto;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import com.android.dialer.R;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.util.PermissionsUtil;

public abstract class ContactPhotoManager implements ComponentCallbacks2 {
    public static final DefaultImageProvider DEFAULT_AVATAR = new LetterTileDefaultImageProvider((C04601) null);
    private static ContactPhotoManager instance;

    public static abstract class DefaultImageProvider {
        public abstract void applyDefaultImage(ImageView imageView, int i, boolean z, DefaultImageRequest defaultImageRequest);
    }

    private static class LetterTileDefaultImageProvider extends DefaultImageProvider {
        /* synthetic */ LetterTileDefaultImageProvider(C04601 r1) {
        }

        public void applyDefaultImage(ImageView imageView, int i, boolean z, DefaultImageRequest defaultImageRequest) {
            LetterTileDrawable letterTileDrawable = new LetterTileDrawable(imageView.getResources());
            int i2 = defaultImageRequest.isCircular ? 1 : 2;
            if (TextUtils.isEmpty(defaultImageRequest.identifier)) {
                letterTileDrawable.setCanonicalDialerLetterTileDetails((String) null, defaultImageRequest.displayName, i2, defaultImageRequest.contactType);
            } else {
                letterTileDrawable.setCanonicalDialerLetterTileDetails(defaultImageRequest.displayName, defaultImageRequest.identifier, i2, defaultImageRequest.contactType);
            }
            letterTileDrawable.setScale(defaultImageRequest.scale);
            letterTileDrawable.setOffset(defaultImageRequest.offset);
            imageView.setImageDrawable(letterTileDrawable);
        }
    }

    static {
        Uri.parse("defaultimage://");
    }

    public static synchronized ContactPhotoManager createContactPhotoManager(Context context) {
        ContactPhotoManagerImpl contactPhotoManagerImpl;
        synchronized (ContactPhotoManager.class) {
            contactPhotoManagerImpl = new ContactPhotoManagerImpl(context);
        }
        return contactPhotoManagerImpl;
    }

    public static ContactPhotoManager getInstance(Context context) {
        if (instance == null) {
            Context applicationContext = context.getApplicationContext();
            instance = createContactPhotoManager(applicationContext);
            applicationContext.registerComponentCallbacks(instance);
            if (PermissionsUtil.hasContactsReadPermissions(context)) {
                instance.preloadPhotosInBackground();
            }
        }
        return instance;
    }

    public static void injectContactPhotoManagerForTesting(ContactPhotoManager contactPhotoManager) {
        instance = contactPhotoManager;
    }

    public static boolean isBusinessContactUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String encodedFragment = uri.getEncodedFragment();
        if (TextUtils.isEmpty(encodedFragment) || !encodedFragment.equals(String.valueOf(2))) {
            return false;
        }
        return true;
    }

    public static Uri removeContactType(Uri uri) {
        if (TextUtils.isEmpty(uri.getEncodedFragment())) {
            return uri;
        }
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.encodedFragment((String) null);
        return buildUpon.build();
    }

    public final void loadDialerThumbnailOrPhoto(QuickContactBadge quickContactBadge, Uri uri, long j, Uri uri2, String str, int i) {
        quickContactBadge.assignContactUri(uri);
        String str2 = null;
        quickContactBadge.setOverlay((Drawable) null);
        quickContactBadge.setContentDescription(quickContactBadge.getContext().getString(R.string.description_quick_contact_for, new Object[]{str}));
        if (uri != null) {
            str2 = R$style.getLookupKeyFromUri(uri);
        }
        DefaultImageRequest defaultImageRequest = new DefaultImageRequest(str, str2, i, true);
        if (j != 0 || uri2 == null) {
            loadThumbnail(quickContactBadge, j, false, true, defaultImageRequest, DEFAULT_AVATAR);
            return;
        }
        loadDirectoryPhoto(quickContactBadge, uri2, false, true, defaultImageRequest);
    }

    public final void loadDirectoryPhoto(ImageView imageView, Uri uri, boolean z, boolean z2, DefaultImageRequest defaultImageRequest) {
        loadPhoto(imageView, uri, -1, z, z2, defaultImageRequest, DEFAULT_AVATAR);
    }

    public final void loadPhoto(ImageView imageView, Uri uri, int i, boolean z, boolean z2, DefaultImageRequest defaultImageRequest) {
        loadPhoto(imageView, uri, i, z, z2, defaultImageRequest, DEFAULT_AVATAR);
    }

    public abstract void loadPhoto(ImageView imageView, Uri uri, int i, boolean z, boolean z2, DefaultImageRequest defaultImageRequest, DefaultImageProvider defaultImageProvider);

    public abstract void loadThumbnail(ImageView imageView, long j, boolean z, boolean z2, DefaultImageRequest defaultImageRequest, DefaultImageProvider defaultImageProvider);

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public abstract void preloadPhotosInBackground();

    public static class DefaultImageRequest {
        public static final DefaultImageRequest EMPTY_CIRCULAR_BUSINESS_IMAGE_REQUEST = new DefaultImageRequest((String) null, (String) null, 2, true);
        public static final DefaultImageRequest EMPTY_CIRCULAR_DEFAULT_IMAGE_REQUEST = new DefaultImageRequest((String) null, (String) null, true);
        public static final DefaultImageRequest EMPTY_DEFAULT_BUSINESS_IMAGE_REQUEST = new DefaultImageRequest((String) null, (String) null, 2, false);
        public static final DefaultImageRequest EMPTY_DEFAULT_IMAGE_REQUEST = new DefaultImageRequest();
        public int contactType;
        public String displayName;
        public String identifier;
        public boolean isCircular;
        public float offset;
        public float scale;

        public DefaultImageRequest() {
            this.contactType = 1;
            this.scale = 1.0f;
            this.offset = 0.0f;
            this.isCircular = false;
        }

        public DefaultImageRequest(String str, String str2, boolean z) {
            this(str, str2, 1, 1.0f, 0.0f, z);
        }

        public DefaultImageRequest(String str, String str2, int i, boolean z) {
            this(str, str2, i, 1.0f, 0.0f, z);
        }

        public DefaultImageRequest(String str, String str2, int i, float f, float f2, boolean z) {
            this.contactType = 1;
            this.scale = 1.0f;
            this.offset = 0.0f;
            this.isCircular = false;
            this.displayName = str;
            this.identifier = str2;
            this.contactType = i;
            this.scale = f;
            this.offset = f2;
            this.isCircular = z;
        }
    }
}
