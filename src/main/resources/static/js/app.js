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
    //'nsPopover',
    //'mgcrea.ngStrap.core',
    //'mgcrea.ngStrap.helpers.dimensions',
    //'mgcrea.ngStrap.tooltip',
    //'mgcrea.ngStrap.popover'
    'angularMoment',
]);


DMApp.factory("roleFactory",function($rootScope,$q){

    return {
        jeAdmin: function(){
            var rez = $rootScope.jeAdmin;
            if(rez)
                return $q.when(200);
            else return $q.reject(403);

        }
    }
})


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
    $translateProvider.fallbackLanguage('en-US');

    $routeProvider
        .when('/home',{
            templateUrl: 'js/app/home/home.html',
            controller: "homeController"
        })
        .when('/home/korisnik',{
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
        .when('/403',{
            templateUrl: 'js/app/403.html'
        })
        .otherwise({redirectTo:'/home'});

    //administracija
    $routeProvider
        .when('/admin',{
            templateUrl:'/js/app/admin/views/adminDashboard.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/korisnik',{
            templateUrl:'/js/app/admin/views/administracijaKorisnika.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/korisnik/:username',{
            templateUrl:'js/app/korisnik.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/akcija',{
            templateUrl:'/js/app/admin/views/administracijaAkcija.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/uloga',{
            templateUrl:'/js/app/admin/views/administracijaUloga.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/status',{
            templateUrl:'/js/app/admin/views/administracijaStatusa.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/vrstaDokumenta',{
            templateUrl:'/js/app/admin/views/administracijaVrstaDokumenta.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/notifikacija',{
            templateUrl:'/js/app/admin/views/administracijaNotifikacija.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/komentar',{
            templateUrl:'/js/app/admin/views/administracijaKomentara.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/dokument',{
            templateUrl:'/js/app/admin/views/administracijaDokumenta.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/relacijaDokument',{
            templateUrl:'/js/app/admin/views/administracijaRelacijaDokumenta.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/dogadjaj',{
            templateUrl:'/js/app/admin/views/administracijaDogadjaja.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/admin/relacijaKorisnik',{
            templateUrl:'/js/app/admin/views/administracijaRelacijaKorisnik.html',
            resolve:{
                jeAdmin: function(roleFactory){
                    return roleFactory.jeAdmin();
                }
            }
        })
        .when('/user/dokumenti',{
            templateUrl:'/js/app/files.html'

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
                    case 403:{
                        redirekt.goTo403();
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

DMApp.run(["$rootScope","loader","auth","redirekt",function($rootScope,loader,auth,redirekt){
    $rootScope.$on("$routeChangeStart",function(event){
        loader.startSpin();
        auth.check();
    });
    $rootScope.$on("$routeChangeSuccess",function(event){
        loader.stopSpin();
    });
    $rootScope.$on("$routeChangeError",function(event,x,y,z){
        loader.stopSpin();
        if(z==403)
            redirekt.goTo403();
        if(z==401){
            auth.logout();
        }
    });

}]);

DMApp.directive('nwtPopover',function(){
    return {
        restrict: 'A',
        //template: '<span>{{label}}</span>',
        link: function(scope,el,attrs){
            scope.label = attrs.nwtPopoverLabel;
            $(el).popover({
                trigger: 'click',
                html: true,
                content: attrs.nwtPopoverHtml,
                placement: attrs.nwtPopoverPlacement
            });
        }
    }
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






DMApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl, data){
        var fd = new FormData();
        //fd.append('name', file.name);
        fd.append('file', file);
        //debugger;
        if(typeof (data) !== 'undefined') fd.append('data', data);
        $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(data){
                alert("Fajl uspješno poslan!");
                //var chars = data;
                //var bytes = new Array(chars.length);
                //for (var i = 0; i < chars.length; i++) bytes[i] = chars.charCodeAt(i);
                //var blob = new Blob([new Uint8Array(bytes)]);
            })
            .error(function(a, b, c, d, e, f){
                alert("Došlo je do greške prilikom slanja!");
            });
    }
}]);

DMApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

DMApp.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });
                event.preventDefault();
            }
        });
    };
});




DMApp.controller('mainToolbarCtrl',function($scope,$rootScope,auth,$translate,$location){
    //$scope.authenticated = $rootScope.authenticated;
    //$scope.$on('authenticated',function(event,args){
    //    $scope.authenticated = $rootScope.authenticated;
    //});

    //$scope.breadcrumb = $location.url();



    $scope.login = function(){
        auth.login($scope.user);
    };

    $scope.logout = function(){
        auth.logout();
    };

    $scope.popover = {
        "title": "Title",
        "content": "Helloooooooooo"
    };

    if($rootScope.user==null){
        $scope.localUser = {name:""}
    }else{
        $scope.localUser = $rootScope.user;
    }

    $scope.$on("user",function(event){
        if($rootScope.user==null){
            $scope.localUser = {name:""}
        }else{
            $scope.localUser = $rootScope.user;
        }
    });

});

DMApp.controller('loginController',function($scope,$http,$rootScope,auth,$translate,localStorageService,$mdToast,$filter){
    //$scope.logovan = $rootScope.logovan;
    //$scope.$on('logovan',function(){
    //    $scope.logovan = $rootScope.logovan;
    //})
    //$scope.jezik = localStorageService.bind($scope, 'locale');

    //if($routeParams.token){
    //    alert('token: '+$routeParams.token);
    //}

    $scope.loginProgress = false;

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

    //auth.check();
    //$scope.auth();
    $scope.user = {};

    $scope.login = function(){
        $scope.loginProgress = true;
        auth.login($scope.user,function(){
            $scope.loginProgress = false;
        },function(){
            $scope.toastMsg($filter('translate')('LOGIN_FAIL'));
        });
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
        key: '6LeH5CATAAAAAI-do8BItc6h-ogHc4kgJTJ2eye5'
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

    $scope.registerProgress = false;

    $scope.submit = function () {
        var valid;
        $scope.registerProgress = true;

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
                    $scope.registerProgress = false;
                    //alert(data.message);
                })
                .error(function(response,status,nesto,request){
                    var a = 0;
                    $scope.toastMsg(response.message);
                    $scope.registerProgress = false;
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



DMApp.controller('korisnikPageController',function($scope,$http,$rootScope,auth,$routeParams,razmjena,SpringDataRestAdapter,$location,$mdToast){
    $scope.name = "korisnikPageController";

    $scope.username = $routeParams.username;
    $scope.korisnik = null;
    $scope.trenutniKorisnik = $rootScope.korisnik;
    $scope.$on('korisnik',function(event){
        $scope.trenutniKorisnik = $rootScope.korisnik;
    })

    $scope.query={};

    $scope.jeSvoj = function(){
        var path = $location.url();
        return path.indexOf("home")>-1;
    }

    $scope.promjeniPass = function(){
        ///user/resetPassword
        var username = $scope.korisnik.username;
        var mail = false;
        var newPass = $scope.newPass;
        var url = "/user/resetPassword?username="+username+"&email="+mail+"&pass="+newPass;
        var promise = $http.post(url)
            .success(function(x,y,z){
                var a =0;
                $scope.toastMsg("Uspjeh");
            })
            .error(function(x,y,z){
                var a =0;
                $scope.toastMsg('Problem');
            })
            .finally(function(){
                $scope.newPass = null;
                $scope.form.$setPristine();
                $scope.form.newPass.$setPristine();
            })
    }

    $scope.toastMsg = function(text) {
        var pinTo = "bottom right";
        $mdToast.show(
            $mdToast.simple()
                .textContent(text)
                .position(pinTo )
                .hideDelay(3000)
        );
    };

    var getKorisnika =function(username){
        var url = "/api/korisnik/search/findByUsername?username="+username;
        var promise = $http.get(url)
            .success(function(x,y,z,k){
                $scope.korisnik = x;
                var a = 0;
            })
            .error(function(x,y,z,k){
                var a = 0;
            })

        var obrada = SpringDataRestAdapter.process(promise,'_allLinks').then(function(data,x,y,z,k){
            //$scope.query.limit = data.page.size;
            //$scope.query.page = data.page.number+1;
            //$scope.query.totalElements = data.page.totalElements;
            $scope.query.data = data.dokumentSet._embeddedItems;
        });
    }

    if($routeParams.username){
        getKorisnika($routeParams.username);
        $scope.korisnikR = razmjena.getObjekat();
        //razmjena.setObjekat(null);
        //if($scope.korisnik==null){
        //
        //}
    }

    if($routeParams.username==undefined){
        $scope.korisnik = $scope.trenutniKorisnik;
    }
});

DMApp.controller('indexController',function($scope,$rootScope,$translate,$mdSidenav){
    $scope.jeAdmin = $rootScope.jeAdmin;
    $scope.$on('jeAdmin',function(event,args){
        //$rootScope.jeAdmin = false;
        $scope.jeAdmin = $rootScope.jeAdmin;
    });

    $scope.authenticated = $rootScope.authenticated;
    $scope.$on('authenticated',function(event,args){
        $scope.authenticated = $rootScope.authenticated;
    });

    $scope.isLoading = $rootScope.isLoading;
    $scope.$on('isLoading',function(event,args){
        $scope.isLoading = $rootScope.isLoading;
    });


    $scope.navBarVisibleFlag = $mdSidenav('left').isOpen();

    $scope.toggleNavBar = function(id){
        //$scope.isOpenRight = function(){
        //    return $mdSidenav('right').isOpen();
        //};
        $mdSidenav(id).open();
        $scope.navBarVisibleFlag = !$scope.navBarVisibleFlag;
        //.toggle()
        //.then(function () {
        //    $log.debug("toggle " +  " is done");
        //});
    };



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

//DMApp.controller('noviDocHomeModalCtrl',function($scope,$controller,$timeout,randomElem)
DMApp.controller('noviDocHomeModalCtrl',function($scope,$controller, $http){

    $scope.loadStatus = function(){
        //return $timeout(function(){
        //    $scope.statusi = randomElem.nizStatus(5);
        //},650);

        var urlPretraga = '/api/status';
        $scope.statusSet = {};
        $http({
            method: 'GET',
            url: urlPretraga
        })
            .success(function(data,status,x,y,z){
                var obj = data._embedded.status;
                $scope.statusiSet = [];
                angular.forEach(obj, function(value, key){
                    $scope.statusiSet.push({href: value._links.self.href, nazivba: value.nazivba, naziven: value.naziven});
                });
            })
            .error(function(data,status,x,y,z){
                var a = 0;
            })

    }

    $controller('noviItemModalCtrl',{$scope:$scope});
})

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


DMApp.controller('sideNavCtrl',function($scope,navigacijaDozvoljena){
    $scope.linkovi = navigacijaDozvoljena.sve();
    $scope.hashIt = function (url) {
        return "/#"+url;
    }
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

DMApp.controller('editItemModalCtrl',function($scope,$mdDialog){
//    var a=0;
//    var naziv_BA = $scope.selected[0].nazivba;
    $scope.item = $scope.selected[0];
//    $scope.nazivba = naziv_BA;
//    var naziv_EN = $scope.selected[0].naziven;
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

DMApp.controller('userInfoCtrl',function($scope,$rootScope,auth){
    $scope.logout = function(){
        auth.logout();
    };
});