package com.android.incallui.spam;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.support.p002v7.app.AlertDialog;
import android.support.p002v7.appcompat.R$style;
import android.telephony.PhoneNumberUtils;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.ContactLookupResult$Type;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ReportingLocation$Type;
import com.android.dialer.notification.DialerNotificationManager;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.SpamSettings;
import com.android.dialer.spam.promo.SpamBlockingPromoHelper;
import com.android.dialer.spam.stub.SpamSettingsStub;
import com.android.dialer.spam.stub.SpamStub;

public class SpamNotificationActivity extends FragmentActivity {
    private final DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            if (!SpamNotificationActivity.this.isFinishing()) {
                SpamNotificationActivity.this.finish();
            }
        }
    };
    private FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler;
    private SpamBlockingPromoHelper spamBlockingPromoHelper;
    private SpamSettings spamSettings;

    public static class FirstTimeNonSpamCallDialogFragment extends DialogFragment {
        private Context context;
        /* access modifiers changed from: private */
        public boolean dismissed;

        public void onAttach(Context context2) {
            super.onAttach(context2);
            this.context = context2.getApplicationContext();
        }

        public Dialog onCreateDialog(Bundle bundle) {
            super.onCreateDialog(bundle);
            final SpamNotificationActivity spamNotificationActivity = (SpamNotificationActivity) getActivity();
            final String string = getArguments().getString("phone_number");
            final ContactLookupResult$Type forNumber = ContactLookupResult$Type.forNumber(getArguments().getInt("contact_lookup_result_type", 0));
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((CharSequence) getString(R.string.non_spam_notification_title, new Object[]{SpamNotificationActivity.getFormattedNumber(string, this.context)}));
            builder.setCancelable(false);
            builder.setMessage((CharSequence) getString(R$style.getResourceIdByName("spam_notification_non_spam_call_expanded_text", this.context)));
            builder.setNeutralButton(getString(R.string.spam_notification_action_dismiss), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirstTimeNonSpamCallDialogFragment.this.dismiss();
                }
            });
            builder.setPositiveButton((CharSequence) getString(R.string.spam_notification_dialog_add_contact_action_text), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean unused = FirstTimeNonSpamCallDialogFragment.this.dismissed = true;
                    FirstTimeNonSpamCallDialogFragment.this.dismiss();
                    FirstTimeNonSpamCallDialogFragment.this.startActivity(SpamNotificationActivity.createInsertContactsIntent(string));
                }
            });
            builder.setNegativeButton((CharSequence) getString(R$style.getResourceIdByName("spam_notification_dialog_block_report_spam_action_text", this.context)), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean unused = FirstTimeNonSpamCallDialogFragment.this.dismissed = true;
                    FirstTimeNonSpamCallDialogFragment.this.dismiss();
                    spamNotificationActivity.maybeShowBlockReportSpamDialog(string, forNumber);
                    SpamNotificationActivity.access$900(spamNotificationActivity);
                }
            });
            return builder.create();
        }

        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            SpamNotificationActivity.logCallImpression(this.context, getArguments(), DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_ON_DISMISS_NON_SPAM_DIALOG);
            if (!this.dismissed && getActivity() != null && !getActivity().isFinishing()) {
                getActivity().finish();
            }
        }

        public void onPause() {
            dismiss();
            super.onPause();
        }
    }

    public static class FirstTimeSpamCallDialogFragment extends DialogFragment {
        private Context applicationContext;
        /* access modifiers changed from: private */
        public boolean dismissed;

        public void onAttach(Context context) {
            super.onAttach(context);
            this.applicationContext = context.getApplicationContext();
        }

        public Dialog onCreateDialog(Bundle bundle) {
            super.onCreateDialog(bundle);
            final SpamNotificationActivity spamNotificationActivity = (SpamNotificationActivity) getActivity();
            final String string = getArguments().getString("phone_number");
            final ContactLookupResult$Type forNumber = ContactLookupResult$Type.forNumber(getArguments().getInt("contact_lookup_result_type", 0));
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setTitle((CharSequence) getString(R$style.getResourceIdByName("spam_notification_title", this.applicationContext), new Object[]{SpamNotificationActivity.getFormattedNumber(string, this.applicationContext)}));
            builder.setNeutralButton(getString(R.string.spam_notification_action_dismiss), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirstTimeSpamCallDialogFragment.this.dismiss();
                }
            });
            builder.setPositiveButton((CharSequence) getString(R.string.spam_notification_block_spam_action_text), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean unused = FirstTimeSpamCallDialogFragment.this.dismissed = true;
                    FirstTimeSpamCallDialogFragment.this.dismiss();
                    spamNotificationActivity.maybeShowBlockReportSpamDialog(string, forNumber);
                    SpamNotificationActivity.access$900(spamNotificationActivity);
                }
            });
            builder.setNegativeButton((CharSequence) getString(R$style.getResourceIdByName("spam_notification_was_not_spam_action_text", this.applicationContext)), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean unused = FirstTimeSpamCallDialogFragment.this.dismissed = true;
                    FirstTimeSpamCallDialogFragment.this.dismiss();
                    spamNotificationActivity.maybeShowNotSpamDialog(string, forNumber);
                }
            });
            return builder.create();
        }

        public void onDismiss(DialogInterface dialogInterface) {
            SpamNotificationActivity.logCallImpression(this.applicationContext, getArguments(), DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_ON_DISMISS_SPAM_DIALOG);
            super.onDismiss(dialogInterface);
            if (!this.dismissed && getActivity() != null && !getActivity().isFinishing()) {
                getActivity().finish();
            }
        }

        public void onPause() {
            dismiss();
            super.onPause();
        }
    }

    static /* synthetic */ void access$900(SpamNotificationActivity spamNotificationActivity) {
        spamNotificationActivity.spamBlockingPromoHelper.shouldShowAfterCallSpamBlockingPromo();
        spamNotificationActivity.finish();
    }

    private void blockReportNumber(String str, boolean z, ContactLookupResult$Type contactLookupResult$Type) {
        if (z) {
            logCallImpression(DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_MARKED_NUMBER_AS_SPAM);
            ((SpamStub) SpamComponent.get(this).spam()).reportSpamFromAfterCallNotification(str, R$style.getCurrentCountryIso(this), 1, ReportingLocation$Type.FEEDBACK_PROMPT, contactLookupResult$Type);
        }
        logCallImpression(DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_BLOCK_NUMBER);
        this.filteredNumberAsyncQueryHandler.blockNumber((FilteredNumberAsyncQueryHandler.OnBlockNumberListener) null, str, R$style.getCurrentCountryIso(this));
    }

    /* access modifiers changed from: private */
    public static Intent createInsertContactsIntent(String str) {
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.addFlags(268435456);
        intent.addFlags(67108864);
        intent.setType("vnd.android.cursor.dir/raw_contact");
        intent.putExtra("phone", str);
        return intent;
    }

    private Bundle getCallInfo() {
        if (getIntent().hasExtra("call_info")) {
            return getIntent().getBundleExtra("call_info");
        }
        return new Bundle();
    }

    /* access modifiers changed from: private */
    public static String getFormattedNumber(String str, Context context) {
        return PhoneNumberUtils.createTtsSpannable(PhoneNumberHelper.formatNumber(context, str, R$style.getCurrentCountryIso(context))).toString();
    }

    private void logCallImpression(DialerImpression$Type dialerImpression$Type) {
        logCallImpression(this, getCallInfo(), dialerImpression$Type);
    }

    /* access modifiers changed from: private */
    public void maybeShowBlockReportSpamDialog(String str, ContactLookupResult$Type contactLookupResult$Type) {
        ((SpamSettingsStub) SpamComponent.get(this).spamSettings()).isDialogEnabledForSpamNotification();
        blockReportNumber(str, true, contactLookupResult$Type);
    }

    /* access modifiers changed from: private */
    public void maybeShowNotSpamDialog(String str, ContactLookupResult$Type contactLookupResult$Type) {
        ((SpamSettingsStub) SpamComponent.get(this).spamSettings()).isDialogEnabledForSpamNotification();
        reportNotSpamAndFinish(str, contactLookupResult$Type);
    }

    private void reportNotSpamAndFinish(String str, ContactLookupResult$Type contactLookupResult$Type) {
        logCallImpression(DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_REPORT_NUMBER_AS_NOT_SPAM);
        ((SpamStub) SpamComponent.get(this).spam()).reportNotSpamFromAfterCallNotification(str, R$style.getCurrentCountryIso(this), 1, ReportingLocation$Type.FEEDBACK_PROMPT, contactLookupResult$Type);
        finish();
    }

    public /* synthetic */ void lambda$showSpamBlockingPromoDialog$0$SpamNotificationActivity(boolean z) {
        if (!z) {
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.SPAM_BLOCKING_MODIFY_FAILURE_THROUGH_AFTER_CALL_NOTIFICATION_PROMO);
        }
        this.spamBlockingPromoHelper.showModifySettingOnCompleteToast(z);
    }

    public /* synthetic */ void lambda$showSpamBlockingPromoDialog$1$SpamNotificationActivity() {
        ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.SPAM_BLOCKING_ENABLED_THROUGH_AFTER_CALL_NOTIFICATION_PROMO);
        ((SpamSettingsStub) this.spamSettings).modifySpamBlockingSetting(true, new SpamSettings.ModifySettingListener() {
            public final void onComplete(boolean z) {
                SpamNotificationActivity.this.lambda$showSpamBlockingPromoDialog$0$SpamNotificationActivity(z);
            }
        });
    }

    public /* synthetic */ void lambda$showSpamBlockingPromoDialog$2$SpamNotificationActivity(DialogInterface dialogInterface) {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.m9i("SpamNotifications", "onCreate", new Object[0]);
        super.onCreate(bundle);
        setFinishOnTouchOutside(true);
        this.filteredNumberAsyncQueryHandler = new FilteredNumberAsyncQueryHandler(this);
        this.spamSettings = SpamComponent.get(this).spamSettings();
        this.spamBlockingPromoHelper = new SpamBlockingPromoHelper(getApplicationContext(), this.spamSettings);
        DialerNotificationManager.cancel(this, getIntent().getStringExtra("notification_tag"), getIntent().getIntExtra("notification_id", 1));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.filteredNumberAsyncQueryHandler = null;
        if (!isFinishing()) {
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0048, code lost:
        if (r1.equals("com.android.incallui.spam.ACTION_ADD_TO_CONTACTS") != false) goto L_0x0074;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume() {
        /*
            r9 = this;
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "SpamNotifications"
            java.lang.String r3 = "onResume"
            com.android.dialer.common.LogUtil.m9i(r2, r3, r1)
            super.onResume()
            android.content.Intent r1 = r9.getIntent()
            android.os.Bundle r2 = r9.getCallInfo()
            java.lang.String r3 = "phone_number"
            java.lang.String r2 = r2.getString(r3)
            android.os.Bundle r3 = r9.getCallInfo()
            java.lang.String r4 = "is_spam"
            boolean r3 = r3.getBoolean(r4)
            android.os.Bundle r4 = r9.getCallInfo()
            java.lang.String r5 = "contact_lookup_result_type"
            int r4 = r4.getInt(r5, r0)
            com.android.dialer.logging.ContactLookupResult$Type.forNumber(r4)
            java.lang.String r1 = r1.getAction()
            int r4 = r1.hashCode()
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            switch(r4) {
                case -1292075633: goto L_0x0069;
                case -585181605: goto L_0x005f;
                case -474617725: goto L_0x0055;
                case 211455871: goto L_0x004b;
                case 1419322346: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x0073
        L_0x0042:
            java.lang.String r4 = "com.android.incallui.spam.ACTION_ADD_TO_CONTACTS"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0073
            goto L_0x0074
        L_0x004b:
            java.lang.String r0 = "com.android.incallui.spam.ACTION_SHOW_SPAM_BLOCKING_PROMO_DIALOG"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0073
            r0 = r5
            goto L_0x0074
        L_0x0055:
            java.lang.String r0 = "com.android.incallui.spam.ACTION_MARK_NUMBER_AS_SPAM"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0073
            r0 = r8
            goto L_0x0074
        L_0x005f:
            java.lang.String r0 = "com.android.incallui.spam.ACTION_SHOW_DIALOG"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0073
            r0 = r6
            goto L_0x0074
        L_0x0069:
            java.lang.String r0 = "com.android.incallui.spam.ACTION_MARK_NUMBER_AS_NOT_SPAM"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0073
            r0 = r7
            goto L_0x0074
        L_0x0073:
            r0 = -1
        L_0x0074:
            if (r0 == 0) goto L_0x00f4
            java.lang.String r1 = "Cannot start this activity with given action because dialogs are not enabled."
            if (r0 == r8) goto L_0x00e1
            if (r0 == r7) goto L_0x00ce
            if (r0 == r6) goto L_0x0096
            if (r0 == r5) goto L_0x0082
            goto L_0x0103
        L_0x0082:
            com.android.dialer.spam.promo.SpamBlockingPromoHelper r0 = r9.spamBlockingPromoHelper
            android.support.v4.app.FragmentManager r1 = r9.getSupportFragmentManager()
            com.android.incallui.spam.-$$Lambda$SpamNotificationActivity$Cc2-6PR3l7aLEWeRys1lEr4zDro r2 = new com.android.incallui.spam.-$$Lambda$SpamNotificationActivity$Cc2-6PR3l7aLEWeRys1lEr4zDro
            r2.<init>()
            com.android.incallui.spam.-$$Lambda$SpamNotificationActivity$7lqhJhZeSXcxnmtatRw-qpNlL_0 r3 = new com.android.incallui.spam.-$$Lambda$SpamNotificationActivity$7lqhJhZeSXcxnmtatRw-qpNlL_0
            r3.<init>()
            r0.showSpamBlockingPromoDialog(r1, r2, r3)
            goto L_0x0103
        L_0x0096:
            if (r3 == 0) goto L_0x00b3
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_SHOW_SPAM_DIALOG
            r9.logCallImpression(r0)
            android.os.Bundle r0 = r9.getCallInfo()
            com.android.incallui.spam.SpamNotificationActivity$FirstTimeSpamCallDialogFragment r1 = new com.android.incallui.spam.SpamNotificationActivity$FirstTimeSpamCallDialogFragment
            r1.<init>()
            r1.setArguments(r0)
            android.app.FragmentManager r9 = r9.getFragmentManager()
            java.lang.String r0 = "FirstTimeSpamDialog"
            r1.show(r9, r0)
            goto L_0x0103
        L_0x00b3:
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_SHOW_NON_SPAM_DIALOG
            r9.logCallImpression(r0)
            android.os.Bundle r0 = r9.getCallInfo()
            com.android.incallui.spam.SpamNotificationActivity$FirstTimeNonSpamCallDialogFragment r1 = new com.android.incallui.spam.SpamNotificationActivity$FirstTimeNonSpamCallDialogFragment
            r1.<init>()
            r1.setArguments(r0)
            android.app.FragmentManager r9 = r9.getFragmentManager()
            java.lang.String r0 = "FirstTimeNonSpamDialog"
            r1.show(r9, r0)
            goto L_0x0103
        L_0x00ce:
            com.android.dialer.spam.SpamComponent r9 = com.android.dialer.spam.SpamComponent.get(r9)
            com.android.dialer.spam.SpamSettings r9 = r9.spamSettings()
            com.android.dialer.spam.stub.SpamSettingsStub r9 = (com.android.dialer.spam.stub.SpamSettingsStub) r9
            r9.isDialogEnabledForSpamNotification()
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r1)
            throw r9
        L_0x00e1:
            com.android.dialer.spam.SpamComponent r9 = com.android.dialer.spam.SpamComponent.get(r9)
            com.android.dialer.spam.SpamSettings r9 = r9.spamSettings()
            com.android.dialer.spam.stub.SpamSettingsStub r9 = (com.android.dialer.spam.stub.SpamSettingsStub) r9
            r9.isDialogEnabledForSpamNotification()
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r1)
            throw r9
        L_0x00f4:
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.SPAM_AFTER_CALL_NOTIFICATION_ADD_TO_CONTACTS
            r9.logCallImpression(r0)
            android.content.Intent r0 = createInsertContactsIntent(r2)
            r9.startActivity(r0)
            r9.finish()
        L_0x0103:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.spam.SpamNotificationActivity.onResume():void");
    }

    /* access modifiers changed from: private */
    public static void logCallImpression(Context context, Bundle bundle, DialerImpression$Type dialerImpression$Type) {
        ((LoggingBindingsStub) Logger.get(context)).logCallImpression(dialerImpression$Type, bundle.getString("call_id"), bundle.getLong("call_start_time_millis", 0));
    }
}
