package io.mycat.config.loader.zkprocess.comm;

/**
 * 当前zk的配制参数信息
* 源文件名：ZkParamCfg.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年9月17日
* 修改作者：liujun
* 修改日期：2016年9月17日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public enum ZkParamCfg {

    /**
     * zk是否启用标识
    * @字段说明 ZK_CFG_OPEN
    */
    ZK_CFG_FLAG("loadZk"),

    MYCAT_SERVER_TYPE("type"),

    MYCAT_BOOSTER_DATAHOSTS("boosterDataHosts"),

    /**
     * 集群中所有节点的名称信息
    * @字段说明 ZK_CFG_CLUSTER_NODES
    */
    ZK_CFG_CLUSTER_NODES("clusterNodes"),

    /**
     * 集群中所有节点的名称信息的分隔符
    * @字段说明 ZK_CFG_CLUSTER_NODES
    */
    ZK_CFG_CLUSTER_NODES_SEPARATE(","),
    
    
    /**
     * zk配制的url地址信息
    * @字段说明 ZK_CFG_URL
    */
    ZK_CFG_URL("zkURL"),
    
    /**
     * zk集群的命名空间
     */
    ZK_NAMESPACE("zknamespace"),

    /**
     * 集群的id
    * @字段说明 ZK_CFG_CLUSTERID
    */
    ZK_CFG_CLUSTERID("clusterId"),
    
    /**
     * 当前mycat节点的id
    * @字段说明 zk_CFG_MYID
    */
    ZK_CFG_MYID("myid"),

    /**
     * 集群服务器数量
     */
    ZK_CFG_CLUSTER_SIZE("clusterSize"),
    
    /**
     * 等待重试的间隔时间的初始值
     */
    BASE_SLEEP_TIME_MILLISECONDS("baseSleepTimeMilliseconds"),
    
    /**
     * 等待重试的间隔时间的最大值
     */
    MAX_SLEEP_TIME_MILLISECONDS("maxSleepTimeMilliseconds"),
    
    /**
     * 最大重试次数
     */
    MAX_RETRIES("maxRetries"),
    
    /**
     * 会话超时时间
     */
    SESSION_TIMEOUT_MILLISECONDS("sessionTimeoutMilliseconds"),
    
    /**
     * 连接超时时间
     */
    CONNECTION_TIMEOUT_MILLISECONDS("connectionTimeoutMilliseconds")
    
    ;

    private ZkParamCfg(String key) {
        this.key = key;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
