package com.android.settings.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.NotificationBackend;
import com.android.settings.widget.MasterSwitchPreference;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChannelListPreferenceController extends NotificationPreferenceController {
    private static String KEY_GENERAL_CATEGORY = "categories";
    protected Comparator<NotificationChannel> mChannelComparator = C0992x62d19ea0.INSTANCE;
    /* access modifiers changed from: private */
    public Comparator<NotificationChannelGroup> mChannelGroupComparator = new Comparator<NotificationChannelGroup>() {
        public int compare(NotificationChannelGroup notificationChannelGroup, NotificationChannelGroup notificationChannelGroup2) {
            if (notificationChannelGroup.getId() == null && notificationChannelGroup2.getId() != null) {
                return 1;
            }
            if (notificationChannelGroup2.getId() != null || notificationChannelGroup.getId() == null) {
                return notificationChannelGroup.getId().compareTo(notificationChannelGroup2.getId());
            }
            return -1;
        }
    };
    /* access modifiers changed from: private */
    public List<NotificationChannelGroup> mChannelGroupList;
    private PreferenceCategory mPreference;

    public String getPreferenceKey() {
        return "channels";
    }

    public ChannelListPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null || appRow.banned) {
            return false;
        }
        if (this.mChannel == null) {
            return true;
        }
        if (this.mBackend.onlyHasDefaultChannel(appRow.pkg, appRow.uid) || "miscellaneous".equals(this.mChannel.getId())) {
            return false;
        }
        return true;
    }

    public void updateState(Preference preference) {
        this.mPreference = (PreferenceCategory) preference;
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                ChannelListPreferenceController channelListPreferenceController = ChannelListPreferenceController.this;
                NotificationBackend notificationBackend = channelListPreferenceController.mBackend;
                NotificationBackend.AppRow appRow = channelListPreferenceController.mAppRow;
                List unused = channelListPreferenceController.mChannelGroupList = notificationBackend.getGroups(appRow.pkg, appRow.uid).getList();
                Collections.sort(ChannelListPreferenceController.this.mChannelGroupList, ChannelListPreferenceController.this.mChannelGroupComparator);
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                ChannelListPreferenceController channelListPreferenceController = ChannelListPreferenceController.this;
                if (channelListPreferenceController.mContext != null) {
                    channelListPreferenceController.populateList();
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void populateList() {
        this.mPreference.removeAll();
        if (this.mChannelGroupList.isEmpty()) {
            PreferenceCategory preferenceCategory = new PreferenceCategory(this.mContext);
            preferenceCategory.setTitle((int) C1715R.string.notification_channels);
            preferenceCategory.setKey(KEY_GENERAL_CATEGORY);
            this.mPreference.addPreference(preferenceCategory);
            Preference preference = new Preference(this.mContext);
            preference.setTitle((int) C1715R.string.no_channels);
            preference.setEnabled(false);
            preferenceCategory.addPreference(preference);
            return;
        }
        populateGroupList();
    }

    private void populateGroupList() {
        for (NotificationChannelGroup next : this.mChannelGroupList) {
            PreferenceCategory preferenceCategory = new PreferenceCategory(this.mContext);
            preferenceCategory.setOrderingAsAdded(true);
            this.mPreference.addPreference(preferenceCategory);
            if (next.getId() == null) {
                if (this.mChannelGroupList.size() > 1) {
                    preferenceCategory.setTitle((int) C1715R.string.notification_channels_other);
                }
                preferenceCategory.setKey(KEY_GENERAL_CATEGORY);
            } else {
                preferenceCategory.setTitle(next.getName());
                preferenceCategory.setKey(next.getId());
                populateGroupToggle(preferenceCategory, next);
            }
            if (!next.isBlocked()) {
                List<NotificationChannel> channels = next.getChannels();
                Collections.sort(channels, this.mChannelComparator);
                int size = channels.size();
                for (int i = 0; i < size; i++) {
                    populateSingleChannelPrefs(preferenceCategory, channels.get(i), next.isBlocked());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void populateGroupToggle(PreferenceGroup preferenceGroup, NotificationChannelGroup notificationChannelGroup) {
        RestrictedSwitchPreference restrictedSwitchPreference = new RestrictedSwitchPreference(this.mContext);
        restrictedSwitchPreference.setTitle((int) C1715R.string.notification_switch_label);
        restrictedSwitchPreference.setEnabled(this.mAdmin == null && isChannelGroupBlockable(notificationChannelGroup));
        restrictedSwitchPreference.setChecked(!notificationChannelGroup.isBlocked());
        restrictedSwitchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(notificationChannelGroup) {
            private final /* synthetic */ NotificationChannelGroup f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onPreferenceClick(Preference preference) {
                return ChannelListPreferenceController.this.lambda$populateGroupToggle$0$ChannelListPreferenceController(this.f$1, preference);
            }
        });
        preferenceGroup.addPreference(restrictedSwitchPreference);
    }

    public /* synthetic */ boolean lambda$populateGroupToggle$0$ChannelListPreferenceController(NotificationChannelGroup notificationChannelGroup, Preference preference) {
        notificationChannelGroup.setBlocked(!((SwitchPreference) preference).isChecked());
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow = this.mAppRow;
        notificationBackend.updateChannelGroup(appRow.pkg, appRow.uid, notificationChannelGroup);
        onGroupBlockStateChanged(notificationChannelGroup);
        return true;
    }

    /* access modifiers changed from: protected */
    public Preference populateSingleChannelPrefs(PreferenceGroup preferenceGroup, NotificationChannel notificationChannel, boolean z) {
        MasterSwitchPreference masterSwitchPreference = new MasterSwitchPreference(this.mContext);
        boolean z2 = false;
        masterSwitchPreference.setSwitchEnabled(this.mAdmin == null && isChannelBlockable(notificationChannel) && isChannelConfigurable(notificationChannel) && !z);
        masterSwitchPreference.setIcon((Drawable) null);
        if (notificationChannel.getImportance() > 2) {
            masterSwitchPreference.setIcon(getAlertingIcon());
        }
        masterSwitchPreference.setIconSize(2);
        masterSwitchPreference.setKey(notificationChannel.getId());
        masterSwitchPreference.setTitle(notificationChannel.getName());
        masterSwitchPreference.setSummary(NotificationBackend.getSentSummary(this.mContext, this.mAppRow.sentByChannel.get(notificationChannel.getId()), false));
        if (notificationChannel.getImportance() != 0) {
            z2 = true;
        }
        masterSwitchPreference.setChecked(z2);
        Bundle bundle = new Bundle();
        bundle.putInt("uid", this.mAppRow.uid);
        bundle.putString("package", this.mAppRow.pkg);
        bundle.putString("android.provider.extra.CHANNEL_ID", notificationChannel.getId());
        bundle.putBoolean("fromSettings", true);
        masterSwitchPreference.setIntent(new SubSettingLauncher(this.mContext).setDestination(ChannelNotificationSettings.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.notification_channel_title).setSourceMetricsCategory(72).toIntent());
        masterSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(notificationChannel) {
            private final /* synthetic */ NotificationChannel f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return ChannelListPreferenceController.this.mo10929xb6edf2dd(this.f$1, preference, obj);
            }
        });
        if (preferenceGroup.findPreference(masterSwitchPreference.getKey()) == null) {
            preferenceGroup.addPreference(masterSwitchPreference);
        }
        return masterSwitchPreference;
    }

    /* renamed from: lambda$populateSingleChannelPrefs$1$ChannelListPreferenceController */
    public /* synthetic */ boolean mo10929xb6edf2dd(NotificationChannel notificationChannel, Preference preference, Object obj) {
        boolean z = false;
        int i = ((Boolean) obj).booleanValue() ? 2 : 0;
        notificationChannel.setImportance(i);
        notificationChannel.lockFields(4);
        MasterSwitchPreference masterSwitchPreference = (MasterSwitchPreference) preference;
        masterSwitchPreference.setIcon((Drawable) null);
        if (notificationChannel.getImportance() > 2) {
            masterSwitchPreference.setIcon(getAlertingIcon());
        }
        Drawable icon = masterSwitchPreference.getIcon();
        if (i != 0) {
            z = true;
        }
        toggleBehaviorIconState(icon, z);
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow = this.mAppRow;
        notificationBackend.updateChannel(appRow.pkg, appRow.uid, notificationChannel);
        return true;
    }

    private Drawable getAlertingIcon() {
        Drawable drawable = this.mContext.getDrawable(C1715R.C1717drawable.ic_notifications_alert);
        drawable.setTintList(Utils.getColorAccent(this.mContext));
        return drawable;
    }

    private void toggleBehaviorIconState(Drawable drawable, boolean z) {
        GradientDrawable gradientDrawable;
        if (drawable != null && (gradientDrawable = (GradientDrawable) ((LayerDrawable) drawable).findDrawableByLayerId(C1715R.C1718id.back)) != null) {
            if (z) {
                gradientDrawable.clearColorFilter();
            } else {
                gradientDrawable.setColorFilter(new BlendModeColorFilter(this.mContext.getColor(C1715R.C1716color.material_grey_300), BlendMode.SRC_IN));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onGroupBlockStateChanged(NotificationChannelGroup notificationChannelGroup) {
        PreferenceGroup preferenceGroup;
        if (notificationChannelGroup != null && (preferenceGroup = (PreferenceGroup) this.mPreference.findPreference(notificationChannelGroup.getId())) != null) {
            int i = 0;
            if (notificationChannelGroup.isBlocked()) {
                ArrayList<Preference> arrayList = new ArrayList<>();
                int preferenceCount = preferenceGroup.getPreferenceCount();
                while (i < preferenceCount) {
                    Preference preference = preferenceGroup.getPreference(i);
                    if (preference instanceof MasterSwitchPreference) {
                        arrayList.add(preference);
                    }
                    i++;
                }
                for (Preference removePreference : arrayList) {
                    preferenceGroup.removePreference(removePreference);
                }
                return;
            }
            List<NotificationChannel> channels = notificationChannelGroup.getChannels();
            Collections.sort(channels, this.mChannelComparator);
            int size = channels.size();
            while (i < size) {
                populateSingleChannelPrefs(preferenceGroup, channels.get(i), notificationChannelGroup.isBlocked());
                i++;
            }
        }
    }

    static /* synthetic */ int lambda$new$2(NotificationChannel notificationChannel, NotificationChannel notificationChannel2) {
        if (notificationChannel.isDeleted() != notificationChannel2.isDeleted()) {
            return Boolean.compare(notificationChannel.isDeleted(), notificationChannel2.isDeleted());
        }
        if (notificationChannel.getId().equals("miscellaneous")) {
            return 1;
        }
        if (notificationChannel2.getId().equals("miscellaneous")) {
            return -1;
        }
        return notificationChannel.getId().compareTo(notificationChannel2.getId());
    }
}
