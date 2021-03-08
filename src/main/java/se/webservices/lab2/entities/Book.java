package se.webservices.lab2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    private String ISBN13;
    @Column
    private String titel;
    @Column
    private String language;
    @Column
    private Double pris;
    @Column
    private Date utgivningsdatum;


    public Book(String ISBN13, String titel, String language, Double pris, Date utgivningsdatum) {
        this.ISBN13 = ISBN13;
        this.titel = titel;
        this.language = language;
        this.pris = pris;
        this.utgivningsdatum = utgivningsdatum;
    }

    public Book() {
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String title) {
        this.titel = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String lang) {
        this.language = lang;
    }

    public Double getPris() {
        return pris;
    }

    public void setPris(Double pris) {
        this.pris = pris;
    }

    public Date getUtgivningsdatum() {
        return utgivningsdatum;
    }

    public void setUtgivningsdatum(Date utgivningsDatum) {
        this.utgivningsdatum = utgivningsDatum;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN13='" + ISBN13 + '\'' +
                ", title='" + titel + '\'' +
                ", språk='" + language + '\'' +
                ", pris=" + pris +
                ", utgivningsDatum=" + utgivningsdatum +
                '}';
    }
}
