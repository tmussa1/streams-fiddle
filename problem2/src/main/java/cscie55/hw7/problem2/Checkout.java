package cscie55.hw7.problem2;

import java.util.Objects;

public class Checkout implements Comparable<Checkout>{

    private String title;
    private String author;
    private int publicationDate;

    @Override
    public int compareTo(Checkout o) {
        return this.title.compareTo(o.getTitle());
    }

    private enum Kind{PHYSICAL, DIGITAL, OTHER};
    private Kind kind;

    public Checkout() {
    }

    public Checkout(String title, String author, int publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return publicationDate == checkout.publicationDate &&
                Objects.equals(title, checkout.title) &&
                Objects.equals(author, checkout.author) &&
                kind == checkout.kind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationDate, kind);
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ", kind=" + kind +
                '}';
    }
}
