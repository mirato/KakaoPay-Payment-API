package com.payment.api.service;

import java.util.List;

import com.payment.api.entity.FlyMoneyHistEntity;
import com.payment.api.entity.FlyMoneyInfoEntity;
import com.payment.api.entity.TokenMstEntity;
import com.payment.api.req.CatchMoneyReqEntity;
import com.payment.api.req.FlyMoneyInfoReqEntity;
import com.payment.api.req.FlyMoneyReqEntity;
import com.payment.api.res.CatchMoneyResEntity;
import com.payment.api.res.FlyMoneyInfoResEntity;
import com.payment.api.res.FlyMoneyResEntity;

public interface PaymentService {
	
	public List<FlyMoneyInfoEntity> selectFlyMoneyInfoList(FlyMoneyInfoEntity req) throws Exception;
	
	public FlyMoneyInfoEntity selectFlyMoneyInfo(FlyMoneyInfoEntity req) throws Exception;
	
	public List<FlyMoneyHistEntity> selectFlyMoneyHistList(FlyMoneyHistEntity req) throws Exception;
	
	public TokenMstEntity selectTokenInfo(TokenMstEntity req) throws Exception;
	
	public int insertFlyMoney(FlyMoneyInfoEntity req) throws Exception;
	
	public int insertFlyMoneyHist(FlyMoneyHistEntity req) throws Exception;
	
	public int insertTokenMst(TokenMstEntity req) throws Exception;
	
	public int updateFlyMoneyInfo(FlyMoneyInfoEntity req) throws Exception;

	public FlyMoneyResEntity flyMoney(FlyMoneyReqEntity req) throws Exception;
	
	public CatchMoneyResEntity catchMoney(CatchMoneyReqEntity req) throws Exception;
	
	public FlyMoneyInfoResEntity flyMoneyInfo(FlyMoneyInfoReqEntity req) throws Exception;

}
