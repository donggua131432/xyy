package com.xyy.controller;

import com.xyy.enums.ErrorCodeEnum;
import com.xyy.domain.ReturnInfo;
import com.xyy.util.JsonUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @描述: 统一异常处理
 * @作者: DuKai
 * @创建时间: 2018/8/22 19:00
 * @版本号: V3.0
 */
@ControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 登录认证异常
     * @param ex
     * @param response
     * @return
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public void authenticationException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(),ex);
        writeJson(ReturnInfo.getInstance().setCode(ErrorCodeEnum.LOGIN_NO.getCode())
                .setMsg(ErrorCodeEnum.LOGIN_NO.getDesc()).setData(null),response);
    }

    /**
     * 权限异常
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public void authorizationException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(),ex);
        writeJson(ReturnInfo.getInstance().setCode(ErrorCodeEnum.AUTH_NO.getCode())
                .setMsg(ErrorCodeEnum.AUTH_NO.getDesc()).setData(null),response);

    }

    /**
     * 未知错误异常
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({Exception.class})
    public void exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(),ex);
        writeJson(ReturnInfo.getInstance().setCode(ErrorCodeEnum.FAILED.getCode())
                .setMsg(ErrorCodeEnum.FAILED.getDesc()).setData(null),response);
    }

    /**
     * 键值冲突异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public void duplicateKeyException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(),ex);
        writeJson(ReturnInfo.getInstance().setCode(ErrorCodeEnum.DUPLICATE_KEY.getCode())
                .setMsg(ErrorCodeEnum.DUPLICATE_KEY.getDesc()).setData(null),response);
    }

    /**
     * 输出JSON
     * @param response
     */
    private void writeJson(ReturnInfo returnInfo, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JsonUtil.objectToString(returnInfo));
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
