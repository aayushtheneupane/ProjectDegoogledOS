package com.android.settings.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.provider.SearchIndexableResource;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.applications.defaultapps.DefaultAppPickerFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.applications.ServiceListing;
import com.android.settingslib.widget.CandidateInfo;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class NotificationAssistantPicker extends DefaultAppPickerFragment implements ServiceListing.Callback {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.notification_assistant_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private List<CandidateInfo> mCandidateInfos = new ArrayList();
    @VisibleForTesting
    protected Context mContext;
    @VisibleForTesting
    protected NotificationBackend mNotificationBackend;
    private ServiceListing mServiceListing;

    public int getMetricsCategory() {
        return 790;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.notification_assistant_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mNotificationBackend = new NotificationBackend();
        ServiceListing.Builder builder = new ServiceListing.Builder(context);
        builder.setTag("NotiAssistantPicker");
        builder.setSetting("enabled_notification_assistant");
        builder.setIntentAction("android.service.notification.NotificationAssistantService");
        builder.setPermission("android.permission.BIND_NOTIFICATION_ASSISTANT_SERVICE");
        builder.setNoun("notification assistant");
        this.mServiceListing = builder.build();
        this.mServiceListing.addCallback(this);
        this.mServiceListing.reload();
    }

    public void onDetach() {
        super.onDetach();
        this.mServiceListing.removeCallback(this);
    }

    /* access modifiers changed from: protected */
    public List<? extends CandidateInfo> getCandidates() {
        return this.mCandidateInfos;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        ComponentName allowedNotificationAssistant = this.mNotificationBackend.getAllowedNotificationAssistant();
        return allowedNotificationAssistant != null ? allowedNotificationAssistant.flattenToString() : "";
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        return this.mNotificationBackend.setNotificationAssistantGranted(ComponentName.unflattenFromString(str));
    }

    /* access modifiers changed from: protected */
    public CharSequence getConfirmationMessage(CandidateInfo candidateInfo) {
        if (TextUtils.isEmpty(candidateInfo.getKey())) {
            return null;
        }
        return this.mContext.getString(C1715R.string.notification_assistant_security_warning_summary, new Object[]{candidateInfo.loadLabel()});
    }

    public void onServicesReloaded(List<ServiceInfo> list) {
        ArrayList arrayList = new ArrayList();
        list.sort(new PackageItemInfo.DisplayNameComparator(this.mPm));
        for (ServiceInfo next : list) {
            if (this.mContext.getPackageManager().checkPermission("android.permission.REQUEST_NOTIFICATION_ASSISTANT_SERVICE", next.packageName) == 0) {
                arrayList.add(new DefaultAppInfo(this.mContext, this.mPm, this.mUserId, new ComponentName(next.packageName, next.name)));
            }
        }
        arrayList.add(new CandidateNone(this.mContext));
        this.mCandidateInfos = arrayList;
    }

    public static class CandidateNone extends CandidateInfo {
        public Context mContext;

        public String getKey() {
            return "";
        }

        public Drawable loadIcon() {
            return null;
        }

        public CandidateNone(Context context) {
            super(true);
            this.mContext = context;
        }

        public CharSequence loadLabel() {
            return this.mContext.getString(C1715R.string.no_notification_assistant);
        }
    }
}
