package io.box.boxio.data;

import java.util.List;

public class CustomizedBox extends Box {

    private final Box.Color selectedColor;

    public CustomizedBox(int id, PredefinedSize size, Color color, List<Color> availableColors, Color selectedColor) {
        super(id, size, color, availableColors);
        this.selectedColor = selectedColor;
    }

    public CustomizedBox(Box box, Color selectedColor) {
        super(box.id, box.size, box.color, box.availableColors);
        this.selectedColor = selectedColor;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    @Override
    public String toString() {
        return "CustomizedBox{" +
                "selectedColor=" + selectedColor +
                ", id=" + id +
                ", size=" + size +
                ", color=" + color +
                ", availableColors=" + availableColors +
                '}';
    }
}
