package cscie55.hw7.problem2;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {

    private List<Checkout> checkouts;
    private static Library library;

    private Library(){
        this.checkouts = new ArrayList<>();
    }

    public static Library getInstance(){
        if(library == null){
            library = new Library();
        }
        return library;
    }

    /**
     * Using a multimap to find out the most frequented value.
     * Also using a flatmap to flat out collection of objects
     * @param limit
     * @return
     */
    public List<Checkout> getMostPopular(int limit){
        Map<String, MostPopular> mostPopularMap = new HashMap<>();
        Multimap<Integer, Checkout> checkoutMap = ArrayListMultimap.create();

        checkouts.stream()
                .forEach(checkout -> {
                    MostPopular mostPopular = null;
                    if (mostPopularMap.containsKey(checkout.getTitle())) {
                        int count = mostPopularMap.get(checkout.getTitle()).getCount();
                        mostPopular =  new MostPopular(checkout.getTitle(), count + 1);
                        mostPopularMap.replace(checkout.getTitle(), mostPopular);
                    } else{
                        mostPopular = new MostPopular(checkout.getTitle(), 1);
                        mostPopularMap.put(checkout.getTitle(), mostPopular);
                    }
                    checkoutMap.put(mostPopular.getCount(), checkout);
                });

        return checkoutMap.keys()
                .stream()
                .sorted(Comparator.reverseOrder())
                .map(count -> checkoutMap.get(count))
                .flatMap(Collection::stream)
                .distinct()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Checkout> getAuthorsBeginsWith(String s){
        return checkouts.stream()
                .filter(checkout -> checkout.getAuthor().startsWith(s))
                .collect(Collectors.toList());
    }

    public List<Checkout> getAllPublishedIn(int year){
        return checkouts.stream()
                .filter(checkout -> checkout.getPublicationDate() == year)
                .collect(Collectors.toList());
    }
    public List<Checkout> getFilteredBy(Predicate p){
        return (List<Checkout>) checkouts.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    public Collection<Checkout> addCheckout(Checkout checkout){
        this.checkouts.add(checkout);
        return checkouts;
    }

    /**
     * Predicate for filtering by author
     * @param author
     * @return
     */
    public Predicate<Checkout> authorPredicate(String author){
        return new Predicate<Checkout>() {
            @Override
            public boolean test(Checkout checkout) {
                return checkout.getAuthor().equals(author);
            }
        };
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }
}
