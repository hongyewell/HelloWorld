package com.example.helloworld;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PeopleAdapter extends BaseAdapter{
	private List<PeopleItem> mList;
	private LayoutInflater inflater;
	
	public PeopleAdapter(Context context,List<PeopleItem> List){
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
			viewHolder.peopleName = (TextView) view.findViewById(R.id.people_name);	
			viewHolder.peopleAge = (TextView) view.findViewById(R.id.people_age);
			view.setTag(viewHolder);
			
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		PeopleItem peopleItem = mList.get(position);
		viewHolder.peopleName.setText(peopleItem.name);
		viewHolder.peopleAge.setText(peopleItem.age);
		return view;
	}
	
	//内部类，用于对控件的实例进行缓存。
	class ViewHolder{
		TextView peopleName;
		TextView peopleAge;
		
	}

}
