DMApp.controller('administracijaKorisnikaController', [
    '$scope',
    '$controller',
    '$http',
    '$location',
    '$mdDialog',
    '$filter',
    function($scope, $controller,$http,$location,$mdDialog,$filter) {
        $scope.ban = function(osoba){
            alert('ban');
        }
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.childEntity = 'korisnik';
        $scope.detalji = function(username){
            $location.path('/admin/korisnik/'+username);
        };

        $scope.ulogeFlag = false;
        $scope.uloge = function(osoba){
            $scope.ulogeFlag = true;
            $scope.trenutni = osoba;
        };

        $scope.resetPasswordMail = function(){
            ///user/resetPassword POST
            if($scope.selected.length>0){
                var user = $scope.selected[0];
                var data = {
                    "username":user.username,
                    email:true
                };
                var url = "/user/resetPassword?username="+user.username+"&email=true";
                var promise = $http({
                    method:'POST',
                    url:url,
                    //data:data
                })
                    .success(function(resource,status, y){
                        var a = 0;
                        $scope.selected = [];
                        $scope.toastMsg(resource.message);
                    })
                    .error(function(x,y,z,k){
                        var a = 0;
                        $scope.toastMsg("error: "+x.message);
                    });
                $scope.promise = promise;
            }
        };

        $scope.newDialogChild = function(event){
            $mdDialog.show({
                //controller: novaUlogaCtrl,
                templateUrl: 'js/app/parts/noviKorisnik.html',
                targetEvent: event
            }).then(function(answer){
                    //$scope.toastMsg(answer);
                    if(answer!=null){
                        answer.deleted = "0";
                        answer.password = "";
                        var url = '/api/'+$scope.entity;
                        $http({
                            method:'POST',
                            data:answer,
                            url:url
                        }).success(function(x,y,z){
                            $scope.toastMsg($filter('translate')('ADDED'));
                            $scope.loadStuff();
                        }).error(function(x,y,z){
                            $scope.toastMsg('Problem');
                        })
                    }
                },
                function(){
                    $scope.toastMsg($filter('translate')('CANCEL'));
                })
        };

        $scope.dodajKorisnikaUUlogu = function(uloga,osoba){
            var korisnik = osoba._links.self.href;
            var url = uloga._links.self.href+'/korisnikSet';
            $http({
                method:'POST',
                url:url,
                data:korisnik,
                headers: {'Content-Type': 'text/uri-list'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            }).error(function(x,y,zr,k){
                var a =0 ;
            });
        };

        $scope.izbaciKorisnikaIzUloge = function(uloga,osoba){
            var korisnik = osoba._links.self.href;
            var t = osoba.ulogaSet._resources();
            var httpUlogaSetPromise = $http.get(osoba._links.ulogaSet.href);

            //var ulogaSet = osoba._links.
            var url = uloga._links.self.href+'/korisnikSet';
            var data = {
                _links: {
                    korisnikSet:
                        [
                            {"href":"http://localhost:8181/api/korisnik/6"},
                            {"href":"http://localhost:8181/api/korisnik/7"},
                            {"href":"http://localhost:8181/api/korisnik/8"}
                        ]}
            };

            $http({
                method:'PUT',
                url:url,
                data:data,
                //headers: {'Content-Type': 'text/uri-list'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            }).error(function(x,y,z,r,k){
                var a =0 ;
            });
        };

        $scope.childDelete = function(objekat,username){
            var urlPretraga = '/api/'+$scope.entity+'/search/findByUsername?username='+username;
            $http({
                method: 'GET',
                url: urlPretraga
            })
                .success(function(data,status,x,y,z){

                    var obj = data;
                    var link = data._links.self.href;
                    var a = 0;

                    $http({
                        method: 'DELETE',
                        url: link
                    })
                        .success(function(data,status){
                            $scope.dajStranicu();
                            //status 204 uspjesno i no content, nema dodatnog sadrzaja
                        })
                        .error(function(x,y,z){
                            var a = 0;
                            //409 constraint, povezan korisnik
                        });

                    //$scope.dajStranicu();
                })
                .error(function(data,status,x,y,z){
                    var a = 0;
                })
        };

        $scope.editModalTemplateUrlChild = 'js/app/parts/editKorisnik.html';

        $controller('administracijaController', { $scope: $scope});
    }
]);

DMApp.controller('administracijaAkcijaController', [
    '$scope',
    '$controller',
    function($scope, $controller) {
        $scope.main = {};
        $scope.name = "naziv!!!akcije";
        $scope.childEntity = 'akcija';
        $controller('administracijaController', { $scope: $scope});
    }
]);

DMApp.controller('administracijaUlogaController', [
    '$scope',
    '$controller',
    '$http',
    '$mdDialog',
    '$filter',
    function($scope, $controller,$http,$mdDialog,$filter) {
        $scope.main = {};
        $scope.name = "naziv!!!uloge";
        $scope.childEntity = 'uloga';

        $scope.selectedAkcije = [];
        $scope.selectedKorisnici = [];

        $scope.queryAkcije = {
            order: 'name',
            limit: 5,
            page: 1
        };

        $scope.queryKorisnici = {
            order: 'name',
            limit: 5,
            page: 1
        };

        $scope.logPaginationUloga = function (page, limit) {
            $scope.getPage(page-1,limit,$scope.entity,$scope.query,$scope.promise);
            $scope.getPage($scope.query.page-1,$scope.query.limit,'akcija',$scope.queryAkcije,$scope.promiseAkcije,"");
            $scope.getPage($scope.query.page-1,$scope.query.limit,'korisnik',$scope.queryKorisnici,$scope.promiseKorisnici,"");
            $scope.selected = [];
            $scope.akcijaSet = {};
            $scope.korisnikSet = {};
            $scope.selectedAkcije = [];
            $scope.selectedKorisnici = [];
        };

        $scope.loadStuffUloga = function(){
            $scope.getPage($scope.query.page-1,$scope.query.limit,$scope.entity,$scope.query,$scope.promise);
            $scope.getPage($scope.query.page-1,$scope.query.limit,'akcija',$scope.queryAkcije,$scope.promiseAkcije,"");
            $scope.getPage($scope.query.page-1,$scope.query.limit,'korisnik',$scope.queryKorisnici,$scope.promiseKorisnici,"");
            $scope.selected = [];
            $scope.akcijaSet = {};
            $scope.korisnikSet = {};
            $scope.selectedAkcije = [];
            $scope.selectedKorisnici = [];
        };

        $scope.paginationKorisnici = function (page, limit) {
            $scope.getPage(page-1,limit,'korisnik',$scope.queryKorisnici,$scope.promiseKorisnici,"");
        };
        $scope.paginationAkcije= function (page, limit) {
            $scope.getPage(page-1,limit,'akcija',$scope.queryAkcije,$scope.promiseAkcije,"");
        };

        $scope.akcijaSet = {};
        $scope.korisnikSet = {};

        $scope.getAkcijeIKorisnikeUloge = function(item){
            var a = 0;
            $scope.akcijaSet = {};
            $scope.korisnikSet = {};
            angular.forEach(item.akcijaSet._embeddedItems,function(item){
                var a = 0;
                $scope.akcijaSet[item.naziv] = {
                    flag:true,
                    href: item._links.self.href
                }
            });
            angular.forEach(item.korisnikSet._embeddedItems,function(item){
                var a = 0;
                $scope.korisnikSet[item.username] = {
                    flag:true,
                    href: item._links.self.href
                }
            });
            //$scope.akcijaSet = item.akcijaSet._embeddedItems;
            //$scope.korisnikSet = item.korisnikSet._embeddedItems;
        };

        $scope.selectAkcije = function(item){
            var a = 0;
        };

        $scope.saveKorisnike = function(){var data = {
            _links:{
                korisnikSet:[]
            }};
            angular.forEach($scope.queryKorisnici.data,function(item){
                if(typeof($scope.korisnikSet[item.username])!='undefined'){
                    $scope.korisnikSet[item.username].flag = false;
                }
            });
            angular.forEach($scope.selectedKorisnici,function(item){
                //if(typeof($scope.akcijaSet[item.naziv])=='undefined'){
                $scope.korisnikSet[item.username] = {
                    flag:true,
                    href: item._links.self.href
                };
                //}
            });

            for (var prop in $scope.korisnikSet) {
                if (!$scope.korisnikSet.hasOwnProperty(prop)) {
                    //The current property is not a direct property of p
                    continue;
                }
                if($scope.korisnikSet[prop].flag){
                    data._links.korisnikSet.push({href:$scope.korisnikSet[prop].href});
                }
                //Do your logic with the property here
            }
            var url = $scope.selected[0]._links.korisnikSet.href;
            if($scope.korisnikSet!={}){
                $http({
                    method:'PUT',
                    data:data,
                    url:url
                }).then(function(x,y,z){
                    var a = 0;
                    $scope.loadStuffUloga();
                });
            }
        };

        $scope.saveAkcije = function(){
            var data = {
                _links:{
                    akcijaSet:[]
                }};
            angular.forEach($scope.queryAkcije.data,function(item){
                if(typeof($scope.akcijaSet[item.naziv])!='undefined'){
                    $scope.akcijaSet[item.naziv].flag = false;
                }
            });
            angular.forEach($scope.selectedAkcije,function(item){
                //if(typeof($scope.akcijaSet[item.naziv])=='undefined'){
                    $scope.akcijaSet[item.naziv] = {
                        flag:true,
                        href: item._links.self.href
                    };
                //}
            });

            for (var prop in $scope.akcijaSet) {
                if (!$scope.akcijaSet.hasOwnProperty(prop)) {
                    //The current property is not a direct property of p
                    continue;
                }
                if($scope.akcijaSet[prop].flag){
                    data._links.akcijaSet.push({href:$scope.akcijaSet[prop].href});
                }
                //Do your logic with the property here
            }
            var url = $scope.selected[0]._links.akcijaSet.href;
            if($scope.akcijaSet!={}){
                $http({
                    method:'PUT',
                    data:data,
                    url:url
                }).then(function(x,y,z){
                    var a = 0;
                    $scope.loadStuffUloga();
                });
            }
        };
        $scope.test = function(){

        };

        $scope.newDialogChild = function(event){
            $mdDialog.show({
                //controller: novaUlogaCtrl,
                templateUrl: 'js/app/parts/novaUloga.html',
                targetEvent: event
            }).then(function(answer){
                    //$scope.toastMsg(answer);
                    if(answer!=null){
                        answer.deleted = "0";
                        var url = '/api/'+$scope.entity;
                        $http({
                            method:'POST',
                            data:answer,
                            url:url
                        }).success(function(x,y,z){
                            $scope.toastMsg($filter('translate')('ADDED'));
                            $scope.loadStuff();
                        }).error(function(x,y,z){
                            $scope.toastMsg('Problem');
                        })
                    }
                },
                function(){
                    $scope.toastMsg($filter('translate')('CANCEL'));
                })
        };

        $scope.editModalTemplateUrlChild = 'js/app/parts/editUloga.html';

        $controller('administracijaController', { $scope: $scope});

        //$scope.dummy();

        //$scope.izvadiAkcije = function(item){
        //    $scope.queryAkcije.data = item.akcijaSet;
        //}
    }
]);