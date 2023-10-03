package com.android.settings.applications.defaultapps;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.autofill.AutofillServiceInfo;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.internal.content.PackageMonitor;
import com.android.settings.applications.defaultapps.DefaultAppPickerFragment;
import com.android.settings.applications.defaultapps.DefaultAutofillPicker;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.CandidateInfo;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class DefaultAutofillPicker extends DefaultAppPickerFragment {
    static final Intent AUTOFILL_PROBE = new Intent("android.service.autofill.AutofillService");
    /* access modifiers changed from: private */
    public DialogInterface.OnClickListener mCancelListener;
    private final PackageMonitor mSettingsPackageMonitor = new PackageMonitor() {
        public /* synthetic */ void lambda$onPackageAdded$0$DefaultAutofillPicker$1() {
            DefaultAutofillPicker.this.update();
        }

        public void onPackageAdded(String str, int i) {
            ThreadUtils.postOnMainThread(new Runnable() {
                public final void run() {
                    DefaultAutofillPicker.C05681.this.lambda$onPackageAdded$0$DefaultAutofillPicker$1();
                }
            });
        }

        public /* synthetic */ void lambda$onPackageModified$1$DefaultAutofillPicker$1() {
            DefaultAutofillPicker.this.update();
        }

        public void onPackageModified(String str) {
            ThreadUtils.postOnMainThread(new Runnable() {
                public final void run() {
                    DefaultAutofillPicker.C05681.this.lambda$onPackageModified$1$DefaultAutofillPicker$1();
                }
            });
        }

        public /* synthetic */ void lambda$onPackageRemoved$2$DefaultAutofillPicker$1() {
            DefaultAutofillPicker.this.update();
        }

        public void onPackageRemoved(String str, int i) {
            ThreadUtils.postOnMainThread(new Runnable() {
                public final void run() {
                    DefaultAutofillPicker.C05681.this.lambda$onPackageRemoved$2$DefaultAutofillPicker$1();
                }
            });
        }
    };

    public int getMetricsCategory() {
        return 792;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.default_autofill_settings;
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowItemNone() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        if (!(activity == null || activity.getIntent().getStringExtra("package_name") == null)) {
            this.mCancelListener = new DialogInterface.OnClickListener(activity) {
                private final /* synthetic */ Activity f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DefaultAutofillPicker.lambda$onCreate$0(this.f$0, dialogInterface, i);
                }
            };
            this.mUserId = UserHandle.myUserId();
        }
        this.mSettingsPackageMonitor.register(activity, activity.getMainLooper(), false);
        update();
    }

    static /* synthetic */ void lambda$onCreate$0(Activity activity, DialogInterface dialogInterface, int i) {
        activity.setResult(0);
        activity.finish();
    }

    /* access modifiers changed from: protected */
    public DefaultAppPickerFragment.ConfirmationDialogFragment newConfirmationDialogFragment(String str, CharSequence charSequence) {
        AutofillPickerConfirmationDialogFragment autofillPickerConfirmationDialogFragment = new AutofillPickerConfirmationDialogFragment();
        autofillPickerConfirmationDialogFragment.init(this, str, charSequence);
        return autofillPickerConfirmationDialogFragment;
    }

    public static class AutofillPickerConfirmationDialogFragment extends DefaultAppPickerFragment.ConfirmationDialogFragment {
        public void onCreate(Bundle bundle) {
            setCancelListener(((DefaultAutofillPicker) getTargetFragment()).mCancelListener);
            super.onCreate(bundle);
        }
    }

    /* access modifiers changed from: private */
    public void update() {
        updateCandidates();
        addAddServicePreference();
    }

    public void onDestroy() {
        this.mSettingsPackageMonitor.unregister();
        super.onDestroy();
    }

    private Preference newAddServicePreferenceOrNull() {
        String stringForUser = Settings.Secure.getStringForUser(getActivity().getContentResolver(), "autofill_service_search_uri", this.mUserId);
        if (TextUtils.isEmpty(stringForUser)) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringForUser));
        Context prefContext = getPrefContext();
        Preference preference = new Preference(prefContext);
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(prefContext, intent) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ Intent f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onPreferenceClick(Preference preference) {
                return DefaultAutofillPicker.this.lambda$newAddServicePreferenceOrNull$1$DefaultAutofillPicker(this.f$1, this.f$2, preference);
            }
        });
        preference.setTitle((int) C1715R.string.print_menu_item_add_service);
        preference.setIcon((int) C1715R.C1717drawable.ic_add_24dp);
        preference.setOrder(2147483646);
        preference.setPersistent(false);
        return preference;
    }

    public /* synthetic */ boolean lambda$newAddServicePreferenceOrNull$1$DefaultAutofillPicker(Context context, Intent intent, Preference preference) {
        context.startActivityAsUser(intent, UserHandle.of(this.mUserId));
        return true;
    }

    private void addAddServicePreference() {
        Preference newAddServicePreferenceOrNull = newAddServicePreferenceOrNull();
        if (newAddServicePreferenceOrNull != null) {
            getPreferenceScreen().addPreference(newAddServicePreferenceOrNull);
        }
    }

    /* access modifiers changed from: protected */
    public List<DefaultAppInfo> getCandidates() {
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> queryIntentServicesAsUser = this.mPm.queryIntentServicesAsUser(AUTOFILL_PROBE, 128, this.mUserId);
        Context context = getContext();
        for (ResolveInfo resolveInfo : queryIntentServicesAsUser) {
            String str = resolveInfo.serviceInfo.permission;
            if ("android.permission.BIND_AUTOFILL_SERVICE".equals(str)) {
                arrayList.add(new DefaultAppInfo(context, this.mPm, this.mUserId, new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name)));
            }
            if ("android.permission.BIND_AUTOFILL".equals(str)) {
                Log.w("DefaultAutofillPicker", "AutofillService from '" + resolveInfo.serviceInfo.packageName + "' uses unsupported permission " + "android.permission.BIND_AUTOFILL" + ". It works for now, but might not be supported on future releases");
                arrayList.add(new DefaultAppInfo(context, this.mPm, this.mUserId, new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name)));
            }
        }
        return arrayList;
    }

    public static String getDefaultKey(Context context, int i) {
        ComponentName unflattenFromString;
        String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), "autofill_service", i);
        if (stringForUser == null || (unflattenFromString = ComponentName.unflattenFromString(stringForUser)) == null) {
            return null;
        }
        return unflattenFromString.flattenToString();
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        return getDefaultKey(getContext(), this.mUserId);
    }

    /* access modifiers changed from: protected */
    public CharSequence getConfirmationMessage(CandidateInfo candidateInfo) {
        if (candidateInfo == null) {
            return null;
        }
        CharSequence loadLabel = candidateInfo.loadLabel();
        return Html.fromHtml(getContext().getString(C1715R.string.autofill_confirmation_message, new Object[]{loadLabel}));
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        String stringExtra;
        Settings.Secure.putStringForUser(getContext().getContentResolver(), "autofill_service", str, this.mUserId);
        FragmentActivity activity = getActivity();
        if (activity == null || (stringExtra = activity.getIntent().getStringExtra("package_name")) == null) {
            return true;
        }
        activity.setResult((str == null || !str.startsWith(stringExtra)) ? 0 : -1);
        activity.finish();
        return true;
    }

    static final class AutofillSettingIntentProvider implements SettingIntentProvider {
        private final Context mContext;
        private final String mSelectedKey;
        private final int mUserId;

        public AutofillSettingIntentProvider(Context context, int i, String str) {
            this.mSelectedKey = str;
            this.mContext = context;
            this.mUserId = i;
        }

        public Intent getIntent() {
            for (ResolveInfo resolveInfo : this.mContext.getPackageManager().queryIntentServicesAsUser(DefaultAutofillPicker.AUTOFILL_PROBE, 128, this.mUserId)) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (TextUtils.equals(this.mSelectedKey, new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString())) {
                    try {
                        String settingsActivity = new AutofillServiceInfo(this.mContext, serviceInfo).getSettingsActivity();
                        if (TextUtils.isEmpty(settingsActivity)) {
                            return null;
                        }
                        return new Intent("android.intent.action.MAIN").setComponent(new ComponentName(serviceInfo.packageName, settingsActivity));
                    } catch (SecurityException e) {
                        Log.w("DefaultAutofillPicker", "Error getting info for " + serviceInfo + ": " + e);
                    }
                }
            }
            return null;
        }
    }
}
