package org.example.Annotations;

import org.springframework.stereotype.Component;

@Component
public class Rock implements IMusic {
    @Override
    public String GetSound(){
        return "РООООООООООООООООООК!";
    }
}
