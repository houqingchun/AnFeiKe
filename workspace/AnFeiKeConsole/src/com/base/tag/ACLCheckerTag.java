package com.base.tag;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.base.util.SessionUtils;

public class ACLCheckerTag extends BodyTagSupport {

	private static final long serialVersionUID = 8518612630558305821L;
	private String actUri = null;
	private String userId = null;
	private boolean remoteCheck = false;

	private Log log = LogFactory.getLog(this.getClass());

	public int doStartTag() {
		// 判断是否超级管理员
		if (!SessionUtils.isAdmin()) {
			if (SessionUtils.getUser() == null || !SessionUtils.getUser().getId().equals(this.userId)) {
				// 日志记录
				log.error("权限验证失败 user Id:" + this.userId);
				return SKIP_BODY;
			}else{
				log.debug("权限验证通过");
				return EVAL_BODY_INCLUDE;
			}
		} else {
			log.debug("管理员权限");
			return EVAL_BODY_INCLUDE;
		}

	}

	/**
	 * @return the remoteCheck
	 */
	public synchronized boolean getRemoteCheck() {
		return remoteCheck;
	}

	/**
	 * @param remoteCheck
	 *            the remoteCheck to set
	 */
	public synchronized void setRemoteCheck(boolean remoteCheck) {
		this.remoteCheck = remoteCheck;
	}

	public synchronized String getActUri() {
		return actUri;
	}

	public synchronized void setActUri(String actUri) {
		this.actUri = actUri;
	}

	public synchronized String getUserId() {
		return userId;
	}

	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}
}
