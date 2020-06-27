package com.payment.api.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class CatchMoneyReqEntity extends ReqEntity {

	@ApiModelProperty(value="토큰")
	@NotEmpty
	@NotNull
	private String token;

}
