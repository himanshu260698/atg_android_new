package com.ATG.World.utilities;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by Chetan on 22-12-2017.
 */

public class CustomFontEditText extends AppCompatEditText {
    public CustomFontEditText(Context context) {
        super(context);
        init(context, null);
    }

    public CustomFontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomFontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }
    // endregion

    // region Helper Methods
    private void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            //CustomFontUtils.applyCustomFont(this, context, attrs);
        }
    }
}
