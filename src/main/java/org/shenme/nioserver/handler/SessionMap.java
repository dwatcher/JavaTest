package org.shenme.nioserver.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class SessionMap {

    private static SessionMap sessionMap = null;

    private static Map<String, IoSession> map = new HashMap<String, IoSession>();

    // 构�?私有�?单例
    private SessionMap() {
    }

    /**
     * @Description: 获取唯一实例
     * @author whl
     * @date 2014-9-29 下午1:29:33
     */
    public static SessionMap newInstance() {

        if (sessionMap == null) {
            sessionMap = new SessionMap();
        }
        return sessionMap;
    }

    /**
     * @Description: 保存session会话
     * @author whl
     * @date 2014-9-29 下午1:31:05
     */
    public void addSession(String key, IoSession session) {
        System.out.println("保存会话到SessionMap单例---key=" + key);
        this.map.put(key, session);
        // sessionMap.addSession(key, session);
    }

    /**
     * @Description: 根据key查找缓存的session
     * @author whl
     * @date 2014-9-29 下午1:31:55
     */
    public IoSession getSession(String key) {
        System.out.println("获取会话从SessionMap单例---key=" + key);
        System.out.println("get:");

        return this.map.get(key);
        // return sessionMap.getSession(key);
    }

    /**
     * @Description: 发�?消息到客户端
     * @author whl
     * @date 2014-9-29 下午1:57:51
     */
    public void sendMessage(String[] keys, Object message) {
        for (String key : keys) {
            IoSession session = getSession(key);

            System.out.println("反向发�?消息到客户端Session---key=" + key + "----------消息=" + message);
            if (session == null) {
                System.out.println("66666");
                return;
            }
            System.out.println("99999");
            session.write(message);

        }
    }

}
