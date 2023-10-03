package com.android.messaging.p041ui.debug;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.core.app.NotificationCompat;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.debug.g */
public class C1254g extends DialogFragment {

    /* renamed from: W */
    private String[] f1978W;
    /* access modifiers changed from: private */
    public String mAction;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        if (r3 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005f, code lost:
        if (r3 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0063, code lost:
        if (r3 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A[SYNTHETIC, Splitter:B:25:0x0056] */
    /* renamed from: Fa */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m3179Fa(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "smsdump-"
            boolean r0 = r12.startsWith(r0)
            r1 = -1
            java.lang.String r2 = "MessagingApp"
            if (r0 == 0) goto L_0x0087
            r0 = 0
            java.io.File r3 = com.android.messaging.util.C1410N.m3551e(r12, r0)
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0062, StreamCorruptedException -> 0x005e, IOException -> 0x005a, all -> 0x0052 }
            r5.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0062, StreamCorruptedException -> 0x005e, IOException -> 0x005a, all -> 0x0052 }
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ FileNotFoundException -> 0x0062, StreamCorruptedException -> 0x005e, IOException -> 0x005a, all -> 0x0052 }
            r3.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0062, StreamCorruptedException -> 0x005e, IOException -> 0x005a, all -> 0x0052 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            if (r5 <= 0) goto L_0x0026
            java.lang.String r5 = r3.readUTF()     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            goto L_0x0027
        L_0x0026:
            r5 = r4
        L_0x0027:
            int r6 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            android.telephony.SmsMessage[] r7 = new android.telephony.SmsMessage[r6]     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            r8 = r0
        L_0x002e:
            if (r8 >= r6) goto L_0x004b
            int r9 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            byte[] r10 = new byte[r9]     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            r3.read(r10, r0, r9)     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            if (r5 != 0) goto L_0x0042
            android.telephony.SmsMessage r9 = android.telephony.SmsMessage.createFromPdu(r10)     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            r7[r8] = r9     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            goto L_0x0048
        L_0x0042:
            android.telephony.SmsMessage r9 = android.telephony.SmsMessage.createFromPdu(r10, r5)     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
            r7[r8] = r9     // Catch:{ FileNotFoundException -> 0x0063, StreamCorruptedException -> 0x005f, IOException -> 0x005b, all -> 0x0050 }
        L_0x0048:
            int r8 = r8 + 1
            goto L_0x002e
        L_0x004b:
            r3.close()     // Catch:{ IOException -> 0x004e }
        L_0x004e:
            r4 = r7
            goto L_0x0068
        L_0x0050:
            r11 = move-exception
            goto L_0x0054
        L_0x0052:
            r11 = move-exception
            r3 = r4
        L_0x0054:
            if (r3 == 0) goto L_0x0059
            r3.close()     // Catch:{ IOException -> 0x0059 }
        L_0x0059:
            throw r11
        L_0x005a:
            r3 = r4
        L_0x005b:
            if (r3 == 0) goto L_0x0068
            goto L_0x0065
        L_0x005e:
            r3 = r4
        L_0x005f:
            if (r3 == 0) goto L_0x0068
            goto L_0x0065
        L_0x0062:
            r3 = r4
        L_0x0063:
            if (r3 == 0) goto L_0x0068
        L_0x0065:
            r3.close()     // Catch:{ IOException -> 0x0068 }
        L_0x0068:
            if (r4 == 0) goto L_0x0072
            android.app.Activity r11 = r11.getActivity()
            com.android.messaging.receiver.SmsReceiver.m2320a(r11, r1, r0, r4)
            goto L_0x00c7
        L_0x0072:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "receiveFromDumpFile: invalid sms dump file "
            r11.append(r0)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            com.android.messaging.util.C1430e.m3622e(r2, r11)
            goto L_0x00c7
        L_0x0087:
            java.lang.String r11 = "mmsdump-"
            boolean r11 = r12.startsWith(r11)
            if (r11 == 0) goto L_0x00b3
            byte[] r11 = com.android.messaging.sms.C1029y.m2451sa(r12)
            if (r11 == 0) goto L_0x009e
            com.android.messaging.datamodel.action.ReceiveMmsMessageAction r12 = new com.android.messaging.datamodel.action.ReceiveMmsMessageAction
            r12.<init>((int) r1, (byte[]) r11)
            r12.start()
            goto L_0x00c7
        L_0x009e:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "receiveFromDumpFile: invalid mms dump file "
            r11.append(r0)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            com.android.messaging.util.C1430e.m3622e(r2, r11)
            goto L_0x00c7
        L_0x00b3:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "receiveFromDumpFile: invalid dump file name "
            r11.append(r0)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            com.android.messaging.util.C1430e.m3622e(r2, r11)
        L_0x00c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.debug.C1254g.m3179Fa(java.lang.String):void");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        this.f1978W = (String[]) arguments.getSerializable("dump_files");
        this.mAction = arguments.getString("action");
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.debug_sms_mms_from_dump_file_dialog, (ViewGroup) null);
        ((ListView) inflate.findViewById(R.id.dump_file_list)).setAdapter(new C1253f(this, getActivity(), this.f1978W));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Resources resources = getResources();
        if ("load".equals(this.mAction)) {
            builder.setTitle(resources.getString(R.string.load_sms_mms_from_dump_file_dialog_title));
        } else if (NotificationCompat.CATEGORY_EMAIL.equals(this.mAction)) {
            builder.setTitle(resources.getString(R.string.email_sms_mms_from_dump_file_dialog_title));
        }
        builder.setView(inflate);
        return builder.create();
    }
}
