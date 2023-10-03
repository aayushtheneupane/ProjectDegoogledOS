package com.android.settings.homepage.contextualcards;

import android.util.Log;
import com.android.settings.homepage.contextualcards.ContextualCardLookupTable;
import com.android.settings.homepage.contextualcards.conditional.ConditionContextualCardController;
import com.android.settings.homepage.contextualcards.conditional.ConditionContextualCardRenderer;
import com.android.settings.homepage.contextualcards.conditional.ConditionFooterContextualCardRenderer;
import com.android.settings.homepage.contextualcards.conditional.ConditionHeaderContextualCardRenderer;
import com.android.settings.homepage.contextualcards.legacysuggestion.LegacySuggestionContextualCardController;
import com.android.settings.homepage.contextualcards.legacysuggestion.LegacySuggestionContextualCardRenderer;
import com.android.settings.homepage.contextualcards.slices.SliceContextualCardController;
import com.android.settings.homepage.contextualcards.slices.SliceContextualCardRenderer;
import com.havoc.config.center.C1715R;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContextualCardLookupTable {
    static final Set<ControllerRendererMapping> LOOKUP_TABLE = new TreeSet<ControllerRendererMapping>() {
        {
            add(new ControllerRendererMapping(3, C1715R.layout.conditional_card_half_tile, ConditionContextualCardController.class, ConditionContextualCardRenderer.class));
            add(new ControllerRendererMapping(3, C1715R.layout.conditional_card_full_tile, ConditionContextualCardController.class, ConditionContextualCardRenderer.class));
            add(new ControllerRendererMapping(2, C1715R.layout.legacy_suggestion_tile, LegacySuggestionContextualCardController.class, LegacySuggestionContextualCardRenderer.class));
            add(new ControllerRendererMapping(1, C1715R.layout.contextual_slice_deferred_setup, SliceContextualCardController.class, SliceContextualCardRenderer.class));
            add(new ControllerRendererMapping(1, C1715R.layout.contextual_slice_full_tile, SliceContextualCardController.class, SliceContextualCardRenderer.class));
            add(new ControllerRendererMapping(1, C1715R.layout.contextual_slice_half_tile, SliceContextualCardController.class, SliceContextualCardRenderer.class));
            add(new ControllerRendererMapping(5, C1715R.layout.conditional_card_footer, ConditionContextualCardController.class, ConditionFooterContextualCardRenderer.class));
            add(new ControllerRendererMapping(4, C1715R.layout.conditional_card_header, ConditionContextualCardController.class, ConditionHeaderContextualCardRenderer.class));
        }
    };

    static class ControllerRendererMapping implements Comparable<ControllerRendererMapping> {
        /* access modifiers changed from: package-private */
        public final int mCardType;
        final Class<? extends ContextualCardController> mControllerClass;
        final Class<? extends ContextualCardRenderer> mRendererClass;
        /* access modifiers changed from: package-private */
        public final int mViewType;

        ControllerRendererMapping(int i, int i2, Class<? extends ContextualCardController> cls, Class<? extends ContextualCardRenderer> cls2) {
            this.mCardType = i;
            this.mViewType = i2;
            this.mControllerClass = cls;
            this.mRendererClass = cls2;
        }

        public int compareTo(ControllerRendererMapping controllerRendererMapping) {
            return Comparator.comparingInt(C0869xa1c67b3a.INSTANCE).thenComparingInt(C0870x17133f34.INSTANCE).compare(this, controllerRendererMapping);
        }
    }

    public static Class<? extends ContextualCardController> getCardControllerClass(int i) {
        for (ControllerRendererMapping next : LOOKUP_TABLE) {
            if (next.mCardType == i) {
                return next.mControllerClass;
            }
        }
        return null;
    }

    public static Class<? extends ContextualCardRenderer> getCardRendererClassByViewType(int i) throws IllegalStateException {
        List list = (List) LOOKUP_TABLE.stream().filter(new Predicate(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return ContextualCardLookupTable.lambda$getCardRendererClassByViewType$0(this.f$0, (ContextualCardLookupTable.ControllerRendererMapping) obj);
            }
        }).collect(Collectors.toList());
        if (list == null || list.isEmpty()) {
            Log.w("ContextualCardLookup", "No matching mapping");
            return null;
        } else if (list.size() == 1) {
            return ((ControllerRendererMapping) list.get(0)).mRendererClass;
        } else {
            throw new IllegalStateException("Have duplicate VIEW_TYPE in lookup table.");
        }
    }

    static /* synthetic */ boolean lambda$getCardRendererClassByViewType$0(int i, ControllerRendererMapping controllerRendererMapping) {
        return controllerRendererMapping.mViewType == i;
    }
}
