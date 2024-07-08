package org.example.DependencyInversion_InversionOfControl;

public interface IMusic {
    default String GetSound(){
        return "треск сверчков :(";
    };
}
