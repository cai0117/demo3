package com.example.demo.movie.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author CSW
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "movie_order",autoResultMap = true)
@ApiModel(value="Order对象", description="订单")
public class MovieOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String orderTime;

    @ApiModelProperty(value = "订单状态")
    private Integer payStatus;

    @ApiModelProperty(value = "订单总额")
    private Integer orderPrice;

    @ApiModelProperty(value = "客户")
    private Integer customerId;

    @ApiModelProperty(value = "实收金额")
    private Integer payPrice;

    @ApiModelProperty(value = "关联电影")
    @TableField(value = "movie",typeHandler = JacksonTypeHandler.class)
    private MovieInfo movie;


}
