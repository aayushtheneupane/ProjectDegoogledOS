package com.android.bubble;

import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import com.android.bubble.AutoValue_BubbleInfo;
import com.android.bubble.AutoValue_BubbleInfo_Action;
import com.google.auto.value.AutoValue;
import java.util.Collections;
import java.util.List;

@AutoValue
public abstract class BubbleInfo {

    @AutoValue
    public static abstract class Action {

        public static abstract class Builder {
            public abstract Action build();

            public abstract Builder setCheckable(boolean z);

            public abstract Builder setChecked(boolean z);

            public abstract Builder setIconDrawable(Drawable drawable);

            public abstract Builder setIntent(PendingIntent pendingIntent);

            public abstract Builder setName(CharSequence charSequence);

            public abstract Builder setSecondaryIconDrawable(Drawable drawable);
        }

        public static Builder builder() {
            AutoValue_BubbleInfo_Action.Builder builder = new AutoValue_BubbleInfo_Action.Builder();
            builder.setCheckable(true);
            builder.setChecked(false);
            return builder;
        }
    }

    public static abstract class Builder {
        public abstract BubbleInfo build();

        public abstract Builder setActions(List<Action> list);

        public abstract Builder setPrimaryColor(int i);

        public abstract Builder setPrimaryIcon(Icon icon);

        public abstract Builder setStartingYPosition(int i);
    }

    public static Builder builder() {
        AutoValue_BubbleInfo.Builder builder = new AutoValue_BubbleInfo.Builder();
        builder.setActions(Collections.emptyList());
        return builder;
    }
}
