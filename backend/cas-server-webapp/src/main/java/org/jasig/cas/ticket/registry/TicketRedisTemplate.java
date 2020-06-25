package org.jasig.cas.ticket.registry;

import org.jasig.cas.ticket.Ticket;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 提供用于redis操作的模板。
 * 
 * @author home
 */
public class TicketRedisTemplate extends RedisTemplate<String, Ticket> {

	public TicketRedisTemplate() {
		super();
		RedisSerializer<Object> valueSerializer = new JdkSerializationRedisSerializer();
		this.setKeySerializer(super.getStringSerializer());
		this.setHashKeySerializer(super.getStringSerializer());
		this.setValueSerializer(valueSerializer);
		this.setHashValueSerializer(valueSerializer);
	}

	public TicketRedisTemplate(final RedisConnectionFactory connectionFactory) {
		this();
		this.setConnectionFactory(connectionFactory);
		this.afterPropertiesSet();
	}

}
