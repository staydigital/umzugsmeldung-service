angular.
  module('dashboard').
  component('landDd', {
    template:
        '<select id="LandSelector" style="width: 100%;">' +
            '<option ng-repeat="land in $ctrl.laender" value="{{land.isoCode}}">{{land.laenderName}}</option>' +
        '</select>',
    bindings: {
        laender: "="
    },
    controller: function PhoneListController($http) {
      console.log(this.laender)
    }
  });