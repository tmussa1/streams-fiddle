package cscie55.hw7.problem2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;
    private List<Checkout> checkouts;
    private Reader reader;
    private CheckoutWriter checkoutWriter;

    @Before
    public void setUp() throws IOException {
        library = Library.getInstance();
        checkouts = library.getCheckouts();
        reader = new Reader();
        checkoutWriter = new CheckoutWriter();
        reader.read(new String("src\\main\\resources\\smaller_seatle.csv"));
    }

    @Test
    public void verifyLibraryAndCheckout(){
        assertNotNull(library);
        assertNotNull(checkouts);
        assertNotNull(reader);
        assertNotNull(checkoutWriter);
        assertEquals(2518, checkouts.size());
//        for(Checkout checkout : checkouts){
//            System.out.println(checkout.getTitle());
//        }
    }

    @Test
    public void testMostPopular() throws IOException {
        List<Checkout> mostPopular = library.getMostPopular(10);
        assertEquals(10, mostPopular.size());
    }

    @Test
    public void testAuthorBeginningWith() {
        List<Checkout> authorWithC = library.getAuthorsBeginsWith("C");
        authorWithC.stream()
                .forEach(checkout -> assertTrue(checkout.getAuthor().startsWith("C")));
        assertEquals(141, authorWithC.size());
    }

    @Test
    public void testGetPublishedYearIn(){
        List<Checkout> publishedIn2018 = library.getAllPublishedIn(2018);
        publishedIn2018.stream()
                .forEach(checkout -> assertTrue(checkout.getPublicationDate() == 2018));
        assertEquals(2518, publishedIn2018.size());
    }

    @Test
    public void testGetFilteredByPredicate(){
        Predicate<Checkout> authorPredicate = library.authorPredicate("Bauer, Marion Dane");
        List<Checkout> filteredByAuthor = library.getFilteredBy(authorPredicate);
        filteredByAuthor.stream()
                .forEach(checkout ->
                        assertTrue(checkout.getAuthor().equals("Bauer, Marion Dane")));
        assertEquals(2, filteredByAuthor.size());
    }

    @Test
    public void testWritingToFile() throws URISyntaxException {
        checkoutWriter.write("src\\main\\resources\\output.csv", checkouts);
    }

    @After
    public void cleanUp(){
        checkouts.clear();
    }
}
