# SampleWeatherApp
This is sample project that is prepared to show a sample app by using Kotlin, Rxjava, Dagger2, Retrofit2, Room Database, JUnit, Simple Animations.

## Project explaination in a nutshell
When the app opens, it checks the local database and searchs for **temperature** table. Table should contain the datas that are fetched from remote server. 
If data can be found in **temperature** table, all data shows in RecylerView as a list. 
There will be only the **Location name, date, temperature and icon** on list items. When **more details** clicked, a new detail page opens that shows the other details of current location. 

If data can not be found in **temperature** table, an empty screen meets us. There can be a no data screen.

When the user click the **save** button, a new request is sent to https://openweathermap.org. The retreived data saves in **temperature** table and shows in List.






