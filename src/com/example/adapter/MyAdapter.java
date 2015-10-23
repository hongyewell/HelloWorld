package com.example.adapter;

import java.util.List;

import com.example.helloworld.R;
import com.example.pojo.People;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	
	private List<People> mList;
	private LayoutInflater inflater;
	
	public MyAdapter(Context context,List<People> list){
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
		//���convertViewΪ�գ���ʹ��LayoutInflaterȥ���ز��֣������Ϊ����ֱ�Ӷ�convertView��������
		if (convertView == null) {
			 convertView =inflater.inflate(R.layout.item, null);
		}	
		ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		TextView content = (TextView) convertView.findViewById(R.id.tv_content);
		People iPeople = mList.get(position);
		imageView.setImageResource(iPeople.peopleImageId);
		title.setText(iPeople.peopleTitle);
		content.setText(iPeople.peopleContent);
		return convertView;
	}

}
