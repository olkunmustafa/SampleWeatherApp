package com.olkunmustafa.sampleweatherapp.weatherdetail.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.olkunmustafa.sampleweatherapp.R;

import javax.annotation.Nullable;

public class DetailItemView extends FrameLayout {

    private Context mContext;
    private AttributeSet mAttributeSet;
    private String mDetailTitle;

    @BindView(R.id.detail_key)
    AppCompatTextView mDetailKey;

    @BindView(R.id.detail_value)
    AppCompatTextView detailValue;

    public DetailItemView(Context context) {
        this(context,null, 0);
    }

    public DetailItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DetailItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;
        this.mAttributeSet = attrs;
        this.init();
    }

    private void init() {
        LayoutInflater inflater = ( LayoutInflater ) this.mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View rootView = inflater.inflate( R.layout.detail_item_group_view, null, false );

        TypedArray a = mContext.getTheme().obtainStyledAttributes(
                this.mAttributeSet,
                R.styleable.DetailItemView,
                0, 0 );

        try {
            this.mDetailTitle = a.getString( R.styleable.DetailItemView_detail_title );

        } finally {
            a.recycle();
        }


        FrameLayout.LayoutParams params =
                new LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );
        this.setLayoutParams(params);

        ButterKnife.bind( this, rootView );
        this.addView( rootView );
        this.setDetailKey( this.mDetailTitle );
    }

    public void setDetailKey(String title) {
        this.mDetailKey.setText( title );
    }
}
