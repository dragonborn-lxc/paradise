package cn.lxc.paradise.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.UUID;

/**
 * File Name: PipelinePractice
 * General Description: Copyright and file header.
 * Revision History:
 * Modification
 * Author                Date(MM/DD/YYYY)   JiraID           Description of Changes
 * ---------------------   ------------    ----------     -----------------------------
 * Le xing chen            2020/4/23
 */
public class PipelinePractice {

	// 并发线程数
	private static final int threadNum = 50;
	// pipeline大小
	private static final int batchSize = 20;
	// 每个任务处理任务数
	private static final int taskNum = 1000;

	private static JedisPool jedisPool;

	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(200);
		poolConfig.setMaxIdle(100);
		poolConfig.setMaxWaitMillis(2000);
		poolConfig.setTestOnBorrow(false);
		poolConfig.setTestOnReturn(false);
		jedisPool = new JedisPool(poolConfig, "10.130.14.24", 6379, 1000, "Redis1024$$", 5);
	}

	public static void main(String[] args) {
		withPipeline();
		withoutPipeline();
	}


	private static void withoutPipeline() {
		long startTime = System.currentTimeMillis();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			for (int i = 1; i <= taskNum; i++) {
				jedis.set("withoutPipeline_" + i, UUID.randomUUID().toString());
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("没有使用pipeline的处理时间为：" + (endTime - startTime) + "毫秒");
	}

	private static void withPipeline() {
		long startTime = System.currentTimeMillis();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Pipeline pipeline = jedis.pipelined();
			for (int i = 1; i <= taskNum; i++) {
				pipeline.set("withPipeline_" + i, UUID.randomUUID().toString());
				if ((i % batchSize) == 0) {
					pipeline.sync();
				}
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("使用pipeline的处理时间为：" + (endTime - startTime) + "毫秒");
	}

}
