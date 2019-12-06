# quiz
[Retrofit2](#Retrofit2), 
[Firebase](#Firebase), 
[Comparator](#Comparator), 
[Glide](#Glide), 
[Fragment](#Fragment), 
[ViewPager](#ViewPager), 
[RecyclerView](#RecyclerView), 
[AsyncTask](#AsyncTask), 
[Try Catch](#Try_Catch).


<a name="Retrofit2"></a> 
## **Retrofit2**
                
1. `<uses-permission android:name="android.permission.INTERNET"/>` - Эта линия позволит нашему проекту использовать интернет устройства Android.
2. `Interface @GET` устанавливает "имя файла" откуда получаем данные
   +`Call<String> getString();` вызываем функцию
3. `RetroModel` - гетеры и сетеры даннх в json.
4. `getResponse` :
+ Прежде всего, компилятор создаст объект класса `Retrofit`.
+  Здесь он будет связывать URL с помощью `.baseUrl ()` .
+  Затем он создаст объект интерфейса. 
+  Используя этот объект, он создаст новый `Callback` и запустит `.enqueue ()` .
+  После этого, при переоборудовании необходимо выполнить http-вызов URL, и он получил ответ.
+  Компилятор будет читать ответ JSON в методе `onResponse ()` .
+  `jsonresponse`  - строка с данными из json.
+   Затем компилятор вызовет метод `writeTv (jsonresponse)` . Компилятор передаст строковую переменную, которая содержит ответ JSON в параметре этого метода.
5. `writeTv (jsonresponse)` :
 +   Прежде всего, компилятор будет анализировать родительский объект из ответа JSON.
 +   Затем из этого родительского объекта он проверит значение `« response_code »`.
 +   Если это правда, то он создаст один массив с объектами `RetroModel` класса , который мы написали на шаге 3.
 +   Теперь компилятор создаст один `JSONArray`, который представлен полем `« results »`.
  +  После этого, он будет делать один для цикла. Во время каждой итерации цикла for компилятор создает объект  `RetroModel.` класса и устанавливает данные для этого объекта.
  +  Затем он добавит этот объект в массив.
  
<a name="Firebase"></a> 
## **Firebase**
 1. Запись: 
 + `FirebaseDatabase.getInstance().getReference();` - путь в БД.
 + `myRef.push().setValue(playerInformation);` - записывает наш обьект.
 2. Чтение:
 + `FirebaseDatabase.getInstance().getReference();` - путь в БД.
 + `Query myQuery = myRef;` -  Класс Query (и его подкласс DatabaseReference) используются для чтения данных. Слушатели подключены, и они будут срабатывать при изменении соответствующих данных.
 + `ChildEventListener` - Классы, реализующие этот интерфейс, могут использоваться для получения событий об изменениях в дочерних местоположениях данного DatabaseReference ref. Присоедините слушателя к местоположению, используя, `addChildEventListener(ChildEventListener)`и соответствующий метод будет запущен, когда произойдут изменения.
 + `DataSnapshot dataSnapshot` - Экземпляр DataSnapshot содержит данные из базы данных Firebase. Каждый раз, когда вы читаете данные базы данных, вы получаете данные как DataSnapshot.
 
 <a name="Comparator"></a> 
 ## **Comparator**
 1. Используется для сравнения объектов по написанным нами правилам.
  
  
 <a name="Glide"></a> 
 ## **Glide**
 1. Анимация `*.gif` .
 
 <a name="Fragment"></a> 
## **Fragment**
1. Фрагмент (класс Fragment) представляет поведение или часть пользовательского интерфейса в операции (класс Activity).
2. `inflater` 
+ `LayoutInflater.inflate(int resource, ViewGroup root, boolean attachToRoot)` - 1 аргумент указание какой файл разметки загрузить. Второй - родительский элемент разметки, в который будет автоматически добавлена загружаемая разметка если 3 параметр true или откуда будут взяты LayoutParams для загружаемой вьюхи, если attachToRoot false.
+ Пример:
```java
//загружаем файл разметки и вручную добавляем его в контейнер
View v = inflater.inflate(R.layout.custom_button, mLinearLayout, false);
mLinearLayout.addView(v);
//загружаем файл разметки и автоматически добавляем его в контейнер
inflater.inflate(R.layout.custom_button, mLinearLayout, true);
//загружаем файл разметки и автоматически добавляем его в контейнер
inflater.inflate(R.layout.custom_button, mLinearLayout);
```
3. Жизненный цикл фрагмента:<br/>

![alt Жизненный цикл фрагмента ](https://developer.android.com/images/fragment_lifecycle.png?hl=RU)

<a name="ViewPager"></a> 
## **ViewPager**
1. `PagerAdapter` – это базовый абстрактный класс, для которого разработчик дописывает реализацию так, как ему надо. Существует распространенная стандартная (частичная) реализация `PagerAdapter`, которая работает с фрагментами – это `FragmentPagerAdapter`. Разработчику остается только создать фрагмент и определить кол-во страниц.
+`pager.setOffscreenPageLimit(PAGE_COUNT);` - сохраняет состояние страниц, количество которых указанно в скобках(`PAGE_COUNT = 10`).
+`onPageSelected` – дает номер текущей отображенной страницы.
+`onPageScrolled` – достаточно сложно объяснить на словах. Метод дает нам представление о текущем значении скроллера при пролистывании. Рекомендую поставить там запись в лог, полистать и посмотреть, что получается.
+`onPageScrollStateChanged` – сообщает нам о состоянии, в котором находится скроллер (SCROLL_STATE_IDLE – ничего не скролится, SCROLL_STATE_DRAGGING – пользователь «тащит» страницу, SCROLL_STATE_SETTLING – скроллер долистывает страницу до конца). 
2. Класс `FragmentPagerAdapter` - абстрактный. Нам надо реализовать в нем пару методов. Для этого создаем класс MyFragmentPagerAdapter. В нем реализуем методы:
+`getItem` – по номеру страницы нам надо вернуть фрагмент, используем наш метод newInstance.
+`getCount` – здесь мы должны возвращать кол-во страниц, используем константу.


<a name="RecyclerView"></a> 
## **RecyclerView**
1. Элемент RecyclerView предназначен для оптимизации работы со списками и во многом позволяет повысить производительность по сравнению со стандартным ListView.
2. Для вывода сложных объектов в RecyclerView необходимо определить свой адаптер. Поэтому добавим новый класс `DataAdapter`:
+`onCreateViewHolder` - возвращает объект ViewHolder, который будет хранить данные по одному объекту Phone.
+`onBindViewHolder`- выполняет привязку объекта ViewHolder к объекту Phone по определенной позиции.
+`getItemCount`- возвращает количество объектов в списке
3. Для хранения данных в классе адаптера определен статический класс ViewHolder, который использует определенные в list_item.xml элементы управления. Т.к. ViewHolder не является классом из Android SDK, мы определяем его сами.
```java 
        ViewHolder(View view) {
            super(view);
            userNameView = (TextView) view.findViewById(R.id.userNameRecord);
            modView = (TextView) view.findViewById(R.id.modRecord);
            numberOfPointsView = (TextView) view.findViewById(R.id.pointRecord);
        }
```
4. Так же из-за того, что мы получаем данные из Firebase - у нас есть задержка и мы создаем метод, который будет вызывать при получении каждой записи из бд  метод `notifyDataSetChanged();` - который уведомляет прикрепленных наблюдателей, что базовые данные были изменены, и любое представление, отражающее набор данных, должно обновляться само.
5. `Context` – это объект, который предоставляет доступ к базовым функциям приложения: доступ к ресурсам, к файловой системе, вызов активности и т.д. `Activity` является подклассом `Context`, поэтому в коде мы можем использовать её как `ИмяАктивности.this` (напр. `MainActivity.this`), или укороченную запись `this`. 

<a name="AsyncTask"></a> 
## **AsyncTask**
1. Цель `AsyncTask`  – это выполнение тяжелых задач и передача в UI-поток результатов работы. (У нас выполняется запрос и потом вывод полученных результатов).
+`doInBackground` – будет выполнен в новом потоке, здесь решаем все свои тяжелые задачи. Т.к. поток не основной - не имеет доступа к UI.
+`onPreExecute` – выполняется перед doInBackground, имеет доступ к UI
+`onPostExecute` – выполняется после doInBackground (не срабатывает в случае, если AsyncTask был отменен), имеет доступ к UI

<a name="Try_Catch"></a>
## **Try Catch**
1. Просто обработчик ошибок, что бы приложение не крашилось.
<br/> Пример:
 ```java 
 try {
    return a/b;
catch(Exception e) {
    return 0; // в переменной е информация об ошибке
}
```
 ***
 ## by Ivan Shavlovski
