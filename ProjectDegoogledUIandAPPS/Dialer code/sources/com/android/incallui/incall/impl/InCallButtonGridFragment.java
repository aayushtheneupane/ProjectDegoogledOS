package com.android.incallui.incall.impl;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.incallui.incall.impl.ButtonController;
import java.util.List;

public class InCallButtonGridFragment extends Fragment {
    private OnButtonGridCreatedListener buttonGridListener;
    private CheckableLabeledButton[] buttons = new CheckableLabeledButton[6];

    public interface OnButtonGridCreatedListener {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.buttonGridListener = (OnButtonGridCreatedListener) FragmentUtils.getParent((Fragment) this, OnButtonGridCreatedListener.class);
        Assert.isNotNull(this.buttonGridListener);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.incall_button_grid, viewGroup, false);
        this.buttons[0] = (CheckableLabeledButton) inflate.findViewById(R.id.incall_first_button);
        this.buttons[1] = (CheckableLabeledButton) inflate.findViewById(R.id.incall_second_button);
        this.buttons[2] = (CheckableLabeledButton) inflate.findViewById(R.id.incall_third_button);
        this.buttons[3] = (CheckableLabeledButton) inflate.findViewById(R.id.incall_fourth_button);
        this.buttons[4] = (CheckableLabeledButton) inflate.findViewById(R.id.incall_fifth_button);
        this.buttons[5] = (CheckableLabeledButton) inflate.findViewById(R.id.incall_sixth_button);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((InCallFragment) this.buttonGridListener).onButtonGridDestroyed();
    }

    public void onInCallScreenDialpadVisibilityChange(boolean z) {
        for (CheckableLabeledButton importantForAccessibility : this.buttons) {
            importantForAccessibility.setImportantForAccessibility(z ? 4 : 0);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        ((InCallFragment) this.buttonGridListener).onButtonGridCreated(this);
    }

    public void updateButtonColor(int i) {
        for (CheckableLabeledButton checkedColor : this.buttons) {
            checkedColor.setCheckedColor(i);
        }
    }

    public int updateButtonStates(List<ButtonController> list, ButtonChooser buttonChooser, int i, int i2) {
        ArraySet arraySet = new ArraySet();
        ArraySet arraySet2 = new ArraySet();
        for (ButtonController next : list) {
            if (next.isAllowed()) {
                arraySet.add(Integer.valueOf(next.getInCallButtonId()));
                if (!next.isEnabled()) {
                    arraySet2.add(Integer.valueOf(next.getInCallButtonId()));
                }
            }
        }
        for (ButtonController button : list) {
            button.setButton((CheckableLabeledButton) null);
        }
        if (buttonChooser == null) {
            buttonChooser = ButtonController.Controllers.newButtonChooser(i, false, i2);
        }
        int integer = getResources().getInteger(R.integer.incall_num_rows) * 3;
        List<Integer> buttonPlacement = buttonChooser.getButtonPlacement(integer, arraySet, arraySet2);
        for (int i3 = 0; i3 < 6; i3++) {
            if (i3 >= buttonPlacement.size()) {
                this.buttons[i3].setVisibility(4);
            } else {
                ((InCallFragment) this.buttonGridListener).getButtonController(buttonPlacement.get(i3).intValue()).setButton(this.buttons[i3]);
            }
        }
        return integer;
    }
}
