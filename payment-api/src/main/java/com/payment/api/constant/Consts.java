package com.payment.api.constant;

import org.springframework.stereotype.Component;

@Component
public class Consts {
	
	public static final String FLY_MONEY_STATUS_READY 	 	 = "R";
	public static final String FLY_MONEY_STATUS_PROCESSING 	 = "P";
	public static final String FLY_MONEY_STATUS_END 	 	 = "E";

	public static final String FLY_MONEY_RES_SUCCESS_CD  = "0000";
	public static final String FLY_MONEY_RES_SUCCESS_MSG = "정상 처리되었습니다.";
	
	public static final String FLY_MONEY_RES_HEADER_ERROR_CD  = "0010";
	public static final String FLY_MONEY_RES_HEADER_ERROR_MSG = "유효하지 않은 헤더데이터 입니다.";
	
	public static final String FLY_MONEY_RES_PARAM_ERROR_CD  = "0011";
	public static final String FLY_MONEY_RES_PARAM_ERROR_MSG = "유효하지 않은 데이터 입니다.";
	
	public static final String FLY_MONEY_RES_NO_DATA_CD  = "0012";
	public static final String FLY_MONEY_RES_NO_DATA_MSG = "데이터가 없습니다.";
	
	public static final String FLY_MONEY_RES_TIME_EXP_CD  = "0013";
	public static final String FLY_MONEY_RES_TIME_EXP_MSG = "기간이 만료되었습니다.";
	
	public static final String FLY_MONEY_RES_DUP_CD  = "0014";
	public static final String FLY_MONEY_RES_DUP_MSG = "이미 참여한 사용자 입니다.";
	
	public static final String FLY_MONEY_RES_MYSELF_CD  = "0015";
	public static final String FLY_MONEY_RES_MYSELF_MSG = "본인은 참여할 수 없습니다.";
	
	public static final String FLY_MONEY_RES_STATUS_END_CD  = "0016";
	public static final String FLY_MONEY_RES_STATUS_END_MSG = "종료되었습니다.";
	
	public static final String FLY_MONEY_RES_STATUS_PROCESSING_CD  = "0017";
	public static final String FLY_MONEY_RES_STATUS_PROCESSING_MSG = "처리중입니다.";
	
	public static final String FLY_MONEY_RES_SYS_ERROR_CD  = "9999";
	public static final String FLY_MONEY_RES_SYS_ERROR_MSG = "시스템 오류입니다. 잠시 후 다시 시도해주세요.";

}
