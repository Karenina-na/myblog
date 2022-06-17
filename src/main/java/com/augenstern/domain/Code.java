package com.augenstern.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 状态码
 */
@ApiModel(description = "返回状态码")
public class Code {
    @ApiModelProperty("保存成功")
    public static final Integer SAVE_OK = 20011;
    @ApiModelProperty("删除成功")
    public static final Integer DELETE_OK = 20021;
    @ApiModelProperty("更新成功")
    public static final Integer UPDATE_OK = 20031;
    @ApiModelProperty("获取成功")
    public static final Integer GET_OK = 20042;

    @ApiModelProperty("文件保存成功")
    public static final Integer File_SAVE_OK = 30011;


    @ApiModelProperty("保存失败")
    public static final Integer SAVE_ERR = 20010;
    @ApiModelProperty("删除失败")
    public static final Integer DELETE_ERR = 20020;
    @ApiModelProperty("更新失败")
    public static final Integer UPDATE_ERR = 20030;
    @ApiModelProperty("获取失败")
    public static final Integer GET_ERR = 20040;

    @ApiModelProperty("文件保存失败")
    public static final Integer File_SAVE_ERR = 30010;


    @ApiModelProperty("文件为空")
    public static final Integer File_Empty = 39999;


    @ApiModelProperty("系统错误")
    public static final Integer SYSTEM_ERR = 50001;
    @ApiModelProperty("系统运行错误")
    public static final Integer SYSTEM_RUN_TIMEOUT_ERR = 50002;
    @ApiModelProperty("系统未知错误")
    public static final Integer SYSTEM_UNKNOWN_ERR = 59999;

    @ApiModelProperty("用户错误")
    public static final Integer BUSINESS_ERR = 40001;
    @ApiModelProperty("用户未知错误")
    public static final Integer BUSINESS_UNKNOWN_ERR = 49999;
}
