package com.android.settings.biometrics.face;

import android.content.Context;
import android.content.Intent;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.SettingsActivity;
import com.android.settings.Utils;
import com.android.settings.biometrics.face.FaceSettingsRemoveButtonPreferenceController;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaceSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.security_settings_face;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            if (FaceSettings.isAvailable(context)) {
                return FaceSettings.buildPreferenceControllers(context, (Lifecycle) null);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return FaceSettings.isAvailable(context);
        }
    };
    private FaceSettingsAttentionPreferenceController mAttentionController;
    private boolean mConfirmingPassword;
    private List<AbstractPreferenceController> mControllers;
    private Preference mEnrollButton;
    private FaceSettingsEnrollButtonPreferenceController mEnrollController;
    private FaceFeatureProvider mFaceFeatureProvider;
    private FaceManager mFaceManager;
    private FaceSettingsLockscreenBypassPreferenceController mLockscreenController;
    private final FaceSettingsRemoveButtonPreferenceController.Listener mRemovalListener = new FaceSettingsRemoveButtonPreferenceController.Listener() {
        public final void onRemoved() {
            FaceSettings.this.lambda$new$0$FaceSettings();
        }
    };
    private Preference mRemoveButton;
    private FaceSettingsRemoveButtonPreferenceController mRemoveController;
    private FaceSettingsSwipePreferenceController mSwipeController;
    private List<Preference> mTogglePreferences;
    private byte[] mToken;
    private int mUserId;
    private UserManager mUserManager;

    public int getHelpResource() {
        return C1715R.string.help_url_face;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "FaceSettings";
    }

    public int getMetricsCategory() {
        return 1511;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.security_settings_face;
    }

    public /* synthetic */ void lambda$new$0$FaceSettings() {
        for (Preference enabled : this.mTogglePreferences) {
            enabled.setEnabled(false);
        }
        this.mRemoveButton.setVisible(false);
        this.mEnrollButton.setVisible(true);
    }

    public static boolean isAvailable(Context context) {
        FaceManager faceManagerOrNull = Utils.getFaceManagerOrNull(context);
        return faceManagerOrNull != null && faceManagerOrNull.isHardwareDetected();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putByteArray("hw_auth_token", this.mToken);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mToken = getIntent().getByteArrayExtra("hw_auth_token");
        this.mUserManager = (UserManager) getPrefContext().getSystemService(UserManager.class);
        this.mFaceManager = (FaceManager) getPrefContext().getSystemService(FaceManager.class);
        this.mUserId = getActivity().getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
        this.mFaceFeatureProvider = FeatureFactory.getFactory(getContext()).getFaceFeatureProvider();
        if (this.mUserManager.getUserInfo(this.mUserId).isManagedProfile()) {
            getActivity().setTitle(getActivity().getResources().getString(C1715R.string.security_settings_face_profile_preference_title));
        }
        Preference findPreference = findPreference("security_settings_face_keyguard");
        Preference findPreference2 = findPreference("security_settings_face_app");
        Preference findPreference3 = findPreference(FaceSettingsAttentionPreferenceController.KEY);
        Preference findPreference4 = findPreference("security_settings_face_require_confirmation");
        Preference findPreference5 = findPreference(this.mLockscreenController.getPreferenceKey());
        findPreference(this.mSwipeController.getPreferenceKey());
        this.mTogglePreferences = new ArrayList(Arrays.asList(new Preference[]{findPreference, findPreference2, findPreference3, findPreference4, findPreference5}));
        this.mRemoveButton = findPreference("security_settings_face_delete_faces_container");
        this.mEnrollButton = findPreference("security_settings_face_enroll_faces_container");
        for (AbstractPreferenceController next : this.mControllers) {
            if (next instanceof FaceSettingsPreferenceController) {
                ((FaceSettingsPreferenceController) next).setUserId(this.mUserId);
            } else if (next instanceof FaceSettingsEnrollButtonPreferenceController) {
                ((FaceSettingsEnrollButtonPreferenceController) next).setUserId(this.mUserId);
            }
        }
        this.mRemoveController.setUserId(this.mUserId);
        if (this.mUserManager.isManagedProfile(this.mUserId)) {
            removePreference("security_settings_face_keyguard");
            removePreference(this.mLockscreenController.getPreferenceKey());
            removePreference(this.mSwipeController.getPreferenceKey());
        }
        if (bundle != null) {
            this.mToken = bundle.getByteArray("hw_auth_token");
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mLockscreenController = (FaceSettingsLockscreenBypassPreferenceController) use(FaceSettingsLockscreenBypassPreferenceController.class);
        this.mLockscreenController.setUserId(this.mUserId);
        this.mSwipeController = (FaceSettingsSwipePreferenceController) use(FaceSettingsSwipePreferenceController.class);
        this.mSwipeController.setUserId(this.mUserId);
    }

    public void onResume() {
        super.onResume();
        if (this.mToken != null || this.mConfirmingPassword) {
            this.mAttentionController.setToken(this.mToken);
            this.mEnrollController.setToken(this.mToken);
        } else {
            long generateChallenge = this.mFaceManager.generateChallenge();
            ChooseLockSettingsHelper chooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity(), this);
            this.mConfirmingPassword = true;
            if (!chooseLockSettingsHelper.launchConfirmationActivity(4, (CharSequence) getString(C1715R.string.security_settings_face_preference_title), (CharSequence) null, (CharSequence) null, generateChallenge, this.mUserId, true)) {
                Log.e("FaceSettings", "Password not set");
                finish();
            }
        }
        boolean hasEnrolledTemplates = this.mFaceManager.hasEnrolledTemplates(this.mUserId);
        this.mEnrollButton.setVisible(!hasEnrolledTemplates);
        this.mRemoveButton.setVisible(hasEnrolledTemplates);
        if (!this.mFaceFeatureProvider.isAttentionSupported(getContext())) {
            removePreference(FaceSettingsAttentionPreferenceController.KEY);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4) {
            this.mConfirmingPassword = false;
            if (i2 == 1 || i2 == -1) {
                this.mFaceManager.setActiveUser(this.mUserId);
                if (intent != null) {
                    this.mToken = intent.getByteArrayExtra("hw_auth_token");
                    byte[] bArr = this.mToken;
                    if (bArr != null) {
                        this.mAttentionController.setToken(bArr);
                        this.mEnrollController.setToken(this.mToken);
                    }
                }
            }
        }
        if (this.mToken == null) {
            getActivity().finish();
        }
    }

    public void onStop() {
        super.onStop();
        if (!this.mEnrollController.isClicked() && !getActivity().isChangingConfigurations() && !this.mConfirmingPassword) {
            if (this.mToken != null) {
                int revokeChallenge = this.mFaceManager.revokeChallenge();
                if (revokeChallenge < 0) {
                    Log.w("FaceSettings", "revokeChallenge failed, result: " + revokeChallenge);
                }
                this.mToken = null;
            }
            getActivity().finish();
        }
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        if (!isAvailable(context)) {
            return null;
        }
        this.mControllers = buildPreferenceControllers(context, getSettingsLifecycle());
        for (AbstractPreferenceController next : this.mControllers) {
            if (next instanceof FaceSettingsAttentionPreferenceController) {
                this.mAttentionController = (FaceSettingsAttentionPreferenceController) next;
            } else if (next instanceof FaceSettingsRemoveButtonPreferenceController) {
                this.mRemoveController = (FaceSettingsRemoveButtonPreferenceController) next;
                this.mRemoveController.setListener(this.mRemovalListener);
                this.mRemoveController.setActivity((SettingsActivity) getActivity());
            } else if (next instanceof FaceSettingsEnrollButtonPreferenceController) {
                this.mEnrollController = (FaceSettingsEnrollButtonPreferenceController) next;
                this.mEnrollController.setActivity((SettingsActivity) getActivity());
            }
        }
        return this.mControllers;
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FaceSettingsVideoPreferenceController(context));
        arrayList.add(new FaceSettingsKeyguardPreferenceController(context));
        arrayList.add(new FaceSettingsAppPreferenceController(context));
        arrayList.add(new FaceSettingsAttentionPreferenceController(context));
        arrayList.add(new FaceSettingsRemoveButtonPreferenceController(context));
        arrayList.add(new FaceSettingsFooterPreferenceController(context));
        arrayList.add(new FaceSettingsConfirmPreferenceController(context));
        arrayList.add(new FaceSettingsEnrollButtonPreferenceController(context));
        return arrayList;
    }
}
