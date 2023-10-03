package com.google.android.systemui.smartspace;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.systemui.smartspace.nano.SmartspaceProto;
import java.io.ByteArrayOutputStream;

public class NewCardInfo {
    private final SmartspaceProto.SmartspaceUpdate.SmartspaceCard mCard;
    private final Intent mIntent;
    private final boolean mIsPrimary;
    private final PackageInfo mPackageInfo;
    private final long mPublishTime;

    public NewCardInfo(SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard, Intent intent, boolean z, long j, PackageInfo packageInfo) {
        this.mCard = smartspaceCard;
        this.mIsPrimary = z;
        this.mIntent = intent;
        this.mPublishTime = j;
        this.mPackageInfo = packageInfo;
    }

    public boolean isPrimary() {
        return this.mIsPrimary;
    }

    public Bitmap retrieveIcon(Context context) {
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Image image = this.mCard.icon;
        if (image == null) {
            return null;
        }
        Bitmap bitmap = (Bitmap) retrieveFromIntent(image.key, this.mIntent);
        if (bitmap != null) {
            return bitmap;
        }
        try {
            if (!TextUtils.isEmpty(image.uri)) {
                return MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(image.uri));
            }
            if (!TextUtils.isEmpty(image.gsaResourceName)) {
                Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
                shortcutIconResource.packageName = "com.google.android.googlequicksearchbox";
                shortcutIconResource.resourceName = image.gsaResourceName;
                return createIconBitmap(shortcutIconResource, context);
            }
            return null;
        } catch (Exception unused) {
            Log.e("NewCardInfo", "retrieving bitmap uri=" + image.uri + " gsaRes=" + image.gsaResourceName);
        }
    }

    public SmartspaceProto.CardWrapper toWrapper(Context context) {
        SmartspaceProto.CardWrapper cardWrapper = new SmartspaceProto.CardWrapper();
        Bitmap retrieveIcon = retrieveIcon(context);
        if (retrieveIcon != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            retrieveIcon.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            cardWrapper.icon = byteArrayOutputStream.toByteArray();
        }
        cardWrapper.card = this.mCard;
        cardWrapper.publishTime = this.mPublishTime;
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo != null) {
            cardWrapper.gsaVersionCode = packageInfo.versionCode;
            cardWrapper.gsaUpdateTime = packageInfo.lastUpdateTime;
        }
        return cardWrapper;
    }

    private static <T> T retrieveFromIntent(String str, Intent intent) {
        if (!TextUtils.isEmpty(str)) {
            return intent.getParcelableExtra(str);
        }
        return null;
    }

    static Bitmap createIconBitmap(Intent.ShortcutIconResource shortcutIconResource, Context context) {
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(shortcutIconResource.packageName);
            if (resourcesForApplication != null) {
                return BitmapFactory.decodeResource(resourcesForApplication, resourcesForApplication.getIdentifier(shortcutIconResource.resourceName, (String) null, (String) null));
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public int getUserId() {
        return this.mIntent.getIntExtra("uid", -1);
    }

    public boolean shouldDiscard() {
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard = this.mCard;
        return smartspaceCard == null || smartspaceCard.shouldDiscard;
    }
}
