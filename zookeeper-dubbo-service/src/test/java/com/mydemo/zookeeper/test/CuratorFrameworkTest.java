package com.mydemo.zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;


	/*	<dependency>
			<groupId>com.netflix.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>1.1.10</version>
			</dependency>
		<dependency>
			<groupId>com.netflix.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>1.1.10</version>
			<classifier>sources</classifier>
		</dependency>
*/
import com.netflix.curator.RetryPolicy;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.ExponentialBackoffRetry;
import com.netflix.curator.retry.RetryNTimes;



/*
<!-- 
	<dependency>
		<groupId>org.apache.curator</groupId>
		<artifactId>curator-framework</artifactId>
		<version>2.8.0</version>
	</dependency>
	<dependency>
	  <groupId>org.apache.curator</groupId>
	  <artifactId>curator-framework</artifactId>
	  <version>2.8.0</version>
	  <classifier>sources</classifier>
	  </dependency>
	   -->
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
*/

public class CuratorFrameworkTest {

	
	

	public static void main(String[] args) throws Exception {
		String path = "/test_path";
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("192.168.233.128::2181")
				.namespace("brokers")
				.retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
				.connectionTimeoutMs(1000 * 60)
				.build();
		// 启动 上面的namespace会作为一个最根的节点在使用时自动创建
		client.start();

		// 创建一个节点
		client.create().forPath("/head", new byte[0]);

		// 异步地删除一个节点
		client.delete().inBackground().forPath("/head");

		// 创建一个临时节点
		client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
				.forPath("/head/child", new byte[0]);

		// 取数据
		client.getData().watched().inBackground().forPath("/test");

		// 检查路径是否存在
		client.checkExists().forPath(path);

		// 异步删除
		client.delete().inBackground().forPath("/head");

		// 注册观察者，当节点变动时触发
		client.getData().usingWatcher(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getType() + "node is changed");
			}
		}).inBackground().forPath("/test");

		// 结束使用
		client.close();
	}  

    // Fluent 风格 
    
    // 封装
    
	/**
	 * 创建CuratorFramework对象
	 * @author sheng.ding
	 * @version V1.0
	 * @date 2015年11月27日 下午2:43:42
	 * @param TODO (形参说明)
	 * @return CuratorFramework    返回类型
	 * @throws IOException 
	 * @throws TODO (可能抛出的异常)
	 */
	public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs) throws IOException {
        return CuratorFrameworkFactory.builder().connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }
	
	// -------------代码调用示例说明
	{
		// 调用代码如下：
		CuratorFramework client = null;
		try {
			client = createWithOptions("10.154.156.180:2181", new ExponentialBackoffRetry(1000, 3), 1000, 1000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.start();
		
		
		// 如果需要创建新目录节点 依然是Fluent风格
		try {
			client.create().forPath("/curator", new byte[0]);
			client.create().withMode(CreateMode.PERSISTENT).forPath("/curator/childOne", new byte[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 非Fluent 风格 
	// 当然创建zk也可以不使用Fluent风格
	public static CuratorFramework createSimple(String connectionString) throws IOException {        
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);        
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
	}
	
	
}
