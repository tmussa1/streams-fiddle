package cscie55.hw7.api;

import cscie55.hw7.foodservice.Dish;

import java.util.List;

public interface Slicer {
    List<Dish> slicer(List<Dish> dishes, int start, int end);
}
