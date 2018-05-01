var dashboard = angular.module('dashboard', ['ngResource']);

dashboard.factory("adresseAPI", function($resource) {
  return $resource("/api/adresse/:id");
});

dashboard.factory("vnAPI", function($resource) {
  return $resource("/api/versicherungsnehmer/:id");
});

function FeatureFlagsService($q, $timeout) {
    var service = {
        timeoutService: $timeout,
        qService: $q
    };
    service.init = function(){
        return this.loadFeatureFlags();
    };
    service.getFeatureFlags = function(){
        return this.features = this.features || window.__otxp_featureFlags__;
    };
    service.getFeatureFlag = getFeatureFlag;
    service.loadFeatureFlags = loadFeatureFlags;
    return service;
}


dashboard.controller('dashboardController', ['$scope', 'adresseAPI', 'vnAPI', function($scope, adresseAPI, vnAPI) {
    console.log(adresseAPI);
    var ctrl = this;

    $scope.createAdresse = function(adresse) {
        adresseAPI.save(adresse);
    };

    $scope.getVNS = function() {
        return vnAPI.get({});
    }
}]);
