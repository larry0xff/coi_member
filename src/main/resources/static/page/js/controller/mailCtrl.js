memberApp.controller('mailCtrl', ['$scope','$rootScope','$http','$interval','$routeParams', function($scope, $rootScope, $http, $interval, $routeParams){
    (function(){
        $scope.mail = {toOrgId: $routeParams.orgId};
        $scope.orgName = $routeParams.orgName;
        $scope.sended = false;
        $scope.process = 0;
    })();

    $scope.send = function(){
        $scope.sended = true;
        $http.post($rootScope.contextPath + '/mail/send', $scope.mail).then(function(result){
            var data = result.data;
            console.log(data);
            if(data.returnCode != 200){
                console.log("出错！")
            }else{
                $scope.sendSuccess = true;
            }
        });
    }
}]);