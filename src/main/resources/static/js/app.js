var DMApp = angular.module('DMApp', [
    'ngRoute',
    'ngTable'
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
        .otherwise('/');

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

DMApp.controller('korisnikPageController',function($scope,$http,$rootScope,auth){
    $scope.poruka = "korisnikPageController";


})
