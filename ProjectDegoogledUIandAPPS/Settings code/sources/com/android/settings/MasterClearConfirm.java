package com.android.settings;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.service.oemlock.OemLockManager;
import android.service.persistentdata.PersistentDataBlockManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import com.havoc.config.center.C1715R;

public class MasterClearConfirm extends InstrumentedFragment {
    View mContentView;
    boolean mEraseEsims;
    private boolean mEraseSdCard;
    private View.OnClickListener mFinalClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (!Utils.isMonkeyRunning()) {
                final PersistentDataBlockManager persistentDataBlockManager = (PersistentDataBlockManager) MasterClearConfirm.this.getActivity().getSystemService("persistent_data_block");
                OemLockManager oemLockManager = (OemLockManager) MasterClearConfirm.this.getActivity().getSystemService("oem_lock");
                if (persistentDataBlockManager == null || oemLockManager.isOemUnlockAllowed() || !Utils.isDeviceProvisioned(MasterClearConfirm.this.getActivity())) {
                    MasterClearConfirm.this.doMasterClear();
                } else {
                    new AsyncTask<Void, Void, Void>() {
                        int mOldOrientation;
                        ProgressDialog mProgressDialog;

                        /* access modifiers changed from: protected */
                        public Void doInBackground(Void... voidArr) {
                            persistentDataBlockManager.wipe();
                            return null;
                        }

                        /* access modifiers changed from: protected */
                        public void onPostExecute(Void voidR) {
                            this.mProgressDialog.hide();
                            if (MasterClearConfirm.this.getActivity() != null) {
                                MasterClearConfirm.this.getActivity().setRequestedOrientation(this.mOldOrientation);
                                MasterClearConfirm.this.doMasterClear();
                            }
                        }

                        /* access modifiers changed from: protected */
                        public void onPreExecute() {
                            this.mProgressDialog = C04031.this.getProgressDialog();
                            this.mProgressDialog.show();
                            this.mOldOrientation = MasterClearConfirm.this.getActivity().getRequestedOrientation();
                            MasterClearConfirm.this.getActivity().setRequestedOrientation(14);
                        }
                    }.execute(new Void[0]);
                }
            }
        }

        /* access modifiers changed from: private */
        public ProgressDialog getProgressDialog() {
            ProgressDialog progressDialog = new ProgressDialog(MasterClearConfirm.this.getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setTitle(MasterClearConfirm.this.getActivity().getString(C1715R.string.master_clear_progress_title));
            progressDialog.setMessage(MasterClearConfirm.this.getActivity().getString(C1715R.string.master_clear_progress_text));
            return progressDialog;
        }
    };

    public int getMetricsCategory() {
        return 67;
    }

    /* access modifiers changed from: private */
    public void doMasterClear() {
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", this.mEraseSdCard);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", this.mEraseEsims);
        getActivity().sendBroadcast(intent);
    }

    private void establishFinalConfirmationState() {
        FooterButton.Builder builder = new FooterButton.Builder(getActivity());
        builder.setText(C1715R.string.master_clear_button_text);
        builder.setListener(this.mFinalClickListener);
        builder.setButtonType(0);
        builder.setTheme(2131952050);
        ((FooterBarMixin) ((GlifLayout) this.mContentView.findViewById(C1715R.C1718id.setup_wizard_layout)).getMixin(FooterBarMixin.class)).setPrimaryButton(builder.build());
    }

    private void setUpActionBarAndTitle() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e("MasterClearConfirm", "No activity attached, skipping setUpActionBarAndTitle");
            return;
        }
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            Log.e("MasterClearConfirm", "No actionbar, skipping setUpActionBarAndTitle");
            return;
        }
        actionBar.hide();
        activity.getWindow().setStatusBarColor(0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_factory_reset", UserHandle.myUserId());
        if (RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_factory_reset", UserHandle.myUserId())) {
            return layoutInflater.inflate(C1715R.layout.master_clear_disallowed_screen, (ViewGroup) null);
        }
        if (checkIfRestrictionEnforced != null) {
            AlertDialog.Builder prepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_factory_reset", checkIfRestrictionEnforced);
            prepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public final void onDismiss(DialogInterface dialogInterface) {
                    MasterClearConfirm.this.lambda$onCreateView$0$MasterClearConfirm(dialogInterface);
                }
            });
            prepareDialogBuilder.show();
            return new View(getActivity());
        }
        this.mContentView = layoutInflater.inflate(C1715R.layout.master_clear_confirm, (ViewGroup) null);
        setUpActionBarAndTitle();
        establishFinalConfirmationState();
        setAccessibilityTitle();
        setSubtitle();
        return this.mContentView;
    }

    public /* synthetic */ void lambda$onCreateView$0$MasterClearConfirm(DialogInterface dialogInterface) {
        getActivity().finish();
    }

    private void setAccessibilityTitle() {
        CharSequence title = getActivity().getTitle();
        TextView textView = (TextView) this.mContentView.findViewById(C1715R.C1718id.sud_layout_description);
        if (textView != null) {
            getActivity().setTitle(Utils.createAccessibleSequence(title, title + "," + textView.getText()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setSubtitle() {
        if (this.mEraseEsims) {
            ((TextView) this.mContentView.findViewById(C1715R.C1718id.sud_layout_description)).setText(C1715R.string.master_clear_final_desc_esim);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        boolean z = true;
        this.mEraseSdCard = arguments != null && arguments.getBoolean("erase_sd");
        if (arguments == null || !arguments.getBoolean("erase_esim")) {
            z = false;
        }
        this.mEraseEsims = z;
    }
}
