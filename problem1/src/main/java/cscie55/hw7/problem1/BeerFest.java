/* Adapted from code in "Java Programming", Chapter 20
   by Yakov Fain
 */
 package cscie55.hw7.problem1;
 
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;

/**
 * Note: This class will report errors on opening.
 * That is because the TODOs are not complete statements
 * Your job is to complete them.
 */
public class BeerFest {
    public  static class Beer {
	public final String name;
	public final String country;
	private float price;

	public Beer(String name, String country,float price){
	    this.name=name;
	    this.country=country;
	    this.price=price;
	}
	
	public String toString(){
	    return "Country: " + country +  " Name: " + name + ", price: " + price;
	}
	public float getPrice() {
	    return price;
	}
	public void setPrice(float price) {
	    this.price = price;
	}
    }
    public static List<Beer> beerQuery(List<Beer> beerList, Predicate <Beer> predicate) {
        List<Beer> result = beerList.stream()
	    // TODO:  Select Beer's that meet the predicate
        return result;
    }
    static List<Beer> loadCellar(){
        List<Beer> beerStock = new ArrayList<>();

        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Bud Light", "USA", 5.00f));
        beerStock.add(new Beer("Yuengling", "USA", 5.50f));
        beerStock.add(new Beer("Leffe Blonde", "Belgium", 8.75f));
        beerStock.add(new Beer("Chimay Blue", "Belgium", 10.00f));
        beerStock.add(new Beer("Brooklyn Lager", "USA", 8.25f));

        return beerStock;
    }
    static Predicate<Beer> priceRangeQuery(...) {
	// TODO: compose and return a Predicate that will
	//       express the selection criterion
    }
    static Predicate<Beer> countryQuery(...) {
	// TODO: compose and return a Predicate that will
	//       express the selection criterion
    }
    public static void main(String argv[]) {
	List<Beer> beerList = loadCellar();
	// TODO: Call beerQuery with a predicate for selecting a country
	beerQuery(beerList, countryQuery(...)).forEach(System.out::println);
	// TODO: Call beerQuery with a predicate for a price range
	beerQuery(beerList, priceRangeQuery(...)).forEach(System.out::println);
    }
}
