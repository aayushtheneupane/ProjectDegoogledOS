package com.android.settings.print;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.dashboard.profileselector.UserAdapter;
import com.havoc.config.center.C1715R;

public abstract class ProfileSettingsPreferenceFragment extends SettingsPreferenceFragment {
    /* access modifiers changed from: protected */
    public abstract String getIntentActionString();

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final UserAdapter createUserSpinnerAdapter = UserAdapter.createUserSpinnerAdapter((UserManager) getSystemService("user"), getActivity());
        if (createUserSpinnerAdapter != null) {
            final Spinner spinner = (Spinner) setPinnedHeaderView((int) C1715R.layout.spinner_view);
            spinner.setAdapter(createUserSpinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    UserHandle userHandle = createUserSpinnerAdapter.getUserHandle(i);
                    if (userHandle.getIdentifier() != UserHandle.myUserId()) {
                        Intent intent = new Intent(ProfileSettingsPreferenceFragment.this.getIntentActionString());
                        intent.addFlags(268435456);
                        intent.addFlags(32768);
                        ProfileSettingsPreferenceFragment.this.getActivity().startActivityAsUser(intent, userHandle);
                        spinner.setSelection(0);
                    }
                }
            });
        }
    }
}
