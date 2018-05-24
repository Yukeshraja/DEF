var clientAppURL = "https://2-platform.altimetrik.com/DEF/"; // Your App URL
var serverAppURI = "https://2-platform.altimetrik.com/"; //security-service URL

var body = document.getElementsByTagName("head")[0];
body.setAttribute("ng-app", "apiApp");
body.setAttribute("ng-controller", "apiCtrl");

var app = angular.module('apiApp', ['ngStorage']).controller('apiCtrl', function($scope, $compile, $window, $http, $location, $localStorage) {
	$scope.loginUrl = "";
	$scope.userDetails = "";
	$scope.selectedIdpType = "o365Identity";

	function getCode(name) {
		if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search)) {
			return decodeURIComponent(name[1]);
		}
	}

	$scope.authCode = getCode("code");
	$scope.redirectURI = clientAppURL;

	if ($localStorage.o365token !== undefined) {
		$http({
			method : 'POST',
	    	url : serverAppURI + '/security/oauth2/isTokenValid',
			data : {
				'access_token' : $localStorage.o365token,
				'idpType' : $scope.selectedIdpType
			},							
		}).success(
			function(response) {
				if (response === false) {
					$localStorage.$reset();
					$http({
						method : 'POST',
						url : serverAppURI + '/security/oauth2/logout',
						data : {
							'redirect_uri' : $scope.redirectURI,
							'idpType' : $scope.selectedIdpType
						},							
					}).success(
						function(response) {
							$window.location.href = response.loginUrl;
						}).error(function() {
							// catch the log
							console.log("Error occured at logout");
						});
				}
			}).error(function() {
				// catch the log
				console.log("Error occured at getUserDetails");
		});
	} else {
		doLogin();
	}

	function doLogin() {
		if ($scope.authCode === undefined) {
			$http({
				method : 'POST',
				url : serverAppURI + '/security/oauth2/login',
				data : {
					'redirect_uri' : $scope.redirectURI,
					'idpType' : $scope.selectedIdpType
				},							
			}).success(
				function(response) {
					$scope.loginUrl = response.loginUrl;
					$scope.errors = response.errors;
				}).error(function() {
					// catch the log
					console.log("Error occured at login");
				});

			$scope.$watch('loginUrl', function(value) {
				if (value) {
					$window.location.href = value;
				}
			});
			
		} else {
			$http({
				method : 'POST',
				url : serverAppURI + '/security/oauth2/getAccessToken',
				data : {
					'byAuthCode' : true,
					'authCode' : $scope.authCode,
					'redirect_uri' : $scope.redirectURI,
					'idpType' : $scope.selectedIdpType
				},							
			}).success(
				function(response) {
					$scope.token = response.tokenVO.access_token;
					$localStorage.o365token = response.tokenVO.access_token;
					$scope.errors = response.errors;
				}).error(function() {
					// catch the log
					console.log("Error occured at getAccessToken");
			});
		}
	}
});
