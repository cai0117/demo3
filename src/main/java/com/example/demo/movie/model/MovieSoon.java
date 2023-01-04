package com.example.demo.movie.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 即将上映电影
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_soon")
@ApiModel(value="MovieSoon对象", description="即将上映电影")
public class MovieSoon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "soon_id", type = IdType.AUTO)
    private Integer soonId;

    private String title;

    @ApiModelProperty(value = "图片")
    private String cover;

    @ApiModelProperty(value = "想看人数")
    private Integer seemNum;

    @ApiModelProperty(value = "导演")
    private String director;

    private String protagonist;

    @ApiModelProperty(value = "电影类型")
    @TableField("`type`")
    private String type;

    private String movieCountry;

    @ApiModelProperty(value = "上映日期")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date comingData;

    private String movieTime;

    @ApiModelProperty(value = "预告片")
    private String preview;

    @ApiModelProperty(value = "电影简介")
    private String synopsis;
}
