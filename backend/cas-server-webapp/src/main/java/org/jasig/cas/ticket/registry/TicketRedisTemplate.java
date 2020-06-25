package org.jasig.cas.ticket.registry;

import org.jasig.cas.ticket.Ticket;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 提供用于redis操作的模板。
 * 
 * @author home
 */
public class TicketRedisTemplate extends RedisTemplate<String, Ticket> {

	public TicketRedisTemplate() {
		super();
		RedisSerializer<String> keySerializer = new StringRedisSerializer();
		RedisSerializer<Object> valueSerializer = new JdkSerializationRedisSerializer();
		this.setKeySerializer(keySerializer);
		this.setValueSerializer(valueSerializer);
		this.setHashKeySerializer(keySerializer);
		this.setHashValueSerializer(valueSerializer);
	}

	public TicketRedisTemplate(final RedisConnectionFactory connectionFactory) {
		this();
		this.setConnectionFactory(connectionFactory);
		this.afterPropertiesSet();
	}

}
