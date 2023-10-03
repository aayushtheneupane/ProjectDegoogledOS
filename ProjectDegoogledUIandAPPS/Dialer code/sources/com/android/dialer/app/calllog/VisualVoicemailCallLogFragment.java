package com.android.dialer.app.calllog;

import android.app.Fragment;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.VoicemailContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogFragment;
import com.android.dialer.app.voicemail.VoicemailErrorManager;
import com.android.dialer.app.voicemail.VoicemailPlaybackPresenter;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.voicemail.listui.error.VoicemailErrorMessageCreator;
import com.android.dialer.voicemail.listui.error.VoicemailStatus;
import com.android.dialer.voicemail.listui.error.VoicemailStatusWorker;
import com.android.dialer.widget.EmptyContentView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public class VisualVoicemailCallLogFragment extends CallLogFragment {
    private DialerExecutor<Context> preSyncVoicemailStatusCheckExecutor;
    private VoicemailErrorManager voicemailErrorManager;
    private VoicemailPlaybackPresenter voicemailPlaybackPresenter;
    private final ContentObserver voicemailStatusObserver = new CallLogFragment.CustomContentObserver();

    public VisualVoicemailCallLogFragment() {
        super(4, -1);
    }

    /* access modifiers changed from: private */
    public void onPreSyncVoicemailStatusChecked(List<VoicemailStatus> list) {
        if (shouldAutoSync(new VoicemailErrorMessageCreator(), list)) {
            Intent intent = new Intent("android.provider.action.SYNC_VOICEMAIL");
            intent.setPackage(getActivity().getPackageName());
            getActivity().sendBroadcast(intent);
        }
    }

    public void fetchCalls() {
        super.fetchCalls();
        if (FragmentUtils.getParent((Fragment) this, CallLogFragment.CallLogFragmentListener.class) != null) {
            ((CallLogFragment.CallLogFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, CallLogFragment.CallLogFragmentListener.class)).updateTabUnreadCounts();
        }
    }

    /* access modifiers changed from: protected */
    public VoicemailPlaybackPresenter getVoicemailPlaybackPresenter() {
        return this.voicemailPlaybackPresenter;
    }

    public void onActivityCreated(Bundle bundle) {
        this.voicemailPlaybackPresenter = VoicemailPlaybackPresenter.getInstance(getActivity(), bundle);
        if (!PermissionsUtil.hasReadVoicemailPermissions(getContext()) || !PermissionsUtil.hasAddVoicemailPermissions(getContext())) {
            LogUtil.m10w("VisualVoicemailCallLogFragment.onActivityCreated", "read voicemail permission unavailable.", new Object[0]);
        } else {
            getActivity().getContentResolver().registerContentObserver(VoicemailContract.Status.CONTENT_URI, true, this.voicemailStatusObserver);
        }
        super.onActivityCreated(bundle);
        this.preSyncVoicemailStatusCheckExecutor = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(getActivity().getFragmentManager(), "fetchVoicemailStatus", new VoicemailStatusWorker()).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                VisualVoicemailCallLogFragment.this.onPreSyncVoicemailStatusChecked((List) obj);
            }
        }).build();
        this.voicemailErrorManager = new VoicemailErrorManager(getContext(), getAdapter().getAlertManager(), this.modalAlertManager);
        if (!PermissionsUtil.hasReadVoicemailPermissions(getContext()) || !PermissionsUtil.hasAddVoicemailPermissions(getContext())) {
            LogUtil.m10w("VisualVoicemailCallLogFragment.onActivityCreated", "read voicemail permission unavailable.", new Object[0]);
        } else {
            getActivity().getContentResolver().registerContentObserver(VoicemailContract.Status.CONTENT_URI, true, this.voicemailErrorManager.getContentObserver());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.call_log_fragment, viewGroup, false);
        setupView(inflate);
        ((EmptyContentView) inflate.findViewById(R.id.empty_list_view)).setImage(R.drawable.quantum_ic_voicemail_vd_theme_24);
        return inflate;
    }

    public void onDestroy() {
        if (isAdded()) {
            getActivity().getContentResolver().unregisterContentObserver(this.voicemailErrorManager.getContentObserver());
            this.voicemailPlaybackPresenter.onDestroy();
            this.voicemailErrorManager.onDestroy();
            getActivity().getContentResolver().unregisterContentObserver(this.voicemailStatusObserver);
        }
        super.onDestroy();
    }

    public void onNotVisible() {
        LogUtil.enterBlock("VisualVoicemailCallLogFragment.onNotVisible");
        LogUtil.enterBlock("CallLogFragment.onPageUnselected");
        if (getActivity() != null) {
            getActivity().setVolumeControlStream(Integer.MIN_VALUE);
            if (!((KeyguardManager) getActivity().getSystemService(KeyguardManager.class)).inKeyguardRestrictedInputMode()) {
                LogUtil.m9i("VisualVoicemailCallLogFragment.onNotVisible", "clearing all new voicemails", new Object[0]);
                CallLogNotificationsService.markAllNewVoicemailsAsOld(getActivity());
            }
        }
    }

    public void onPause() {
        this.voicemailPlaybackPresenter.onPause();
        this.voicemailErrorManager.onPause();
        AudioManager audioManager = (AudioManager) getContext().getSystemService(AudioManager.class);
        if (audioManager.isSpeakerphoneOn()) {
            audioManager.setSpeakerphoneOn(false);
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.voicemailPlaybackPresenter.onResume();
        this.voicemailErrorManager.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        VoicemailPlaybackPresenter voicemailPlaybackPresenter2 = this.voicemailPlaybackPresenter;
        if (voicemailPlaybackPresenter2 != null) {
            voicemailPlaybackPresenter2.onSaveInstanceState(bundle);
        }
    }

    public void onVisible() {
        DialerExecutor<Context> dialerExecutor;
        LogUtil.enterBlock("VisualVoicemailCallLogFragment.onVisible");
        LogUtil.enterBlock("CallLogFragment.onPageSelected");
        if (!(getActivity() == null || FragmentUtils.getParent((Fragment) this, CallLogFragment.HostInterface.class) == null)) {
            ((CallLogFragment.HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, CallLogFragment.HostInterface.class)).enableFloatingButton(!isModalAlertVisible());
        }
        if (getActivity() != null && (dialerExecutor = this.preSyncVoicemailStatusCheckExecutor) != null) {
            dialerExecutor.executeParallel(getActivity());
            ((LoggingBindingsStub) Logger.get(getActivity())).logImpression(DialerImpression$Type.VVM_TAB_VIEWED);
            getActivity().setVolumeControlStream(0);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAutoSync(VoicemailErrorMessageCreator voicemailErrorMessageCreator, List<VoicemailStatus> list) {
        for (VoicemailStatus next : list) {
            if (next.isActive(getContext()) && voicemailErrorMessageCreator.isSyncBlockingError(next)) {
                LogUtil.m9i("VisualVoicemailCallLogFragment.shouldAutoSync", GeneratedOutlineSupport.outline6("auto-sync blocked due to ", next), new Object[0]);
                return false;
            }
        }
        return true;
    }
}
