# Re-engineering: лабораторна робота №1

## Проблеми в наявному коді та їх вирішення

Спочатку почнемо з розбору поки що наявного коду.

На даний момент клас `humanIMB` розташований в файлі `Main.java`. Це не має грати суттєвої ролі, але в майбутньому це може завадити масштабуванню коду. Тому я б порадив винести цей клас в окремий файл, але це може бути зовсім некритично у нашому випадку (очевидно, що це зроблено просто в рамках проекту). Також про це говориться в [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html#s3.4.1-one-top-level-class).
Перейдемо спочатку до найбільш очевидного: відсутність рядка `package`. Механізм `package` допомагає організувати код в Java проектах, а також уникати дуплікацій (якщо, наприклад, є два або більше класів з таким же іменем, але які слабо зв'язні між собою). Тому для початку я додав наступний рядок в `Main.java`:

```diff
+ package com.racconbor.reengineering;
```

Наступний крок: ім'я класу. На даний момент воно у нас виглядає ось так:

```java
class humanIMB {
    // Code is omitted
}
```

Згідно всіх можливих конвенцій, ім'я класу в Java має бути в форматі `PascalCase` (або його ще називають `UpperCamelCase`). Тому, згідно правил, ми можемо написати це наступним чином:

```diff
- class humanIMB {
+ class HumanImb {
    // Code is omitted
}
```

> [!NOTE]
> В Java немає єдиного стандарту для правильного написання акронімів. Іноді в стандартних бібліотеках Sun зустрічається і `HttpURLConnection`, і `HTTPAddress`. Тому це скоріше стосується особистих переконань програміста. В даному випадку я використав саме `HumanImb` замість `HumanIMB`. Про це детально можна прочитати за [цим посиланням](https://stackoverflow.com/questions/2236807/java-naming-convention-with-acronyms).

Хоча якщо виправити невелику помилку, то ми отримаємо `HumanBmi` (від Body Mass Index). Також варто додати ключове слово `public`:

```diff
- class HumanImb
+ public class HumanBmi {
    // Code is omitted
}
```

> [!NOTE]
> Тут також можна наголосити на тому, що варто додати модифікатор `public` до класу (якщо нам обов'язково потрібно, щоб він був публічним), але це може бути не настільки важливим аспектом. Якщо не вказати модифікатор доступу, то клас буде доступний суто для пакету, в якому його було оголошено. В нашому випадку ми можемо обійтися і цим.

Далі перейдемо до полів класу. По-перше, варто змінити імена цих полів. Їхня суть пояснена в коментарях, які наведено на тих же рядках, але навіщо писати коментарі, якщо ми можемо просто написати імена цим полів прямо? По-друге, поля мають бути записаними в форматі `camelCase`. Тобто, на виході маємо наступне:

```diff
public class HumanBmi {
-   public double W; //Weight Human
+   public double weight;
-   public double H; // Height Human
+   public double height;
    // Code is omitted
}
```

Найважливіше тепер те, що код сам себе документує: ми надали чіткі імена полів, завдяки чому будь-який інженер зрозуміє код з ходу.

Наступний момент: ідентифікатори полів. Всі поля класу мають бути інкапсульовані, якщо слідувати правилам ООП. Тому ці поля стають приватними:

```diff
public class HumanBmi {
-   public double weight;
+   private double weight;
-   public double height; 
+   private double height;
    // Code is omitted
}
```

> [!WARNING]
> Поки що я пропустив та пропущу поле `private static double imb;` та інтеракції з ним, але ми до нього обов'язково повернемося, бо це справа не лише конвенцій, а практично дизайну цього класу як такого (зі сторони логіки).

Враховуючи всі попередні зміни, конструктор почне виглядати ось так:

```diff
public class HumanBmi {
    // Code omitted...
-   public humanIMB(double w, double h) {
+   public HumanBmi(double weight, double height) {
-       W = w;
-       H = h;
+       this.weight = weight;
+       this.height = height;
        imb = W / (H * H);
    }
    // Code omitted...
}
```

Тепер перейдемо до певних методів цього класу.
Як бачимо, в якості аксесорів до наших полів, коли вони стають приватними, ми можемо скористатися методами. У нас для цього використовуються такі методи, як `takeW`, `putW`, `takeH`, `putH` та `takeImb`. Ці методи названо не використовуючи вже прийняту програмістами конвенцію про методи `get/set`, які носять назву getter/setter. Відповідно, наш код має змінитися ось так:

```diff
public class HumanBmi {
    // Code omitted...
-   public double takeW() {
+   public double getWeight() {
-       return W;
+       return this.weight;
    }

-   public void putW(double w) {
+   public void setWeight(double value) {
-       W = w;
+       this.weight = value;
        imb = W / (H * H);
    }
    
-   public double takeH() {
+   public double getHeight() {
-       return H;
+       return this.height;
    }

-   public void putH(double h) {
+   public void setHeight(double value) {
-       H = h;
+       this.height = value;
        imb = W / (H * H);
    }
    
-   public static double takeImt() {
+   public static double getImt() {
        return imb;
    }
    // Code omitted...
}
```

> [!TIP]
> Варто завжди дотримуватися відстані між методами, тому ще одна помилка, яку я тут виправив, це newline після кожного методу та конструктору. Це допомагає програмістам читати код і не мати враження, що цей код пропустили через прес.

Перед тим, як ми перейдемо до рефакторингу методу `Result()`, я пропроную провести наступне: позбутися поля `private static double imb;`. Це може бути різким, але правильним рішенням, і я поясню чому. Даний код пропонує нам просту калькуляцію індексу маси тіла. Тобто, ми подаємо значення висоти та ваги і просто їх обчислюємо. Звісно ж, тут можна спростити ще сильніше, але про це вже поговоримо в іншому розділі, де я розкажу про функційне програмування. Але говорячи про даний клас, є два наступних аргументи. Ми **не маємо** тримати поле `imb` тому, що результат обчислень завжди буде вірним для одних і тих же значень `weight` та `height`, тому немає потреби щось зберігати, тим паче на рівні класу. Також, якщо взяти до уваги наступний фрагмент коду:

```java
public class HumanBmi {
    // Code omitted...
    public void setWeight(double value) {
        this.weight = value;
        imb = W / (H * H);
    }
    // Code omitted...
}
```

то можна зауважити, що тут є місце потенційного багу. Формула працює відмінно, але я не думаю, що даний метод має виконувати додатковий функціонал — він має стосуватися саме оновлення поля `weight` (хоча це непоганий натяк на реактивність). Це може призвести до того, що коли код буде масштабуватися, у нас може виникнути баг через банальну помилку програміста. Тому краще дотриматися **Single Responsibility Principle** та відмовитися від калькуляції значення індексу маси тіла.

Тому, в результаті ми отримаємо ось таке:

```diff
public class HumanBmi {
    // Code omitted...
-   private static double imb;

    public HumanBmi(double weight, double height) {
        this.weight = weight;
        this.height = height;
-       imb = W / (H * H);
    }

    // Code omitted...

    public void setWeight(double value) {
        this.weight = value;
-       imb = W / (H * H);
    }

    // Code omitted...

    public void setHeight(double value) {
        this.height = value;
-       imb = W / (H * H);
    }

    // Code omitted...
}
```

Тоді метод `getBmi` набуде такого вигляду:

```java
public class HumanBmi {
    // Code omitted

    public double getBmi() {
        return this.weight / (this.height * this.height);
    }

    // Code omitted
}
```

Тепер передостанній етап — метод `Result()`.

По-перше, його назва мусить бути в **camelCase**, а по-друге, цей метод повертає результат, тому логічніше буде його назвати `getResult()`:

```diff
public class HumanBmi {
    // Code omitted

-   public static String Result() {
+   public String getResult() { 
    // Code omitted
}
```

Ми також позбулися ключового слова `static`, тому що раніше воно було потрібним для доступу до поля `imb`.

Далі, думаю, варто зробити рефакторінг дещо глобальніше. Справа в тому, що ціль цього класу практично виконана — ми повертаємо числове значення, тобто індекс. **Але** щоб будь-хто міг зрозуміти, про що йде мова, варто це показати певними словами, тобто позначеннями.

Так як це індекс, в якого є чітко визначені межі та кількість значень обмежена, то гадаю, що варто це перетворити в `enum`. В результаті я створив `public enum BmiLevel`:

```java
public enum BmiLevel {
    UNKNOWN,
    UNDERWEIGHT,
    HEALTHY,
    OVERWEIGHT,
    OBESITY;

    // Code emitted
}
```

Також я створив метод, завдяки якому на основі числового значення можна визначити словесне значення (це практично **factory method**):

```java
public enum BmiLevel {
    // Code emitted

    public static BmiLevel fromBmiValue(double bmiValue) {
        if (bmiValue >= 18.5 && bmiValue < 25) {
            return HEALTHY;
        }
        // Code emitted
    }

    // Code emitted
}
```

Я фактично переніс логіку в даний `enum`, бо краще, щоб ця логіка була його відповідальністю. Також я зробив так, щоб даний `enum` перетворювався у ті ж символьні значення, що і поверталися в методі `Result()`:

```java
public enum BmiLevel {
    // Code emitted

    @Override
    public String toString() {
        if (this == HEALTHY) {
            return "Norm";
        }
        // Code emitted
    }

    // Code emitted
}
```

Повернемося до методу `getResult()`. В нас є два варіанти того, як його записати:

1. Ми повернемо той же `String`, метод набуває такого вигляду:

    ```java
    public class HumanBmi {
        // Code omitted

        public String getResult() {
            return BmiLevel
                .fromBmiValue(this.getBmi())
                .toString(); 
        }
    }
    ```

2. Ми будемо повертати `BmiLevel`, але тепер метод набуває такого вигляду:

    ```java
    public class HumanBmi {
        // Code omitted

        public BmiLevel getBmiLevel() {
            return BmiLevel.fromBmiValue(this.getBmi());
        }
    }
    ```

Різниця лише в тому, що другий варіант може спричинити баг, так як одне діло — замінити назву методу, коли це можна зробити за пару комбінацій в сучасній IDE, а інша — застосувати новий тип даних. Тому я зупинюся на першому варіанті, хоча з архітектурної точки зору другий варіант виглядає краще.

Рефакторинг нашого класу закінчено, останній крок — додати документацію засобами Javadoc. Це варто робити, тому що ми не знаємо, які одиниці вимірювання використовуються в обчисленнях (а це важливо для читабельності).

Всі зміни я вніс в файл `HumanBmi.java`. Також варто не забути про `BmiLevel.java`, де знаходиться наш `enum`.

Тому, в результаті, наш код буде виконуватися ось так:

```java
public class Main {
    public static void main(String[] args) {
        var humanBmi = new HumanBmi(80, 1.52);
        System.out.println(humanBmi.getResult());
    }
}
```

Якщо перевірити його роботу — ми отримаємо те саме значення. Роботу виконано.

P.S. Я хотів до цієї роботи також метод виконання цієї роботи в парадигмі функціонального програмування, але це буде за межами роботи. Можливо, я застосую ці навички в наступній роботі, там є простір для цього.