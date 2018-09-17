package io.box.boxio.data;

import java.util.List;

public class Box {
    protected final int id;
    protected final PredefinedSize size;
    protected final Color color;

    protected final List<Color> availableColors;

    // Color selected color

    public Box(int id, PredefinedSize size, Color color, List<Color> availableColors) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.availableColors = availableColors;
    }

    public int getId() {
        return id;
    }

    public PredefinedSize getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public List<Color> getAvailableColors() {
        return availableColors;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", size=" + size +
                ", color=" + color +
                ", availableColors=" + availableColors +
                '}';
    }

    public static enum Color {
        RED, BLUE, YELLOW, PURPLE, GREEN, ORANGE
    }

    public static class PredefinedSize {
        private final int width;
        private final int height;
        private final int depth;

        public PredefinedSize(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getDepth() {
            return depth;
        }

        @Override
        public String toString() {
            return "PredefinedSize{" +
                    "width=" + width +
                    ", height=" + height +
                    ", depth=" + depth +
                    '}';
        }
    }
}
