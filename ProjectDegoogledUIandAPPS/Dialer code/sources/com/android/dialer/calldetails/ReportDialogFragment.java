package com.android.dialer.calldetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.p000v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.phonenumbercache.CachedNumberLookupService$CachedContactInfo;
import com.android.dialer.phonenumbercache.PhoneNumberCache;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;

public class ReportDialogFragment extends DialogFragment {
    private TextView name;
    private String number;
    private TextView numberView;

    public static /* synthetic */ Pair lambda$s1WwXvZx0jzcQ2by3wG7pFzItw0(ReportDialogFragment reportDialogFragment, Context context) {
        reportDialogFragment.reportCallerId(context);
        throw null;
    }

    /* access modifiers changed from: private */
    public void onReportCallerId(Pair<Context, Boolean> pair) {
        Context context = (Context) pair.first;
        if (((Boolean) pair.second).booleanValue()) {
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.CALLER_ID_REPORTED);
            Toast.makeText(context, R.string.report_caller_id_toast, 0).show();
            return;
        }
        ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.CALLER_ID_REPORT_FAILED);
        Toast.makeText(context, R.string.report_caller_id_failed, 0).show();
    }

    private Pair<Context, Boolean> reportCallerId(Context context) {
        throw null;
    }

    /* access modifiers changed from: private */
    public void setCachedContactInfo(CachedNumberLookupService$CachedContactInfo cachedNumberLookupService$CachedContactInfo) {
        if (cachedNumberLookupService$CachedContactInfo != null) {
            this.name.setText(cachedNumberLookupService$CachedContactInfo.getContactInfo().name);
            this.numberView.setText(cachedNumberLookupService$CachedContactInfo.getContactInfo().number);
            return;
        }
        this.numberView.setText(this.number);
        this.name.setVisibility(8);
    }

    public /* synthetic */ CachedNumberLookupService$CachedContactInfo lambda$lookupContactInfo$3$ReportDialogFragment(String str) throws Throwable {
        getContext();
        throw null;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$ReportDialogFragment(DialogInterface dialogInterface, int i) {
        $$Lambda$ReportDialogFragment$s1WwXvZx0jzcQ2by3wG7pFzItw0 r6 = new DialerExecutor.Worker() {
            public final Object doInBackground(Object obj) {
                return ReportDialogFragment.lambda$s1WwXvZx0jzcQ2by3wG7pFzItw0(ReportDialogFragment.this, (Context) obj);
            }
        };
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "report_caller_id", r6).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                ReportDialogFragment.this.onReportCallerId((Pair) obj);
            }
        }).build().executeParallel(getActivity());
        dialogInterface.dismiss();
    }

    public /* synthetic */ void lambda$onCreateDialog$2$ReportDialogFragment(AlertDialog alertDialog, DialogInterface dialogInterface) {
        int colorPrimary = ((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorPrimary();
        alertDialog.getButton(-1).setTextColor(colorPrimary);
        alertDialog.getButton(-2).setTextColor(colorPrimary);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.number = getArguments().getString("number");
        PhoneNumberCache.get(getContext()).getCachedNumberLookupService();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.caller_id_report_dialog, (ViewGroup) null, false);
        this.name = (TextView) inflate.findViewById(R.id.name);
        this.numberView = (TextView) inflate.findViewById(R.id.number);
        String str = this.number;
        $$Lambda$ReportDialogFragment$ykrB3l82TYpj9ifjR7dxUm5zmKo r1 = new DialerExecutor.Worker() {
            public final Object doInBackground(Object obj) {
                return ReportDialogFragment.this.lambda$lookupContactInfo$3$ReportDialogFragment((String) obj);
            }
        };
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "lookup_contact_info", r1).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                ReportDialogFragment.this.setCachedContactInfo((CachedNumberLookupService$CachedContactInfo) obj);
            }
        }).build().executeParallel(str);
        AlertDialog create = new AlertDialog.Builder(getActivity()).setTitle(R.string.report_caller_id_dialog_title).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ReportDialogFragment.this.lambda$onCreateDialog$0$ReportDialogFragment(dialogInterface, i);
            }
        }).setNegativeButton(17039360, $$Lambda$ReportDialogFragment$GM93kfhRiEv0WKYmPkYrSjHo7y8.INSTANCE).setView(inflate).create();
        create.setOnShowListener(new DialogInterface.OnShowListener(create) {
            private final /* synthetic */ AlertDialog f$1;

            {
                this.f$1 = r2;
            }

            public final void onShow(DialogInterface dialogInterface) {
                ReportDialogFragment.this.lambda$onCreateDialog$2$ReportDialogFragment(this.f$1, dialogInterface);
            }
        });
        return create;
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage((Message) null);
        }
        super.onDestroyView();
    }
}
