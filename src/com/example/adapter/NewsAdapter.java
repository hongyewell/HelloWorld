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
			view = inflater.inflate(R.layout.people_item, null);
			viewHolder.content = (TextView) view.findViewById(R.id.people_name);	
			viewHolder.title = (TextView) view.findViewById(R.id.people_age);
			view.setTag(viewHolder);
			
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		NewsItem newsItemItem = mList.get(position);
		viewHolder.content.setText(newsItemItem.getTitle());
		viewHolder.title.setText(newsItemItem.getContent());
		return view;
	}
	
	//�ڲ��࣬���ڶԿؼ���ʵ�����л��档
	class ViewHolder{
		TextView title;
		TextView content;
		
	}

}