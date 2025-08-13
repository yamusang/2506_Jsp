package org.iclass.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDto {
	private String car;
	private String model;
	private Integer volume;
	private Integer weight;
	private Integer co2;
	
}
