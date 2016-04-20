package com.mydemo.zookeeper.test;

import java.io.IOException;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkClientTest {

	protected static final Logger logger = LoggerFactory.getLogger(ZkClientTest.class);
    
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		testZkClientConnect();
	}

	private static void testZkClientConnect() throws IOException, KeeperException, InterruptedException {
		String connect = "192.168.233.128:"+2181;//"localhost:" + ClientBase.CLIENT_PORT
		// 创建一个与服务器的连接
		 ZooKeeper zk = new ZooKeeper(connect, 
		        ClientBase.CONNECTION_TIMEOUT, new Watcher() {
		            // 监控所有被触发的事件
		            public void process(WatchedEvent event) { 
		                System.out.println("已经触发了" + event.getType() + "事件！"); 
		            } 
		        }); 
		 // 创建一个目录节点
		 zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 
		 // 创建一个子目录节点
		 zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 System.out.println(new String(zk.getData("/testRootPath",false,null))); 
		 
		 // 取出子目录节点列表
		 System.out.println(zk.getChildren("/testRootPath",true)); 
		 
		 // 修改子目录节点数据
		 zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
		 System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]"); 
		 
		 // 创建另外一个子目录节点
		 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
		 
		 // 删除子目录节点
		 zk.delete("/testRootPath/testChildPathTwo",-1); 
		 zk.delete("/testRootPath/testChildPathOne",-1); 
		 
		 // 删除父目录节点
		 zk.delete("/testRootPath",-1); 
		 
		 // 关闭连接
		 zk.close();
		 
		 
		 {
			 /*
			 
			 已经触发了None事件！
			 testRootData
			 [testChildPathOne]
			 目录节点状态：[81604378647,81604378647,1448648968443,1448648968443,0,1,0,0,12,1,81604378648
			 ]
			 已经触发了NodeChildrenChanged事件！
			 testChildDataTwo
			 已经触发了NodeDeleted事件！
			 已经触发了NodeDeleted事件！
			 
			 */
		 }
		 
		 {
			 
		 }
	}
	
	/**
	 *  Leader Election 关键代码
	 * @author sheng.ding
	 * @version V1.0
	 * @date 2015年11月27日 上午10:56:00
	 * @param TODO (形参说明)
	 * @return void    返回类型
	 * @throws TODO (可能抛出的异常)
	 */
	static void findLeader(ZooKeeper zk) throws InterruptedException { 
		/*String root = "";
        byte[] leader = null; 
        try { 
            leader = zk.getData(root + "/leader", true, null); 
        } catch (Exception e) { 
            logger.error("Exception:"+e); 
        } 
        if (leader != null) { 
            following(); 
        } else { 
            String newLeader = null; 
            try { 
                byte[] localhost = InetAddress.getLocalHost().getAddress(); 
                newLeader = zk.create(root + "/leader", localhost, 
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL); 
            } catch (Exception e) { 
                logger.error("Exception:"+e); 
            } 
            if (newLeader != null) { 
                leading(); 
            } else { 
                mutex.wait(); 
            } 
        }*/ 
    }
	
	/**
	 *  同步锁的关键代码
	 * @author Administrator
	 *
	 */
	static void getLock(ZooKeeper zk) throws KeeperException, InterruptedException{ 
        /*List<String> list = zk.getChildren(root, false); 
        String[] nodes = list.toArray(new String[list.size()]); 
        Arrays.sort(nodes); 
        if(myZnode.equals(root+"/"+nodes[0])){ 
            doAction(); 
        } 
        else{ 
            waitForLock(nodes[0]); 
        } */
    } 
    void waitForLock(ZooKeeper zk,String lower) throws InterruptedException, KeeperException {
        /*Stat stat = zk.exists(root + "/" + lower,true); 
        if(stat != null){ 
            mutex.wait(); 
        } 
        else{ 
            getLock(); 
        }*/ 
    }
    
    /**
     * 队列管理
     * @author sheng.ding
     * @version V1.0
     * @date 2015年11月27日 上午11:03:09
     * @param TODO (形参说明)
     * @return void    返回类型
     * @throws TODO (可能抛出的异常)
     */
    static void addQueue(ZooKeeper zk) throws KeeperException, InterruptedException{ 
        /*zk.exists(root + "/start",true); 
        zk.create(root + "/" + name, new byte[0], Ids.OPEN_ACL_UNSAFE, 
        CreateMode.EPHEMERAL_SEQUENTIAL); 
        synchronized (mutex) { 
            List<String> list = zk.getChildren(root, false); 
            if (list.size() < size) { 
                mutex.wait(); 
            } else { 
                zk.create(root + "/start", new byte[0], Ids.OPEN_ACL_UNSAFE,
                 CreateMode.PERSISTENT); 
            } 
        }*/ 
    }
    
	class ClientBase{
		public static final int CLIENT_PORT = 20881;
		public static final int CONNECTION_TIMEOUT = 1000 * 60 * 1;
		
	}
}
