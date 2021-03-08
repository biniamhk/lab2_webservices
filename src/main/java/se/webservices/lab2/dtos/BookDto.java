package se.webservices.lab2.dtos;

import java.sql.Date;

public class BookDto {
    private String ISBN13;
    private String titel;
    private String language;
    private Double pris;
    private Date utgivningsdatum;

    public BookDto(String ISBN13, String titel, String language, Double pris, Date utgivningsdatum) {
        this.ISBN13 = ISBN13;
        this.titel = titel;
        this.language = language;
        this.pris = pris;
        this.utgivningsdatum = utgivningsdatum;
    }

    public BookDto() {
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

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public void setUtgivningsdatum(Date utgivningsdatum) {
        this.utgivningsdatum = utgivningsdatum;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "ISBN13='" + ISBN13 + '\'' +
                ", title='" + titel + '\'' +
                ", language='" + language + '\'' +
                ", pris=" + pris +
                ", utgivningsDatum=" + utgivningsdatum +
                '}';
    }
}
