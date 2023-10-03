package com.android.settings.network;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkUtils;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.utils.AnnotationSpan;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.havoc.config.center.C1715R;
import java.util.HashMap;
import java.util.Map;

public class PrivateDnsModeDialogPreference extends CustomDialogPreferenceCompat implements DialogInterface.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher {
    static final String HOSTNAME_KEY = "private_dns_specifier";
    static final String MODE_KEY = "private_dns_mode";
    private static final Map<String, Integer> PRIVATE_DNS_MAP = new HashMap();
    EditText mEditText;
    String mMode;
    RadioGroup mRadioGroup;
    private final AnnotationSpan.LinkInfo mUrlLinkInfo = new AnnotationSpan.LinkInfo("url", C0950x9e20a738.INSTANCE);

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    static {
        PRIVATE_DNS_MAP.put("off", Integer.valueOf(C1715R.C1718id.private_dns_mode_off));
        PRIVATE_DNS_MAP.put("opportunistic", Integer.valueOf(C1715R.C1718id.private_dns_mode_opportunistic));
        PRIVATE_DNS_MAP.put("hostname", Integer.valueOf(C1715R.C1718id.private_dns_mode_provider));
    }

    public static String getModeFromSettings(ContentResolver contentResolver) {
        String string = Settings.Global.getString(contentResolver, MODE_KEY);
        if (!PRIVATE_DNS_MAP.containsKey(string)) {
            string = Settings.Global.getString(contentResolver, "private_dns_default_mode");
        }
        return PRIVATE_DNS_MAP.containsKey(string) ? string : "opportunistic";
    }

    public static String getHostnameFromSettings(ContentResolver contentResolver) {
        return Settings.Global.getString(contentResolver, HOSTNAME_KEY);
    }

    public PrivateDnsModeDialogPreference(Context context) {
        super(context);
        initialize();
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize();
    }

    static /* synthetic */ void lambda$new$0(View view) {
        Context context = view.getContext();
        Intent helpIntent = HelpUtils.getHelpIntent(context, context.getString(C1715R.string.help_uri_private_dns), context.getClass().getName());
        if (helpIntent != null) {
            try {
                view.startActivityForResult(helpIntent, 0);
            } catch (ActivityNotFoundException unused) {
                Log.w("PrivateDnsModeDialog", "Activity was not found for intent, " + helpIntent.toString());
            }
        }
    }

    private void initialize() {
        setWidgetLayoutResource(C1715R.layout.restricted_icon);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (isDisabledByAdmin()) {
            preferenceViewHolder.itemView.setEnabled(true);
        }
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.restricted_icon);
        if (findViewById != null) {
            findViewById.setVisibility(isDisabledByAdmin() ? 0 : 8);
        }
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        Context context = getContext();
        ContentResolver contentResolver = context.getContentResolver();
        this.mMode = getModeFromSettings(context.getContentResolver());
        this.mEditText = (EditText) view.findViewById(C1715R.C1718id.private_dns_mode_provider_hostname);
        this.mEditText.addTextChangedListener(this);
        this.mEditText.setText(getHostnameFromSettings(contentResolver));
        this.mRadioGroup = (RadioGroup) view.findViewById(C1715R.C1718id.private_dns_radio_group);
        this.mRadioGroup.setOnCheckedChangeListener(this);
        this.mRadioGroup.check(PRIVATE_DNS_MAP.getOrDefault(this.mMode, Integer.valueOf(C1715R.C1718id.private_dns_mode_opportunistic)).intValue());
        TextView textView = (TextView) view.findViewById(C1715R.C1718id.private_dns_help_info);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        AnnotationSpan.LinkInfo linkInfo = new AnnotationSpan.LinkInfo(context, "url", HelpUtils.getHelpIntent(context, context.getString(C1715R.string.help_uri_private_dns), context.getClass().getName()));
        if (linkInfo.isActionable()) {
            textView.setText(AnnotationSpan.linkify(context.getText(C1715R.string.private_dns_help_message), linkInfo));
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            Context context = getContext();
            if (this.mMode.equals("hostname")) {
                Settings.Global.putString(context.getContentResolver(), HOSTNAME_KEY, this.mEditText.getText().toString());
            }
            FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 1249, this.mMode);
            Settings.Global.putString(context.getContentResolver(), MODE_KEY, this.mMode);
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case C1715R.C1718id.private_dns_mode_off /*2131362746*/:
                this.mMode = "off";
                break;
            case C1715R.C1718id.private_dns_mode_opportunistic /*2131362747*/:
                this.mMode = "opportunistic";
                break;
            case C1715R.C1718id.private_dns_mode_provider /*2131362748*/:
                this.mMode = "hostname";
                break;
        }
        updateDialogInfo();
    }

    public void afterTextChanged(Editable editable) {
        updateDialogInfo();
    }

    public void performClick() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = getEnforcedAdmin();
        if (enforcedAdmin == null) {
            super.performClick();
        } else {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), enforcedAdmin);
        }
    }

    private RestrictedLockUtils.EnforcedAdmin getEnforcedAdmin() {
        return RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "disallow_config_private_dns", UserHandle.myUserId());
    }

    private boolean isDisabledByAdmin() {
        return getEnforcedAdmin() != null;
    }

    private Button getSaveButton() {
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog == null) {
            return null;
        }
        return alertDialog.getButton(-1);
    }

    private void updateDialogInfo() {
        boolean equals = "hostname".equals(this.mMode);
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.setEnabled(equals);
        }
        Button saveButton = getSaveButton();
        if (saveButton != null) {
            saveButton.setEnabled(equals ? NetworkUtils.isWeaklyValidatedHostname(this.mEditText.getText().toString()) : true);
        }
    }
}
