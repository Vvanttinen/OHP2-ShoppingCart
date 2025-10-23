package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

  @Test
  void calculateTotal() {
    ShoppingCart cart = new ShoppingCart();
    cart.items.add(new Item(10.0, 2)); // 20.0
    cart.items.add(new Item(5.5, 4));  // 22.0
    cart.items.add(new Item(3.0, 3));  // 9.0

    double expectedTotal = 51.0;
    assertEquals(expectedTotal, cart.calculateTotal(), 0.001);
  }
}
