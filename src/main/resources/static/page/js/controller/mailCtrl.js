memberApp.controller('mailCtrl', ['$scope','$rootScope','$http','$interval','$routeParams','$cookieStore', function($scope, $rootScope, $http, $interval, $routeParams,$cookieStore){
    (function(){
        $scope.mail = {toOrgId: $routeParams.orgId};
        $scope.orgName = $routeParams.orgName;
        $scope.sended = false;
        $scope.process = 0;
    })();

    $scope.send = function(){
        if (!$cookieStore.get("realname")) {
            alert("请先登录！");
            return;
        }
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