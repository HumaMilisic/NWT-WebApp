DMApp.controller('administracijaController', [
    '$scope',
    //"NgTableParams",
    'Resource',
    '$http',
    'loader',
    '$location',
    'auth',
    '$q',
    'SpringDataRestAdapter',
    'Item',
    '$resource',
    function($scope/*,NgTableParams*/,Resource,$http,loader,$location,auth,$q,SpringDataRestAdapter,Item,$resource) {
        //auth.check();
        $scope.main = {};
        $scope.name = "administracija";


        $scope.dummy = function(){
            alert('nije napravljeno');
        };
        //loader.startSpin();

        $scope.maxSize = 5;
        $scope.bigTotalItems = 175;
        $scope.bigCurrentPage = 1;

        $scope.checkModel = {
            left: false,
            middle: true,
            right: false
        };

        $scope.displayed = [];
        $scope.page ={
            size:5,
            totalElements:1
        };

        $scope.entity = null;

        if($scope.childEntity){
            $scope.entity = $scope.childEntity;
        }

        $scope.dajStranicu = function(){
            //loader.startSpin();
            //auth.check();

            var httpPromise = $http.get('/api/'+$scope.entity)
                .success(function(x,y,z){
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a = 0;
                });

            SpringDataRestAdapter.process(httpPromise,'_allLinks').then(function(x,y,z,k){
                var resourceObject = {
                    "name": "self",
                    "parameters": {
                        "size": 20,
                        "sort": "asc"
                    }
                };

                //$scope.ppl = x._resources(resourceObject, paramDefaults, actions, options);
                $scope.displayed = x._embeddedItems;
            });

            $scope.displaySet = function(objekat){
                var rez = "";
                angular.forEach(objekat._embeddedItems,function(item){
                    if(item['naziv']){
                        rez+='['+item['naziv']+'] ';
                    }else if(item['username']){
                        rez+='['+item['username']+'] ';
                    }
                });
                return rez;
            };
            //Resource.getPageBroj($scope.bigCurrentPage,$scope.page.size,$scope.entity).then(function(result){
            //    loader.stopSpin();
            //    if(result.status == 200){
            //        $scope.displayed = result.data;
            //        $scope.page = result.page;
            //        $scope.links = result.links;
            //    }else {
            //        //alert(result.status);
            //    }
            //})
        };

        $scope.dajStranicu();

        $scope.resursTest = $resource("/api/korisnik");
        $scope.dodajUloga = function(){
            $scope.ppl = $scope.resursTest.get(null,function(){
                var a = 8;
            });
            var httpPromise = $http.get('/api/'+$scope.entity)
                .success(function(x,y,z){
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a = 0;
                });

            SpringDataRestAdapter.process(httpPromise,'_allLinks').then(function(x,y,z,k){

                var item = x._resources();
                var a = 0;
                $scope.items = x._embeddedItems;
                //$scope.items[5]._resources()
                angular.forEach(items,function(a){
                    console.log(a.naziv);
                    var t = a._resources()
                })
            });
            $http({
                method:'DELETE',
                url:'/api/uloga/2',
                //headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            });

            //
            //Item.query(function(response){
            //    $scope.items = response ? response :[];
            //},'uloga').then(function(data){
            //
            //})
            //
            //$scope.ppl = $scope.resursTest.query(null,function(x,y,z,k){
            //    var fist = ppl[0];
            //
            //})
            //
            //$scope.newItem = "";
        };



        $scope.dodaj = function(){
            //new Item({
            //    naziv:'test'
            //},'uloga').save(function(item){
            //    //$scope.items.push(item);
            //    var a = 0;
            //});
            //$http({
            //    method:'POST',
            //    url:'/api/uloga',
            //    data:{'naziv':'testunos','deleted':'0'}
            //    //headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            //}).success(function(x,y,z,r,k){
            //    $scope.dajStranicu();
            //}).error(function(x,y,zr,k){
            //    var a =0 ;
            //});
        };

        $scope.dodaj();
        $scope.dodaj();
        $scope.dodaj();
        $scope.dodaj();

        $scope.dodaj2 = function(){
            var korisnik = "http://localhost:8181/api/korisnik/5";//},
    //            {"uri":"http://localhost:8181/api/korisnik/2"}
       //     ];
            $http({
                method:'POST',
                url:'/api/uloga/1/korisnikSet',
                data:korisnik,
                headers: {'Content-Type': 'text/uri-list'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            }).error(function(x,y,zr,k){
                var a =0 ;
            });
        };
        $scope.dodaj3 = function(){
            var korisnik = "http://localhost:8181/api/korisnik/2";//},
            //            {"uri":"http://localhost:8181/api/korisnik/2"}
            //     ];
            $http({
                method:'POST',
                url:'/api/uloga/2/korisnikSet',
                data:korisnik,
                headers: {'Content-Type': 'text/uri-list'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            }).error(function(x,y,zr,k){
                var a =0 ;
            });
        };



        //function getPageBroj(brojStranice,brojNaStranici,tabelaa){
        //    var deferred = $q.defer();
        //    auth.check().then(function(rez){
        //        if(rez.status==200){
        //            //http://localhost:8181/api/korisnik?page=1&size=5
        //            var url = '/api/'+tabelaa+'?page='+(brojStranice-1)+'&size='+brojNaStranici;
        //
        //            $http({
        //                method: 'GET',
        //                url: url,
        //                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        //            })
        //                .success(function (data,status,x,y,z){
        //                    if(data.page!=null && data.page!=undefined){
        //                        //result = data;
        //                        //links = result._links;
        //                        result = data;
        //                        links = result._links;
        //                        page = result.page;
        //                        deferred.resolve({
        //                            data: result._embedded[tabelaa],
        //                            page: result.page,
        //                            links: result._links,
        //                            status: 200
        //                        });
        //                    }else {
        //                        deferred.resolve({
        //                            status: 404
        //                        });
        //                    }
        //
        //                })
        //                .error(function(response,status,nesto,request){
        //                    var a = 0;
        //                    deferred.resolve({
        //                        status:status
        //                    });
        //                })
        //        }else{
        //            deferred.resolve({
        //                status:403
        //            });
        //        }
        //    });
        //    return deferred.promise;
        //}



        $scope.delete = function(objekat,naziv){
            var urlPretraga = '/api/'+$scope.entity+'/search/findByNaziv?naziv='+naziv;
            $http({
                method: 'GET',
                url: urlPretraga
            })
                .success(function(data,status,x,y,z){

                    var obj = data._embedded[$scope.entity];
                    var link = obj[0]._links.self.href;
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
                        });

                    //$scope.dajStranicu();
                })
                .error(function(data,status,x,y,z){
                    var a = 0;
                })
        };

        if($scope.childDelete){
            $scope.delete = $scope.childDelete;
        }


}]);