package com.payment.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class HeaderEntity {

	@ApiModelProperty(value="요청한 사용자의 식별값")
	private String xUserId;
	
	@ApiModelProperty(value="요청한 사용자가 속한 대화방의 식별값")
	private String xRoomId;

}
