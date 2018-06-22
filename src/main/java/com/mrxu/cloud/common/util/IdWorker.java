package com.mrxu.cloud.common.util;

public class IdWorker {


	private final SnowFlake snowFlake;

	public IdWorker(Long dataCenterId, Long machineId) {
		dataCenterId = dataCenterId == null ? 10L : dataCenterId;
		machineId = machineId == null ? 10L : machineId;
		snowFlake = new SnowFlake(dataCenterId, machineId);
	}

	public String genVerifyCode() {
		return RandomUtils.getRandomString(4);
	}

	public Long getNextId() {
		return snowFlake.nextId();
	}

	public Long currentTimeMillis() {
		return snowFlake.getNextMill();
	}

	public String getNextIdOfString() {
		return UuidUtils.numericToString(snowFlake.nextId(), 62);
	}

}
