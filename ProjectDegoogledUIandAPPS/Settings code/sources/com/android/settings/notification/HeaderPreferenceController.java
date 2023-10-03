package com.android.settings.notification;

import android.app.Activity;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class HeaderPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, LifecycleObserver {
    private final DashboardFragment mFragment;
    private EntityHeaderController mHeaderController;
    private boolean mStarted = false;

    public String getPreferenceKey() {
        return "pref_app_header";
    }

    public HeaderPreferenceController(Context context, DashboardFragment dashboardFragment) {
        super(context, (NotificationBackend) null);
        this.mFragment = dashboardFragment;
    }

    public boolean isAvailable() {
        return this.mAppRow != null;
    }

    public void updateState(Preference preference) {
        DashboardFragment dashboardFragment;
        if (this.mAppRow != null && (dashboardFragment = this.mFragment) != null) {
            FragmentActivity fragmentActivity = null;
            if (this.mStarted) {
                fragmentActivity = dashboardFragment.getActivity();
            }
            if (fragmentActivity != null) {
                this.mHeaderController = EntityHeaderController.newInstance(fragmentActivity, this.mFragment, ((LayoutPreference) preference).findViewById(C1715R.C1718id.entity_header));
                this.mHeaderController.setIcon(this.mAppRow.icon).setLabel(getLabel()).setSummary(getSummary()).setPackageName(this.mAppRow.pkg).setUid(this.mAppRow.uid).setButtonActions(1, 0).setHasAppInfoLink(true).setRecyclerView(this.mFragment.getListView(), this.mFragment.getSettingsLifecycle()).done((Activity) fragmentActivity, this.mContext).findViewById(C1715R.C1718id.entity_header).setVisibility(0);
            }
        }
    }

    public CharSequence getSummary() {
        if (this.mChannel == null || isDefaultChannel()) {
            return this.mChannelGroup != null ? this.mAppRow.label.toString() : "";
        }
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        if (notificationChannelGroup == null || TextUtils.isEmpty(notificationChannelGroup.getName())) {
            return this.mAppRow.label.toString();
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        BidiFormatter instance = BidiFormatter.getInstance();
        spannableStringBuilder.append(instance.unicodeWrap(this.mAppRow.label.toString()));
        spannableStringBuilder.append(instance.unicodeWrap(this.mContext.getText(C1715R.string.notification_header_divider_symbol_with_spaces)));
        spannableStringBuilder.append(instance.unicodeWrap(this.mChannelGroup.getName().toString()));
        return spannableStringBuilder.toString();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.mStarted = true;
        EntityHeaderController entityHeaderController = this.mHeaderController;
        if (entityHeaderController != null) {
            entityHeaderController.styleActionBar(this.mFragment.getActivity());
        }
    }

    /* access modifiers changed from: package-private */
    public CharSequence getLabel() {
        if (this.mChannel != null && !isDefaultChannel()) {
            return this.mChannel.getName();
        }
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        if (notificationChannelGroup != null) {
            return notificationChannelGroup.getName();
        }
        return this.mAppRow.label;
    }
}
