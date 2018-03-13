package com.tenacity.free.zookeeper.sync;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @project_name: tenacity-free-zookeeper
 * @package_name: com.tenacity.free.zookeeper.sync
 * @file_name: Delete_API_Usage.java
 * @author: free.zhang
 * @datetime: 2018年3月13日 下午9:13:31
 * @desc: 删除节点:只允许删除叶子节点，即一个节点如果有子节点，那么该节点无法直接删除，必须先删掉其所有子节点
 */
public class Delete_API_Usage implements Watcher {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static ZooKeeper zooKeeper;

	/**
	 * @author: free.zhang
	 * @datetime: 2018年3月13日 下午9:13:31
	 * @desc: TODO
	 * @param event
	 */
	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) {
			if (EventType.None == event.getType() && null == event.getPath()) {
				countDownLatch.countDown();
			}
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年3月13日 下午9:13:31
	 * @desc: TODO
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "/zk-book";
		try {
			zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Delete_API_Usage());
			countDownLatch.await();

			zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("success create znode: " + path);
			zooKeeper.create(path + "/c1", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("success create znode: " + path + "/c1");

			zooKeeper.delete(path, -1);

			zooKeeper.delete(path + "/c1", -1);

			System.out.println("success delete znode: " + path + "/c1");
			zooKeeper.delete(path, -1);

			System.out.println("success delete znode: " + path);

			Thread.sleep(Integer.MAX_VALUE);
		} catch (IOException e) {
			System.out.println("io exception....");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("interrupted exception....");
			e.printStackTrace();
		} catch (KeeperException e) {
			System.out.println("keeper exception....");
			e.printStackTrace();
		}
	}

}
