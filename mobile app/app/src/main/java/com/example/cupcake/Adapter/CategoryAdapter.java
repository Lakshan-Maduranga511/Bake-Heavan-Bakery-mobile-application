package com.example.cupcake.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cupcake.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    private final List<String> mCategories;

    public CategoryAdapter(Context context, List<String> categories) {
        super(context, R.layout.list_item_category, categories);
        mContext = context;
        mCategories = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item_category, parent, false);

            holder = new ViewHolder();
            holder.textCategoryName = view.findViewById(R.id.text_category_name);
            holder.imageArrow = view.findViewById(R.id.image_arrow);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Set category name
        holder.textCategoryName.setText(mCategories.get(position));

        return view;
    }

    private static class ViewHolder {
        TextView textCategoryName;
        ImageView imageArrow;
    }
}
