DMApp.directive('breadcrumb',function(){
    return{
        template:"{{crumb}}",
        controller: function($scope,$rootScope,$location){
            $rootScope.$on("$routeChangeSuccess",function(event){
                $scope.crumb = $location.url();
            });
            $rootScope.$on("$routeChangeError",function(event){
                $scope.crumb = $location.url();
            });
        }
    }
});

DMApp.directive('baseWidget',function(){
   return{
       templateUrl:"/js/app/parts/widgets/baseWidget.html",
       controller: function($scope){
           $scope.widgetTemplateUrl ="/js/app/parts/widgets/testWidget.html"
       }
   }
});

DMApp.directive('prevodOdabir',function(){
    return{
        controller: ["$scope","$translate","localStorageService",function($scope,$translate,localStorageService){
            $scope.jezici = ['en-US','bs-Latn-BA'];
            $scope.jezik = $translate.use();

            $scope.changeLanguage = function (langKey) {
                if(langKey==null || typeof (langKey)=='undefined'){
                    langKey='en-US';
                }
                $translate.use(langKey);
                var tempJezik = localStorageService.get('jezik');
                localStorageService.set('jezik',langKey);
                //$scope.jezik = langKey;
            };

            var tempJezik = localStorageService.get('jezik');

            if(tempJezik){
                $scope.jezik = tempJezik;
                $scope.changeLanguage(tempJezik);
            }



            $scope.$watch('jezik',function(newVal,oldVal){
                $scope.changeLanguage(newVal);
            })

        }],
        template:'<md-input-container>'+
        '<md-select ng-model="jezik">'+
        '<md-option ng-repeat="j in jezici" value="{{j}}">{{j}}</md-option>'+
        '</md-select>'+'</md-input-container>'
    }
})