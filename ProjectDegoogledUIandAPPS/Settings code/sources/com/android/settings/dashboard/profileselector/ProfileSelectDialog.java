package com.android.settings.dashboard.profileselector;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.settingslib.drawer.Tile;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;

public class ProfileSelectDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private static final boolean DEBUG = Log.isLoggable("ProfileSelectDialog", 3);
    private Tile mSelectedTile;

    public static void show(FragmentManager fragmentManager, Tile tile) {
        ProfileSelectDialog profileSelectDialog = new ProfileSelectDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedTile", tile);
        profileSelectDialog.setArguments(bundle);
        profileSelectDialog.show(fragmentManager, "select_profile");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSelectedTile = (Tile) getArguments().getParcelable("selectedTile");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        UserAdapter createUserAdapter = UserAdapter.createUserAdapter(UserManager.get(activity), activity, this.mSelectedTile.userHandle);
        builder.setTitle((int) C1715R.string.choose_profile);
        builder.setAdapter(createUserAdapter, this);
        return builder.create();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = this.mSelectedTile.getIntent();
        intent.addFlags(32768);
        getActivity().startActivityAsUser(intent, this.mSelectedTile.userHandle.get(i));
    }

    public static void updateUserHandlesIfNeeded(Context context, Tile tile) {
        ArrayList<UserHandle> arrayList = tile.userHandle;
        if (arrayList != null && arrayList.size() > 1) {
            UserManager userManager = UserManager.get(context);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (userManager.getUserInfo(arrayList.get(size).getIdentifier()) == null) {
                    if (DEBUG) {
                        Log.d("ProfileSelectDialog", "Delete the user: " + arrayList.get(size).getIdentifier());
                    }
                    arrayList.remove(size);
                }
            }
        }
    }
}
