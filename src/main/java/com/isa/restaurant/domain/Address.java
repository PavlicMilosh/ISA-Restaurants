package com.isa.restaurant.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_street_and_number")
    private String address;

    @Column(name = "address_postal_code")
    private String postalCode;

    @Column(name = "address_country")
    private String country;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (id != null ? !id.equals(address.id) : false) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (this.address != null ? !this.address.equals(address.address) : address.address != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }


    @Override
    public String toString()
    {
        return address + ", " + city + " , " + country;
    }
}


