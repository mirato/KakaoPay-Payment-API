package com.payment.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payment.api.constant.Consts;
import com.payment.api.entity.FlyMoneyHistEntity;
import com.payment.api.entity.FlyMoneyInfoEntity;
import com.payment.api.entity.TokenMstEntity;
import com.payment.api.mapper.PaymentMapper;
import com.payment.api.req.CatchMoneyReqEntity;
import com.payment.api.req.FlyMoneyInfoReqEntity;
import com.payment.api.req.FlyMoneyReqEntity;
import com.payment.api.res.CatchInfoEntity;
import com.payment.api.res.CatchMoneyResEntity;
import com.payment.api.res.FlyMoneyInfoResEntity;
import com.payment.api.res.FlyMoneyResEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{
	
	private final SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

	@Autowired
	private PaymentMapper mapper;
	
	@Override
	public List<FlyMoneyInfoEntity> selectFlyMoneyInfoList(FlyMoneyInfoEntity req) throws Exception {
		return mapper.selectFlyMoneyInfoList(req);
	}
	
	@Override
	public FlyMoneyInfoEntity selectFlyMoneyInfo(FlyMoneyInfoEntity req) throws Exception {
		return mapper.selectFlyMoneyInfo(req);
	}
	
	@Override
	public List<FlyMoneyHistEntity> selectFlyMoneyHistList(FlyMoneyHistEntity req) throws Exception {
		return mapper.selectFlyMoneyHistList(req);
	}
	
	@Override
	public TokenMstEntity selectTokenInfo(TokenMstEntity req) throws Exception {
		return mapper.selectTokenInfo(req);
	}
	
	@Override
	@Transactional
	public int insertFlyMoney(FlyMoneyInfoEntity req) throws Exception {
		return mapper.insertFlyMoney(req);
	}
	
	@Override
	@Transactional
	public int insertFlyMoneyHist(FlyMoneyHistEntity req) throws Exception {
		return mapper.insertFlyMoneyHist(req);
	}
	
	@Override
	@Transactional
	public int insertTokenMst(TokenMstEntity req) throws Exception {
		return mapper.insertTokenMst(req);
	}
	
	@Override
	@Transactional
	public int updateFlyMoneyInfo(FlyMoneyInfoEntity req) throws Exception {
		return mapper.updateFlyMoneyInfo(req);
	}

	@Override
	public FlyMoneyResEntity flyMoney(FlyMoneyReqEntity req) throws Exception {
		
		FlyMoneyResEntity flyMoneyResEntity = new FlyMoneyResEntity();
		flyMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_SUCCESS_CD);
		flyMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_SUCCESS_MSG);
		
		FlyMoneyInfoEntity flyMoneyInfoEntity = new FlyMoneyInfoEntity();
		// 1. 토큰 발급 (3자리 랜덤 문자열)
		String token = RandomStringUtils.randomAlphanumeric(3);
		TokenMstEntity tokenMstEntity = new TokenMstEntity();
		tokenMstEntity.setUserId(req.getHeader().getXUserId());
		tokenMstEntity.setToken(token);
		tokenMstEntity.setDiffTime(10);
		mapper.insertTokenMst(tokenMstEntity);
		
		// 2. 뿌리기 등록
		flyMoneyInfoEntity.setToken(token);
		flyMoneyInfoEntity.setFlyMoney(req.getFlyMoney());
		flyMoneyInfoEntity.setLimitCnt(req.getLimitCnt());
		flyMoneyInfoEntity.setLeftMoney(req.getFlyMoney());
		flyMoneyInfoEntity.setLeftCnt(req.getLimitCnt());
		flyMoneyInfoEntity.setStatus(Consts.FLY_MONEY_STATUS_READY);	// 대기중
		flyMoneyInfoEntity.setUserId(req.getHeader().getXUserId());
		flyMoneyInfoEntity.setRoomId(req.getHeader().getXRoomId());
		mapper.insertFlyMoney(flyMoneyInfoEntity);
		
		flyMoneyResEntity.setToken(token);
		
		return flyMoneyResEntity;
	}

	@Override
	public CatchMoneyResEntity catchMoney(CatchMoneyReqEntity req) throws Exception {
		
		CatchMoneyResEntity catchMoneyResEntity = new CatchMoneyResEntity();
		catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_SUCCESS_CD);
		catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_SUCCESS_MSG);
		
		// 뿌리기 정보 조회
		FlyMoneyInfoEntity flyMoneyInfoEntity = new FlyMoneyInfoEntity();
		flyMoneyInfoEntity.setToken(req.getToken());
		flyMoneyInfoEntity.setRoomId(req.getHeader().getXRoomId());
		log.debug(flyMoneyInfoEntity.toString());
		flyMoneyInfoEntity = mapper.selectFlyMoneyInfo(flyMoneyInfoEntity);
		
		// 뿌리기 정보가 있는 경우만 처리
		if (flyMoneyInfoEntity != null) {
			
			// 진행여부 확인
			if (Consts.FLY_MONEY_STATUS_END.equals(flyMoneyInfoEntity.getStatus())) {
				catchMoneyResEntity = new CatchMoneyResEntity();
				catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_STATUS_END_CD);
				catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_STATUS_END_MSG);
				return catchMoneyResEntity;
			}
			// 대기중이 아닌 경우
			else if (!Consts.FLY_MONEY_STATUS_READY.equals(flyMoneyInfoEntity.getStatus())) {
				catchMoneyResEntity = new CatchMoneyResEntity();
				catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_STATUS_PROCESSING_CD);
				catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_STATUS_PROCESSING_MSG);
				return catchMoneyResEntity;
			}
			
			// 본인 여부 조회
			if (flyMoneyInfoEntity.getUserId().equals(req.getHeader().getXUserId())) {
				catchMoneyResEntity = new CatchMoneyResEntity();
				catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_MYSELF_CD);
				catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_MYSELF_MSG);
				return catchMoneyResEntity;
			}
			
			// 토큰 정보조회
			TokenMstEntity tokenMstEntity = new TokenMstEntity();
			tokenMstEntity.setToken(req.getToken());
			tokenMstEntity.setUserId(req.getHeader().getXUserId());
			tokenMstEntity = mapper.selectTokenInfo(tokenMstEntity);
			
			if (tokenMstEntity != null) {
				int expTime = Integer.parseInt(tokenMstEntity.getExpTime());
				int nowTime = Integer.parseInt(sf.format(new Date()));
				
				// 만료일시를 초과한 경우
				if ( (expTime - nowTime) < 0 ) {
					catchMoneyResEntity = new CatchMoneyResEntity();
					catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_TIME_EXP_CD);
					catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_TIME_EXP_MSG);
					return catchMoneyResEntity;
				}
			}
			
			// 뿌리기 이력조회
			FlyMoneyHistEntity flyMoneyHistEntity = new FlyMoneyHistEntity();
			flyMoneyHistEntity.setFlyMoneySeq(flyMoneyInfoEntity.getFlyMoneySeq());
			flyMoneyHistEntity.setCatchUserId(req.getHeader().getXUserId());
			List<FlyMoneyHistEntity> flyMoneyHist = mapper.selectFlyMoneyHistList(flyMoneyHistEntity);
			
			// 참여 이력이 있는지 확인
			if (flyMoneyHist != null && flyMoneyHist.size() > 0) {
				catchMoneyResEntity = new CatchMoneyResEntity();
				catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_DUP_CD);
				catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_DUP_MSG);
				return catchMoneyResEntity;
			}
			
			// 뿌리기 상태를 처리중으로 변경
			flyMoneyInfoEntity.setStatus(Consts.FLY_MONEY_STATUS_PROCESSING); // 처리중
			mapper.updateFlyMoneyInfo(flyMoneyInfoEntity);
			
			// 뿌린 돈 받기 처리
			catchMoneyProcess(req, flyMoneyInfoEntity, flyMoneyHistEntity);
		
		} else {
			catchMoneyResEntity = new CatchMoneyResEntity();
			catchMoneyResEntity.setResultCd(Consts.FLY_MONEY_RES_NO_DATA_CD);
			catchMoneyResEntity.setResultMsg(Consts.FLY_MONEY_RES_NO_DATA_MSG);
		}

		return catchMoneyResEntity;
	}
	
	public void catchMoneyProcess(CatchMoneyReqEntity req, FlyMoneyInfoEntity flyMoneyInfoEntity, FlyMoneyHistEntity flyMoneyHistEntity) throws Exception {
		int start = 1;	// 시작값
		int remain = flyMoneyInfoEntity.getLeftMoney();	// 남은금액
		int limit = flyMoneyInfoEntity.getLeftCnt();
		
		// 랜덤 금액
		int randomMoney = 0;
		
		// 최소 인원이 아닌 경우 남은 금액으로 랜덤 금액 생성
		if (limit != 1) { randomMoney = (int) (Math.random() * remain) + start; } 
		// 1명이 남은 경우 남은 금액을 모두 할당
		else { randomMoney = remain; }
		
		// 할당된 금액을 남은 금액에서 빼기
		remain -= randomMoney;
		// 제한 인원 
		limit--;
		
		// 뿌리기 정보 갱신
		flyMoneyInfoEntity.setLeftCnt(limit);
		flyMoneyInfoEntity.setLeftMoney(remain);
		
		if (limit == 0) {
			flyMoneyInfoEntity.setStatus(Consts.FLY_MONEY_STATUS_END); // 종료
		} else {
			flyMoneyInfoEntity.setStatus(Consts.FLY_MONEY_STATUS_READY); // 처리중
		}
		mapper.updateFlyMoneyInfo(flyMoneyInfoEntity);
		
		// 뿌리기 이력 등록
		flyMoneyHistEntity = new FlyMoneyHistEntity();
		flyMoneyHistEntity.setFlyMoneySeq(flyMoneyInfoEntity.getFlyMoneySeq());
		flyMoneyHistEntity.setUserId(flyMoneyInfoEntity.getUserId());
		flyMoneyHistEntity.setRoomId(flyMoneyInfoEntity.getRoomId());
		flyMoneyHistEntity.setToken(req.getToken());
		flyMoneyHistEntity.setCatchMoney(randomMoney);
		flyMoneyHistEntity.setCatchUserId(req.getHeader().getXUserId());
		mapper.insertFlyMoneyHist(flyMoneyHistEntity);
	}

	@Override
	public FlyMoneyInfoResEntity flyMoneyInfo(FlyMoneyInfoReqEntity req) throws Exception {
		
		FlyMoneyInfoResEntity flyMoneyInfoResEntity = new FlyMoneyInfoResEntity();
		flyMoneyInfoResEntity.setResultCd(Consts.FLY_MONEY_RES_SUCCESS_CD);
		flyMoneyInfoResEntity.setResultMsg(Consts.FLY_MONEY_RES_SUCCESS_MSG);
		
		// 뿌리기 정보 조회
		FlyMoneyInfoEntity flyMoneyInfoEntity = new FlyMoneyInfoEntity();
		flyMoneyInfoEntity.setUserId(req.getHeader().getXUserId());
		flyMoneyInfoEntity.setRoomId(req.getHeader().getXRoomId());
		flyMoneyInfoEntity.setToken(req.getToken());
		flyMoneyInfoEntity.setLimitDay(7);	// 7일 이내 정보만 조회
		flyMoneyInfoEntity = mapper.selectFlyMoneyInfo(flyMoneyInfoEntity);
		
		if (flyMoneyInfoEntity != null) {
			// 뿌리기 이력조회
			FlyMoneyHistEntity flyMoneyHistEntity = new FlyMoneyHistEntity();
			flyMoneyHistEntity.setFlyMoneySeq(flyMoneyInfoEntity.getFlyMoneySeq());
			flyMoneyHistEntity.setUserId(req.getHeader().getXUserId());
			List<FlyMoneyHistEntity> flyMoneyHist = mapper.selectFlyMoneyHistList(flyMoneyHistEntity);
			
			// 토큰 정보조회
			TokenMstEntity tokenMstEntity = new TokenMstEntity();
			tokenMstEntity.setToken(req.getToken());
			tokenMstEntity.setUserId(req.getHeader().getXUserId());
			tokenMstEntity = mapper.selectTokenInfo(tokenMstEntity);
			
			List<CatchInfoEntity> catchInfoList = new ArrayList<>();
			CatchInfoEntity catchInfoEntity = null;
			for (FlyMoneyHistEntity hist : flyMoneyHist) {
				catchInfoEntity = new CatchInfoEntity();
				catchInfoEntity.setCatchMoney(hist.getCatchMoney());
				catchInfoEntity.setCatchUserId(hist.getCatchUserId());
				catchInfoList.add(catchInfoEntity);
			}
			
			flyMoneyInfoResEntity.setFlyMoney(flyMoneyInfoEntity.getFlyMoney());
			flyMoneyInfoResEntity.setFlyTime(tokenMstEntity.getPubTime());
			flyMoneyInfoResEntity.setLeftMoney(flyMoneyInfoEntity.getLeftMoney());
			flyMoneyInfoResEntity.setCatchInfoList(catchInfoList);
		} 
		// 조회 결과가 없는 경우
		else {
			flyMoneyInfoResEntity.setResultCd(Consts.FLY_MONEY_RES_NO_DATA_CD);
			flyMoneyInfoResEntity.setResultMsg(Consts.FLY_MONEY_RES_NO_DATA_MSG);
		}
		
		return flyMoneyInfoResEntity;
	}
}

