package com.isa.restaurant.domain.DTO.authentication;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Created by Milos on 23-May-17.
 */
public class ModelBaseDTO implements Serializable
{
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }

}
