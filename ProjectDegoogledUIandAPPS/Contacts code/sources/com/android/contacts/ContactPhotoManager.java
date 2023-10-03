package com.android.contacts;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.contacts.lettertiles.LetterTileDrawable;
import com.android.contacts.util.PermissionsUtil;

public abstract class ContactPhotoManager implements ComponentCallbacks2 {
    private static final String CONTACT_TYPE_PARAM_KEY = "contact_type";
    static final boolean DEBUG = false;
    static final boolean DEBUG_SIZES = false;
    public static DefaultImageProvider DEFAULT_AVATAR = new LetterTileDefaultImageProvider();
    public static final DefaultImageProvider DEFAULT_BLANK = new BlankDefaultImageProvider();
    private static final Uri DEFAULT_IMAGE_URI = Uri.parse("defaultimage://");
    private static final String DEFAULT_IMAGE_URI_SCHEME = "defaultimage";
    private static final String DISPLAY_NAME_PARAM_KEY = "display_name";
    private static final String IDENTIFIER_PARAM_KEY = "identifier";
    public static final boolean IS_CIRCULAR_DEFAULT = false;
    private static final String IS_CIRCULAR_PARAM_KEY = "is_circular";
    public static final float OFFSET_DEFAULT = 0.0f;
    private static final String OFFSET_PARAM_KEY = "offset";
    public static final float SCALE_DEFAULT = 1.0f;
    private static final String SCALE_PARAM_KEY = "scale";
    static final String TAG = "ContactPhotoManager";
    public static final int TYPE_BUSINESS = 2;
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_PERSON = 1;
    public static final int TYPE_VOICEMAIL = 3;
    private static Drawable sDefaultLetterAvatar = null;
    private static ContactPhotoManager sInstance;

    public static abstract class DefaultImageProvider {
        public abstract void applyDefaultImage(ImageView imageView, int i, boolean z, DefaultImageRequest defaultImageRequest);
    }

    public abstract void cacheBitmap(Uri uri, Bitmap bitmap, byte[] bArr);

    public abstract void cancelPendingRequests(View view);

    public abstract void loadPhoto(ImageView imageView, Uri uri, int i, boolean z, boolean z2, DefaultImageRequest defaultImageRequest, DefaultImageProvider defaultImageProvider);

    public abstract void loadThumbnail(ImageView imageView, long j, boolean z, boolean z2, DefaultImageRequest defaultImageRequest, DefaultImageProvider defaultImageProvider);

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int i) {
    }

    public abstract void pause();

    public abstract void preloadPhotosInBackground();

    public abstract void refreshCache();

    public abstract void removePhoto(ImageView imageView);

    public abstract void resume();

    public static Drawable getDefaultAvatarDrawableForContact(Resources resources, boolean z, DefaultImageRequest defaultImageRequest) {
        if (defaultImageRequest != null) {
            return LetterTileDefaultImageProvider.getDefaultImageForContact(resources, defaultImageRequest);
        }
        if (sDefaultLetterAvatar == null) {
            sDefaultLetterAvatar = LetterTileDefaultImageProvider.getDefaultImageForContact(resources, (DefaultImageRequest) null);
        }
        return sDefaultLetterAvatar;
    }

    public static Uri getDefaultAvatarUriForContact(DefaultImageRequest defaultImageRequest) {
        Uri.Builder buildUpon = DEFAULT_IMAGE_URI.buildUpon();
        if (defaultImageRequest != null) {
            if (!TextUtils.isEmpty(defaultImageRequest.displayName)) {
                buildUpon.appendQueryParameter(DISPLAY_NAME_PARAM_KEY, defaultImageRequest.displayName);
            }
            if (!TextUtils.isEmpty(defaultImageRequest.identifier)) {
                buildUpon.appendQueryParameter(IDENTIFIER_PARAM_KEY, defaultImageRequest.identifier);
            }
            int i = defaultImageRequest.contactType;
            if (i != 1) {
                buildUpon.appendQueryParameter(CONTACT_TYPE_PARAM_KEY, String.valueOf(i));
            }
            float f = defaultImageRequest.scale;
            if (f != 1.0f) {
                buildUpon.appendQueryParameter(SCALE_PARAM_KEY, String.valueOf(f));
            }
            float f2 = defaultImageRequest.offset;
            if (f2 != OFFSET_DEFAULT) {
                buildUpon.appendQueryParameter(OFFSET_PARAM_KEY, String.valueOf(f2));
            }
            boolean z = defaultImageRequest.isCircular;
            if (z) {
                buildUpon.appendQueryParameter(IS_CIRCULAR_PARAM_KEY, String.valueOf(z));
            }
        }
        return buildUpon.build();
    }

    public static String appendBusinessContactType(String str) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.encodedFragment(String.valueOf(2));
        return buildUpon.build().toString();
    }

    public static Uri removeContactType(Uri uri) {
        if (TextUtils.isEmpty(uri.getEncodedFragment())) {
            return uri;
        }
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.encodedFragment((String) null);
        return buildUpon.build();
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

    protected static DefaultImageRequest getDefaultImageRequestFromUri(Uri uri) {
        DefaultImageRequest defaultImageRequest = new DefaultImageRequest(uri.getQueryParameter(DISPLAY_NAME_PARAM_KEY), uri.getQueryParameter(IDENTIFIER_PARAM_KEY), false);
        try {
            String queryParameter = uri.getQueryParameter(CONTACT_TYPE_PARAM_KEY);
            if (!TextUtils.isEmpty(queryParameter)) {
                defaultImageRequest.contactType = Integer.valueOf(queryParameter).intValue();
            }
            String queryParameter2 = uri.getQueryParameter(SCALE_PARAM_KEY);
            if (!TextUtils.isEmpty(queryParameter2)) {
                defaultImageRequest.scale = Float.valueOf(queryParameter2).floatValue();
            }
            String queryParameter3 = uri.getQueryParameter(OFFSET_PARAM_KEY);
            if (!TextUtils.isEmpty(queryParameter3)) {
                defaultImageRequest.offset = Float.valueOf(queryParameter3).floatValue();
            }
            String queryParameter4 = uri.getQueryParameter(IS_CIRCULAR_PARAM_KEY);
            if (!TextUtils.isEmpty(queryParameter4)) {
                defaultImageRequest.isCircular = Boolean.valueOf(queryParameter4).booleanValue();
            }
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Invalid DefaultImageRequest image parameters provided, ignoring and using defaults.");
        }
        return defaultImageRequest;
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultImageUri(Uri uri) {
        return DEFAULT_IMAGE_URI_SCHEME.equals(uri.getScheme());
    }

    public static class DefaultImageRequest {
        public static DefaultImageRequest EMPTY_CIRCULAR_BUSINESS_IMAGE_REQUEST = new DefaultImageRequest((String) null, (String) null, 2, true);
        public static DefaultImageRequest EMPTY_CIRCULAR_DEFAULT_IMAGE_REQUEST = new DefaultImageRequest((String) null, (String) null, true);
        public static DefaultImageRequest EMPTY_DEFAULT_BUSINESS_IMAGE_REQUEST = new DefaultImageRequest((String) null, (String) null, 2, false);
        public static DefaultImageRequest EMPTY_DEFAULT_IMAGE_REQUEST = new DefaultImageRequest();
        public int contactType;
        public String displayName;
        public String identifier;
        public boolean isCircular;
        public float offset;
        public float scale;

        public DefaultImageRequest() {
            this.contactType = 1;
            this.scale = 1.0f;
            this.offset = ContactPhotoManager.OFFSET_DEFAULT;
            this.isCircular = false;
        }

        public DefaultImageRequest(String str, String str2, boolean z) {
            this(str, str2, 1, 1.0f, ContactPhotoManager.OFFSET_DEFAULT, z);
        }

        public DefaultImageRequest(String str, String str2, int i, boolean z) {
            this(str, str2, i, 1.0f, ContactPhotoManager.OFFSET_DEFAULT, z);
        }

        public DefaultImageRequest(String str, String str2, int i, float f, float f2, boolean z) {
            this.contactType = 1;
            this.scale = 1.0f;
            this.offset = ContactPhotoManager.OFFSET_DEFAULT;
            this.isCircular = false;
            this.displayName = str;
            this.identifier = str2;
            this.contactType = i;
            this.scale = f;
            this.offset = f2;
            this.isCircular = z;
        }
    }

    private static class LetterTileDefaultImageProvider extends DefaultImageProvider {
        private LetterTileDefaultImageProvider() {
        }

        public void applyDefaultImage(ImageView imageView, int i, boolean z, DefaultImageRequest defaultImageRequest) {
            imageView.setImageDrawable(getDefaultImageForContact(imageView.getResources(), defaultImageRequest));
        }

        public static Drawable getDefaultImageForContact(Resources resources, DefaultImageRequest defaultImageRequest) {
            LetterTileDrawable letterTileDrawable = new LetterTileDrawable(resources);
            if (defaultImageRequest != null) {
                if (TextUtils.isEmpty(defaultImageRequest.identifier)) {
                    letterTileDrawable.setLetterAndColorFromContactDetails((String) null, defaultImageRequest.displayName);
                } else {
                    letterTileDrawable.setLetterAndColorFromContactDetails(defaultImageRequest.displayName, defaultImageRequest.identifier);
                }
                letterTileDrawable.setContactType(defaultImageRequest.contactType);
                letterTileDrawable.setScale(defaultImageRequest.scale);
                letterTileDrawable.setOffset(defaultImageRequest.offset);
                letterTileDrawable.setIsCircular(defaultImageRequest.isCircular);
            }
            return letterTileDrawable;
        }
    }

    private static class BlankDefaultImageProvider extends DefaultImageProvider {
        private static Drawable sDrawable;

        private BlankDefaultImageProvider() {
        }

        public void applyDefaultImage(ImageView imageView, int i, boolean z, DefaultImageRequest defaultImageRequest) {
            if (sDrawable == null) {
                sDrawable = new ColorDrawable(imageView.getContext().getResources().getColor(R.color.image_placeholder));
            }
            imageView.setImageDrawable(sDrawable);
        }
    }

    public static ContactPhotoManager getInstance(Context context) {
        if (sInstance == null) {
            Context applicationContext = context.getApplicationContext();
            sInstance = createContactPhotoManager(applicationContext);
            applicationContext.registerComponentCallbacks(sInstance);
            if (PermissionsUtil.hasContactsPermissions(context)) {
                sInstance.preloadPhotosInBackground();
            }
        }
        return sInstance;
    }

    public static synchronized ContactPhotoManager createContactPhotoManager(Context context) {
        ContactPhotoManagerImpl contactPhotoManagerImpl;
        synchronized (ContactPhotoManager.class) {
            contactPhotoManagerImpl = new ContactPhotoManagerImpl(context);
        }
        return contactPhotoManagerImpl;
    }

    public static void injectContactPhotoManagerForTesting(ContactPhotoManager contactPhotoManager) {
        sInstance = contactPhotoManager;
    }

    public final void loadThumbnail(ImageView imageView, long j, boolean z, boolean z2, DefaultImageRequest defaultImageRequest) {
        loadThumbnail(imageView, j, z, z2, defaultImageRequest, DEFAULT_AVATAR);
    }

    public final void loadPhoto(ImageView imageView, Uri uri, int i, boolean z, boolean z2, DefaultImageRequest defaultImageRequest) {
        loadPhoto(imageView, uri, i, z, z2, defaultImageRequest, DEFAULT_AVATAR);
    }

    public final void loadDirectoryPhoto(ImageView imageView, Uri uri, boolean z, boolean z2, DefaultImageRequest defaultImageRequest) {
        loadPhoto(imageView, uri, -1, z, z2, defaultImageRequest, DEFAULT_AVATAR);
    }
}
