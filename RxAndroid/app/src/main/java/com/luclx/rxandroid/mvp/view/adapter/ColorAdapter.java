package com.luclx.rxandroid.mvp.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luclx.rxandroid.R;
import com.luclx.rxandroid.mvp.model.MyColor;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lucle on 2/20/17.
 */

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