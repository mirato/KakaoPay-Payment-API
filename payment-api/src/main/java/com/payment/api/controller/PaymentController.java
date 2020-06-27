package com.payment.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.api.constant.Consts;
import com.payment.api.entity.HeaderEntity;
import com.payment.api.req.CatchMoneyReqEntity;
import com.payment.api.req.FlyMoneyInfoReqEntity;
import com.payment.api.req.FlyMoneyReqEntity;
import com.payment.api.res.CatchMoneyResEntity;
import com.payment.api.res.FlyMoneyInfoResEntity;
import com.payment.api.res.FlyMoneyResEntity;
import com.payment.api.res.ResEntity;
import com.payment.api.service.PaymentService;
import com.payment.api.validate.EntityValidator;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	private final SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

	/**
	 * 뿌리기
	 * @param FlyMoneyReqEntity
	 * @return
	 */
	@ApiOperation(value="", notes = "뿌리기")
	@PostMapping("/v1/payment/money/fly")
	public ResEntity moneyFly(@RequestHeader(value="X-USER-ID") String xUserId
								, @RequestHeader(value="X-ROOM-ID") String xRoomId
								, @Valid @RequestBody FlyMoneyReqEntity req, BindingResult bindingResult) {
		
		FlyMoneyResEntity responseVO = new FlyMoneyResEntity();
		HeaderEntity headerEntity = new HeaderEntity();
		headerEntity.setXUserId(xUserId);
		headerEntity.setXRoomId(xRoomId);
		
		try {
			log.debug("Fly Money Start");
			
			// 1. Header 검증
			if (EntityValidator.isValid(headerEntity, Default.class)) {
				log.debug("Invalid Header!!");
				responseVO.setResultCd(Consts.FLY_MONEY_RES_HEADER_ERROR_CD);
				responseVO.setResultMsg(Consts.FLY_MONEY_RES_HEADER_ERROR_MSG);
				return responseVO;
	        }
			req.setHeader(headerEntity);

			// 2. Request Parameter 검증
			if (bindingResult.hasErrors()) {
				log.debug("Invalid Parameter!!");
	            throw new BindException(bindingResult);
	        }
			
			// 3. Process Call	
			responseVO = paymentService.flyMoney(req);

		} catch (BindException e) {
			log.debug("Exception: {}", e.getFieldErrors());
			responseVO.setResultCd(Consts.FLY_MONEY_RES_PARAM_ERROR_CD);
			responseVO.setResultMsg(Consts.FLY_MONEY_RES_PARAM_ERROR_MSG);
		} catch (Exception e) {
			log.debug("Exception: {}", e);
			responseVO.setResultCd(Consts.FLY_MONEY_RES_SYS_ERROR_CD);
			responseVO.setResultMsg(Consts.FLY_MONEY_RES_SYS_ERROR_MSG);
		} finally {
			responseVO.setResDate(sf.format(new Date()));
		}

		return responseVO;
	}
	
	/**
	 * 받기
	 * @param CatchMoneyReqEntity
	 * @return
	 */
	@ApiOperation(value="", notes = "받기")
	@PostMapping("/v1/payment/money/catch")
	public ResEntity moneyCatch(@RequestHeader(value="X-USER-ID") String xUserId
								, @RequestHeader(value="X-ROOM-ID") String xRoomId
								, @Valid @RequestBody CatchMoneyReqEntity req, BindingResult bindingResult) {
		CatchMoneyResEntity responseVO = new CatchMoneyResEntity();
		
		HeaderEntity headerEntity = new HeaderEntity();
		headerEntity.setXUserId(xUserId);
		headerEntity.setXRoomId(xRoomId);
		
		try {
			log.debug("Catch Money Start");
			
			// 1. Header 검증
			if (EntityValidator.isValid(headerEntity, Default.class)) {
				log.debug("Invalid Header!!");
				responseVO.setResultCd(Consts.FLY_MONEY_RES_HEADER_ERROR_CD);
				responseVO.setResultMsg(Consts.FLY_MONEY_RES_HEADER_ERROR_MSG);
				return responseVO;
			}
			req.setHeader(headerEntity);
			
			// 2. Request Parameter 검증
			if (bindingResult.hasErrors()) {
				log.debug("Invalid Parameter!!");
				throw new BindException(bindingResult);
			}
			
			// 3. Process Call	
			responseVO = paymentService.catchMoney(req);
			
		} catch (BindException e) {
			log.debug("Exception: {}", e.getFieldErrors());
			responseVO.setResultCd(Consts.FLY_MONEY_RES_PARAM_ERROR_CD);
			responseVO.setResultMsg(Consts.FLY_MONEY_RES_PARAM_ERROR_MSG);
		} catch (Exception e) {
			log.debug("Exception: {}", e);
			responseVO.setResultCd(Consts.FLY_MONEY_RES_SYS_ERROR_CD);
			responseVO.setResultMsg(Consts.FLY_MONEY_RES_SYS_ERROR_MSG);
		} finally {
			responseVO.setResDate(sf.format(new Date()));
		}
		
		return responseVO;
	}
	
	/**
	 * 조회
	 * @param FlyMoneyInfoReqEntity
	 * @return
	 */
	@ApiOperation(value="", notes = "조회")
	@GetMapping("/v1/payment/money/info")
	public ResEntity flyMoneyInfo(@RequestHeader(value="X-USER-ID") String xUserId
									, @RequestHeader(value="X-ROOM-ID") String xRoomId
									, @Valid FlyMoneyInfoReqEntity req, BindingResult bindingResult) {
		
		FlyMoneyInfoResEntity responseVO = new FlyMoneyInfoResEntity();
		
		HeaderEntity headerEntity = new HeaderEntity();
		headerEntity.setXUserId(xUserId);
		headerEntity.setXRoomId(xRoomId);
		
		try {
			log.debug("Fly Money Info Start");
			
			// 1. Header 검증
			if (EntityValidator.isValid(headerEntity, Default.class)) {
				log.debug("Invalid Header!!");
				responseVO.setResultCd(Consts.FLY_MONEY_RES_HEADER_ERROR_CD);
				responseVO.setResultMsg(Consts.FLY_MONEY_RES_HEADER_ERROR_MSG);
				return responseVO;
			}
			
			req.setHeader(headerEntity);
			
			// 2. Request Parameter 검증
			if (bindingResult.hasErrors()) {
				log.debug("Invalid Parameter!!");
				throw new BindException(bindingResult);
			}
			
			// 3. Process Call	
			responseVO = paymentService.flyMoneyInfo(req);
			
		} catch (BindException e) {
			log.debug("Exception: {}", e.getFieldErrors());
			responseVO.setResultCd(Consts.FLY_MONEY_RES_PARAM_ERROR_CD);
			responseVO.setResultMsg(Consts.FLY_MONEY_RES_PARAM_ERROR_MSG);
		} catch (Exception e) {
			log.debug("Exception: {}", e);
			responseVO.setResultCd(Consts.FLY_MONEY_RES_SYS_ERROR_CD);
			responseVO.setResultMsg(Consts.FLY_MONEY_RES_SYS_ERROR_MSG);
		} finally {
			responseVO.setResDate(sf.format(new Date()));
		}
		
		return responseVO;
	}
}
