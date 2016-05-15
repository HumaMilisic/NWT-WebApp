
DMApp.service('redirekt',function($location){
    var staro = null;

    this.goTo = function(gdje,force){
        var url = $location.path();
        if(url!=gdje){
            staro = staro!=url?url:staro;
            $location.path(gdje);
        }else{
            if(force) $location.path(gdje);
        }
    };

    this.goToHome = function(){
        this.goTo('/');
    };

    this.goToLogin = function(force){
        this.goTo('/login', force);
    };

    this.goToStaro = function(){
        if(staro!=null){
            this.goTo(staro);
        }else
            this.goToHome();
    };
    this.goTo404 = function(){
        this.goTo('/404');
    };
    this.goTo403 = function(){
        this.goTo('/403');
    }

});

DMApp.service('loader',function(usSpinnerService,$rootScope){
    this.startSpin = function(){
        //usSpinnerService.spin('mainSpinner');
        $rootScope.isLoading = true;
        $rootScope.$broadcast('isLoading');
    };
    this.stopSpin = function(){
        //usSpinnerService.stop('mainSpinner');
        $rootScope.isLoading = false;
        $rootScope.$broadcast('isLoading');
    }
});

DMApp.service('widgetRegistration',function(){
    var availableWidgets = {
        text:{},
        doughnutDijagram: {}
    };
});
