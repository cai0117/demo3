package com.example.demo.movie.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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

    @ApiModelProperty(value = "是否为新上映")
    private Boolean isNew;


}
