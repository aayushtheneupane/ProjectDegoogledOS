package com.android.settings.notification;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.INotificationManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotificationStation extends SettingsPreferenceFragment implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.title = context.getString(C1715R.string.notification_log_title);
            searchIndexableRaw.screenTitle = context.getString(C1715R.string.notification_log_title);
            searchIndexableRaw.key = NotificationStation.TAG;
            searchIndexableRaw.keywords = context.getString(C1715R.string.keywords_notification_log);
            arrayList.add(searchIndexableRaw);
            return arrayList;
        }
    };
    private static final String TAG = "NotificationStation";
    private Context mContext;
    private Handler mHandler;
    private final NotificationListenerService mListener = new NotificationListenerService() {
        public void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            Object[] objArr = new Object[2];
            int i = 0;
            objArr[0] = statusBarNotification.getNotification();
            if (rankingMap != null) {
                i = rankingMap.getOrderedKeys().length;
            }
            objArr[1] = Integer.valueOf(i);
            NotificationStation.access$100("onNotificationPosted: %s, with update for %d", objArr);
            NotificationListenerService.RankingMap unused = NotificationStation.this.mRanking = rankingMap;
            NotificationStation.this.scheduleRefreshList();
        }

        public void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(rankingMap == null ? 0 : rankingMap.getOrderedKeys().length);
            NotificationStation.access$100("onNotificationRankingUpdate with update for %d", objArr);
            NotificationListenerService.RankingMap unused = NotificationStation.this.mRanking = rankingMap;
            NotificationStation.this.scheduleRefreshList();
        }

        public void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(rankingMap == null ? 0 : rankingMap.getOrderedKeys().length);
            NotificationStation.access$100("onNotificationRankingUpdate with update for %d", objArr);
            NotificationListenerService.RankingMap unused = NotificationStation.this.mRanking = rankingMap;
            NotificationStation.this.scheduleRefreshList();
        }

        public void onListenerConnected() {
            NotificationListenerService.RankingMap unused = NotificationStation.this.mRanking = getCurrentRanking();
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(NotificationStation.this.mRanking == null ? 0 : NotificationStation.this.mRanking.getOrderedKeys().length);
            NotificationStation.access$100("onListenerConnected with update for %d", objArr);
            NotificationStation.this.scheduleRefreshList();
        }
    };
    private INotificationManager mNoMan;
    private final Comparator<HistoricalNotificationInfo> mNotificationSorter = new Comparator<HistoricalNotificationInfo>() {
        public int compare(HistoricalNotificationInfo historicalNotificationInfo, HistoricalNotificationInfo historicalNotificationInfo2) {
            return Long.compare(historicalNotificationInfo2.timestamp, historicalNotificationInfo.timestamp);
        }
    };
    private PackageManager mPm;
    /* access modifiers changed from: private */
    public NotificationListenerService.RankingMap mRanking;
    private Runnable mRefreshListRunnable = new Runnable() {
        public void run() {
            NotificationStation.this.refreshList();
        }
    };

    static /* synthetic */ void access$100(String str, Object[] objArr) {
    }

    public int getMetricsCategory() {
        return 75;
    }

    private static class HistoricalNotificationInfo {
        public boolean active;
        public String channel;
        public CharSequence extra;
        public Drawable icon;
        public String key;
        public String pkg;
        public Drawable pkgicon;
        public CharSequence pkgname;
        public int priority;
        public long timestamp;
        public CharSequence title;
        public int user;

        private HistoricalNotificationInfo() {
        }
    }

    /* access modifiers changed from: private */
    public void scheduleRefreshList() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mRefreshListRunnable);
            this.mHandler.postDelayed(this.mRefreshListRunnable, 100);
        }
    }

    public void onAttach(Activity activity) {
        new Object[1][0] = activity.getClass().getSimpleName();
        super.onAttach(activity);
        this.mHandler = new Handler(activity.getMainLooper());
        this.mContext = activity;
        this.mPm = this.mContext.getPackageManager();
        this.mNoMan = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    }

    public void onDetach() {
        this.mHandler.removeCallbacks(this.mRefreshListRunnable);
        this.mHandler = null;
        super.onDetach();
    }

    public void onPause() {
        try {
            this.mListener.unregisterAsSystemService();
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot unregister listener", e);
        }
        super.onPause();
    }

    public void onActivityCreated(Bundle bundle) {
        new Object[1][0] = bundle;
        super.onActivityCreated(bundle);
        Utils.forceCustomPadding(getListView(), false);
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(C1715R.string.notification_log_title);
        try {
            this.mListener.registerAsSystemService(this.mContext, new ComponentName(this.mContext.getPackageName(), getClass().getCanonicalName()), ActivityManager.getCurrentUser());
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot register listener", e);
        }
        refreshList();
    }

    /* access modifiers changed from: private */
    public void refreshList() {
        List<HistoricalNotificationInfo> loadNotifications = loadNotifications();
        if (loadNotifications != null) {
            int size = loadNotifications.size();
            new Object[1][0] = Integer.valueOf(size);
            Collections.sort(loadNotifications, this.mNotificationSorter);
            if (getPreferenceScreen() == null) {
                setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
            }
            getPreferenceScreen().removeAll();
            for (int i = 0; i < size; i++) {
                getPreferenceScreen().addPreference(new HistoricalNotificationPreference(getPrefContext(), loadNotifications.get(i)));
            }
        }
    }

    private static CharSequence bold(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return charSequence;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new StyleSpan(1), 0, charSequence.length(), 0);
        return spannableString;
    }

    private static String getTitleString(Notification notification) {
        CharSequence charSequence;
        Bundle bundle = notification.extras;
        if (bundle != null) {
            charSequence = bundle.getCharSequence("android.title");
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("android.text");
            }
        } else {
            charSequence = null;
        }
        if (TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(notification.tickerText)) {
            charSequence = notification.tickerText;
        }
        return String.valueOf(charSequence);
    }

    private static String formatPendingIntent(PendingIntent pendingIntent) {
        StringBuilder sb = new StringBuilder();
        IntentSender intentSender = pendingIntent.getIntentSender();
        sb.append("Intent(pkg=");
        sb.append(intentSender.getCreatorPackage());
        try {
            if (ActivityManager.getService().isIntentSenderAnActivity(intentSender.getTarget())) {
                sb.append(" (activity)");
            }
        } catch (RemoteException unused) {
        }
        sb.append(")");
        return sb.toString();
    }

    private List<HistoricalNotificationInfo> loadNotifications() {
        char c;
        int currentUser = ActivityManager.getCurrentUser();
        C10341 r2 = null;
        try {
            StatusBarNotification[] activeNotifications = this.mNoMan.getActiveNotifications(this.mContext.getPackageName());
            StatusBarNotification[] historicalNotifications = this.mNoMan.getHistoricalNotifications(this.mContext.getPackageName(), 100);
            ArrayList arrayList = new ArrayList(activeNotifications.length + historicalNotifications.length);
            char c2 = 2;
            int i = 0;
            int i2 = 1;
            StatusBarNotification[][] statusBarNotificationArr = {activeNotifications, historicalNotifications};
            int length = statusBarNotificationArr.length;
            int i3 = 0;
            while (i3 < length) {
                StatusBarNotification[] statusBarNotificationArr2 = statusBarNotificationArr[i3];
                int length2 = statusBarNotificationArr2.length;
                int i4 = i;
                while (i4 < length2) {
                    StatusBarNotification statusBarNotification = statusBarNotificationArr2[i4];
                    if (((statusBarNotification.getUserId() != -1 ? i2 : i) & (statusBarNotification.getUserId() != currentUser ? i2 : i)) != 0) {
                        c = 2;
                    } else {
                        Notification notification = statusBarNotification.getNotification();
                        HistoricalNotificationInfo historicalNotificationInfo = new HistoricalNotificationInfo();
                        historicalNotificationInfo.pkg = statusBarNotification.getPackageName();
                        historicalNotificationInfo.user = statusBarNotification.getUserId();
                        historicalNotificationInfo.icon = loadIconDrawable(historicalNotificationInfo.pkg, historicalNotificationInfo.user, notification.icon);
                        historicalNotificationInfo.pkgicon = loadPackageIconDrawable(historicalNotificationInfo.pkg, historicalNotificationInfo.user);
                        historicalNotificationInfo.pkgname = loadPackageName(historicalNotificationInfo.pkg);
                        historicalNotificationInfo.title = getTitleString(notification);
                        if (TextUtils.isEmpty(historicalNotificationInfo.title)) {
                            historicalNotificationInfo.title = getString(C1715R.string.notification_log_no_title);
                        }
                        historicalNotificationInfo.timestamp = statusBarNotification.getPostTime();
                        historicalNotificationInfo.priority = notification.priority;
                        historicalNotificationInfo.channel = notification.getChannelId();
                        historicalNotificationInfo.key = statusBarNotification.getKey();
                        historicalNotificationInfo.active = statusBarNotificationArr2 == activeNotifications;
                        historicalNotificationInfo.extra = generateExtraText(statusBarNotification, historicalNotificationInfo);
                        i = 0;
                        i2 = 1;
                        c = 2;
                        Object[] objArr = {Long.valueOf(historicalNotificationInfo.timestamp), historicalNotificationInfo.pkg, historicalNotificationInfo.title};
                        arrayList.add(historicalNotificationInfo);
                    }
                    i4++;
                    c2 = c;
                    r2 = null;
                }
                char c3 = c2;
                i3++;
                r2 = null;
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot load Notifications: ", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0464  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0414 A[EDGE_INSN: B:106:0x0414->B:90:0x0414 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x01bd A[Catch:{ RemoteException -> 0x01fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01e0 A[Catch:{ RemoteException -> 0x01fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01e8 A[Catch:{ RemoteException -> 0x01fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0313  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0418  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.CharSequence generateExtraText(android.service.notification.StatusBarNotification r13, com.android.settings.notification.NotificationStation.HistoricalNotificationInfo r14) {
        /*
            r12 = this;
            android.service.notification.NotificationListenerService$Ranking r0 = new android.service.notification.NotificationListenerService$Ranking
            r0.<init>()
            android.app.Notification r1 = r13.getNotification()
            android.text.SpannableStringBuilder r2 = new android.text.SpannableStringBuilder
            r2.<init>()
            r3 = 2131889458(0x7f120d32, float:1.941358E38)
            java.lang.String r3 = r12.getString(r3)
            r4 = 2131889468(0x7f120d3c, float:1.94136E38)
            java.lang.String r4 = r12.getString(r4)
            java.lang.CharSequence r4 = bold(r4)
            android.text.SpannableStringBuilder r4 = r2.append(r4)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            java.lang.String r5 = r14.pkg
            android.text.SpannableStringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "\n"
            android.text.SpannableStringBuilder r4 = r4.append(r5)
            r6 = 2131889466(0x7f120d3a, float:1.9413596E38)
            java.lang.String r6 = r12.getString(r6)
            java.lang.CharSequence r6 = bold(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            java.lang.String r6 = r13.getKey()
            r4.append(r6)
            android.text.SpannableStringBuilder r4 = r2.append(r5)
            r6 = 2131889464(0x7f120d38, float:1.9413592E38)
            java.lang.String r6 = r12.getString(r6)
            java.lang.CharSequence r6 = bold(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            android.graphics.drawable.Icon r6 = r1.getSmallIcon()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r4.append(r6)
            android.text.SpannableStringBuilder r4 = r2.append(r5)
            java.lang.String r6 = "channelId"
            java.lang.CharSequence r6 = bold(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            java.lang.String r6 = r1.getChannelId()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r4.append(r6)
            android.text.SpannableStringBuilder r4 = r2.append(r5)
            java.lang.String r6 = "postTime"
            java.lang.CharSequence r6 = bold(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            long r6 = r13.getPostTime()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r4.append(r6)
            long r6 = r1.getTimeoutAfter()
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 == 0) goto L_0x00d1
            android.text.SpannableStringBuilder r4 = r2.append(r5)
            java.lang.String r6 = "timeoutAfter"
            java.lang.CharSequence r6 = bold(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            long r6 = r1.getTimeoutAfter()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r4.append(r6)
        L_0x00d1:
            boolean r4 = r13.isGroup()
            if (r4 == 0) goto L_0x010d
            android.text.SpannableStringBuilder r4 = r2.append(r5)
            r6 = 2131889462(0x7f120d36, float:1.9413588E38)
            java.lang.String r6 = r12.getString(r6)
            java.lang.CharSequence r6 = bold(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r6)
            android.text.SpannableStringBuilder r4 = r4.append(r3)
            java.lang.String r6 = r13.getGroupKey()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r4.append(r6)
            boolean r4 = r1.isGroupSummary()
            if (r4 == 0) goto L_0x010d
            r4 = 2131889463(0x7f120d37, float:1.941359E38)
            java.lang.String r4 = r12.getString(r4)
            java.lang.CharSequence r4 = bold(r4)
            r2.append(r4)
        L_0x010d:
            boolean r4 = r14.active
            if (r4 == 0) goto L_0x0139
            android.service.notification.NotificationListenerService$RankingMap r4 = r12.mRanking
            if (r4 == 0) goto L_0x0139
            java.lang.String r6 = r13.getKey()
            boolean r4 = r4.getRanking(r6, r0)
            if (r4 == 0) goto L_0x0139
            long r6 = r0.getLastAudiblyAlertedMillis()
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x0139
            android.text.SpannableStringBuilder r4 = r2.append(r5)
            r6 = 2131889451(0x7f120d2b, float:1.9413566E38)
            java.lang.String r6 = r12.getString(r6)
            java.lang.CharSequence r6 = bold(r6)
            r4.append(r6)
        L_0x0139:
            r4 = 0
            android.app.INotificationManager r6 = r12.mNoMan     // Catch:{ RemoteException -> 0x01fc }
            java.lang.String r7 = r13.getPackageName()     // Catch:{ RemoteException -> 0x01fc }
            int r8 = r13.getUid()     // Catch:{ RemoteException -> 0x01fc }
            java.lang.String r9 = r1.getChannelId()     // Catch:{ RemoteException -> 0x01fc }
            android.app.NotificationChannel r6 = r6.getNotificationChannelForPackage(r7, r8, r9, r4)     // Catch:{ RemoteException -> 0x01fc }
            android.text.SpannableStringBuilder r7 = r2.append(r5)     // Catch:{ RemoteException -> 0x01fc }
            r8 = 2131889475(0x7f120d43, float:1.9413615E38)
            java.lang.String r8 = r12.getString(r8)     // Catch:{ RemoteException -> 0x01fc }
            java.lang.CharSequence r8 = bold(r8)     // Catch:{ RemoteException -> 0x01fc }
            android.text.SpannableStringBuilder r7 = r7.append(r8)     // Catch:{ RemoteException -> 0x01fc }
            r7.append(r3)     // Catch:{ RemoteException -> 0x01fc }
            r7 = 2131889456(0x7f120d30, float:1.9413576E38)
            r8 = -1000(0xfffffffffffffc18, float:NaN)
            r9 = 2131889467(0x7f120d3b, float:1.9413598E38)
            if (r6 == 0) goto L_0x017f
            int r10 = r6.getImportance()     // Catch:{ RemoteException -> 0x01fc }
            if (r10 != r8) goto L_0x0173
            goto L_0x017f
        L_0x0173:
            android.net.Uri r10 = r6.getSound()     // Catch:{ RemoteException -> 0x01fc }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r10)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x01a2
        L_0x017f:
            int r10 = r1.defaults     // Catch:{ RemoteException -> 0x01fc }
            r10 = r10 & 1
            if (r10 == 0) goto L_0x018d
            java.lang.String r10 = r12.getString(r7)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r10)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x01a2
        L_0x018d:
            android.net.Uri r10 = r1.sound     // Catch:{ RemoteException -> 0x01fc }
            if (r10 == 0) goto L_0x019b
            android.net.Uri r10 = r1.sound     // Catch:{ RemoteException -> 0x01fc }
            java.lang.String r10 = r10.toString()     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r10)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x01a2
        L_0x019b:
            java.lang.String r10 = r12.getString(r9)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r10)     // Catch:{ RemoteException -> 0x01fc }
        L_0x01a2:
            android.text.SpannableStringBuilder r10 = r2.append(r5)     // Catch:{ RemoteException -> 0x01fc }
            r11 = 2131889477(0x7f120d45, float:1.9413619E38)
            java.lang.String r11 = r12.getString(r11)     // Catch:{ RemoteException -> 0x01fc }
            java.lang.CharSequence r11 = bold(r11)     // Catch:{ RemoteException -> 0x01fc }
            android.text.SpannableStringBuilder r10 = r10.append(r11)     // Catch:{ RemoteException -> 0x01fc }
            r10.append(r3)     // Catch:{ RemoteException -> 0x01fc }
            r10 = 2131889478(0x7f120d46, float:1.941362E38)
            if (r6 == 0) goto L_0x01da
            int r11 = r6.getImportance()     // Catch:{ RemoteException -> 0x01fc }
            if (r11 != r8) goto L_0x01c4
            goto L_0x01da
        L_0x01c4:
            long[] r6 = r6.getVibrationPattern()     // Catch:{ RemoteException -> 0x01fc }
            if (r6 == 0) goto L_0x01d2
            java.lang.String r6 = r12.getString(r10)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r6)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x0204
        L_0x01d2:
            java.lang.String r6 = r12.getString(r9)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r6)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x0204
        L_0x01da:
            int r6 = r1.defaults     // Catch:{ RemoteException -> 0x01fc }
            r6 = r6 & 2
            if (r6 == 0) goto L_0x01e8
            java.lang.String r6 = r12.getString(r7)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r6)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x0204
        L_0x01e8:
            long[] r6 = r1.vibrate     // Catch:{ RemoteException -> 0x01fc }
            if (r6 == 0) goto L_0x01f4
            java.lang.String r6 = r12.getString(r10)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r6)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x0204
        L_0x01f4:
            java.lang.String r6 = r12.getString(r9)     // Catch:{ RemoteException -> 0x01fc }
            r2.append(r6)     // Catch:{ RemoteException -> 0x01fc }
            goto L_0x0204
        L_0x01fc:
            r6 = move-exception
            java.lang.String r7 = TAG
            java.lang.String r8 = "cannot read channel info"
            android.util.Log.d(r7, r8, r6)
        L_0x0204:
            android.text.SpannableStringBuilder r6 = r2.append(r5)
            r7 = 2131889479(0x7f120d47, float:1.9413623E38)
            java.lang.String r7 = r12.getString(r7)
            java.lang.CharSequence r7 = bold(r7)
            android.text.SpannableStringBuilder r6 = r6.append(r7)
            android.text.SpannableStringBuilder r6 = r6.append(r3)
            int r7 = r1.visibility
            java.lang.String r7 = android.app.Notification.visibilityToString(r7)
            r6.append(r7)
            android.app.Notification r6 = r1.publicVersion
            if (r6 == 0) goto L_0x0248
            android.text.SpannableStringBuilder r6 = r2.append(r5)
            r7 = 2131889471(0x7f120d3f, float:1.9413607E38)
            java.lang.String r7 = r12.getString(r7)
            java.lang.CharSequence r7 = bold(r7)
            android.text.SpannableStringBuilder r6 = r6.append(r7)
            android.text.SpannableStringBuilder r6 = r6.append(r3)
            android.app.Notification r7 = r1.publicVersion
            java.lang.String r7 = getTitleString(r7)
            r6.append(r7)
        L_0x0248:
            android.text.SpannableStringBuilder r6 = r2.append(r5)
            r7 = 2131889470(0x7f120d3e, float:1.9413604E38)
            java.lang.String r7 = r12.getString(r7)
            java.lang.CharSequence r7 = bold(r7)
            android.text.SpannableStringBuilder r6 = r6.append(r7)
            android.text.SpannableStringBuilder r6 = r6.append(r3)
            int r7 = r1.priority
            java.lang.String r7 = android.app.Notification.priorityToString(r7)
            r6.append(r7)
            boolean r14 = r14.active
            if (r14 == 0) goto L_0x030c
            android.service.notification.NotificationListenerService$RankingMap r14 = r12.mRanking
            if (r14 == 0) goto L_0x02e3
            java.lang.String r13 = r13.getKey()
            boolean r13 = r14.getRanking(r13, r0)
            if (r13 == 0) goto L_0x02e3
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889465(0x7f120d39, float:1.9413594E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            int r14 = r0.getImportance()
            java.lang.String r14 = android.service.notification.NotificationListenerService.Ranking.importanceToString(r14)
            r13.append(r14)
            java.lang.CharSequence r13 = r0.getImportanceExplanation()
            if (r13 == 0) goto L_0x02c0
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889459(0x7f120d33, float:1.9413582E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            java.lang.CharSequence r14 = r0.getImportanceExplanation()
            r13.append(r14)
        L_0x02c0:
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889453(0x7f120d2d, float:1.941357E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            boolean r14 = r0.canShowBadge()
            java.lang.String r14 = java.lang.Boolean.toString(r14)
            r13.append(r14)
            goto L_0x030c
        L_0x02e3:
            android.service.notification.NotificationListenerService$RankingMap r13 = r12.mRanking
            if (r13 != 0) goto L_0x02fa
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889473(0x7f120d41, float:1.941361E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            r13.append(r14)
            goto L_0x030c
        L_0x02fa:
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889472(0x7f120d40, float:1.9413609E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            r13.append(r14)
        L_0x030c:
            android.app.PendingIntent r13 = r1.contentIntent
            r14 = 2131889454(0x7f120d2e, float:1.9413572E38)
            if (r13 == 0) goto L_0x0330
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            java.lang.String r0 = r12.getString(r14)
            java.lang.CharSequence r0 = bold(r0)
            android.text.SpannableStringBuilder r13 = r13.append(r0)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            android.app.PendingIntent r0 = r1.contentIntent
            java.lang.String r0 = formatPendingIntent(r0)
            r13.append(r0)
        L_0x0330:
            android.app.PendingIntent r13 = r1.deleteIntent
            if (r13 == 0) goto L_0x0354
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r0 = 2131889457(0x7f120d31, float:1.9413578E38)
            java.lang.String r0 = r12.getString(r0)
            java.lang.CharSequence r0 = bold(r0)
            android.text.SpannableStringBuilder r13 = r13.append(r0)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            android.app.PendingIntent r0 = r1.deleteIntent
            java.lang.String r0 = formatPendingIntent(r0)
            r13.append(r0)
        L_0x0354:
            android.app.PendingIntent r13 = r1.fullScreenIntent
            if (r13 == 0) goto L_0x0378
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r0 = 2131889461(0x7f120d35, float:1.9413586E38)
            java.lang.String r0 = r12.getString(r0)
            java.lang.CharSequence r0 = bold(r0)
            android.text.SpannableStringBuilder r13 = r13.append(r0)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            android.app.PendingIntent r0 = r1.fullScreenIntent
            java.lang.String r0 = formatPendingIntent(r0)
            r13.append(r0)
        L_0x0378:
            android.app.Notification$Action[] r13 = r1.actions
            java.lang.String r0 = "\n  "
            r6 = 32
            if (r13 == 0) goto L_0x0414
            int r13 = r13.length
            if (r13 <= 0) goto L_0x0414
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r7 = 2131889450(0x7f120d2a, float:1.9413564E38)
            java.lang.String r7 = r12.getString(r7)
            java.lang.CharSequence r7 = bold(r7)
            r13.append(r7)
            r13 = r4
        L_0x0396:
            android.app.Notification$Action[] r7 = r1.actions
            int r8 = r7.length
            if (r13 >= r8) goto L_0x0414
            r7 = r7[r13]
            android.text.SpannableStringBuilder r8 = r2.append(r0)
            java.lang.String r9 = java.lang.String.valueOf(r13)
            android.text.SpannableStringBuilder r8 = r8.append(r9)
            android.text.SpannableStringBuilder r8 = r8.append(r6)
            r9 = 2131889476(0x7f120d44, float:1.9413617E38)
            java.lang.String r9 = r12.getString(r9)
            java.lang.CharSequence r9 = bold(r9)
            android.text.SpannableStringBuilder r8 = r8.append(r9)
            android.text.SpannableStringBuilder r8 = r8.append(r3)
            java.lang.CharSequence r9 = r7.title
            r8.append(r9)
            android.app.PendingIntent r8 = r7.actionIntent
            java.lang.String r9 = "\n    "
            if (r8 == 0) goto L_0x03e8
            android.text.SpannableStringBuilder r8 = r2.append(r9)
            java.lang.String r10 = r12.getString(r14)
            java.lang.CharSequence r10 = bold(r10)
            android.text.SpannableStringBuilder r8 = r8.append(r10)
            android.text.SpannableStringBuilder r8 = r8.append(r3)
            android.app.PendingIntent r10 = r7.actionIntent
            java.lang.String r10 = formatPendingIntent(r10)
            r8.append(r10)
        L_0x03e8:
            android.app.RemoteInput[] r8 = r7.getRemoteInputs()
            if (r8 == 0) goto L_0x0411
            android.text.SpannableStringBuilder r8 = r2.append(r9)
            r9 = 2131889474(0x7f120d42, float:1.9413613E38)
            java.lang.String r9 = r12.getString(r9)
            java.lang.CharSequence r9 = bold(r9)
            android.text.SpannableStringBuilder r8 = r8.append(r9)
            android.text.SpannableStringBuilder r8 = r8.append(r3)
            android.app.RemoteInput[] r7 = r7.getRemoteInputs()
            int r7 = r7.length
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r8.append(r7)
        L_0x0411:
            int r13 = r13 + 1
            goto L_0x0396
        L_0x0414:
            android.widget.RemoteViews r13 = r1.contentView
            if (r13 == 0) goto L_0x0438
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889455(0x7f120d2f, float:1.9413574E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r14)
            android.text.SpannableStringBuilder r13 = r13.append(r3)
            android.widget.RemoteViews r14 = r1.contentView
            java.lang.String r14 = r14.toString()
            r13.append(r14)
        L_0x0438:
            android.os.Bundle r13 = r1.extras
            if (r13 == 0) goto L_0x04a1
            int r13 = r13.size()
            if (r13 <= 0) goto L_0x04a1
            android.text.SpannableStringBuilder r13 = r2.append(r5)
            r14 = 2131889460(0x7f120d34, float:1.9413584E38)
            java.lang.String r14 = r12.getString(r14)
            java.lang.CharSequence r14 = bold(r14)
            r13.append(r14)
            android.os.Bundle r13 = r1.extras
            java.util.Set r13 = r13.keySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x045e:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x04a1
            java.lang.Object r14 = r13.next()
            java.lang.String r14 = (java.lang.String) r14
            android.os.Bundle r7 = r1.extras
            java.lang.Object r7 = r7.get(r14)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            int r8 = r7.length()
            r9 = 100
            if (r8 <= r9) goto L_0x0491
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r7 = r7.substring(r4, r9)
            r8.append(r7)
            java.lang.String r7 = "..."
            r8.append(r7)
            java.lang.String r7 = r8.toString()
        L_0x0491:
            android.text.SpannableStringBuilder r8 = r2.append(r0)
            android.text.SpannableStringBuilder r14 = r8.append(r14)
            android.text.SpannableStringBuilder r14 = r14.append(r3)
            r14.append(r7)
            goto L_0x045e
        L_0x04a1:
            android.os.Parcel r13 = android.os.Parcel.obtain()
            r1.writeToParcel(r13, r4)
            android.text.SpannableStringBuilder r14 = r2.append(r5)
            r0 = 2131889469(0x7f120d3d, float:1.9413602E38)
            java.lang.String r0 = r12.getString(r0)
            java.lang.CharSequence r0 = bold(r0)
            android.text.SpannableStringBuilder r14 = r14.append(r0)
            android.text.SpannableStringBuilder r14 = r14.append(r3)
            int r0 = r13.dataPosition()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            android.text.SpannableStringBuilder r14 = r14.append(r0)
            android.text.SpannableStringBuilder r14 = r14.append(r6)
            r0 = 2131889452(0x7f120d2c, float:1.9413568E38)
            java.lang.String r12 = r12.getString(r0)
            java.lang.CharSequence r12 = bold(r12)
            android.text.SpannableStringBuilder r12 = r14.append(r12)
            android.text.SpannableStringBuilder r12 = r12.append(r3)
            long r13 = r13.getBlobAshmemSize()
            java.lang.String r13 = java.lang.String.valueOf(r13)
            android.text.SpannableStringBuilder r12 = r12.append(r13)
            r12.append(r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.notification.NotificationStation.generateExtraText(android.service.notification.StatusBarNotification, com.android.settings.notification.NotificationStation$HistoricalNotificationInfo):java.lang.CharSequence");
    }

    private Resources getResourcesForUserPackage(String str, int i) {
        if (str == null) {
            return this.mContext.getResources();
        }
        if (i == -1) {
            i = 0;
        }
        try {
            return this.mPm.getResourcesForApplicationAsUser(str, i);
        } catch (PackageManager.NameNotFoundException e) {
            String str2 = TAG;
            Log.e(str2, "Icon package not found: " + str, e);
            return null;
        }
    }

    private Drawable loadPackageIconDrawable(String str, int i) {
        try {
            return this.mPm.getApplicationIcon(str);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot get application icon", e);
            return null;
        }
    }

    private CharSequence loadPackageName(String str) {
        try {
            ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(str, 4194304);
            if (applicationInfo != null) {
                return this.mPm.getApplicationLabel(applicationInfo);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot load package name", e);
        }
        return str;
    }

    private Drawable loadIconDrawable(String str, int i, int i2) {
        Resources resourcesForUserPackage = getResourcesForUserPackage(str, i);
        if (i2 == 0) {
            return null;
        }
        try {
            return resourcesForUserPackage.getDrawable(i2, (Resources.Theme) null);
        } catch (RuntimeException e) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Icon not found in ");
            sb.append(str != null ? Integer.valueOf(i2) : "<system>");
            sb.append(": ");
            sb.append(Integer.toHexString(i2));
            Log.w(str2, sb.toString(), e);
            return null;
        }
    }

    private static class HistoricalNotificationPreference extends Preference {
        /* access modifiers changed from: private */
        public static long sLastExpandedTimestamp;
        /* access modifiers changed from: private */
        public final HistoricalNotificationInfo mInfo;

        public void performClick() {
        }

        public HistoricalNotificationPreference(Context context, HistoricalNotificationInfo historicalNotificationInfo) {
            super(context);
            setLayoutResource(C1715R.layout.notification_log_row);
            this.mInfo = historicalNotificationInfo;
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            if (this.mInfo.icon != null) {
                ((ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.icon)).setImageDrawable(this.mInfo.icon);
            }
            if (this.mInfo.pkgicon != null) {
                ((ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.pkgicon)).setImageDrawable(this.mInfo.pkgicon);
            }
            preferenceViewHolder.findViewById(C1715R.C1718id.timestamp).setTime(this.mInfo.timestamp);
            ((TextView) preferenceViewHolder.findViewById(C1715R.C1718id.title)).setText(this.mInfo.title);
            ((TextView) preferenceViewHolder.findViewById(C1715R.C1718id.pkgname)).setText(this.mInfo.pkgname);
            final TextView textView = (TextView) preferenceViewHolder.findViewById(C1715R.C1718id.extra);
            textView.setText(this.mInfo.extra);
            textView.setVisibility(this.mInfo.timestamp == sLastExpandedTimestamp ? 0 : 8);
            preferenceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TextView textView = textView;
                    textView.setVisibility(textView.getVisibility() == 0 ? 8 : 0);
                    long unused = HistoricalNotificationPreference.sLastExpandedTimestamp = HistoricalNotificationPreference.this.mInfo.timestamp;
                }
            });
            preferenceViewHolder.itemView.setAlpha(this.mInfo.active ? 1.0f : 0.5f);
        }
    }
}
