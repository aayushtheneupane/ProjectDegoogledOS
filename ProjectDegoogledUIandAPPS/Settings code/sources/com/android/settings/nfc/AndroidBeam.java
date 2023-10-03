package com.android.settings.nfc;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.SettingsActivity;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.havoc.config.center.C1715R;

public class AndroidBeam extends InstrumentedFragment implements SwitchBar.OnSwitchChangeListener {
    private boolean mBeamDisallowedByBase;
    private boolean mBeamDisallowedByOnlyAdmin;
    private NfcAdapter mNfcAdapter;
    private CharSequence mOldActivityTitle;
    private SwitchBar mSwitchBar;
    private View mView;

    public int getMetricsCategory() {
        return 69;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        PackageManager packageManager = activity.getPackageManager();
        if (this.mNfcAdapter == null || !packageManager.hasSystemFeature("android.sofware.nfc.beam")) {
            getActivity().finish();
        }
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        HelpUtils.prepareHelpMenuItem((Activity) getActivity(), menu, (int) C1715R.string.help_uri_beam, AndroidBeam.class.getName());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_outgoing_beam", UserHandle.myUserId());
        UserManager.get(getActivity());
        this.mBeamDisallowedByBase = RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_outgoing_beam", UserHandle.myUserId());
        if (this.mBeamDisallowedByBase || checkIfRestrictionEnforced == null) {
            this.mView = layoutInflater.inflate(C1715R.layout.preference_footer, viewGroup, false);
            ((ImageView) this.mView.findViewById(16908294)).setImageResource(C1715R.C1717drawable.ic_info_outline_24dp);
            ((TextView) this.mView.findViewById(16908310)).setText(C1715R.string.android_beam_explained);
            return this.mView;
        }
        new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_outgoing_beam", checkIfRestrictionEnforced).show();
        this.mBeamDisallowedByOnlyAdmin = true;
        return new View(getContext());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        this.mOldActivityTitle = settingsActivity.getActionBar().getTitle();
        this.mSwitchBar = settingsActivity.getSwitchBar();
        if (this.mBeamDisallowedByOnlyAdmin) {
            this.mSwitchBar.hide();
        } else {
            this.mSwitchBar.setChecked(!this.mBeamDisallowedByBase && this.mNfcAdapter.isNdefPushEnabled());
            this.mSwitchBar.addOnSwitchChangeListener(this);
            this.mSwitchBar.setEnabled(!this.mBeamDisallowedByBase);
            this.mSwitchBar.show();
        }
        settingsActivity.setTitle(C1715R.string.android_beam_settings_title);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mOldActivityTitle != null) {
            getActivity().getActionBar().setTitle(this.mOldActivityTitle);
        }
        if (!this.mBeamDisallowedByOnlyAdmin) {
            this.mSwitchBar.removeOnSwitchChangeListener(this);
            this.mSwitchBar.hide();
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        boolean z2;
        this.mSwitchBar.setEnabled(false);
        if (z) {
            z2 = this.mNfcAdapter.enableNdefPush();
        } else {
            z2 = this.mNfcAdapter.disableNdefPush();
        }
        if (z2) {
            this.mSwitchBar.setChecked(z);
        }
        this.mSwitchBar.setEnabled(true);
    }
}
