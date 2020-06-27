package com.duylong.animemangacollection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duylong.animemangacollection.R;
import com.exblr.dropdownmenu.DropdownListItem;

import java.util.List;

public class CategoryFilterAdapter extends BaseAdapter {
    private Context context;
    private List<DropdownListItem> categoryList;
    private LayoutInflater layoutInflater;

    public CategoryFilterAdapter(Context context, List<DropdownListItem> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return this.categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public DropdownListItem setSelectedItem(int position) {
        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).setSelected(position == i);
        }
        notifyDataSetChanged();
        return categoryList.get(position);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = this.layoutInflater.inflate(R.layout.category_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.bind(i);

        return view;
    }

    private class ViewHolder {
        private TextView mTextView;

        public ViewHolder(View view) {
            mTextView = (TextView) view.findViewById(R.id.category_title);
        }

        public void bind(int position) {
            DropdownListItem item = categoryList.get(position);
            mTextView.setText(item.getText());
            if (item.isSelected()) {
                mTextView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                mTextView.setBackgroundResource(R.drawable.rounded_box_selected);
            } else {
                mTextView.setTextColor(context.getResources().getColor(R.color.textDefault));
                mTextView.setBackgroundResource(R.drawable.rounded_box_normal);
            }
        }
    }
}
