package com.android.dialer.calldetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.app.AccountSelectionActivity;
import com.android.dialer.calldetails.CallDetailsActivityCommon;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.compat.telephony.TelephonyManagerCompat;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.glidephotomanager.GlidePhotoManagerComponent;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.widget.BidiTextView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Objects;

public class CallDetailsHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, DialerExecutor.FailureListener {
    private final RelativeLayout assistedDialingContainer;
    private final TextView assistedDialingInternationalDirectDialCodeAndCountryCodeText;
    private final CallDetailsHeaderListener callDetailsHeaderListener;
    private int callbackAction;
    private final ImageView callbackButton;
    private final QuickContactBadge contactPhoto;
    private final Context context;
    private final BidiTextView nameView;
    private final TextView networkView;
    private final String number;
    private final BidiTextView numberView;
    private final String postDialDigits;

    interface CallDetailsHeaderListener {
    }

    CallDetailsHeaderViewHolder(View view, String str, String str2, CallDetailsHeaderListener callDetailsHeaderListener2) {
        super(view);
        this.context = view.getContext();
        this.callbackButton = (ImageView) view.findViewById(R.id.call_back_button);
        this.nameView = (BidiTextView) view.findViewById(R.id.contact_name);
        this.numberView = (BidiTextView) view.findViewById(R.id.phone_number);
        this.networkView = (TextView) view.findViewById(R.id.network);
        this.contactPhoto = (QuickContactBadge) view.findViewById(R.id.quick_contact_photo);
        this.assistedDialingInternationalDirectDialCodeAndCountryCodeText = (TextView) view.findViewById(R.id.assisted_dialing_text);
        this.assistedDialingContainer = (RelativeLayout) view.findViewById(R.id.assisted_dialing_container);
        RelativeLayout relativeLayout = this.assistedDialingContainer;
        Objects.requireNonNull(callDetailsHeaderListener2);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ((CallDetailsActivityCommon.CallDetailsHeaderListener) CallDetailsHeaderViewHolder.CallDetailsHeaderListener.this).openAssistedDialingSettings(view);
            }
        });
        this.callbackButton.setOnClickListener(this);
        this.callbackButton.setOnLongClickListener(this);
        this.number = str;
        this.postDialDigits = str2;
        this.callDetailsHeaderListener = callDetailsHeaderListener2;
        ((LoggingBindingsStub) Logger.get(this.context)).logQuickContactOnTouch(this.contactPhoto, InteractionEvent$Type.OPEN_QUICK_CONTACT_FROM_CALL_DETAILS, true);
    }

    private void setCallbackAction(int i) {
        this.callbackAction = i;
        if (i == 0) {
            this.callbackButton.setVisibility(8);
        } else if (i == 1 || i == 2) {
            this.callbackButton.setVisibility(0);
            this.callbackButton.setImageResource(R.drawable.quantum_ic_videocam_vd_theme_24);
        } else if (i == 3) {
            this.callbackButton.setVisibility(0);
            this.callbackButton.setImageResource(R.drawable.quantum_ic_call_vd_theme_24);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid action: ", i));
        }
    }

    private void showAssistedDialingContainer(boolean z) {
        if (z) {
            this.assistedDialingContainer.setVisibility(0);
            return;
        }
        LogUtil.m9i("CallDetailsHeaderViewHolder.updateAssistedDialingInfo", "hiding assisted dialing ui elements", new Object[0]);
        this.assistedDialingContainer.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void updateAssistedDialingText(Integer num) {
        if (num.intValue() <= 0) {
            onFailure(new IllegalStateException());
            return;
        }
        LogUtil.m9i("CallDetailsHeaderViewHolder.updateAssistedDialingText", "Updating Assisted Dialing Text", new Object[0]);
        this.assistedDialingInternationalDirectDialCodeAndCountryCodeText.setText(this.context.getString(R.string.assisted_dialing_country_code_entry, new Object[]{String.valueOf(num)}));
    }

    public void onClick(View view) {
        if (view == this.callbackButton) {
            int i = this.callbackAction;
            if (i == 1) {
                ((CallDetailsActivityCommon.CallDetailsHeaderListener) this.callDetailsHeaderListener).placeImsVideoCall(this.number);
            } else if (i == 2) {
                ((CallDetailsActivityCommon.CallDetailsHeaderListener) this.callDetailsHeaderListener).placeDuoVideoCall(this.number);
            } else if (i == 3) {
                ((CallDetailsActivityCommon.CallDetailsHeaderListener) this.callDetailsHeaderListener).placeVoiceCall(this.number, this.postDialDigits);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid action: ");
                outline13.append(this.callbackAction);
                throw new IllegalStateException(outline13.toString());
            }
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("View OnClickListener not implemented: ", view));
        }
    }

    public void onFailure(Throwable th) {
        this.assistedDialingInternationalDirectDialCodeAndCountryCodeText.setText(R.string.assisted_dialing_country_code_entry_failure);
    }

    public boolean onLongClick(View view) {
        Intent createIntent;
        if (view != this.callbackButton || (createIntent = AccountSelectionActivity.createIntent(view.getContext(), this.number, CallInitiationType$Type.CALL_DETAILS)) == null) {
            return false;
        }
        DialerUtils.startActivityWithErrorToast(view.getContext(), createIntent, R.string.activity_not_available);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void updateAssistedDialingInfo(CallDetailsEntries.CallDetailsEntry callDetailsEntry) {
        if (callDetailsEntry != null) {
            if ((Integer.valueOf(callDetailsEntry.getFeatures()).intValue() & TelephonyManagerCompat.FEATURES_ASSISTED_DIALING.intValue()) == TelephonyManagerCompat.FEATURES_ASSISTED_DIALING.intValue()) {
                showAssistedDialingContainer(true);
                ((CallDetailsActivityCommon.CallDetailsHeaderListener) this.callDetailsHeaderListener).createAssistedDialerNumberParserTask(new CallDetailsActivityCommon.AssistedDialingNumberParseWorker(), new DialerExecutor.SuccessListener() {
                    public final void onSuccess(Object obj) {
                        CallDetailsHeaderViewHolder.this.updateAssistedDialingText((Integer) obj);
                    }
                }, new DialerExecutor.FailureListener() {
                    public final void onFailure(Throwable th) {
                        CallDetailsHeaderViewHolder.this.onFailure(th);
                    }
                });
                return;
            }
        }
        showAssistedDialingContainer(false);
    }

    /* access modifiers changed from: package-private */
    public void updateContactInfo(DialerContact dialerContact, int i) {
        String str;
        ContactPhotoManager.getInstance(this.context).loadDialerThumbnailOrPhoto(this.contactPhoto, dialerContact.getContactUri().isEmpty() ? null : Uri.parse(dialerContact.getContactUri()), dialerContact.getPhotoId(), dialerContact.getPhotoUri().isEmpty() ? null : Uri.parse(dialerContact.getPhotoUri()), dialerContact.getNameOrNumber(), dialerContact.getContactType());
        this.numberView.setVisibility(8);
        this.numberView.setText((CharSequence) null);
        if (PhoneNumberHelper.isLocalEmergencyNumber(this.context, dialerContact.getNumber())) {
            this.nameView.setText(this.context.getResources().getString(R.string.emergency_number));
        } else {
            this.nameView.setText(dialerContact.getNameOrNumber());
            if (!TextUtils.isEmpty(dialerContact.getDisplayNumber())) {
                this.numberView.setVisibility(0);
                if (TextUtils.isEmpty(dialerContact.getNumberLabel())) {
                    str = dialerContact.getDisplayNumber();
                } else {
                    str = this.context.getString(R.string.call_subject_type_and_number, new Object[]{dialerContact.getNumberLabel(), dialerContact.getDisplayNumber()});
                }
                this.numberView.setText(str);
            }
        }
        if (!TextUtils.isEmpty(dialerContact.getSimDetails().getNetwork())) {
            this.networkView.setVisibility(0);
            this.networkView.setText(dialerContact.getSimDetails().getNetwork());
            if (dialerContact.getSimDetails().getColor() != 0) {
                this.networkView.setTextColor(dialerContact.getSimDetails().getColor());
            }
        }
        setCallbackAction(i);
    }

    /* access modifiers changed from: package-private */
    public void updateContactInfo(CallDetailsHeaderInfo callDetailsHeaderInfo, int i) {
        ((GlidePhotoManagerImpl) GlidePhotoManagerComponent.get(this.context).glidePhotoManager()).loadQuickContactBadge(this.contactPhoto, callDetailsHeaderInfo.getPhotoInfo());
        this.nameView.setText(callDetailsHeaderInfo.getPrimaryText());
        if (!callDetailsHeaderInfo.getSecondaryText().isEmpty()) {
            this.numberView.setVisibility(0);
            this.numberView.setText(callDetailsHeaderInfo.getSecondaryText());
        } else {
            this.numberView.setVisibility(8);
            this.numberView.setText((CharSequence) null);
        }
        setCallbackAction(i);
    }
}
