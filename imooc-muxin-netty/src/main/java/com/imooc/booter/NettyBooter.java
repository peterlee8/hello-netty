package com.imooc.booter;

import com.imooc.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author peterLee
 * @Date 2018/10/11 下午12:38
 * @Describtion :
 */
@Component
public class NettyBooter  implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() ==null){
            try {
                WSServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
