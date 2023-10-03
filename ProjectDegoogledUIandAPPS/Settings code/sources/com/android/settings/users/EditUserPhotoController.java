package com.android.settings.users;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.havoc.config.center.C1715R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditUserPhotoController {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Uri mCropPictureUri;
    /* access modifiers changed from: private */
    public final Fragment mFragment;
    /* access modifiers changed from: private */
    public final ImageView mImageView;
    /* access modifiers changed from: private */
    public Bitmap mNewUserPhotoBitmap;
    /* access modifiers changed from: private */
    public Drawable mNewUserPhotoDrawable;
    /* access modifiers changed from: private */
    public final int mPhotoSize = getPhotoSize(this.mContext);
    /* access modifiers changed from: private */
    public final Uri mTakePictureUri;

    public EditUserPhotoController(Fragment fragment, ImageView imageView, Bitmap bitmap, Drawable drawable, boolean z) {
        this.mContext = imageView.getContext();
        this.mFragment = fragment;
        this.mImageView = imageView;
        this.mCropPictureUri = createTempImageUri(this.mContext, "CropEditUserPhoto.jpg", !z);
        this.mTakePictureUri = createTempImageUri(this.mContext, "TakeEditUserPhoto2.jpg", !z);
        this.mImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditUserPhotoController.this.showUpdatePhotoPopup();
            }
        });
        this.mNewUserPhotoBitmap = bitmap;
        this.mNewUserPhotoDrawable = drawable;
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            return false;
        }
        Uri data = (intent == null || intent.getData() == null) ? this.mTakePictureUri : intent.getData();
        switch (i) {
            case 1001:
            case 1002:
                if (this.mTakePictureUri.equals(data)) {
                    cropPhoto();
                } else {
                    copyAndCropPhoto(data);
                }
                return true;
            case 1003:
                onPhotoCropped(data, true);
                return true;
            default:
                return false;
        }
    }

    public Bitmap getNewUserPhotoBitmap() {
        return this.mNewUserPhotoBitmap;
    }

    public Drawable getNewUserPhotoDrawable() {
        return this.mNewUserPhotoDrawable;
    }

    /* access modifiers changed from: private */
    public void showUpdatePhotoPopup() {
        boolean canTakePhoto = canTakePhoto();
        boolean canChoosePhoto = canChoosePhoto();
        if (canTakePhoto || canChoosePhoto) {
            Context context = this.mImageView.getContext();
            ArrayList arrayList = new ArrayList();
            if (canTakePhoto) {
                arrayList.add(new RestrictedMenuItem(context, context.getString(C1715R.string.user_image_take_photo), "no_set_user_icon", new Runnable() {
                    public void run() {
                        EditUserPhotoController.this.takePhoto();
                    }
                }));
            }
            if (canChoosePhoto) {
                arrayList.add(new RestrictedMenuItem(context, context.getString(C1715R.string.user_image_choose_photo), "no_set_user_icon", new Runnable() {
                    public void run() {
                        EditUserPhotoController.this.choosePhoto();
                    }
                }));
            }
            final ListPopupWindow listPopupWindow = new ListPopupWindow(context);
            listPopupWindow.setAnchorView(this.mImageView);
            listPopupWindow.setModal(true);
            listPopupWindow.setInputMethodMode(2);
            listPopupWindow.setAdapter(new RestrictedPopupMenuAdapter(context, arrayList));
            listPopupWindow.setWidth(Math.max(this.mImageView.getWidth(), context.getResources().getDimensionPixelSize(C1715R.dimen.update_user_photo_popup_min_width)));
            listPopupWindow.setDropDownGravity(8388611);
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
                /* JADX WARNING: Unknown variable types count: 1 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                    /*
                        r0 = this;
                        android.widget.ListPopupWindow r0 = r0
                        r0.dismiss()
                        android.widget.Adapter r0 = r1.getAdapter()
                        java.lang.Object r0 = r0.getItem(r3)
                        com.android.settings.users.EditUserPhotoController$RestrictedMenuItem r0 = (com.android.settings.users.EditUserPhotoController.RestrictedMenuItem) r0
                        r0.doAction()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settings.users.EditUserPhotoController.C12084.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
                }
            });
            listPopupWindow.show();
        }
    }

    private boolean canTakePhoto() {
        return this.mImageView.getContext().getPackageManager().queryIntentActivities(new Intent("android.media.action.IMAGE_CAPTURE"), 65536).size() > 0;
    }

    private boolean canChoosePhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        if (this.mImageView.getContext().getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        appendOutputExtra(intent, this.mTakePictureUri);
        this.mFragment.startActivityForResult(intent, 1002);
    }

    /* access modifiers changed from: private */
    public void choosePhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT", (Uri) null);
        intent.setType("image/*");
        appendOutputExtra(intent, this.mTakePictureUri);
        this.mFragment.startActivityForResult(intent, 1001);
    }

    private void copyAndCropPhoto(final Uri uri) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
                if (r3 != null) goto L_0x002e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
                $closeResource(r4, r3);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
                throw r2;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:0x0034, code lost:
                r4 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:0x0035, code lost:
                if (r1 != null) goto L_0x0037;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
                $closeResource(r3, r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x003a, code lost:
                throw r4;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void doInBackground(java.lang.Void... r4) {
                /*
                    r3 = this;
                    com.android.settings.users.EditUserPhotoController r4 = com.android.settings.users.EditUserPhotoController.this
                    android.content.Context r4 = r4.mContext
                    android.content.ContentResolver r4 = r4.getContentResolver()
                    r0 = 0
                    android.net.Uri r1 = r2     // Catch:{ IOException -> 0x003b }
                    java.io.InputStream r1 = r4.openInputStream(r1)     // Catch:{ IOException -> 0x003b }
                    com.android.settings.users.EditUserPhotoController r3 = com.android.settings.users.EditUserPhotoController.this     // Catch:{ all -> 0x0032 }
                    android.net.Uri r3 = r3.mTakePictureUri     // Catch:{ all -> 0x0032 }
                    java.io.OutputStream r3 = r4.openOutputStream(r3)     // Catch:{ all -> 0x0032 }
                    libcore.io.Streams.copy(r1, r3)     // Catch:{ all -> 0x0029 }
                    if (r3 == 0) goto L_0x0023
                    $closeResource(r0, r3)     // Catch:{ all -> 0x0032 }
                L_0x0023:
                    if (r1 == 0) goto L_0x0043
                    $closeResource(r0, r1)     // Catch:{ IOException -> 0x003b }
                    goto L_0x0043
                L_0x0029:
                    r4 = move-exception
                    throw r4     // Catch:{ all -> 0x002b }
                L_0x002b:
                    r2 = move-exception
                    if (r3 == 0) goto L_0x0031
                    $closeResource(r4, r3)     // Catch:{ all -> 0x0032 }
                L_0x0031:
                    throw r2     // Catch:{ all -> 0x0032 }
                L_0x0032:
                    r3 = move-exception
                    throw r3     // Catch:{ all -> 0x0034 }
                L_0x0034:
                    r4 = move-exception
                    if (r1 == 0) goto L_0x003a
                    $closeResource(r3, r1)     // Catch:{ IOException -> 0x003b }
                L_0x003a:
                    throw r4     // Catch:{ IOException -> 0x003b }
                L_0x003b:
                    r3 = move-exception
                    java.lang.String r4 = "EditUserPhotoController"
                    java.lang.String r1 = "Failed to copy photo"
                    android.util.Log.w(r4, r1, r3)
                L_0x0043:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.settings.users.EditUserPhotoController.C12095.doInBackground(java.lang.Void[]):java.lang.Void");
            }

            private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
                if (th != null) {
                    try {
                        autoCloseable.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    autoCloseable.close();
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                if (EditUserPhotoController.this.mFragment.isAdded()) {
                    EditUserPhotoController.this.cropPhoto();
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void cropPhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(this.mTakePictureUri, "image/*");
        appendOutputExtra(intent, this.mCropPictureUri);
        appendCropExtras(intent);
        if (intent.resolveActivity(this.mContext.getPackageManager()) != null) {
            try {
                StrictMode.disableDeathOnFileUriExposure();
                this.mFragment.startActivityForResult(intent, 1003);
            } finally {
                StrictMode.enableDeathOnFileUriExposure();
            }
        } else {
            onPhotoCropped(this.mTakePictureUri, false);
        }
    }

    private void appendOutputExtra(Intent intent, Uri uri) {
        intent.putExtra("output", uri);
        intent.addFlags(3);
        intent.setClipData(ClipData.newRawUri("output", uri));
    }

    private void appendCropExtras(Intent intent) {
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", this.mPhotoSize);
        intent.putExtra("outputY", this.mPhotoSize);
    }

    private void onPhotoCropped(final Uri uri, final boolean z) {
        new AsyncTask<Void, Void, Bitmap>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x0035 A[SYNTHETIC, Splitter:B:20:0x0035] */
            /* JADX WARNING: Removed duplicated region for block: B:28:0x0042 A[SYNTHETIC, Splitter:B:28:0x0042] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.graphics.Bitmap doInBackground(java.lang.Void... r8) {
                /*
                    r7 = this;
                    java.lang.String r8 = "Cannot close image stream"
                    java.lang.String r0 = "EditUserPhotoController"
                    boolean r1 = r3
                    r2 = 0
                    if (r1 == 0) goto L_0x004b
                    com.android.settings.users.EditUserPhotoController r1 = com.android.settings.users.EditUserPhotoController.this     // Catch:{ FileNotFoundException -> 0x002c, all -> 0x002a }
                    android.content.Context r1 = r1.mContext     // Catch:{ FileNotFoundException -> 0x002c, all -> 0x002a }
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ FileNotFoundException -> 0x002c, all -> 0x002a }
                    android.net.Uri r7 = r2     // Catch:{ FileNotFoundException -> 0x002c, all -> 0x002a }
                    java.io.InputStream r7 = r1.openInputStream(r7)     // Catch:{ FileNotFoundException -> 0x002c, all -> 0x002a }
                    android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r7)     // Catch:{ FileNotFoundException -> 0x0028 }
                    if (r7 == 0) goto L_0x0027
                    r7.close()     // Catch:{ IOException -> 0x0023 }
                    goto L_0x0027
                L_0x0023:
                    r7 = move-exception
                    android.util.Log.w(r0, r8, r7)
                L_0x0027:
                    return r1
                L_0x0028:
                    r1 = move-exception
                    goto L_0x002e
                L_0x002a:
                    r1 = move-exception
                    goto L_0x0040
                L_0x002c:
                    r1 = move-exception
                    r7 = r2
                L_0x002e:
                    java.lang.String r3 = "Cannot find image file"
                    android.util.Log.w(r0, r3, r1)     // Catch:{ all -> 0x003e }
                    if (r7 == 0) goto L_0x003d
                    r7.close()     // Catch:{ IOException -> 0x0039 }
                    goto L_0x003d
                L_0x0039:
                    r7 = move-exception
                    android.util.Log.w(r0, r8, r7)
                L_0x003d:
                    return r2
                L_0x003e:
                    r1 = move-exception
                    r2 = r7
                L_0x0040:
                    if (r2 == 0) goto L_0x004a
                    r2.close()     // Catch:{ IOException -> 0x0046 }
                    goto L_0x004a
                L_0x0046:
                    r7 = move-exception
                    android.util.Log.w(r0, r8, r7)
                L_0x004a:
                    throw r1
                L_0x004b:
                    com.android.settings.users.EditUserPhotoController r8 = com.android.settings.users.EditUserPhotoController.this
                    int r8 = r8.mPhotoSize
                    com.android.settings.users.EditUserPhotoController r0 = com.android.settings.users.EditUserPhotoController.this
                    int r0 = r0.mPhotoSize
                    android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
                    android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r8, r0, r1)
                    android.graphics.Canvas r0 = new android.graphics.Canvas
                    r0.<init>(r8)
                    com.android.settings.users.EditUserPhotoController r1 = com.android.settings.users.EditUserPhotoController.this     // Catch:{ FileNotFoundException -> 0x00b5 }
                    android.content.Context r1 = r1.mContext     // Catch:{ FileNotFoundException -> 0x00b5 }
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ FileNotFoundException -> 0x00b5 }
                    android.net.Uri r3 = r2     // Catch:{ FileNotFoundException -> 0x00b5 }
                    java.io.InputStream r1 = r1.openInputStream(r3)     // Catch:{ FileNotFoundException -> 0x00b5 }
                    android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ FileNotFoundException -> 0x00b5 }
                    if (r1 == 0) goto L_0x00b5
                    int r2 = r1.getWidth()
                    int r3 = r1.getHeight()
                    int r2 = java.lang.Math.min(r2, r3)
                    int r3 = r1.getWidth()
                    int r3 = r3 - r2
                    int r3 = r3 / 2
                    int r4 = r1.getHeight()
                    int r4 = r4 - r2
                    int r4 = r4 / 2
                    android.graphics.Rect r5 = new android.graphics.Rect
                    int r6 = r3 + r2
                    int r2 = r2 + r4
                    r5.<init>(r3, r4, r6, r2)
                    android.graphics.Rect r2 = new android.graphics.Rect
                    com.android.settings.users.EditUserPhotoController r3 = com.android.settings.users.EditUserPhotoController.this
                    int r3 = r3.mPhotoSize
                    com.android.settings.users.EditUserPhotoController r7 = com.android.settings.users.EditUserPhotoController.this
                    int r7 = r7.mPhotoSize
                    r4 = 0
                    r2.<init>(r4, r4, r3, r7)
                    android.graphics.Paint r7 = new android.graphics.Paint
                    r7.<init>()
                    r0.drawBitmap(r1, r5, r2, r7)
                    return r8
                L_0x00b5:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.settings.users.EditUserPhotoController.C12106.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    Bitmap unused = EditUserPhotoController.this.mNewUserPhotoBitmap = bitmap;
                    EditUserPhotoController editUserPhotoController = EditUserPhotoController.this;
                    Drawable unused2 = editUserPhotoController.mNewUserPhotoDrawable = CircleFramedDrawable.getInstance(editUserPhotoController.mImageView.getContext(), EditUserPhotoController.this.mNewUserPhotoBitmap);
                    EditUserPhotoController.this.mImageView.setImageDrawable(EditUserPhotoController.this.mNewUserPhotoDrawable);
                }
                new File(EditUserPhotoController.this.mContext.getCacheDir(), "TakeEditUserPhoto2.jpg").delete();
                new File(EditUserPhotoController.this.mContext.getCacheDir(), "CropEditUserPhoto.jpg").delete();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
    }

    private static int getPhotoSize(Context context) {
        Cursor query = context.getContentResolver().query(ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI, new String[]{"display_max_dim"}, (String) null, (String[]) null, (String) null);
        try {
            query.moveToFirst();
            return query.getInt(0);
        } finally {
            query.close();
        }
    }

    private Uri createTempImageUri(Context context, String str, boolean z) {
        File cacheDir = context.getCacheDir();
        cacheDir.mkdirs();
        File file = new File(cacheDir, str);
        if (z) {
            file.delete();
        }
        return FileProvider.getUriForFile(context, "com.android.settings.files", file);
    }

    /* access modifiers changed from: package-private */
    public File saveNewUserPhotoBitmap() {
        if (this.mNewUserPhotoBitmap == null) {
            return null;
        }
        try {
            File file = new File(this.mContext.getCacheDir(), "NewUserPhoto.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.mNewUserPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            Log.e("EditUserPhotoController", "Cannot create temp file", e);
            return null;
        }
    }

    static Bitmap loadNewUserPhotoBitmap(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    /* access modifiers changed from: package-private */
    public void removeNewUserPhotoBitmapFile() {
        new File(this.mContext.getCacheDir(), "NewUserPhoto.png").delete();
    }

    private static final class RestrictedMenuItem {
        private final Runnable mAction;
        private final RestrictedLockUtils.EnforcedAdmin mAdmin;
        private final Context mContext;
        private final boolean mIsRestrictedByBase;
        private final String mTitle;

        public RestrictedMenuItem(Context context, String str, String str2, Runnable runnable) {
            this.mContext = context;
            this.mTitle = str;
            this.mAction = runnable;
            int myUserId = UserHandle.myUserId();
            this.mAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, str2, myUserId);
            this.mIsRestrictedByBase = RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, str2, myUserId);
        }

        public String toString() {
            return this.mTitle;
        }

        /* access modifiers changed from: package-private */
        public final void doAction() {
            if (!isRestrictedByBase()) {
                if (isRestrictedByAdmin()) {
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mContext, this.mAdmin);
                } else {
                    this.mAction.run();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean isRestrictedByAdmin() {
            return this.mAdmin != null;
        }

        /* access modifiers changed from: package-private */
        public final boolean isRestrictedByBase() {
            return this.mIsRestrictedByBase;
        }
    }

    private static final class RestrictedPopupMenuAdapter extends ArrayAdapter<RestrictedMenuItem> {
        public RestrictedPopupMenuAdapter(Context context, List<RestrictedMenuItem> list) {
            super(context, C1715R.layout.restricted_popup_menu_item, C1715R.C1718id.text, list);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            RestrictedMenuItem restrictedMenuItem = (RestrictedMenuItem) getItem(i);
            TextView textView = (TextView) view2.findViewById(C1715R.C1718id.text);
            ImageView imageView = (ImageView) view2.findViewById(C1715R.C1718id.restricted_icon);
            int i2 = 0;
            textView.setEnabled(!restrictedMenuItem.isRestrictedByAdmin() && !restrictedMenuItem.isRestrictedByBase());
            if (!restrictedMenuItem.isRestrictedByAdmin() || restrictedMenuItem.isRestrictedByBase()) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
            return view2;
        }
    }
}
