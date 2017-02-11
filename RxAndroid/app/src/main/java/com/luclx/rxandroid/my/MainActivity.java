package com.luclx.rxandroid.my;

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
import android.widget.Toast;

import com.luclx.rxandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.color_list)
    RecyclerView mColorList;

    ProgressBar mProgressBar;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mColorList = (RecyclerView) findViewById(R.id.color_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mColorList.setLayoutManager(new LinearLayoutManager(this));

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        // the old way
/*        Observable<ColorResponse> rxMyColor = apiService.getMyColorRx();
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
                    mColorList.setAdapter(new ColorAdapter(colorResponse.getmMyColorLst()));
                }
            }
        };

        rxMyColor.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> handleResponse(response), error -> handleError(error));
        .subscribe(this::handleResponse, this::handleError);*/

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(apiService.getMyColorRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> handleResponse(response), error -> handleError(error)));
        //or to get same result
//                .subscribe(this::handleResponse, this::handleError));


        // for normal retrofit
//        Call<ColorResponse> call = apiService.getMyColor();
//        call.enqueue(new Callback<ColorResponse>() {
//            @Override
//            public void onResponse(Call<ColorResponse> call, Response<ColorResponse> response) {
//                mProgressBar.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    mColorList.setAdapter(new ColorAdapter(response.body().getmMyColorLst()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ColorResponse> call, Throwable t) {
//                mProgressBar.setVisibility(View.GONE);
//            }
//        });
    }

    private void handleResponse(ColorResponse colorResponse) {
        mProgressBar.setVisibility(View.GONE);
        if (colorResponse != null) {
            mColorList.setAdapter(new ColorAdapter(colorResponse.getmMyColorLst()));
        }
    }

    private void handleError(Throwable error) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
        private ArrayList<MyColor> mMyColorList;

        public ColorAdapter(ArrayList<MyColor> mMyColorList) {
            this.mMyColorList = mMyColorList;
        }

        @Override
        public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
            return new ColorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ColorViewHolder holder, int position) {
            holder.mColorLabel.setText(mMyColorList.get(position).getmColorName());
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
            TextView mColorLabel;

            ColorViewHolder(View v) {
                super(v);
                mMain = (RelativeLayout) v.findViewById(R.id.item_main);
                mColorLabel = (TextView) v.findViewById(R.id.color_label);
                ButterKnife.bind(this, v);
            }
        }
    }
}
