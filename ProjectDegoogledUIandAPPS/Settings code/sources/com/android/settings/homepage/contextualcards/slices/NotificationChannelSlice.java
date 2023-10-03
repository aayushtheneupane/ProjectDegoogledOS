package com.android.settings.homepage.contextualcards.slices;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.SubSettings;
import com.android.settings.Utils;
import com.android.settings.applications.AppAndNotificationDashboardFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.AppNotificationSettings;
import com.android.settings.notification.ChannelNotificationSettings;
import com.android.settings.notification.NotificationBackend;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBroadcastReceiver;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotificationChannelSlice implements CustomSliceable {
    private static final Comparator<NotificationChannelState> CHANNEL_STATE_COMPARATOR = $$Lambda$NotificationChannelSlice$XUnbmGVSTgIKGjYAyN0s0g7vw5Q.INSTANCE;
    @VisibleForTesting
    static final int DEFAULT_EXPANDED_ROW_COUNT = 3;
    @VisibleForTesting
    static final long DURATION_END_DAYS = TimeUnit.DAYS.toMillis(3);
    @VisibleForTesting
    static final long DURATION_START_DAYS = TimeUnit.DAYS.toMillis(7);
    @VisibleForTesting
    static final int MIN_NOTIFICATION_SENT_COUNT = 10;
    private NotificationBackend.AppRow mAppRow;
    protected final Context mContext;
    @VisibleForTesting
    NotificationBackend mNotificationBackend = new NotificationBackend();
    private String mPackageName;
    private int mUid;

    /* access modifiers changed from: protected */
    public boolean isUserInteracted(String str) {
        return false;
    }

    static /* synthetic */ int lambda$static$0(NotificationChannelState notificationChannelState, NotificationChannelState notificationChannelState2) {
        NotificationBackend.NotificationsSentState notificationsSentState = notificationChannelState.getNotificationsSentState();
        int i = notificationChannelState2.getNotificationsSentState().avgSentWeekly;
        int i2 = notificationsSentState.avgSentWeekly;
        if (i != i2) {
            return i - i2;
        }
        NotificationChannel notificationChannel = notificationChannelState.getNotificationChannel();
        NotificationChannel notificationChannel2 = notificationChannelState2.getNotificationChannel();
        if (TextUtils.equals(notificationChannel.getId(), "miscellaneous")) {
            return 1;
        }
        if (TextUtils.equals(notificationChannel2.getId(), "miscellaneous")) {
            return -1;
        }
        return notificationChannel.getId().compareTo(notificationChannel2.getId());
    }

    public NotificationChannelSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        ListBuilder listBuilder = new ListBuilder(this.mContext, getUri(), -1);
        listBuilder.setAccentColor(-1);
        this.mPackageName = getEligibleNotificationsPackage(getRecentlyInstalledPackages());
        String str = this.mPackageName;
        if (str == null) {
            listBuilder.setHeader(getNoSuggestedAppHeader());
            listBuilder.setIsError(true);
            return listBuilder.build();
        }
        this.mUid = getApplicationUid(str);
        IconCompat applicationIcon = getApplicationIcon(this.mPackageName);
        Context context = this.mContext;
        String string = context.getString(C1715R.string.manage_app_notification, new Object[]{Utils.getApplicationLabel(context, this.mPackageName)});
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitleItem(applicationIcon, 0);
        rowBuilder.setTitle(string);
        rowBuilder.setSubtitle(getSubTitle(this.mPackageName, this.mUid));
        rowBuilder.setPrimaryAction(getPrimarySliceAction(applicationIcon, string, getIntent()));
        listBuilder.addRow(rowBuilder);
        for (ListBuilder.RowBuilder addRow : getNotificationChannelRows(applicationIcon)) {
            listBuilder.addRow(addRow);
        }
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.NOTIFICATION_CHANNEL_SLICE_URI;
    }

    public void onNotifyChange(Intent intent) {
        int i = 0;
        boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", false);
        String stringExtra = intent.getStringExtra("package_name");
        int intExtra = intent.getIntExtra("package_uid", -1);
        NotificationChannel channel = this.mNotificationBackend.getChannel(stringExtra, intExtra, intent.getStringExtra("channel_id"));
        if (booleanExtra) {
            i = 2;
        }
        channel.setImportance(i);
        channel.lockFields(4);
        this.mNotificationBackend.updateChannel(stringExtra, intExtra, channel);
    }

    public Intent getIntent() {
        Bundle bundle = new Bundle();
        bundle.putString("package", this.mPackageName);
        bundle.putInt("uid", this.mUid);
        return new SubSettingLauncher(this.mContext).setDestination(AppNotificationSettings.class.getName()).setTitleRes(C1715R.string.notifications_title).setArguments(bundle).setSourceMetricsCategory(1401).toIntent();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public IconCompat getApplicationIcon(String str) {
        try {
            return Utils.createIconWithDrawable(this.mContext.getPackageManager().getApplicationIcon(str));
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("NotifChannelSlice", "No such package to get application icon.");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int getApplicationUid(String str) {
        return ApplicationsState.getInstance((Application) this.mContext.getApplicationContext()).getEntry(str, UserHandle.myUserId()).info.uid;
    }

    private SliceAction buildRowSliceAction(NotificationChannel notificationChannel, IconCompat iconCompat) {
        Bundle bundle = new Bundle();
        bundle.putInt("uid", this.mUid);
        bundle.putString("package", this.mPackageName);
        bundle.putString("android.provider.extra.CHANNEL_ID", notificationChannel.getId());
        return SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, notificationChannel.hashCode(), new SubSettingLauncher(this.mContext).setDestination(ChannelNotificationSettings.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.notification_channel_title).setSourceMetricsCategory(1401).toIntent(), 0), iconCompat, 0, notificationChannel.getName());
    }

    private ListBuilder.HeaderBuilder getNoSuggestedAppHeader() {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_homepage_apps);
        String string = this.mContext.getString(C1715R.string.no_suggested_app);
        SliceAction primarySliceAction = getPrimarySliceAction(createWithResource, string, getAppAndNotificationPageIntent());
        ListBuilder.HeaderBuilder headerBuilder = new ListBuilder.HeaderBuilder();
        headerBuilder.setTitle(string);
        headerBuilder.setPrimaryAction(primarySliceAction);
        return headerBuilder;
    }

    private List<ListBuilder.RowBuilder> getNotificationChannelRows(IconCompat iconCompat) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel next : getDisplayableChannels(this.mAppRow)) {
            ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
            rowBuilder.setTitle(next.getName());
            boolean z = false;
            rowBuilder.setSubtitle(NotificationBackend.getSentSummary(this.mContext, this.mAppRow.sentByChannel.get(next.getId()), false));
            rowBuilder.setPrimaryAction(buildRowSliceAction(next, iconCompat));
            PendingIntent toggleIntent = getToggleIntent(next.getId());
            if (next.getImportance() != 0) {
                z = true;
            }
            rowBuilder.addEndItem(SliceAction.createToggle(toggleIntent, (CharSequence) null, z));
            arrayList.add(rowBuilder);
        }
        return arrayList;
    }

    private PendingIntent getToggleIntent(String str) {
        Intent putExtra = new Intent(getUri().toString()).setClass(this.mContext, SliceBroadcastReceiver.class).putExtra("package_name", this.mPackageName).putExtra("package_uid", this.mUid).putExtra("channel_id", str);
        return PendingIntent.getBroadcast(this.mContext, putExtra.hashCode(), putExtra, 0);
    }

    private List<PackageInfo> getRecentlyInstalledPackages() {
        long currentTimeMillis = System.currentTimeMillis() - DURATION_START_DAYS;
        long currentTimeMillis2 = System.currentTimeMillis() - DURATION_END_DAYS;
        ArrayList arrayList = new ArrayList();
        for (PackageInfo next : this.mContext.getPackageManager().getInstalledPackages(0)) {
            if (!next.applicationInfo.isSystemApp() && !isUserInteracted(next.packageName)) {
                long j = next.firstInstallTime;
                if (j >= currentTimeMillis && j <= currentTimeMillis2) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    private SliceAction getPrimarySliceAction(IconCompat iconCompat, CharSequence charSequence, Intent intent) {
        return SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, intent.hashCode(), intent, 0), iconCompat, 0, charSequence);
    }

    private List<NotificationChannel> getDisplayableChannels(NotificationBackend.AppRow appRow) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : (List) this.mNotificationBackend.getGroups(appRow.pkg, appRow.uid).getList().stream().flatMap(new Function(appRow) {
            private final /* synthetic */ NotificationBackend.AppRow f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return NotificationChannelSlice.this.lambda$getDisplayableChannels$2$NotificationChannelSlice(this.f$1, (NotificationChannelGroup) obj);
            }
        }).collect(Collectors.toList())) {
            NotificationBackend.NotificationsSentState notificationsSentState = appRow.sentByChannel.get(notificationChannel.getId());
            if (notificationsSentState == null) {
                notificationsSentState = new NotificationBackend.NotificationsSentState();
            }
            arrayList.add(new NotificationChannelState(notificationsSentState, notificationChannel));
        }
        return (List) arrayList.stream().sorted(CHANNEL_STATE_COMPARATOR).map($$Lambda$NotificationChannelSlice$EcNouM_UqHGlYQGbnX7K4vNSbgE.INSTANCE).limit(3).collect(Collectors.toList());
    }

    public /* synthetic */ Stream lambda$getDisplayableChannels$2$NotificationChannelSlice(NotificationBackend.AppRow appRow, NotificationChannelGroup notificationChannelGroup) {
        return notificationChannelGroup.getChannels().stream().filter(new Predicate(notificationChannelGroup, appRow) {
            private final /* synthetic */ NotificationChannelGroup f$1;
            private final /* synthetic */ NotificationBackend.AppRow f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean test(Object obj) {
                return NotificationChannelSlice.this.lambda$getDisplayableChannels$1$NotificationChannelSlice(this.f$1, this.f$2, (NotificationChannel) obj);
            }
        });
    }

    private String getEligibleNotificationsPackage(List<PackageInfo> list) {
        NotificationBackend.AppRow appRow;
        int i;
        if (list.isEmpty()) {
            return null;
        }
        ArrayList<Future> arrayList = new ArrayList<>();
        for (PackageInfo notificationMultiChannelAppRow : list) {
            arrayList.add(ThreadUtils.postOnBackgroundThread((Callable) new NotificationMultiChannelAppRow(this.mContext, this.mNotificationBackend, notificationMultiChannelAppRow)));
        }
        int i2 = 0;
        String str = null;
        for (Future future : arrayList) {
            try {
                appRow = (NotificationBackend.AppRow) future.get(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.w("NotifChannelSlice", "Failed to get notification data.", e);
                appRow = null;
            }
            if (appRow != null && !appRow.banned && !isAllChannelsBlocked(getDisplayableChannels(appRow)) && (i = appRow.sentByApp.sentCount) >= 10 && i > i2) {
                str = appRow.pkg;
                this.mAppRow = appRow;
                i2 = i;
            }
        }
        return str;
    }

    private boolean isAllChannelsBlocked(List<NotificationChannel> list) {
        for (NotificationChannel importance : list) {
            if (importance.getImportance() != 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public CharSequence getSubTitle(String str, int i) {
        int channelCount = this.mNotificationBackend.getChannelCount(str, i);
        if (channelCount > 3) {
            return this.mContext.getString(C1715R.string.notification_many_channel_count_summary, new Object[]{Integer.valueOf(channelCount)});
        }
        return this.mContext.getResources().getQuantityString(C1715R.plurals.notification_few_channel_count_summary, channelCount, new Object[]{Integer.valueOf(channelCount)});
    }

    private Intent getAppAndNotificationPageIntent() {
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, AppAndNotificationDashboardFragment.class.getName(), "", this.mContext.getText(C1715R.string.app_and_notification_dashboard_title).toString(), 1401).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(getUri());
    }

    /* access modifiers changed from: private */
    /* renamed from: isChannelEnabled */
    public boolean lambda$getDisplayableChannels$1$NotificationChannelSlice(NotificationChannelGroup notificationChannelGroup, NotificationChannel notificationChannel, NotificationBackend.AppRow appRow) {
        return RestrictedLockUtilsInternal.checkIfApplicationIsSuspended(this.mContext, this.mPackageName, this.mUid) == null && isChannelBlockable(notificationChannel, appRow) && isChannelConfigurable(notificationChannel, appRow) && !notificationChannelGroup.isBlocked();
    }

    private boolean isChannelConfigurable(NotificationChannel notificationChannel, NotificationBackend.AppRow appRow) {
        if (notificationChannel == null || appRow == null) {
            return false;
        }
        return !notificationChannel.isImportanceLockedByOEM();
    }

    private boolean isChannelBlockable(NotificationChannel notificationChannel, NotificationBackend.AppRow appRow) {
        if (notificationChannel == null || appRow == null) {
            return false;
        }
        if (!appRow.systemApp) {
            return true;
        }
        if (notificationChannel.isBlockableSystem() || notificationChannel.getImportance() == 0) {
            return true;
        }
        return false;
    }

    private static class NotificationChannelState {
        private final NotificationChannel mNotificationChannel;
        private final NotificationBackend.NotificationsSentState mNotificationsSentState;

        public NotificationChannelState(NotificationBackend.NotificationsSentState notificationsSentState, NotificationChannel notificationChannel) {
            this.mNotificationsSentState = notificationsSentState;
            this.mNotificationChannel = notificationChannel;
        }

        public NotificationChannel getNotificationChannel() {
            return this.mNotificationChannel;
        }

        public NotificationBackend.NotificationsSentState getNotificationsSentState() {
            return this.mNotificationsSentState;
        }
    }
}
