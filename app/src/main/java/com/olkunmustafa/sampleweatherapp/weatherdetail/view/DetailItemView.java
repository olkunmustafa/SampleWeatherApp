package com.olkunmustafa.sampleweatherapp.weatherdetail.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.olkunmustafa.sampleweatherapp.R;

import javax.annotation.Nullable;

public class DetailItemView extends CardView {

    private Context mContext;
    private AttributeSet mAttributeSet;
    private String mDetailTitle;

    @BindView(R.id.detail_key)
    AppCompatTextView mDetailKey;

    @BindView(R.id.detail_value)
    AppCompatTextView mDetailValue;

    public DetailItemView(Context context) {
        this(context, null, 0);
    }

    public DetailItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;
        this.mAttributeSet = attrs;
        this.init();
    }

    private void init() {
        inflate(mContext, R.layout.detail_item_group_view, this);

        TypedArray a = mContext.getTheme().obtainStyledAttributes(
                this.mAttributeSet,
                R.styleable.DetailItemView,
                0, 0);

        try {
            this.mDetailTitle = a.getString(R.styleable.DetailItemView_detail_title);

        } finally {
            a.recycle();
        }

        int wrapperPaddingTB = mContext.getResources().getDimensionPixelOffset(R.dimen.detail_wrapper_item_ptb);
        this.setPadding(0, wrapperPaddingTB, 0, wrapperPaddingTB);
        this.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.white));

        ButterKnife.bind(this);

        this.setDetailKey(this.mDetailTitle);
    }

    public void setDetailKey(String title) {
        this.mDetailKey.setText(title);
    }

    public void setDetailValue( String value ){
        this.mDetailValue.setText(value);
    }

}
