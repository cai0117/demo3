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
 * 高分电影
 * </p>
 *
 * @author CSW
 * @since 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_high_scoure")
@ApiModel(value="MovieHighScoure对象", description="高分电影")
public class MovieHighScoure implements Serializable {

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
    private Date release;

    private String movieTime;


}
