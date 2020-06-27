package com.payment.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class TokenMstEntity {

	private String token;               // 토큰
	private String userId;				// 사용자ID
	private String pubTime;             // 발급시간
	private String expTime;             // 만료시간
	
	private int diffTime;         	// diff시간
	
}
