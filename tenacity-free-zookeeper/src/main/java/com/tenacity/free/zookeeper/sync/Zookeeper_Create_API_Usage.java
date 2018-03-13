package com.tenacity.free.zookeeper.sync;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @project_name tenacity-free-zookeeper
 * @package_name com.tenacity.free.zookeeper.sync
 * @file_name Zookeeper_Create_API_Usage.java
 * @author tenacity.free_zhang
 * @time 下午5:22:25 2018年3月13日
 * @desc 同步创建节点:不支持递归调用：无法在父节点不存在的情况下创建子节点；如果一个节点已经存在那么在创建同名节点时则抛出NodeExistsException异常
 */
public class Zookeeper_Create_API_Usage implements Watcher{

	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	/**
	 * @file_name Zookeeper_Create_API_Usage.java
	 × @method_name main
	 * @author tenacity.free_zhang
	 * @time 下午5:22:25 2018年3月13日
	 * @desc main测试方法
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args){
		ZooKeeper zooKeeper;
		try {
			zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Create_API_Usage());
			System.out.println(zooKeeper.getState());
			countDownLatch.await();
			//创建第一个节点
			String path1 = zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			
			System.out.println("Success create znote: " + path1);
			
			//创建第二个节点
			String path2 = zooKeeper.create("/zk-test-enhemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			
			System.out.println("Success create znote: " + path2);
		} catch (IOException e) {
			System.out.println("io exception.....");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("interrupted exception.....");
			e.printStackTrace();
		} catch (KeeperException e) {
			System.out.println("keeper exception.....");
			e.printStackTrace();
		}
	}

	/**
	 * @file_name Zookeeper_Create_API_Usage.java
	 × @method_name process
	 * @author tenacity.free_zhang
	 * @time 下午5:23:52 2018年3月13日
	 * @desc TODO
	 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 * @param event
	 */
	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) {
			countDownLatch.countDown();
		}
	}

}
