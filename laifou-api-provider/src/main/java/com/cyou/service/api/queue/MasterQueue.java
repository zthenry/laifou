package com.cyou.service.api.queue;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MasterQueue {
	private static final Log log =LogFactory.getLog(MasterQueue.class);
	private static BlockingQueue<Long> queue;
	
	static{
		queue = new ArrayBlockingQueue<Long>(1000);
	}
	/**
	 * insert the element intto  queue,
	 * if the queue is full, wait for space;
	 * 
	 * @param str
	 * @throws Exception 
	 */
	public static void add(Long masterPhoneId) throws InterruptedException {
		log.debug("queue size = "+ queue.size());
		try {
			
			queue.put(masterPhoneId);
		} catch (InterruptedException e) {
			log.error("masterPhoneId :"+masterPhoneId+" add to queue failure");
			throw new InterruptedException("masterPhoneId :"+masterPhoneId+" add to queue failure /n" + e.getMessage());
		}
	}
	/**
	 * Retrieves and removes the head of this queue, 
	 * throw exception when the queue is empty.
	 * 
	 * @param str
	 */
	public static Long remove() {
		log.debug("queue size = "+ queue.size());
		try {
			return queue.take();
		} catch (NoSuchElementException e) {
			log.debug("",e);
		}
        catch (InterruptedException e)
        {
            log.debug("",e);
        }
		return null;
	}
}
