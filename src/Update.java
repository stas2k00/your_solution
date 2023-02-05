public class Update {
    private int price;
    private int size;
    private String AskBid;

    public Update() {
    }

    public Update(int price, int size, String askBid) {
        this.price = price;
        this.size = size;
        AskBid = askBid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAskBid() {
        return AskBid;
    }

    public void setAskBid(String askBid) {
        AskBid = askBid;
    }

    @Override
    public String toString() {
        return this.price + "," + this.size + "\n";
    }
    public String toStringSize() {
        return this.size + "\n";
    }
}
