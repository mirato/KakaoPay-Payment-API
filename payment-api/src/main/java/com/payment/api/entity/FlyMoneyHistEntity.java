package com.payment.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class FlyMoneyHistEntity {

	private String flyMoneyHistSeq;		// 뿌리기이력일련번호
	private String flyMoneySeq;			// 뿌리기일련번호
	private String userId;				// 사용자ID
	private String roomId;              // 대화방ID
	private String token;               // 토큰
	private String catchUserId;         // 받은사용자ID
	private int catchMoney;          // 받은금액
	private String insUser;             // 등록자
	private String insDate;             // 등록일시
	private String uptUser;             // 수정자
	private String uptDate;             // 수정일시
	
}
