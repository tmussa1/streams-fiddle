package cscie55.hw7.problem2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public List<Checkout> getMostPopular(int limit){
        return checkouts.stream()
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
