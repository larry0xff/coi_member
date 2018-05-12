var memberApp = angular.module('memberApp');
memberApp.run(['$rootScope', function($rootScope){
    // $rootScope.contextPath = 'http://120.79.81.123:8801/member/';
    $rootScope.contextPath = 'http://localhost:8801/member/';
    $rootScope.appName = "征集系统";
}]);