package com.payment.api.req;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class FlyMoneyReqEntity extends ReqEntity {

	@ApiModelProperty(value="뿌릴 금액")
	@NumberFormat
	private int flyMoney;
	
	@ApiModelProperty(value="뿌릴 인원")
	@NumberFormat
	private int limitCnt;

}
