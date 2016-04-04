var DMApp = angular.module('DMApp', [
    'ngRoute',
    //'ngTable',
    //'smart-table',
    'ui.bootstrap',
    'angularSpinner'
]);

DMApp.config(function($httpProvider,$routeProvider){
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    $routeProvider
        .when('/korisnik',{
            templateUrl:'korisnik.html'
        })
        .when('/login',{
            templateUrl:'login.html'
        })
        .when('/admin/korisnik',{
            templateUrl:'/js/app/admin/views/administracijaKorisnika.html'
        })
        .when('/admin/korisnik/:username',{
            templateUrl:'korisnik.html'
        })
        .otherwise('/');

})

DMApp.service('loader',function(usSpinnerService){
    this.startSpin = function(){
        usSpinnerService.spin('mainSpinner');
    }
    this.stopSpin = function(){
        usSpinnerService.stop('mainSpinner');
    }
})

DMApp.service('redirekt',function($location){
    var staro = null;

    this.goTo = function(gdje){
        var url = $location.path();
        if(url!=gdje){
            staro = staro!=url?url:staro;
            $location.path(gdje);
        }
    }

    this.goToHome = function(){
        this.goTo('/');
    }

    this.goToLogin = function(){
        this.goTo('/login');
    }

    this.goToStaro = function(){
        if(staro!=null){
            this.goTo(staro);
        }
    }
})

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
    }

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
    }
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
    }
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
    }
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
            })

        return deferred.promise;
    }
})

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
                })

            return deferred.promise;
        }
        function nextPage(){
            if(links!=null){
                if(links.next!=undefined){
                    return goTo('GET',links.next.href)
                }
            }
        };
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
        };
        function prevPage(){};
        function firstPage(){};
        function lastPage(){};
        return {
            //getPage: getPage,
            //nextPage: nextPage,
            //prevPage: prevPage,
            //firstPage: firstPage,
            //lastPage: lastPage,
            getPageBroj: getPageBroj
        };
    }
)

DMApp.controller('loginController',function($scope,$http,$rootScope,auth){
    $scope.logovan = $rootScope.logovan;
    $scope.$on('logovan',function(){
        $scope.logovan = $rootScope.logovan;
    })
    var user = {username:'user',password:'user'};
    $scope.login = function(){
        auth.login(user.username,user.password);
    }
    $scope.logout = function(){
        auth.logout();
    }

})

DMApp.controller('korisnikPageController',function($scope,$http,$rootScope,auth,$routeParams,auth){
    $scope.name = "korisnikPageController";

    $scope.username = $routeParams.username;
    if($routeParams.username==undefined){
        auth.getKorisnik().then(function(rez){
            $scope.username = rez.data.username;
        });
        //$scope.username = a.name;
    }
})
