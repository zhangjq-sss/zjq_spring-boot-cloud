package com.zjq.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zjq.core.utils.JsonUtil;
import com.zjq.gateway.model.AuthResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessTokenFilter extends ZuulFilter{
	
	@Value("${filter.ignore-uri:/api/login,/api/logout,/api/static,/payment/pay/notifyByAlipay,/payment/pay/notifyByWeChat}")
	private String ignoreUri;

	@Override
	public Object run() {
		String[] ignoreArray = ignoreUri.split(",");
		log.debug("== ignoreUri{} ",ignoreUri );
    	
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
        log.debug("=={} request to url {}  rui: {}", request.getMethod(), request.getRequestURL().toString(), request.getRequestURI() );
        
//        String token = request.getHeader("token");
        String token = request.getParameter("token");
        if(token==null || token.equals("null")){
        	token = null;
        }
        
        boolean flag=false;
        for(int i=0;i<ignoreArray.length;i++){
        	if ( StringUtils.isNotBlank( ignoreArray[i]) ) {
        		if(  request.getRequestURI() .startsWith(ignoreArray[i]) ) {
            		flag = true;
                }
        	}
        }
        
        if(!flag&&token == null){	
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(  HttpServletResponse.SC_UNAUTHORIZED );
            ctx.setResponseBody( getErrorResponse( HttpServletResponse.SC_UNAUTHORIZED,  "token is empty" ) );
            return null;
            
        }else if(!flag&&token != null){
        	//验证token是否生效
//			try {
//				AuthResult authResult = authSeviceFeignClient.checkToken(token.toString());
//				if( ! authResult.isSuccess() ) {
//					  log.error("token invalid! {}", authResult.getMessage() );
//					  ctx.setSendZuulResponse(false);
//			          ctx.setResponseStatusCode(  HttpServletResponse.SC_UNAUTHORIZED );
//			          ctx.setResponseBody(  getErrorResponse( HttpServletResponse.SC_UNAUTHORIZED, authResult.getMessage() ) );
//			          
//				} else {
//					 log.debug("access token ok");
//					ctx.setSendZuulResponse(true);
//					ctx.setResponseStatusCode(  HttpServletResponse.SC_OK );
//				}
//			} catch (Exception e) {
//				log.error(e.getMessage());
//			}
        }
        return null;
	}
	
	public String getErrorResponse(int status, String message) {
        AuthResult authResult=new AuthResult();
        authResult.setCode(String.valueOf(status));
        authResult.setMessage(message);
		return JsonUtil.toJson(authResult);
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
