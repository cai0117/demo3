package com.example.demo.movie.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
@TableName("movie_user")
@ApiModel(value="MovieUser对象", description="")
public class MovieUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String name;

    private String tel;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Integer status;


}
