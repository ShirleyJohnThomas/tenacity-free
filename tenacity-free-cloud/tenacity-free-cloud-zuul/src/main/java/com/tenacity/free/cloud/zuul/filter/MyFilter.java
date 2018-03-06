package com.tenacity.free.cloud.zuul.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @ProjectName: tenacity-free-cloud-zuul
 * @PackageName: com.tenacity.free.cloud.zuul.filter
 * @ClassName: MyFilter.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午4:04:22
 * @Description: TODO
 */
public class MyFilter extends ZuulFilter {

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 下午4:04:22
	 * @Description:
	 * @return
	 */
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		Object accessToken = request.getParameter("token");
		if (null == accessToken) {
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			try {
				context.getResponse().getWriter().write("token is empty");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 下午4:04:22
	 * @Description: TODO
	 * @return
	 */
	@Override
	public boolean shouldFilter() {

		return true;
	}

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 下午4:04:22
	 * @Description: TODO
	 * @return
	 */
	@Override
	public int filterOrder() {

		return 0;
	}

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 下午4:04:22
	 * @Description: TODO
	 * @return
	 */
	@Override
	public String filterType() {

		return "pre";
	}

}
