package com.ruoyi;

import com.ruoyi.common.config.Global;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import static com.ruoyi.moudleSet.utils.ConsoleOutputPicture.getData;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication {

    private static String Console = Global.getConfig("ConsoleOutputPicture.Picture");

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.err.println(
                "$$\\      $$\\                                             $$\\       $$\\                        $$$$$$\\                          $$\\     \n" +
                        "$$$\\    $$$ |                                            $$ |      $$ |                      $$  __$$\\                         $$ |    \n" +
                        "$$$$\\  $$$$ |       $$$$$$\\        $$\\   $$\\        $$$$$$$ |      $$ |       $$$$$$\\        $$ /  \\__|       $$$$$$\\        $$$$$$\\   \n" +
                        "$$\\$$\\$$ $$ |      $$  __$$\\       $$ |  $$ |      $$  __$$ |      $$ |      $$  __$$\\       \\$$$$$$\\        $$  __$$\\       \\_$$  _|  \n" +
                        "$$ \\$$$  $$ |      $$ /  $$ |      $$ |  $$ |      $$ /  $$ |      $$ |      $$$$$$$$ |       \\____$$\\       $$$$$$$$ |        $$ |    \n" +
                        "$$ |\\$  /$$ |      $$ |  $$ |      $$ |  $$ |      $$ |  $$ |      $$ |      $$   ____|      $$\\   $$ |      $$   ____|        $$ |$$\\ \n" +
                        "$$ | \\_/ $$ |      \\$$$$$$  |      \\$$$$$$  |      \\$$$$$$$ |      $$ |      \\$$$$$$$\\       \\$$$$$$  |      \\$$$$$$$\\         \\$$$$  |\n" +
                        "\\__|     \\__|       \\______/        \\______/        \\_______|      \\__|       \\_______|       \\______/        \\_______|         \\____/ "
        );
        // 下面方法是输出图片的Api
        // getData(Console);
    }
}