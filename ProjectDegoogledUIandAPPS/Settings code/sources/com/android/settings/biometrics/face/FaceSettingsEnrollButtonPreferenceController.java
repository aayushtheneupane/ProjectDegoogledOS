package com.android.settings.biometrics.face;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import com.android.settings.SettingsActivity;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class FaceSettingsEnrollButtonPreferenceController extends BasePreferenceController implements View.OnClickListener {
    static final String KEY = "security_settings_face_enroll_faces_container";
    private static final String TAG = "FaceSettings/Remove";
    private SettingsActivity mActivity;
    private Button mButton;
    private boolean mIsClicked;
    private byte[] mToken;
    private int mUserId;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FaceSettingsEnrollButtonPreferenceController(Context context) {
        this(context, KEY);
    }

    public FaceSettingsEnrollButtonPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mButton = (Button) ((LayoutPreference) preference).findViewById(C1715R.C1718id.security_settings_face_settings_enroll_button);
        this.mButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        this.mIsClicked = true;
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", FaceEnrollIntroduction.class.getName());
        intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
        intent.putExtra("hw_auth_token", this.mToken);
        this.mContext.startActivity(intent);
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    public void setToken(byte[] bArr) {
        this.mToken = bArr;
    }

    public boolean isClicked() {
        boolean z = this.mIsClicked;
        this.mIsClicked = false;
        return z;
    }

    public void setActivity(SettingsActivity settingsActivity) {
        this.mActivity = settingsActivity;
    }
}
