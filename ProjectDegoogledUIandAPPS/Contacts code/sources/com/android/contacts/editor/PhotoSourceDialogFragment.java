package com.android.contacts.editor;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.contacts.R;
import com.android.contacts.editor.PhotoActionPopup;
import java.util.ArrayList;

public class PhotoSourceDialogFragment extends DialogFragment {

    public interface Listener {
        void onPickFromGalleryChosen();

        void onRemovePictureChosen();

        void onTakePhotoChosen();
    }

    public static void show(Activity activity, int i) {
        if (activity instanceof Listener) {
            Bundle bundle = new Bundle();
            bundle.putInt("photoMode", i);
            PhotoSourceDialogFragment photoSourceDialogFragment = new PhotoSourceDialogFragment();
            photoSourceDialogFragment.setArguments(bundle);
            photoSourceDialogFragment.show(activity.getFragmentManager(), "photoSource");
            return;
        }
        throw new IllegalArgumentException("Activity must implement " + Listener.class.getName());
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final ArrayList<PhotoActionPopup.ChoiceListItem> choices = PhotoActionPopup.getChoices(getActivity(), getArguments().getInt("photoMode"));
        CharSequence[] charSequenceArr = new CharSequence[choices.size()];
        for (int i = 0; i < charSequenceArr.length; i++) {
            charSequenceArr[i] = choices.get(i).toString();
        }
        C03291 r1 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Listener listener = (Listener) PhotoSourceDialogFragment.this.getActivity();
                int id = ((PhotoActionPopup.ChoiceListItem) choices.get(i)).getId();
                if (id == 1) {
                    listener.onTakePhotoChosen();
                } else if (id == 2) {
                    listener.onPickFromGalleryChosen();
                } else if (id == 3) {
                    listener.onRemovePictureChosen();
                }
                PhotoSourceDialogFragment.this.dismiss();
            }
        };
        TextView textView = (TextView) View.inflate(getActivity(), R.layout.dialog_title, (ViewGroup) null);
        textView.setText(R.string.menu_change_photo);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCustomTitle(textView);
        builder.setItems(charSequenceArr, r1);
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
