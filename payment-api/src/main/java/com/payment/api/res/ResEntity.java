package com.payment.api.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResEntity {

	@ApiModelProperty(value="결과 코드")
	private String resultCd;

	@ApiModelProperty(value="결과 메시지")
	private String resultMsg;
	
	@ApiModelProperty(value="응답시간(yyyyMMddHHmmss)")
	private String resDate;
}
