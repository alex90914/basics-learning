package com.dream.basics.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-07-11 11:33
 * @desc
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Emp {
	private String  empName;
	private Integer age;



	public Emp(Integer age) {
		this.age = age;
	}
}
