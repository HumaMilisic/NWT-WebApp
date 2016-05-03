DMApp.controller('administracijaDashboardController', [
    '$scope',
    '$controller',
    //'chart.js',
    function($scope, $controller) {
        $scope.main = {};
        $scope.name = "administracijaDashboardController";

        $scope.labelsLine = ["January", "February", "March", "April", "May", "June", "July"];
        $scope.seriesLine = ['Series A', 'Series B'];
        $scope.dataLine = [
            [65, 59, 80, 81, 56, 55, 40],
            [28, 48, 40, 19, 86, 27, 90]
        ];

        $scope.labelsRadar =["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"];

        $scope.dataRadar = [
            [65, 59, 90, 81, 56, 55, 40],
            [28, 48, 40, 19, 96, 27, 100]
        ];

        $scope.labelsPie = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
        $scope.dataPie = [300, 500, 100];

        $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
        $scope.data = [300, 500, 100];

        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };

        //$scope.childEntity = 'korisnik';
        //$controller('administracijaController', { $scope: $scope});
    }
]);