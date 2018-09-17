package io.box.boxio.data;

public class BoxRequest {
    private final User user;
    private final CustomizedBox box;
    private final boolean printName;

    public BoxRequest(final User user, final CustomizedBox box, boolean printName) {
        this.user = user;
        this.box = box;
        this.printName = printName;
    }

    public User getUser() {
        return user;
    }

    public CustomizedBox getBox() {
        return box;
    }

    public boolean isPrintName() {
        return printName;
    }

    @Override
    public String toString() {
        return "BoxRequest{" +
                "user=" + user +
                ", box=" + box +
                ", printName=" + printName +
                '}';
    }
}
