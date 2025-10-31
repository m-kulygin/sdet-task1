# sdet-task1
Домашнее задание №1 в рамках SDET-практикума от Simbirsoft.

## Цель
В соответствии с условиями задания на выбранном языке программирования Java (версия 11 или 17) создать проект UI-автотестов по тест-кейсам. Описание тест-кейсов прикрепить в MD.
Тест-кейсы направлены на тестирование UI у формы: https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager  
Результаты оформить в виде пулл-реквеста ветки, в которой велась разработка, в мейн-ветку.  
Полные условия задания: https://github.com/m-kulygin/sdet-task1/blob/78f2400dab808768fff5d9ea58339ee1ad464e65/docs/Task1.pdf

## Технологии
- Язык программирования: Java 17 (Microsoft OpenJDK 17.0.6)
- Среда разработки: IntelliJ IDEA 2024.3 (Ultimate Edition)
- Сборка: Maven 3.9.9
- Браузер: Google Chrome 141.0.7390.77
- Веб-драйвер: Selenium 4.35.0 (ChromeDriver)
- Автотесты: JUnit 4.13.2
- Тест-репорты: Allure 2.30.0

## Инструкция по запуску проекта
1. Убедиться, что на устройстве:
   - установлена Java 17+
   - установлен сборщик Maven
   - есть подключение к интернету
   - установлен браузер Google Chrome
2. Скачать папку проекта или склонировать репозиторий на устройство.
3. Открыть проект в IDE или перейти по пути проекта в консоли.
4. Запустить автотесты через Maven (mvn test). Дождаться выполнения.
5. Запустить плагин (mvn allure:serve) для создания визуальных тест-репортов. Итоговые отчёты должны открыться автоматически  
## Дополнительно к инструкции (для проверяющего)
В ветке task1 настроен CI/CD, который прогоняет автотесты при пуше в ветку. Также после прогонки тестов система сама генерирует allure report.  
Результаты флоу можно посмотреть у ветки во вкладке Actions, выбрав раздел интересующего коммита.  
<img width="500" alt="image" src="https://github.com/user-attachments/assets/486eb0f0-f6dc-4bb5-8858-9789086588fc" />  
Конкретные отчёты Allure можно посмотреть внутри выбранного флоу, скачав архив с отчётом себе на устройство.  
<img width="500" alt="image" src="https://github.com/user-attachments/assets/bdba3500-fe55-4775-9053-56a5213ef13d" />  
Для корректного открытия отчёта **настоятельно рекомендуется** делать действия по инструкции: https://allurereport.org/docs/gettingstarted-view-report/  
_(из принципиальных моментов - перед открытием отчёта браузер должен быть полностью закрыт, иначе отчёт может не прогрузиться)_  
Таким образом, для ознакомления с результатами тестирования необязательно производить полные действия по запуску проекта. Достаточным вариантом может быть ознакомление с готовыми allure-репортами, сгенерированными в процессе CI/CD.


## Тест-кейсы
### Кейс 1: Успешное создание клиента
Предусловие:
1. Открыть браузер
2. Перейти по ссылке
   https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager

Шаги:
1. Нажать на кнопку Add Customer
2. Заполнить поле First Name
3. Заполнить поле Last Name
4. Заполнить поле Post Code
5. Нажать на кнопку Add Customer **в нижней части формы**

**Ожидаемый результат:** появился алерт с текстом, содержащим "Customer added successfully"

### Тест-репорт Allure (к кейсу 1):
<img width="500" alt="image" src="https://github.com/user-attachments/assets/3082b6c0-722c-47b3-a032-94c6b5e742a6" />  

### Кейс 2: Сортировка списка клиентов по имени (First Name)
Предусловие:
1. Открыть браузер
2. Перейти по ссылке
   https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager

Шаги:
1.  Нажать на кнопку Customers
2.  В заголовочной строке открывшейся таблицы нажать на первый элемент, содержащий ссылочный текст First Name

**Ожидаемый результат:** Записи из списка клиентов должны быть отсортированы по First Name (лексикографически)

### Тест-репорт Allure (к кейсу 2):
<img width="500" alt="image" src="https://github.com/user-attachments/assets/65f72295-eb5c-4efb-aac4-d956b21c4688" />  

### Кейс 3: удаление клиента
Предусловие:
1. Открыть браузер
2. Перейти по ссылке
   https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager

Шаги:
1.  Нажать на кнопку Customers
2.  У произвольного клиента нажать на кнопку Delete в столбце Delete Customer

**Ожидаемый результат:** Строка с информацией о клиенте, в записи которого была нажата кнопка Delete, полностью удалена из таблицы

### Тест-репорт Allure (к кейсу 3):
<img width="500" alt="image" src="https://github.com/user-attachments/assets/ba2ab45c-fb82-4952-9c11-e7f07114182f" />  

### Тест-репорт Allure (общий):
<img width="500" alt="image" src="https://github.com/user-attachments/assets/92a09e83-c007-4cf4-83d7-f4e73a0470de" />  
<img width="500" alt="image" src="https://github.com/user-attachments/assets/da93cd99-8a83-44fc-ab8b-929a99c30bbb" />  
<img width="500" alt="image" src="https://github.com/user-attachments/assets/c35de4ce-97c3-4385-8770-bbba51c9bba4" />  

## Мои контакты
- Telegram: @longpastgone
- Email: akademuk97@gmail.com  


