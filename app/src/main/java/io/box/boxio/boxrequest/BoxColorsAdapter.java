package io.box.boxio.boxrequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import io.box.boxio.data.Box;

import java.util.ArrayList;
import java.util.List;

class BoxColorsAdapter extends ArrayAdapter<Box.Color> {
    private final List<Box.Color> colors = new ArrayList<>();

    public BoxColorsAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_item);
    }

    public void setBox(Box box) {
        colors.clear();

        if (box != null && box.getAvailableColors() != null) {
            final List<Box.Color> availableColors = box.getAvailableColors();
            colors.addAll(availableColors);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Box.Color getItem(int position) {
        return colors.get(position);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        // FIXME: 16-Sep-18 change to ViewHolder pattern
        final Context context = parent.getContext();
        @SuppressLint("ViewHolder") final TextView textView = (TextView) LayoutInflater
                .from(context)
                .inflate(android.R.layout.simple_spinner_item, parent, false);

        textView.setText(getItem(position).toString());

        return textView;
    }

    @Override
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        final TextView dropDownView = (TextView) super.getDropDownView(position, convertView, parent);
        dropDownView.setText(getItem(position).toString());
        return dropDownView;
    }
}
