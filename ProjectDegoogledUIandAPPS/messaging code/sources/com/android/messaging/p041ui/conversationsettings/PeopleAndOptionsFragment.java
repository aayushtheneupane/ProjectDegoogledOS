package com.android.messaging.p041ui.conversationsettings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.p016v4.media.session.C0107q;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0905Q;
import com.android.messaging.datamodel.data.C0906S;
import com.android.messaging.datamodel.data.C0907T;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.C1033B;
import com.android.messaging.util.C1424b;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversationsettings.PeopleAndOptionsFragment */
public class PeopleAndOptionsFragment extends Fragment implements C0905Q, C1245h {

    /* renamed from: ab */
    private C1240c f1959ab;

    /* renamed from: bb */
    private C1243f f1960bb;

    /* renamed from: db */
    private List f1961db;
    /* access modifiers changed from: private */
    public final C0783c mBinding = C0784d.m1315q(this);
    private ListView mListView;

    /* renamed from: e */
    public void mo7600e(String str) {
        C1424b.m3592ia(getView() == null);
        C1424b.m3594t(str);
        this.mBinding.mo5930b(C0947h.get().mo6596a(str, (Context) getActivity(), (C0905Q) this));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((C0906S) this.mBinding.getData()).mo6368a(getLoaderManager(), (C0784d) this.mBinding);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.people_and_options_fragment, viewGroup, false);
        this.mListView = (ListView) inflate.findViewById(16908298);
        this.f1960bb = new C1243f(this, getActivity());
        this.f1959ab = new C1240c(this, (C1239b) null);
        C1033B b = new C1033B(getActivity());
        b.mo6888a(new C1241d(this, this.f1959ab, R.string.general_settings_title, false));
        b.mo6888a(new C1241d(this, this.f1960bb, R.string.participant_list_title, true));
        this.mListView.setAdapter(b);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBinding.unbind();
    }

    /* renamed from: a */
    public void mo6365a(C0906S s, Cursor cursor) {
        boolean z = true;
        if (!(cursor == null || cursor.getCount() == 1)) {
            z = false;
        }
        C1424b.m3592ia(z);
        this.mBinding.mo5929a(s);
        this.f1959ab.swapCursor(cursor);
    }

    /* renamed from: a */
    public void mo6366a(C0906S s, List list) {
        this.mBinding.mo5929a(s);
        C1243f fVar = this.f1960bb;
        fVar.clear();
        fVar.addAll(list);
        fVar.notifyDataSetChanged();
        this.f1961db = list;
        this.f1959ab.mo7609a(list.size() == 1 ? (ParticipantData) list.get(0) : null);
    }

    /* renamed from: a */
    public void mo7599a(C0907T t) {
        int itemId = t.getItemId();
        if (itemId == 0) {
            ArrayList arrayList = new ArrayList();
            for (ParticipantData P : this.f1961db) {
                arrayList.add(P.mo6330P(true));
            }
            C0107q.m126a((Context) getActivity(), "conversation_group", (int) R.string.notification_channel_messages_title);
            C0107q.m127a((Context) getActivity(), ((C0906S) this.mBinding.getData()).mo6367Ue(), String.join(", ", arrayList), 3, "conversation_group");
            Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getContext().getPackageName());
            startActivity(intent);
        } else if (itemId == 1) {
            if (t.mo6373Ye().isBlocked()) {
                ((C0906S) this.mBinding.getData()).mo6369a((C0784d) this.mBinding, false);
                return;
            }
            Resources resources = getResources();
            Activity activity = getActivity();
            new AlertDialog.Builder(activity, R.style.BugleThemeDialog).setTitle(resources.getString(R.string.block_confirmation_title, new Object[]{t.mo6373Ye().mo6344mh()})).setMessage(resources.getString(R.string.block_confirmation_message)).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, new C1239b(this, activity)).create().show();
        }
    }
}
