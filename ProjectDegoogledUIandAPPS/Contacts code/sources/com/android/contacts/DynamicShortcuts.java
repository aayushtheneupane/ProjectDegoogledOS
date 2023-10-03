package com.android.contacts;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.util.Log;
import androidx.core.p002os.BuildCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.lettertiles.LetterTileDrawable;
import com.android.contacts.util.BitmapUtil;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.PermissionsUtil;
import com.android.contactsbind.experiments.Flags;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@TargetApi(25)
public class DynamicShortcuts {
    private static final String EXTRA_SHORTCUT_TYPE = "extraShortcutType";
    private static final int LONG_LABEL_MAX_LENGTH = 30;
    private static final int MAX_SHORTCUTS = 3;
    static final String[] PROJECTION = {"_id", "lookup", "display_name"};
    public static final String SHORTCUT_ADD_CONTACT = "shortcut-add-contact";
    private static final int SHORTCUT_TYPE_ACTION_URI = 2;
    private static final int SHORTCUT_TYPE_CONTACT_URI = 1;
    private static final int SHORTCUT_TYPE_UNKNOWN = 0;
    private static final int SHORT_LABEL_MAX_LENGTH = 12;
    private static final String TAG = "DynamicShortcuts";
    private final int mContentChangeMaxUpdateDelay;
    private final int mContentChangeMinUpdateDelay;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private int mIconSize;
    private final JobScheduler mJobScheduler;
    private int mLongLabelMaxLength;
    private int mShortLabelMaxLength;
    private final ShortcutManager mShortcutManager;

    public DynamicShortcuts(Context context) {
        this(context, context.getContentResolver(), (ShortcutManager) context.getSystemService("shortcut"), (JobScheduler) context.getSystemService("jobscheduler"));
    }

    public DynamicShortcuts(Context context, ContentResolver contentResolver, ShortcutManager shortcutManager, JobScheduler jobScheduler) {
        this.mShortLabelMaxLength = 12;
        this.mLongLabelMaxLength = 30;
        this.mContext = context;
        this.mContentResolver = contentResolver;
        this.mShortcutManager = shortcutManager;
        this.mJobScheduler = jobScheduler;
        this.mContentChangeMinUpdateDelay = Flags.getInstance().getInteger("Shortcuts__dynamic_min_content_change_update_delay_millis");
        this.mContentChangeMaxUpdateDelay = Flags.getInstance().getInteger("Shortcuts__dynamic_max_content_change_update_delay_millis");
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.shortcut_icon_size);
        if (this.mIconSize == 0) {
            this.mIconSize = activityManager.getLauncherLargeIconSize();
        }
    }

    /* access modifiers changed from: package-private */
    public void setShortLabelMaxLength(int i) {
        this.mShortLabelMaxLength = i;
    }

    /* access modifiers changed from: package-private */
    public void setLongLabelMaxLength(int i) {
        this.mLongLabelMaxLength = i;
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        if (hasRequiredPermissions()) {
            List<ShortcutInfo> strequentShortcuts = getStrequentShortcuts();
            this.mShortcutManager.setDynamicShortcuts(strequentShortcuts);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "set dynamic shortcuts " + strequentShortcuts);
            }
            updatePinned();
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePinned() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (ShortcutInfo next : this.mShortcutManager.getPinnedShortcuts()) {
            PersistableBundle extras = next.getExtras();
            if (extras != null && extras.getInt(EXTRA_SHORTCUT_TYPE, 0) == 1) {
                ShortcutInfo createShortcutForUri = createShortcutForUri(ContactsContract.Contacts.getLookupUri(extras.getLong("_id"), next.getId()));
                if (createShortcutForUri != null) {
                    arrayList.add(createShortcutForUri);
                    if (!next.isEnabled()) {
                        arrayList3.add(createShortcutForUri.getId());
                    }
                } else if (next.isEnabled()) {
                    arrayList2.add(next.getId());
                }
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "updating " + arrayList);
            Log.d(TAG, "enabling " + arrayList3);
            Log.d(TAG, "disabling " + arrayList2);
        }
        this.mShortcutManager.updateShortcuts(arrayList);
        this.mShortcutManager.enableShortcuts(arrayList3);
        this.mShortcutManager.disableShortcuts(arrayList2, this.mContext.getString(R.string.dynamic_shortcut_contact_removed_message));
    }

    private ShortcutInfo createShortcutForUri(Uri uri) {
        Cursor query = this.mContentResolver.query(uri, PROJECTION, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        try {
            if (query.moveToFirst()) {
                return createShortcutFromRow(query);
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    public List<ShortcutInfo> getStrequentShortcuts() {
        Cursor query = this.mContentResolver.query(ContactsContract.Contacts.CONTENT_STREQUENT_URI.buildUpon().appendQueryParameter("limit", String.valueOf(3)).build(), PROJECTION, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < 3) {
            try {
                if (!query.moveToNext()) {
                    break;
                }
                ShortcutInfo createShortcutFromRow = createShortcutFromRow(query);
                if (createShortcutFromRow != null) {
                    arrayList.add(createShortcutFromRow);
                    i++;
                }
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ShortcutInfo createShortcutFromRow(Cursor cursor) {
        ShortcutInfo.Builder builderForContactShortcut = builderForContactShortcut(cursor);
        if (builderForContactShortcut == null) {
            return null;
        }
        addIconForContact(cursor, builderForContactShortcut);
        return builderForContactShortcut.build();
    }

    /* access modifiers changed from: package-private */
    public ShortcutInfo.Builder builderForContactShortcut(Cursor cursor) {
        return builderForContactShortcut(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    /* access modifiers changed from: package-private */
    public ShortcutInfo.Builder builderForContactShortcut(long j, String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putLong("_id", j);
        persistableBundle.putInt(EXTRA_SHORTCUT_TYPE, 1);
        ShortcutInfo.Builder extras = new ShortcutInfo.Builder(this.mContext, str).setIntent(ImplicitIntentsUtil.getIntentForQuickContactLauncherShortcut(this.mContext, ContactsContract.Contacts.getLookupUri(j, str))).setDisabledMessage(this.mContext.getString(R.string.dynamic_shortcut_disabled_message)).setExtras(persistableBundle);
        setLabel(extras, str2);
        return extras;
    }

    /* access modifiers changed from: package-private */
    public ShortcutInfo getActionShortcutInfo(String str, String str2, Intent intent, Icon icon) {
        if (str == null || str2 == null) {
            return null;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putInt(EXTRA_SHORTCUT_TYPE, 2);
        ShortcutInfo.Builder disabledMessage = new ShortcutInfo.Builder(this.mContext, str).setIntent(intent).setIcon(icon).setExtras(persistableBundle).setDisabledMessage(this.mContext.getString(R.string.dynamic_shortcut_disabled_message));
        setLabel(disabledMessage, str2);
        return disabledMessage.build();
    }

    public ShortcutInfo getQuickContactShortcutInfo(long j, String str, String str2) {
        ShortcutInfo.Builder builderForContactShortcut = builderForContactShortcut(j, str, str2);
        if (builderForContactShortcut == null) {
            return null;
        }
        addIconForContact(j, str, str2, builderForContactShortcut);
        return builderForContactShortcut.build();
    }

    private void setLabel(ShortcutInfo.Builder builder, String str) {
        if (str.length() < this.mLongLabelMaxLength) {
            builder.setLongLabel(str);
        } else {
            builder.setLongLabel(str.substring(0, this.mLongLabelMaxLength - 1).trim() + "…");
        }
        if (str.length() < this.mShortLabelMaxLength) {
            builder.setShortLabel(str);
            return;
        }
        builder.setShortLabel(str.substring(0, this.mShortLabelMaxLength - 1).trim() + "…");
    }

    private void addIconForContact(Cursor cursor, ShortcutInfo.Builder builder) {
        addIconForContact(cursor.getLong(0), cursor.getString(1), cursor.getString(2), builder);
    }

    private void addIconForContact(long j, String str, String str2, ShortcutInfo.Builder builder) {
        Icon icon;
        Bitmap contactPhoto = getContactPhoto(j);
        if (contactPhoto == null) {
            contactPhoto = getFallbackAvatar(str2, str);
        }
        if (BuildCompat.isAtLeastO()) {
            icon = Icon.createWithAdaptiveBitmap(contactPhoto);
        } else {
            icon = Icon.createWithBitmap(contactPhoto);
        }
        builder.setIcon(icon);
    }

    private Bitmap getContactPhoto(long j) {
        InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.mContext.getContentResolver(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, j), true);
        if (openContactPhotoInputStream == null) {
            return null;
        }
        try {
            Bitmap decodeStreamForShortcut = decodeStreamForShortcut(openContactPhotoInputStream);
            openContactPhotoInputStream.close();
            try {
                openContactPhotoInputStream.close();
            } catch (IOException unused) {
            }
            return decodeStreamForShortcut;
        } catch (IOException e) {
            Log.e(TAG, "Failed to decode contact photo for shortcut. ID=" + j, e);
            try {
                openContactPhotoInputStream.close();
            } catch (IOException unused2) {
            }
            return null;
        } catch (Throwable th) {
            try {
                openContactPhotoInputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    private Bitmap decodeStreamForShortcut(InputStream inputStream) throws IOException {
        BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(inputStream, false);
        int width = newInstance.getWidth();
        int height = newInstance.getHeight();
        int iconMaxWidth = this.mShortcutManager.getIconMaxWidth();
        int iconMaxHeight = this.mShortcutManager.getIconMaxHeight();
        int min = Math.min(BitmapUtil.findOptimalSampleSize(width, this.mIconSize), BitmapUtil.findOptimalSampleSize(height, this.mIconSize));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = min;
        int i = options.inSampleSize;
        int i2 = width / i;
        int i3 = height / i;
        int min2 = Math.min(Math.min(i2, iconMaxWidth), Math.min(i3, iconMaxHeight));
        int i4 = options.inSampleSize;
        int i5 = ((i2 - min2) * i4) / 2;
        int i6 = ((i3 - min2) * i4) / 2;
        Bitmap decodeRegion = newInstance.decodeRegion(new Rect(i5, i6, width - i5, height - i6), options);
        newInstance.recycle();
        return !BuildCompat.isAtLeastO() ? BitmapUtil.getRoundedBitmap(decodeRegion, min2, min2) : decodeRegion;
    }

    private Bitmap getFallbackAvatar(String str, String str2) {
        ContactPhotoManager.DefaultImageRequest defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(str, str2, !BuildCompat.isAtLeastO());
        if (BuildCompat.isAtLeastO()) {
            defaultImageRequest.scale = LetterTileDrawable.getAdaptiveIconScale();
        }
        Drawable defaultAvatarDrawableForContact = ContactPhotoManager.getDefaultAvatarDrawableForContact(this.mContext.getResources(), true, defaultImageRequest);
        int i = this.mIconSize;
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        defaultAvatarDrawableForContact.setVisible(true, true);
        Canvas canvas = new Canvas(createBitmap);
        int i2 = this.mIconSize;
        defaultAvatarDrawableForContact.setBounds(0, 0, i2, i2);
        defaultAvatarDrawableForContact.draw(canvas);
        return createBitmap;
    }

    /* access modifiers changed from: package-private */
    public void handleFlagDisabled() {
        removeAllShortcuts();
        this.mJobScheduler.cancel(1);
    }

    private void removeAllShortcuts() {
        this.mShortcutManager.removeAllDynamicShortcuts();
        List<ShortcutInfo> pinnedShortcuts = this.mShortcutManager.getPinnedShortcuts();
        ArrayList arrayList = new ArrayList(pinnedShortcuts.size());
        for (ShortcutInfo id : pinnedShortcuts) {
            arrayList.add(id.getId());
        }
        this.mShortcutManager.disableShortcuts(arrayList, this.mContext.getString(R.string.dynamic_shortcut_disabled_message));
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "DynamicShortcuts have been removed.");
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleUpdateJob() {
        this.mJobScheduler.schedule(new JobInfo.Builder(1, new ComponentName(this.mContext, ContactsJobService.class)).addTriggerContentUri(new JobInfo.TriggerContentUri(ContactsContract.AUTHORITY_URI, 1)).setTriggerContentUpdateDelay((long) this.mContentChangeMinUpdateDelay).setTriggerContentMaxDelay((long) this.mContentChangeMaxUpdateDelay).build());
    }

    /* access modifiers changed from: package-private */
    public void updateInBackground() {
        new ShortcutUpdateTask(this).execute(new Void[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a1, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void initialize(android.content.Context r8) {
        /*
            java.lang.Class<com.android.contacts.DynamicShortcuts> r0 = com.android.contacts.DynamicShortcuts.class
            monitor-enter(r0)
            java.lang.String r1 = "DynamicShortcuts"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch:{ all -> 0x00a2 }
            r2 = 0
            if (r1 == 0) goto L_0x0063
            com.android.contactsbind.experiments.Flags r1 = com.android.contactsbind.experiments.Flags.getInstance()     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = "DynamicShortcuts"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a2 }
            r4.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "DyanmicShortcuts.initialize\nVERSION >= N_MR1? "
            r4.append(r5)     // Catch:{ all -> 0x00a2 }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00a2 }
            r6 = 25
            r7 = 1
            if (r5 < r6) goto L_0x0026
            r5 = 1
            goto L_0x0027
        L_0x0026:
            r5 = 0
        L_0x0027:
            r4.append(r5)     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "\nisJobScheduled? "
            r4.append(r5)     // Catch:{ all -> 0x00a2 }
            boolean r5 = com.android.contacts.compat.CompatUtils.isLauncherShortcutCompatible()     // Catch:{ all -> 0x00a2 }
            if (r5 == 0) goto L_0x003c
            boolean r5 = isJobScheduled(r8)     // Catch:{ all -> 0x00a2 }
            if (r5 == 0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r7 = 0
        L_0x003d:
            r4.append(r7)     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "\nminDelay="
            r4.append(r5)     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "Shortcuts__dynamic_min_content_change_update_delay_millis"
            int r5 = r1.getInteger(r5)     // Catch:{ all -> 0x00a2 }
            r4.append(r5)     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "\nmaxDelay="
            r4.append(r5)     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "Shortcuts__dynamic_max_content_change_update_delay_millis"
            int r1 = r1.getInteger(r5)     // Catch:{ all -> 0x00a2 }
            r4.append(r1)     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00a2 }
            android.util.Log.d(r3, r1)     // Catch:{ all -> 0x00a2 }
        L_0x0063:
            boolean r1 = com.android.contacts.compat.CompatUtils.isLauncherShortcutCompatible()     // Catch:{ all -> 0x00a2 }
            if (r1 != 0) goto L_0x006b
            monitor-exit(r0)
            return
        L_0x006b:
            com.android.contacts.DynamicShortcuts r1 = new com.android.contacts.DynamicShortcuts     // Catch:{ all -> 0x00a2 }
            r1.<init>(r8)     // Catch:{ all -> 0x00a2 }
            boolean r3 = r1.hasRequiredPermissions()     // Catch:{ all -> 0x00a2 }
            if (r3 != 0) goto L_0x0090
            android.content.IntentFilter r8 = new android.content.IntentFilter     // Catch:{ all -> 0x00a2 }
            r8.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = "broadcastPermissionsGranted"
            r8.addAction(r2)     // Catch:{ all -> 0x00a2 }
            android.content.Context r1 = r1.mContext     // Catch:{ all -> 0x00a2 }
            androidx.localbroadcastmanager.content.LocalBroadcastManager r1 = androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(r1)     // Catch:{ all -> 0x00a2 }
            com.android.contacts.DynamicShortcuts$PermissionsGrantedReceiver r2 = new com.android.contacts.DynamicShortcuts$PermissionsGrantedReceiver     // Catch:{ all -> 0x00a2 }
            r3 = 0
            r2.<init>()     // Catch:{ all -> 0x00a2 }
            r1.registerReceiver(r2, r8)     // Catch:{ all -> 0x00a2 }
            goto L_0x00a0
        L_0x0090:
            boolean r8 = isJobScheduled(r8)     // Catch:{ all -> 0x00a2 }
            if (r8 != 0) goto L_0x00a0
            com.android.contacts.DynamicShortcuts$ShortcutUpdateTask r8 = new com.android.contacts.DynamicShortcuts$ShortcutUpdateTask     // Catch:{ all -> 0x00a2 }
            r8.<init>(r1)     // Catch:{ all -> 0x00a2 }
            java.lang.Void[] r1 = new java.lang.Void[r2]     // Catch:{ all -> 0x00a2 }
            r8.execute(r1)     // Catch:{ all -> 0x00a2 }
        L_0x00a0:
            monitor-exit(r0)
            return
        L_0x00a2:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.DynamicShortcuts.initialize(android.content.Context):void");
    }

    public static void reset(Context context) {
        ((JobScheduler) context.getSystemService("jobscheduler")).cancel(1);
        if (CompatUtils.isLauncherShortcutCompatible()) {
            new DynamicShortcuts(context).removeAllShortcuts();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasRequiredPermissions() {
        return PermissionsUtil.hasContactsPermissions(this.mContext);
    }

    public static void updateFromJob(final JobService jobService, final JobParameters jobParameters) {
        new ShortcutUpdateTask(new DynamicShortcuts(jobService)) {
            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                super.onPostExecute(voidR);
                jobService.jobFinished(jobParameters, false);
            }
        }.execute(new Void[0]);
    }

    public static boolean isJobScheduled(Context context) {
        if (((JobScheduler) context.getSystemService("jobscheduler")).getPendingJob(1) != null) {
            return true;
        }
        return false;
    }

    public static void reportShortcutUsed(Context context, String str) {
        if (CompatUtils.isLauncherShortcutCompatible() && str != null) {
            ((ShortcutManager) context.getSystemService("shortcut")).reportShortcutUsed(str);
        }
    }

    private static class ShortcutUpdateTask extends AsyncTask<Void, Void, Void> {
        private DynamicShortcuts mDynamicShortcuts;

        public ShortcutUpdateTask(DynamicShortcuts dynamicShortcuts) {
            this.mDynamicShortcuts = dynamicShortcuts;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            this.mDynamicShortcuts.refresh();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            if (Log.isLoggable(DynamicShortcuts.TAG, 3)) {
                Log.d(DynamicShortcuts.TAG, "ShorcutUpdateTask.onPostExecute");
            }
            this.mDynamicShortcuts.scheduleUpdateJob();
        }
    }

    private static class PermissionsGrantedReceiver extends BroadcastReceiver {
        private PermissionsGrantedReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
            DynamicShortcuts.initialize(context);
        }
    }
}
