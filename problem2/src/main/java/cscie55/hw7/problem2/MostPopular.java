package cscie55.hw7.problem2;

/**
 * Utility class convenient for traversing map
 */
public class MostPopular {
    private String title;
    private int count;

    public MostPopular(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
