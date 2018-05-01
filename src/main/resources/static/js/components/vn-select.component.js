function VnSelectController() {
    var ctrl = this;
}
angular.
  module('dashboard').
  component('vnSelect', {
    templateUrl: 'js/components/vn-select.template.html',
    controller: VnSelectController,
    bindings: {
        vns: '<'
    }
  });