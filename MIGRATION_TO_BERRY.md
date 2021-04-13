## Переход на `yarnpkg-berry`

Официальная документация [Migration | Yarn - Package Manager](https://yarnpkg.com/getting-started/migration)



### Rebase

Загрузите в _рабочую копию_ ветку, на которую будет проходить _rebase_, `<branch>`

Переместите указатель HEAD на ветку с которой надо сделать _rebase_:

````
git switch yarnpkg-berry-experimental
````

И сделайте _rebase_ на ветку `<branch>`:

````
git rebase <branch>
````


#### Решение конфликтов

Конфликт будет один, а именно `package.json`

Необходимо сохранить рабочий вариант из ветки `<branch>` и добавить в него следующие зависимости:

````
"dependencies": {
  "react-is": "^17.0.2",
},
"devDependencies": {
  "@yarnpkg/pnpify": "^3.0.0-rc.3",
  "eslint-import-resolver-node": "^0.3.4",
}
````

Но, скорее всего, `package.json` из ветки `yarnpkg-berry-experimental` будет со всеми актуальными зависимостями*


### Настройка ESLint

Перейдите в директорию `hh-gowork/front` и установите зависимости командой `yarn`

Если вы пользуетесь `IDE JetBrains`, то... все готово к работе! c:

А вот в `vscode` придется кое-что настроить...
Поскольку теперь папки `node_modules` нет, то теперь параметр `NODE_PATH` со значением по умолчанию бесполезен

Для решения этой _проблемы_ в корневой директории репозитория есть папка `.vscode`
и если Редактор автоматически не подцепит новый путь, это надо сделать вручную:

````
Press F1 -> ESLint: Select Node Path -> front/.yarn/sdk [Use NODE_PATH value defined vai settings]
````

И перезапустите `ESLint Server`:

````
Press F1 -> ESLint: Restart ESLint Server
````


#### Круто!

Теперь можете _поэкспериментировать_, _протестировать_ `yarnpkg-berry` в нашем проекте :)

Почитать про фишки этой версии пакетного менеджера можно тут:
[Plug'n'Play | Yarn - Package Manager](https://yarnpkg.com/features/pnp)



Отлично, с миграцией на `yarnpkg-berry` разобрались...

Но если по каким-то причинам Вам надо вернуться на `yarn-classic`, то для этого есть следующий параграф



## Переход обратно на `yarn-classic`

Тут все весьма _тривиально_

Эта команда установит последнюю версию Yarn 1 release:

````
yarn set version classic
````

Дальше нужно просто обновить зависимости, вернуть параметр `NODE_PATH` в значение по умолчанию,
если редактор не сделал это автоматически и перезапустите `ESLint Server`

Есть еще более простой и радикальный вариант, плюс которого — проект без лишних файлов:

Удалите папки `.vscode` `.yarn` `.node_modules` и файл `.yarnrc.yml`, обновить зависимости
и радоваться проделанной работе :P

Конец.
