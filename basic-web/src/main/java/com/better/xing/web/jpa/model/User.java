package com.better.xing.web.jpa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/11 10:04
 */
@Entity
@Table(name ="user")
@Data
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String clientName;
    private String mobile;
    private Date bithday;
}
