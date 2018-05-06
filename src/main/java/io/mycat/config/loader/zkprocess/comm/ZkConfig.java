package io.mycat.config.loader.zkprocess.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import io.mycat.config.loader.zkprocess.zktoxml.ZktoXmlMain;
import io.mycat.util.StringUtil;


/**
 * 进行zk的配制信息
* 源文件名：ZkConfig.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年9月15日
* 修改作者：liujun
* 修改日期：2016年9月15日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ZkConfig {
    /**
     * 日志信息
    * @字段说明 LOGGER
    */
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkConfig.class);

    private static final String ZK_CONFIG_FILE_NAME = "/myid.properties";
    
    private static final int DEFAULT_BASE_SLEEP_TIME = 1000;
    private static final int DEFAULT_MAX_SLEEP_TIME = 3000;
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final int DEFAULT_SESSION_TIMEOUT = 60000;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 10000;

    private ZkConfig() {
    }

    /**
     * 实例对象信息
    * @字段说明 ZKCFGINSTANCE
    */
    private static ZkConfig ZKCFGINSTANCE = new ZkConfig();


    /**
     * myid的属性文件信息
    * @字段说明 ZKPROPERTIES
    */
    private static Properties ZKPROPERTIES = null;

    static {
        ZKPROPERTIES = LoadMyidPropersites();
    }

    public void initZk()
    {
        try {
            if (Boolean.parseBoolean(ZKPROPERTIES.getProperty(ZkParamCfg.ZK_CFG_FLAG.getKey()))) {
                ZktoXmlMain.loadZktoFile();
            }
        } catch (Exception e) {
            LOGGER.error("error:",e);
        }
    }

    /**
     * 获得实例对象信息
    * 方法描述
    * @return
    * @创建日期 2016年9月15日
    */
    public  static ZkConfig getInstance() {

        return ZKCFGINSTANCE;
    }

    /**
     * 获取myid属性文件中的属性值 
    * 方法描述
    * @param param 参数信息
    * @return
    * @创建日期 2016年9月15日
    */
    public String getValue(ZkParamCfg param) {
        if (null != param) {
            return ZKPROPERTIES.getProperty(param.getKey());
        }

        return null;
    }

    /**
     * 加载myid配制文件信息
    * 方法描述
    * @return
    * @创建日期 2016年9月15日
    */
    private static Properties LoadMyidPropersites() {
        Properties pros = new Properties();

        try (InputStream configIS = ZkConfig.class.getResourceAsStream(ZK_CONFIG_FILE_NAME)) {
            if (configIS == null) {
                return null;
            }

            pros.load(configIS);
        } catch (IOException e) {
            LOGGER.error("ZkConfig LoadMyidPropersites error:", e);
            throw new RuntimeException("can't find myid properties file : " + ZK_CONFIG_FILE_NAME);
        }

        // validate
        String zkURL = pros.getProperty(ZkParamCfg.ZK_CFG_URL.getKey());
        String myid = pros.getProperty(ZkParamCfg.ZK_CFG_MYID.getKey());

        String clusterId = pros.getProperty(ZkParamCfg.ZK_CFG_CLUSTERID.getKey());

        if (Strings.isNullOrEmpty(clusterId) ||Strings.isNullOrEmpty(zkURL) || Strings.isNullOrEmpty(myid)) {
            throw new RuntimeException("clusterId and zkURL and myid must not be null or empty!");
        }
        return pros;

    }
    
    /** 获取与ZK集群相关的属性 */
    
    public final String getZkNamespace() {
    	return getValue(ZkParamCfg.ZK_NAMESPACE);
    }
    
    public final String getZkURL() {
    	return getValue(ZkParamCfg.ZK_CFG_URL);
    }
    
    /**
     * 获取
     * @return
     */
    public final String getClusterId() {
    	return getValue(ZkParamCfg.ZK_CFG_CLUSTERID);
    }
    
    /**
     * 获取集群内当前实例Id
     * @return
     */
    public final String getInstanceId() {
    	return getValue(ZkParamCfg.ZK_CFG_MYID);
    }
    
    /**
     * 获取等待重试的间隔时间的初始值
     * @return
     */
    public final int getBaseSleepTimeMilliseconds() {
    	String str = getValue(ZkParamCfg.BASE_SLEEP_TIME_MILLISECONDS);
    	return StringUtil.isEmpty(str) ? DEFAULT_BASE_SLEEP_TIME : Integer.parseInt(str);
    }
    
    /**
     * 等待重试的间隔时间的最大值 
     * @return
     */
    public final int getMaxSleepTimeMilliseconds() {
    	String str = getValue(ZkParamCfg.MAX_SLEEP_TIME_MILLISECONDS);
    	return StringUtil.isEmpty(str) ? DEFAULT_MAX_SLEEP_TIME : Integer.parseInt(str);
    }
    
    /**
     * 最大重试次数
     * @return
     */
    public final int getMaxRetries() {
    	String str = getValue(ZkParamCfg.MAX_RETRIES);
    	return StringUtil.isEmpty(str) ? DEFAULT_MAX_RETRIES : Integer.parseInt(str);
    }
    
    /**
     * 获取session过期时间
     * @return
     */
    public final int getSessionTimeoutMilliseconds() {
    	String str = getValue(ZkParamCfg.SESSION_TIMEOUT_MILLISECONDS);
    	return StringUtil.isEmpty(str) ? DEFAULT_SESSION_TIMEOUT : Integer.parseInt(str);
    }
    
    /**
     * 连接超时时间
     * @return
     */
    public final int getConnectionTimeoutMilliseconds() {
    	String str = getValue(ZkParamCfg.CONNECTION_TIMEOUT_MILLISECONDS);
    	return StringUtil.isEmpty(str) ? DEFAULT_CONNECTION_TIMEOUT : Integer.parseInt(str);
    }
    
    /**
     * 集群数量不从这里获取，实时从ZK服务器获取
     * @return
     */
//    public final int getClusterSize() {
//    	
//    }

    public static void main(String[] args) {
        String zk = ZkConfig.getInstance().getValue(ZkParamCfg.ZK_CFG_CLUSTERID);
        System.out.println(zk);
    }

}
