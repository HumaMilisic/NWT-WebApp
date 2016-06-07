DMApp.directive('breadcrumb',function(){
    return{
        template:"<span ng-repeat='link in linkovi'>" +
        "<a ng-href='{{pre(link.link)}}'>&nbsp;&nbsp;{{link.str}}&nbsp;&nbsp;</a>" +
        "<span  ng-show='!$last'>|</span>"+
        "</span>",
        controller: function($scope,$rootScope,$location){
            $scope.linkovi = [];



            $scope.pre = function(str){ return "#\/"+str;};


            var isparcaj = function(){
                $scope.linkovi = [];
                $scope.str = [];
                var full = $scope.crumb.split('\/');
                for(var i=1;i<full.length;i++){
                    var link = {};
                    link.link = full[i];
                    if($scope.linkovi.length>0){
                        link['link'] = $scope.linkovi[$scope.linkovi.length-1].link + "\/"+link.link;
                    }
                    link.str = full[i];
                    $scope.linkovi.push(link);
                }
            }

            $rootScope.$on("$routeChangeSuccess",function(event){
                $scope.crumb = $location.url();
                isparcaj();
            });
            $rootScope.$on("$routeChangeError",function(event){
                $scope.crumb = $location.url();
                isparcaj();
            });
        }
    }
});

DMApp.directive('docListItem',function(){
    return{
        templateUrl: "/js/app/parts/docListItem.html",
        scope:{
            'doc':'=doc',
            'details':'&',
            'delete':'&',
            'edit':'&',
            'ocr':'&',
            'upload':'&',
            'potpis':'&'//,
            //'komentar':'&'
        },
        controller:["$scope", "$http", function($scope, $http){
            $scope.dumdum = function(){
                alert("nije implementirano: "+$scope.doc._links.self.href);
            }

            $scope.potpis = function(fileName){
                //za newDoc i meni
                //alert('potpis');
                $http.post("/sign/" + fileName).then(function(response) {
                        alert("sucess!")
                    },
                    function(response) {
                        //alert("fail!")
                    });
            }

            //$scope.newKomentar = function(){
            //    alert('ef');
            //}

            //
            //$scope.download = function(){
            //    alert("download");
            //}
            //
            $scope.deleteFlag = false;
            if($scope.delete){
                $scope.deleteFlag = true;
            }

            $scope.ocrFlag = false;
            if($scope.ocr){
                $scope.ocrFlag = true;
            }

            $scope.editFlag = false;
            if($scope.edit){
                $scope.editFlag = true;
            }

            $scope.detailsFlag = false;
            if($scope.details){
                $scope.detailsFlag = true;
            }

            $scope.potpisFlag = false;
            if($scope.potpis){
                $scope.potpisFlag = true;
            }

            $scope.uploadFlag = false;
            if($scope.upload){
                $scope.uploadFlag = true;
            }

            //$scope.$apply();
        }]
    }
})

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
        controller: ["$scope","$translate","localStorageService","amMoment",function($scope,$translate,localStorageService,amMoment){
            $scope.jezici = ['en-US','bs-Latn-BA'];
            $scope.jezik = $translate.use();
            //moment.locale(String);
            //amMoment.changeLocale('de');
            $scope.changeLanguage = function (langKey) {
                if(langKey==null || typeof (langKey)=='undefined'){
                    langKey='en-US';
                }
                $translate.use(langKey);
                var tempJezik = localStorageService.get('jezik');
                localStorageService.set('jezik',langKey);
                amMoment.changeLocale(langKey);
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
});

DMApp.directive('dijagram',function(){

})