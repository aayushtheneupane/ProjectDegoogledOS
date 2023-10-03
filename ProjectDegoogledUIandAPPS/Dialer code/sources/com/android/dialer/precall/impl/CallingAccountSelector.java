package com.android.dialer.precall.impl;

import android.app.Activity;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import com.android.contacts.common.widget.SelectPhoneAccountDialogFragment;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.function.Consumer;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallCoordinator;
import com.android.dialer.precall.impl.PreCallCoordinatorImpl;
import com.android.dialer.preferredsim.PreferredAccountRecorder;
import com.android.dialer.preferredsim.PreferredAccountWorker;
import com.android.dialer.preferredsim.impl.PreferredAccountWorkerImpl;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CallingAccountSelector implements PreCallAction {
    static final String TAG_CALLING_ACCOUNT_SELECTOR = "CallingAccountSelector";
    /* access modifiers changed from: private */
    public boolean isDiscarding;
    private final PreferredAccountWorker preferredAccountWorker;
    private SelectPhoneAccountDialogFragment selectPhoneAccountDialogFragment;

    private class SelectedListener extends SelectPhoneAccountDialogFragment.SelectPhoneAccountListener {
        private final PreCallCoordinator coordinator;
        private final PreCallCoordinator.PendingAction listener;
        private final PreferredAccountRecorder recorder;

        public SelectedListener(PreCallCoordinator preCallCoordinator, PreCallCoordinator.PendingAction pendingAction, PreferredAccountRecorder preferredAccountRecorder) {
            Assert.isNotNull(preCallCoordinator);
            this.coordinator = preCallCoordinator;
            Assert.isNotNull(pendingAction);
            this.listener = pendingAction;
            Assert.isNotNull(preferredAccountRecorder);
            this.recorder = preferredAccountRecorder;
        }

        public void onDialogDismissed(String str) {
            if (!CallingAccountSelector.this.isDiscarding) {
                ((PreCallCoordinatorImpl) this.coordinator).abortCall();
                ((PreCallCoordinatorImpl.PendingActionImpl) this.listener).finish();
            }
        }

        public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
            ((PreCallCoordinatorImpl) this.coordinator).getBuilder().setPhoneAccountHandle(phoneAccountHandle);
            this.recorder.record(((PreCallCoordinatorImpl) this.coordinator).getActivity(), phoneAccountHandle, z);
            ((PreCallCoordinatorImpl.PendingActionImpl) this.listener).finish();
        }
    }

    CallingAccountSelector(PreferredAccountWorker preferredAccountWorker2) {
        this.preferredAccountWorker = preferredAccountWorker2;
    }

    private void showDialog(PreCallCoordinator preCallCoordinator, PreCallCoordinator.PendingAction pendingAction, SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, String str, String str2, SuggestionProvider.Suggestion suggestion) {
        Assert.isMainThread();
        this.selectPhoneAccountDialogFragment = SelectPhoneAccountDialogFragment.newInstance(selectPhoneAccountDialogOptions, new SelectedListener(preCallCoordinator, pendingAction, new PreferredAccountRecorder(str2, suggestion, str)));
        this.selectPhoneAccountDialogFragment.show(((PreCallCoordinatorImpl) preCallCoordinator).getActivity().getFragmentManager(), TAG_CALLING_ACCOUNT_SELECTOR);
    }

    public /* synthetic */ void lambda$processPreferredAccount$0$CallingAccountSelector(CallIntentBuilder callIntentBuilder, PreCallCoordinator preCallCoordinator, PreCallCoordinator.PendingAction pendingAction, String str, PreferredAccountWorker.Result result) {
        if (!this.isDiscarding) {
            if (result.getSelectedPhoneAccountHandle().isPresent()) {
                if (result.getSuggestion().isPresent() && result.getSelectedPhoneAccountHandle().get().equals(result.getSuggestion().get().phoneAccountHandle)) {
                    callIntentBuilder.getInCallUiIntentExtras().putString("sim_suggestion_reason", result.getSuggestion().get().reason.name());
                }
                ((PreCallCoordinatorImpl) preCallCoordinator).getBuilder().setPhoneAccountHandle(result.getSelectedPhoneAccountHandle().get());
                ((PreCallCoordinatorImpl.PendingActionImpl) pendingAction).finish();
                return;
            }
            showDialog(preCallCoordinator, pendingAction, (SelectPhoneAccountDialogOptions) result.getDialogOptionsBuilder().get().build(), result.getDataId().orNull(), str, result.getSuggestion().orNull());
        }
    }

    public void onDiscard() {
        this.isDiscarding = true;
        SelectPhoneAccountDialogFragment selectPhoneAccountDialogFragment2 = this.selectPhoneAccountDialogFragment;
        if (selectPhoneAccountDialogFragment2 != null) {
            selectPhoneAccountDialogFragment2.dismiss();
        }
    }

    public boolean requiresUi(Context context, CallIntentBuilder callIntentBuilder) {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("precall_calling_account_selector_enabled", true) && callIntentBuilder.getPhoneAccountHandle() == null && !PhoneNumberUtils.isEmergencyNumber(callIntentBuilder.getUri().getSchemeSpecificPart()) && ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts().size() > 1;
    }

    public void runWithUi(PreCallCoordinator preCallCoordinator) {
        PreCallCoordinatorImpl preCallCoordinatorImpl = (PreCallCoordinatorImpl) preCallCoordinator;
        CallIntentBuilder builder = preCallCoordinatorImpl.getBuilder();
        Activity activity = preCallCoordinatorImpl.getActivity();
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(activity).getConfigProvider()).getBoolean("precall_calling_account_selector_enabled", true) && builder.getPhoneAccountHandle() == null && !PhoneNumberUtils.isEmergencyNumber(builder.getUri().getSchemeSpecificPart()) && ((TelecomManager) activity.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts().size() > 1) {
            String scheme = builder.getUri().getScheme();
            char c = 65535;
            int hashCode = scheme.hashCode();
            if (hashCode != -1018298903) {
                if (hashCode == 114715 && scheme.equals("tel")) {
                    c = 1;
                }
            } else if (scheme.equals("voicemail")) {
                c = 0;
            }
            if (c == 0) {
                showDialog(preCallCoordinatorImpl, preCallCoordinatorImpl.startPendingAction(), ((PreferredAccountWorkerImpl) this.preferredAccountWorker).getVoicemailDialogOptions(), (String) null, (String) null, (SuggestionProvider.Suggestion) null);
                ((LoggingBindingsStub) Logger.get(preCallCoordinatorImpl.getActivity())).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_VOICEMAIL);
            } else if (c != 1) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("unable to process scheme ");
                outline13.append(builder.getUri().getScheme());
                LogUtil.m8e("CallingAccountSelector.run", outline13.toString(), new Object[0]);
            } else {
                Assert.isMainThread();
                CallIntentBuilder builder2 = preCallCoordinatorImpl.getBuilder();
                Activity activity2 = preCallCoordinatorImpl.getActivity();
                String schemeSpecificPart = builder2.getUri().getSchemeSpecificPart();
                preCallCoordinatorImpl.listen(((PreferredAccountWorkerImpl) this.preferredAccountWorker).selectAccount(schemeSpecificPart, ((TelecomManager) activity2.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()), new Consumer(builder2, preCallCoordinatorImpl, preCallCoordinatorImpl.startPendingAction(), schemeSpecificPart) {
                    private final /* synthetic */ CallIntentBuilder f$1;
                    private final /* synthetic */ PreCallCoordinator f$2;
                    private final /* synthetic */ PreCallCoordinator.PendingAction f$3;
                    private final /* synthetic */ String f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void accept(Object obj) {
                        CallingAccountSelector.this.lambda$processPreferredAccount$0$CallingAccountSelector(this.f$1, this.f$2, this.f$3, this.f$4, (PreferredAccountWorker.Result) obj);
                    }
                }, $$Lambda$CallingAccountSelector$oKd5kmcVsBzmRb1tSz3gCZQbGA.INSTANCE);
            }
        }
    }

    public void runWithoutUi(Context context, CallIntentBuilder callIntentBuilder) {
    }
}
