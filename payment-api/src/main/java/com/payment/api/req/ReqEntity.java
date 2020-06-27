package com.payment.api.req;

import com.payment.api.entity.HeaderEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReqEntity {
	private HeaderEntity header;
}
