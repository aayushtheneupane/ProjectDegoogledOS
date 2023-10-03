package com.android.settings;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.havoc.config.center.C1715R;

public class BandMode extends Activity {
    /* access modifiers changed from: private */
    public static final String[] BAND_NAMES = {"Automatic", "Europe", "United States", "Japan", "Australia", "Australia 2", "Cellular 800", "PCS", "Class 3 (JTACS)", "Class 4 (Korea-PCS)", "Class 5", "Class 6 (IMT2000)", "Class 7 (700Mhz-Upper)", "Class 8 (1800Mhz-Upper)", "Class 9 (900Mhz)", "Class 10 (800Mhz-Secondary)", "Class 11 (Europe PAMR 400Mhz)", "Class 15 (US-AWS)", "Class 16 (US-2500Mhz)"};
    private ListView mBandList;
    private ArrayAdapter mBandListAdapter;
    private AdapterView.OnItemClickListener mBandSelectionHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            BandMode.this.getWindow().setFeatureInt(5, -1);
            BandListItem unused = BandMode.this.mTargetBand = (BandListItem) adapterView.getAdapter().getItem(i);
            BandMode.this.mPhone.setBandMode(BandMode.this.mTargetBand.getBand(), BandMode.this.mHandler.obtainMessage(200));
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 100) {
                BandMode.this.bandListLoaded((AsyncResult) message.obj);
            } else if (i == 200) {
                AsyncResult asyncResult = (AsyncResult) message.obj;
                BandMode.this.getWindow().setFeatureInt(5, -2);
                if (!BandMode.this.isFinishing()) {
                    BandMode.this.displayBandSelectionResult(asyncResult.exception);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Phone mPhone = null;
    private DialogInterface mProgressPanel;
    /* access modifiers changed from: private */
    public BandListItem mTargetBand = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(5);
        setContentView(C1715R.layout.band_mode);
        this.mPhone = PhoneFactory.getDefaultPhone();
        this.mBandList = (ListView) findViewById(C1715R.C1718id.band);
        this.mBandListAdapter = new ArrayAdapter(this, 17367043);
        this.mBandList.setAdapter(this.mBandListAdapter);
        this.mBandList.setOnItemClickListener(this.mBandSelectionHandler);
        loadBandList();
    }

    private static class BandListItem {
        private int mBandMode = 0;

        public BandListItem(int i) {
            this.mBandMode = i;
        }

        public int getBand() {
            return this.mBandMode;
        }

        public String toString() {
            if (this.mBandMode < BandMode.BAND_NAMES.length) {
                return BandMode.BAND_NAMES[this.mBandMode];
            }
            return "Band mode " + this.mBandMode;
        }
    }

    private void loadBandList() {
        String string = getString(C1715R.string.band_mode_loading);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) string);
        this.mProgressPanel = builder.show();
        this.mPhone.queryAvailableBandMode(this.mHandler.obtainMessage(100));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045 A[LOOP:1: B:19:0x0045->B:21:0x0049, LOOP_START, PHI: r1 
      PHI: (r1v1 int) = (r1v0 int), (r1v2 int) binds: [B:18:0x0043, B:21:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bandListLoaded(android.os.AsyncResult r7) {
        /*
            r6 = this;
            android.content.DialogInterface r0 = r6.mProgressPanel
            if (r0 == 0) goto L_0x0007
            r0.dismiss()
        L_0x0007:
            r6.clearList()
            java.lang.Object r7 = r7.result
            r0 = 1
            r1 = 0
            if (r7 == 0) goto L_0x0042
            int[] r7 = (int[]) r7
            int r2 = r7.length
            if (r2 != 0) goto L_0x001d
            java.lang.String r6 = "phone"
            java.lang.String r7 = "No Supported Band Modes"
            android.util.Log.wtf(r6, r7)
            return
        L_0x001d:
            r2 = r7[r1]
            if (r2 <= 0) goto L_0x0042
            android.widget.ArrayAdapter r3 = r6.mBandListAdapter
            com.android.settings.BandMode$BandListItem r4 = new com.android.settings.BandMode$BandListItem
            r4.<init>(r1)
            r3.add(r4)
            r3 = r0
        L_0x002c:
            if (r3 > r2) goto L_0x0043
            r4 = r7[r3]
            if (r4 != 0) goto L_0x0033
            goto L_0x003f
        L_0x0033:
            com.android.settings.BandMode$BandListItem r4 = new com.android.settings.BandMode$BandListItem
            r5 = r7[r3]
            r4.<init>(r5)
            android.widget.ArrayAdapter r5 = r6.mBandListAdapter
            r5.add(r4)
        L_0x003f:
            int r3 = r3 + 1
            goto L_0x002c
        L_0x0042:
            r0 = r1
        L_0x0043:
            if (r0 != 0) goto L_0x0056
        L_0x0045:
            r7 = 19
            if (r1 >= r7) goto L_0x0056
            com.android.settings.BandMode$BandListItem r7 = new com.android.settings.BandMode$BandListItem
            r7.<init>(r1)
            android.widget.ArrayAdapter r0 = r6.mBandListAdapter
            r0.add(r7)
            int r1 = r1 + 1
            goto L_0x0045
        L_0x0056:
            android.widget.ListView r6 = r6.mBandList
            r6.requestFocus()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.BandMode.bandListLoaded(android.os.AsyncResult):void");
    }

    /* access modifiers changed from: private */
    public void displayBandSelectionResult(Throwable th) {
        String str;
        String str2 = getString(C1715R.string.band_mode_set) + " [" + this.mTargetBand.toString() + "] ";
        if (th != null) {
            str = str2 + getString(C1715R.string.band_mode_failed);
        } else {
            str = str2 + getString(C1715R.string.band_mode_succeeded);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        this.mProgressPanel = builder.show();
    }

    private void clearList() {
        while (this.mBandListAdapter.getCount() > 0) {
            ArrayAdapter arrayAdapter = this.mBandListAdapter;
            arrayAdapter.remove(arrayAdapter.getItem(0));
        }
    }
}
