package testdata;

import java.util.List;

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

    /**
     * Helper method to print items and their details
     * @param items list of items to print
     */
    public static void printItems(List<Item> items) {
        int num = 1;
        for (Item item : items) {
            System.out.println("\n" + num + ")");
            System.out.println("Item: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            num++;
        }
        System.out.println("\nTotal items found: " + items.size());
    }


}