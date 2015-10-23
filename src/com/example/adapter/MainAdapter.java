package com.example.adapter;

import java.util.List;

import com.example.helloworld.R;
import com.example.pojo.MainItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter{
	
	private List<MainItem> mList;
	private LayoutInflater inflater;
	
	public MainAdapter(Context context,List<MainItem> list){
		mList = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder viewHolder;
		MainItem item = mList.get(position);
		//当convertView为空时，创建一个ViewHolder对象，并将控件的实例都存放在ViewHolder里，然后调用View的setTag()方法，将ViewHolder对象存储在View中。
		if (convertView == null) {
			view = inflater.inflate(R.layout.main_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.imgView);
			viewHolder.textView = (TextView) view.findViewById(R.id.tvName);
			view.setTag(viewHolder);
			
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.imageView.setImageResource(item.getImagView());
		viewHolder.textView.setText(item.getTvName());
		return view;
	}
	
	//内部类ViewHolder，用于对控件的实例进行缓存。
	class ViewHolder{
		ImageView imageView;
		TextView textView;
	}

}
