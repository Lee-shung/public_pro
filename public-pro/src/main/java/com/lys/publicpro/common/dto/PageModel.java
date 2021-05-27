package com.lys.publicpro.common.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

import java.util.List;

/**
 * @author 李无敌
 * @date 2021年01月20日 10:52
 */
@ApiModel
public class PageModel extends Page {
    @ApiModelProperty(value = "当前页码", name = "current", required = true, example = "1")
    private long current = 1;
    @ApiModelProperty(value = "每页条数", name = "size", required = true, example = "10")
    private long size = 10;

    @ApiParam(hidden = true)
    private long total;
    @ApiParam(hidden = true)
    private List records;
//    @ApiParam(hidden = true)
//    private boolean optimizeCountSql;
//    @ApiParam(hidden = true)
//    private boolean isSearchCount;
//    @ApiParam(hidden = true)
//    private boolean hitCount;
//    @ApiParam(hidden = true)
//    private String countId;
//    @ApiParam(hidden = true)
//    private Long maxLimit;
    @ApiParam(hidden = true)
    private boolean searchCount;

}
