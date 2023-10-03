package com.android.settings;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.EncryptionInterstitial;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import com.havoc.config.center.C1715R;
import java.util.List;

public class EncryptionInterstitial extends SettingsActivity {
    /* access modifiers changed from: private */
    public static final String TAG = "EncryptionInterstitial";

    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", EncryptionInterstitialFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return EncryptionInterstitialFragment.class.getName().equals(str);
    }

    public static Intent createStartIntent(Context context, int i, boolean z, Intent intent) {
        return new Intent(context, EncryptionInterstitial.class).putExtra("extra_password_quality", i).putExtra(":settings:show_fragment_title_resid", C1715R.string.encryption_interstitial_header).putExtra("extra_require_password", z).putExtra("extra_unlock_method_intent", intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        findViewById(C1715R.C1718id.content_parent).setFitsSystemWindows(false);
    }

    public static class EncryptionInterstitialFragment extends InstrumentedFragment {
        private boolean mPasswordRequired;
        private int mRequestedPasswordQuality;
        private Intent mUnlockMethodIntent;

        public int getMetricsCategory() {
            return 48;
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(C1715R.layout.encryption_interstitial, viewGroup, false);
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            boolean booleanExtra = getActivity().getIntent().getBooleanExtra("for_fingerprint", false);
            boolean booleanExtra2 = getActivity().getIntent().getBooleanExtra("for_face", false);
            Intent intent = getActivity().getIntent();
            this.mRequestedPasswordQuality = intent.getIntExtra("extra_password_quality", 0);
            this.mUnlockMethodIntent = (Intent) intent.getParcelableExtra("extra_unlock_method_intent");
            int i = this.mRequestedPasswordQuality;
            ((TextView) getActivity().findViewById(C1715R.C1718id.sud_layout_description)).setText(i != 65536 ? (i == 131072 || i == 196608) ? booleanExtra ? C1715R.string.encryption_interstitial_message_pin_for_fingerprint : booleanExtra2 ? C1715R.string.encryption_interstitial_message_pin_for_face : C1715R.string.encryption_interstitial_message_pin : booleanExtra ? C1715R.string.encryption_interstitial_message_password_for_fingerprint : booleanExtra2 ? C1715R.string.encryption_interstitial_message_password_for_face : C1715R.string.encryption_interstitial_message_password : booleanExtra ? C1715R.string.encryption_interstitial_message_pattern_for_fingerprint : booleanExtra2 ? C1715R.string.encryption_interstitial_message_pattern_for_face : C1715R.string.encryption_interstitial_message_pattern);
            setRequirePasswordState(getActivity().getIntent().getBooleanExtra("extra_require_password", true));
            GlifLayout glifLayout = (GlifLayout) view;
            glifLayout.setHeaderText(getActivity().getTitle());
            FooterBarMixin footerBarMixin = (FooterBarMixin) glifLayout.getMixin(FooterBarMixin.class);
            FooterButton.Builder builder = new FooterButton.Builder(getContext());
            builder.setText(C1715R.string.encryption_interstitial_no);
            builder.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    EncryptionInterstitial.EncryptionInterstitialFragment.this.onNoButtonClicked(view);
                }
            });
            builder.setButtonType(7);
            builder.setTheme(2131952051);
            footerBarMixin.setSecondaryButton(builder.build());
            FooterButton.Builder builder2 = new FooterButton.Builder(getContext());
            builder2.setText(C1715R.string.encryption_interstitial_yes);
            builder2.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    EncryptionInterstitial.EncryptionInterstitialFragment.this.onYesButtonClicked(view);
                }
            });
            builder2.setButtonType(5);
            builder2.setTheme(2131952050);
            footerBarMixin.setPrimaryButton(builder2.build());
        }

        /* access modifiers changed from: protected */
        public void startLockIntent() {
            Intent intent = this.mUnlockMethodIntent;
            if (intent != null) {
                intent.putExtra("extra_require_password", this.mPasswordRequired);
                startActivityForResult(this.mUnlockMethodIntent, 100);
                return;
            }
            Log.wtf(EncryptionInterstitial.TAG, "no unlock intent to start");
            finish();
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i == 100 && i2 != 0) {
                getActivity().setResult(i2, intent);
                finish();
            }
        }

        /* access modifiers changed from: private */
        public void onYesButtonClicked(View view) {
            if (!AccessibilityManager.getInstance(getActivity()).isEnabled() || this.mPasswordRequired) {
                setRequirePasswordState(true);
                startLockIntent();
                return;
            }
            setRequirePasswordState(false);
            AccessibilityWarningDialogFragment.newInstance(this.mRequestedPasswordQuality).show(getChildFragmentManager(), "AccessibilityWarningDialog");
        }

        /* access modifiers changed from: private */
        public void onNoButtonClicked(View view) {
            setRequirePasswordState(false);
            startLockIntent();
        }

        /* access modifiers changed from: private */
        public void setRequirePasswordState(boolean z) {
            this.mPasswordRequired = z;
        }

        public void finish() {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    activity.finish();
                }
            }
        }
    }

    public static class AccessibilityWarningDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        public int getMetricsCategory() {
            return 581;
        }

        public static AccessibilityWarningDialogFragment newInstance(int i) {
            AccessibilityWarningDialogFragment accessibilityWarningDialogFragment = new AccessibilityWarningDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putInt("extra_password_quality", i);
            accessibilityWarningDialogFragment.setArguments(bundle);
            return accessibilityWarningDialogFragment;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            int i;
            int i2;
            Object obj;
            int i3 = getArguments().getInt("extra_password_quality");
            if (i3 == 65536) {
                i = C1715R.string.encrypt_talkback_dialog_require_pattern;
                i2 = C1715R.string.encrypt_talkback_dialog_message_pattern;
            } else if (i3 == 131072 || i3 == 196608) {
                i = C1715R.string.encrypt_talkback_dialog_require_pin;
                i2 = C1715R.string.encrypt_talkback_dialog_message_pin;
            } else {
                i = C1715R.string.encrypt_talkback_dialog_require_password;
                i2 = C1715R.string.encrypt_talkback_dialog_message_password;
            }
            FragmentActivity activity = getActivity();
            List<AccessibilityServiceInfo> enabledAccessibilityServiceList = AccessibilityManager.getInstance(activity).getEnabledAccessibilityServiceList(-1);
            if (enabledAccessibilityServiceList.isEmpty()) {
                obj = "";
            } else {
                obj = enabledAccessibilityServiceList.get(0).getResolveInfo().loadLabel(activity.getPackageManager());
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(i);
            builder.setMessage((CharSequence) getString(i2, obj));
            builder.setCancelable(true);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) this);
            return builder.create();
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            EncryptionInterstitialFragment encryptionInterstitialFragment = (EncryptionInterstitialFragment) getParentFragment();
            if (encryptionInterstitialFragment == null) {
                return;
            }
            if (i == -1) {
                encryptionInterstitialFragment.setRequirePasswordState(true);
                encryptionInterstitialFragment.startLockIntent();
            } else if (i == -2) {
                encryptionInterstitialFragment.setRequirePasswordState(false);
            }
        }
    }
}
