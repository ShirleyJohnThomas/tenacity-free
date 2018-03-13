package com.tenacity.free.zookeeper.async;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @project_name tenacity-free-zookeeper
 * @package_name com.tenacity.free.zookeeper.async
 * @file_name Zookeeper_Create_API_Usage.java
 * @author tenacity.free_zhang
 * @time 下午5:25:46 2018年3月13日
 * @desc 异步添加节点
 */
public class Zookeeper_Create_API_Usage implements Watcher {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);

	/**
	 * @file_name Zookeeper_Create_API_Usage.java × @method_name process
	 * @author tenacity.free_zhang
	 * @time 下午5:25:47 2018年3月13日
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

	/**
	 * @file_name Zookeeper_Create_API_Usage.java × @method_name main
	 * @author tenacity.free_zhang
	 * @time 下午5:25:47 2018年3月13日
	 * @desc main测试方法
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Create_API_Usage());
		System.out.println(zooKeeper.getState());

		countDownLatch.await();

		zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
				new IStringCallback(), "I am context. ");

		zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
				new IStringCallback(), "I am context. ");

		zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
				new IStringCallback(), "I am context. ");

		Thread.sleep(Integer.MAX_VALUE);
	}

}

class IStringCallback implements AsyncCallback.StringCallback {
	/**
	 * @file_name Zookeeper_Create_API_Usage.java × @method_name processResult
	 * @author tenacity.free_zhang
	 * @time 下午5:52:43 2018年3月13日
	 * @desc TODO
	 * @see org.apache.zookeeper.AsyncCallback.StringCallback#processResult(int,
	 *      java.lang.String, java.lang.Object, java.lang.String)
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
	}

}
