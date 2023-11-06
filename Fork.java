package pac.main;

import java.util.concurrent.atomic.AtomicInteger;

public class Fork {
    private AtomicInteger ownerNumber; // номер философа. Проверяется и изменяется философами атомарно,
                                // что исключает возможность взятия вилки двумя философами
    private static int count;
    private int index;

    public Fork(){
        index = ++count;
        ownerNumber = new AtomicInteger(0);
    }

    public AtomicInteger getOwnerNumber() {
        return ownerNumber;
    }

    @Override
    public String toString(){
        return "Вилка " + index;
    }
}
