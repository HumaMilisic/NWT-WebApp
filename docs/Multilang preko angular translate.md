# Multilang preko angular translate #

### 1. Potrebno ###

[angular-translate](https://github.com/angular-translate/angular-translate)

[bower-angular-translate-loader-static-files](https://github.com/angular-translate/bower-angular-translate-loader-static-files)

[folder sa jezicima u json formatu](https://github.com/HumaMilisic/NWT-WebApp/tree/translatePostavke/src/main/resources/static/i18n)


### 2. Postavka: ###

index.html:

    <script src="js/scripts/angular-translate.js"></script>
    <script src="js/scripts/angular-translate-loader-static-files.js"></script>

app.js:

    var DMApp = angular.module('DMApp', [
        ...
        'pascalprecht.translate',
    ]);


    DMApp.config(function($translateProvider){
        $translateProvider.useStaticFilesLoader({
            prefix: 'i18n/',
            suffix: '.json'
        });
        $translateProvider.preferredLanguage('en-US');
    }

### 3. Koristenje

Stringovi se dodaju, sa engleskim key i prevodom ?key, u file-ove za sve jezike. Prevod se unosi umjesto ?key

[bs-Latn-BA.json:](https://github.com/HumaMilisic/NWT-WebApp/blob/translatePostavke/src/main/resources/static/i18n/bs-Latn-BA.json)

    "EXAMPLE_TRANSLATION": "?EXAMPLE_TRANSLATION"

[en-US.json:](https://github.com/HumaMilisic/NWT-WebApp/blob/translatePostavke/src/main/resources/static/i18n/en-US.json)

    "EXAMPLE_TRANSLATION": "Example translation"


U html-u se koristi, tako sto se doda u roditelj html tag

    <h2 translate="GREETING">poz</h2>
    <span translate="GREETING">poz</span>