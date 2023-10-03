package com.android.incallui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.dialer.R;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.incallui.ConferenceManagerPresenter;
import com.android.incallui.baseui.BaseFragment;
import com.android.incallui.baseui.C0701Ui;
import com.android.incallui.baseui.Presenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import java.util.List;

public class ConferenceManagerFragment extends BaseFragment<ConferenceManagerPresenter, ConferenceManagerPresenter.ConferenceManagerUi> implements ConferenceManagerPresenter.ConferenceManagerUi {
    private ListView conferenceParticipantList;
    private ConferenceParticipantListAdapter conferenceParticipantListAdapter;
    private ContactPhotoManager contactPhotoManager;

    public Presenter createPresenter() {
        return new ConferenceManagerPresenter();
    }

    public C0701Ui getUi() {
        return this;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            ((LoggingBindingsStub) Logger.get(getContext())).logScreenView(ScreenEvent$Type.CONFERENCE_MANAGEMENT, getActivity());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.conference_manager_fragment, viewGroup, false);
        this.conferenceParticipantList = (ListView) inflate.findViewById(R.id.participantList);
        this.contactPhotoManager = ContactPhotoManager.getInstance(getActivity().getApplicationContext());
        return inflate;
    }

    public void onResume() {
        super.onResume();
        ((ConferenceManagerPresenter) getPresenter()).init(CallList.getInstance());
        this.conferenceParticipantList.requestFocus();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void refreshCall(DialerCall dialerCall) {
        this.conferenceParticipantListAdapter.refreshCall(dialerCall);
    }

    public void update(List<DialerCall> list, boolean z) {
        if (this.conferenceParticipantListAdapter == null) {
            this.conferenceParticipantListAdapter = new ConferenceParticipantListAdapter(this.conferenceParticipantList, this.contactPhotoManager);
            this.conferenceParticipantList.setAdapter(this.conferenceParticipantListAdapter);
        }
        this.conferenceParticipantListAdapter.updateParticipants(list, z);
    }
}
