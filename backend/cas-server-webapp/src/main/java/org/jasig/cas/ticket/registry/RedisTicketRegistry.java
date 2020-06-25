package org.jasig.cas.ticket.registry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jasig.cas.ticket.ServiceTicket;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 键值票证注册表实现，用于在票证ID上键入的Redis中存储票证。
 * 
 * @author home
 */
public class RedisTicketRegistry extends AbstractDistributedTicketRegistry {
	private static final Logger logger = LoggerFactory.getLogger(RedisTicketRegistry.class);
	private static final String CAS_TICKET_PREFIX = "CAS_TICKET:";
	private static final String NO_REDIS_CLIENT_IS_DEFINED = "没有定义redis客户端。";
	@NotNull
	private final TicketRedisTemplate client;
	/** TGT缓存条目超时（以秒为单位）。 */
	@Min(0)
	private final long tgtTimeout;
	/** ST缓存条目超时（以秒为单位）。 */
	@Min(0)
	private final long stTimeout;

	public RedisTicketRegistry(final TicketRedisTemplate client, final long tgtTimeout, final long stTimeout) {
		super();
		this.client = client;
		this.tgtTimeout = tgtTimeout;
		this.stTimeout = stTimeout;
	}

	@Override
	public void addTicket(final Ticket ticket) {
		Assert.notNull(this.client, NO_REDIS_CLIENT_IS_DEFINED);
		try {
			logger.debug("添加票据 [{}]", ticket);
			String redisKey = getTicketRedisKey(ticket.getId());
			this.client.boundValueOps(redisKey).set(ticket, getTimeout(ticket), TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("添加失败 [{}]", ticket);
		}
	}

	@Override
	public Ticket getTicket(final String ticketId) {
		Assert.notNull(this.client, NO_REDIS_CLIENT_IS_DEFINED);
		try {
			String redisKey = getTicketRedisKey(ticketId);
			Ticket t = this.client.boundValueOps(redisKey).get();
			if (t != null) {
				return super.getProxiedTicketInstance(t);
			}
		} catch (Exception e) {
			logger.error("提取失败 [{}] ", ticketId);
		}
		return null;
	}

	@Override
	public boolean deleteTicket(final String ticketId) {
		Assert.notNull(this.client, NO_REDIS_CLIENT_IS_DEFINED);
		try {
			String redisKey = getTicketRedisKey(ticketId);
			this.client.delete(redisKey);
			return true;
		} catch (Exception e) {
			logger.error("票证未找到或已被删除。 删除失败 [{}]", ticketId);
			return false;
		}
	}

	@Override
	public Collection<Ticket> getTickets() {
		Assert.notNull(this.client, NO_REDIS_CLIENT_IS_DEFINED);
		Set<Ticket> tickets = new HashSet<>();
		Set<String> redisKeys = this.client.keys(getPatternTicketRedisKey());
		redisKeys.forEach(redisKey -> {
			Ticket ticket = this.client.boundValueOps(redisKey).get();
			if (ticket == null) {
				this.client.delete(redisKey);
			} else {
				tickets.add(ticket);
			}
		});
		return tickets;
	}

	@Override
	protected void updateTicket(final Ticket ticket) {
		Assert.notNull(this.client, NO_REDIS_CLIENT_IS_DEFINED);
		try {
			logger.debug("更新票据 [{}]", ticket);
			String redisKey = getTicketRedisKey(ticket.getId());
			this.client.boundValueOps(redisKey).set(ticket, getTimeout(ticket), TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("更新失败 [{}]", ticket);
		}
	}

	@Override
	protected boolean needsCallback() {
		return true;
	}

	/**
	 * 获取票证的超时值。
	 * 
	 * @param ticket 票证通用概念的接口。
	 * @return 超时时间
	 */
	private long getTimeout(final Ticket ticket) {
		if (ticket instanceof TicketGrantingTicket) {
			return this.tgtTimeout;
		}
		if (ticket instanceof ServiceTicket) {
			return this.stTimeout;
		}
		throw new IllegalArgumentException("无效的票证类型");
	}

	/**
	 * @param ticketId 票证编号
	 * @return 添加前缀作为redis的键
	 */
	private static String getTicketRedisKey(final String ticketId) {
		return CAS_TICKET_PREFIX + ticketId;
	}

	/**
	 * @return 模式化所有票证Redis键
	 */
	private static String getPatternTicketRedisKey() {
		return CAS_TICKET_PREFIX + "*";
	}
}
