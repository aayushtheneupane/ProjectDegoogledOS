package com.android.dialer.blockreportspam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p002v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumberCompat;
import com.android.dialer.blockreportspam.BlockReportSpamDialogs;

public final class BlockReportSpamDialogs {

    private static abstract class CommonDialogsFragment extends DialogFragment {
        protected DialogInterface.OnDismissListener dismissListener;
        protected String displayNumber;
        protected OnConfirmListener positiveListener;

        /* synthetic */ CommonDialogsFragment(C03921 r1) {
        }

        public void onDismiss(DialogInterface dialogInterface) {
            DialogInterface.OnDismissListener onDismissListener = this.dismissListener;
            if (onDismissListener != null) {
                onDismissListener.onDismiss(dialogInterface);
            }
            super.onDismiss(dialogInterface);
        }

        public void onPause() {
            dismiss();
            this.dismissListener = null;
            this.positiveListener = null;
            this.displayNumber = null;
            super.onPause();
        }
    }

    public static class DialogFragmentForBlockingNumber extends CommonDialogsFragment {
        public static DialogFragment newInstance(String str, OnConfirmListener onConfirmListener, DialogInterface.OnDismissListener onDismissListener) {
            DialogFragmentForBlockingNumberAndReportingAsSpam dialogFragmentForBlockingNumberAndReportingAsSpam = new DialogFragmentForBlockingNumberAndReportingAsSpam();
            dialogFragmentForBlockingNumberAndReportingAsSpam.displayNumber = str;
            dialogFragmentForBlockingNumberAndReportingAsSpam.positiveListener = onConfirmListener;
            dialogFragmentForBlockingNumberAndReportingAsSpam.dismissListener = onDismissListener;
            return dialogFragmentForBlockingNumberAndReportingAsSpam;
        }
    }

    public static class DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam extends CommonDialogsFragment {
        private OnSpamDialogClickListener onSpamDialogClickListener;
        private boolean spamChecked;

        public DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam() {
            super((C03921) null);
        }

        public static DialogFragment newInstance(String str, boolean z, OnSpamDialogClickListener onSpamDialogClickListener2, DialogInterface.OnDismissListener onDismissListener) {
            DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam dialogFragmentForBlockingNumberAndOptionallyReportingAsSpam = new DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam();
            dialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.spamChecked = z;
            dialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.displayNumber = str;
            dialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.onSpamDialogClickListener = onSpamDialogClickListener2;
            dialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.dismissListener = onDismissListener;
            return dialogFragmentForBlockingNumberAndOptionallyReportingAsSpam;
        }

        /* renamed from: lambda$onCreateDialog$0$BlockReportSpamDialogs$DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam */
        public /* synthetic */ void mo5100xbadf86c9(CompoundButton compoundButton, boolean z) {
            this.spamChecked = z;
        }

        /* renamed from: lambda$onCreateDialog$1$BlockReportSpamDialogs$DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam */
        public /* synthetic */ void mo5101x2dee528(CheckBox checkBox, DialogInterface dialogInterface, int i) {
            dismiss();
            this.onSpamDialogClickListener.onClick(checkBox.isChecked());
        }

        public Dialog onCreateDialog(Bundle bundle) {
            super.onCreateDialog(bundle);
            View inflate = View.inflate(getActivity(), R.layout.block_report_spam_dialog, (ViewGroup) null);
            CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.report_number_as_spam_action);
            checkBox.setChecked(this.spamChecked);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    BlockReportSpamDialogs.DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.this.mo5100xbadf86c9(compoundButton, z);
                }
            });
            ((TextView) inflate.findViewById(R.id.block_details)).setText(BlockReportSpamDialogs.access$100(getContext()));
            AlertDialog.Builder access$200 = BlockReportSpamDialogs.access$200(getActivity(), this);
            access$200.setView(inflate);
            access$200.setTitle((CharSequence) getString(R.string.block_report_number_alert_title, this.displayNumber));
            access$200.setPositiveButton((int) R.string.block_number_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(checkBox) {
                private final /* synthetic */ CheckBox f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    BlockReportSpamDialogs.DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.this.mo5101x2dee528(this.f$1, dialogInterface, i);
                }
            });
            AlertDialog create = access$200.create();
            create.setCanceledOnTouchOutside(true);
            return create;
        }

        public /* bridge */ /* synthetic */ void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
        }

        public /* bridge */ /* synthetic */ void onPause() {
            super.onPause();
        }
    }

    public static class DialogFragmentForBlockingNumberAndReportingAsSpam extends CommonDialogsFragment {
        private boolean isSpamEnabled;

        public DialogFragmentForBlockingNumberAndReportingAsSpam() {
            super((C03921) null);
        }

        public static DialogFragment newInstance(String str, boolean z, OnConfirmListener onConfirmListener, DialogInterface.OnDismissListener onDismissListener) {
            DialogFragmentForBlockingNumberAndReportingAsSpam dialogFragmentForBlockingNumberAndReportingAsSpam = new DialogFragmentForBlockingNumberAndReportingAsSpam();
            dialogFragmentForBlockingNumberAndReportingAsSpam.displayNumber = str;
            dialogFragmentForBlockingNumberAndReportingAsSpam.positiveListener = onConfirmListener;
            dialogFragmentForBlockingNumberAndReportingAsSpam.dismissListener = onDismissListener;
            dialogFragmentForBlockingNumberAndReportingAsSpam.isSpamEnabled = z;
            return dialogFragmentForBlockingNumberAndReportingAsSpam;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            String str;
            super.onCreateDialog(bundle);
            AlertDialog.Builder access$200 = BlockReportSpamDialogs.access$200(getActivity(), this);
            access$200.setTitle((CharSequence) getString(R.string.block_number_confirmation_title, this.displayNumber));
            if (this.isSpamEnabled) {
                str = getString(R.string.block_number_alert_details, BlockReportSpamDialogs.access$100(getContext()));
            } else {
                str = getString(R.string.block_report_number_alert_details);
            }
            access$200.setMessage((CharSequence) str);
            access$200.setPositiveButton((int) R.string.block_number_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this.positiveListener) {
                private final /* synthetic */ BlockReportSpamDialogs.OnConfirmListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    BlockReportSpamDialogs.OnConfirmListener onConfirmListener = this.f$1;
                    dialogFragment.dismiss();
                    onConfirmListener.onClick();
                }
            });
            AlertDialog create = access$200.create();
            create.setCanceledOnTouchOutside(true);
            return create;
        }

        public /* bridge */ /* synthetic */ void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
        }

        public /* bridge */ /* synthetic */ void onPause() {
            super.onPause();
        }
    }

    public static class DialogFragmentForReportingNotSpam extends CommonDialogsFragment {
        public DialogFragmentForReportingNotSpam() {
            super((C03921) null);
        }

        public static DialogFragment newInstance(String str, OnConfirmListener onConfirmListener, DialogInterface.OnDismissListener onDismissListener) {
            DialogFragmentForReportingNotSpam dialogFragmentForReportingNotSpam = new DialogFragmentForReportingNotSpam();
            dialogFragmentForReportingNotSpam.displayNumber = str;
            dialogFragmentForReportingNotSpam.positiveListener = onConfirmListener;
            dialogFragmentForReportingNotSpam.dismissListener = onDismissListener;
            return dialogFragmentForReportingNotSpam;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            super.onCreateDialog(bundle);
            AlertDialog.Builder access$200 = BlockReportSpamDialogs.access$200(getActivity(), this);
            access$200.setTitle((int) R.string.report_not_spam_alert_title);
            access$200.setMessage((CharSequence) getString(R.string.report_not_spam_alert_details, this.displayNumber));
            access$200.setPositiveButton((int) R.string.report_not_spam_alert_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this.positiveListener) {
                private final /* synthetic */ BlockReportSpamDialogs.OnConfirmListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    BlockReportSpamDialogs.OnConfirmListener onConfirmListener = this.f$1;
                    dialogFragment.dismiss();
                    onConfirmListener.onClick();
                }
            });
            AlertDialog create = access$200.create();
            create.setCanceledOnTouchOutside(true);
            return create;
        }

        public /* bridge */ /* synthetic */ void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
        }

        public /* bridge */ /* synthetic */ void onPause() {
            super.onPause();
        }
    }

    public static class DialogFragmentForUnblockingNumber extends CommonDialogsFragment {
        public static DialogFragment newInstance(String str, OnConfirmListener onConfirmListener, DialogInterface.OnDismissListener onDismissListener) {
            DialogFragmentForUnblockingNumberAndReportingAsNotSpam dialogFragmentForUnblockingNumberAndReportingAsNotSpam = new DialogFragmentForUnblockingNumberAndReportingAsNotSpam();
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.displayNumber = str;
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.positiveListener = onConfirmListener;
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.dismissListener = onDismissListener;
            return dialogFragmentForUnblockingNumberAndReportingAsNotSpam;
        }
    }

    public static class DialogFragmentForUnblockingNumberAndReportingAsNotSpam extends CommonDialogsFragment {
        private boolean isSpam;

        public DialogFragmentForUnblockingNumberAndReportingAsNotSpam() {
            super((C03921) null);
        }

        public static DialogFragment newInstance(String str, boolean z, OnConfirmListener onConfirmListener, DialogInterface.OnDismissListener onDismissListener) {
            DialogFragmentForUnblockingNumberAndReportingAsNotSpam dialogFragmentForUnblockingNumberAndReportingAsNotSpam = new DialogFragmentForUnblockingNumberAndReportingAsNotSpam();
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.displayNumber = str;
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.isSpam = z;
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.positiveListener = onConfirmListener;
            dialogFragmentForUnblockingNumberAndReportingAsNotSpam.dismissListener = onDismissListener;
            return dialogFragmentForUnblockingNumberAndReportingAsNotSpam;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            super.onCreateDialog(bundle);
            AlertDialog.Builder access$200 = BlockReportSpamDialogs.access$200(getActivity(), this);
            if (this.isSpam) {
                access$200.setMessage((int) R.string.unblock_number_alert_details);
                access$200.setTitle((CharSequence) getString(R.string.unblock_report_number_alert_title, this.displayNumber));
            } else {
                access$200.setMessage((CharSequence) getString(R.string.unblock_report_number_alert_title, this.displayNumber));
            }
            access$200.setPositiveButton((int) R.string.unblock_number_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this.positiveListener) {
                private final /* synthetic */ BlockReportSpamDialogs.OnConfirmListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogFragment dialogFragment = DialogFragment.this;
                    BlockReportSpamDialogs.OnConfirmListener onConfirmListener = this.f$1;
                    dialogFragment.dismiss();
                    onConfirmListener.onClick();
                }
            });
            AlertDialog create = access$200.create();
            create.setCanceledOnTouchOutside(true);
            return create;
        }

        public /* bridge */ /* synthetic */ void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
        }

        public /* bridge */ /* synthetic */ void onPause() {
            super.onPause();
        }
    }

    public interface OnConfirmListener {
        void onClick();
    }

    public interface OnSpamDialogClickListener {
        void onClick(boolean z);
    }

    static /* synthetic */ String access$100(Context context) {
        if (FilteredNumberCompat.useNewFiltering(context)) {
            return context.getString(R.string.block_number_confirmation_message_new_filtering);
        }
        return context.getString(R.string.block_report_number_alert_details);
    }

    static /* synthetic */ AlertDialog.Builder access$200(Activity activity, DialogFragment dialogFragment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogFragment.this.dismiss();
            }
        });
        return builder;
    }
}
