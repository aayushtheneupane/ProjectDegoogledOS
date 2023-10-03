package com.android.settings.biometrics.fingerprint;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.Fingerprint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.SubSettings;
import com.android.settings.Utils;
import com.android.settings.biometrics.fingerprint.FingerprintAuthenticateSidecar;
import com.android.settings.biometrics.fingerprint.FingerprintRemoveSidecar;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.password.ChooseLockGeneric;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settings.utils.AnnotationSpan;
import com.android.settings.widget.ImeAwareEditText;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.TwoTargetPreference;
import com.android.settingslib.widget.FooterPreference;
import com.havoc.config.center.C1715R;
import java.util.HashMap;
import java.util.List;

public class FingerprintSettings extends SubSettings {
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", FingerprintSettingsFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return FingerprintSettingsFragment.class.getName().equals(str);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getText(C1715R.string.security_settings_fingerprint_preference_title));
    }

    public static class FingerprintSettingsFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, FingerprintPreference.OnDeleteClickListener {
        FingerprintAuthenticateSidecar.Listener mAuthenticateListener = new FingerprintAuthenticateSidecar.Listener() {
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1001, authenticationResult.getFingerprint().getBiometricId(), 0).sendToTarget();
            }

            public void onAuthenticationFailed() {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1002).sendToTarget();
            }

            public void onAuthenticationError(int i, CharSequence charSequence) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1003, i, 0, charSequence).sendToTarget();
            }

            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1004, i, 0, charSequence).sendToTarget();
            }
        };
        private FingerprintAuthenticateSidecar mAuthenticateSidecar;
        private final Runnable mFingerprintLockoutReset = new Runnable() {
            public void run() {
                boolean unused = FingerprintSettingsFragment.this.mInFingerprintLockout = false;
                FingerprintSettingsFragment.this.retryFingerprint();
            }
        };
        private FingerprintManager mFingerprintManager;
        private HashMap<Integer, String> mFingerprintsRenaming;
        /* access modifiers changed from: private */
        public final Handler mHandler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1000:
                        FingerprintSettingsFragment.this.removeFingerprintPreference(message.arg1);
                        FingerprintSettingsFragment.this.updateAddPreference();
                        FingerprintSettingsFragment.this.retryFingerprint();
                        return;
                    case 1001:
                        FingerprintSettingsFragment.this.highlightFingerprintItem(message.arg1);
                        FingerprintSettingsFragment.this.retryFingerprint();
                        return;
                    case 1003:
                        FingerprintSettingsFragment.this.handleError(message.arg1, (CharSequence) message.obj);
                        return;
                    default:
                        return;
                }
            }
        };
        private boolean mHasFod;
        private Drawable mHighlightDrawable;
        /* access modifiers changed from: private */
        public boolean mInFingerprintLockout;
        private boolean mLaunchedConfirm;
        FingerprintRemoveSidecar.Listener mRemovalListener = new FingerprintRemoveSidecar.Listener() {
            public void onRemovalSucceeded(Fingerprint fingerprint) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1000, fingerprint.getBiometricId(), 0).sendToTarget();
                updateDialog();
            }

            public void onRemovalError(Fingerprint fingerprint, int i, CharSequence charSequence) {
                FragmentActivity activity = FingerprintSettingsFragment.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, charSequence, 0);
                }
                updateDialog();
            }

            private void updateDialog() {
                RenameDialog renameDialog = (RenameDialog) FingerprintSettingsFragment.this.getFragmentManager().findFragmentByTag(RenameDialog.class.getName());
                if (renameDialog != null) {
                    renameDialog.enableDelete();
                }
            }
        };
        private FingerprintRemoveSidecar mRemovalSidecar;
        private byte[] mToken;
        private int mUserId;

        public int getHelpResource() {
            return C1715R.string.help_url_fingerprint;
        }

        public int getMetricsCategory() {
            return 49;
        }

        /* access modifiers changed from: protected */
        public void handleError(int i, CharSequence charSequence) {
            FragmentActivity activity;
            if (i != 5) {
                if (i == 7) {
                    this.mInFingerprintLockout = true;
                    if (!this.mHandler.hasCallbacks(this.mFingerprintLockoutReset)) {
                        this.mHandler.postDelayed(this.mFingerprintLockoutReset, 30000);
                    }
                } else if (i == 9) {
                    this.mInFingerprintLockout = true;
                }
                if (this.mInFingerprintLockout && (activity = getActivity()) != null) {
                    Toast.makeText(activity, charSequence, 0).show();
                }
                retryFingerprint();
            }
        }

        /* access modifiers changed from: private */
        public void retryFingerprint() {
            this.mHasFod = getPackageManager().hasSystemFeature("vendor.lineage.biometrics.fingerprint.inscreen");
            if (!this.mRemovalSidecar.inProgress() && this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() != 0 && !this.mLaunchedConfirm && !this.mHasFod && !this.mInFingerprintLockout) {
                this.mAuthenticateSidecar.startAuthentication(this.mUserId);
                this.mAuthenticateSidecar.setListener(this.mAuthenticateListener);
            }
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            FragmentActivity activity = getActivity();
            this.mFingerprintManager = Utils.getFingerprintManagerOrNull(activity);
            this.mToken = getIntent().getByteArrayExtra("hw_auth_token");
            this.mAuthenticateSidecar = (FingerprintAuthenticateSidecar) getFragmentManager().findFragmentByTag("authenticate_sidecar");
            if (this.mAuthenticateSidecar == null) {
                this.mAuthenticateSidecar = new FingerprintAuthenticateSidecar();
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                beginTransaction.add((Fragment) this.mAuthenticateSidecar, "authenticate_sidecar");
                beginTransaction.commit();
            }
            this.mAuthenticateSidecar.setFingerprintManager(this.mFingerprintManager);
            this.mRemovalSidecar = (FingerprintRemoveSidecar) getFragmentManager().findFragmentByTag("removal_sidecar");
            if (this.mRemovalSidecar == null) {
                this.mRemovalSidecar = new FingerprintRemoveSidecar();
                FragmentTransaction beginTransaction2 = getFragmentManager().beginTransaction();
                beginTransaction2.add((Fragment) this.mRemovalSidecar, "removal_sidecar");
                beginTransaction2.commit();
            }
            this.mRemovalSidecar.setFingerprintManager(this.mFingerprintManager);
            this.mRemovalSidecar.setListener(this.mRemovalListener);
            RenameDialog renameDialog = (RenameDialog) getFragmentManager().findFragmentByTag(RenameDialog.class.getName());
            if (renameDialog != null) {
                renameDialog.setDeleteInProgress(this.mRemovalSidecar.inProgress());
            }
            this.mFingerprintsRenaming = new HashMap<>();
            if (bundle != null) {
                this.mFingerprintsRenaming = (HashMap) bundle.getSerializable("mFingerprintsRenaming");
                this.mToken = bundle.getByteArray("hw_auth_token");
                this.mLaunchedConfirm = bundle.getBoolean("launched_confirm", false);
            }
            this.mUserId = getActivity().getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
            if (this.mToken == null && !this.mLaunchedConfirm) {
                this.mLaunchedConfirm = true;
                launchChooseOrConfirmLock();
            }
            FooterPreference createFooterPreference = this.mFooterPreferenceMixin.createFooterPreference();
            RestrictedLockUtils.EnforcedAdmin checkIfKeyguardFeaturesDisabled = RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(activity, 32, this.mUserId);
            AnnotationSpan.LinkInfo linkInfo = new AnnotationSpan.LinkInfo("admin_details", new View.OnClickListener(activity, checkIfKeyguardFeaturesDisabled) {
                private final /* synthetic */ Activity f$0;
                private final /* synthetic */ RestrictedLockUtils.EnforcedAdmin f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.f$0, this.f$1);
                }
            });
            createFooterPreference.setTitle(AnnotationSpan.linkify(getText(checkIfKeyguardFeaturesDisabled != null ? C1715R.string.f98x60fec2a1 : C1715R.string.security_settings_fingerprint_enroll_disclaimer), new AnnotationSpan.LinkInfo(activity, "url", HelpUtils.getHelpIntent(activity, getString(getHelpResource()), activity.getClass().getName())), linkInfo));
        }

        /* access modifiers changed from: protected */
        public void removeFingerprintPreference(int i) {
            String genKey = genKey(i);
            Preference findPreference = findPreference(genKey);
            if (findPreference == null) {
                Log.w("FingerprintSettings", "Can't find preference to remove: " + genKey);
            } else if (!getPreferenceScreen().removePreference(findPreference)) {
                Log.w("FingerprintSettings", "Failed to remove preference with key " + genKey);
            }
        }

        private PreferenceScreen createPreferenceHierarchy() {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.removeAll();
            }
            addPreferencesFromResource(C1715R.xml.security_settings_fingerprint);
            PreferenceScreen preferenceScreen2 = getPreferenceScreen();
            addFingerprintItemPreferences(preferenceScreen2);
            setPreferenceScreen(preferenceScreen2);
            return preferenceScreen2;
        }

        private void addFingerprintItemPreferences(PreferenceGroup preferenceGroup) {
            preferenceGroup.removeAll();
            List enrolledFingerprints = this.mFingerprintManager.getEnrolledFingerprints(this.mUserId);
            int size = enrolledFingerprints.size();
            for (int i = 0; i < size; i++) {
                Fingerprint fingerprint = (Fingerprint) enrolledFingerprints.get(i);
                FingerprintPreference fingerprintPreference = new FingerprintPreference(preferenceGroup.getContext(), this);
                fingerprintPreference.setKey(genKey(fingerprint.getBiometricId()));
                fingerprintPreference.setTitle(fingerprint.getName());
                fingerprintPreference.setFingerprint(fingerprint);
                fingerprintPreference.setPersistent(false);
                fingerprintPreference.setIcon((int) C1715R.C1717drawable.ic_fingerprint_24dp);
                if (this.mRemovalSidecar.isRemovingFingerprint(fingerprint.getBiometricId())) {
                    fingerprintPreference.setEnabled(false);
                }
                if (this.mFingerprintsRenaming.containsKey(Integer.valueOf(fingerprint.getBiometricId()))) {
                    fingerprintPreference.setTitle((CharSequence) this.mFingerprintsRenaming.get(Integer.valueOf(fingerprint.getBiometricId())));
                }
                preferenceGroup.addPreference(fingerprintPreference);
                fingerprintPreference.setOnPreferenceChangeListener(this);
            }
            Preference preference = new Preference(preferenceGroup.getContext());
            preference.setKey("key_fingerprint_add");
            preference.setTitle((int) C1715R.string.fingerprint_add_title);
            preference.setIcon((int) C1715R.C1717drawable.ic_add_24dp);
            preferenceGroup.addPreference(preference);
            preference.setOnPreferenceChangeListener(this);
            updateAddPreference();
        }

        /* access modifiers changed from: private */
        public void updateAddPreference() {
            if (getActivity() != null) {
                int integer = getContext().getResources().getInteger(17694825);
                boolean z = true;
                boolean z2 = this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() >= integer;
                boolean inProgress = this.mRemovalSidecar.inProgress();
                String string = z2 ? getContext().getString(C1715R.string.fingerprint_add_max, new Object[]{Integer.valueOf(integer)}) : "";
                Preference findPreference = findPreference("key_fingerprint_add");
                findPreference.setSummary((CharSequence) string);
                if (z2 || inProgress) {
                    z = false;
                }
                findPreference.setEnabled(z);
            }
        }

        private static String genKey(int i) {
            return "key_fingerprint_item_" + i;
        }

        public void onResume() {
            super.onResume();
            this.mInFingerprintLockout = false;
            updatePreferences();
            FingerprintRemoveSidecar fingerprintRemoveSidecar = this.mRemovalSidecar;
            if (fingerprintRemoveSidecar != null) {
                fingerprintRemoveSidecar.setListener(this.mRemovalListener);
            }
        }

        private void updatePreferences() {
            createPreferenceHierarchy();
            retryFingerprint();
        }

        public void onPause() {
            super.onPause();
            FingerprintRemoveSidecar fingerprintRemoveSidecar = this.mRemovalSidecar;
            if (fingerprintRemoveSidecar != null) {
                fingerprintRemoveSidecar.setListener((FingerprintRemoveSidecar.Listener) null);
            }
            FingerprintAuthenticateSidecar fingerprintAuthenticateSidecar = this.mAuthenticateSidecar;
            if (fingerprintAuthenticateSidecar != null) {
                fingerprintAuthenticateSidecar.setListener((FingerprintAuthenticateSidecar.Listener) null);
                this.mAuthenticateSidecar.stopAuthentication();
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            bundle.putByteArray("hw_auth_token", this.mToken);
            bundle.putBoolean("launched_confirm", this.mLaunchedConfirm);
            bundle.putSerializable("mFingerprintsRenaming", this.mFingerprintsRenaming);
        }

        public boolean onPreferenceTreeClick(Preference preference) {
            if ("key_fingerprint_add".equals(preference.getKey())) {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", FingerprintEnrollEnrolling.class.getName());
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                intent.putExtra("hw_auth_token", this.mToken);
                startActivityForResult(intent, 10);
            } else if (preference instanceof FingerprintPreference) {
                showRenameDialog(((FingerprintPreference) preference).getFingerprint());
            }
            return super.onPreferenceTreeClick(preference);
        }

        public void onDeleteClick(FingerprintPreference fingerprintPreference) {
            boolean z = true;
            if (this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() <= 1) {
                z = false;
            }
            Fingerprint fingerprint = fingerprintPreference.getFingerprint();
            if (!z) {
                ConfirmLastDeleteDialog confirmLastDeleteDialog = new ConfirmLastDeleteDialog();
                boolean isManagedProfile = UserManager.get(getContext()).isManagedProfile(this.mUserId);
                Bundle bundle = new Bundle();
                bundle.putParcelable("fingerprint", fingerprint);
                bundle.putBoolean("isProfileChallengeUser", isManagedProfile);
                confirmLastDeleteDialog.setArguments(bundle);
                confirmLastDeleteDialog.setTargetFragment(this, 0);
                confirmLastDeleteDialog.show(getFragmentManager(), ConfirmLastDeleteDialog.class.getName());
            } else if (this.mRemovalSidecar.inProgress()) {
                Log.d("FingerprintSettings", "Fingerprint delete in progress, skipping");
            } else {
                DeleteFingerprintDialog.newInstance(fingerprint, this).show(getFragmentManager(), DeleteFingerprintDialog.class.getName());
            }
        }

        private void showRenameDialog(Fingerprint fingerprint) {
            RenameDialog renameDialog = new RenameDialog();
            Bundle bundle = new Bundle();
            if (this.mFingerprintsRenaming.containsKey(Integer.valueOf(fingerprint.getBiometricId()))) {
                bundle.putParcelable("fingerprint", new Fingerprint(this.mFingerprintsRenaming.get(Integer.valueOf(fingerprint.getBiometricId())), fingerprint.getGroupId(), fingerprint.getBiometricId(), fingerprint.getDeviceId()));
            } else {
                bundle.putParcelable("fingerprint", fingerprint);
            }
            renameDialog.setDeleteInProgress(this.mRemovalSidecar.inProgress());
            renameDialog.setArguments(bundle);
            renameDialog.setTargetFragment(this, 0);
            renameDialog.show(getFragmentManager(), RenameDialog.class.getName());
        }

        public boolean onPreferenceChange(Preference preference, Object obj) {
            String key = preference.getKey();
            if ("fingerprint_enable_keyguard_toggle".equals(key)) {
                return true;
            }
            Log.v("FingerprintSettings", "Unknown key:" + key);
            return true;
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i == 102 || i == 101) {
                this.mLaunchedConfirm = false;
                if ((i2 == 1 || i2 == -1) && intent != null) {
                    this.mToken = intent.getByteArrayExtra("hw_auth_token");
                }
            } else if (i == 10 && i2 == 3) {
                FragmentActivity activity = getActivity();
                activity.setResult(3);
                activity.finish();
            }
            if (this.mToken == null) {
                getActivity().finish();
            }
        }

        public void onDestroy() {
            int postEnroll;
            super.onDestroy();
            if (getActivity().isFinishing() && (postEnroll = this.mFingerprintManager.postEnroll()) < 0) {
                Log.w("FingerprintSettings", "postEnroll failed: result = " + postEnroll);
            }
        }

        private Drawable getHighlightDrawable() {
            FragmentActivity activity;
            if (this.mHighlightDrawable == null && (activity = getActivity()) != null) {
                this.mHighlightDrawable = activity.getDrawable(C1715R.C1717drawable.preference_highlight);
            }
            return this.mHighlightDrawable;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
            r4 = r4.getView();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void highlightFingerprintItem(int r4) {
            /*
                r3 = this;
                java.lang.String r4 = genKey(r4)
                androidx.preference.Preference r4 = r3.findPreference(r4)
                com.android.settings.biometrics.fingerprint.FingerprintSettings$FingerprintPreference r4 = (com.android.settings.biometrics.fingerprint.FingerprintSettings.FingerprintPreference) r4
                android.graphics.drawable.Drawable r0 = r3.getHighlightDrawable()
                if (r0 == 0) goto L_0x0041
                if (r4 == 0) goto L_0x0041
                android.view.View r4 = r4.getView()
                if (r4 != 0) goto L_0x0019
                return
            L_0x0019:
                int r1 = r4.getWidth()
                int r1 = r1 / 2
                int r2 = r4.getHeight()
                int r2 = r2 / 2
                float r1 = (float) r1
                float r2 = (float) r2
                r0.setHotspot(r1, r2)
                r4.setBackground(r0)
                r0 = 1
                r4.setPressed(r0)
                r0 = 0
                r4.setPressed(r0)
                android.os.Handler r0 = r3.mHandler
                com.android.settings.biometrics.fingerprint.FingerprintSettings$FingerprintSettingsFragment$4 r1 = new com.android.settings.biometrics.fingerprint.FingerprintSettings$FingerprintSettingsFragment$4
                r1.<init>(r4)
                r3 = 500(0x1f4, double:2.47E-321)
                r0.postDelayed(r1, r3)
            L_0x0041:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.highlightFingerprintItem(int):void");
        }

        private void launchChooseOrConfirmLock() {
            Intent intent = new Intent();
            long preEnroll = this.mFingerprintManager.preEnroll();
            if (!new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(101, (CharSequence) getString(C1715R.string.security_settings_fingerprint_preference_title), (CharSequence) null, (CharSequence) null, preEnroll, this.mUserId, true)) {
                intent.setClassName("com.android.settings", ChooseLockGeneric.class.getName());
                intent.putExtra("minimum_quality", 65536);
                intent.putExtra("hide_disabled_prefs", true);
                intent.putExtra("has_challenge", true);
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                intent.putExtra("challenge", preEnroll);
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                startActivityForResult(intent, 102);
            }
        }

        /* access modifiers changed from: package-private */
        public void deleteFingerPrint(Fingerprint fingerprint) {
            this.mRemovalSidecar.startRemove(fingerprint, this.mUserId);
            findPreference(genKey(fingerprint.getBiometricId())).setEnabled(false);
            updateAddPreference();
        }

        /* access modifiers changed from: private */
        public void renameFingerPrint(int i, String str) {
            this.mFingerprintManager.rename(i, this.mUserId, str);
            if (!TextUtils.isEmpty(str)) {
                this.mFingerprintsRenaming.put(Integer.valueOf(i), str);
            }
            updatePreferences();
        }

        public static class DeleteFingerprintDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
            private AlertDialog mAlertDialog;
            private Fingerprint mFp;

            public int getMetricsCategory() {
                return 570;
            }

            public static DeleteFingerprintDialog newInstance(Fingerprint fingerprint, FingerprintSettingsFragment fingerprintSettingsFragment) {
                DeleteFingerprintDialog deleteFingerprintDialog = new DeleteFingerprintDialog();
                Bundle bundle = new Bundle();
                bundle.putParcelable("fingerprint", fingerprint);
                deleteFingerprintDialog.setArguments(bundle);
                deleteFingerprintDialog.setTargetFragment(fingerprintSettingsFragment, 0);
                return deleteFingerprintDialog;
            }

            public Dialog onCreateDialog(Bundle bundle) {
                this.mFp = getArguments().getParcelable("fingerprint");
                String string = getString(C1715R.string.fingerprint_delete_title, this.mFp.getName());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle((CharSequence) string);
                builder.setMessage((int) C1715R.string.fingerprint_delete_message);
                builder.setPositiveButton((int) C1715R.string.security_settings_fingerprint_enroll_dialog_delete, (DialogInterface.OnClickListener) this);
                builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
                this.mAlertDialog = builder.create();
                return this.mAlertDialog;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    int biometricId = this.mFp.getBiometricId();
                    Log.v("FingerprintSettings", "Removing fpId=" + biometricId);
                    this.mMetricsFeatureProvider.action(getContext(), 253, biometricId);
                    ((FingerprintSettingsFragment) getTargetFragment()).deleteFingerPrint(this.mFp);
                }
            }
        }

        public static class RenameDialog extends InstrumentedDialogFragment {
            /* access modifiers changed from: private */
            public AlertDialog mAlertDialog;
            /* access modifiers changed from: private */
            public boolean mDeleteInProgress;
            /* access modifiers changed from: private */
            public ImeAwareEditText mDialogTextField;
            /* access modifiers changed from: private */
            public Fingerprint mFp;

            public int getMetricsCategory() {
                return 570;
            }

            public void setDeleteInProgress(boolean z) {
                this.mDeleteInProgress = z;
            }

            public Dialog onCreateDialog(Bundle bundle) {
                final int i;
                final String str;
                this.mFp = getArguments().getParcelable("fingerprint");
                final int i2 = -1;
                if (bundle != null) {
                    str = bundle.getString("fingerName");
                    int i3 = bundle.getInt("startSelection", -1);
                    i = bundle.getInt("endSelection", -1);
                    i2 = i3;
                } else {
                    str = null;
                    i = -1;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView((int) C1715R.layout.fingerprint_rename_dialog);
                builder.setPositiveButton((int) C1715R.string.security_settings_fingerprint_enroll_dialog_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String obj = RenameDialog.this.mDialogTextField.getText().toString();
                        CharSequence name = RenameDialog.this.mFp.getName();
                        if (!TextUtils.equals(obj, name)) {
                            Log.d("FingerprintSettings", "rename " + name + " to " + obj);
                            RenameDialog.this.mMetricsFeatureProvider.action(RenameDialog.this.getContext(), 254, RenameDialog.this.mFp.getBiometricId());
                            ((FingerprintSettingsFragment) RenameDialog.this.getTargetFragment()).renameFingerPrint(RenameDialog.this.mFp.getBiometricId(), obj);
                        }
                        dialogInterface.dismiss();
                    }
                });
                this.mAlertDialog = builder.create();
                this.mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    public void onShow(DialogInterface dialogInterface) {
                        RenameDialog renameDialog = RenameDialog.this;
                        ImeAwareEditText unused = renameDialog.mDialogTextField = (ImeAwareEditText) renameDialog.mAlertDialog.findViewById(C1715R.C1718id.fingerprint_rename_field);
                        CharSequence charSequence = str;
                        if (charSequence == null) {
                            charSequence = RenameDialog.this.mFp.getName();
                        }
                        RenameDialog.this.mDialogTextField.setText(charSequence);
                        if (i2 == -1 || i == -1) {
                            RenameDialog.this.mDialogTextField.selectAll();
                        } else {
                            RenameDialog.this.mDialogTextField.setSelection(i2, i);
                        }
                        if (RenameDialog.this.mDeleteInProgress) {
                            RenameDialog.this.mAlertDialog.getButton(-2).setEnabled(false);
                        }
                        RenameDialog.this.mDialogTextField.requestFocus();
                        RenameDialog.this.mDialogTextField.scheduleShowSoftInput();
                    }
                });
                return this.mAlertDialog;
            }

            public void enableDelete() {
                this.mDeleteInProgress = false;
                AlertDialog alertDialog = this.mAlertDialog;
                if (alertDialog != null) {
                    alertDialog.getButton(-2).setEnabled(true);
                }
            }

            public void onSaveInstanceState(Bundle bundle) {
                super.onSaveInstanceState(bundle);
                ImeAwareEditText imeAwareEditText = this.mDialogTextField;
                if (imeAwareEditText != null) {
                    bundle.putString("fingerName", imeAwareEditText.getText().toString());
                    bundle.putInt("startSelection", this.mDialogTextField.getSelectionStart());
                    bundle.putInt("endSelection", this.mDialogTextField.getSelectionEnd());
                }
            }
        }

        public static class ConfirmLastDeleteDialog extends InstrumentedDialogFragment {
            /* access modifiers changed from: private */
            public Fingerprint mFp;

            public int getMetricsCategory() {
                return 571;
            }

            public Dialog onCreateDialog(Bundle bundle) {
                this.mFp = getArguments().getParcelable("fingerprint");
                boolean z = getArguments().getBoolean("isProfileChallengeUser");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle((int) C1715R.string.fingerprint_last_delete_title);
                builder.setMessage(z ? C1715R.string.fingerprint_last_delete_message_profile_challenge : C1715R.string.fingerprint_last_delete_message);
                builder.setPositiveButton((int) C1715R.string.fingerprint_last_delete_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((FingerprintSettingsFragment) ConfirmLastDeleteDialog.this.getTargetFragment()).deleteFingerPrint(ConfirmLastDeleteDialog.this.mFp);
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                return builder.create();
            }
        }
    }

    public static class FingerprintPreference extends TwoTargetPreference {
        private View mDeleteView;
        private Fingerprint mFingerprint;
        /* access modifiers changed from: private */
        public final OnDeleteClickListener mOnDeleteClickListener;
        private View mView;

        public interface OnDeleteClickListener {
            void onDeleteClick(FingerprintPreference fingerprintPreference);
        }

        /* access modifiers changed from: protected */
        public int getSecondTargetResId() {
            return C1715R.layout.preference_widget_delete;
        }

        public FingerprintPreference(Context context, OnDeleteClickListener onDeleteClickListener) {
            super(context);
            this.mOnDeleteClickListener = onDeleteClickListener;
        }

        public View getView() {
            return this.mView;
        }

        public void setFingerprint(Fingerprint fingerprint) {
            this.mFingerprint = fingerprint;
        }

        public Fingerprint getFingerprint() {
            return this.mFingerprint;
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            View view = preferenceViewHolder.itemView;
            this.mView = view;
            this.mDeleteView = view.findViewById(C1715R.C1718id.delete_button);
            this.mDeleteView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FingerprintPreference.this.mOnDeleteClickListener != null) {
                        FingerprintPreference.this.mOnDeleteClickListener.onDeleteClick(FingerprintPreference.this);
                    }
                }
            });
        }
    }
}
