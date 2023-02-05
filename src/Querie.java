public class Querie {
    private Update best_bid;
    private Update best_ask;

    public Querie() {
    }

    public Querie(Update best_bid, Update best_ask) {
        this.best_bid = best_bid;
        this.best_ask = best_ask;
    }

    public Update getBest_bid() {
        return best_bid;
    }

    public void setBest_bid(Update best_bid) {
        this.best_bid = best_bid;
    }

    public Update getBest_ask() {
        return best_ask;
    }

    public void setBest_ask(Update best_ask) {
        this.best_ask = best_ask;
    }

    @Override
    public String toString() {
        return "Querie{" +
                "best_bid=" + best_bid +
                ", best_ask=" + best_ask +
                '}';
    }
}
