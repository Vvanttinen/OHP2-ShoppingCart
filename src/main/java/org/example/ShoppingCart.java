package org.example;

import java.util.*;

public class ShoppingCart {
  List<Item> items = new ArrayList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ShoppingCart cart = new ShoppingCart();

    System.out.println(
        "Select language:\n" +
            "1. English\n" +
            "2. Finnish\n" +
            "3. Swedish\n" +
            "4. Japanese"
    );

    int choice = sc.nextInt();
    String language = "";
    String country = "";

    switch (choice) {
      case 1:
        language = "en";
        country = "US";
        break;
      case 2:
        language = "fi";
        country = "FI";
        break;
      case 3:
        language = "sv";
        country = "SE";
        break;
      case 4:
        language = "ja";
        country = "JP";
        break;
      default:
        System.out.println("Invalid choice, defaulting to English.");
        language = "en";
        country = "US";
    }

    Locale locale = new Locale(language, country);
    ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", locale);

    System.out.println(bundle.getString("greeting"));
    System.out.println(bundle.getString("prompt.items"));
    int itemAmount = sc.nextInt();

    for (int i = 1; i <= itemAmount; i++) {
      System.out.print(bundle.getString("item.price") + " " + i + ": ");
      double itemPrice = sc.nextDouble();
      System.out.println();
      System.out.print(bundle.getString("item.quantity") + " " + i + ": ");
      int quantity = sc.nextInt();
      System.out.println();
      cart.items.add(new Item(itemPrice, quantity));
    }

    System.out.println(bundle.getString("total.amount") + ": " + String.format("%.2f", cart.calculateTotal()));
    sc.close();
  }

  public double calculateTotal() {
    double total = 0.0;
    for (Item item : items) {
      total += item.price * item.quantity;
    }
    return total;
  }
}
