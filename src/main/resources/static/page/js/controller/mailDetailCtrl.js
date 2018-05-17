memberApp.controller('mailDetailCtrl', ['$scope','$rootScope','$http','$interval','$routeParams','$cookieStore', function($scope, $rootScope, $http, $interval, $routeParams,$cookieStore){
    $scope.listMail = function(){
        $http.get($rootScope.contextPath + '/mail/notifyList').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                alert(data.msg);
            }else{
                $scope.mails = data.data;
                $scope.temp = $scope.mails[$routeParams.index]
            }
        });
    };
    (function () {
        $scope.listMail();
    })();
}]);