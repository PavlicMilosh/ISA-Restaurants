package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guest")
@Indexed
public class Guest extends User
{

//    @OneToMany(mappedBy = "firstUser")
//    private Set<Friendship> friendships1;
//
//    @OneToMany(mappedBy = "secondUser")
//    private  Set<Friendship> friendships2;

    public Guest(String email, String password, String firstName, String lastName)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = false;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Guest))
            return false;

        User user = (Guest) o;

        if (id != null ? !id.equals(user.id) : false)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;

        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }


//    public Set<Friendship> getFriendships()
//    {
//        Set<Friendship> ret = new HashSet<>();
//        ret = this.friendships1;
//        ret.addAll(this.friendships2);
//        return ret;
//    }

}

