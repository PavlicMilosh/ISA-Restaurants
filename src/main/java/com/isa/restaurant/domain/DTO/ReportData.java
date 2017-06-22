package com.isa.restaurant.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Created by Milos on 22-Jun-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class ReportData
{
    Date time;
    Double value;
}
