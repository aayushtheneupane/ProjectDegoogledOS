package com.android.messaging.p041ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.action.ReceiveSmsMessageAction;
import com.android.messaging.util.C1424b;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.ClassZeroActivity */
public class ClassZeroActivity extends Activity {

    /* renamed from: Zb */
    private ContentValues f1607Zb = null;
    /* access modifiers changed from: private */

    /* renamed from: _b */
    public boolean f1608_b = false;

    /* renamed from: ac */
    private long f1609ac = 0;

    /* renamed from: bc */
    private ArrayList f1610bc = null;

    /* renamed from: cc */
    private final DialogInterface.OnClickListener f1611cc = new C1389x(this);
    private final DialogInterface.OnClickListener mCancelListener = new C1387w(this);
    /* access modifiers changed from: private */
    public AlertDialog mDialog = null;
    private final Handler mHandler = new C1385v(this);

    /* renamed from: b */
    static /* synthetic */ void m2523b(ClassZeroActivity classZeroActivity) {
        classZeroActivity.f1607Zb.put("read", Integer.valueOf(classZeroActivity.f1608_b ? 1 : 0));
        new ReceiveSmsMessageAction(classZeroActivity.f1607Zb).start();
    }

    /* renamed from: c */
    private boolean m2525c(Intent intent) {
        ContentValues contentValues = (ContentValues) intent.getParcelableExtra("message_values");
        if (!TextUtils.isEmpty(contentValues.getAsString("body"))) {
            this.f1610bc.add(contentValues);
            C0944e.m2079Vd();
            return true;
        } else if (this.f1610bc.size() != 0) {
            return false;
        } else {
            finish();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (this.f1610bc == null) {
            this.f1610bc = new ArrayList();
        }
        if (m2525c(getIntent())) {
            C1424b.m3592ia(this.f1610bc.size() == 1);
            if (this.f1610bc.size() == 1) {
                m2522b((ContentValues) this.f1610bc.get(0));
            }
            if (bundle != null) {
                this.f1609ac = bundle.getLong("timer_fire", this.f1609ac);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        m2525c(intent);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("timer_fire", this.f1609ac);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = this.f1609ac;
        if (j <= uptimeMillis) {
            this.mHandler.sendEmptyMessage(1);
        } else {
            this.mHandler.sendEmptyMessageAtTime(1, j);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(1);
    }

    /* renamed from: b */
    private void m2522b(ContentValues contentValues) {
        this.f1607Zb = contentValues;
        this.mDialog = new AlertDialog.Builder(this).setMessage(contentValues.getAsString("body")).setPositiveButton(R.string.save, this.f1611cc).setNegativeButton(17039360, this.mCancelListener).setTitle(R.string.class_0_message_activity).setCancelable(false).show();
        this.f1609ac = SystemClock.uptimeMillis() + 300000;
    }

    /* renamed from: c */
    static /* synthetic */ void m2524c(ClassZeroActivity classZeroActivity) {
        if (classZeroActivity.f1610bc.size() > 0) {
            classZeroActivity.f1610bc.remove(0);
        }
        if (classZeroActivity.f1610bc.size() == 0) {
            classZeroActivity.finish();
        } else {
            classZeroActivity.m2522b((ContentValues) classZeroActivity.f1610bc.get(0));
        }
    }
}
