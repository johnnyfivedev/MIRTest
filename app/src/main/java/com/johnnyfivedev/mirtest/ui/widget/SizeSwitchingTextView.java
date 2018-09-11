package com.johnnyfivedev.mirtest.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.johnnyfivedev.mirtest.R;

import java.util.ArrayList;
import java.util.List;

public class SizeSwitchingTextView extends AppCompatTextView {

    private Context context;
    private AttributeSet attrs;
    private int defStyleAttr;

    private List<Integer> sizeResIds = new ArrayList<>();
    private int currentSizePosition;


    //region ===================== Constructor ======================

    public SizeSwitchingTextView(Context context) {
        super(context);
        this.context = context;
        initUI();
    }

    public SizeSwitchingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        initUI();
    }

    public SizeSwitchingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        this.defStyleAttr = defStyleAttr;
        initUI();
    }

    //endregion

    //region ===================== Public ======================

    public void applyNextSize() {
        if (sizeResIds != null) {
            if (currentSizePosition < sizeResIds.size() - 1) {
                currentSizePosition++;
            } else {
                currentSizePosition = 0;
            }
            Integer sizeResId = sizeResIds.get(currentSizePosition);
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(sizeResId));
        }
    }

    //endregion

    //region ===================== Internal ======================

    protected void initUI() {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SizeSwitchingTextView);
        int arrayResId = typedArray.getResourceId(R.styleable.SizeSwitchingTextView_sizes, 0);

        if (arrayResId != 0) {
            TypedArray sizesTypedArray = getResources().obtainTypedArray(arrayResId);
            for (int i = 0; i < sizesTypedArray.length(); i++) {
                sizeResIds.add(sizesTypedArray.getResourceId(i, 0));
            }
            sizesTypedArray.recycle();
            applyFirstSizeAsDefault();
        }
        typedArray.recycle();
    }

    private void applyFirstSizeAsDefault() {
        if (sizeResIds != null && !sizeResIds.isEmpty()) {
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(sizeResIds.get(0)));
        }
    }
}
