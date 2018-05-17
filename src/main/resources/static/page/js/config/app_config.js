var memberApp = angular.module('memberApp');
memberApp.run(['$rootScope','$http', function($rootScope, $http){
    // $rootScope.contextPath = 'http://120.79.81.123:8801/member/';
    $rootScope.contextPath = 'http://localhost:8801/member/';
    function inject(item, index) {
        switch (item.name) {
            case "HOMEPAGE_NAME" :
                $rootScope.appName = item.value;
                break;
            case "HOMEPAGE_IMG_1" :
                $rootScope.img1 = item.value;
                break;
            case "HOMEPAGE_IMG_2" :
                $rootScope.img2 = item.value;
                break;
            case "HOMEPAGE_YEAR" :
                $rootScope.year = item.value;
                break;
            default:break;
        }
    }
    (function () {
        $http.get($rootScope.contextPath + "/pagedata/global").then(function(result) {
            var data = result.data;
            data.data.forEach(inject);
        })
    })();
}]);