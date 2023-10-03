package com.android.messaging.p041ui.debug;

import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import com.android.messaging.R;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1468pa;
import com.android.messaging.util.C1474sa;
import java.util.List;

/* renamed from: com.android.messaging.ui.debug.DebugMmsConfigFragment */
public class DebugMmsConfigFragment extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Integer[] numArr;
        View inflate = layoutInflater.inflate(R.layout.mms_config_debug_fragment, viewGroup, false);
        ListView listView = (ListView) inflate.findViewById(16908298);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.sim_selector);
        if (!C1464na.m3759Zj()) {
            numArr = new Integer[]{-1};
        } else {
            List activeSubscriptionInfoList = ((C1468pa) C1474sa.getDefault().mo8231mk()).getActiveSubscriptionInfoList();
            if (activeSubscriptionInfoList == null) {
                numArr = new Integer[0];
            } else {
                Integer[] numArr2 = new Integer[activeSubscriptionInfoList.size()];
                for (int i = 0; i < activeSubscriptionInfoList.size(); i++) {
                    numArr2[i] = Integer.valueOf(((SubscriptionInfo) activeSubscriptionInfoList.get(i)).getSubscriptionId());
                }
                numArr = numArr2;
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367048, numArr);
        arrayAdapter.setDropDownViewResource(17367049);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new C1248a(this, listView, numArr, inflate));
        return inflate;
    }
}
