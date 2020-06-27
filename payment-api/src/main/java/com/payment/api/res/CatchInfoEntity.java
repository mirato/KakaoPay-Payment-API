package com.payment.api.res;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class CatchInfoEntity {
	
	@ApiModelProperty(value="받은 금액")
	@JsonInclude(Include.NON_NULL)
	private int catchMoney;
	
	@ApiModelProperty(value="받은 사용자ID")
	@JsonInclude(Include.NON_NULL)
	private String catchUserId;
}
