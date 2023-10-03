package com.android.incallui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contacts.ContactsComponent;
import com.android.incallui.ContactInfoCache;
import com.android.incallui.call.CallList;
import java.lang.ref.WeakReference;

public class RttRequestDialogFragment extends DialogFragment {
    private TextView detailsTextView;

    private static class ContactLookupCallback implements ContactInfoCache.ContactInfoCacheCallback {
        private final WeakReference<RttRequestDialogFragment> rttRequestDialogFragmentWeakReference;

        /* synthetic */ ContactLookupCallback(RttRequestDialogFragment rttRequestDialogFragment, C06471 r2) {
            this.rttRequestDialogFragmentWeakReference = new WeakReference<>(rttRequestDialogFragment);
        }

        public void onContactInfoComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            RttRequestDialogFragment rttRequestDialogFragment = (RttRequestDialogFragment) this.rttRequestDialogFragmentWeakReference.get();
            if (rttRequestDialogFragment != null) {
                CharSequence displayName = ContactsComponent.get(rttRequestDialogFragment.getContext()).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
                if (TextUtils.isEmpty(displayName)) {
                    if (TextUtils.isEmpty(contactCacheEntry.number)) {
                        displayName = null;
                    } else {
                        displayName = PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(contactCacheEntry.number, TextDirectionHeuristics.LTR));
                    }
                }
                rttRequestDialogFragment.detailsTextView.setText(rttRequestDialogFragment.getString(R.string.rtt_request_dialog_details, displayName));
            }
        }

        public void onImageLoadComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$0$RttRequestDialogFragment(View view) {
        LogUtil.enterBlock("RttRequestDialogFragment.onNegativeButtonClick");
        CallList.getInstance().getCallById(getArguments().getString("call_id")).respondToRttRequest(false, getArguments().getInt("rtt_request_id"));
        dismiss();
    }

    public /* synthetic */ void lambda$onCreateDialog$1$RttRequestDialogFragment(View view) {
        LogUtil.enterBlock("RttRequestDialogFragment.onPositiveButtonClick");
        CallList.getInstance().getCallById(getArguments().getString("call_id")).respondToRttRequest(true, getArguments().getInt("rtt_request_id"));
        dismiss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        LogUtil.enterBlock("RttRequestDialogFragment.onCreateDialog");
        View inflate = View.inflate(getActivity(), R.layout.frag_rtt_request_dialog, (ViewGroup) null);
        this.detailsTextView = (TextView) inflate.findViewById(R.id.details);
        ContactInfoCache.getInstance(getContext()).findInfo(CallList.getInstance().getCallById(getArguments().getString("call_id")), false, new ContactLookupCallback(this, (C06471) null));
        inflate.findViewById(R.id.rtt_button_decline_request).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttRequestDialogFragment.this.lambda$onCreateDialog$0$RttRequestDialogFragment(view);
            }
        });
        inflate.findViewById(R.id.rtt_button_accept_request).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttRequestDialogFragment.this.lambda$onCreateDialog$1$RttRequestDialogFragment(view);
            }
        });
        AlertDialog create = new AlertDialog.Builder(getActivity()).setCancelable(false).setView(inflate).setTitle(R.string.rtt_request_dialog_title).create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }
}
