package com.payment.api.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class FlyMoneyResEntity extends ResEntity {
	
	@ApiModelProperty(value="토큰")
	@JsonInclude(Include.NON_NULL)
	private String token;
}
