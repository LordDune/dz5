package pac.main;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        // создаем 5 вилок
        Fork f1 = new Fork();
        Fork f2 = new Fork();
        Fork f3 = new Fork();
        Fork f4 = new Fork();
        Fork f5 = new Fork();

        // создаем 5 философов, каждый из которых смотрит на две рядом лежащие вилки
        Philosopher p1 = new Philosopher("Платон (1,2)",f1, f2);
        Philosopher p2 = new Philosopher("Аристотель (2,3)",f2, f3);
        Philosopher p3 = new Philosopher("Сократ (3,4)",f3, f4);
        Philosopher p4 = new Philosopher("Ницше (4,5)",f4, f5);
        Philosopher p5 = new Philosopher("Конфуций (5,1)",f5, f1);

        // создаем список философов для удобства запуска потоков
        ArrayList<Philosopher> philosophers = new ArrayList(Arrays.asList(p1,p2,p3,p4,p5));

        // запускаем потоки философов
        for (Philosopher p: philosophers) {
            new Thread(p).start();
        }
    }
}

//        Конфуций (5,1) думает
//        Ницше (4,5) думает
//        Аристотель (2,3) думает
//        Аристотель (2,3) думает
//        Конфуций (5,1) думает
//        Ницше (4,5) думает
//        >>> Платон (1,2) взял Вилка 1 и Вилка 2, ЕСТ 1 РАЗ
//        >>> Сократ (3,4) взял Вилка 3 и Вилка 4, ЕСТ 1 РАЗ
//        Ницше (4,5) думает
//        Конфуций (5,1) думает
//        Аристотель (2,3) думает
//        <<< Платон (1,2) кладет Вилка 1 и Вилка 2
//        <<< Сократ (3,4) кладет Вилка 3 и Вилка 4
//        Ницше (4,5) думает
//        >>> Конфуций (5,1) взял Вилка 5 и Вилка 1, ЕСТ 1 РАЗ
//        >>> Аристотель (2,3) взял Вилка 2 и Вилка 3, ЕСТ 1 РАЗ
//        Платон (1,2) думает
//        Сократ (3,4) думает
//        Ницше (4,5) думает
//        <<< Аристотель (2,3) кладет Вилка 2 и Вилка 3
//        <<< Конфуций (5,1) кладет Вилка 5 и Вилка 1
//        >>> Сократ (3,4) взял Вилка 3 и Вилка 4, ЕСТ 2 РАЗ
//        >>> Платон (1,2) взял Вилка 1 и Вилка 2, ЕСТ 2 РАЗ
//        Ницше (4,5) думает
//        Конфуций (5,1) думает
//        Аристотель (2,3) думает
//        <<< Сократ (3,4) кладет Вилка 3 и Вилка 4
//        <<< Платон (1,2) кладет Вилка 1 и Вилка 2
//        >>> Ницше (4,5) взял Вилка 4 и Вилка 5, ЕСТ 1 РАЗ
//        Конфуций (5,1) думает
//        >>> Аристотель (2,3) взял Вилка 2 и Вилка 3, ЕСТ 2 РАЗ
//        Сократ (3,4) думает
//        Платон (1,2) думает
//        <<< Ницше (4,5) кладет Вилка 4 и Вилка 5
//        Конфуций (5,1) думает
//        <<< Аристотель (2,3) кладет Вилка 2 и Вилка 3
//        >>> Сократ (3,4) взял Вилка 3 и Вилка 4, ЕСТ 3 РАЗ
//        >>> Платон (1,2) взял Вилка 1 и Вилка 2, ЕСТ 3 РАЗ
//        Конфуций (5,1) думает
//        Ницше (4,5) думает
//        Аристотель (2,3) думает
//        <<< Сократ (3,4) кладет Вилка 3 и Вилка 4
//        <<< Платон (1,2) кладет Вилка 1 и Вилка 2
//        >>> Конфуций (5,1) взял Вилка 5 и Вилка 1, ЕСТ 2 РАЗ
//        Ницше (4,5) думает
//        >>> Аристотель (2,3) взял Вилка 2 и Вилка 3, ЕСТ 3 РАЗ
//        Платон (1,2): * поел * подумал * поел * подумал * поел * СЫТЫЙ
//        Сократ (3,4): * поел * подумал * поел * подумал * поел * СЫТЫЙ
//        <<< Конфуций (5,1) кладет Вилка 5 и Вилка 1
//        >>> Ницше (4,5) взял Вилка 4 и Вилка 5, ЕСТ 2 РАЗ
//        <<< Аристотель (2,3) кладет Вилка 2 и Вилка 3
//        Конфуций (5,1) думает
//        Аристотель (2,3): * подумал * подумал * подумал * поел * подумал * поел * подумал * поел * СЫТЫЙ
//        <<< Ницше (4,5) кладет Вилка 4 и Вилка 5
//        >>> Конфуций (5,1) взял Вилка 5 и Вилка 1, ЕСТ 3 РАЗ
//        Ницше (4,5) думает
//        <<< Конфуций (5,1) кладет Вилка 5 и Вилка 1
//        >>> Ницше (4,5) взял Вилка 4 и Вилка 5, ЕСТ 3 РАЗ
//        Конфуций (5,1): * подумал * подумал * подумал * поел * подумал * подумал * подумал * подумал * поел * подумал * поел * СЫТЫЙ
//        <<< Ницше (4,5) кладет Вилка 4 и Вилка 5
//        Ницше (4,5): * подумал * подумал * подумал * подумал * подумал * подумал * поел * подумал * подумал * поел * подумал * поел * СЫТЫЙ
