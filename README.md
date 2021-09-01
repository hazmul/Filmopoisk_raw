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