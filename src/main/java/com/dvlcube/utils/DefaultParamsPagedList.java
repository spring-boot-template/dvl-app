package com.dvlcube.utils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", required = true, dataType = "int", defaultValue = "0", paramType = "query"),
        @ApiImplicitParam(name = "count", dataType = "int", defaultValue = "25", paramType = "query"),
        @ApiImplicitParam(name = "order", dataType = "string", defaultValue = "ASC", paramType = "query"),
        @ApiImplicitParam(name = "sortProperty", dataType = "string", defaultValue = "id", paramType = "query"),
        @ApiImplicitParam(name = "like", dataType = "boolean", defaultValue = "false", paramType = "query")})
public @interface DefaultParamsPagedList {
}
