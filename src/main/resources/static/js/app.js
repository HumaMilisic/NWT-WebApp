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

    $routeProvider
        .when('/home',{
            templateUrl: 'js/app/home.html'
        })
        .when('/korisnik',{
            templateUrl:'js/app/korisnik.html'
        })
        .when('/login',{
            templateUrl:'js/app/loginA.html',
        })
        .when('/login/registracija/:token',{
            templateUrl:'js/app/loginA.html'
        })
        .when('/login/resetPassword/:token',{
            templateUrl:'js/app/loginA.html'
        })
        .when('/uitest',{
            templateUrl:'uitest.html'
        })
        .when('/404',{
            templateUrl: 'js/app/404.html'
        })
        .otherwise({redirectTo:'/home'});

    //administracija
    $routeProvider
        .when('/admin',{
            templateUrl:'/js/app/admin/views/adminDashboard.html'
        })
        .when('/admin/korisnik',{
            templateUrl:'/js/app/admin/views/administracijaKorisnika.html'
        })
        .when('/admin/korisnik/:username',{
            templateUrl:'/js/app/admin/views/korisnik.html'
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
        });


    var redirectOnError = ['$q','redirekt','$rootScope',function($q,redirekt,$rootScope){
        var success = function(response){
            return response;
        };
        var error = function(response){
            if(response){
                var url = response.config.url;
                var flag = ~url.indexOf('username=admin');
                if(flag){
                    return $q.reject(response);
                }

                switch (response.status){
                    case 404:{
                        redirekt.goTo404();
                        return $q.reject(response);
                    }
                    case 401:{
                        redirekt.goToLogin();
                        return $q.reject(response);
                    }
                    default:{
                        return $q.reject(response);
                    }
                }
            }
        };

        return {
            'responseError' :function(rejection){
                return error(rejection);
            }
        }
    }];

    $httpProvider.interceptors.push(redirectOnError);
});

//DMApp.factory("UserProfile",function($http,$q){
//    "use strict";
//
//    var getPromise = $http.get("/user")
//        .success(function(x,y,z){
//            var a = 0;
//        })
//        .error(function(x,y,z){
//            var a = 0;
//        });
//    return getPromise;
//    //return $resource("user");
//})

DMApp.factory("Access",function($q,auth){
    "use strict";

    var Access = {
        OK: 200,
        UNAUTHORISED: 401,
        FORBIDDEN: 403,

        hasRole: function(role){
            var deferred = $q.defer();
            deferred.reject(Access.UNAUTHORISED);
            return deferred.promise;
        },

        isAnon: function(){
            var deferred = $q.defer();
            //deferred.reject(Access.FORBIDDEN);
            deferred.resolve(Access.OK);
            return deferred.promise;
        }
    };

    return Access;
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
    };
    this.goTo404 = function(){
        this.goTo('/404');
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
DMApp.factory('auth',function($http,$rootScope,$location,SpringDataRestAdapter,redirekt){
    var user = null;
    var korisnik = null;

    var getKorisnik = function(username){
        var url = '/api/korisnik/search/findByUsername?username='+username;
        var promise = $http.get(url)
            .success(function(x,y,z){
                var a = 0;
            })
            .error(function(x,y,z){
                var a = 0;
            });
        SpringDataRestAdapter.process(promise,'_allLinks').then(function(resurs,y,z,k){
            var a = 0;
            korisnik = resurs;
            $rootScope.korisnik = korisnik;
            $rootScope.$broadcast('korisnik');
        })
    };

    var checkUser = function(user,callback){
        var headers = {};

        if(user!=null && typeof (user)!='undefined'){
            headers = {authorization:"Basic " + btoa(user.username+":"+user.password)};
        }

        $http.get('user',{headers:headers})
            .then(function(response){
                if(response.data.name){
                    $rootScope.authenticated = true;
                    $rootScope.$broadcast('authenticated');
                    user = response.data;
                    if(korisnik==null)
                        getKorisnik(user.name);
                }else{
                    $rootScope.authenticated = false;
                    $rootScope.$broadcast('authenticated');
                }
                callback && callback();
            },function(x,y,z,k){
                $rootScope.authenticated = false;
                $rootScope.$broadcast('authenticated');
                callback && callback();
            })
    };

    var login = function(user){
        checkUser(user,function(){
            if($rootScope.authenticated){
                checkUser(user,function(){
                    if($rootScope.authenticated){
                        //$location.path("/");
                        redirekt.goToHome();
                    }else{
                        //$location.path("login");
                        redirekt.goToLogin();
                    }
                })
            }else{
                //$location.path("login");
                redirekt.goToLogin();
            }
        })
    };

    var logout = function(){
        $http.post('logout',{})
            .success(function(x,y,z){
                var a = 0;
                korisnik = null;
                user = null;
                $rootScope.$broadcast('korisnik');
            })
            .error(function(x,y,z){
                var a  = 0;
            })
            .finally(function(){
                $rootScope.authenticated = false;
                $rootScope.$broadcast('authenticated');
                //$location.path("/login");
                redirekt.goToLogin();
            })
    };
    return{
        check: checkUser,
        login: login,
        logout: logout,
        user: user,
        korisnik: korisnik
    }
});


DMApp.controller('loginController',function($scope,$http,$rootScope,auth,$translate,localStorageService){
    //$scope.logovan = $rootScope.logovan;
    //$scope.$on('logovan',function(){
    //    $scope.logovan = $rootScope.logovan;
    //})
    //$scope.jezik = localStorageService.bind($scope, 'locale');

    //if($routeParams.token){
    //    alert('token: '+$routeParams.token);
    //}

    $scope.toastMsg = function(text) {
        var pinTo = "bottom right";
        $mdToast.show(
            $mdToast.simple()
                .textContent(text)
                .position(pinTo )
                .hideDelay(3000)
        );
    };

    $scope.authenticated = $rootScope.authenticated;
    $scope.$on('authenticated',function(event,args){
        $scope.authenticated = $rootScope.authenticated;
    });

    $scope.korisnik = auth.korisnik;
    $scope.$on('korisnik',function(event,args){
        $scope.korisnik = $rootScope.korisnik;
        //$scope.authenticated = $rootScope.korisnik?true:false;
    });

    $scope.registracijaFlag = false;
    $scope.resetMailFlag = false;
    //$scope.toggle($scope.registracijaFlag);
    $scope.toggle = function(value){
        value = !value;
    };

    $scope.sendMail = function(){
        //alert("test: "+$scope.user.email);
        var url = "/resetPassword?email="+$scope.user.email;
        //$http.get(url)
        //    .success(function(x,status,z){
        //        var a = 0;
        //        if(status===202){
        //            $scope.toastMsg('korisnik aktiviran');
        //        }
        //    })
        //    .error(function(x,y,x){
        //        var a=0;
        //        $scope.toastMsg('problem sa tokenom');
        //    })
        $http.post(url)
            .success(function(data, status, x){
                var a = 0;
                if(status===201){
                    $scope.toastMsg(data.message);
                }
                //alert(data.message);
            })
            .error(function(response,status,nesto,request){
                var a = 0;
                $scope.toastMsg(response.message);
                //alert(response.message);
                console.log('Failed validation');

                // In case of a failed validation you need to reload the captcha
                // because each response can be checked just once
                vcRecaptchaService.reload($scope.widgetId);
            })
    };

    $scope.toogleResetMail = function(){
        $scope.resetMailFlag = !$scope.resetMailFlag;
    };

    $scope.toggleRegistracija = function(){
        $scope.registracijaFlag = !$scope.registracijaFlag;
    };

    $scope.changeLanguage = function (langKey) {
        if(langKey==null || typeof (langKey)=='undefined'){
            langKey='en-US';
        }
        $translate.use(langKey);
        $scope.jezik = langKey;
    };
    //
    //$scope.auth = function(user,callback){
    //    var headers = user ? {authorization:"Basic " + btoa(user.username+":"+user.password)}:{};
    //
    //    $http.get('user',{headers:headers})
    //        .then(function(response){
    //        if(response.data.name){
    //            $rootScope.authenticated = true;
    //        }else{
    //            $rootScope.authenticated = false;
    //        }
    //        callback && callback();
    //    },function(){
    //        $rootScope.authenticated = false;
    //        callback && callback();
    //    })
    //}

    auth.check();
    //$scope.auth();
    $scope.user = {};

    $scope.login = function(){
        auth.login($scope.user);
    };

    $scope.logout = function(){
        auth.logout();
    };
    //$scope.changeLanguage($scope.jezik);

    //$scope.logCheck = function(){
    //        auth.jeLogovan().then(function(data){
    //        $scope.logovan = data.logovan;
    //        auth.getKorisnik().then(function(data){
    //            $scope.korisnik = data.data;
    //        });
    //});};
    //var user = {username:'user',password:'user'};
    //
    //$scope.korisnik = {
    //    username:'username'
    //};

    //$scope.login = function(){
    //    auth.login(user.username,user.password);
    //    $scope.logCheck();
    //};
    //$scope.logout = function(){
    //
    //    auth.logout();
    //    //$scope.logovan = auth.jeLogovan();
    //};
    //$scope.logCheck();
});

DMApp.directive('nwtMeni',function(){
    return{
        templateUrl: '/js/app/parts/meni.html',
        controller: 'loginController'
    }
});

//DMApp.directive('formaRegistracija',function(){
//    return{
//        templateUrl: 'registracija.html',
//    }
//});

DMApp.controller('registracijaController', function ($scope, vcRecaptchaService,$http,$mdToast,$routeParams,$location) {
    console.log("this is your app's controller");
    $scope.user = {};
    $scope.response = null;
    $scope.widgetId = null;

    $scope.toastMsg = function(text) {
        var pinTo = "bottom right";
        $mdToast.show(
            $mdToast.simple()
                .textContent(text)
                .position(pinTo )
                .hideDelay(3000)
        );
    };

    $scope.registrationConfirm = function(token){
        var url = '/registrationConfirm?'+token;
        $http.get(url)
            .success(function(x,status,z){
                var a = 0;
                if(status===202){
                    $scope.toastMsg('korisnik aktiviran');
                }
            })
            .error(function(x,y,x){
                var a=0;
                $scope.toastMsg('problem sa tokenom');
            })
    };

    $scope.resetPassword = function(token){
        var url = '/resetPassword?'+token;
        $http.get(url)
            .success(function(x,status,z){
                var a = 0;
                if(status===202){
                    $scope.toastMsg('potvrda');
                }
            })
            .error(function(x,y,x){
                var a=0;
                $scope.toastMsg('problem sa tokenom');
            })
    };

    if($routeParams.token){
        //alert('token: '+$routeParams.token);
        var url = $location.path();
        //token i tokenr
        if(url.indexOf("registracija/token=")>-1){
            $scope.registrationConfirm($routeParams.token);
        }else if(url.indexOf("resetPassword/token=")>-1){
            $scope.resetPassword($routeParams.token);
        }
        //resetPassword
    }



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
                    if(status===201){
                        $scope.toastMsg(data.message);
                    }
                    //alert(data.message);
                })
                .error(function(response,status,nesto,request){
                    var a = 0;
                    $scope.toastMsg(response.message);
                    //alert(response.message);
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

DMApp.controller('indexController',function($scope,$rootScope,$translate,$mdSidenav){
    $scope.authenticated = $rootScope.authenticated;
    $scope.$on('authenticated',function(event,args){
        $scope.authenticated = $rootScope.authenticated;
    });

    $scope.jezici = ['en-US','bs-Latn-BA'];
    $scope.jezik = 'en-US';

    $scope.toggleNavBar = function(){
        //$scope.isOpenRight = function(){
        //    return $mdSidenav('right').isOpen();
        //};
        //$mdSidenav("left")
        //    .toggle()
        //    .then(function () {
        //        $log.debug("toggle " +  " is done");
        //    });
    };

    $scope.changeLanguage = function (langKey) {
        if(langKey==null || typeof (langKey)=='undefined'){
            langKey='en-US';
        }
        $translate.use(langKey);
        //$scope.jezik = langKey;
    };

    $scope.$watch('jezik',function(newVal,oldVal){
        $scope.changeLanguage(newVal);
    })
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

DMApp.controller('noviItemModalCtrl',function($scope,$mdDialog){
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

DMApp.controller('SubheaderAppCtrl', function($scope) {
    var imagePath = 'img/list/60.jpeg';
    $scope.messages = [
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
        {
            face : imagePath,
            what: 'Brunch this weekend?',
            who: 'Min Li Chan',
            when: '3:08PM',
            notes: " I'll be in your neighborhood doing errands"
        },
    ];
});