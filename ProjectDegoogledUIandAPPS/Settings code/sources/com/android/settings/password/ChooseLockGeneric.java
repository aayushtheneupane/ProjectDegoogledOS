package com.android.settings.password;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.hardware.face.Face;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.Fingerprint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.EncryptionInterstitial;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollActivity;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.password.ChooseLockPassword;
import com.android.settings.password.ChooseLockPattern;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.havoc.config.center.C1715R;
import java.util.List;

public class ChooseLockGeneric extends SettingsActivity {

    public static class InternalActivity extends ChooseLockGeneric {
    }

    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", getFragmentClass().getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return ChooseLockGenericFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public Class<? extends Fragment> getFragmentClass() {
        return ChooseLockGenericFragment.class;
    }

    public static class ChooseLockGenericFragment extends SettingsPreferenceFragment {
        static final int CHOOSE_LOCK_BEFORE_BIOMETRIC_REQUEST = 103;
        static final int CHOOSE_LOCK_REQUEST = 102;
        static final int CONFIRM_EXISTING_REQUEST = 100;
        static final int ENABLE_ENCRYPTION_REQUEST = 101;
        static final int SKIP_FINGERPRINT_REQUEST = 104;
        private String mCallerAppName = null;
        private long mChallenge;
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;
        private ChooseLockGenericController mController;
        private DevicePolicyManager mDPM;
        private FaceManager mFaceManager;
        private FingerprintManager mFingerprintManager;
        private boolean mForChangeCredRequiredForBoot = false;
        protected boolean mForFace = false;
        protected boolean mForFingerprint = false;
        private boolean mHasChallenge = false;
        private boolean mIsCallingAppAdmin;
        private boolean mIsSetNewPassword = false;
        private LockPatternUtils mLockPatternUtils;
        private ManagedLockPasswordProvider mManagedPasswordProvider;
        private boolean mPasswordConfirmed = false;
        private int mRequestedMinComplexity;
        private int mUserId;
        private UserManager mUserManager;
        private byte[] mUserPassword;
        private boolean mWaitingForConfirmation = false;

        /* access modifiers changed from: protected */
        public boolean canRunBeforeDeviceProvisioned() {
            return false;
        }

        public int getHelpResource() {
            return C1715R.string.help_url_choose_lockscreen;
        }

        public int getMetricsCategory() {
            return 27;
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            FragmentActivity activity = getActivity();
            if (Utils.isDeviceProvisioned(activity) || canRunBeforeDeviceProvisioned()) {
                String action = getActivity().getIntent().getAction();
                this.mFingerprintManager = Utils.getFingerprintManagerOrNull(getActivity());
                this.mFaceManager = Utils.getFaceManagerOrNull(getActivity());
                this.mDPM = (DevicePolicyManager) getSystemService("device_policy");
                this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
                this.mLockPatternUtils = new LockPatternUtils(getActivity());
                boolean z = true;
                this.mIsSetNewPassword = "android.app.action.SET_NEW_PARENT_PROFILE_PASSWORD".equals(action) || "android.app.action.SET_NEW_PASSWORD".equals(action);
                boolean booleanExtra = getActivity().getIntent().getBooleanExtra("confirm_credentials", true);
                if (getActivity() instanceof InternalActivity) {
                    this.mPasswordConfirmed = !booleanExtra;
                    this.mUserPassword = getActivity().getIntent().getByteArrayExtra("password");
                }
                this.mHasChallenge = getActivity().getIntent().getBooleanExtra("has_challenge", false);
                this.mChallenge = getActivity().getIntent().getLongExtra("challenge", 0);
                this.mForFingerprint = getActivity().getIntent().getBooleanExtra("for_fingerprint", false);
                this.mForFace = getActivity().getIntent().getBooleanExtra("for_face", false);
                this.mRequestedMinComplexity = getActivity().getIntent().getIntExtra("requested_min_complexity", 0);
                this.mCallerAppName = getActivity().getIntent().getStringExtra("caller_app_name");
                this.mIsCallingAppAdmin = getActivity().getIntent().getBooleanExtra("is_calling_app_admin", false);
                this.mForChangeCredRequiredForBoot = getArguments() != null && getArguments().getBoolean("for_cred_req_boot");
                this.mUserManager = UserManager.get(getActivity());
                if (bundle != null) {
                    this.mPasswordConfirmed = bundle.getBoolean("password_confirmed");
                    this.mWaitingForConfirmation = bundle.getBoolean("waiting_for_confirmation");
                    if (this.mUserPassword == null) {
                        this.mUserPassword = bundle.getByteArray("password");
                    }
                }
                this.mUserId = Utils.getSecureTargetUser(getActivity().getActivityToken(), UserManager.get(getActivity()), getArguments(), getActivity().getIntent().getExtras()).getIdentifier();
                this.mController = new ChooseLockGenericController(getContext(), this.mUserId, this.mRequestedMinComplexity, this.mLockPatternUtils);
                if ("android.app.action.SET_NEW_PASSWORD".equals(action) && UserManager.get(getActivity()).isManagedProfile(this.mUserId) && this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mUserId)) {
                    getActivity().setTitle(C1715R.string.lock_settings_picker_title_profile);
                }
                this.mManagedPasswordProvider = ManagedLockPasswordProvider.get(getActivity(), this.mUserId);
                if (this.mPasswordConfirmed) {
                    if (bundle == null) {
                        z = false;
                    }
                    updatePreferencesOrFinish(z);
                    if (this.mForChangeCredRequiredForBoot) {
                        maybeEnableEncryption(this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId), false);
                    }
                } else if (!this.mWaitingForConfirmation) {
                    ChooseLockSettingsHelper chooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity(), this);
                    if (((UserManager.get(getActivity()).isManagedProfile(this.mUserId) && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mUserId)) && !this.mIsSetNewPassword) || !chooseLockSettingsHelper.launchConfirmationActivity(CONFIRM_EXISTING_REQUEST, getString(C1715R.string.unlock_set_unlock_launch_picker_title), true, this.mUserId)) {
                        this.mPasswordConfirmed = true;
                        if (bundle == null) {
                            z = false;
                        }
                        updatePreferencesOrFinish(z);
                    } else {
                        this.mWaitingForConfirmation = true;
                    }
                }
                addHeaderView();
                return;
            }
            Log.i("ChooseLockGenericFragment", "Refusing to start because device is not provisioned");
            activity.finish();
        }

        /* access modifiers changed from: protected */
        public Class<? extends InternalActivity> getInternalActivityClass() {
            return InternalActivity.class;
        }

        /* access modifiers changed from: protected */
        public void addHeaderView() {
            if (this.mForFingerprint) {
                setHeaderView(C1715R.layout.choose_lock_generic_fingerprint_header);
                if (this.mIsSetNewPassword) {
                    ((TextView) getHeaderView().findViewById(C1715R.C1718id.fingerprint_header_description)).setText(C1715R.string.fingerprint_unlock_title);
                }
            } else if (this.mForFace) {
                setHeaderView(C1715R.layout.choose_lock_generic_face_header);
                if (this.mIsSetNewPassword) {
                    ((TextView) getHeaderView().findViewById(C1715R.C1718id.face_header_description)).setText(C1715R.string.face_unlock_title);
                }
            }
        }

        public boolean onPreferenceTreeClick(Preference preference) {
            String key = preference.getKey();
            if (!isUnlockMethodSecure(key) && this.mLockPatternUtils.isSecure(this.mUserId)) {
                showFactoryResetProtectionWarningDialog(key);
                return true;
            } else if (!"unlock_skip_fingerprint".equals(key) && !"unlock_skip_face".equals(key)) {
                return setUnlockMethod(key);
            } else {
                Intent intent = new Intent(getActivity(), getInternalActivityClass());
                intent.setAction(getIntent().getAction());
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                intent.putExtra("confirm_credentials", !this.mPasswordConfirmed);
                intent.putExtra("requested_min_complexity", this.mRequestedMinComplexity);
                intent.putExtra("caller_app_name", this.mCallerAppName);
                byte[] bArr = this.mUserPassword;
                if (bArr != null) {
                    intent.putExtra("password", bArr);
                }
                startActivityForResult(intent, SKIP_FINGERPRINT_REQUEST);
                return true;
            }
        }

        private void maybeEnableEncryption(int i, boolean z) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService("device_policy");
            if (UserManager.get(getActivity()).isAdminUser() && this.mUserId == UserHandle.myUserId() && LockPatternUtils.isDeviceEncryptionEnabled() && !LockPatternUtils.isFileEncryptionEnabled() && !devicePolicyManager.getDoNotAskCredentialsOnBoot()) {
                Intent intentForUnlockMethod = getIntentForUnlockMethod(i);
                intentForUnlockMethod.putExtra("for_cred_req_boot", this.mForChangeCredRequiredForBoot);
                FragmentActivity activity = getActivity();
                Intent encryptionInterstitialIntent = getEncryptionInterstitialIntent(activity, i, this.mLockPatternUtils.isCredentialRequiredToDecrypt(!AccessibilityManager.getInstance(activity).isEnabled()), intentForUnlockMethod);
                encryptionInterstitialIntent.putExtra("for_fingerprint", this.mForFingerprint);
                encryptionInterstitialIntent.putExtra("for_face", this.mForFace);
                startActivityForResult(encryptionInterstitialIntent, (!this.mIsSetNewPassword || !this.mHasChallenge) ? ENABLE_ENCRYPTION_REQUEST : CHOOSE_LOCK_BEFORE_BIOMETRIC_REQUEST);
            } else if (this.mForChangeCredRequiredForBoot) {
                finish();
            } else {
                updateUnlockMethodAndFinish(i, z, false);
            }
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            this.mWaitingForConfirmation = false;
            if (i == CONFIRM_EXISTING_REQUEST && i2 == -1) {
                this.mPasswordConfirmed = true;
                this.mUserPassword = intent != null ? intent.getByteArrayExtra("password") : null;
                updatePreferencesOrFinish(false);
                if (this.mForChangeCredRequiredForBoot) {
                    byte[] bArr = this.mUserPassword;
                    if (bArr == null || bArr.length == 0) {
                        finish();
                    } else {
                        maybeEnableEncryption(this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId), false);
                    }
                }
            } else if (i == CHOOSE_LOCK_REQUEST || i == ENABLE_ENCRYPTION_REQUEST) {
                if (i2 != 0 || this.mForChangeCredRequiredForBoot) {
                    getActivity().setResult(i2, intent);
                    finish();
                } else if (getIntent().getIntExtra("lockscreen.password_type", -1) != -1) {
                    getActivity().setResult(0, intent);
                    finish();
                }
            } else if (i == CHOOSE_LOCK_BEFORE_BIOMETRIC_REQUEST && i2 == 1) {
                Intent biometricEnrollIntent = getBiometricEnrollIntent(getActivity());
                if (intent != null) {
                    biometricEnrollIntent.putExtras(intent.getExtras());
                }
                biometricEnrollIntent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                startActivity(biometricEnrollIntent);
                finish();
            } else if (i == SKIP_FINGERPRINT_REQUEST) {
                if (i2 != 0) {
                    FragmentActivity activity = getActivity();
                    if (i2 == 1) {
                        i2 = -1;
                    }
                    activity.setResult(i2, intent);
                    finish();
                }
            } else if (i != 501) {
                getActivity().setResult(0);
                finish();
            } else {
                return;
            }
            if (i == 0 && this.mForChangeCredRequiredForBoot) {
                finish();
            }
        }

        /* access modifiers changed from: protected */
        public Intent getBiometricEnrollIntent(Context context) {
            Intent intent = new Intent(context, BiometricEnrollActivity.InternalActivity.class);
            intent.putExtra("skip_intro", true);
            return intent;
        }

        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean("password_confirmed", this.mPasswordConfirmed);
            bundle.putBoolean("waiting_for_confirmation", this.mWaitingForConfirmation);
            byte[] bArr = this.mUserPassword;
            if (bArr != null) {
                bundle.putByteArray("password", bArr);
            }
        }

        /* access modifiers changed from: package-private */
        public void updatePreferencesOrFinish(boolean z) {
            int i;
            Intent intent = getActivity().getIntent();
            if (StorageManager.isFileEncryptedNativeOrEmulated()) {
                i = intent.getIntExtra("lockscreen.password_type", -1);
            } else {
                Log.i("ChooseLockGenericFragment", "Ignoring PASSWORD_TYPE_KEY because device is not file encrypted");
                i = -1;
            }
            if (i == -1) {
                int upgradeQuality = this.mController.upgradeQuality(intent.getIntExtra("minimum_quality", -1));
                boolean booleanExtra = intent.getBooleanExtra("hide_disabled_prefs", false);
                PreferenceScreen preferenceScreen = getPreferenceScreen();
                if (preferenceScreen != null) {
                    preferenceScreen.removeAll();
                }
                addPreferences();
                disableUnusablePreferences(upgradeQuality, booleanExtra);
                updatePreferenceText();
                updateCurrentPreference();
                updatePreferenceSummaryIfNeeded();
            } else if (!z) {
                updateUnlockMethodAndFinish(i, false, true);
            }
        }

        /* access modifiers changed from: protected */
        public void addPreferences() {
            addPreferencesFromResource(C1715R.xml.security_settings_picker);
            if (!TextUtils.isEmpty(this.mCallerAppName) && !this.mIsCallingAppAdmin) {
                new FooterPreferenceMixinCompat(this, getSettingsLifecycle()).createFooterPreference().setTitle((CharSequence) getFooterString());
            }
            findPreference(ScreenLockType.NONE.preferenceKey).setViewId(C1715R.C1718id.lock_none);
            findPreference("unlock_skip_fingerprint").setViewId(C1715R.C1718id.lock_none);
            findPreference("unlock_skip_face").setViewId(C1715R.C1718id.lock_none);
            findPreference(ScreenLockType.PIN.preferenceKey).setViewId(C1715R.C1718id.lock_pin);
            findPreference(ScreenLockType.PASSWORD.preferenceKey).setViewId(C1715R.C1718id.lock_password);
        }

        private String getFooterString() {
            int i = this.mRequestedMinComplexity;
            return getResources().getString(i != 65536 ? i != 196608 ? i != 327680 ? C1715R.string.unlock_footer_none_complexity_requested : C1715R.string.unlock_footer_high_complexity_requested : C1715R.string.unlock_footer_medium_complexity_requested : C1715R.string.unlock_footer_low_complexity_requested, new Object[]{this.mCallerAppName});
        }

        private void updatePreferenceText() {
            if (this.mForFingerprint) {
                setPreferenceTitle(ScreenLockType.PATTERN, (int) C1715R.string.fingerprint_unlock_set_unlock_pattern);
                setPreferenceTitle(ScreenLockType.PIN, (int) C1715R.string.fingerprint_unlock_set_unlock_pin);
                setPreferenceTitle(ScreenLockType.PASSWORD, (int) C1715R.string.fingerprint_unlock_set_unlock_password);
            } else if (this.mForFace) {
                setPreferenceTitle(ScreenLockType.PATTERN, (int) C1715R.string.face_unlock_set_unlock_pattern);
                setPreferenceTitle(ScreenLockType.PIN, (int) C1715R.string.face_unlock_set_unlock_pin);
                setPreferenceTitle(ScreenLockType.PASSWORD, (int) C1715R.string.face_unlock_set_unlock_password);
            }
            if (this.mManagedPasswordProvider.isSettingManagedPasswordSupported()) {
                setPreferenceTitle(ScreenLockType.MANAGED, this.mManagedPasswordProvider.getPickerOptionTitle(this.mForFingerprint));
            } else {
                removePreference(ScreenLockType.MANAGED.preferenceKey);
            }
            if (!this.mForFingerprint || !this.mIsSetNewPassword) {
                removePreference("unlock_skip_fingerprint");
            }
            if (!this.mForFace || !this.mIsSetNewPassword) {
                removePreference("unlock_skip_face");
            }
        }

        private void setPreferenceTitle(ScreenLockType screenLockType, int i) {
            Preference findPreference = findPreference(screenLockType.preferenceKey);
            if (findPreference != null) {
                findPreference.setTitle(i);
            }
        }

        private void setPreferenceTitle(ScreenLockType screenLockType, CharSequence charSequence) {
            Preference findPreference = findPreference(screenLockType.preferenceKey);
            if (findPreference != null) {
                findPreference.setTitle(charSequence);
            }
        }

        private void setPreferenceSummary(ScreenLockType screenLockType, int i) {
            Preference findPreference = findPreference(screenLockType.preferenceKey);
            if (findPreference != null) {
                findPreference.setSummary(i);
            }
        }

        private void updateCurrentPreference() {
            Preference findPreference = findPreference(getKeyForCurrent());
            if (findPreference != null) {
                findPreference.setSummary((int) C1715R.string.current_screen_lock);
            }
        }

        private String getKeyForCurrent() {
            int credentialOwnerProfile = UserManager.get(getContext()).getCredentialOwnerProfile(this.mUserId);
            if (this.mLockPatternUtils.isLockScreenDisabled(credentialOwnerProfile)) {
                return ScreenLockType.NONE.preferenceKey;
            }
            ScreenLockType fromQuality = ScreenLockType.fromQuality(this.mLockPatternUtils.getKeyguardStoredPasswordQuality(credentialOwnerProfile));
            if (fromQuality != null) {
                return fromQuality.preferenceKey;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void disableUnusablePreferences(int i, boolean z) {
            disableUnusablePreferencesImpl(i, z);
        }

        /* access modifiers changed from: protected */
        public void disableUnusablePreferencesImpl(int i, boolean z) {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            int passwordQuality = this.mDPM.getPasswordQuality((ComponentName) null, this.mUserId);
            RestrictedLockUtils.EnforcedAdmin checkIfPasswordQualityIsSet = RestrictedLockUtilsInternal.checkIfPasswordQualityIsSet(getActivity(), this.mUserId);
            for (ScreenLockType screenLockType : ScreenLockType.values()) {
                Preference findPreference = findPreference(screenLockType.preferenceKey);
                if (findPreference instanceof RestrictedPreference) {
                    boolean isScreenLockVisible = this.mController.isScreenLockVisible(screenLockType);
                    boolean isScreenLockEnabled = this.mController.isScreenLockEnabled(screenLockType, i);
                    boolean isScreenLockDisabledByAdmin = this.mController.isScreenLockDisabledByAdmin(screenLockType, passwordQuality);
                    if (z) {
                        isScreenLockVisible = isScreenLockVisible && isScreenLockEnabled;
                    }
                    if (!isScreenLockVisible) {
                        preferenceScreen.removePreference(findPreference);
                    } else if (isScreenLockDisabledByAdmin && checkIfPasswordQualityIsSet != null) {
                        ((RestrictedPreference) findPreference).setDisabledByAdmin(checkIfPasswordQualityIsSet);
                    } else if (!isScreenLockEnabled) {
                        ((RestrictedPreference) findPreference).setDisabledByAdmin((RestrictedLockUtils.EnforcedAdmin) null);
                        findPreference.setSummary((int) C1715R.string.unlock_set_unlock_disabled_summary);
                        findPreference.setEnabled(false);
                    } else {
                        ((RestrictedPreference) findPreference).setDisabledByAdmin((RestrictedLockUtils.EnforcedAdmin) null);
                    }
                }
            }
        }

        private void updatePreferenceSummaryIfNeeded() {
            if (StorageManager.isBlockEncrypted() && !StorageManager.isNonDefaultBlockEncrypted() && !AccessibilityManager.getInstance(getActivity()).getEnabledAccessibilityServiceList(-1).isEmpty()) {
                setPreferenceSummary(ScreenLockType.PATTERN, C1715R.string.secure_lock_encryption_warning);
                setPreferenceSummary(ScreenLockType.PIN, C1715R.string.secure_lock_encryption_warning);
                setPreferenceSummary(ScreenLockType.PASSWORD, C1715R.string.secure_lock_encryption_warning);
                setPreferenceSummary(ScreenLockType.MANAGED, C1715R.string.secure_lock_encryption_warning);
            }
        }

        /* access modifiers changed from: protected */
        public Intent getLockManagedPasswordIntent(byte[] bArr) {
            return this.mManagedPasswordProvider.createIntent(false, bArr);
        }

        /* access modifiers changed from: protected */
        public Intent getLockPasswordIntent(int i) {
            ChooseLockPassword.IntentBuilder intentBuilder = new ChooseLockPassword.IntentBuilder(getContext());
            intentBuilder.setPasswordQuality(i);
            intentBuilder.setRequestedMinComplexity(this.mRequestedMinComplexity);
            intentBuilder.setForFingerprint(this.mForFingerprint);
            intentBuilder.setForFace(this.mForFace);
            intentBuilder.setUserId(this.mUserId);
            if (this.mHasChallenge) {
                intentBuilder.setChallenge(this.mChallenge);
            }
            byte[] bArr = this.mUserPassword;
            if (bArr != null) {
                intentBuilder.setPassword(bArr);
            }
            return intentBuilder.build();
        }

        /* access modifiers changed from: protected */
        public Intent getLockPatternIntent() {
            ChooseLockPattern.IntentBuilder intentBuilder = new ChooseLockPattern.IntentBuilder(getContext());
            intentBuilder.setForFingerprint(this.mForFingerprint);
            intentBuilder.setForFace(this.mForFace);
            intentBuilder.setUserId(this.mUserId);
            if (this.mHasChallenge) {
                intentBuilder.setChallenge(this.mChallenge);
            }
            byte[] bArr = this.mUserPassword;
            if (bArr != null) {
                intentBuilder.setPattern(bArr);
            }
            return intentBuilder.build();
        }

        /* access modifiers changed from: protected */
        public Intent getEncryptionInterstitialIntent(Context context, int i, boolean z, Intent intent) {
            return EncryptionInterstitial.createStartIntent(context, i, z, intent);
        }

        private class RemovalTracker {
            boolean mFaceDone;
            boolean mFingerprintDone;

            private RemovalTracker() {
            }

            /* synthetic */ RemovalTracker(ChooseLockGenericFragment chooseLockGenericFragment, C11111 r2) {
                this();
            }

            /* access modifiers changed from: package-private */
            public void onFingerprintDone() {
                this.mFingerprintDone = true;
                if (this.mFingerprintDone && this.mFaceDone) {
                    ChooseLockGenericFragment.this.finish();
                }
            }

            /* access modifiers changed from: package-private */
            public void onFaceDone() {
                this.mFaceDone = true;
                if (this.mFingerprintDone && this.mFaceDone) {
                    ChooseLockGenericFragment.this.finish();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void updateUnlockMethodAndFinish(int i, boolean z, boolean z2) {
            if (this.mPasswordConfirmed) {
                int upgradeQuality = this.mController.upgradeQuality(i);
                Intent intentForUnlockMethod = getIntentForUnlockMethod(upgradeQuality);
                if (intentForUnlockMethod != null) {
                    if (getIntent().getBooleanExtra("show_options_button", false)) {
                        intentForUnlockMethod.putExtra("show_options_button", z2);
                    }
                    intentForUnlockMethod.putExtra("choose_lock_generic_extras", getIntent().getExtras());
                    startActivityForResult(intentForUnlockMethod, (!this.mIsSetNewPassword || !this.mHasChallenge) ? CHOOSE_LOCK_REQUEST : CHOOSE_LOCK_BEFORE_BIOMETRIC_REQUEST);
                } else if (upgradeQuality == 0) {
                    this.mChooseLockSettingsHelper.utils().clearLock(this.mUserPassword, this.mUserId);
                    this.mChooseLockSettingsHelper.utils().setLockScreenDisabled(z, this.mUserId);
                    getActivity().setResult(-1);
                    removeAllBiometricsForUserAndFinish(this.mUserId);
                } else {
                    removeAllBiometricsForUserAndFinish(this.mUserId);
                }
            } else {
                throw new IllegalStateException("Tried to update password without confirming it");
            }
        }

        private void removeAllBiometricsForUserAndFinish(int i) {
            RemovalTracker removalTracker = new RemovalTracker(this, (C11111) null);
            removeAllFingerprintForUserAndFinish(i, removalTracker);
            removeAllFaceForUserAndFinish(i, removalTracker);
        }

        private Intent getIntentForUnlockMethod(int i) {
            if (i >= 524288) {
                return getLockManagedPasswordIntent(this.mUserPassword);
            }
            if (i >= 131072) {
                return getLockPasswordIntent(i);
            }
            if (i == 65536) {
                return getLockPatternIntent();
            }
            return null;
        }

        private void removeAllFingerprintForUserAndFinish(final int i, final RemovalTracker removalTracker) {
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
                Utils.savePINPasswordLength(this.mLockPatternUtils, 0, this.mUserId);
                removalTracker.onFingerprintDone();
            } else if (this.mFingerprintManager.hasEnrolledFingerprints(i)) {
                this.mFingerprintManager.setActiveUser(i);
                this.mFingerprintManager.remove(new Fingerprint((CharSequence) null, i, 0, 0), i, new FingerprintManager.RemovalCallback() {
                    public void onRemovalError(Fingerprint fingerprint, int i, CharSequence charSequence) {
                        Log.e("ChooseLockGenericFragment", String.format("Can't remove fingerprint %d in group %d. Reason: %s", new Object[]{Integer.valueOf(fingerprint.getBiometricId()), Integer.valueOf(fingerprint.getGroupId()), charSequence}));
                    }

                    public void onRemovalSucceeded(Fingerprint fingerprint, int i) {
                        if (i == 0) {
                            ChooseLockGenericFragment.this.removeManagedProfileFingerprintsAndFinishIfNecessary(i, removalTracker);
                        }
                    }
                });
            } else {
                removeManagedProfileFingerprintsAndFinishIfNecessary(i, removalTracker);
            }
        }

        /* access modifiers changed from: private */
        public void removeManagedProfileFingerprintsAndFinishIfNecessary(int i, RemovalTracker removalTracker) {
            boolean z;
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
                this.mFingerprintManager.setActiveUser(UserHandle.myUserId());
            }
            if (!this.mUserManager.getUserInfo(i).isManagedProfile()) {
                List profiles = this.mUserManager.getProfiles(i);
                int size = profiles.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    UserInfo userInfo = (UserInfo) profiles.get(i2);
                    if (userInfo.isManagedProfile() && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(userInfo.id)) {
                        removeAllFingerprintForUserAndFinish(userInfo.id, removalTracker);
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            z = false;
            if (!z) {
                Utils.savePINPasswordLength(this.mLockPatternUtils, 0, this.mUserId);
                removalTracker.onFingerprintDone();
            }
        }

        private void removeAllFaceForUserAndFinish(final int i, final RemovalTracker removalTracker) {
            FaceManager faceManager = this.mFaceManager;
            if (faceManager == null || !faceManager.isHardwareDetected()) {
                removalTracker.onFaceDone();
            } else if (this.mFaceManager.hasEnrolledTemplates(i)) {
                this.mFaceManager.setActiveUser(i);
                this.mFaceManager.remove(new Face((CharSequence) null, 0, 0), i, new FaceManager.RemovalCallback() {
                    public void onRemovalError(Face face, int i, CharSequence charSequence) {
                        Log.e("ChooseLockGenericFragment", String.format("Can't remove face %d. Reason: %s", new Object[]{Integer.valueOf(face.getBiometricId()), charSequence}));
                    }

                    public void onRemovalSucceeded(Face face, int i) {
                        if (i == 0) {
                            ChooseLockGenericFragment.this.removeManagedProfileFacesAndFinishIfNecessary(i, removalTracker);
                        }
                    }
                });
            } else {
                removeManagedProfileFacesAndFinishIfNecessary(i, removalTracker);
            }
        }

        /* access modifiers changed from: private */
        public void removeManagedProfileFacesAndFinishIfNecessary(int i, RemovalTracker removalTracker) {
            FaceManager faceManager = this.mFaceManager;
            if (faceManager != null && faceManager.isHardwareDetected()) {
                this.mFaceManager.setActiveUser(UserHandle.myUserId());
            }
            boolean z = false;
            if (!this.mUserManager.getUserInfo(i).isManagedProfile()) {
                List profiles = this.mUserManager.getProfiles(i);
                int size = profiles.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    UserInfo userInfo = (UserInfo) profiles.get(i2);
                    if (userInfo.isManagedProfile() && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(userInfo.id)) {
                        removeAllFaceForUserAndFinish(userInfo.id, removalTracker);
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            if (!z) {
                removalTracker.onFaceDone();
            }
        }

        public void onDestroy() {
            super.onDestroy();
        }

        private int getResIdForFactoryResetProtectionWarningTitle() {
            return UserManager.get(getActivity()).isManagedProfile(this.mUserId) ? C1715R.string.unlock_disable_frp_warning_title_profile : C1715R.string.unlock_disable_frp_warning_title;
        }

        private int getResIdForFactoryResetProtectionWarningMessage() {
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            boolean hasEnrolledFingerprints = (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) ? false : this.mFingerprintManager.hasEnrolledFingerprints(this.mUserId);
            boolean isManagedProfile = UserManager.get(getActivity()).isManagedProfile(this.mUserId);
            int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId);
            if (keyguardStoredPasswordQuality != 65536) {
                if (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) {
                    if (hasEnrolledFingerprints && isManagedProfile) {
                        return C1715R.string.unlock_disable_frp_warning_content_pin_fingerprint_profile;
                    }
                    if (!hasEnrolledFingerprints || isManagedProfile) {
                        return isManagedProfile ? C1715R.string.unlock_disable_frp_warning_content_pin_profile : C1715R.string.unlock_disable_frp_warning_content_pin;
                    }
                    return C1715R.string.unlock_disable_frp_warning_content_pin_fingerprint;
                } else if (keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288) {
                    if (hasEnrolledFingerprints && isManagedProfile) {
                        return C1715R.string.unlock_disable_frp_warning_content_password_fingerprint_profile;
                    }
                    if (!hasEnrolledFingerprints || isManagedProfile) {
                        return isManagedProfile ? C1715R.string.unlock_disable_frp_warning_content_password_profile : C1715R.string.unlock_disable_frp_warning_content_password;
                    }
                    return C1715R.string.unlock_disable_frp_warning_content_password_fingerprint;
                } else if (hasEnrolledFingerprints && isManagedProfile) {
                    return C1715R.string.unlock_disable_frp_warning_content_unknown_fingerprint_profile;
                } else {
                    if (!hasEnrolledFingerprints || isManagedProfile) {
                        return isManagedProfile ? C1715R.string.unlock_disable_frp_warning_content_unknown_profile : C1715R.string.unlock_disable_frp_warning_content_unknown;
                    }
                    return C1715R.string.unlock_disable_frp_warning_content_unknown_fingerprint;
                }
            } else if (hasEnrolledFingerprints && isManagedProfile) {
                return C1715R.string.unlock_disable_frp_warning_content_pattern_fingerprint_profile;
            } else {
                if (!hasEnrolledFingerprints || isManagedProfile) {
                    return isManagedProfile ? C1715R.string.unlock_disable_frp_warning_content_pattern_profile : C1715R.string.unlock_disable_frp_warning_content_pattern;
                }
                return C1715R.string.unlock_disable_frp_warning_content_pattern_fingerprint;
            }
        }

        private boolean isUnlockMethodSecure(String str) {
            return !ScreenLockType.SWIPE.preferenceKey.equals(str) && !ScreenLockType.NONE.preferenceKey.equals(str);
        }

        /* access modifiers changed from: private */
        public boolean setUnlockMethod(String str) {
            EventLog.writeEvent(90200, str);
            ScreenLockType fromKey = ScreenLockType.fromKey(str);
            if (fromKey != null) {
                switch (C11111.$SwitchMap$com$android$settings$password$ScreenLockType[fromKey.ordinal()]) {
                    case 1:
                    case 2:
                        updateUnlockMethodAndFinish(fromKey.defaultQuality, fromKey == ScreenLockType.NONE, false);
                        return true;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        maybeEnableEncryption(fromKey.defaultQuality, false);
                        return true;
                }
            }
            Log.e("ChooseLockGenericFragment", "Encountered unknown unlock method to set: " + str);
            return false;
        }

        private void showFactoryResetProtectionWarningDialog(String str) {
            FactoryResetProtectionWarningDialog.newInstance(getResIdForFactoryResetProtectionWarningTitle(), getResIdForFactoryResetProtectionWarningMessage(), str).show(getChildFragmentManager(), "frp_warning_dialog");
        }

        public static class FactoryResetProtectionWarningDialog extends InstrumentedDialogFragment {
            public int getMetricsCategory() {
                return 528;
            }

            public static FactoryResetProtectionWarningDialog newInstance(int i, int i2, String str) {
                FactoryResetProtectionWarningDialog factoryResetProtectionWarningDialog = new FactoryResetProtectionWarningDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("titleRes", i);
                bundle.putInt("messageRes", i2);
                bundle.putString("unlockMethodToSet", str);
                factoryResetProtectionWarningDialog.setArguments(bundle);
                return factoryResetProtectionWarningDialog;
            }

            public void show(FragmentManager fragmentManager, String str) {
                if (fragmentManager.findFragmentByTag(str) == null) {
                    super.show(fragmentManager, str);
                }
            }

            public Dialog onCreateDialog(Bundle bundle) {
                Bundle arguments = getArguments();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(arguments.getInt("titleRes"));
                builder.setMessage(arguments.getInt("messageRes"));
                builder.setPositiveButton((int) C1715R.string.unlock_disable_frp_warning_ok, (DialogInterface.OnClickListener) 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0027: INVOKE  
                      (r0v0 'builder' androidx.appcompat.app.AlertDialog$Builder)
                      (wrap: ? : ?: SGET   com.havoc.config.center.R.string.unlock_disable_frp_warning_ok int)
                      (wrap: com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k : 0x0021: CONSTRUCTOR  (r1v5 com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k) = 
                      (r2v0 'this' com.android.settings.password.ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog A[THIS])
                      (r3v1 'arguments' android.os.Bundle)
                     call: com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k.<init>(com.android.settings.password.ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog, android.os.Bundle):void type: CONSTRUCTOR)
                     androidx.appcompat.app.AlertDialog.Builder.setPositiveButton(int, android.content.DialogInterface$OnClickListener):androidx.appcompat.app.AlertDialog$Builder type: VIRTUAL in method: com.android.settings.password.ChooseLockGeneric.ChooseLockGenericFragment.FactoryResetProtectionWarningDialog.onCreateDialog(android.os.Bundle):android.app.Dialog, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0021: CONSTRUCTOR  (r1v5 com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k) = 
                      (r2v0 'this' com.android.settings.password.ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog A[THIS])
                      (r3v1 'arguments' android.os.Bundle)
                     call: com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k.<init>(com.android.settings.password.ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog, android.os.Bundle):void type: CONSTRUCTOR in method: com.android.settings.password.ChooseLockGeneric.ChooseLockGenericFragment.FactoryResetProtectionWarningDialog.onCreateDialog(android.os.Bundle):android.app.Dialog, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 59 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 65 more
                    */
                /*
                    this = this;
                    android.os.Bundle r3 = r2.getArguments()
                    androidx.appcompat.app.AlertDialog$Builder r0 = new androidx.appcompat.app.AlertDialog$Builder
                    androidx.fragment.app.FragmentActivity r1 = r2.getActivity()
                    r0.<init>(r1)
                    java.lang.String r1 = "titleRes"
                    int r1 = r3.getInt(r1)
                    r0.setTitle((int) r1)
                    java.lang.String r1 = "messageRes"
                    int r1 = r3.getInt(r1)
                    r0.setMessage((int) r1)
                    com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k r1 = new com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Abdb-f1FnDmiVy0c3RZHU7n2B2k
                    r1.<init>(r2, r3)
                    r3 = 2131891104(0x7f1213a0, float:1.9416919E38)
                    r0.setPositiveButton((int) r3, (android.content.DialogInterface.OnClickListener) r1)
                    com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$YUiXVX_8NlQHl0UI000UMbpVL0U r3 = new com.android.settings.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$YUiXVX_8NlQHl0UI000UMbpVL0U
                    r3.<init>(r2)
                    r2 = 2131887138(0x7f120422, float:1.9408875E38)
                    r0.setNegativeButton((int) r2, (android.content.DialogInterface.OnClickListener) r3)
                    androidx.appcompat.app.AlertDialog r2 = r0.create()
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.settings.password.ChooseLockGeneric.ChooseLockGenericFragment.FactoryResetProtectionWarningDialog.onCreateDialog(android.os.Bundle):android.app.Dialog");
            }

            /* renamed from: lambda$onCreateDialog$0$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog */
            public /* synthetic */ void mo11348xcb2daf80(Bundle bundle, DialogInterface dialogInterface, int i) {
                boolean unused = ((ChooseLockGenericFragment) getParentFragment()).setUnlockMethod(bundle.getString("unlockMethodToSet"));
            }

            /* renamed from: lambda$onCreateDialog$1$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog */
            public /* synthetic */ void mo11349xaba70581(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        }
    }

    /* renamed from: com.android.settings.password.ChooseLockGeneric$1 */
    static /* synthetic */ class C11111 {
        static final /* synthetic */ int[] $SwitchMap$com$android$settings$password$ScreenLockType = new int[ScreenLockType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.settings.password.ScreenLockType[] r0 = com.android.settings.password.ScreenLockType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$android$settings$password$ScreenLockType = r0
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.SWIPE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PATTERN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PIN     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PASSWORD     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.MANAGED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.password.ChooseLockGeneric.C11111.<clinit>():void");
        }
    }
}
