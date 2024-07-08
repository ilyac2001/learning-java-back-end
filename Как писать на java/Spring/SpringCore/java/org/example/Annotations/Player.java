package org.example.Annotations;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Player {
    //@Autowired //почему работает с private? - читай механизм рефлексии Java Reflection API
    private IMusic music1, music2;
    @Value("${Player.value}")
    private int volume;

    private List<IMusic> musicList;

    public void setMusicList(List<IMusic> musicList) {
        this.musicList = musicList;
    }

    public List<IMusic> getMusicList() {
        return musicList;
    }

    @Autowired //если в классе @Configuration обозначено создание бина, этот конструктор вызовится, как обычный конструткор, а ни как конструктор при создании бина
    public Player(@Qualifier("rock") IMusic music_r, @Qualifier("classic") IMusic music_c) {
        this.music1 = music_r;
        this.music2 = music_c;
    }

    public void Play(){
        System.out.println("Звучит:\n" + this.music1.GetSound() + "\n" + this.music2.GetSound());
    }

    public void setVolume(int volume) {
        if(volume < 0){return;}
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}
