function AdresseFormController() {
    var ctrl = this;

    ctrl.create = function() {
        ctrl.onCreate({adresse : ctrl.adresse});
    }
}
angular.
  module('dashboard').
  component('adresseForm', {
    templateUrl: 'js/components/adresse-form.template.html',
    controller: AdresseFormController,
    bindings: {
        adresse: '<',
        onCreate: '&'
    }
  });