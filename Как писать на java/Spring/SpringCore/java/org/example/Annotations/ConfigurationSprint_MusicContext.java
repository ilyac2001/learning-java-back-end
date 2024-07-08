package org.example.Annotations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("org.example.Annotations")
@PropertySource("classpath:player.properties")
public class ConfigurationSprint_MusicContext {
    @Bean
    public Classic classic() { //classic - bean id
        return new Classic();
    }

    @Bean
    public List<IMusic> musicList() {
        return Arrays.asList(classic());
    }

    @Bean
    public Player player(@Qualifier("rock") IMusic music_r, @Qualifier("classic") IMusic music_c) {
        Player player = new Player(music_r, music_c);
        player.setMusicList(musicList());
        return player;
    }

}
