package com.android.dialer.postcall;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.DialerToolbar;
import com.android.dialer.widget.MessageFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;

public class PostCallActivity extends AppCompatActivity implements MessageFragment.Listener {
    private boolean useRcs;

    public static Intent newIntent(Context context, String str, boolean z) {
        Assert.isNotNull(context);
        Intent intent = new Intent(context, PostCallActivity.class);
        Assert.isNotNull(str);
        intent.putExtra("phone_number", str);
        intent.putExtra("rcs_post_call", z);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.post_call_activity);
        ((DialerToolbar) findViewById(R.id.toolbar)).setTitle((int) R.string.post_call_message);
        this.useRcs = getIntent().getBooleanExtra("rcs_post_call", false);
        LogUtil.m9i("PostCallActivity.onCreate", "useRcs: %b", Boolean.valueOf(this.useRcs));
        int integer = this.useRcs ? getResources().getInteger(R.integer.post_call_char_limit) : -1;
        String[] strArr = {getString(R.string.post_call_message_1), getString(R.string.post_call_message_2), getString(R.string.post_call_message_3)};
        MessageFragment.Builder builder = MessageFragment.builder();
        builder.setCharLimit(integer);
        builder.showSendIcon();
        builder.setMessages(strArr);
        MessageFragment build = builder.build();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.message_container, build);
        beginTransaction.commit();
    }

    public void onMessageFragmentAfterTextChange(String str) {
    }

    public void onMessageFragmentSendMessage(String str) {
        String stringExtra = getIntent().getStringExtra("phone_number");
        Assert.isNotNull(stringExtra);
        String str2 = stringExtra;
        getIntent().putExtra("message", str);
        if (this.useRcs) {
            LogUtil.m9i("PostCallActivity.onMessageFragmentSendMessage", "sending post call Rcs.", new Object[0]);
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(this).getEnrichedCallManager()).sendPostCallNote(str2, str);
            PostCall.onMessageSent(this, str2);
            finish();
        } else if (PermissionsUtil.hasPermission(this, "android.permission.SEND_SMS")) {
            LogUtil.m9i("PostCallActivity.sendMessage", "Sending post call SMS.", new Object[0]);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendMultipartTextMessage(str2, (String) null, smsManager.divideMessage(str), (ArrayList) null, (ArrayList) null);
            PostCall.onMessageSent(this, str2);
            finish();
        } else if (PermissionsUtil.isFirstRequest(this, "android.permission.SEND_SMS") || shouldShowRequestPermissionRationale("android.permission.SEND_SMS")) {
            LogUtil.m9i("PostCallActivity.sendMessage", "Requesting SMS_SEND permission.", new Object[0]);
            requestPermissions(new String[]{"android.permission.SEND_SMS"}, 1);
        } else {
            LogUtil.m9i("PostCallActivity.sendMessage", "Permission permanently denied, sending to settings.", new Object[0]);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addFlags(268435456);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("package:");
            outline13.append(getPackageName());
            intent.setData(Uri.parse(outline13.toString()));
            startActivity(intent);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr.length > 0 && strArr[0].equals("android.permission.SEND_SMS")) {
            PermissionsUtil.permissionRequested(this, strArr[0]);
        }
        if (i == 1 && iArr.length > 0 && iArr[0] == 0) {
            onMessageFragmentSendMessage(getIntent().getStringExtra("message"));
        }
    }
}
