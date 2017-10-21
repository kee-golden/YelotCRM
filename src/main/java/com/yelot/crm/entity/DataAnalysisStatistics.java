package com.yelot.crm.entity;

public class DataAnalysisStatistics {
	/**
	 * 对比时间
	 */
	private String timeInterval;
	/**
	 * 第一个时间对应的值
	 */
	private Integer firstValue;
	/**
	 * 第二个时间对应的值
	 */
	private Integer secondValue;
	/**
	 * 变化量
	 */
	private Integer variation;
	/**
	 * 变化率
	 */
	private String changeRate;

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Integer getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(Integer firstValue) {
		this.firstValue = firstValue;
	}

	public Integer getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(Integer secondValue) {
		this.secondValue = secondValue;
	}

	public Integer getVariation() {
		return variation;
	}

	public void setVariation(Integer variation) {
		this.variation = variation;
	}

	public String getChangeRate() {
		return changeRate;
	}

	public void setChangeRate(String changeRate) {
		this.changeRate = changeRate;
	}

}
