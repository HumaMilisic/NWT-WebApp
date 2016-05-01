var DMApp = angular.module('DMApp', [
    'ngRoute',
    //'ngTable',
    //'smart-table',
    'ui.bootstrap',
    //'angularSpinner',
    'chart.js',
    'angularSpinner',
    'vcRecaptcha',
    'ngResource',
    'spring-data-rest',
    'ngSanitize',
    'ngAnimate',
    //'ngQuantum',
    //'mgcrea.ngStrap',
    'ngMaterial',
    'md.data.table',
    //'mdDataTable',
    'ngMdIcons',
    'pascalprecht.translate',
    //'ngMessages',
    'LocalStorageModule',
]);


DMApp.config(function($httpProvider,$routeProvider/*,SpringDataRestInterceptor*/,$translateProvider,localStorageServiceProvider){
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    //$translateProvider
    //    .translations('en',translations)
    //    .preferredLanguage('en');
    //
    //$translateProvider.useLoader('$translatePartialLoader', {
    //    urlTemplate: '/i18n/{lang}.json'
    //});

    //jezik.loadEN();
    localStorageServiceProvider
        .setPrefix('NWT-DMS.')
        .setStorageType('sessionStorage')
        .setNotify(true, true);

    $translateProvider.useStaticFilesLoader({
        //prefix: 'locale-',
        prefix: 'i18n/',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage('en-US');

    //http://www.webdeveasy.com/interceptors-in-angularjs-and-useful-examples/
    //auth intercept nesto nesto sigh
    //$httpProvider.interceptors.push(SpringDataRestInterceptor);

    $routeProvider
        .when('/korisnik',{
            templateUrl:'korisnik.html'
        })
        .when('/login',{
            templateUrl:'login.html'
        })
        .when('/uitest',{
            templateUrl:'uitest.html'
        })
        .otherwise('/');

    //administracija
    $routeProvider
        .when('/admin',{
            templateUrl:'/js/app/admin/views/adminDashboard.html'
        })
        .when('/admin/korisnik',{
            templateUrl:'/js/app/admin/views/administracijaKorisnika.html'
        })
        .when('/admin/korisnik/:username',{
            templateUrl:'korisnik.html'
        })
        .when('/admin/akcija',{
            templateUrl:'/js/app/admin/views/administracijaAkcija.html'
        })
        .when('/admin/uloga',{
            templateUrl:'/js/app/admin/views/administracijaUloga.html'
        })
        .when('/admin/status',{
            templateUrl:'/js/app/admin/views/administracijaStatusa.html'
        })
        .when('/admin/vrstaDokumenta',{
            templateUrl:'/js/app/admin/views/administracijaVrstaDokumenta.html'
        })
        .when('/admin/notifikacija',{
            templateUrl:'/js/app/admin/views/administracijaNotifikacija.html'
        })


});



DMApp.service('loader',function(usSpinnerService){
    this.startSpin = function(){
        usSpinnerService.spin('mainSpinner');
    };
    this.stopSpin = function(){
        usSpinnerService.stop('mainSpinner');
    }
});

DMApp.service('redirekt',function($location){
    var staro = null;

    this.goTo = function(gdje){
        var url = $location.path();
        if(url!=gdje){
            staro = staro!=url?url:staro;
            $location.path(gdje);
        }
    };

    this.goToHome = function(){
        this.goTo('/');
    };

    this.goToLogin = function(){
        this.goTo('/login');
    };

    this.goToStaro = function(){
        if(staro!=null){
            this.goTo(staro);
        }
    }
});

DMApp.service('auth',function($rootScope,$http,$q,$location,redirekt,loader){
    var korisnik = null;
    var logovan = null;
    var authorities = null;



    this.jeLogovan = function(){
        loader.startSpin();
        var deferred = $q.defer();
        if(logovan==null){
            this.check().then(function(data){
                loader.stopSpin();
                if(data.status==200){
                    logovan = true;
                    deferred.resolve({
                        logovan:logovan
                    });
                }else{
                    logovan = false;
                    deferred.resolve({
                        logovan:logovan
                    });

                }
            })
        }else{
            loader.stopSpin();
            deferred.resolve({
                logovan:logovan
            });
        }
        return deferred.promise;
    };

    this.getKorisnik = function(){
        var deferred = $q.defer();
        this.check().then(function(rez){
            loader.startSpin();
            if(rez.status==200){
                //http://localhost:8181/api/korisnik/search/findByUsername?name=huma
                if(korisnik==null){

                    $http({
                        method:'GET',
                        url:'/api/korisnik/search/findByUsername?name='+rez.name
                    })
                        .success(function(data, status, x){
                            loader.stopSpin();
                            var a = 0;
                            //return data;
                            korisnik = data;
                            deferred.resolve({
                                data:data,
                                status:status
                            });
                        })
                        .error(function(response,status,nesto,request){
                            loader.stopSpin();
                            //return response;
                            deferred.resolve({
                                status:status
                            });
                        })
                }
                else {
                    loader.stopSpin();
                    deferred.resolve({
                        status:200,
                        data:korisnik
                    });
                }
            }
            else{
                loader.stopSpin();
                //return 403;
                deferred.resolve({
                    status:403
                });
            }
        });

        return deferred.promise;
    };
    this.login = function(username,password){
        $http({
            method: 'POST',
            url: '/login',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data: {username:username,password:password}
        })
        //$http.post("/login",{headers:headers})
            .success(function(data){
                //alert(data);
                $rootScope.logovan = true;
                $rootScope.$broadcast('logovan');
            })
            .error(function(data){
                //alert("error: "+data);
                $rootScope.logovan = false;
                $rootScope.$broadcast('logovan');
            })
    };
    this.logout = function(){
        $http.post("/logout")
            .success(function(x,y,z,k,i){
                var a = 0;
                //logovan = null;
                korisnik = null;
                redirekt.goToHome();
            })
            .error(function(x,y,z,k,i){
                var a = 0;
            })
            .finally(function(x,y,z,k,i){
                var a = 0;
                //$rootScope.logovan = false;
                //$rootScope.$broadcast('logovan');
            });
    };
    this.check = function(){
        var deferred = $q.defer();

        $http({
            method:'GET',
            url:'/user'
        })
            .success(function(data, status, x){
                loader.stopSpin();
                if(data.name!=undefined){
                    logovan = true;
                    authorities = data.authorities;
                    deferred.resolve({
                        //data: result._embedded[tabela],
                        //page: result.page,
                        //links: result._links,
                        name: data.name,
                        status: status
                    });
                }
                else{
                    logovan = false;
                    deferred.resolve({
                        //data: result._embedded[tabela],
                        //page: result.page,
                        //links: result._links,
                        status: 403
                    });
                    korisnik = null;
                    redirekt.goToLogin();
                    //$location.path("/");
                }
            })
            .error(function(response,status,nesto,request){
                loader.stopSpin();
                logovan = false;
                deferred.resolve({
                    //data: result._embedded[tabela],
                    //page: result.page,
                    //links: result._links,
                    status: status
                });
                korisnik = null;
                redirekt.goToLogin();
                //$location.path("/");
            });

        return deferred.promise;
    }
});

DMApp.factory('Resource',
    //['$q','$filter','$timeout','tabela'],

    function($q,$filter,$timeout,$http,auth){
        //http://localhost:8181/api/korisnik?page=1&size=5
        var links = null;
        var tabela = "";
        var result = [];
        var page = [];
        var result = null;
        function goTo(glagol,link){
            var deferred = $q.defer();
            $http({
                method: glagol,
                url: link
            })
                .success(function(data,status,x){
                    if(data.page!=null && data.page!=undefined){
                        result = data;
                        links = result._links;
                        page = result.page;
                        deferred.resolve({
                            data: result._embedded[tabela],
                            page: result.page,
                            links: result._links,
                            status: 200
                        });
                    }else {
                        deferred.resolve({
                            status: 404
                        });
                    }
                })
                .error(function(response,status,nesto,request){
                    deferred.resolve({
                        status:status
                    });
                });

            return deferred.promise;
        }
        function getPage(page, size, params,tabelaa,link) {
            tabela = tabelaa;
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/api/'+tabela+'?page='+page+'$size='+size,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            })
                .success(function (data,status){
                    if(data.page!=null && data.page!=undefined){
                        //result = data;
                        //links = result._links;
                        result = data;
                        links = result._links;
                        page = result.page;
                        deferred.resolve({
                            data: result._embedded[tabelaa],
                            page: result.page,
                            links: result._links,
                            status: 200
                        });
                    }else {
                        deferred.resolve({
                            status: 404
                        });
                    }

                })
                .error(function(response,status,nesto,request){
                    var a = 0;
                    deferred.resolve({
                        status:status
                    });
                });

            return deferred.promise;
        }
        function nextPage(){
            if(links!=null){
                if(links.next!=undefined){
                    return goTo('GET',links.next.href)
                }
            }
        }
        function getPageBroj(brojStranice,brojNaStranici,tabelaa){
            var deferred = $q.defer();
            auth.check().then(function(rez){
                if(rez.status==200){
                    //http://localhost:8181/api/korisnik?page=1&size=5
                    var url = '/api/'+tabelaa+'?page='+(brojStranice-1)+'&size='+brojNaStranici;

                    $http({
                        method: 'GET',
                        url: url,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    })
                        .success(function (data,status,x,y,z){
                            if(data.page!=null && data.page!=undefined){
                                //result = data;
                                //links = result._links;
                                result = data;
                                links = result._links;
                                page = result.page;
                                deferred.resolve({
                                    data: result._embedded[tabelaa],
                                    page: result.page,
                                    links: result._links,
                                    status: 200
                                });
                            }else {
                                deferred.resolve({
                                    status: 404
                                });
                            }

                        })
                        .error(function(response,status,nesto,request){
                            var a = 0;
                            deferred.resolve({
                                status:status
                            });
                        })
                }else{
                    deferred.resolve({
                        status:403
                    });
                }
            });
            return deferred.promise;
        }
        function prevPage() {
        }
        function firstPage() {
        }
        function lastPage() {
        }
        return {
            //getPage: getPage,
            //nextPage: nextPage,
            //prevPage: prevPage,
            //firstPage: firstPage,
            //lastPage: lastPage,
            getPageBroj: getPageBroj
        };
    }
);

DMApp.controller('loginController',function($scope,$http,$rootScope,auth,$translate,localStorageService){
    //$scope.logovan = $rootScope.logovan;
    //$scope.$on('logovan',function(){
    //    $scope.logovan = $rootScope.logovan;
    //})
    //$scope.jezik = localStorageService.bind($scope, 'locale');

    $scope.changeLanguage = function (langKey) {
        if(langKey==null || typeof (langKey)=='undefined'){
            langKey='en-US';
        }
        $translate.use(langKey);
        $scope.jezik = langKey;
    };

    $scope.changeLanguage($scope.jezik);

    $scope.logCheck = function(){
            auth.jeLogovan().then(function(data){
            $scope.logovan = data.logovan;
            auth.getKorisnik().then(function(data){
                $scope.korisnik = data.data;
            });
    });};
    var user = {username:'user',password:'user'};

    $scope.korisnik = {
        username:'username'
    };

    $scope.login = function(){
        auth.login(user.username,user.password);
        $scope.logCheck();
    };
    $scope.logout = function(){

        auth.logout();
        //$scope.logovan = auth.jeLogovan();
    };
    $scope.logCheck();
});

DMApp.directive('nwtMeni',function(){
    return{
        templateUrl: 'meni.html',
        controller: 'loginController'
    }
});

DMApp.directive('formaRegistracija',function(){
    return{

        templateUrl: 'registracija.html',
    }
});

DMApp.controller('registracijaController', function ($scope, vcRecaptchaService,$http) {
    console.log("this is your app's controller");
    $scope.user = {};
    $scope.response = null;
    $scope.widgetId = null;

    $scope.model = {
        key: '6Ld7gB0TAAAAAHP-rEzmJM9H93ZbNvM__Ndx89qW'
    };

    $scope.setResponse = function (response) {
        console.info('Response available');

        $scope.response = response;
    };

    $scope.setWidgetId = function (widgetId) {
        console.info('Created widget ID: %s', widgetId);

        $scope.widgetId = widgetId;
    };

    $scope.cbExpiration = function() {
        console.info('Captcha expired. Resetting response object');

        vcRecaptchaService.reload($scope.widgetId);

        $scope.response = null;
    };

    $scope.$watch('response',function(){
        $scope.user.recaptchaResponse = $scope.response;
    });

    $scope.submit = function () {
        var valid;

        /**
         * SERVER SIDE VALIDATION
         *
         * You need to implement your server side validation here.
         * Send the reCaptcha response to the server and use some of the server side APIs to validate it
         * See https://developers.google.com/recaptcha/docs/verify
         */
        console.log('sending the captcha response to the server', $scope.response);
        if($scope.response!=null){
            $http({
                method: 'POST',
                url:'/register',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: $scope.user
            })
                .success(function(data, status, x){
                    var a = 0;
                    alert(data.message);
                })
                .error(function(response,status,nesto,request){
                    var a = 0;
                    alert(response.message);
                    console.log('Failed validation');

                    // In case of a failed validation you need to reload the captcha
                    // because each response can be checked just once
                    vcRecaptchaService.reload($scope.widgetId);
                })
        }
        //if (valid) {
        //    console.log('Success');
        //} else {
        //    console.log('Failed validation');
        //
        //    // In case of a failed validation you need to reload the captcha
        //    // because each response can be checked just once
        //    vcRecaptchaService.reload($scope.widgetId);
        //}
    };
});



DMApp.controller('korisnikPageController',function($scope,$http,$rootScope,auth,$routeParams,auth){
    $scope.name = "korisnikPageController";

    $scope.username = $routeParams.username;
    $scope.korisnik = null;
    if($routeParams.username==undefined){
        auth.getKorisnik().then(function(rez){
            $scope.username = rez.data.username;
            $scope.korisnik = rez.data;
        });
        //$scope.username = a.name;
    }
});

DMApp.factory('Item',function(SpringDataRestAdapter,$http){
    var entity = "";
    var baseUrl = "/api/";
    function Item(item,tabela){
        if(tabela){
            entity = tabela;
        }

        if(item._resources){
            item.resources = item._resources("self",{},{
                update:{
                    method: 'PUT'
                }
            });

            item.save = function(callback){
                item.resources.update(item,function(){
                    callback && callback(item);
                })
            };

            item.remove = function(callback){
                item.resources.remove(function(){
                    callback && callback(item);
                })
            }
        }else {
            item.save = function (callback) {
                Item.resources.save(item, function (item, headers) {
                    var deferred = $http.get(headers().location);
                    return SpringDataRestAdapter.processWithPromise(deferred).then(function (newItem) {
                        callback && callback(new Item(newItem));
                    });
                });
            };
        }
        return item;

    }

    Item.query = function(callback,tabela){
        if(tabela){
            entity = tabela;
        }
        var deffered = $http.get(baseUrl+entity);
        return SpringDataRestAdapter.process(deffered,'_allLinks').then(function(data){
            Item.resources = data._resources("self");
            callback && callback(_.map(data._embeddedItems,function(item){
                return new Item(item);
            }))
        })
    };

    Item.resources = null;

    return Item;
});

DMApp.controller('uiTestCtrl',function($scope){
    $scope.modal = {
        "title": "Title",
        "content": "Hello Modal<br />This is a multiline message!"
    };
    $scope.aside = {
        "title": "Title",
        "content": "Hello Aside<br />This is a multiline message!"
    };
    $scope.alert = {
        "title": "Holy guacamole!",
        "content": "Best check yo self, you're not looking too good.",
        "type": "info"
    };
    $scope.popover = {
        "title": "Title",
        "content": "Hello Popover<br />This is a multiline message!"
    };
    $scope.selectedDate = "2016-04-20T07:06:46.479Z"; // <- [object Date]
    $scope.selectedDateAsNumber = 509414400000; // <- [object Number]
    //$scope.fromDate = ; // <- [object Undefined]
    //$scope.untilDate = ; // <- [object Undefined]
    $scope.selectedIcon = "";
    $scope.selectedIcons = ["Globe","Heart"];
    $scope.icons = [
        {"value":"Gear","label":"<i class=\"fa fa-gear\"></i> Gear"},
        {"value":"Globe","label":"<i class=\"fa fa-globe\"></i> Globe"},
        {"value":"Heart","label":"<i class=\"fa fa-heart\"></i> Heart"},
        {"value":"Camera","label":"<i class=\"fa fa-camera\"></i> Camera"}];
});

DMApp.controller('novaUlogaCtrl',function ($scope,$mdDialog){
    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $mdDialog.hide(answer);
    };
});