package com.android.dialer.speeddial;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p002v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.ArraySet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class DisambigDialog extends DialogFragment {
    public static final String FRAGMENT_TAG = "disambig_dialog";
    public List<SpeedDialEntry.Channel> channels;
    public LinearLayout container;
    private final Set<String> phoneNumbers = new ArraySet();
    public CheckBox rememberThisChoice;
    private SpeedDialUiItem speedDialUiItem;

    static /* synthetic */ Void lambda$setDefaultChannel$2(Context context, SpeedDialUiItem speedDialUiItem2, SpeedDialEntry.Channel channel) throws Exception {
        Assert.isWorkerThread();
        SpeedDialEntry.Builder builder = SpeedDialEntry.builder();
        builder.setId(speedDialUiItem2.speedDialEntryId());
        builder.setContactId(speedDialUiItem2.contactId());
        builder.setLookupKey(speedDialUiItem2.lookupKey());
        builder.setDefaultChannel(channel);
        new SpeedDialEntryDatabaseHelper(context).update(ImmutableList.m75of(builder.build()));
        return null;
    }

    private static void setDefaultChannel(Context context, SpeedDialUiItem speedDialUiItem2, SpeedDialEntry.Channel channel) {
        LogUtil.enterBlock("DisambigDialog.setDefaultChannel");
        Futures.addCallback(DialerExecutorComponent.get(context).backgroundExecutor().submit(new Callable(context, speedDialUiItem2, channel) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ SpeedDialUiItem f$1;
            private final /* synthetic */ SpeedDialEntry.Channel f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                DisambigDialog.lambda$setDefaultChannel$2(this.f$0, this.f$1, this.f$2);
                return null;
            }
        }), new DefaultFutureCallback(), DialerExecutorComponent.get(context).backgroundExecutor());
    }

    public static DisambigDialog show(SpeedDialUiItem speedDialUiItem2, FragmentManager fragmentManager) {
        DisambigDialog disambigDialog = new DisambigDialog();
        disambigDialog.speedDialUiItem = speedDialUiItem2;
        disambigDialog.channels = speedDialUiItem2.channels();
        disambigDialog.show(fragmentManager, FRAGMENT_TAG);
        return disambigDialog;
    }

    public /* synthetic */ void lambda$insertOption$0$DisambigDialog(SpeedDialEntry.Channel channel, View view) {
        if (this.rememberThisChoice.isChecked()) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.FAVORITE_SET_VIDEO_DEFAULT);
            setDefaultChannel(getContext().getApplicationContext(), this.speedDialUiItem, channel);
        }
        if (channel.technology() == 3) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.LIGHTBRINGER_VIDEO_REQUESTED_FOR_FAVORITE_CONTACT_DISAMBIG);
        }
        Context context = getContext();
        boolean z = true;
        CallIntentBuilder isVideoCall = new CallIntentBuilder(channel.number(), CallInitiationType$Type.SPEED_DIAL_DISAMBIG_DIALOG).setAllowAssistedDial(true).setIsVideoCall(true);
        if (channel.technology() != 3) {
            z = false;
        }
        PreCall.start(context, isVideoCall.setIsDuoCall(z));
        dismiss();
    }

    public /* synthetic */ void lambda$insertOption$1$DisambigDialog(SpeedDialEntry.Channel channel, View view) {
        if (this.rememberThisChoice.isChecked()) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.FAVORITE_SET_VOICE_DEFAULT);
            setDefaultChannel(getContext().getApplicationContext(), this.speedDialUiItem, channel);
        }
        PreCall.start(getContext(), new CallIntentBuilder(channel.number(), CallInitiationType$Type.SPEED_DIAL_DISAMBIG_DIALOG).setAllowAssistedDial(true));
        dismiss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.disambig_dialog_layout, (ViewGroup) null, false);
        this.container = (LinearLayout) inflate.findViewById(R.id.communication_avenue_container);
        this.rememberThisChoice = (CheckBox) inflate.findViewById(R.id.remember_this_choice_checkbox);
        LinearLayout linearLayout = (LinearLayout) this.container.findViewById(R.id.communication_avenue_container);
        for (SpeedDialEntry.Channel next : this.channels) {
            if (this.phoneNumbers.add(next.number())) {
                if (this.phoneNumbers.size() != 1) {
                    linearLayout.addView(getActivity().getLayoutInflater().inflate(R.layout.disambig_dialog_divider, linearLayout, false));
                }
                String number = next.number();
                String label = next.label();
                View inflate2 = getActivity().getLayoutInflater().inflate(R.layout.disambig_option_header_layout, linearLayout, false);
                if (!TextUtils.isEmpty(label)) {
                    number = getContext().getString(R.string.call_subject_type_and_number, new Object[]{label, number});
                }
                ((TextView) inflate2.findViewById(R.id.disambig_header_phone_label)).setText(number);
                linearLayout.addView(inflate2);
            }
            View inflate3 = getActivity().getLayoutInflater().inflate(R.layout.disambig_option_layout, linearLayout, false);
            if (next.isVideoTechnology()) {
                View findViewById = inflate3.findViewById(R.id.option_container);
                findViewById.setOnClickListener(new View.OnClickListener(next) {
                    private final /* synthetic */ SpeedDialEntry.Channel f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        DisambigDialog.this.lambda$insertOption$0$DisambigDialog(this.f$1, view);
                    }
                });
                findViewById.setContentDescription(getActivity().getString(R.string.disambig_option_video_call));
                ((ImageView) inflate3.findViewById(R.id.disambig_option_image)).setImageResource(R.drawable.quantum_ic_videocam_vd_theme_24);
                ((TextView) inflate3.findViewById(R.id.disambig_option_text)).setText(R.string.disambig_option_video_call);
            } else {
                View findViewById2 = inflate3.findViewById(R.id.option_container);
                findViewById2.setOnClickListener(new View.OnClickListener(next) {
                    private final /* synthetic */ SpeedDialEntry.Channel f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        DisambigDialog.this.lambda$insertOption$1$DisambigDialog(this.f$1, view);
                    }
                });
                findViewById2.setContentDescription(getActivity().getString(R.string.disambig_option_voice_call));
                ((ImageView) inflate3.findViewById(R.id.disambig_option_image)).setImageResource(R.drawable.quantum_ic_phone_vd_theme_24);
                ((TextView) inflate3.findViewById(R.id.disambig_option_text)).setText(R.string.disambig_option_voice_call);
            }
            linearLayout.addView(inflate3);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflate);
        return builder.create();
    }

    public void onPause() {
        super.onPause();
        dismiss();
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(getContext().getResources().getDimensionPixelSize(R.dimen.disambig_dialog_width), -2);
    }
}
