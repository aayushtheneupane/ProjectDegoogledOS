package com.android.contacts.common.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.precall.PreCall;
import com.android.dialer.util.CallUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

public class CallSubjectDialog extends Activity {
    /* access modifiers changed from: private */
    public int mAnimationDuration;
    private View.OnClickListener mBackgroundListener = new View.OnClickListener() {
        public void onClick(View view) {
            CallSubjectDialog.this.finish();
        }
    };
    private View mBackgroundView;
    private final View.OnClickListener mCallSubjectClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (CallSubjectDialog.this.mSubjectList.getVisibility() == 0) {
                CallSubjectDialog.access$300(CallSubjectDialog.this, false);
            }
        }
    };
    /* access modifiers changed from: private */
    public EditText mCallSubjectView;
    private TextView mCharacterLimitView;
    private QuickContactBadge mContactPhoto;
    private int mContactType;
    private Uri mContactUri;
    /* access modifiers changed from: private */
    public View mDialogView;
    private String mDisplayNumber;
    private View mHistoryButton;
    private final View.OnClickListener mHistoryOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            CallSubjectDialog callSubjectDialog = CallSubjectDialog.this;
            callSubjectDialog.hideSoftKeyboard(callSubjectDialog, callSubjectDialog.mCallSubjectView);
            CallSubjectDialog callSubjectDialog2 = CallSubjectDialog.this;
            CallSubjectDialog.access$300(callSubjectDialog2, callSubjectDialog2.mSubjectList.getVisibility() == 8);
        }
    };
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CallSubjectDialog.this.mCallSubjectView.setText((CharSequence) CallSubjectDialog.this.mSubjectHistory.get(i));
            CallSubjectDialog.access$300(CallSubjectDialog.this, false);
        }
    };
    private int mLimit = 16;
    private Charset mMessageEncoding;
    private String mNameOrNumber;
    private TextView mNameView;
    /* access modifiers changed from: private */
    public String mNumber;
    private String mNumberLabel;
    private TextView mNumberView;
    /* access modifiers changed from: private */
    public PhoneAccountHandle mPhoneAccountHandle;
    private long mPhotoID;
    private Uri mPhotoUri;
    private SharedPreferences mPrefs;
    private View mSendAndCallButton;
    private final View.OnClickListener mSendAndCallOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            String obj = CallSubjectDialog.this.mCallSubjectView.getText().toString();
            CallSubjectDialog callSubjectDialog = CallSubjectDialog.this;
            PreCall.start(callSubjectDialog, new CallIntentBuilder(callSubjectDialog.mNumber, CallInitiationType$Type.CALL_SUBJECT_DIALOG).setPhoneAccountHandle(CallSubjectDialog.this.mPhoneAccountHandle).setCallSubject(obj));
            CallSubjectDialog.this.mSubjectHistory.add(obj);
            CallSubjectDialog callSubjectDialog2 = CallSubjectDialog.this;
            callSubjectDialog2.saveSubjectHistory(callSubjectDialog2.mSubjectHistory);
            CallSubjectDialog.this.finish();
        }
    };
    /* access modifiers changed from: private */
    public List<String> mSubjectHistory;
    /* access modifiers changed from: private */
    public ListView mSubjectList;
    private final TextWatcher mTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            CallSubjectDialog.this.updateCharacterLimit();
        }
    };

    static /* synthetic */ void access$300(CallSubjectDialog callSubjectDialog, final boolean z) {
        if (z && callSubjectDialog.mSubjectList.getVisibility() == 0) {
            return;
        }
        if (z || callSubjectDialog.mSubjectList.getVisibility() != 8) {
            final int bottom = callSubjectDialog.mDialogView.getBottom();
            if (z) {
                callSubjectDialog.mSubjectList.setAdapter(new ArrayAdapter(callSubjectDialog, R.layout.call_subject_history_list_item, callSubjectDialog.mSubjectHistory));
                callSubjectDialog.mSubjectList.setVisibility(0);
            } else {
                callSubjectDialog.mSubjectList.setVisibility(8);
            }
            CallUtil.doOnPreDraw(callSubjectDialog.mBackgroundView, true, new Runnable() {
                public void run() {
                    int bottom = bottom - CallSubjectDialog.this.mDialogView.getBottom();
                    if (bottom != 0) {
                        CallSubjectDialog.this.mDialogView.setTranslationY((float) bottom);
                        CallSubjectDialog.this.mDialogView.animate().translationY(0.0f).setInterpolator(AnimUtils.EASE_OUT_EASE_IN).setDuration((long) CallSubjectDialog.this.mAnimationDuration).start();
                    }
                    if (z) {
                        CallSubjectDialog.this.mSubjectList.setTranslationY((float) CallSubjectDialog.this.mSubjectList.getHeight());
                        CallSubjectDialog.this.mSubjectList.animate().translationY(0.0f).setInterpolator(AnimUtils.EASE_OUT_EASE_IN).setDuration((long) CallSubjectDialog.this.mAnimationDuration).setListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                            }

                            public void onAnimationStart(Animator animator) {
                                super.onAnimationStart(animator);
                                CallSubjectDialog.this.mSubjectList.setVisibility(0);
                            }
                        }).start();
                        return;
                    }
                    CallSubjectDialog.this.mSubjectList.setTranslationY(0.0f);
                    CallSubjectDialog.this.mSubjectList.animate().translationY((float) CallSubjectDialog.this.mSubjectList.getHeight()).setInterpolator(AnimUtils.EASE_OUT_EASE_IN).setDuration((long) CallSubjectDialog.this.mAnimationDuration).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            CallSubjectDialog.this.mSubjectList.setVisibility(8);
                        }

                        public void onAnimationStart(Animator animator) {
                            super.onAnimationStart(animator);
                        }
                    }).start();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void saveSubjectHistory(List<String> list) {
        int i;
        while (true) {
            i = 0;
            if (list.size() <= 5) {
                break;
            }
            list.remove(0);
        }
        SharedPreferences.Editor edit = this.mPrefs.edit();
        for (String next : list) {
            if (!TextUtils.isEmpty(next)) {
                edit.putString("subject_history_item" + i, next);
                i++;
            }
        }
        edit.putInt("subject_history_count", i);
        edit.apply();
    }

    public static void start(Activity activity, long j, Uri uri, Uri uri2, String str, String str2, String str3, String str4, int i, PhoneAccountHandle phoneAccountHandle) {
        Bundle bundle = new Bundle();
        bundle.putLong("PHOTO_ID", j);
        bundle.putParcelable("PHOTO_URI", uri);
        bundle.putParcelable("CONTACT_URI", uri2);
        bundle.putString("NAME_OR_NUMBER", str);
        bundle.putString("NUMBER", str2);
        bundle.putString("DISPLAY_NUMBER", str3);
        bundle.putString("NUMBER_LABEL", str4);
        bundle.putInt("CONTACT_TYPE", i);
        bundle.putParcelable("PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        Intent intent = new Intent(activity, CallSubjectDialog.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void updateCharacterLimit() {
        int i;
        String obj = this.mCallSubjectView.getText().toString();
        Charset charset = this.mMessageEncoding;
        if (charset != null) {
            i = obj.getBytes(charset).length;
        } else {
            i = obj.length();
        }
        this.mCharacterLimitView.setText(getString(R.string.call_subject_limit, new Object[]{Integer.valueOf(i), Integer.valueOf(this.mLimit)}));
        if (i >= this.mLimit) {
            this.mCharacterLimitView.setTextColor(getResources().getColor(R.color.call_subject_limit_exceeded));
        } else {
            this.mCharacterLimitView.setTextColor(getResources().getColor(R.color.dialer_secondary_text_color));
        }
    }

    public void hideSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    public void onCreate(Bundle bundle) {
        String str;
        PhoneAccount phoneAccount;
        Bundle extras;
        super.onCreate(bundle);
        this.mAnimationDuration = getResources().getInteger(R.integer.call_subject_animation_duration);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Bundle extras2 = getIntent().getExtras();
        if (extras2 == null) {
            LogUtil.m8e("CallSubjectDialog.readArguments", "arguments cannot be null", new Object[0]);
        } else {
            this.mPhotoID = extras2.getLong("PHOTO_ID");
            this.mPhotoUri = (Uri) extras2.getParcelable("PHOTO_URI");
            this.mContactUri = (Uri) extras2.getParcelable("CONTACT_URI");
            this.mNameOrNumber = extras2.getString("NAME_OR_NUMBER");
            this.mNumber = extras2.getString("NUMBER");
            this.mDisplayNumber = extras2.getString("DISPLAY_NUMBER");
            this.mNumberLabel = extras2.getString("NUMBER_LABEL");
            this.mContactType = extras2.getInt("CONTACT_TYPE", 1);
            this.mPhoneAccountHandle = (PhoneAccountHandle) extras2.getParcelable("PHONE_ACCOUNT_HANDLE");
        }
        if (!(this.mPhoneAccountHandle == null || (phoneAccount = ((TelecomManager) getSystemService("telecom")).getPhoneAccount(this.mPhoneAccountHandle)) == null || (extras = phoneAccount.getExtras()) == null)) {
            this.mLimit = extras.getInt("android.telecom.extra.CALL_SUBJECT_MAX_LENGTH", this.mLimit);
            String string = extras.getString("android.telecom.extra.CALL_SUBJECT_CHARACTER_ENCODING");
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.mMessageEncoding = Charset.forName(string);
                } catch (UnsupportedCharsetException unused) {
                    LogUtil.m8e("CallSubjectDialog.loadConfiguration", GeneratedOutlineSupport.outline8("invalid charset: ", string), new Object[0]);
                    this.mMessageEncoding = null;
                }
            } else {
                this.mMessageEncoding = null;
            }
        }
        SharedPreferences sharedPreferences = this.mPrefs;
        int i = sharedPreferences.getInt("subject_history_count", 0);
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            String string2 = sharedPreferences.getString("subject_history_item" + i2, (String) null);
            if (!TextUtils.isEmpty(string2)) {
                arrayList.add(string2);
            }
        }
        this.mSubjectHistory = arrayList;
        setContentView(R.layout.dialog_call_subject);
        getWindow().setLayout(-1, -1);
        this.mBackgroundView = findViewById(R.id.call_subject_dialog);
        this.mBackgroundView.setOnClickListener(this.mBackgroundListener);
        this.mDialogView = findViewById(R.id.dialog_view);
        this.mContactPhoto = (QuickContactBadge) findViewById(R.id.contact_photo);
        this.mNameView = (TextView) findViewById(R.id.name);
        this.mNumberView = (TextView) findViewById(R.id.number);
        this.mCallSubjectView = (EditText) findViewById(R.id.call_subject);
        this.mCallSubjectView.addTextChangedListener(this.mTextWatcher);
        this.mCallSubjectView.setOnClickListener(this.mCallSubjectClickListener);
        this.mCallSubjectView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mLimit)});
        this.mCharacterLimitView = (TextView) findViewById(R.id.character_limit);
        this.mHistoryButton = findViewById(R.id.history_button);
        this.mHistoryButton.setOnClickListener(this.mHistoryOnClickListener);
        this.mHistoryButton.setVisibility(this.mSubjectHistory.isEmpty() ? 8 : 0);
        this.mSendAndCallButton = findViewById(R.id.send_and_call_button);
        this.mSendAndCallButton.setOnClickListener(this.mSendAndCallOnClickListener);
        this.mSubjectList = (ListView) findViewById(R.id.subject_list);
        this.mSubjectList.setOnItemClickListener(this.mItemClickListener);
        this.mSubjectList.setVisibility(8);
        if (this.mContactUri != null) {
            ContactPhotoManager.getInstance(this).loadDialerThumbnailOrPhoto(this.mContactPhoto, this.mContactUri, this.mPhotoID, this.mPhotoUri, this.mNameOrNumber, this.mContactType);
        } else {
            this.mContactPhoto.setVisibility(8);
        }
        this.mNameView.setText(this.mNameOrNumber);
        if (!TextUtils.isEmpty(this.mDisplayNumber)) {
            this.mNumberView.setVisibility(0);
            TextView textView = this.mNumberView;
            if (TextUtils.isEmpty(this.mNumberLabel)) {
                str = this.mDisplayNumber;
            } else {
                str = getString(R.string.old_call_subject_type_and_number, new Object[]{this.mNumberLabel, this.mDisplayNumber});
            }
            textView.setText(str);
        } else {
            this.mNumberView.setVisibility(8);
            this.mNumberView.setText((CharSequence) null);
        }
        updateCharacterLimit();
    }
}
