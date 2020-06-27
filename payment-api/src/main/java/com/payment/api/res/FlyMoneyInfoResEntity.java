package com.payment.api.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class FlyMoneyInfoResEntity extends ResEntity {
	
	@ApiModelProperty(value="뿌린 시각")
	@JsonInclude(Include.NON_NULL)
	private String flyTime;
	
	@ApiModelProperty(value="뿌린 금액")
	@JsonInclude(Include.NON_NULL)
	private int flyMoney;
	
	@ApiModelProperty(value="받기 완료된 금액")
	@JsonInclude(Include.NON_NULL)
	private int leftMoney;
	
	@ApiModelProperty(value="받은 정보 리스트")
	@JsonInclude(Include.NON_NULL)
	private List<CatchInfoEntity> catchInfoList;

}
