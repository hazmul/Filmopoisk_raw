Задачи:
1) подготовить retrofit and okhttp
2) запрос в сеть на configuration
3) premain fragment с запросом
4) запрос на поиск 
5) search fragment с поисковой строкой
6) подготовить все возможные запросы






PreMainActivity
картинка
прогресс бар
на фоне загрузка инфы
/configuration
/genre/movie/list
успех - переходим в mainactivity
неуспех - alerdialog с ошибкой интернета по кнопке ОК закрытие приложения?


Main Activity
Layout:
1) nav-drawer
2) menu? preferences (settings)
3) fragment container
4) ...

Nav-drawer
1) сейчас в кино GET movie/now_playing
2) популярные GET /movie/popular
3) с высоким рейтингом GET /movie/top_rated
4) скоро в кино GET /movie/upcoming
5) поиск фильма по названию GET /search/movie
6) //
7) настройки (выбор языка и региона)
8) Licenses?

--(файл с АПИ настройками надо держать актуальным)
GET /configuration
кэшировать в префы?
(как качать картинки)
https://developers.themoviedb.org/3/configuration/get-api-configuration

Между слоями общаться обьектами
В домэйн слой передавать настройки

-- ...

</br>Ключ API (v3 auth)  = 5f7614e8aead89a63d1dae413fd0153a
</br>Пример API-запроса = https://api.themoviedb.org/3/movie/550?api_key=5f7614e8aead89a63d1dae413fd0153a
</br>Ключ доступа к API (v4 auth) = eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1Zjc2MTRlOGFlYWQ4OWE2M2QxZGFlNDEzZmQwMTUzYSIsInN1YiI6IjYwZmRhOWYyM2QzNTU3MDAyZDU0YzQ3YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OvEyG5d_V5KV9WQRMa3Fc4-6028PxGX5CsKyPaXjreQ



https://developers.themoviedb.org/3/getting-started/introduction