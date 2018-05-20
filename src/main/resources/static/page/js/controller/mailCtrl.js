memberApp.controller('mailCtrl', ['$scope','$rootScope','$http','$interval','$routeParams','$cookieStore', function($scope, $rootScope, $http, $interval, $routeParams,$cookieStore){
    $scope.checkSwitch = function () {
        $http.get($rootScope.contextPath + "/switch/check?name=MAILBOX_MODULE").then(function (result) {
            var data = result.data;
            if(data.returnCode != 200) {
                Materialize.toast(data.msg, 2000);
            } else {
                $scope.sw =  data.data;
            }
        });
    };
    (function(){
        $scope.mail = {toOrgId: $routeParams.orgId, title:"", content:""};
        $scope.orgName = $routeParams.orgName;
        $scope.sended = false;
        $scope.process = 0;
        $scope.checkSwitch();
    })();

    $scope.send = function(){
        if ($scope.sw.status == 1) {
            alert($scope.sw.message);
            return;
        }
        if (!$cookieStore.get("realname")) {
            alert("请先登录！");
            return;
        }
        if (!/\S/.test($scope.mail.content) || !/\S/.test($scope.mail.title)) {
            $scope.errorMsg = "标题和内容都不能为空！";
            return;
        }
        $scope.sended = true;
        $http.post($rootScope.contextPath + '/mail/send', $scope.mail).then(function(result){
            var data = result.data;
            console.log(data);
            if(data.returnCode != 200){
                console.log("出错！")
                $scope.errorMsg = data.msg;
            }else{
                $scope.sendSuccess = true;
            }
        });
    }
}]);