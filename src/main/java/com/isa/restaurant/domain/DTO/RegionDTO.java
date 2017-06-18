package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Q on 28-May-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class RegionDTO
{
    private String name;
    private String color;
    private Long id;


    public RegionDTO(Region region)
    {
        this.name = region.getName();
        this.color = region.getColor();
        this.id = region.getId();
    }
}
