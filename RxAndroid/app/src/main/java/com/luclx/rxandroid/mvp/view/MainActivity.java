package com.luclx.rxandroid.mvp.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luclx.rxandroid.R;
import com.luclx.rxandroid.application.BaseApplication;
import com.luclx.rxandroid.base.BaseActivity;
import com.luclx.rxandroid.di.component.DaggerColorComponent;
import com.luclx.rxandroid.di.module.ColorModule;
import com.luclx.rxandroid.mvp.model.MyColor;
import com.luclx.rxandroid.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    SharedPreferences sharedPreferences;

    @Bind(R.id.color_list)
    RecyclerView mColorList;

    ColorAdapter colorAdapter;

//    CompositeDisposable compositeDisposable;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        initializeRecycleView();
        loadColors();

        /*compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(apiService.getMyColorRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> handleResponse(response), error -> handleError(error)));
        or to get same result
                .subscribe(this::handleResponse, this::handleError));

         for normal retrofit
        Call<ColorResponse> call = apiService.getMyColor();
        call.enqueue(new Callback<ColorResponse>() {
            @Override
            public void onResponse(Call<ColorResponse> call, Response<ColorResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    mColorList.setAdapter(new ColorAdapter(response.body().getmMyColorLst()));
                }
            }

            @Override
            public void onFailure(Call<ColorResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });*/

        sharedPreferences.edit().putString("text1", "Hello world!").commit();
        Toast.makeText(MainActivity.this, sharedPreferences.getString("text1", "NO EXIST"), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void initDaggerDependency() {
        DaggerColorComponent
                .builder()
                .applicationComponent(BaseApplication.getInstance().getApplicationComponent())
                .colorModule(new ColorModule(MainActivity.this))
                .build()
                .inject(MainActivity.this);
    }

    private void initializeRecycleView() {
        mColorList.setLayoutManager(new LinearLayoutManager(this));
        mColorList.setHasFixedSize(true);
        colorAdapter = new ColorAdapter();
        mColorList.setAdapter(colorAdapter);
    }

    private void loadColors() {
        mainPresenter.getColor();
    }

    /*private void handleResponse(ColorResponse colorResponse) {
        mProgressBar.setVisibility(View.GONE);
        if (colorResponse != null) {
            mColorList.setAdapter(new ColorAdapter(colorResponse.getmMyColorLst()));
        }
    }

    private void handleError(Throwable error) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        compositeDisposable.clear();
    }

    @Override
    public void onColorLoaded(List<MyColor> colors) {
        colorAdapter.setMyColorList(colors);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        colorAdapter.clearColors();
    }

    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
        private ArrayList<MyColor> mMyColorList = new ArrayList<>();

        public void setMyColorList(List<MyColor> colorList) {
            this.mMyColorList.addAll(colorList);
            notifyDataSetChanged();
        }

        public void clearColors() {
            this.mMyColorList.clear();
            notifyDataSetChanged();
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
            @Bind(R.id.item_main)
            RelativeLayout mMain;
            @Bind(R.id.color_label)
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
