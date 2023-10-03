package com.android.messaging.p041ui.conversationsettings;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.ParticipantData;

/* renamed from: com.android.messaging.ui.conversationsettings.c */
class C1240c extends BaseAdapter {
    final /* synthetic */ PeopleAndOptionsFragment this$0;

    /* renamed from: wl */
    private Cursor f1963wl;

    /* renamed from: xl */
    private ParticipantData f1964xl;

    /* synthetic */ C1240c(PeopleAndOptionsFragment peopleAndOptionsFragment, C1239b bVar) {
        this.this$0 = peopleAndOptionsFragment;
    }

    /* renamed from: a */
    public void mo7609a(ParticipantData participantData) {
        if (this.f1964xl != participantData) {
            this.f1964xl = participantData;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        int i = this.f1964xl == null ? 1 : 2;
        if (this.f1963wl == null) {
            return 0;
        }
        return i;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PeopleOptionsItemView peopleOptionsItemView;
        if (view == null || !(view instanceof PeopleOptionsItemView)) {
            peopleOptionsItemView = (PeopleOptionsItemView) ((LayoutInflater) this.this$0.getActivity().getSystemService("layout_inflater")).inflate(R.layout.people_options_item_view, viewGroup, false);
        } else {
            peopleOptionsItemView = (PeopleOptionsItemView) view;
        }
        this.f1963wl.moveToFirst();
        peopleOptionsItemView.mo7604a(this.f1963wl, i, this.f1964xl, this.this$0);
        return peopleOptionsItemView;
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.f1963wl;
        if (cursor != cursor2) {
            this.f1963wl = cursor;
            notifyDataSetChanged();
        }
        return cursor2;
    }
}
