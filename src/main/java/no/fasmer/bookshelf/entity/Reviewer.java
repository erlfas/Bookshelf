package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reviewer")
public class Reviewer implements Serializable {

    @SequenceGenerator(name = "Reviewer_Gen", sequenceName = "Reviewer_Seq")
    @Id
    @GeneratedValue(generator = "Reviewer_Gen")
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
