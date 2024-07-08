package org.example.DependencyInversion_InversionOfControl;

import java.util.ArrayList;
import java.util.List;

public class Player {
    List<IMusic> music = new ArrayList<>();
    private int volume;

    public void setMusic(List<IMusic> music) {
        this.music = music;
    }

    public void Play(){
        System.out.println("Звучит:");
        for(IMusic m: music){
            System.out.println(m.GetSound());
        }
        System.out.println("--------------");
    }

    public void setVolume(int volume) {
        if(volume < 0){return;}
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}
