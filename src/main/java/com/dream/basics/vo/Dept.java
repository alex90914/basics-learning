package com.dream.basics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-07-26 14:25
 * @desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    @XmlElement(name = "DEPT_ID")
    private Long deptId;
    @XmlElement(name = "DEPT_NAME")
    private String deptName;
}
