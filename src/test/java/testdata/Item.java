package testdata;

public class Item {
    private final String name;
    private final String price;
    private final String rating;
    private final String others;

    public Item(String name, String price, String rating, String others) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.others = others;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getRating() { return rating; }
    public String getOthers() { return others; }

    @Override
    public String toString() {
        return "Item{name='" + name + "', price='" + price + "', rating='" + rating + "', others='" + others + "'}";
    }
}