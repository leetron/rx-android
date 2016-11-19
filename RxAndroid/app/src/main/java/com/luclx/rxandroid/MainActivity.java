package com.luclx.rxandroid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.color_list)
    RecyclerView mColorList;

    ProgressBar mProgressBar;

    ColorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mColorList = (RecyclerView) findViewById(R.id.color_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mColorList.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Observable<ColorResponse> rxMyColor = apiService.getMyColorRx();
        rxMyColor.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ColorResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(ColorResponse colorResponse) {
                        mProgressBar.setVisibility(View.GONE);
                        if (colorResponse != null) {
                            mColorList.setAdapter(new ColorAdapter(MainActivity.this, colorResponse.getmMyColorLst()));
                        }
                    }
                });
//        Call<ColorResponse> call = apiService.getMyColor();
//        call.enqueue(new Callback<ColorResponse>() {
//            @Override
//            public void onResponse(Call<ColorResponse> call, Response<ColorResponse> response) {
//                mProgressBar.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    mColorList.setAdapter(new ColorAdapter(MainActivity.this, response.body().getmMyColorLst()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ColorResponse> call, Throwable t) {
//                mProgressBar.setVisibility(View.GONE);
//            }
//        });
    }

    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
        private Context mContext;
        private ArrayList<MyColor> mMyColorList;

        public ColorAdapter(Context context, ArrayList<MyColor> mMyColorList) {
            this.mContext = context;
            this.mMyColorList = mMyColorList;
        }

        @Override
        public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
            return new ColorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ColorViewHolder holder, int position) {
            holder.mColorLable.setText(mMyColorList.get(position).getmColorName());
            holder.mMain.setBackgroundColor(Color.parseColor((mMyColorList.get(position).getmHexColor())));
        }

        @Override
        public int getItemCount() {
            return this.mMyColorList.size();
        }

        public class ColorViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_main)
            RelativeLayout mMain;
            @BindView(R.id.color_label)
            TextView mColorLable;

            ColorViewHolder(View v) {
                super(v);
                mMain = (RelativeLayout) v.findViewById(R.id.item_main);
                mColorLable = (TextView) v.findViewById(R.id.color_label);
                ButterKnife.bind(this, v);
            }
        }
    }
}
