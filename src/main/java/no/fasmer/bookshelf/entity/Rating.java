package no.fasmer.bookshelf.entity;

import java.text.DecimalFormat;
import java.text.ParseException;

public enum Rating {
    
    RATING_1_0("1.0"),
    RATING_1_5("1.5"),
    RATING_2_0("2.0"),
    RATING_2_5("2.5"),
    RATING_3_0("3.0"),
    RATING_3_5("3.5"),
    RATING_4_0("4.0"),
    RATING_4_5("4.5"),
    RATING_5_0("5.0");
    
    private final String rating;
    
    private Rating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }
    
    public Number getRatingAsDouble() {
        try {
            return new DecimalFormat("#.#").parse(rating).doubleValue();
        } catch (ParseException ex) {
            return null;
        }
    }
    
}
