package com.android.systemui.screenshot;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.Slog;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.SystemUI;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.screenshot.GlobalScreenshot;
import com.android.systemui.util.NotificationChannels;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/* compiled from: GlobalScreenshot */
class SaveImageInBackgroundTask extends AsyncTask<Void, Void, Void> {
    private final String mImageFileName;
    private final int mImageHeight;
    private final long mImageTime;
    private final int mImageWidth;
    private final Notification.Builder mNotificationBuilder;
    private final NotificationManager mNotificationManager;
    private final Notification.BigPictureStyle mNotificationStyle;
    private final SaveImageInBackgroundData mParams;
    private final Notification.Builder mPublicNotificationBuilder;
    private final Random mRandom = new Random();
    private final String mScreenshotId;
    private final boolean mSmartActionsEnabled;
    private final ScreenshotNotificationSmartActionsProvider mSmartActionsProvider;

    private static CharSequence getRunningActivityName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(ActivityManager.class)).getRunningTasks(1);
        if (runningTasks == null || runningTasks.isEmpty()) {
            return null;
        }
        try {
            return packageManager.getApplicationLabel(packageManager.getActivityInfo(runningTasks.get(0).topActivity, 0).applicationInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    SaveImageInBackgroundTask(Context context, SaveImageInBackgroundData saveImageInBackgroundData, NotificationManager notificationManager) {
        Context context2 = context;
        SaveImageInBackgroundData saveImageInBackgroundData2 = saveImageInBackgroundData;
        Resources resources = context.getResources();
        this.mParams = saveImageInBackgroundData2;
        this.mImageTime = System.currentTimeMillis();
        String format = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(this.mImageTime));
        CharSequence runningActivityName = getRunningActivityName(context);
        if (((KeyguardManager) context2.getSystemService(KeyguardManager.class)).isKeyguardLocked() || runningActivityName == null) {
            this.mImageFileName = String.format("Screenshot_%s.png", new Object[]{format});
        } else {
            String charSequence = runningActivityName.toString();
            try {
                charSequence = new String(charSequence.getBytes("ISO-8859-15"), "UTF-8").replaceAll("[\u001a]+", "");
            } catch (UnsupportedEncodingException unused) {
            }
            this.mImageFileName = String.format("Screenshot_%s_%s.png", new Object[]{format, charSequence.replaceAll("[\\\\/:*?\"<>|\\s]+", "_")});
        }
        this.mScreenshotId = String.format("Screenshot_%s", new Object[]{UUID.randomUUID()});
        this.mSmartActionsEnabled = DeviceConfig.getBoolean("systemui", "enable_screenshot_notification_smart_actions", false);
        if (this.mSmartActionsEnabled) {
            this.mSmartActionsProvider = SystemUIFactory.getInstance().createScreenshotNotificationSmartActionsProvider(context2, AsyncTask.THREAD_POOL_EXECUTOR, new Handler());
        } else {
            this.mSmartActionsProvider = new ScreenshotNotificationSmartActionsProvider();
        }
        this.mImageWidth = saveImageInBackgroundData2.image.getWidth();
        this.mImageHeight = saveImageInBackgroundData2.image.getHeight();
        int i = saveImageInBackgroundData2.iconSize;
        int i2 = saveImageInBackgroundData2.previewWidth;
        int i3 = saveImageInBackgroundData2.previewheight;
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.25f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((i2 - this.mImageWidth) / 2), (float) ((i3 - this.mImageHeight) / 2));
        Paint paint2 = paint;
        Bitmap generateAdjustedHwBitmap = generateAdjustedHwBitmap(saveImageInBackgroundData2.image, i2, i3, matrix, paint2, 1090519039);
        float f = (float) i;
        float min = f / ((float) Math.min(this.mImageWidth, this.mImageHeight));
        matrix.setScale(min, min);
        matrix.postTranslate((f - (((float) this.mImageWidth) * min)) / 2.0f, (f - (min * ((float) this.mImageHeight))) / 2.0f);
        Bitmap generateAdjustedHwBitmap2 = generateAdjustedHwBitmap(saveImageInBackgroundData2.image, i, i, matrix, paint2, 1090519039);
        this.mNotificationManager = notificationManager;
        long currentTimeMillis = System.currentTimeMillis();
        this.mNotificationStyle = new Notification.BigPictureStyle().bigPicture(generateAdjustedHwBitmap.createAshmemBitmap());
        this.mPublicNotificationBuilder = new Notification.Builder(context2, NotificationChannels.SCREENSHOTS_HEADSUP).setContentTitle(resources.getString(C1784R$string.screenshot_saving_title)).setSmallIcon(C1776R$drawable.stat_notify_image).setCategory("progress").setWhen(currentTimeMillis).setShowWhen(true).setColor(resources.getColor(17170460));
        SystemUI.overrideNotificationAppName(context2, this.mPublicNotificationBuilder, true);
        this.mNotificationBuilder = new Notification.Builder(context2, NotificationChannels.SCREENSHOTS_HEADSUP).setContentTitle(resources.getString(C1784R$string.screenshot_saving_title)).setSmallIcon(C1776R$drawable.stat_notify_image).setWhen(currentTimeMillis).setShowWhen(true).setColor(resources.getColor(17170460)).setStyle(this.mNotificationStyle).setPublicVersion(this.mPublicNotificationBuilder.build());
        this.mNotificationBuilder.setFlag(32, true);
        SystemUI.overrideNotificationAppName(context2, this.mNotificationBuilder, true);
        this.mNotificationManager.notify(1, this.mNotificationBuilder.build());
        this.mNotificationBuilder.setLargeIcon(generateAdjustedHwBitmap2.createAshmemBitmap());
        this.mNotificationStyle.bigLargeIcon((Bitmap) null);
    }

    private List<Notification.Action> buildSmartActions(List<Notification.Action> list, Context context) {
        ArrayList arrayList = new ArrayList();
        for (Notification.Action next : list) {
            Bundle extras = next.getExtras();
            String string = extras.getString("action_type", "Smart Action");
            Intent putExtra = new Intent(context, GlobalScreenshot.SmartActionsReceiver.class).putExtra("android:screenshot_action_intent", next.actionIntent);
            addIntentExtras(this.mScreenshotId, putExtra, string, this.mSmartActionsEnabled);
            arrayList.add(new Notification.Action.Builder(next.getIcon(), next.title, PendingIntent.getBroadcast(context, this.mRandom.nextInt(), putExtra, 268435456)).setContextual(true).addExtras(extras).build());
        }
        return arrayList;
    }

    private static void addIntentExtras(String str, Intent intent, String str2, boolean z) {
        intent.putExtra("android:screenshot_action_type", str2).putExtra("android:screenshot_id", str).putExtra("android:smart_actions_enabled", z);
    }

    private int getUserHandleOfForegroundApplication(Context context) {
        try {
            return ActivityTaskManager.getService().getLastResumedActivityUserId();
        } catch (RemoteException e) {
            Slog.w("SaveImageInBackgroundTask", "getUserHandleOfForegroundApplication: ", e);
            return context.getUserId();
        }
    }

    private boolean isManagedProfile(Context context) {
        return UserManager.get(context).getUserInfo(getUserHandleOfForegroundApplication(context)).isManagedProfile();
    }

    private Bitmap generateAdjustedHwBitmap(Bitmap bitmap, int i, int i2, Matrix matrix, Paint paint, int i3) {
        Picture picture = new Picture();
        Canvas beginRecording = picture.beginRecording(i, i2);
        beginRecording.drawColor(i3);
        beginRecording.drawBitmap(bitmap, matrix, paint);
        picture.endRecording();
        return Bitmap.createBitmap(picture);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        if (r4 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0084, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Void doInBackground(java.lang.Void... r10) {
        /*
            r9 = this;
            boolean r10 = r9.isCancelled()
            r0 = 0
            if (r10 == 0) goto L_0x0008
            return r0
        L_0x0008:
            r10 = -2
            android.os.Process.setThreadPriority(r10)
            com.android.systemui.screenshot.SaveImageInBackgroundData r10 = r9.mParams
            android.content.Context r2 = r10.context
            android.graphics.Bitmap r10 = r10.image
            android.content.res.Resources r3 = r2.getResources()
            java.lang.String r1 = r9.mScreenshotId     // Catch:{ Exception -> 0x0090 }
            com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider r4 = r9.mSmartActionsProvider     // Catch:{ Exception -> 0x0090 }
            boolean r5 = r9.mSmartActionsEnabled     // Catch:{ Exception -> 0x0090 }
            boolean r6 = r9.isManagedProfile(r2)     // Catch:{ Exception -> 0x0090 }
            java.util.concurrent.CompletableFuture r5 = com.android.systemui.screenshot.GlobalScreenshot.getSmartActionsFuture(r1, r10, r4, r5, r6)     // Catch:{ Exception -> 0x0090 }
            android.provider.MediaStore$PendingParams r1 = new android.provider.MediaStore$PendingParams     // Catch:{ Exception -> 0x0090 }
            android.net.Uri r4 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch:{ Exception -> 0x0090 }
            java.lang.String r6 = r9.mImageFileName     // Catch:{ Exception -> 0x0090 }
            java.lang.String r7 = "image/png"
            r1.<init>(r4, r6, r7)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r4 = android.os.Environment.DIRECTORY_PICTURES     // Catch:{ Exception -> 0x0090 }
            r1.setPrimaryDirectory(r4)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r4 = android.os.Environment.DIRECTORY_SCREENSHOTS     // Catch:{ Exception -> 0x0090 }
            r1.setSecondaryDirectory(r4)     // Catch:{ Exception -> 0x0090 }
            android.net.Uri r7 = android.provider.MediaStore.createPending(r2, r1)     // Catch:{ Exception -> 0x0090 }
            android.provider.MediaStore$PendingSession r1 = android.provider.MediaStore.openPending(r2, r7)     // Catch:{ Exception -> 0x0090 }
            java.io.OutputStream r4 = r1.openOutputStream()     // Catch:{ Exception -> 0x0087 }
            android.graphics.Bitmap$CompressFormat r6 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0077 }
            r8 = 100
            boolean r6 = r10.compress(r6, r8, r4)     // Catch:{ all -> 0x0077 }
            if (r6 == 0) goto L_0x006f
            if (r4 == 0) goto L_0x0054
            r4.close()     // Catch:{ Exception -> 0x0087 }
        L_0x0054:
            r1.publish()     // Catch:{ Exception -> 0x0087 }
            libcore.io.IoUtils.closeQuietly(r1)     // Catch:{ Exception -> 0x0090 }
            android.app.Notification$Builder r6 = r9.mNotificationBuilder     // Catch:{ Exception -> 0x0090 }
            r1 = r9
            r4 = r7
            r1.populateNotificationActions(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0090 }
            com.android.systemui.screenshot.SaveImageInBackgroundData r1 = r9.mParams     // Catch:{ Exception -> 0x0090 }
            r1.imageUri = r7     // Catch:{ Exception -> 0x0090 }
            com.android.systemui.screenshot.SaveImageInBackgroundData r1 = r9.mParams     // Catch:{ Exception -> 0x0090 }
            r1.image = r0     // Catch:{ Exception -> 0x0090 }
            com.android.systemui.screenshot.SaveImageInBackgroundData r1 = r9.mParams     // Catch:{ Exception -> 0x0090 }
            r2 = 0
            r1.errorMsgResId = r2     // Catch:{ Exception -> 0x0090 }
            goto L_0x00a3
        L_0x006f:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = "Failed to compress"
            r2.<init>(r3)     // Catch:{ all -> 0x0077 }
            throw r2     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r3 = move-exception
            if (r4 == 0) goto L_0x0084
            r4.close()     // Catch:{ all -> 0x0080 }
            goto L_0x0084
        L_0x0080:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ Exception -> 0x0087 }
        L_0x0084:
            throw r3     // Catch:{ Exception -> 0x0087 }
        L_0x0085:
            r2 = move-exception
            goto L_0x008c
        L_0x0087:
            r2 = move-exception
            r1.abandon()     // Catch:{ all -> 0x0085 }
            throw r2     // Catch:{ all -> 0x0085 }
        L_0x008c:
            libcore.io.IoUtils.closeQuietly(r1)     // Catch:{ Exception -> 0x0090 }
            throw r2     // Catch:{ Exception -> 0x0090 }
        L_0x0090:
            r1 = move-exception
            java.lang.String r2 = "SaveImageInBackgroundTask"
            java.lang.String r3 = "unable to save screenshot"
            android.util.Slog.e(r2, r3, r1)
            com.android.systemui.screenshot.SaveImageInBackgroundData r1 = r9.mParams
            r1.clearImage()
            com.android.systemui.screenshot.SaveImageInBackgroundData r9 = r9.mParams
            int r1 = com.android.systemui.C1784R$string.screenshot_failed_to_save_text
            r9.errorMsgResId = r1
        L_0x00a3:
            if (r10 == 0) goto L_0x00a8
            r10.recycle()
        L_0x00a8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.SaveImageInBackgroundTask.doInBackground(java.lang.Void[]):java.lang.Void");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void populateNotificationActions(Context context, Resources resources, Uri uri, CompletableFuture<List<Notification.Action>> completableFuture, Notification.Builder builder) {
        Context context2 = context;
        Resources resources2 = resources;
        Uri uri2 = uri;
        Notification.Builder builder2 = builder;
        String format = String.format("Screenshot (%s)", new Object[]{DateFormat.getDateTimeInstance().format(new Date(this.mImageTime))});
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/png");
        intent.putExtra("android.intent.extra.STREAM", uri2);
        intent.setClipData(new ClipData(new ClipDescription("content", new String[]{"text/plain"}), new ClipData.Item(uri2)));
        intent.putExtra("android.intent.extra.SUBJECT", format);
        intent.addFlags(1);
        builder2.addAction(new Notification.Action.Builder(C1776R$drawable.ic_screenshot_share, resources2.getString(17041087), PendingIntent.getBroadcastAsUser(context2, 0, new Intent(context2, GlobalScreenshot.ActionProxyReceiver.class).putExtra("android:screenshot_action_intent", Intent.createChooser(intent, (CharSequence) null, PendingIntent.getBroadcast(context2, 0, new Intent(context2, GlobalScreenshot.TargetChosenReceiver.class), 1342177280).getIntentSender()).addFlags(268468224).addFlags(1)).putExtra("android:screenshot_disallow_enter_pip", true).putExtra("android:screenshot_id", this.mScreenshotId).putExtra("android:smart_actions_enabled", this.mSmartActionsEnabled), 268435456, UserHandle.SYSTEM)).build());
        String string = context2.getString(C1784R$string.config_screenshotEditor);
        Intent intent2 = new Intent("android.intent.action.EDIT");
        if (!TextUtils.isEmpty(string)) {
            intent2.setComponent(ComponentName.unflattenFromString(string));
        }
        intent2.setType("image/png");
        intent2.setData(uri2);
        intent2.addFlags(1);
        intent2.addFlags(2);
        builder2.addAction(new Notification.Action.Builder(C1776R$drawable.ic_screenshot_edit, resources2.getString(17041049), PendingIntent.getBroadcastAsUser(context2, 1, new Intent(context2, GlobalScreenshot.ActionProxyReceiver.class).putExtra("android:screenshot_action_intent", intent2).putExtra("android:screenshot_cancel_notification", intent2.getComponent() != null).putExtra("android:screenshot_id", this.mScreenshotId).putExtra("android:smart_actions_enabled", this.mSmartActionsEnabled), 268435456, UserHandle.SYSTEM)).build());
        builder2.addAction(new Notification.Action.Builder(C1776R$drawable.ic_screenshot_delete, resources2.getString(17039899), PendingIntent.getBroadcast(context2, 0, new Intent(context2, GlobalScreenshot.DeleteScreenshotReceiver.class).putExtra("android:screenshot_uri_id", uri.toString()).putExtra("android:screenshot_id", this.mScreenshotId).putExtra("android:smart_actions_enabled", this.mSmartActionsEnabled), 1342177280)).build());
        if (this.mSmartActionsEnabled) {
            for (Notification.Action addAction : buildSmartActions(GlobalScreenshot.getSmartActions(this.mScreenshotId, completableFuture, DeviceConfig.getInt("systemui", "screenshot_notification_smart_actions_timeout_ms", 1000), this.mSmartActionsProvider), context2)) {
                builder2.addAction(addAction);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        SaveImageInBackgroundData saveImageInBackgroundData = this.mParams;
        int i = saveImageInBackgroundData.errorMsgResId;
        if (i != 0) {
            GlobalScreenshot.notifyScreenshotError(saveImageInBackgroundData.context, this.mNotificationManager, i);
        } else {
            Context context = saveImageInBackgroundData.context;
            Resources resources = context.getResources();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(this.mParams.imageUri, "image/png");
            intent.setFlags(268435457);
            long currentTimeMillis = System.currentTimeMillis();
            this.mPublicNotificationBuilder.setContentTitle(resources.getString(C1784R$string.screenshot_saved_title)).setContentText(resources.getString(C1784R$string.screenshot_saved_text)).setContentIntent(PendingIntent.getActivity(this.mParams.context, 0, intent, 0)).setWhen(currentTimeMillis).setAutoCancel(true).setColor(context.getColor(17170460));
            this.mNotificationBuilder.setContentTitle(resources.getString(C1784R$string.screenshot_saved_title)).setContentText(resources.getString(C1784R$string.screenshot_saved_text)).setContentIntent(PendingIntent.getActivity(this.mParams.context, 0, intent, 0)).setWhen(currentTimeMillis).setAutoCancel(true).setColor(context.getColor(17170460)).setPublicVersion(this.mPublicNotificationBuilder.build()).setFlag(32, false);
            this.mNotificationManager.notify(1, this.mNotificationBuilder.build());
        }
        SaveImageInBackgroundData saveImageInBackgroundData2 = this.mParams;
        saveImageInBackgroundData2.finisher.accept(saveImageInBackgroundData2.imageUri);
        this.mParams.clearContext();
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Void voidR) {
        this.mParams.finisher.accept((Object) null);
        this.mParams.clearImage();
        this.mParams.clearContext();
        this.mNotificationManager.cancel(1);
    }
}
