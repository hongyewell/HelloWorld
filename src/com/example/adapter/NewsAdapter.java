package com.example.adapter;

import java.util.List;

import com.example.helloworld.R;
import com.example.pojo.NewsItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter{
	private List<NewsItem> mList;
	private LayoutInflater inflater;
	
	public NewsAdapter(Context context,List<NewsItem> List){
		mList = List;
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
		View view ;
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			view = inflater.inflate(R.layout.news_item, null);
			viewHolder.title = (TextView) view.findViewById(R.id.news_title);
			viewHolder.author = (TextView) view.findViewById(R.id.news_author);
			viewHolder.time = (TextView) view.findViewById(R.id.news_time);
			view.setTag(viewHolder);
			
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		NewsItem newsItemItem = mList.get(position);
		
		viewHolder.title.setText(newsItemItem.getTitle());
		viewHolder.author.setText(newsItemItem.getAuthor());
		viewHolder.time.setText(newsItemItem.getTime());

		return view;
	}
	
	//内部类，用于对控件的实例进行缓存。
	class ViewHolder{
		
		TextView title;
		TextView author;
		TextView time;
		
	}

}
