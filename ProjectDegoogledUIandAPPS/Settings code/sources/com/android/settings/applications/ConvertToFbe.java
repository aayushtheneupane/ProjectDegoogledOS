package com.android.settings.applications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.havoc.config.center.C1715R;

public class ConvertToFbe extends InstrumentedFragment {
    public int getMetricsCategory() {
        return 402;
    }

    private boolean runKeyguardConfirmation(int i) {
        return new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(i, getActivity().getResources().getText(C1715R.string.convert_to_file_encryption));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(C1715R.string.convert_to_file_encryption);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.convert_fbe, (ViewGroup) null);
        ((Button) inflate.findViewById(C1715R.C1718id.button_convert_fbe)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ConvertToFbe.this.lambda$onCreateView$0$ConvertToFbe(view);
            }
        });
        return inflate;
    }

    public /* synthetic */ void lambda$onCreateView$0$ConvertToFbe(View view) {
        if (!runKeyguardConfirmation(55)) {
            convert();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 55 && i2 == -1) {
            convert();
        }
    }

    private void convert() {
        new SubSettingLauncher(getContext()).setDestination(ConfirmConvertToFbe.class.getName()).setTitleRes(C1715R.string.convert_to_file_encryption).setSourceMetricsCategory(getMetricsCategory()).launch();
    }
}
