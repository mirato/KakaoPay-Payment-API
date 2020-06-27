package com.payment.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.payment.api.entity.FlyMoneyHistEntity;
import com.payment.api.entity.FlyMoneyInfoEntity;
import com.payment.api.entity.TokenMstEntity;

@Mapper
public interface PaymentMapper {
	
	public List<FlyMoneyInfoEntity> selectFlyMoneyInfoList(FlyMoneyInfoEntity req) throws Exception;
	
	public FlyMoneyInfoEntity selectFlyMoneyInfo(FlyMoneyInfoEntity req) throws Exception;
	
	public List<FlyMoneyHistEntity> selectFlyMoneyHistList(FlyMoneyHistEntity req) throws Exception;
	
	public TokenMstEntity selectTokenInfo(TokenMstEntity req) throws Exception;
	
	public int insertFlyMoney(FlyMoneyInfoEntity req) throws Exception;
	
	public int insertFlyMoneyHist(FlyMoneyHistEntity req) throws Exception;
	
	public int insertTokenMst(TokenMstEntity req) throws Exception;
	
	public int updateFlyMoneyInfo(FlyMoneyInfoEntity req) throws Exception;
}
