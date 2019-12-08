package cscie55.hw7.foodservice;

import cscie55.hw7.api.Shop;
import cscie55.hw7.api.Slicer;
import cscie55.hw7.impl.Address;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cscie55.hw7.utils.NumUtil;
import cscie55.hw7.writer.MenuWriter;

public class TakeOutShop implements Shop<Dish> {


    private List<Dish> menu = new ArrayList<>();
    String url = "https://cscie-55-out.s3.amazonaws.com/menu7.json";
    private static int ordersReceivedId = 0;
    private Map<Integer, ArrayDeque<FoodOrder>> ordersReadyOut = new HashMap<>();
    private Map<Integer, ArrayDeque<FoodOrder>> ordersIn = new HashMap<>();
    private List<Chef> chefs = new ArrayList<>();
    private List<DeliveryPerson> deliveryPeople = new ArrayList<>();
    private static final int NUMBER_CHEFS_ON_STAFF = 10;
    private static final int DELIVERY_PERSONS_ON_STAFF = 7;
    private String[] names = {"James Beard", "Melissa Clark", "Gordon Ramsey", "Ayesha Curry", "Anthony Bourdain", "Julia Child", "Gaida De Laurentis", "Jamie Oliver", "Jiro Ono", "Alice Waters"};
    // TODO: add a variable of Type 'Slicer' that sets the Parameter Types of your abstract 'Slicer' interface


    /**
     * Constructor. Here we initialize the menu so we have something to sell
     */
    public TakeOutShop() {
        // TODO: use Jackson methods to Read Dishes from Amazon S3 location in variable 'url'
        // see testReadStreamToJson() in FoodServiceTest class for sample
        //  populate the initial menu by converting the data returned into Dish[]
        // replace below
        Dish[] menuArray = {
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH)
        };

        /***** BELOW HERE THIS METHOD SHOULD REMAIN AS IS *******************/
        Arrays.stream(menuArray).forEach(menu::add);

        // create some chefs to run the shop
        int numChefs = NUMBER_CHEFS_ON_STAFF;
        while (numChefs > 0) {
            chefs.add(new Chef(names[numChefs - 1], ordersIn, ordersReadyOut));
            numChefs--;
        }

        int deliverors = DELIVERY_PERSONS_ON_STAFF;
        while (deliverors > 0) {
            deliveryPeople.add(new DeliveryPerson("Thomas", "Sims" + deliverors, new Address(2, 1, deliverors), this));
            deliverors--;
        }
    }

    @Override
    public List<Dish> getMenu() {
        return menu;
    }

    public List<Dish> getVegetatianMenu() {
        //TODO: filter the menu items and return a subset consisting of only vegetarian dishes
        return null;// TODO...remove null and return veggie menu
    }

    public Map<Boolean, List<Dish>> partitionByCalorieLimit(int calorie) {
        // TODO: write a method that uses passed 'calorie' param to divide the menu.
        //  The resulting map has 2 keys: true and false.
        //  The List associated with the true key will contain all Dishes less than 'calorie'
        // the list associated w/ false has those Dishes over the limit.
        return null;
    }


    /**
     * @param dishes - List<Dish>
     * @param start
     * @param end
     * @return - List<Dish>
     */

    //TODO: here implement public 'slicer' method
	
	
	
	
	
    @Override
    public void setNewMenu(List<Dish> newMenu) {
        menu = newMenu;
    }

    /**
     * This method should use the MenuWriter's publish method to write a receipt for a foodOrder
     *
     * @param foodOrder
     */
    public void generateReceipt(FoodOrder foodOrder) {
        List<Dish> items = foodOrder.getItems();
        Address addr = foodOrder.getAddress();
        String fileName = addr.toString() + ".json";
        MenuWriter.publish(fileName, items);
    }

    /**
     * This method allows the menu to grow.
     *
     * @param dish
     */
    public void addMenuItem(Dish dish) {
        menu.add(dish);
    }

    /**
     * This method adds a list of dishes to the current menu
     *
     * @param dishes
     */
    public void addMenuItemList(List<Dish> dishes) {
        menu.addAll(dishes);
    }


    /**
     * This method receives the Dish[] from the world and uses that and the addresses to create a FoodOrder object.
     * It stores the order in a List in a Map, using the address' floorId as the Map's key
     * It calls processOrder to get the Chef working
     * and it returns a reference to the FoodOrder to the source.
     *
     * @param address
     * @param dishes
     * @return
     */
    @Override
    public FoodOrder placeOrder(Address address, Dish[] dishes) {
        FoodOrder order = new FoodOrder(address, dishes);
        order.setOrderAckId(++ordersReceivedId);
        int destinationFloor = address.getFloorId();
        ordersIn.computeIfAbsent(destinationFloor, ArrayDeque::new);
        ArrayDeque<FoodOrder> list = ordersIn.get(destinationFloor);
        list.addLast(order);
        return order;
    }

}

