memberApp.controller('profileCtrl', ['$scope', '$rootScope', '$http', '$timeout', function ($scope, $rootScope, $http, $timeout) {
    function getProfile() {
        $http.get($rootScope.contextPath + '/pagedata/profile').then(function (result) {
            var data = result.data;
            if (data.returnCode != 200) {
                console.log(data.msg);
            } else {
            }
            $scope.pageData = data.data;
        });
    }

    $scope.closeChangePswModal = function () {
        delete $scope.errMsg;
        $('#changePswModal').modal('close');
    };
    $scope.openChangePswModal = function () {
        var $modal = $('#changePswModal');
        var options = {
            height: 370
        };
        $modal.modal(options);
        $modal.modal('open');
    };
    $scope.closeChangeInfoModal = function () {
        delete $scope.errMsg;
        $('#changeInfoModal').modal('close');
        getProfile();
    };
    $scope.openChangeInfoModal = function () {
        var $modal = $('#changeInfoModal');
        var options = {
            height: 370
        };
        $modal.modal(options);
        $modal.modal('open');
    };
    $scope.changePsw = function () {
        delete $scope.errMsg;
        if ($scope.newPsw != $scope.newPsw2) {
            $scope.errMsg = "两次输入不一致"
            return;
        }
        $scope.loading = true;
        $http.post($rootScope.contextPath + '/member/changePsw?oldPsw=' + $scope.oldPsw + '&newPsw=' + $scope.newPsw).then(function (result) {
            var data = result.data;
            if (data.returnCode != 200) {
                $scope.errMsg = data.msg;
                $scope.loading = false;
            } else {
                $scope.errMsg = "修改成功";
                $scope.loading = false;
                $timeout($scope.closeChangePswModal(), 2000);
            }
        });
    }
    $scope.changeInfo = function () {
        delete $scope.errMsg;
        $scope.loading = true;
        $http.post($rootScope.contextPath + '/member/changeInfo?email=' + $scope.pageData.profile.email + '&tel=' + $scope.pageData.profile.tel).then(function (result) {
            var data = result.data;
            if (data.returnCode != 200) {
                $scope.errMsg = data.msg;
                $scope.loading = false;
            } else {
                $scope.errMsg = "修改成功";
                $scope.loading = false;
                $timeout($scope.closeChangeInfoModal(), 2000);
            }
        });
    };
    (function () {
        $scope.oldPsw = "";
        $scope.newPsw2 = "";
        $scope.newPsw = "";
        getProfile();
    })();
    }
]);