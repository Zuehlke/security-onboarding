package com.zuehlke.zrs.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by nesp on 21-Sep-16.
 */
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;
    @Column(name = "LASTNAME", nullable = false)
    private String lastName;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(nullable=false)
    private Boolean disabled;

    protected Employee() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public Employee(String firstName, String lastName, String title, boolean disabled) {
        this(null, firstName, lastName, title, disabled);
    }

    public Employee(Long id, String firstName, String lastName, String title, boolean disabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.disabled = disabled;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
