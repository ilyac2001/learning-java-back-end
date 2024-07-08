package org.example;

import org.example.Annotations.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSprintHW {
    public static void main(String[] arg){
        /*
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBeanSomeClass tb = context.getBean("testBean", TestBeanSomeClass.class);
        System.out.println(tb.getName());
        context.close();
        System.out.println("-------------------------------");

        ClassPathXmlApplicationContext music_context = new ClassPathXmlApplicationContext("musicContext.xml");
        IMusic music = music_context.getBean("musicBean2", IMusic.class);
        IMusic music2 = music_context.getBean("musicBean2", IMusic.class);
        Player p = music_context.getBean("playerBean", Player.class); //зависимость определена в конфигурации
        Player p2 = music_context.getBean("playerBean", Player.class);
        System.out.println(p2.toString() + "\n" + p.toString());
        p2.Play();
        music_context.close();
        System.out.println("-------------------------------");
        */

        ClassPathXmlApplicationContext music_context_annotations_xml = new ClassPathXmlApplicationContext("musicContext_Annotations.xml");
        Player p = music_context_annotations_xml.getBean("player", Player.class);
        p.Play();
        System.out.println("громкость = " + p.getVolume());
        music_context_annotations_xml.close();
        System.out.println("-------------------------------");

        AnnotationConfigApplicationContext music_context_annotation_javaCode = new AnnotationConfigApplicationContext(ConfigurationSprint_MusicContext.class);
        Player p2 = music_context_annotation_javaCode.getBean("player", Player.class);
        p2.Play();
        System.out.println("громкость = " + p2.getVolume());
        System.out.println(p == p2);
        System.out.println(p2.getMusicList().get(0).GetSound());
    }
}
