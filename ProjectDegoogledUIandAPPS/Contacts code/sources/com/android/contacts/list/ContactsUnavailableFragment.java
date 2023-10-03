package com.android.contacts.list;

import android.app.Fragment;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.android.contacts.R;
import com.android.contacts.compat.ProviderStatusCompat;
import com.android.contacts.interactions.ImportDialogFragment;
import com.android.contacts.util.ImplicitIntentsUtil;

public class ContactsUnavailableFragment extends Fragment implements View.OnClickListener {
    private Button mAddAccountButton;
    private View mButtonsContainer;
    private ImageView mImageView;
    private Button mImportContactsButton;
    private TextView mMessageView;
    private ProgressBar mProgress;
    private Integer mProviderStatus;
    private View mView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.layout.contacts_unavailable_fragment, (ViewGroup) null);
        this.mImageView = (ImageView) this.mView.findViewById(R.id.empty_image);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mImageView.getLayoutParams();
        layoutParams.setMargins(0, (getResources().getDisplayMetrics().heightPixels / getResources().getInteger(R.integer.contacts_no_account_empty_image_margin_divisor)) - getResources().getDimensionPixelSize(R.dimen.contacts_no_account_empty_image_offset), 0, 0);
        layoutParams.gravity = 1;
        this.mImageView.setLayoutParams(layoutParams);
        this.mMessageView = (TextView) this.mView.findViewById(R.id.message);
        this.mAddAccountButton = (Button) this.mView.findViewById(R.id.add_account_button);
        this.mAddAccountButton.setOnClickListener(this);
        this.mAddAccountButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.primary_color), PorterDuff.Mode.SRC_ATOP);
        this.mImportContactsButton = (Button) this.mView.findViewById(R.id.import_contacts_button);
        this.mImportContactsButton.setOnClickListener(this);
        this.mImportContactsButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.primary_color), PorterDuff.Mode.SRC_ATOP);
        this.mProgress = (ProgressBar) this.mView.findViewById(R.id.progress);
        if (getResources().getConfiguration().orientation == 2) {
            this.mButtonsContainer = this.mView.findViewById(R.id.buttons_container);
        }
        Integer num = this.mProviderStatus;
        if (num != null) {
            updateStatus(num.intValue());
        }
        return this.mView;
    }

    public void updateStatus(int i) {
        this.mProviderStatus = Integer.valueOf(i);
        if (this.mView != null) {
            if (i == ProviderStatusCompat.STATUS_EMPTY) {
                updateViewsForEmptyStatus();
            } else if (i == ProviderStatusCompat.STATUS_BUSY || i == 3) {
                updateViewsForBusyStatus();
            }
        }
    }

    private void updateViewsForEmptyStatus() {
        this.mMessageView.setVisibility(0);
        updateButtonVisibility(0);
        this.mProgress.setVisibility(8);
    }

    private void updateViewsForBusyStatus() {
        this.mMessageView.setVisibility(8);
        this.mImageView.setVisibility(8);
        updateButtonVisibility(8);
        this.mProgress.setVisibility(0);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.add_account_button) {
            ImplicitIntentsUtil.startActivityOutsideApp(getActivity(), ImplicitIntentsUtil.getIntentForAddingGoogleAccount());
        } else if (id == R.id.import_contacts_button) {
            ImportDialogFragment.show(getFragmentManager());
        }
    }

    private void updateButtonVisibility(int i) {
        if (getResources().getConfiguration().orientation == 2) {
            this.mAddAccountButton.setVisibility(i);
            this.mImportContactsButton.setVisibility(i);
            this.mButtonsContainer.setVisibility(i);
            return;
        }
        this.mAddAccountButton.setVisibility(i);
        this.mImportContactsButton.setVisibility(i);
    }

    public Context getContext() {
        return getActivity();
    }
}
