package pac.main;


import static java.lang.Thread.sleep;

public class Philosopher implements Runnable {

    private Fork left; // вилка слева
    private Fork right; // вилка справа
    private int satiety = 0; // уровень сытости
    private int MAX_SATIETY = 3; // максимальный уровень сытости
    private String name;
    private int number;
    private static int count;
    private StringBuilder log;

    public Philosopher(String name, Fork left, Fork right) {
        this.left = left;
        this.right = right;
        this.name = name;
        number = ++count;
        log = new StringBuilder();
    }

    public void run() {
        try {
            while (this.satiety < MAX_SATIETY) { // проверяем, не насытился ли философ

                // проверяем состояние атомарного поля левой вилки. если равно 0, то устаналиваем значение, равное number философа
                if (left.getOwnerNumber().compareAndSet(0, number)) {

                    // аналогично проверяем состояние правой вилки
                    if (right.getOwnerNumber().compareAndSet(0, number)) {

                        // если обе вилки были свободны, то выводим сообщение, что философ взял их в руку и ест 20 мс
                        System.out.println(">>> " + name + " взял " + left + " и " + right + ", ЕСТ " + ++satiety + " РАЗ \t\t");
                        log.append("* поел ");
                        sleep(20);

                        // после того, как философ поел, он кладет вилки на стол и устанавливает значение атомарного поля = 0
                        System.out.println("<<< " + name + " кладет " + left + " и " + right);
                        right.getOwnerNumber().compareAndSet(number, 0);
                        left.getOwnerNumber().compareAndSet(number, 0);

                        // ждет еще 20 мс, чтобы другие философы успели взять освободившиеся вилки
                        sleep(20);
                    } else {
                        // если правая вилка занята, атомарное значение левой вилки устанавливается равным 0 и философ думает 18 мс
                        left.getOwnerNumber().compareAndSet(number, 0);
                        think();
                    }
                }
                else {
                    // если левая вилка занята, философ думает 18 мс
                    think();
                }
            }

            // завершается поток сообщением, что философ насытился и выводится вся история о нем
            log.append("* СЫТЫЙ");
            System.out.println(name + ": " + log);
        } catch (InterruptedException e) {
        throw new RuntimeException(e);
        }
    }

    // метод, чтобы философ думал
    public void think(){
        System.out.println(this + " думает \t\t");
        log.append("* подумал ");
        try {
            sleep(18);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
        public String toString () {
            StringBuilder sb = new StringBuilder();
            sb.append(name);

            // проверяем, какое значение атомарного поля установлено у вилки, когда философ есть ею
            if (left.getOwnerNumber().doubleValue() == number)
                sb.append(": ").append(left.toString());
            if (right.getOwnerNumber().doubleValue() == number)
                sb.append(": ").append(right.toString());
            return sb.toString();
         }
    }

