memberApp.controller('loginCtrl', ['$scope', '$rootScope', '$http', '$cookieStore', function($scope, $rootScope, $http, $cookieStore){
    $scope.initVerifyCode = "/member/verifycode?" + Math.random()*1000;
    $scope.closeModal = function(){
        delete $scope.errMsg;
        $('#loginModal').modal('close');
    };
    $scope.login = function(){
        $scope.loading = true;
        $http.post($rootScope.contextPath + '/login?username=' + $scope.username + '&password=' + $scope.password + '&verifyCode=' + $scope.verifyCode).then(function(result){
            var data = result.data;
            if(data.returnCode != 10000){
                $scope.errMsg = data.msg;
                $scope.loading = false;
            }else{
                $scope.loading = false;
                $cookieStore.put('realname', data.data.realname);
                $cookieStore.put('memberId', data.data.igMemberId);
                $rootScope.realname = $cookieStore.get('realname');
                $scope.closeModal();
            }
        });
    }
}]);