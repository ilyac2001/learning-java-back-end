package org.example.Annotations;

import org.springframework.stereotype.Component;

//@Component
public class Classic implements IMusic {
    @Override
    public String GetSound(){
        return "спокойствие и умерОтВаренья";
    }
}
