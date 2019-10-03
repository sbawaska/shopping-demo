package io.projectriffdemo.homepage;

import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private String userId;
    private List<Merchandise> items;

    public Cart(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Merchandise> getItems() {
        return items;
    }

    public void setItems(List<Merchandise> items) {
        this.items = items;
    }

    public void addItem(Merchandise item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public void removeItem(String itemId) {
        List<Merchandise> newList = new ArrayList<>();
        Flux<Merchandise> merchandiseFlux = Flux.fromIterable(this.items);
        merchandiseFlux.filter(merchandise -> !merchandise.getItemId().equals(itemId))
                .subscribe(newList::add);
        this.items = newList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId='" + userId + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return userId.equals(cart.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
