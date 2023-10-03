package com.android.contacts;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.p002os.BuildCompat;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.lettertiles.LetterTileDrawable;
import com.android.contacts.util.BitmapUtil;

public class ShortcutIntentBuilder {
    /* access modifiers changed from: private */
    public static final String[] CONTACT_COLUMNS = {"display_name", "photo_id", "lookup"};
    /* access modifiers changed from: private */
    public static final String[] PHONE_COLUMNS = {"display_name", "photo_id", "data1", "data2", "data3", "lookup"};
    /* access modifiers changed from: private */
    public static final String[] PHOTO_COLUMNS = {"data15"};
    /* access modifiers changed from: private */
    public final Context mContext;
    private final int mIconDensity;
    private int mIconSize = this.mResources.getDimensionPixelSize(R.dimen.shortcut_icon_size);
    private final OnShortcutIntentCreatedListener mListener;
    private final int mOverlayTextBackgroundColor;
    private final Resources mResources;

    public interface OnShortcutIntentCreatedListener {
        void onShortcutIntentCreated(Uri uri, Intent intent);
    }

    public ShortcutIntentBuilder(Context context, OnShortcutIntentCreatedListener onShortcutIntentCreatedListener) {
        this.mContext = context;
        this.mListener = onShortcutIntentCreatedListener;
        this.mResources = context.getResources();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (this.mIconSize == 0) {
            this.mIconSize = activityManager.getLauncherLargeIconSize();
        }
        this.mIconDensity = activityManager.getLauncherLargeIconDensity();
        this.mOverlayTextBackgroundColor = this.mResources.getColor(R.color.shortcut_overlay_text_background);
    }

    public void createContactShortcutIntent(Uri uri) {
        new ContactLoadingAsyncTask(uri).execute(new Void[0]);
    }

    public void createPhoneNumberShortcutIntent(Uri uri, String str) {
        new PhoneNumberLoadingAsyncTask(uri, str).execute(new Void[0]);
    }

    private abstract class LoadingAsyncTask extends AsyncTask<Void, Void, Void> {
        protected byte[] mBitmapData;
        protected String mContentType;
        protected String mDisplayName;
        protected String mLookupKey;
        protected long mPhotoId;
        protected Uri mUri;

        /* access modifiers changed from: protected */
        public abstract void loadData();

        public LoadingAsyncTask(Uri uri) {
            this.mUri = uri;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            this.mContentType = ShortcutIntentBuilder.this.mContext.getContentResolver().getType(this.mUri);
            loadData();
            loadPhoto();
            return null;
        }

        private void loadPhoto() {
            Cursor query;
            if (this.mPhotoId != 0 && (query = ShortcutIntentBuilder.this.mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, ShortcutIntentBuilder.PHOTO_COLUMNS, "_id=?", new String[]{String.valueOf(this.mPhotoId)}, (String) null)) != null) {
                try {
                    if (query.moveToFirst()) {
                        this.mBitmapData = query.getBlob(0);
                    }
                } finally {
                    query.close();
                }
            }
        }
    }

    private final class ContactLoadingAsyncTask extends LoadingAsyncTask {
        public ContactLoadingAsyncTask(Uri uri) {
            super(uri);
        }

        /* access modifiers changed from: protected */
        public void loadData() {
            Cursor query = ShortcutIntentBuilder.this.mContext.getContentResolver().query(this.mUri, ShortcutIntentBuilder.CONTACT_COLUMNS, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        this.mDisplayName = query.getString(0);
                        this.mPhotoId = query.getLong(1);
                        this.mLookupKey = query.getString(2);
                    }
                } finally {
                    query.close();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            ShortcutIntentBuilder.this.createContactShortcutIntent(this.mUri, this.mContentType, this.mDisplayName, this.mLookupKey, this.mBitmapData);
        }
    }

    private final class PhoneNumberLoadingAsyncTask extends LoadingAsyncTask {
        private String mPhoneLabel;
        private String mPhoneNumber;
        private int mPhoneType;
        private final String mShortcutAction;

        public PhoneNumberLoadingAsyncTask(Uri uri, String str) {
            super(uri);
            this.mShortcutAction = str;
        }

        /* access modifiers changed from: protected */
        public void loadData() {
            Cursor query = ShortcutIntentBuilder.this.mContext.getContentResolver().query(this.mUri, ShortcutIntentBuilder.PHONE_COLUMNS, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        this.mDisplayName = query.getString(0);
                        this.mPhotoId = query.getLong(1);
                        this.mPhoneNumber = query.getString(2);
                        this.mPhoneType = query.getInt(3);
                        this.mPhoneLabel = query.getString(4);
                        this.mLookupKey = query.getString(5);
                    }
                } finally {
                    query.close();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            ShortcutIntentBuilder.this.createPhoneNumberShortcutIntent(this.mUri, this.mDisplayName, this.mLookupKey, this.mBitmapData, this.mPhoneNumber, this.mPhoneType, this.mPhoneLabel, this.mShortcutAction);
        }
    }

    private Drawable getPhotoDrawable(byte[] bArr, String str, String str2) {
        if (bArr != null) {
            return new BitmapDrawable(this.mContext.getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length, (BitmapFactory.Options) null));
        }
        ContactPhotoManager.DefaultImageRequest defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(str, str2, false);
        if (BuildCompat.isAtLeastO()) {
            defaultImageRequest.scale = LetterTileDrawable.getAdaptiveIconScale();
        }
        return ContactPhotoManager.getDefaultAvatarDrawableForContact(this.mContext.getResources(), false, defaultImageRequest);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createContactShortcutIntent(android.net.Uri r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, byte[] r10) {
        /*
            r5 = this;
            boolean r7 = android.text.TextUtils.isEmpty(r8)
            if (r7 == 0) goto L_0x0013
            android.content.Context r7 = r5.mContext
            android.content.res.Resources r7 = r7.getResources()
            r8 = 2131755478(0x7f1001d6, float:1.9141836E38)
            java.lang.String r8 = r7.getString(r8)
        L_0x0013:
            boolean r7 = androidx.core.p002os.BuildCompat.isAtLeastO()
            r0 = 0
            if (r7 == 0) goto L_0x003a
            long r1 = android.content.ContentUris.parseId(r6)
            android.content.Context r7 = r5.mContext
            java.lang.String r3 = "shortcut"
            java.lang.Object r7 = r7.getSystemService(r3)
            android.content.pm.ShortcutManager r7 = (android.content.pm.ShortcutManager) r7
            com.android.contacts.DynamicShortcuts r3 = new com.android.contacts.DynamicShortcuts
            android.content.Context r4 = r5.mContext
            r3.<init>(r4)
            android.content.pm.ShortcutInfo r1 = r3.getQuickContactShortcutInfo(r1, r9, r8)
            if (r1 == 0) goto L_0x003a
            android.content.Intent r7 = r7.createShortcutResultIntent(r1)
            goto L_0x003b
        L_0x003a:
            r7 = r0
        L_0x003b:
            android.graphics.drawable.Drawable r9 = r5.getPhotoDrawable(r10, r8, r9)
            android.content.Context r10 = r5.mContext
            android.content.Intent r10 = com.android.contacts.util.ImplicitIntentsUtil.getIntentForQuickContactLauncherShortcut(r10, r6)
            if (r7 != 0) goto L_0x004c
            android.content.Intent r7 = new android.content.Intent
            r7.<init>()
        L_0x004c:
            android.graphics.Bitmap r9 = r5.generateQuickContactIcon(r9)
            boolean r1 = androidx.core.p002os.BuildCompat.isAtLeastO()
            if (r1 == 0) goto L_0x0060
            androidx.core.graphics.drawable.IconCompat r9 = androidx.core.graphics.drawable.IconCompat.createWithAdaptiveBitmap(r9)
            android.content.Context r1 = r5.mContext
            r9.addToShortcutIntent(r7, r0, r1)
            goto L_0x0065
        L_0x0060:
            java.lang.String r0 = "android.intent.extra.shortcut.ICON"
            r7.putExtra(r0, r9)
        L_0x0065:
            java.lang.String r9 = "android.intent.extra.shortcut.INTENT"
            r7.putExtra(r9, r10)
            java.lang.String r9 = "android.intent.extra.shortcut.NAME"
            r7.putExtra(r9, r8)
            com.android.contacts.ShortcutIntentBuilder$OnShortcutIntentCreatedListener r8 = r5.mListener
            r8.onShortcutIntentCreated(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.ShortcutIntentBuilder.createContactShortcutIntent(android.net.Uri, java.lang.String, java.lang.String, java.lang.String, byte[]):void");
    }

    /* access modifiers changed from: private */
    public void createPhoneNumberShortcutIntent(Uri uri, String str, String str2, byte[] bArr, String str3, int i, String str4, String str5) {
        String str6;
        Uri uri2;
        Bitmap bitmap;
        Intent intent;
        IconCompat iconCompat;
        Drawable photoDrawable = getPhotoDrawable(bArr, str, str2);
        if (TextUtils.isEmpty(str)) {
            str = this.mContext.getResources().getString(R.string.missing_name);
        }
        if ("android.intent.action.CALL".equals(str5)) {
            uri2 = Uri.fromParts("tel", str3, (String) null);
            bitmap = generatePhoneNumberIcon(photoDrawable, i, str4, R.drawable.quantum_ic_phone_vd_theme_24);
            str6 = this.mContext.getResources().getString(R.string.call_by_shortcut, new Object[]{str});
        } else {
            uri2 = Uri.fromParts(ContactsUtils.SCHEME_SMSTO, str3, (String) null);
            bitmap = generatePhoneNumberIcon(photoDrawable, i, str4, R.drawable.quantum_ic_message_vd_theme_24);
            str6 = this.mContext.getResources().getString(R.string.sms_by_shortcut, new Object[]{str});
        }
        Intent intent2 = new Intent(str5, uri2);
        intent2.setFlags(67108864);
        if (BuildCompat.isAtLeastO()) {
            iconCompat = IconCompat.createWithAdaptiveBitmap(bitmap);
            ShortcutManager shortcutManager = (ShortcutManager) this.mContext.getSystemService("shortcut");
            ShortcutInfo actionShortcutInfo = new DynamicShortcuts(this.mContext).getActionShortcutInfo(str5 + str2 + uri2.toString().hashCode(), str, intent2, iconCompat.toIcon());
            intent = actionShortcutInfo != null ? shortcutManager.createShortcutResultIntent(actionShortcutInfo) : null;
        } else {
            intent = null;
            iconCompat = null;
        }
        if (intent == null) {
            intent = new Intent();
        }
        if (iconCompat != null) {
            iconCompat.addToShortcutIntent(intent, (Drawable) null, this.mContext);
        } else {
            intent.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        }
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        intent.putExtra("android.intent.extra.shortcut.NAME", str6);
        this.mListener.onShortcutIntentCreated(uri, intent);
    }

    private Bitmap generateQuickContactIcon(Drawable drawable) {
        int i = this.mIconSize;
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int i2 = this.mIconSize;
        Rect rect = new Rect(0, 0, i2, i2);
        drawable.setBounds(rect);
        drawable.draw(canvas);
        if (BuildCompat.isAtLeastO()) {
            return createBitmap;
        }
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.mResources, createBitmap);
        create.setAntiAlias(true);
        create.setCornerRadius((float) (this.mIconSize / 2));
        int i3 = this.mIconSize;
        Bitmap createBitmap2 = Bitmap.createBitmap(i3, i3, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(createBitmap2);
        create.setBounds(rect);
        create.draw(canvas);
        canvas.setBitmap((Bitmap) null);
        return createBitmap2;
    }

    private Bitmap generatePhoneNumberIcon(Drawable drawable, int i, String str, int i2) {
        Resources resources = this.mContext.getResources();
        float f = resources.getDisplayMetrics().density;
        Drawable drawableForDensity = resources.getDrawableForDensity(i2, this.mIconDensity);
        Bitmap drawableToBitmap = BitmapUtil.drawableToBitmap(drawableForDensity, drawableForDensity.getIntrinsicHeight());
        Bitmap generateQuickContactIcon = generateQuickContactIcon(drawable);
        Canvas canvas = new Canvas(generateQuickContactIcon);
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        int i3 = this.mIconSize;
        Rect rect = new Rect(0, 0, i3, i3);
        CharSequence typeLabel = ContactsContract.CommonDataKinds.Phone.getTypeLabel(resources, i, str);
        if (!BuildCompat.isAtLeastO() && typeLabel != null) {
            TextPaint textPaint = new TextPaint(257);
            textPaint.setTextSize(resources.getDimension(R.dimen.shortcut_overlay_text_size));
            textPaint.setColor(resources.getColor(R.color.textColorIconOverlay));
            textPaint.setShadowLayer(4.0f, ContactPhotoManager.OFFSET_DEFAULT, 2.0f, resources.getColor(R.color.textColorIconOverlayShadow));
            Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
            Paint paint2 = new Paint();
            paint2.setColor(this.mOverlayTextBackgroundColor);
            paint2.setStyle(Paint.Style.FILL);
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.shortcut_overlay_text_background_padding);
            int i4 = (fontMetricsInt.descent - fontMetricsInt.ascent) + (dimensionPixelOffset * 2);
            int i5 = this.mIconSize;
            rect.set(0, i5 - i4, i5, i5);
            canvas.drawRect(rect, paint2);
            CharSequence ellipsize = TextUtils.ellipsize(typeLabel, textPaint, (float) this.mIconSize, TextUtils.TruncateAt.END);
            float measureText = textPaint.measureText(ellipsize, 0, ellipsize.length());
            int length = ellipsize.length();
            int i6 = this.mIconSize;
            float f2 = (float) ((i6 - fontMetricsInt.descent) - dimensionPixelOffset);
            canvas.drawText(ellipsize, 0, length, (((float) i6) - measureText) / 2.0f, f2, textPaint);
        }
        int width = generateQuickContactIcon.getWidth();
        if (BuildCompat.isAtLeastO()) {
            canvas.drawBitmap(drawableToBitmap, (float) ((int) (((float) this.mIconSize) - (45.0f * f))), (float) ((int) (f * 21.0f)), paint);
        } else {
            rect.set(width - ((int) (20.0f * f)), -1, width, (int) (f * 19.0f));
            canvas.drawBitmap(drawableToBitmap, (Rect) null, rect, paint);
        }
        canvas.setBitmap((Bitmap) null);
        return generateQuickContactIcon;
    }
}
