package com.android.dialer.interactions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.p000v4.app.ActivityCompat;
import android.support.p002v7.appcompat.R$style;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.contacts.common.Collapser$Collapsible;
import com.android.contacts.common.MoreContactUtils;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.util.TransactionSafeActivity;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PhoneNumberInteraction implements Loader.OnLoadCompleteListener<Cursor> {
    public static final String[] PHONE_NUMBER_PROJECTION = {"_id", "data1", "is_super_primary", "account_type", "data_set", "data2", "data3", "mimetype", "contact_id"};
    private final CallSpecificAppData callSpecificAppData;
    private long contactId = -1;
    private final Context context;
    private final int interactionType;
    private boolean isVideoCall;
    private CursorLoader loader;

    public interface DisambigDialogDismissedListener {
        void onDisambigDialogDismissed();
    }

    public interface InteractionErrorListener {
        void interactionError(int i);
    }

    public static class PhoneDisambiguationDialogFragment extends DialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
        private CallSpecificAppData callSpecificAppData;
        private int interactionType;
        private boolean isVideoCall;
        private List<PhoneItem> phoneList;
        private ListAdapter phonesAdapter;

        public void onClick(DialogInterface dialogInterface, int i) {
            Activity activity = getActivity();
            if (activity != null) {
                AlertDialog alertDialog = (AlertDialog) dialogInterface;
                if (this.phoneList.size() <= i || i < 0) {
                    dialogInterface.dismiss();
                    return;
                }
                PhoneItem phoneItem = this.phoneList.get(i);
                if (((CheckBox) alertDialog.findViewById(R.id.setPrimary)).isChecked()) {
                    if (this.callSpecificAppData.getCallInitiationType() == CallInitiationType$Type.SPEED_DIAL) {
                        ((LoggingBindingsStub) Logger.get(getContext())).logInteraction(InteractionEvent$Type.SPEED_DIAL_SET_DEFAULT_NUMBER_FOR_AMBIGUOUS_CONTACT);
                    }
                    activity.startService(ContactUpdateService.createSetSuperPrimaryIntent(activity, phoneItem.f28id));
                }
                PhoneNumberInteraction.performAction(activity, phoneItem.phoneNumber, this.interactionType, this.isVideoCall, this.callSpecificAppData);
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            Activity activity = getActivity();
            Assert.checkState(activity instanceof DisambigDialogDismissedListener);
            this.phoneList = getArguments().getParcelableArrayList("phoneList");
            this.interactionType = getArguments().getInt("interactionType");
            this.isVideoCall = getArguments().getBoolean("is_video_call");
            this.callSpecificAppData = R$style.getCallSpecificAppData(getArguments());
            this.phonesAdapter = new PhoneItemAdapter(activity, this.phoneList, this.interactionType);
            return new AlertDialog.Builder(activity).setAdapter(this.phonesAdapter, this).setTitle(this.interactionType == 2 ? R.string.sms_disambig_title : R.string.call_disambig_title).setView(activity.getLayoutInflater().inflate(R.layout.set_primary_checkbox, (ViewGroup) null)).create();
        }

        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            Activity activity = getActivity();
            if (activity != null) {
                ((DisambigDialogDismissedListener) activity).onDisambigDialogDismissed();
            }
        }
    }

    static class PhoneItem implements Parcelable, Collapser$Collapsible<PhoneItem> {
        public static final Parcelable.Creator<PhoneItem> CREATOR = new Parcelable.Creator<PhoneItem>() {
            public Object createFromParcel(Parcel parcel) {
                return new PhoneItem(parcel, (C04891) null);
            }

            public Object[] newArray(int i) {
                return new PhoneItem[i];
            }
        };
        String accountType;
        String dataSet;

        /* renamed from: id */
        long f28id;
        String label;
        String mimeType;
        String phoneNumber;
        long type;

        private PhoneItem() {
        }

        public void collapseWith(PhoneItem phoneItem) {
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return this.phoneNumber;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f28id);
            parcel.writeString(this.phoneNumber);
            parcel.writeString(this.accountType);
            parcel.writeString(this.dataSet);
            parcel.writeLong(this.type);
            parcel.writeString(this.label);
            parcel.writeString(this.mimeType);
        }

        /* synthetic */ PhoneItem(C04891 r1) {
        }

        public boolean shouldCollapseWith(PhoneItem phoneItem, Context context) {
            return MoreContactUtils.shouldCollapse("vnd.android.cursor.item/phone_v2", this.phoneNumber, "vnd.android.cursor.item/phone_v2", phoneItem.phoneNumber);
        }

        /* synthetic */ PhoneItem(Parcel parcel, C04891 r4) {
            this.f28id = parcel.readLong();
            this.phoneNumber = parcel.readString();
            this.accountType = parcel.readString();
            this.dataSet = parcel.readString();
            this.type = parcel.readLong();
            this.label = parcel.readString();
            this.mimeType = parcel.readString();
        }
    }

    private static class PhoneItemAdapter extends ArrayAdapter<PhoneItem> {
        private final int interactionType;

        PhoneItemAdapter(Context context, List<PhoneItem> list, int i) {
            super(context, R.layout.phone_disambig_item, 16908309, list);
            this.interactionType = i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2;
            View view2 = super.getView(i, view, viewGroup);
            PhoneItem phoneItem = (PhoneItem) getItem(i);
            Assert.isNotNull(phoneItem, "Null item at position: %d", Integer.valueOf(i));
            TextView textView = (TextView) view2.findViewById(16908308);
            Integer valueOf = Integer.valueOf((int) phoneItem.type);
            CharSequence charSequence = phoneItem.label;
            int i3 = this.interactionType;
            Context context = getContext();
            Objects.requireNonNull(context);
            if (!R$style.isCustomPhoneType(valueOf)) {
                if (i3 == 2) {
                    i2 = R$style.getSmsLabelResourceId(valueOf);
                } else {
                    i2 = R$style.getPhoneLabelResourceId(valueOf);
                    if (i3 != 1) {
                        LogUtil.m8e("ContactDisplayUtils.getLabelForCallOrSms", "un-recognized interaction type: " + i3 + ". Defaulting to ContactDisplayUtils.INTERACTION_CALL.", new Object[0]);
                    }
                }
                charSequence = context.getResources().getText(i2);
            } else if (charSequence == null) {
                charSequence = "";
            }
            textView.setText(charSequence);
            return view2;
        }
    }

    private PhoneNumberInteraction(Context context2, int i, boolean z, CallSpecificAppData callSpecificAppData2) {
        this.context = context2;
        this.interactionType = i;
        this.callSpecificAppData = callSpecificAppData2;
        this.isVideoCall = z;
        Assert.checkArgument(context2 instanceof InteractionErrorListener);
        Assert.checkArgument(context2 instanceof DisambigDialogDismissedListener);
        Assert.checkArgument(context2 instanceof ActivityCompat.OnRequestPermissionsResultCallback);
    }

    private void interactionError(int i) {
        ((InteractionErrorListener) this.context).interactionError(i);
    }

    /* access modifiers changed from: private */
    public static void performAction(Context context2, String str, int i, boolean z, CallSpecificAppData callSpecificAppData2) {
        Intent intent;
        if (i != 2) {
            intent = PreCall.getIntent(context2, new CallIntentBuilder(str, callSpecificAppData2).setIsVideoCall(z).setAllowAssistedDial(callSpecificAppData2.getAllowAssistedDialing()));
        } else {
            intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("sms", str, (String) null));
        }
        DialerUtils.startActivityWithErrorToast(context2, intent, R.string.activity_not_available);
    }

    private void showDisambiguationDialog(ArrayList<PhoneItem> arrayList) {
        Activity activity = (Activity) this.context;
        if (activity.isFinishing()) {
            LogUtil.m9i("PhoneNumberInteraction.showDisambiguationDialog", "activity finishing", new Object[0]);
        } else if (activity.isDestroyed()) {
            LogUtil.m9i("PhoneNumberInteraction.showDisambiguationDialog", "activity destroyed", new Object[0]);
        } else {
            try {
                FragmentManager fragmentManager = activity.getFragmentManager();
                int i = this.interactionType;
                boolean z = this.isVideoCall;
                CallSpecificAppData callSpecificAppData2 = this.callSpecificAppData;
                PhoneDisambiguationDialogFragment phoneDisambiguationDialogFragment = new PhoneDisambiguationDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("phoneList", arrayList);
                bundle.putInt("interactionType", i);
                bundle.putBoolean("is_video_call", z);
                R$style.putCallSpecificAppData(bundle, callSpecificAppData2);
                phoneDisambiguationDialogFragment.setArguments(bundle);
                phoneDisambiguationDialogFragment.show(fragmentManager, "PhoneNumberInteraction");
            } catch (IllegalStateException e) {
                LogUtil.m7e("PhoneNumberInteraction.showDisambiguationDialog", "caught exception", (Throwable) e);
            }
        }
    }

    public static void startInteractionForPhoneCall(TransactionSafeActivity transactionSafeActivity, Uri uri, boolean z, CallSpecificAppData callSpecificAppData2) {
        PhoneNumberInteraction phoneNumberInteraction = new PhoneNumberInteraction(transactionSafeActivity, 1, z, callSpecificAppData2);
        if (!PermissionsUtil.hasPhonePermissions(phoneNumberInteraction.context)) {
            LogUtil.m9i("PhoneNumberInteraction.startInteraction", "Need phone permission: CALL_PHONE", new Object[0]);
            ActivityCompat.requestPermissions((Activity) phoneNumberInteraction.context, new String[]{"android.permission.CALL_PHONE"}, 2);
            return;
        }
        String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(phoneNumberInteraction.context, PermissionsUtil.allContactsGroupPermissionsUsedInDialer);
        if (permissionsCurrentlyDenied.length > 0) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Need contact permissions: ");
            outline13.append(Arrays.toString(permissionsCurrentlyDenied));
            LogUtil.m9i("PhoneNumberInteraction.startInteraction", outline13.toString(), new Object[0]);
            ActivityCompat.requestPermissions((Activity) phoneNumberInteraction.context, permissionsCurrentlyDenied, 1);
            return;
        }
        CursorLoader cursorLoader = phoneNumberInteraction.loader;
        if (cursorLoader != null) {
            cursorLoader.reset();
        }
        String uri2 = uri.toString();
        if (uri2.startsWith(ContactsContract.Contacts.CONTENT_URI.toString())) {
            if (!uri2.endsWith("data")) {
                uri = Uri.withAppendedPath(uri, "data");
            }
        } else if (!uri2.startsWith(ContactsContract.Data.CONTENT_URI.toString())) {
            throw new UnsupportedOperationException(GeneratedOutlineSupport.outline7("Input Uri must be contact Uri or data Uri (input: \"", uri, "\")"));
        }
        phoneNumberInteraction.loader = new CursorLoader(phoneNumberInteraction.context, uri, PHONE_NUMBER_PROJECTION, "mimetype IN ('vnd.android.cursor.item/phone_v2', 'vnd.android.cursor.item/sip_address') AND data1 NOT NULL", (String[]) null, (String) null);
        phoneNumberInteraction.loader.registerListener(0, phoneNumberInteraction);
        phoneNumberInteraction.loader.startLoading();
    }

    /* access modifiers changed from: package-private */
    public CursorLoader getLoader() {
        return this.loader;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e A[Catch:{ all -> 0x0126 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d A[SYNTHETIC, Splitter:B:16:0x003d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLoadComplete(android.content.Loader<android.database.Cursor> r20, android.database.Cursor r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            r2 = 0
            java.lang.String r3 = "PhoneNumberInteraction.onLoadComplete"
            if (r1 != 0) goto L_0x0015
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r2 = "null cursor"
            com.android.dialer.common.LogUtil.m9i(r3, r2, r1)
            r1 = 3
            r0.interactionError(r1)
            return
        L_0x0015:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0126 }
            r4.<init>()     // Catch:{ all -> 0x0126 }
            android.content.Context r5 = r0.context     // Catch:{ all -> 0x0126 }
            boolean r6 = r5 instanceof com.android.dialer.util.TransactionSafeActivity     // Catch:{ all -> 0x0126 }
            if (r6 == 0) goto L_0x002b
            com.android.dialer.util.TransactionSafeActivity r5 = (com.android.dialer.util.TransactionSafeActivity) r5     // Catch:{ all -> 0x0126 }
            boolean r5 = r5.isSafeToCommitTransactions()     // Catch:{ all -> 0x0126 }
            if (r5 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r5 = r2
            goto L_0x002c
        L_0x002b:
            r5 = 1
        L_0x002c:
            if (r5 != 0) goto L_0x003d
            java.lang.String r4 = "not safe to commit transaction"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0126 }
            com.android.dialer.common.LogUtil.m9i(r3, r4, r2)     // Catch:{ all -> 0x0126 }
            r2 = 4
            r0.interactionError(r2)     // Catch:{ all -> 0x0126 }
            r21.close()
            return
        L_0x003d:
            boolean r3 = r21.moveToFirst()     // Catch:{ all -> 0x0126 }
            if (r3 == 0) goto L_0x011e
            java.lang.String r3 = "contact_id"
            int r3 = r1.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x0126 }
            java.lang.String r5 = "is_super_primary"
            int r5 = r1.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x0126 }
            java.lang.String r6 = "data1"
            int r6 = r1.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x0126 }
            java.lang.String r8 = "_id"
            int r8 = r1.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x0126 }
            java.lang.String r9 = "account_type"
            int r9 = r1.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x0126 }
            java.lang.String r10 = "data_set"
            int r10 = r1.getColumnIndexOrThrow(r10)     // Catch:{ all -> 0x0126 }
            java.lang.String r11 = "data2"
            int r11 = r1.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x0126 }
            java.lang.String r12 = "data3"
            int r12 = r1.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x0126 }
            java.lang.String r13 = "mimetype"
            int r13 = r1.getColumnIndexOrThrow(r13)     // Catch:{ all -> 0x0126 }
            r14 = 0
            r16 = r8
            r15 = r14
        L_0x007d:
            long r7 = r0.contactId     // Catch:{ all -> 0x0126 }
            r17 = -1
            int r7 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r7 != 0) goto L_0x008b
            long r7 = r1.getLong(r3)     // Catch:{ all -> 0x0126 }
            r0.contactId = r7     // Catch:{ all -> 0x0126 }
        L_0x008b:
            int r7 = r1.getInt(r5)     // Catch:{ all -> 0x0126 }
            if (r7 == 0) goto L_0x0096
            java.lang.String r7 = r1.getString(r6)     // Catch:{ all -> 0x0126 }
            r15 = r7
        L_0x0096:
            com.android.dialer.interactions.PhoneNumberInteraction$PhoneItem r7 = new com.android.dialer.interactions.PhoneNumberInteraction$PhoneItem     // Catch:{ all -> 0x0126 }
            r7.<init>(r14)     // Catch:{ all -> 0x0126 }
            r17 = r3
            r8 = r16
            long r2 = r1.getLong(r8)     // Catch:{ all -> 0x0126 }
            r7.f28id = r2     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = r1.getString(r6)     // Catch:{ all -> 0x0126 }
            r7.phoneNumber = r2     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = r1.getString(r9)     // Catch:{ all -> 0x0126 }
            r7.accountType = r2     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = r1.getString(r10)     // Catch:{ all -> 0x0126 }
            r7.dataSet = r2     // Catch:{ all -> 0x0126 }
            int r2 = r1.getInt(r11)     // Catch:{ all -> 0x0126 }
            long r2 = (long) r2     // Catch:{ all -> 0x0126 }
            r7.type = r2     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = r1.getString(r12)     // Catch:{ all -> 0x0126 }
            r7.label = r2     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = r1.getString(r13)     // Catch:{ all -> 0x0126 }
            r7.mimeType = r2     // Catch:{ all -> 0x0126 }
            r4.add(r7)     // Catch:{ all -> 0x0126 }
            boolean r2 = r21.moveToNext()     // Catch:{ all -> 0x0126 }
            if (r2 != 0) goto L_0x0117
            if (r15 == 0) goto L_0x00e4
            android.content.Context r2 = r0.context     // Catch:{ all -> 0x0126 }
            int r3 = r0.interactionType     // Catch:{ all -> 0x0126 }
            boolean r4 = r0.isVideoCall     // Catch:{ all -> 0x0126 }
            com.android.dialer.callintent.CallSpecificAppData r0 = r0.callSpecificAppData     // Catch:{ all -> 0x0126 }
            performAction(r2, r15, r3, r4, r0)     // Catch:{ all -> 0x0126 }
            r21.close()
            return
        L_0x00e4:
            android.content.Context r2 = r0.context     // Catch:{ all -> 0x0126 }
            android.support.p002v7.appcompat.R$style.collapseList(r4, r2)     // Catch:{ all -> 0x0126 }
            int r2 = r4.size()     // Catch:{ all -> 0x0126 }
            if (r2 != 0) goto L_0x00f4
            r2 = 2
            r0.interactionError(r2)     // Catch:{ all -> 0x0126 }
            goto L_0x0113
        L_0x00f4:
            int r2 = r4.size()     // Catch:{ all -> 0x0126 }
            r3 = 1
            if (r2 != r3) goto L_0x0110
            r2 = 0
            java.lang.Object r2 = r4.get(r2)     // Catch:{ all -> 0x0126 }
            com.android.dialer.interactions.PhoneNumberInteraction$PhoneItem r2 = (com.android.dialer.interactions.PhoneNumberInteraction.PhoneItem) r2     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = r2.phoneNumber     // Catch:{ all -> 0x0126 }
            android.content.Context r3 = r0.context     // Catch:{ all -> 0x0126 }
            int r4 = r0.interactionType     // Catch:{ all -> 0x0126 }
            boolean r5 = r0.isVideoCall     // Catch:{ all -> 0x0126 }
            com.android.dialer.callintent.CallSpecificAppData r0 = r0.callSpecificAppData     // Catch:{ all -> 0x0126 }
            performAction(r3, r2, r4, r5, r0)     // Catch:{ all -> 0x0126 }
            goto L_0x0113
        L_0x0110:
            r0.showDisambiguationDialog(r4)     // Catch:{ all -> 0x0126 }
        L_0x0113:
            r21.close()
            return
        L_0x0117:
            r16 = r8
            r3 = r17
            r2 = 0
            goto L_0x007d
        L_0x011e:
            r2 = 1
            r0.interactionError(r2)     // Catch:{ all -> 0x0126 }
            r21.close()
            return
        L_0x0126:
            r0 = move-exception
            r21.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.interactions.PhoneNumberInteraction.onLoadComplete(android.content.Loader, android.database.Cursor):void");
    }
}
