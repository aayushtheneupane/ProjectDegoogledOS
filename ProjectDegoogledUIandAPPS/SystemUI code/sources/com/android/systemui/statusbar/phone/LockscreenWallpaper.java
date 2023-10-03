package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.IWallpaperManager;
import android.app.IWallpaperManagerCallback;
import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.NotificationMediaManager;
import libcore.io.IoUtils;

public class LockscreenWallpaper extends IWallpaperManagerCallback.Stub implements Runnable {
    private final StatusBar mBar;
    /* access modifiers changed from: private */
    public Bitmap mCache;
    /* access modifiers changed from: private */
    public boolean mCached;
    private int mCurrentUserId;

    /* renamed from: mH */
    private final Handler f67mH;
    /* access modifiers changed from: private */
    public AsyncTask<Void, Void, LoaderResult> mLoader;
    /* access modifiers changed from: private */
    public final NotificationMediaManager mMediaManager = ((NotificationMediaManager) Dependency.get(NotificationMediaManager.class));
    private UserHandle mSelectedUser;
    /* access modifiers changed from: private */
    public final KeyguardUpdateMonitor mUpdateMonitor;
    private final WallpaperManager mWallpaperManager;

    public void onWallpaperColorsChanged(WallpaperColors wallpaperColors, int i, int i2) {
    }

    public LockscreenWallpaper(Context context, StatusBar statusBar, Handler handler) {
        this.mBar = statusBar;
        this.f67mH = handler;
        this.mWallpaperManager = (WallpaperManager) context.getSystemService("wallpaper");
        this.mCurrentUserId = ActivityManager.getCurrentUser();
        this.mUpdateMonitor = KeyguardUpdateMonitor.getInstance(context);
        IWallpaperManager asInterface = IWallpaperManager.Stub.asInterface(ServiceManager.getService("wallpaper"));
        if (asInterface != null) {
            try {
                asInterface.setLockWallpaperCallback(this);
            } catch (RemoteException e) {
                Log.e("LockscreenWallpaper", "System dead?" + e);
            }
        }
    }

    public Bitmap getBitmap() {
        if (this.mCached) {
            return this.mCache;
        }
        boolean z = true;
        if (!this.mWallpaperManager.isWallpaperSupported()) {
            this.mCached = true;
            this.mCache = null;
            return null;
        }
        LoaderResult loadBitmap = loadBitmap(this.mCurrentUserId, this.mSelectedUser);
        if (loadBitmap.success) {
            this.mCached = true;
            KeyguardUpdateMonitor keyguardUpdateMonitor = this.mUpdateMonitor;
            if (loadBitmap.bitmap == null) {
                z = false;
            }
            keyguardUpdateMonitor.setHasLockscreenWallpaper(z);
            this.mCache = loadBitmap.bitmap;
        }
        return this.mCache;
    }

    public LoaderResult loadBitmap(int i, UserHandle userHandle) {
        if (!this.mWallpaperManager.isWallpaperSupported()) {
            return LoaderResult.success((Bitmap) null);
        }
        if (userHandle != null) {
            i = userHandle.getIdentifier();
        }
        ParcelFileDescriptor wallpaperFile = this.mWallpaperManager.getWallpaperFile(2, i);
        if (wallpaperFile != null) {
            try {
                return LoaderResult.success(BitmapFactory.decodeFileDescriptor(wallpaperFile.getFileDescriptor(), (Rect) null, new BitmapFactory.Options()));
            } catch (OutOfMemoryError e) {
                Log.w("LockscreenWallpaper", "Can't decode file", e);
                return LoaderResult.fail();
            } finally {
                IoUtils.closeQuietly(wallpaperFile);
            }
        } else if (userHandle != null) {
            return LoaderResult.success(this.mWallpaperManager.getBitmapAsUser(userHandle.getIdentifier(), true));
        } else {
            return LoaderResult.success((Bitmap) null);
        }
    }

    public void setCurrentUser(int i) {
        if (i != this.mCurrentUserId) {
            UserHandle userHandle = this.mSelectedUser;
            if (userHandle == null || i != userHandle.getIdentifier()) {
                this.mCached = false;
            }
            this.mCurrentUserId = i;
        }
    }

    public void onWallpaperChanged() {
        postUpdateWallpaper();
    }

    private void postUpdateWallpaper() {
        this.f67mH.removeCallbacks(this);
        this.f67mH.post(this);
    }

    public void run() {
        AsyncTask<Void, Void, LoaderResult> asyncTask = this.mLoader;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        final int i = this.mCurrentUserId;
        final UserHandle userHandle = this.mSelectedUser;
        this.mLoader = new AsyncTask<Void, Void, LoaderResult>() {
            /* access modifiers changed from: protected */
            public LoaderResult doInBackground(Void... voidArr) {
                return LockscreenWallpaper.this.loadBitmap(i, userHandle);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(LoaderResult loaderResult) {
                super.onPostExecute(loaderResult);
                if (!isCancelled()) {
                    if (loaderResult.success) {
                        boolean unused = LockscreenWallpaper.this.mCached = true;
                        Bitmap unused2 = LockscreenWallpaper.this.mCache = loaderResult.bitmap;
                        LockscreenWallpaper.this.mUpdateMonitor.setHasLockscreenWallpaper(loaderResult.bitmap != null);
                        LockscreenWallpaper.this.mMediaManager.updateMediaMetaData(true, true);
                    }
                    AsyncTask unused3 = LockscreenWallpaper.this.mLoader = null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class LoaderResult {
        public final Bitmap bitmap;
        public final boolean success;

        LoaderResult(boolean z, Bitmap bitmap2) {
            this.success = z;
            this.bitmap = bitmap2;
        }

        static LoaderResult success(Bitmap bitmap2) {
            return new LoaderResult(true, bitmap2);
        }

        static LoaderResult fail() {
            return new LoaderResult(false, (Bitmap) null);
        }
    }

    public static class WallpaperDrawable extends DrawableWrapper {
        private final ConstantState mState;
        private final Rect mTmpRect;

        public int getIntrinsicHeight() {
            return -1;
        }

        public int getIntrinsicWidth() {
            return -1;
        }

        public WallpaperDrawable(Resources resources, Bitmap bitmap) {
            this(resources, new ConstantState(bitmap));
        }

        private WallpaperDrawable(Resources resources, ConstantState constantState) {
            super(new BitmapDrawable(resources, constantState.mBackground));
            this.mTmpRect = new Rect();
            this.mState = constantState;
        }

        public void setXfermode(Xfermode xfermode) {
            getDrawable().setXfermode(xfermode);
        }

        /* access modifiers changed from: protected */
        public void onBoundsChange(Rect rect) {
            float f;
            float f2;
            int width = getBounds().width();
            int height = getBounds().height();
            int width2 = this.mState.mBackground.getWidth();
            int height2 = this.mState.mBackground.getHeight();
            if (width2 * height > width * height2) {
                f2 = (float) height;
                f = (float) height2;
            } else {
                f2 = (float) width;
                f = (float) width2;
            }
            float f3 = f2 / f;
            if (f3 <= 1.0f) {
                f3 = 1.0f;
            }
            float f4 = ((float) height2) * f3;
            float f5 = (((float) height) - f4) * 0.5f;
            this.mTmpRect.set(rect.left, rect.top + Math.round(f5), rect.left + Math.round(((float) width2) * f3), rect.top + Math.round(f4 + f5));
            super.onBoundsChange(this.mTmpRect);
        }

        public ConstantState getConstantState() {
            return this.mState;
        }

        static class ConstantState extends Drawable.ConstantState {
            /* access modifiers changed from: private */
            public final Bitmap mBackground;

            public int getChangingConfigurations() {
                return 0;
            }

            ConstantState(Bitmap bitmap) {
                this.mBackground = bitmap;
            }

            public Drawable newDrawable() {
                return newDrawable((Resources) null);
            }

            public Drawable newDrawable(Resources resources) {
                return new WallpaperDrawable(resources, this);
            }
        }
    }
}
