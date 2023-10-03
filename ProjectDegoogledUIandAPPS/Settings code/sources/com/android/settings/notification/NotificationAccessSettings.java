package com.android.settings.notification;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.utils.ManagedServiceSettings;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class NotificationAccessSettings extends ManagedServiceSettings {
    private static final ManagedServiceSettings.Config CONFIG;
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.notification_access_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private NotificationManager mNm;

    public int getMetricsCategory() {
        return 179;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.notification_access_settings;
    }

    static {
        ManagedServiceSettings.Config.Builder builder = new ManagedServiceSettings.Config.Builder();
        builder.setTag("NotificationAccessSettings");
        builder.setSetting("enabled_notification_listeners");
        builder.setIntentAction("android.service.notification.NotificationListenerService");
        builder.setPermission("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE");
        builder.setNoun("notification listener");
        builder.setWarningDialogTitle(C1715R.string.notification_listener_security_warning_title);
        builder.setWarningDialogSummary(C1715R.string.notification_listener_security_warning_summary);
        builder.setEmptyText(C1715R.string.no_notification_listeners);
        CONFIG = builder.build();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (UserManager.get(context).isManagedProfile()) {
            Toast.makeText(context, C1715R.string.notification_settings_work_profile, 0).show();
            finish();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mNm = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    /* access modifiers changed from: protected */
    public ManagedServiceSettings.Config getConfig() {
        return CONFIG;
    }

    /* access modifiers changed from: protected */
    public boolean setEnabled(ComponentName componentName, String str, boolean z) {
        logSpecialPermissionChange(z, componentName.getPackageName());
        if (!z) {
            if (!isServiceEnabled(componentName)) {
                return true;
            }
            new FriendlyWarningDialogFragment().setServiceInfo(componentName, str, this).show(getFragmentManager(), "friendlydialog");
            return false;
        } else if (isServiceEnabled(componentName)) {
            return true;
        } else {
            new ManagedServiceSettings.ScaryWarningDialogFragment().setServiceInfo(componentName, str, this).show(getFragmentManager(), "dialog");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isServiceEnabled(ComponentName componentName) {
        return this.mNm.isNotificationListenerAccessGranted(componentName);
    }

    /* access modifiers changed from: protected */
    public void enable(ComponentName componentName) {
        this.mNm.setNotificationListenerAccessGranted(componentName, true);
    }

    /* access modifiers changed from: package-private */
    public void logSpecialPermissionChange(boolean z, String str) {
        FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider().action(getContext(), z ? 776 : 777, str);
    }

    /* access modifiers changed from: private */
    public static void disable(NotificationAccessSettings notificationAccessSettings, ComponentName componentName) {
        notificationAccessSettings.mNm.setNotificationListenerAccessGranted(componentName, false);
        AsyncTask.execute(new Runnable(componentName) {
            private final /* synthetic */ ComponentName f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                NotificationAccessSettings.lambda$disable$0(NotificationAccessSettings.this, this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$disable$0(NotificationAccessSettings notificationAccessSettings, ComponentName componentName) {
        if (!notificationAccessSettings.mNm.isNotificationPolicyAccessGrantedForPackage(componentName.getPackageName())) {
            notificationAccessSettings.mNm.removeAutomaticZenRules(componentName.getPackageName());
        }
    }

    public static class FriendlyWarningDialogFragment extends InstrumentedDialogFragment {
        static /* synthetic */ void lambda$onCreateDialog$1(DialogInterface dialogInterface, int i) {
        }

        public int getMetricsCategory() {
            return 552;
        }

        public FriendlyWarningDialogFragment setServiceInfo(ComponentName componentName, String str, Fragment fragment) {
            Bundle bundle = new Bundle();
            bundle.putString("c", componentName.flattenToString());
            bundle.putString("l", str);
            setArguments(bundle);
            setTargetFragment(fragment, 0);
            return this;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            Bundle arguments = getArguments();
            String string = arguments.getString("l");
            ComponentName unflattenFromString = ComponentName.unflattenFromString(arguments.getString("c"));
            String string2 = getResources().getString(C1715R.string.notification_listener_disable_warning_summary, new Object[]{string});
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage((CharSequence) string2);
            builder.setCancelable(true);
            builder.setPositiveButton((int) C1715R.string.notification_listener_disable_warning_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(unflattenFromString) {
                private final /* synthetic */ ComponentName f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    NotificationAccessSettings.disable(NotificationAccessSettings.this, this.f$1);
                }
            });
            builder.setNegativeButton((int) C1715R.string.notification_listener_disable_warning_cancel, (DialogInterface.OnClickListener) C0996x1af37038.INSTANCE);
            return builder.create();
        }
    }
}
