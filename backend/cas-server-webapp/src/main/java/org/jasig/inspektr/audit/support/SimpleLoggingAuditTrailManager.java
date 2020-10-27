package org.jasig.inspektr.audit.support;

import java.time.LocalDateTime;
import org.jasig.inspektr.audit.AuditActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZhangWeiWei
 * @date 2020年10月27日,上午11:17:18
 * @description 简单日志审核跟踪管理器
 */
public class SimpleLoggingAuditTrailManager extends AbstractStringAuditTrailManager {
	private static final Logger log = LoggerFactory.getLogger(SimpleLoggingAuditTrailManager.class);

	@Override
	public void record(AuditActionContext auditActionContext) {
		log.info(toString(auditActionContext));
	}

	@Override
	protected String getSingleLineAuditString(AuditActionContext auditActionContext) {
		final StringBuilder builder = new StringBuilder();
		builder.append(LocalDateTime.now());
		builder.append(getEntrySeparator());
		builder.append(auditActionContext.getApplicationCode());
		builder.append(getEntrySeparator());
		builder.append(auditActionContext.getResourceOperatedUpon());
		builder.append(getEntrySeparator());
		builder.append(auditActionContext.getActionPerformed());
		builder.append(getEntrySeparator());
		builder.append(auditActionContext.getPrincipal());
		return builder.toString();
	}

	@Override
	protected String getMultiLineAuditString(AuditActionContext auditActionContext) {
		final StringBuilder builder = new StringBuilder();
		builder.append("Audit trail record BEGIN\n");
		builder.append("=============================================================\n");
		builder.append("WHO: ");
		builder.append(auditActionContext.getPrincipal());
		builder.append("\n");
		builder.append("WHAT: ");
		builder.append(auditActionContext.getResourceOperatedUpon());
		builder.append("\n");
		builder.append("ACTION: ");
		builder.append(auditActionContext.getActionPerformed());
		builder.append("\n");
		builder.append("APPLICATION: ");
		builder.append(auditActionContext.getApplicationCode());
		builder.append("\n");
		builder.append("WHEN: ");
		builder.append(LocalDateTime.now());
		builder.append("\n");
		builder.append("=============================================================");
		builder.append("\n\n");
		return builder.toString();
	}
}
