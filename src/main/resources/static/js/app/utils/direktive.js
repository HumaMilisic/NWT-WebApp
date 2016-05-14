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