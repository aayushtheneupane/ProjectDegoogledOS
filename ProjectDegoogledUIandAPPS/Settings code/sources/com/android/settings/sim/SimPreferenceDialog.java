package com.android.settings.sim;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.Utils;
import com.havoc.config.center.C1715R;

public class SimPreferenceDialog extends Activity {
    private final String SIM_NAME = "sim_name";
    private final String TINT_POS = "tint_pos";
    AlertDialog.Builder mBuilder;
    private String[] mColorStrings;
    private Context mContext;
    View mDialogLayout;
    private int mSlotId;
    /* access modifiers changed from: private */
    public SubscriptionInfo mSubInfoRecord;
    /* access modifiers changed from: private */
    public SubscriptionManager mSubscriptionManager;
    /* access modifiers changed from: private */
    public int[] mTintArr;
    /* access modifiers changed from: private */
    public int mTintSelectorPos;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mSlotId = getIntent().getExtras().getInt("slot_id", -1);
        this.mSubscriptionManager = SubscriptionManager.from(this.mContext);
        this.mSubInfoRecord = this.mSubscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(this.mSlotId);
        this.mTintArr = this.mContext.getResources().getIntArray(17236131);
        this.mColorStrings = this.mContext.getResources().getStringArray(C1715R.array.color_picker);
        this.mTintSelectorPos = 0;
        this.mBuilder = new AlertDialog.Builder(this.mContext);
        this.mDialogLayout = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(C1715R.layout.multi_sim_dialog, (ViewGroup) null);
        this.mBuilder.setView(this.mDialogLayout);
        createEditDialog(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("tint_pos", this.mTintSelectorPos);
        bundle.putString("sim_name", ((EditText) this.mDialogLayout.findViewById(C1715R.C1718id.sim_name)).getText().toString());
        super.onSaveInstanceState(bundle);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("tint_pos");
        ((Spinner) this.mDialogLayout.findViewById(C1715R.C1718id.spinner)).setSelection(i);
        this.mTintSelectorPos = i;
        EditText editText = (EditText) this.mDialogLayout.findViewById(C1715R.C1718id.sim_name);
        editText.setText(bundle.getString("sim_name"));
        Utils.setEditTextCursorPosition(editText);
    }

    private void createEditDialog(Bundle bundle) {
        Resources resources = this.mContext.getResources();
        EditText editText = (EditText) this.mDialogLayout.findViewById(C1715R.C1718id.sim_name);
        editText.setText(this.mSubInfoRecord.getDisplayName());
        Utils.setEditTextCursorPosition(editText);
        final Spinner spinner = (Spinner) this.mDialogLayout.findViewById(C1715R.C1718id.spinner);
        SelectColorAdapter selectColorAdapter = new SelectColorAdapter(this.mContext, C1715R.layout.settings_color_picker_item, this.mColorStrings);
        selectColorAdapter.setDropDownViewResource(17367049);
        spinner.setAdapter(selectColorAdapter);
        int i = 0;
        while (true) {
            int[] iArr = this.mTintArr;
            if (i >= iArr.length) {
                break;
            } else if (iArr[i] == this.mSubInfoRecord.getIconTint()) {
                spinner.setSelection(i);
                this.mTintSelectorPos = i;
                break;
            } else {
                i++;
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                spinner.setSelection(i);
                int unused = SimPreferenceDialog.this.mTintSelectorPos = i;
            }
        });
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        TextView textView = (TextView) this.mDialogLayout.findViewById(C1715R.C1718id.number);
        String line1Number = telephonyManager.getLine1Number(this.mSubInfoRecord.getSubscriptionId());
        if (TextUtils.isEmpty(line1Number)) {
            textView.setText(resources.getString(17039374));
        } else {
            textView.setText(PhoneNumberUtils.formatNumber(line1Number));
        }
        String simOperatorName = telephonyManager.getSimOperatorName(this.mSubInfoRecord.getSubscriptionId());
        TextView textView2 = (TextView) this.mDialogLayout.findViewById(C1715R.C1718id.carrier);
        if (TextUtils.isEmpty(simOperatorName)) {
            simOperatorName = this.mContext.getString(17039374);
        }
        textView2.setText(simOperatorName);
        this.mBuilder.setTitle((CharSequence) String.format(resources.getString(C1715R.string.sim_editor_title), new Object[]{Integer.valueOf(this.mSubInfoRecord.getSimSlotIndex() + 1)}));
        this.mBuilder.setPositiveButton((int) C1715R.string.okay, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText = (EditText) SimPreferenceDialog.this.mDialogLayout.findViewById(C1715R.C1718id.sim_name);
                Utils.setEditTextCursorPosition(editText);
                String obj = editText.getText().toString();
                int subscriptionId = SimPreferenceDialog.this.mSubInfoRecord.getSubscriptionId();
                SimPreferenceDialog.this.mSubInfoRecord.setDisplayName(obj);
                SimPreferenceDialog.this.mSubscriptionManager.setDisplayName(obj, subscriptionId, 2);
                int selectedItemPosition = spinner.getSelectedItemPosition();
                int subscriptionId2 = SimPreferenceDialog.this.mSubInfoRecord.getSubscriptionId();
                int i2 = SimPreferenceDialog.this.mTintArr[selectedItemPosition];
                SimPreferenceDialog.this.mSubInfoRecord.setIconTint(i2);
                SimPreferenceDialog.this.mSubscriptionManager.setIconTint(i2, subscriptionId2);
                dialogInterface.dismiss();
            }
        });
        this.mBuilder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        this.mBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                SimPreferenceDialog.this.finish();
            }
        });
        this.mBuilder.create().show();
    }

    private class SelectColorAdapter extends ArrayAdapter<CharSequence> {
        private Context mContext;
        private int mResId;

        public SelectColorAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            this.mContext = context;
            this.mResId = i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            Resources resources = this.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(C1715R.dimen.color_swatch_size);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(C1715R.dimen.color_swatch_stroke_width);
            if (view == null) {
                view = layoutInflater.inflate(this.mResId, (ViewGroup) null);
                viewHolder = new ViewHolder();
                ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
                shapeDrawable.setIntrinsicHeight(dimensionPixelSize);
                shapeDrawable.setIntrinsicWidth(dimensionPixelSize);
                shapeDrawable.getPaint().setStrokeWidth((float) dimensionPixelSize2);
                viewHolder.label = (TextView) view.findViewById(C1715R.C1718id.color_text);
                viewHolder.icon = (ImageView) view.findViewById(C1715R.C1718id.color_icon);
                viewHolder.swatch = shapeDrawable;
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.label.setText((CharSequence) getItem(i));
            viewHolder.swatch.getPaint().setColor(SimPreferenceDialog.this.mTintArr[i]);
            viewHolder.swatch.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            viewHolder.icon.setVisibility(0);
            viewHolder.icon.setImageDrawable(viewHolder.swatch);
            return view;
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View view2 = getView(i, view, viewGroup);
            ViewHolder viewHolder = (ViewHolder) view2.getTag();
            if (SimPreferenceDialog.this.mTintSelectorPos == i) {
                viewHolder.swatch.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            } else {
                viewHolder.swatch.getPaint().setStyle(Paint.Style.STROKE);
            }
            viewHolder.icon.setVisibility(0);
            return view2;
        }

        private class ViewHolder {
            ImageView icon;
            TextView label;
            ShapeDrawable swatch;

            private ViewHolder() {
            }
        }
    }
}
