package com.android.messaging.p041ui.conversationsettings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.p041ui.C1117ca;
import com.android.messaging.p041ui.PersonItemView;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.conversationsettings.f */
class C1243f extends ArrayAdapter {
    final /* synthetic */ PeopleAndOptionsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1243f(PeopleAndOptionsFragment peopleAndOptionsFragment, Context context) {
        super(context, R.layout.people_list_item_view, new ArrayList());
        this.this$0 = peopleAndOptionsFragment;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PersonItemView personItemView;
        ParticipantData participantData = (ParticipantData) getItem(i);
        if (view == null || !(view instanceof PersonItemView)) {
            personItemView = (PersonItemView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.people_list_item_view, viewGroup, false);
        } else {
            personItemView = (PersonItemView) view;
        }
        personItemView.mo7066d(C0947h.get().mo6605b(participantData));
        personItemView.mo7065a((C1117ca) new C1242e(this, personItemView));
        return personItemView;
    }
}
