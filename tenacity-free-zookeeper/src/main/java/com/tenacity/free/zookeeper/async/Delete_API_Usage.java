package com.tenacity.free.zookeeper.async;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
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
 * @package_name: com.tenacity.free.zookeeper.async
 * @file_name: Delete_API_Usage.java
 * @author: free.zhang
 * @datetime: 2018年3月13日 下午9:27:59
 * @desc: 异步删除节点：
 */
public class Delete_API_Usage implements Watcher {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);

	private static ZooKeeper zooKeeper;

	/**
	 * @author: free.zhang
	 * @datetime: 2018年3月13日 下午9:27:59
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
	 * @datetime: 2018年3月13日 下午9:27:59
	 * @desc: TODO
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "/zk-book";
		try {
			zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Delete_API_Usage());

			countDownLatch.await();

			zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

			System.out.println("success create znode: " + path);

			zooKeeper.create(path + "/c1", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("success create znode: " + path + "/c1");

			zooKeeper.delete(path, -1, new IVoidCallback(), null);
			zooKeeper.delete(path + "/c1", -1, new IVoidCallback(), null);
			zooKeeper.delete(path, -1, new IVoidCallback(), null);

			Thread.sleep(Integer.MAX_VALUE);
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} catch (KeeperException e) {

			e.printStackTrace();
		}
	}

}

class IVoidCallback implements AsyncCallback.VoidCallback {

	/**
	 * @author: free.zhang
	 * @datetime: 2018年3月13日 下午9:34:32
	 * @desc: TODO
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	@Override
	public void processResult(int rc, String path, Object ctx) {
		System.out.println(rc + ", " + path + ", " + ctx);
	}

}
