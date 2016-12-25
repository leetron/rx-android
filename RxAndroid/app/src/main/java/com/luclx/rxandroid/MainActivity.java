package com.luclx.rxandroid;

import android.content.Context;
import android.databinding.tool.reflection.Callable;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.color_list)
    RecyclerView mColorList;

    ProgressBar mProgressBar;

    ColorAdapter mAdapter;

    Observable<ColorResponse> rxMyColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mColorList = (RecyclerView) findViewById(R.id.color_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mColorList.setLayoutManager(new LinearLayoutManager(this));

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Observable<ColorResponse> rxMyColor = apiService.getMyColorRx();
        Subscriber<ColorResponse> mObserver = new Subscriber<ColorResponse>() {
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
        };

        rxMyColor.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);

        rxMyColor.unsafeSubscribe(mObserver);


//        Observable myColorObservable = Observable.fromCallable(new java.util.concurrent.Callable() {
//            @Override
//            public Object call() throws Exception {
//                return apiService.getMyColorRx();
//            }
//        });

//        rxMyColor = myColorObservable.subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Observer() {
//                                            @Override
//                                            public void onCompleted() {
//
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//
//                                            }
//
//                                            @Override
//                                            public void onNext(Object o) {
//
//                                            }
//                                        });

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

        rxMyColor.unsubscribeOn(Schedulers.newThread());
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
