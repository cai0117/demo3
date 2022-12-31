package com.example.demo.movie.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 热映
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_hot_relase")
@ApiModel(value="MovieHotRelase对象", description="热映")
public class MovieHotRelase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "hot_id", type = IdType.AUTO)
    private Integer hotId;

    private String title;

    @ApiModelProperty(value = "图片")
    private String cover;

    @ApiModelProperty(value = "导演")
    private String director;

    private String protagonist;

    @ApiModelProperty(value = "电影类型")
    @TableField("`type`")
    private String type;

    private String movieCountry;

    @ApiModelProperty(value = "上映日期")
    @TableField("`release`") //保留字段需要加上``
    private Date release;

    private String movieTime;

    @ApiModelProperty(value = "热短评")

    private String hotShortCommend;

    @ApiModelProperty(value = "点赞数")
    private Integer star;

    @ApiModelProperty(value = "评价人数")

    private Integer evaluateNum;

    @ApiModelProperty(value = "评分")
    private Double rate;

    @ApiModelProperty(value = "预告片")
    private String preview;


}
