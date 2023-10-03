package android.support.transition;

import android.view.View;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransitionValues {
    final ArrayList<Transition> mTargetedTransitions = new ArrayList<>();
    public final Map<String, Object> values = new HashMap();
    public View view;

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues)) {
            return false;
        }
        TransitionValues transitionValues = (TransitionValues) obj;
        return this.view == transitionValues.view && this.values.equals(transitionValues.values);
    }

    public int hashCode() {
        return this.values.hashCode() + (this.view.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("TransitionValues@");
        outline13.append(Integer.toHexString(this.values.hashCode() + (this.view.hashCode() * 31)));
        outline13.append(":\n");
        String outline8 = GeneratedOutlineSupport.outline8(GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline14(outline13.toString(), "    view = "), this.view, "\n"), "    values:");
        for (String next : this.values.keySet()) {
            outline8 = outline8 + "    " + next + ": " + this.values.get(next) + "\n";
        }
        return outline8;
    }
}
