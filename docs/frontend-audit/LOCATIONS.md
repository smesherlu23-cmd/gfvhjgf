# Где находятся проблемы фронтенда

Репозиторий `g8cx/pass_manager`, ветка `frontend`, коммит `3ff2966`. Номера строк совпадают с GitHub.
Пояснения, почему каждая проблема важна, есть в [README.md](README.md).

---

## [js/load_collections.js](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/js/load_collections.js)

| Строки | Что там | Проблема |
|---|---|---|
| весь файл | переводы строк CRLF (в остальных JS-файлах LF), в конце нет перевода строки | форматирование |
| 1 | `async function load_collections()` | snake_case, а в остальных файлах camelCase |
| 1–104 | одна функция: загрузка (5–9), разбор (11), построение DOM (24–99) | функция делает всё сразу |
| 3 / 5–13 / 15–20 / 22–24 / 101 | отступы 2 / 8 / 4 / 6 / 4 пробела | смешанные отступы |
| 13 | `const navigation` | то же имя, что у глобальной `navigation` из `scroll_effector.js` (ESLint `no-shadow`) |
| 15–20 | закомментированный код с `/api/collections` | мёртвый код |
| 25, 28, 36 | `// const Attributes`, `// HTML elements`, `// SVG element` | комментарии на английском… |
| 45–92 | `// Div коллекции`, `// Сборка элементов`… | …и на русском в одном файле |
| 45, 48, 51, 55, 58 | `// Картинка коллекции` над `coll_img.src = …` и т. п. | комментарии повторяют код |
| 26 | `const iconViewSize = 24` | константа внутри цикла, нет `;` |
| 29–34, 37 | `coll_content`, `coll_a`, `coll_img`, `coll_name`, `coll_settings`, `setting_image` | snake_case и сокращения |
| 37–40, 62–72, 74–90 | SVG-иконка собирается вручную | дублирует `assets/icons/ellipsis.svg` |
| 63 | `setAttribute('xmlns', …)` | лишнее при `createElementNS` |
| 75–88 | `circle1`, `circle2`, `circle3` | одинаковый блок скопирован трижды |
| 42 | `li.id = '/collections/…'` | id используется как маршрут, `querySelector('#/…')` на нём падает |
| 43 | `li.className = 'tab_btn collection_btn_effects'` | класс для стилей одновременно служит JS-хуком |
| 49 | `coll_a` создаётся без `href` | ссылка не получает фокус с клавиатуры |
| 59–60 | у `coll_settings` нет `aria-label` | кнопка без имени для скринридера |
| 93–97 | `appendChild(li)` в цикле, ещё до наполнения пункта | лучше `replaceChildren(...)` или `DocumentFragment` |
| 101–103 | `catch` только пишет `console.error` | пользователь ошибку не видит |
| 106 | `load_collections();` вызывается без экспорта и `await` | отсюда гонка с эффектами прокрутки |

## [js/scroll_baraban_effector.js](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/js/scroll_baraban_effector.js)

| Строки | Что там | Проблема |
|---|---|---|
| имя файла | `baraban_effector` | транслит |
| 1 | `const navigator = …` | **перекрывает `window.navigator`** (ESLint `no-redeclare`, error) |
| 15 → 44–45 | `getBoundingClientRect()` и `setProperty()` в одном цикле | чтения чередуются с записями, браузер каждый раз пересчитывает стили |
| 25–26 | `[...items].slice(0, 3).includes(item)`, `slice(-3)` | O(n²), «3» — магическое число |
| 32, 35, 38 | `1.08` три раза | магическое число, повторяется |
| 38, 39 | `0.18`, `0.35` | магические числа |
| 42 | `// console.log(scale, opacity)` | мёртвый код |
| 51 | `addEventListener('scroll', updateWheelEffect)` | нет `requestAnimationFrame` и `{ passive: true }` |
| 53 | `updateWheelEffect()` | срабатывает, пока список ещё пуст (гонка) |
| — | нет обработчика `resize` | при изменении размера окна эффект не пересчитывается |

## [js/scroll_effector.js](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/js/scroll_effector.js)

| Строки | Что там | Проблема |
|---|---|---|
| 1–2 | `collectionsList`, `navigation` на верхнем уровне | глобальная область; `navigation` **перекрывает `window.navigation`** (ESLint error) |
| 18 | `// console.log(…, navigation.maxScroll)` | мёртвый код, к тому же свойства `maxScroll` не существует |
| 19 | `}` без `;` после `const … = () => {…}` | точки с запятой расставлены непоследовательно |
| 23 | `updateScrollShadows()` | срабатывает, пока список ещё пуст (гонка) |

## [js/switch_collections.js](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/js/switch_collections.js)

| Строки | Что там | Проблема |
|---|---|---|
| 1–27 | IIFE | единственный изолированный файл, в остальных такого нет |
| 2, 3, 10 | нет `;` | ловушка автоподстановки точки с запятой (ASI) |
| 3 | `querySelector('#active')` | id как JS-хук |
| 3, 5 | `defaultTemp`, `temp` | имена ничего не говорят, по смыслу это `activeTab` |
| 8 | `closest('.tab_btn')` | класс для стилей служит JS-хуком |
| 10 | `console.log(event.target)` | лог на каждый клик |
| 13, 21 | отступ 2 пробела внутри кода с шагом 4 | смешанные отступы |
| 20–25 | переключается только класс `active` | `id="active"` остаётся на «Logins» |

## [html/index.html](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/html/index.html)

| Строки | Что там | Проблема |
|---|---|---|
| 2 | `lang="en"` | данные коллекций на русском |
| 6–9 | 4 классических `<script defer>` без `type="module"` | общая глобальная область, работа зависит от порядка тегов |
| 12 | Google Fonts, 7 семейств | используется только Sansation |
| 19 | sidebar размечен как `<section>` | по смыслу это `<aside>`/`<nav>` |
| 20, 21, 31, 50, 76 | `<section>` без заголовков | семантика |
| 23 | `data-img=""` | ни один скрипт его не читает |
| 26–27 | `data-user="…"` и захардкоженные `ivan1137` / почта | атрибуты не используются, чужие данные в разметке |
| 31 | `funtional_btn` | опечатка |
| 32, 36, 40, 44, 77 | `<a>` без `href` | не фокусируются, не открываются Enter |
| 32, 36, 40, 44 | класс `func` | имя ничего не говорит |
| 33, 37, 41, 45, 78 | иконки PNG (способ №1) | не перекрашиваются при смене темы |
| 44 | `id="active"` и класс `active` | id как JS-хук, после клика указывает не на ту вкладку |
| 53 | `<button>` без `type` и `aria-label` | в форме отправит её, скринридер прочитает просто «button» |
| 54–63 | SVG «+» прямо в HTML (способ №2) | дублирует `assets/icons/plus.svg` |
| 54, 86 | пробелы в конце строки | форматирование |
| 84, 92, 93 | классы `.footer`, `.header`, `.main` | слишком общие имена, будут конфликты |
| 92 | `<header class="header">Logins</header>` | на странице нет `<h1>` |
| 94 | `add_login.png`, 603 КБ, 990×990 | показывается 250×250 |
| 96–97 | `<span class="main_h1">`, `<span class="main_h2">` | span вместо `<h1>`/`<p>` |
| 96, 97 | «Starting adding logins», «curely» | опечатки |
| 100–101 | `<button>` без `type` | в форме отправит её |

## [html/auth.html](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/html/auth.html)

| Строки | Что там | Проблема |
|---|---|---|
| 3–7 | `<head>` без единого `<link rel="stylesheet">` | страница без стилей |
| 17, 33 | `<form action="">` без `method` | отправка GET-запросом, данные окажутся в URL |
| 19–20, 35–37 | `<input>` без `<label>`, `name`, `autocomplete` | доступность, менеджеры паролей не распознают поля |
| 15, 22, 39 | «Sing in», «Sing up» | опечатки, правильно *Sign* |

## [styles/sass/_variables.sass](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/styles/sass/_variables.sass)

| Строки | Что там | Проблема |
|---|---|---|
| 4 | `$backgound-workplace-dark` | опечатка, а в `work_place.sass:24` эту переменную ищут под другим именем |
| 9 | `// UI EFFETCS` | опечатка |
| 13 | `$accent-color-dark: #10E3fFFB9` | **9 hex-символов, невалидный цвет** |
| 3–14 | `$…-dark` | имена по теме, а не по назначению, тему на лету не сменить |
| 17 | `$background-light` | светлая тема состоит из одной переменной |

## [styles/sass/styles.sass](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/styles/sass/styles.sass)

| Строки | Что там | Проблема |
|---|---|---|
| 7–8 | `font-family` и `color` на `*` | ломают наследование цвета |
| 9–19 | `body` вложен в `*` | компилируется в `* body` |
| 20–24 | `a` вложен в `*` | компилируется в `* a` |

## [styles/sass/work_place.sass](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/styles/sass/work_place.sass)

| Строки | Что там | Проблема |
|---|---|---|
| 2 | строка из одних пробелов | форматирование |
| 24 | `v.$workplace-backgound-dark` | **переменной не существует, сборка падает** |
| 44 | `line-height: 8px` при `font-size: 14px` | текст налезает при переносе строки |
| 54–62 | кнопки: циановый фон, белый текст (цвет приходит от `*`) | контраст 2.67:1 |

## [styles/sass/sidebar.sass](https://github.com/g8cx/pass_manager/blob/3ff296626374ebc18550b46020d0ab944eed235e/styles/sass/sidebar.sass)

| Строки | Что там | Проблема |
|---|---|---|
| 2 | строка из одних пробелов | форматирование |
| 3 → 12 → 65 → 131 → 161 → 175 → 188 → 198 | `.sidebar` › `.main_data` › `.collections` › `.collections_navigation` › `li` › `.collection_content` › `.collection_a` › `.collection_image` | **8 уровней вложенности** |
| 54, 170, 234 | `&.active` | три одинаковых блока, хватит одного `.tab_btn.active` |
| 55–57 | отступ 4 пробела вместо 2 | смешанные отступы |
| 57, 172, 237 | `border: none` у `.active` | сдвиг пункта на 2px |
| 69, 92 | стили для тега `button` описаны дважды | селектор по тегу, дубль |
| 216–221 и 250–255 | `.settings_line` | описан дважды |
| 258–262 и 264–268 | `.btn_effects` и `.collection_btn_effects` | совпадают построчно |
| 30, 56, 150, 173, 220, 236, 254, 260, 266 | `border-radius: 10px` | повтор 9 раз, нужна переменная |
| 60–61, 96–97, 199–200, 240–241 | `width/height: 20px` у иконок | повтор 4 раза |
| 142–156 | только `::-webkit-scrollbar` | в Firefox не работает |
| 196 | `// padding-right: 10px` | мёртвый код |

**Во всех `.sass` файлах:** переводы строк CRLF, в конце нет перевода строки, все размеры в `px` (sidebar — 59, work_place — 15, styles — 5), `rem` нет нигде.

## Скомпилированные файлы и репозиторий

| Где | Что | Проблема |
|---|---|---|
| `styles/css/*.css` | скомпилированный CSS в git | два источника правды |
| `styles/css/work_place.css:41–42` | `rgba(33, 37, 41, 0.6)`, `rgba(16, 227, 255, 0.725)` | значения устарели, в Sass их уже нет |
| `styles/css/sidebar.css:207` | `background: rgba(…) 9;` | невалидное значение, браузер его отбрасывает |
| `styles/css/styles.css:9, 26` | `* body`, `* a` | результат ошибки вложенности |
| `assets/icons/*.svg` (4 файла) | `ellipsis`, `eye`, `eye-off`, `plus` | нигде не используются |
| `prepros.config` | конфиг GUI-программы на 883 строки | сборка зависит от Prepros |
| *отсутствуют* | `package.json`, `eslint.config.js`, `.prettierrc`, `.editorconfig`, `.gitattributes`, `.gitignore` | нет линтера, форматтера и единых переводов строк |
