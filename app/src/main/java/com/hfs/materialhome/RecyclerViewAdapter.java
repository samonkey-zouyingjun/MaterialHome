/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hfs.materialhome;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfs.materialhome.adapter.GridLayoutAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends GridLayoutAdapter<ViewModel> implements View.OnClickListener {


    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(List list) {
        super(list);
    }

    public RecyclerViewAdapter(List list, int headerViewRes) {
        super(list, headerViewRes);
    }

    public RecyclerViewAdapter(List list, int headerViewRes, int footerViewRes) {
        super(list, headerViewRes, footerViewRes);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        view.setOnClickListener(this);
        return new RecyclerViewAdapter.ItemViewHolder(view);
    }

    @Override
    protected void onBindHeaderView(View headerView) {


    }

    @Override
    protected void onBindFooterView(View footerView) {

    }

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, ViewModel item) {
        RecyclerViewAdapter.ItemViewHolder itemViewHolder = (RecyclerViewAdapter.ItemViewHolder) holder;
        itemViewHolder.text.setText(item.getText());
        itemViewHolder.image.setImageBitmap(null);
        Picasso.with(itemViewHolder.image.getContext()).load(item.getImage()).into(itemViewHolder.image);
        holder.itemView.setTag(item);

    }

    @Override
    public void onClick(final View v) {
        onItemClickListener.onItemClick(v, (ViewModel) v.getTag());
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, ViewModel viewModel);

    }
}
