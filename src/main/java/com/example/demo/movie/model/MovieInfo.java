package com.example.demo.movie.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CSW
 * @since 2022-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_info")
@ApiModel(value="MovieInfo对象", description="")
public class MovieInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "movie_id", type = IdType.AUTO)
    private Integer movieId;

    @ApiModelProperty(value = "评分")
    private String rate;

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
    @TableField("`release`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private String release;

    private String movieTime;

    @ApiModelProperty(value = "热短评")

    private String hotShortCommend;

    @ApiModelProperty(value = "评价人数")

    private Integer evaluateNum;

    @ApiModelProperty(value = "电影简介")
    private String synopsis;
    @ApiModelProperty(value = "待映")
    private Integer isNew;
    @ApiModelProperty(value = "热映")
    private Integer isHot;

}
