package com.smd.studio.adcashdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smd.studio.adcashdemo.AsyncTask.ImageDownloaderTask;
import com.smd.studio.adcashdemo.Model.Deal;
import com.smd.studio.adcashdemo.R;

import java.util.ArrayList;

/**
 * Created by Doychev on 30.3.2015 г..
 */
public class DealListAdapter extends BaseAdapter {

    private ArrayList dealsData;
    private LayoutInflater layoutInflater;

    public DealListAdapter(Context context, ArrayList dealsData) {
        this.dealsData = dealsData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dealsData.size();
    }

    @Override
    public Object getItem(int position) {
        return dealsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.deal_list_item, null);
            holder = new ViewHolder();
            holder.dealImage = (ImageView) convertView.findViewById(R.id.dealImage);
            holder.dealTitle = (TextView) convertView.findViewById(R.id.dealTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Deal deal = (Deal) dealsData.get(position);
        if (holder.dealImage != null) {
            new ImageDownloaderTask(holder.dealImage).execute(deal.getImageLink());
        }
        holder.dealTitle.setText(deal.getTitle());
        return convertView;
    }

    static class ViewHolder {
        ImageView dealImage;
        TextView dealTitle;
    }
}
