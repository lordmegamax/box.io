package io.box.boxio.boxrequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import io.box.boxio.R;
import io.box.boxio.data.Box;

import java.util.ArrayList;
import java.util.List;

class BoxSizesAdapter extends ArrayAdapter<Box> {
    private final List<Box> boxes = new ArrayList<>(10);

    public BoxSizesAdapter(final Context context) {
        super(context, android.R.layout.simple_spinner_item);
    }

    public void setBoxes(final List<Box> boxes) {
        this.boxes.clear();
        this.boxes.addAll(boxes);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return boxes.size();
    }

    @Override
    public Box getItem(final int position) {
        return boxes.get(position);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        // FIXME: 15-Sep-18 change to ViewHolder pattern
        final Context context = parent.getContext();
        @SuppressLint("ViewHolder") final TextView textView = (TextView) LayoutInflater
                .from(context)
                .inflate(android.R.layout.simple_spinner_item, parent, false);

        final String formattedSize = getFormattedSize(position, context);
        textView.setText(formattedSize);

        return textView;
    }

    @Override
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        final TextView dropDownView = (TextView) super.getDropDownView(position, convertView, parent);
        dropDownView.setText(getFormattedSize(position, parent.getContext()));
        return dropDownView;
    }

    @NonNull
    private String getFormattedSize(int position, Context context) {
        final Box box = getItem(position);
        final Box.PredefinedSize item = box.getSize();
        return context.getString(R.string.box_size, item.getWidth(), item.getHeight(), item.getDepth());
    }
}
