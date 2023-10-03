package com.android.dialer.blocking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p002v7.appcompat.R$style;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.voicemailstatus.VisualVoicemailEnabledChecker;

@Deprecated
public class BlockNumberDialogFragment extends DialogFragment {
    private Callback callback;
    private String countryIso;
    private String displayNumber;
    /* access modifiers changed from: private */
    public FilteredNumberAsyncQueryHandler handler;
    private String number;
    /* access modifiers changed from: private */
    public View parentView;
    private VisualVoicemailEnabledChecker voicemailEnabledChecker;

    public interface Callback {
        void onChangeFilteredNumberUndo();

        void onFilterNumberSuccess();

        void onUnfilterNumberSuccess();
    }

    static /* synthetic */ void access$000(BlockNumberDialogFragment blockNumberDialogFragment) {
        final CharSequence unblockedMessage = blockNumberDialogFragment.getUnblockedMessage();
        final CharSequence blockedMessage = blockNumberDialogFragment.getBlockedMessage();
        final Callback callback2 = blockNumberDialogFragment.callback;
        final int actionTextColor = blockNumberDialogFragment.getActionTextColor();
        final Context applicationContext = blockNumberDialogFragment.getActivity().getApplicationContext();
        final C03714 r3 = new FilteredNumberAsyncQueryHandler.OnBlockNumberListener() {
            public void onBlockComplete(Uri uri) {
                Snackbar.make(BlockNumberDialogFragment.this.parentView, blockedMessage, 0).show();
                Callback callback = callback2;
                if (callback != null) {
                    callback.onChangeFilteredNumberUndo();
                }
            }
        };
        blockNumberDialogFragment.handler.unblock((FilteredNumberAsyncQueryHandler.OnUnblockNumberListener) new FilteredNumberAsyncQueryHandler.OnUnblockNumberListener() {
            public void onUnblockComplete(int i, final ContentValues contentValues) {
                C03731 r3 = new View.OnClickListener() {
                    public void onClick(View view) {
                        ((LoggingBindingsStub) Logger.get(applicationContext)).logInteraction(InteractionEvent$Type.UNDO_UNBLOCK_NUMBER);
                        BlockNumberDialogFragment.this.handler.blockNumber(r3, contentValues);
                    }
                };
                Snackbar make = Snackbar.make(BlockNumberDialogFragment.this.parentView, unblockedMessage, 0);
                make.setAction((int) R.string.snackbar_undo, (View.OnClickListener) r3);
                make.setActionTextColor(actionTextColor);
                make.show();
                Callback callback = callback2;
                if (callback != null) {
                    callback.onUnfilterNumberSuccess();
                }
            }
        }, Integer.valueOf(blockNumberDialogFragment.getArguments().getInt("argBlockId")));
    }

    static /* synthetic */ void access$100(BlockNumberDialogFragment blockNumberDialogFragment) {
        final CharSequence blockedMessage = blockNumberDialogFragment.getBlockedMessage();
        final CharSequence unblockedMessage = blockNumberDialogFragment.getUnblockedMessage();
        final Callback callback2 = blockNumberDialogFragment.callback;
        final int actionTextColor = blockNumberDialogFragment.getActionTextColor();
        final Context applicationContext = blockNumberDialogFragment.getActivity().getApplicationContext();
        final C03682 r3 = new FilteredNumberAsyncQueryHandler.OnUnblockNumberListener() {
            public void onUnblockComplete(int i, ContentValues contentValues) {
                Snackbar.make(BlockNumberDialogFragment.this.parentView, unblockedMessage, 0).show();
                Callback callback = callback2;
                if (callback != null) {
                    callback.onChangeFilteredNumberUndo();
                }
            }
        };
        blockNumberDialogFragment.handler.blockNumber(new FilteredNumberAsyncQueryHandler.OnBlockNumberListener() {
            public void onBlockComplete(final Uri uri) {
                C03701 r0 = new View.OnClickListener() {
                    public void onClick(View view) {
                        ((LoggingBindingsStub) Logger.get(applicationContext)).logInteraction(InteractionEvent$Type.UNDO_BLOCK_NUMBER);
                        BlockNumberDialogFragment.this.handler.unblock(r3, uri);
                    }
                };
                Snackbar make = Snackbar.make(BlockNumberDialogFragment.this.parentView, blockedMessage, 0);
                make.setAction((int) R.string.snackbar_undo, (View.OnClickListener) r0);
                make.setActionTextColor(actionTextColor);
                make.show();
                Callback callback = callback2;
                if (callback != null) {
                    callback.onFilterNumberSuccess();
                }
                if (FilteredNumbersUtil.hasRecentEmergencyCall(applicationContext)) {
                    FilteredNumbersUtil.maybeNotifyCallBlockingDisabled(applicationContext);
                }
            }
        }, blockNumberDialogFragment.number, blockNumberDialogFragment.countryIso);
    }

    private int getActionTextColor() {
        return getActivity().getResources().getColor(R.color.dialer_snackbar_action_text_color);
    }

    private CharSequence getBlockedMessage() {
        return R$style.getTtsSpannedPhoneNumber(getResources(), R.string.snackbar_number_blocked, this.displayNumber);
    }

    private CharSequence getUnblockedMessage() {
        return R$style.getTtsSpannedPhoneNumber(getResources(), R.string.snackbar_number_unblocked, this.displayNumber);
    }

    public static BlockNumberDialogFragment show(Integer num, String str, String str2, String str3, Integer num2, FragmentManager fragmentManager, Callback callback2) {
        BlockNumberDialogFragment blockNumberDialogFragment = new BlockNumberDialogFragment();
        Bundle bundle = new Bundle();
        if (num != null) {
            bundle.putInt("argBlockId", num.intValue());
        }
        if (num2 != null) {
            bundle.putInt("parentViewId", num2.intValue());
        }
        bundle.putString("argNumber", str);
        bundle.putString("argCountryIso", str2);
        bundle.putString("argDisplayNumber", str3);
        blockNumberDialogFragment.setArguments(bundle);
        blockNumberDialogFragment.callback = callback2;
        blockNumberDialogFragment.show(fragmentManager, "BlockNumberDialog");
        return blockNumberDialogFragment;
    }

    public Context getContext() {
        return getActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!FilteredNumbersUtil.canBlockNumber(getActivity(), PhoneNumberUtils.formatNumberToE164(this.number, this.countryIso), this.number)) {
            dismiss();
            Toast.makeText(getActivity(), R$style.getTtsSpannedPhoneNumber(getResources(), R.string.invalidNumber, this.displayNumber), 0).show();
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String str;
        String str2;
        CharSequence charSequence;
        super.onCreateDialog(bundle);
        final boolean containsKey = getArguments().containsKey("argBlockId");
        this.number = getArguments().getString("argNumber");
        this.displayNumber = getArguments().getString("argDisplayNumber");
        this.countryIso = getArguments().getString("argCountryIso");
        if (TextUtils.isEmpty(this.displayNumber)) {
            this.displayNumber = this.number;
        }
        this.handler = new FilteredNumberAsyncQueryHandler(getActivity());
        this.voicemailEnabledChecker = new VisualVoicemailEnabledChecker(getActivity(), (VisualVoicemailEnabledChecker.Callback) null);
        this.parentView = getActivity().findViewById(getArguments().getInt("parentViewId"));
        if (containsKey) {
            String string = getString(R.string.unblock_number_ok);
            str = R$style.getTtsSpannedPhoneNumber(getResources(), R.string.unblock_number_confirmation_title, this.displayNumber).toString();
            str2 = string;
            charSequence = null;
        } else {
            charSequence = R$style.getTtsSpannedPhoneNumber(getResources(), R.string.old_block_number_confirmation_title, this.displayNumber);
            str2 = getString(R.string.block_number_ok);
            if (FilteredNumberCompat.useNewFiltering(getActivity())) {
                str = getString(R.string.block_number_confirmation_message_new_filtering);
            } else if (this.voicemailEnabledChecker.isVisualVoicemailEnabled()) {
                str = getString(R.string.block_number_confirmation_message_vvm);
            } else {
                str = getString(R.string.block_number_confirmation_message_no_vvm);
            }
        }
        return new AlertDialog.Builder(getActivity()).setTitle(charSequence).setMessage(str).setPositiveButton(str2, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (containsKey) {
                    BlockNumberDialogFragment.access$000(BlockNumberDialogFragment.this);
                } else {
                    BlockNumberDialogFragment.access$100(BlockNumberDialogFragment.this);
                }
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
    }

    public void onPause() {
        dismiss();
        this.callback = null;
        super.onPause();
    }
}
