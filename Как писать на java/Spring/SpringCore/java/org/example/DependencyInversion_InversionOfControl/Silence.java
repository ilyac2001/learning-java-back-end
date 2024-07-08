package org.example.DependencyInversion_InversionOfControl;

public class Silence implements IMusic {

    private Silence(){ //вот это прекол конечно, что spring может вызвать private конструктор
        System.out.println("Java конструктор");
    }

    public static Silence Factory(){ //если scope="singleton" то метод вызывается иденожды (по-моему реализация сомнительная, читать такое сложно, не очевидно)
        System.out.println("создание объекта фабричным методом");
        return new Silence();
    }

    private void DoMyInit(){ //если емеет аргументы, то падает на этапе выполнения
        System.out.println("Spring init-method bean");
    }

    public void DoMyDestroy(){ //аналогично про аргументы И не вызывается для scope
        System.out.println("Spring destroy-method bean");
    }
}
