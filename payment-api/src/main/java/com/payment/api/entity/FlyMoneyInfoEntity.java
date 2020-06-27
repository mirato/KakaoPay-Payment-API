package com.payment.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class FlyMoneyInfoEntity {
	
	private String flyMoneySeq;			// 뿌리기일련번호
	private String userId;				// 사용자ID
	private String roomId;              // 대화방ID
	private String token;               // 토큰
	private String status;              // 상태 1:대기중, 2:처리중, 3:종료
	private int flyMoney;            	// 뿌린금액
	private int limitCnt;            	// 제한인원
	private int leftMoney;           	// 남은금액
	private int leftCnt;	           	// 남은인원
	private String insUser;             // 등록자
	private String insDate;             // 등록일시
	private String uptUser;             // 수정자
	private String uptDate;             // 수정일시
	
	private int limitDay;            	// 조회일자

}
