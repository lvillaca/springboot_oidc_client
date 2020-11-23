var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope, $http) {



    $scope.logout =function() {

          // TODO - Improve logging out

	     $http({
		method : 'GET',
		withCredentials: true,
		url : '/logoutOIDC'
		}).then(function successCallback(response) {
				  window.location.href = response.data.url;
                                  return true;
			       },function errorCallback(error) {
                                  return false;
			       });
    };


    function getLogin(element) {
            element.user = '';
            
            $http({
                method : 'GET',
                withCredentials: true,
                url : '/hasLogged'
                }).then(function successCallback(response) {
                                   element.user = response.data.user;
                               },function errorCallback(error) {
                                   element.user = '';
                               });
            return true;
    };


    getLogin($scope);

    $scope.setModal = function() {
        $http({
                method : 'GET',
                withCredentials: true,
                url : '/details'
                }).then(function successCallback(response) {
                                   $scope.userdetail = response.data;
                               },function errorCallback(error) {
                                   $scope.userdetail = '';
                               });
	$('#updateModal').modal('show');
    };
    
});
