package org.shenme.nioserver.handler;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.shenme.nioserver.domain.NioConstant;
import org.shenme.nioserver.util.CRC;

public class ServerHandler extends IoHandlerAdapter {

    private static final String String = null;
    private CRC crc;
    private static String hexString = "0123456789ABCDEF";

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String content = message.toString();
        System.out.println("服务端接收到的一开始的数据为: " + content + "----结束啦!!");
        // content = content.subSequence(content.lastIndexOf("##"), content.length()).toString();
        System.out.println("服务端session接收到的截取后的数据为: " + session + "----结束啦!!");

        // 获取客户端发过来的key
        String key = message.toString();
        System.out.println("message :" + message.toString());
        String carPark_id = key.substring(key.indexOf("=") + 1);
        System.out.println("carPark_id :" + carPark_id);

        // 保存客户端的会话session
        SessionMap sessionMap = SessionMap.newInstance();
        sessionMap.addSession(carPark_id, session);

        crc = new CRC();
        // CRC效验
        if (!content.equals("") && content.contains(NioConstant.MSG_HEADER) && content.substring(0, 2).equals(NioConstant.MSG_HEADER)) {

            String oldCrc = content.substring(content.lastIndexOf(NioConstant.MSG_END) + 2, content.lastIndexOf(NioConstant.MSG_END) + 6);
            content = content.substring(6, content.lastIndexOf(NioConstant.MSG_END)) + NioConstant.MSG_END;

            String crcChar = crc.getCrc(content.getBytes(), 4);

            if (oldCrc.equals(crcChar)) {

                // String setTime = dhRtdServiceImpl.setUpComputer(map);
                // 返回给下位机 设置时间
                // session.write(setTime);

                String str = "";
                System.out.println("getData");
                

                // session.write(new String("lalla".getBytes(),"UTF-8"));

                // if(map.containsKey("ExeRtn")){
                // String exerth = (String) map.get("ExeRtn");
                // str = dhRtdServiceImpl.lowerLoginReturn(map);
                // System.out.println("给下位机反馈登录信息:"+str+crcChar);
                // session.write(str+crcChar);
                // if(exerth.equals("1&&")){
                // System.out.println("CN的值:"+cn);
                // if (cn != null && cn.equals("9021")) {
                // str = dhRtdServiceImpl.lowerLoginReturn(map);
                // System.out.println("给下位机反馈登录信息:"+str+crcChar);
                // session.write(str+crcChar);
                // } else if (cn != null && cn.equals("2011")) {
                //
                // } else if (cn != null && cn.equals("9011")) {
                //
                // } else if (cn != null && cn.equals("9012")) {
                // str = dhRtdServiceImpl.lowerLoginReturn(map);
                // session.write(str+crcChar);
                // }
                //
                // }
                // }else{
                // if (cn != null && cn.equals("2011")) {
                //
                // } else if (cn != null && cn.equals("9011")) {
                //
                // }else if (cn != null && cn.equals("9021")) {
                // session.write(setTime);
                // }
                // }
                super.messageReceived(session, message);
            }
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("服务端发送信息成功: " + message.toString());
        super.messageSent(session, message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("服务端发送异常: " + cause.getMessage());
        super.exceptionCaught(session, cause);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("服务端与客户端创建连接...");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("服务端与客户端连接打开...");
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("服务端与客户端连接关闭...");
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // System.out.println("服务端进入空闲状态: " + session.getIdleCount(status));
        super.sessionIdle(session, status);
    }

    public static String decode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }
}