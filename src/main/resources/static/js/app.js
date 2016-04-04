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
        //.when('/login',{
        //    templateUrl:'login.html'
        //})
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

DMApp.service('auth',function($rootScope,$http){
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
            .finally(function(){
                $rootScope.logovan = false;
                $rootScope.$broadcast('logovan');
            });
    }
})

DMApp.factory('Resource',
    //['$q','$filter','$timeout','tabela'],

    function($q,$filter,$timeout,$http){
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
            //http://localhost:8181/api/korisnik?page=1&size=5
            var url = '/api/'+tabelaa+'?page='+(brojStranice-1)+'&size='+brojNaStranici;
            var deferred = $q.defer();
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

            return deferred.promise;
        };
        function prevPage(){};
        function firstPage(){};
        function lastPage(){};
        return {
            getPage: getPage,
            nextPage: nextPage,
            prevPage: prevPage,
            firstPage: firstPage,
            lastPage: lastPage,
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

DMApp.controller('korisnikPageController',function($scope,$http,$rootScope,auth,$routeParams){
    $scope.name = "korisnikPageController";

    $scope.username = $routeParams.username;

})
