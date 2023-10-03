package com.android.settings.applications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;

public class ConfirmConvertToFbe extends SettingsPreferenceFragment {
    public int getMetricsCategory() {
        return 403;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.confirm_convert_fbe, (ViewGroup) null);
        ((Button) inflate.findViewById(C1715R.C1718id.button_confirm_convert_fbe)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.FACTORY_RESET");
                intent.addFlags(268435456);
                intent.setPackage("android");
                intent.putExtra("android.intent.extra.REASON", "convert_fbe");
                ConfirmConvertToFbe.this.getActivity().sendBroadcast(intent);
            }
        });
        return inflate;
    }
}
