package com.android.dialer.dialpadview;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.contacts.common.database.NoNullCursorAsyncQueryHandler;
import com.android.contacts.common.widget.SelectPhoneAccountDialogFragment;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.telecom.TelecomUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Arrays;
import java.util.Locale;

public class SpecialCharSequenceMgr {
    static final String MMI_IMEI_DISPLAY = "*#06#";
    /* access modifiers changed from: private */
    public static QueryHandler previousAdnQueryHandler;

    public static class HandleAdnEntryAccountSelectedCallback extends SelectPhoneAccountDialogFragment.SelectPhoneAccountListener {
        private final Context context;
        private final SimContactQueryCookie cookie;
        private final QueryHandler queryHandler;

        public HandleAdnEntryAccountSelectedCallback(Context context2, QueryHandler queryHandler2, SimContactQueryCookie simContactQueryCookie) {
            this.context = context2;
            this.queryHandler = queryHandler2;
            this.cookie = simContactQueryCookie;
        }

        public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
            SpecialCharSequenceMgr.handleAdnQuery(this.queryHandler, this.cookie, TelecomUtil.getAdnUriForPhoneAccount(this.context, phoneAccountHandle));
        }
    }

    public static class HandleMmiAccountSelectedCallback extends SelectPhoneAccountDialogFragment.SelectPhoneAccountListener {
        private final Context context;
        private final String input;

        public HandleMmiAccountSelectedCallback(Context context2, String str) {
            this.context = context2.getApplicationContext();
            this.input = str;
        }

        public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
            TelecomUtil.handleMmi(this.context, this.input, phoneAccountHandle);
        }
    }

    private static class QueryHandler extends NoNullCursorAsyncQueryHandler {
        private boolean canceled;

        public QueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        public void cancel() {
            this.canceled = true;
            cancelOperation(-1);
        }

        /* access modifiers changed from: protected */
        public void onNotNullableQueryComplete(int i, Object obj, Cursor cursor) {
            try {
                QueryHandler unused = SpecialCharSequenceMgr.previousAdnQueryHandler = null;
                if (!this.canceled) {
                    SimContactQueryCookie simContactQueryCookie = (SimContactQueryCookie) obj;
                    simContactQueryCookie.progressDialog.dismiss();
                    EditText textField = simContactQueryCookie.getTextField();
                    if (!(cursor == null || textField == null || !cursor.moveToPosition(simContactQueryCookie.contactNum))) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        textField.getText().replace(0, 0, cursor.getString(cursor.getColumnIndexOrThrow("number")));
                        Context context = simContactQueryCookie.progressDialog.getContext();
                        Toast.makeText(context, R$style.getTtsSpannedPhoneNumber(context.getResources(), R.string.menu_callNumber, string), 0).show();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    private static class SimContactQueryCookie implements DialogInterface.OnCancelListener {
        public int contactNum;
        private QueryHandler handler;
        public ProgressDialog progressDialog;
        private EditText textField;
        private int token;

        public SimContactQueryCookie(int i, QueryHandler queryHandler, int i2) {
            this.contactNum = i;
            this.handler = queryHandler;
            this.token = i2;
        }

        public synchronized EditText getTextField() {
            return this.textField;
        }

        public synchronized void onCancel(DialogInterface dialogInterface) {
            if (this.progressDialog != null) {
                this.progressDialog.dismiss();
            }
            this.textField = null;
            this.handler.cancelOperation(this.token);
        }

        public synchronized void setTextField(EditText editText) {
            this.textField = editText;
        }
    }

    private static void addDeviceIdRow(ViewGroup viewGroup, String str, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str)) {
            ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_deviceid, viewGroup, false);
            viewGroup.addView(viewGroup2);
            final String substring = str.length() == 15 ? str.substring(0, 14) : str;
            if (substring.length() != 14 || !z) {
                viewGroup2.findViewById(R.id.deviceid_hex_label).setVisibility(8);
                ((TextView) viewGroup2.findViewById(R.id.deviceid_hex)).setText(str);
            } else {
                ((TextView) viewGroup2.findViewById(R.id.deviceid_hex)).setText(substring);
                TextView textView = (TextView) viewGroup2.findViewById(R.id.deviceid_dec);
                String str2 = "";
                String substring2 = substring.substring(0, 8);
                String substring3 = substring.substring(8);
                try {
                    String format = String.format(Locale.US, "%010d", new Object[]{Long.valueOf(Long.parseLong(substring2, 16))});
                    try {
                        String format2 = String.format(Locale.US, "%08d", new Object[]{Long.valueOf(Long.parseLong(substring3, 16))});
                        StringBuilder sb = new StringBuilder(22);
                        sb.append(format, 0, 5);
                        sb.append(' ');
                        sb.append(format, 5, format.length());
                        sb.append(' ');
                        sb.append(format2, 0, 4);
                        sb.append(' ');
                        sb.append(format2, 4, format2.length());
                        str2 = sb.toString();
                    } catch (NumberFormatException e) {
                        LogUtil.m7e("SpecialCharSequenceMgr.getDecimalFromHex", "unable to parse hex", (Throwable) e);
                    }
                } catch (NumberFormatException e2) {
                    LogUtil.m7e("SpecialCharSequenceMgr.getDecimalFromHex", "unable to parse hex", (Throwable) e2);
                }
                textView.setText(str2);
                viewGroup2.findViewById(R.id.deviceid_dec_label).setVisibility(0);
            }
            final ImageView imageView = (ImageView) viewGroup2.findViewById(R.id.deviceid_barcode);
            if (z2) {
                imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        Bitmap bitmap;
                        imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        String str = substring;
                        int width = imageView.getWidth();
                        int height = imageView.getHeight();
                        try {
                            BitMatrix encode = new MultiFormatWriter().encode(Uri.encode(str), BarcodeFormat.CODE_128, width, 1);
                            bitmap = Bitmap.createBitmap(encode.getWidth(), height, Bitmap.Config.RGB_565);
                            for (int i = 0; i < encode.getWidth(); i++) {
                                int[] iArr = new int[height];
                                Arrays.fill(iArr, encode.get(i, 0) ? -16777216 : -1);
                                bitmap.setPixels(iArr, 0, 1, i, 0, 1, height);
                            }
                        } catch (WriterException e) {
                            LogUtil.m7e("SpecialCharSequenceMgr.generateBarcode", "error generating barcode", (Throwable) e);
                            bitmap = null;
                        }
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public static void cleanup() {
        Assert.isMainThread();
        QueryHandler queryHandler = previousAdnQueryHandler;
        if (queryHandler != null) {
            queryHandler.cancel();
            previousAdnQueryHandler = null;
        }
    }

    /* access modifiers changed from: private */
    public static void handleAdnQuery(QueryHandler queryHandler, SimContactQueryCookie simContactQueryCookie, Uri uri) {
        if (queryHandler == null || simContactQueryCookie == null || uri == null) {
            LogUtil.m10w("SpecialCharSequenceMgr.handleAdnQuery", "queryAdn parameters incorrect", new Object[0]);
            return;
        }
        simContactQueryCookie.progressDialog.show();
        queryHandler.startQuery(-1, simContactQueryCookie, uri, new String[]{"number"}, (String) null, (String[]) null, (String) null);
        QueryHandler queryHandler2 = previousAdnQueryHandler;
        if (queryHandler2 != null) {
            queryHandler2.cancel();
        }
        previousAdnQueryHandler = queryHandler;
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x025b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x025c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean handleChars(android.content.Context r15, java.lang.String r16, android.widget.EditText r17) {
        /*
            r1 = r15
            java.lang.String r2 = android.telephony.PhoneNumberUtils.stripSeparators(r16)
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = com.android.dialer.util.PermissionsUtil.hasPermission(r15, r0)
            r3 = -1
            java.lang.String r4 = "phone"
            r5 = 0
            r6 = 0
            r7 = 1
            if (r0 != 0) goto L_0x0016
        L_0x0013:
            r0 = r6
            goto L_0x00ae
        L_0x0016:
            java.lang.Object r0 = r15.getSystemService(r4)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L_0x0013
            java.lang.String r8 = "*#06#"
            boolean r8 = r2.equals(r8)
            if (r8 == 0) goto L_0x0013
            int r8 = r0.getPhoneType()
            if (r8 != r7) goto L_0x0030
            r8 = 2131820985(0x7f1101b9, float:1.92747E38)
            goto L_0x0033
        L_0x0030:
            r8 = 2131821094(0x7f110226, float:1.9274921E38)
        L_0x0033:
            android.view.LayoutInflater r9 = android.view.LayoutInflater.from(r15)
            r10 = 2131492949(0x7f0c0055, float:1.8609364E38)
            android.view.View r9 = r9.inflate(r10, r5)
            r10 = 2131296494(0x7f0900ee, float:1.8210906E38)
            android.view.View r10 = r9.findViewById(r10)
            android.view.ViewGroup r10 = (android.view.ViewGroup) r10
            int r11 = com.android.dialer.compat.telephony.TelephonyManagerCompat.getPhoneCount(r0)
            r12 = 2131034133(0x7f050015, float:1.7678775E38)
            if (r11 <= r7) goto L_0x006f
            r11 = r6
        L_0x0051:
            int r13 = r0.getPhoneCount()
            if (r11 >= r13) goto L_0x0089
            java.lang.String r13 = r0.getDeviceId(r11)
            boolean r14 = android.text.TextUtils.isEmpty(r13)
            if (r14 != 0) goto L_0x006c
            android.content.res.Resources r14 = r15.getResources()
            boolean r14 = r14.getBoolean(r12)
            addDeviceIdRow(r10, r13, r14, r6)
        L_0x006c:
            int r11 = r11 + 1
            goto L_0x0051
        L_0x006f:
            java.lang.String r0 = r0.getDeviceId()
            android.content.res.Resources r11 = r15.getResources()
            boolean r11 = r11.getBoolean(r12)
            android.content.res.Resources r12 = r15.getResources()
            r13 = 2131034132(0x7f050014, float:1.7678773E38)
            boolean r12 = r12.getBoolean(r13)
            addDeviceIdRow(r10, r0, r11, r12)
        L_0x0089:
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r0.<init>(r15)
            android.app.AlertDialog$Builder r0 = r0.setTitle(r8)
            android.app.AlertDialog$Builder r0 = r0.setView(r9)
            r8 = 17039370(0x104000a, float:2.42446E-38)
            android.app.AlertDialog$Builder r0 = r0.setPositiveButton(r8, r5)
            android.app.AlertDialog$Builder r0 = r0.setCancelable(r6)
            android.app.AlertDialog r0 = r0.show()
            android.view.Window r0 = r0.getWindow()
            r8 = -2
            r0.setLayout(r3, r8)
            r0 = r7
        L_0x00ae:
            if (r0 != 0) goto L_0x025d
            java.lang.String r0 = "*#07#"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00d5
            java.lang.Object[] r0 = new java.lang.Object[r6]
            java.lang.String r8 = "SpecialCharSequenceMgr.handleRegulatoryInfoDisplay"
            java.lang.String r9 = "sending intent to settings app"
            com.android.dialer.common.LogUtil.m9i(r8, r9, r0)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r9 = "android.settings.SHOW_REGULATORY_INFO"
            r0.<init>(r9)
            r15.startActivity(r0)     // Catch:{ ActivityNotFoundException -> 0x00cc }
            goto L_0x00d3
        L_0x00cc:
            r0 = move-exception
            r9 = r0
            java.lang.String r0 = "startActivity() failed: "
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r8, (java.lang.String) r0, (java.lang.Throwable) r9)
        L_0x00d3:
            r0 = r7
            goto L_0x00d6
        L_0x00d5:
            r0 = r6
        L_0x00d6:
            if (r0 != 0) goto L_0x025d
            java.lang.String r0 = "**04"
            boolean r0 = r2.startsWith(r0)
            java.lang.String r8 = "tag_select_acct_fragment"
            java.lang.String r9 = "tel"
            java.lang.String r10 = "#"
            if (r0 != 0) goto L_0x00ee
            java.lang.String r0 = "**05"
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x012d
        L_0x00ee:
            boolean r0 = r2.endsWith(r10)
            if (r0 == 0) goto L_0x012d
            java.util.List r0 = com.android.dialer.telecom.TelecomUtil.getSubscriptionPhoneAccounts(r15)
            android.telecom.PhoneAccountHandle r11 = com.android.dialer.telecom.TelecomUtil.getDefaultOutgoingPhoneAccount(r15, r9)
            boolean r11 = r0.contains(r11)
            int r12 = r0.size()
            if (r12 <= r7) goto L_0x0128
            if (r11 == 0) goto L_0x0109
            goto L_0x0128
        L_0x0109:
            com.android.dialer.dialpadview.SpecialCharSequenceMgr$HandleMmiAccountSelectedCallback r11 = new com.android.dialer.dialpadview.SpecialCharSequenceMgr$HandleMmiAccountSelectedCallback
            r11.<init>(r15, r2)
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$Builder r0 = android.support.p002v7.appcompat.R$style.builderWithAccounts(r0)
            com.google.protobuf.GeneratedMessageLite r0 = r0.build()
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions r0 = (com.android.contacts.common.widget.SelectPhoneAccountDialogOptions) r0
            com.android.contacts.common.widget.SelectPhoneAccountDialogFragment r0 = com.android.contacts.common.widget.SelectPhoneAccountDialogFragment.newInstance(r0, r11)
            r11 = r1
            android.app.Activity r11 = (android.app.Activity) r11
            android.app.FragmentManager r11 = r11.getFragmentManager()
            r0.show(r11, r8)
            r0 = r7
            goto L_0x012e
        L_0x0128:
            boolean r0 = com.android.dialer.telecom.TelecomUtil.handleMmi(r15, r2, r5)
            goto L_0x012e
        L_0x012d:
            r0 = r6
        L_0x012e:
            if (r0 != 0) goto L_0x025d
            java.lang.Object r0 = r15.getSystemService(r4)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            r4 = 4
            if (r0 == 0) goto L_0x01f2
            int r0 = r0.getPhoneType()
            if (r0 == r7) goto L_0x0141
            goto L_0x01f2
        L_0x0141:
            java.lang.String r0 = "keyguard"
            java.lang.Object r0 = r15.getSystemService(r0)
            android.app.KeyguardManager r0 = (android.app.KeyguardManager) r0
            boolean r0 = r0.inKeyguardRestrictedInputMode()
            if (r0 == 0) goto L_0x0151
            goto L_0x01f2
        L_0x0151:
            int r0 = r2.length()
            if (r0 <= r7) goto L_0x01f2
            r11 = 5
            if (r0 >= r11) goto L_0x01f2
            boolean r11 = r2.endsWith(r10)
            if (r11 == 0) goto L_0x01f2
            int r0 = r0 - r7
            java.lang.String r0 = r2.substring(r6, r0)     // Catch:{ NumberFormatException -> 0x01f2 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x01f2 }
            com.android.dialer.dialpadview.SpecialCharSequenceMgr$QueryHandler r11 = new com.android.dialer.dialpadview.SpecialCharSequenceMgr$QueryHandler     // Catch:{ NumberFormatException -> 0x01f2 }
            android.content.ContentResolver r12 = r15.getContentResolver()     // Catch:{ NumberFormatException -> 0x01f2 }
            r11.<init>(r12)     // Catch:{ NumberFormatException -> 0x01f2 }
            com.android.dialer.dialpadview.SpecialCharSequenceMgr$SimContactQueryCookie r12 = new com.android.dialer.dialpadview.SpecialCharSequenceMgr$SimContactQueryCookie     // Catch:{ NumberFormatException -> 0x01f2 }
            int r0 = r0 - r7
            r12.<init>(r0, r11, r3)     // Catch:{ NumberFormatException -> 0x01f2 }
            r12.contactNum = r0     // Catch:{ NumberFormatException -> 0x01f2 }
            r3 = r17
            r12.setTextField(r3)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = new android.app.ProgressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.<init>(r15)     // Catch:{ NumberFormatException -> 0x01f2 }
            r12.progressDialog = r0     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = r12.progressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            r3 = 2131821305(0x7f1102f9, float:1.927535E38)
            r0.setTitle(r3)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = r12.progressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            r3 = 2131821304(0x7f1102f8, float:1.9275347E38)
            java.lang.CharSequence r3 = r15.getText(r3)     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.setMessage(r3)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = r12.progressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.setIndeterminate(r7)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = r12.progressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.setCancelable(r7)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = r12.progressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.setOnCancelListener(r12)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.ProgressDialog r0 = r12.progressDialog     // Catch:{ NumberFormatException -> 0x01f2 }
            android.view.Window r0 = r0.getWindow()     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.addFlags(r4)     // Catch:{ NumberFormatException -> 0x01f2 }
            java.util.List r0 = com.android.dialer.telecom.TelecomUtil.getSubscriptionPhoneAccounts(r15)     // Catch:{ NumberFormatException -> 0x01f2 }
            android.content.Context r3 = r15.getApplicationContext()     // Catch:{ NumberFormatException -> 0x01f2 }
            android.telecom.PhoneAccountHandle r9 = com.android.dialer.telecom.TelecomUtil.getDefaultOutgoingPhoneAccount(r3, r9)     // Catch:{ NumberFormatException -> 0x01f2 }
            boolean r9 = r0.contains(r9)     // Catch:{ NumberFormatException -> 0x01f2 }
            int r13 = r0.size()     // Catch:{ NumberFormatException -> 0x01f2 }
            if (r13 <= r7) goto L_0x01e9
            if (r9 == 0) goto L_0x01cb
            goto L_0x01e9
        L_0x01cb:
            com.android.dialer.dialpadview.SpecialCharSequenceMgr$HandleAdnEntryAccountSelectedCallback r5 = new com.android.dialer.dialpadview.SpecialCharSequenceMgr$HandleAdnEntryAccountSelectedCallback     // Catch:{ NumberFormatException -> 0x01f2 }
            r5.<init>(r3, r11, r12)     // Catch:{ NumberFormatException -> 0x01f2 }
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$Builder r0 = android.support.p002v7.appcompat.R$style.builderWithAccounts(r0)     // Catch:{ NumberFormatException -> 0x01f2 }
            com.google.protobuf.GeneratedMessageLite r0 = r0.build()     // Catch:{ NumberFormatException -> 0x01f2 }
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions r0 = (com.android.contacts.common.widget.SelectPhoneAccountDialogOptions) r0     // Catch:{ NumberFormatException -> 0x01f2 }
            com.android.contacts.common.widget.SelectPhoneAccountDialogFragment r0 = com.android.contacts.common.widget.SelectPhoneAccountDialogFragment.newInstance(r0, r5)     // Catch:{ NumberFormatException -> 0x01f2 }
            r3 = r1
            android.app.Activity r3 = (android.app.Activity) r3     // Catch:{ NumberFormatException -> 0x01f2 }
            android.app.FragmentManager r3 = r3.getFragmentManager()     // Catch:{ NumberFormatException -> 0x01f2 }
            r0.show(r3, r8)     // Catch:{ NumberFormatException -> 0x01f2 }
            goto L_0x01f0
        L_0x01e9:
            android.net.Uri r0 = com.android.dialer.telecom.TelecomUtil.getAdnUriForPhoneAccount(r3, r5)     // Catch:{ NumberFormatException -> 0x01f2 }
            handleAdnQuery(r11, r12, r0)     // Catch:{ NumberFormatException -> 0x01f2 }
        L_0x01f0:
            r0 = r7
            goto L_0x01f3
        L_0x01f2:
            r0 = r6
        L_0x01f3:
            if (r0 != 0) goto L_0x025d
            boolean r0 = com.android.dialer.oem.TranssionUtils.isTranssionSecretCode(r2)
            if (r0 == 0) goto L_0x022c
            boolean r0 = com.android.dialer.oem.TranssionUtils.isTranssionSecretCode(r2)
            com.android.dialer.common.Assert.checkState(r0)
            int r0 = r2.length()
            r3 = 3
            if (r0 <= r3) goto L_0x0219
            java.lang.String r0 = "*#"
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x0219
            boolean r0 = r2.endsWith(r10)
            if (r0 == 0) goto L_0x0219
            r0 = r7
            goto L_0x021a
        L_0x0219:
            r0 = r6
        L_0x021a:
            com.android.dialer.common.Assert.checkArgument(r0)
            r0 = 2
            int r3 = r2.length()
            int r3 = r3 - r7
            java.lang.String r0 = r2.substring(r0, r3)
            com.android.dialer.compat.telephony.TelephonyManagerCompat.handleSecretCode(r15, r0)
        L_0x022a:
            r0 = r7
            goto L_0x0252
        L_0x022c:
            int r0 = r2.length()
            r3 = 8
            if (r0 <= r3) goto L_0x0251
            java.lang.String r0 = "*#*#"
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x0251
            java.lang.String r0 = "#*#*"
            boolean r0 = r2.endsWith(r0)
            if (r0 == 0) goto L_0x0251
            int r0 = r2.length()
            int r0 = r0 - r4
            java.lang.String r0 = r2.substring(r4, r0)
            com.android.dialer.compat.telephony.TelephonyManagerCompat.handleSecretCode(r15, r0)
            goto L_0x022a
        L_0x0251:
            r0 = r6
        L_0x0252:
            if (r0 == 0) goto L_0x0255
            goto L_0x025d
        L_0x0255:
            boolean r0 = com.android.dialer.oem.MotorolaUtils.handleSpecialCharSequence(r15, r16)
            if (r0 == 0) goto L_0x025c
            return r7
        L_0x025c:
            return r6
        L_0x025d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.dialpadview.SpecialCharSequenceMgr.handleChars(android.content.Context, java.lang.String, android.widget.EditText):boolean");
    }
}
