package com.dream.basics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-07-11 11:33
 * @desc
 */
@XmlRootElement(name = "EMP")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp extends Base implements Serializable {
    @XmlElement(name = "EMP_NAME")
    private String empName;
    @XmlElement(name = "AGE")
    private Integer age;
    @XmlElement(name = "DEPT")
    private Dept dept;

    public Emp(String empName, Integer age) {
        this.empName = empName;
        this.age = age;
    }

    public Emp(Integer age) {
        this.age = age;
    }
}
