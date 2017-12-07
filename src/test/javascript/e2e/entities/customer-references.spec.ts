import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('CustomerReferences e2e test', () => {

    let navBarPage: NavBarPage;
    let customerReferencesDialogPage: CustomerReferencesDialogPage;
    let customerReferencesComponentsPage: CustomerReferencesComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CustomerReferences', () => {
        navBarPage.goToEntity('customer-references');
        customerReferencesComponentsPage = new CustomerReferencesComponentsPage();
        expect(customerReferencesComponentsPage.getTitle()).toMatch(/refplattformOneApp.customerReferences.home.title/);

    });

    it('should load create CustomerReferences dialog', () => {
        customerReferencesComponentsPage.clickOnCreateButton();
        customerReferencesDialogPage = new CustomerReferencesDialogPage();
        expect(customerReferencesDialogPage.getModalTitle()).toMatch(/refplattformOneApp.customerReferences.home.createOrEditLabel/);
        customerReferencesDialogPage.close();
    });

    it('should create and save CustomerReferences', () => {
        customerReferencesComponentsPage.clickOnCreateButton();
        customerReferencesDialogPage.setTitelInput('titel');
        expect(customerReferencesDialogPage.getTitelInput()).toMatch('titel');
        customerReferencesDialogPage.setProjectTimeSpanInput('5');
        expect(customerReferencesDialogPage.getProjectTimeSpanInput()).toMatch('5');
        customerReferencesDialogPage.setProjectVolumeInput('5');
        expect(customerReferencesDialogPage.getProjectVolumeInput()).toMatch('5');
        customerReferencesDialogPage.setProjectTeamInput('projectTeam');
        expect(customerReferencesDialogPage.getProjectTeamInput()).toMatch('projectTeam');
        customerReferencesDialogPage.setExxetaConsultingAmountInput('5');
        expect(customerReferencesDialogPage.getExxetaConsultingAmountInput()).toMatch('5');
        customerReferencesDialogPage.setReferenceOwnerInput('referenceOwner');
        expect(customerReferencesDialogPage.getReferenceOwnerInput()).toMatch('referenceOwner');
        customerReferencesDialogPage.getExxetaProjectInput().isSelected().then(function (selected) {
            if (selected) {
                customerReferencesDialogPage.getExxetaProjectInput().click();
                expect(customerReferencesDialogPage.getExxetaProjectInput().isSelected()).toBeFalsy();
            } else {
                customerReferencesDialogPage.getExxetaProjectInput().click();
                expect(customerReferencesDialogPage.getExxetaProjectInput().isSelected()).toBeTruthy();
            }
        });
        customerReferencesDialogPage.getFinishedInput().isSelected().then(function (selected) {
            if (selected) {
                customerReferencesDialogPage.getFinishedInput().click();
                expect(customerReferencesDialogPage.getFinishedInput().isSelected()).toBeFalsy();
            } else {
                customerReferencesDialogPage.getFinishedInput().click();
                expect(customerReferencesDialogPage.getFinishedInput().isSelected()).toBeTruthy();
            }
        });
        customerReferencesDialogPage.setSchlagworteInput('schlagworte');
        expect(customerReferencesDialogPage.getSchlagworteInput()).toMatch('schlagworte');
        customerReferencesDialogPage.setContactInput('contact');
        expect(customerReferencesDialogPage.getContactInput()).toMatch('contact');
        customerReferencesDialogPage.setKritischeErfolgsfaktorenInput('kritischeErfolgsfaktoren');
        expect(customerReferencesDialogPage.getKritischeErfolgsfaktorenInput()).toMatch('kritischeErfolgsfaktoren');
        customerReferencesDialogPage.setAnmerkungenInput('anmerkungen');
        expect(customerReferencesDialogPage.getAnmerkungenInput()).toMatch('anmerkungen');
        customerReferencesDialogPage.consultingdivisionSelectLastOption();
        customerReferencesDialogPage.customerSelectLastOption();
        customerReferencesDialogPage.industrySelectLastOption();
        // customerReferencesDialogPage.projectroleSelectLastOption();
        // customerReferencesDialogPage.servicecomponentSelectLastOption();
        customerReferencesDialogPage.save();
        expect(customerReferencesDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class CustomerReferencesComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-customer-references div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CustomerReferencesDialogPage {
    modalTitle = element(by.css('h4#myCustomerReferencesLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    titelInput = element(by.css('input#field_titel'));
    projectTimeSpanInput = element(by.css('input#field_projectTimeSpan'));
    projectVolumeInput = element(by.css('input#field_projectVolume'));
    projectTeamInput = element(by.css('input#field_projectTeam'));
    exxetaConsultingAmountInput = element(by.css('input#field_exxetaConsultingAmount'));
    referenceOwnerInput = element(by.css('input#field_referenceOwner'));
    exxetaProjectInput = element(by.css('input#field_exxetaProject'));
    finishedInput = element(by.css('input#field_finished'));
    schlagworteInput = element(by.css('input#field_schlagworte'));
    contactInput = element(by.css('input#field_contact'));
    kritischeErfolgsfaktorenInput = element(by.css('input#field_kritischeErfolgsfaktoren'));
    anmerkungenInput = element(by.css('input#field_anmerkungen'));
    consultingdivisionSelect = element(by.css('select#field_consultingdivision'));
    customerSelect = element(by.css('select#field_customer'));
    industrySelect = element(by.css('select#field_industry'));
    projectroleSelect = element(by.css('select#field_projectrole'));
    servicecomponentSelect = element(by.css('select#field_servicecomponent'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setTitelInput = function (titel) {
        this.titelInput.sendKeys(titel);
    }

    getTitelInput = function () {
        return this.titelInput.getAttribute('value');
    }

    setProjectTimeSpanInput = function (projectTimeSpan) {
        this.projectTimeSpanInput.sendKeys(projectTimeSpan);
    }

    getProjectTimeSpanInput = function () {
        return this.projectTimeSpanInput.getAttribute('value');
    }

    setProjectVolumeInput = function (projectVolume) {
        this.projectVolumeInput.sendKeys(projectVolume);
    }

    getProjectVolumeInput = function () {
        return this.projectVolumeInput.getAttribute('value');
    }

    setProjectTeamInput = function (projectTeam) {
        this.projectTeamInput.sendKeys(projectTeam);
    }

    getProjectTeamInput = function () {
        return this.projectTeamInput.getAttribute('value');
    }

    setExxetaConsultingAmountInput = function (exxetaConsultingAmount) {
        this.exxetaConsultingAmountInput.sendKeys(exxetaConsultingAmount);
    }

    getExxetaConsultingAmountInput = function () {
        return this.exxetaConsultingAmountInput.getAttribute('value');
    }

    setReferenceOwnerInput = function (referenceOwner) {
        this.referenceOwnerInput.sendKeys(referenceOwner);
    }

    getReferenceOwnerInput = function () {
        return this.referenceOwnerInput.getAttribute('value');
    }

    getExxetaProjectInput = function () {
        return this.exxetaProjectInput;
    }
    getFinishedInput = function () {
        return this.finishedInput;
    }
    setSchlagworteInput = function (schlagworte) {
        this.schlagworteInput.sendKeys(schlagworte);
    }

    getSchlagworteInput = function () {
        return this.schlagworteInput.getAttribute('value');
    }

    setContactInput = function (contact) {
        this.contactInput.sendKeys(contact);
    }

    getContactInput = function () {
        return this.contactInput.getAttribute('value');
    }

    setKritischeErfolgsfaktorenInput = function (kritischeErfolgsfaktoren) {
        this.kritischeErfolgsfaktorenInput.sendKeys(kritischeErfolgsfaktoren);
    }

    getKritischeErfolgsfaktorenInput = function () {
        return this.kritischeErfolgsfaktorenInput.getAttribute('value');
    }

    setAnmerkungenInput = function (anmerkungen) {
        this.anmerkungenInput.sendKeys(anmerkungen);
    }

    getAnmerkungenInput = function () {
        return this.anmerkungenInput.getAttribute('value');
    }

    consultingdivisionSelectLastOption = function () {
        this.consultingdivisionSelect.all(by.tagName('option')).last().click();
    }

    consultingdivisionSelectOption = function (option) {
        this.consultingdivisionSelect.sendKeys(option);
    }

    getConsultingdivisionSelect = function () {
        return this.consultingdivisionSelect;
    }

    getConsultingdivisionSelectedOption = function () {
        return this.consultingdivisionSelect.element(by.css('option:checked')).getText();
    }

    customerSelectLastOption = function () {
        this.customerSelect.all(by.tagName('option')).last().click();
    }

    customerSelectOption = function (option) {
        this.customerSelect.sendKeys(option);
    }

    getCustomerSelect = function () {
        return this.customerSelect;
    }

    getCustomerSelectedOption = function () {
        return this.customerSelect.element(by.css('option:checked')).getText();
    }

    industrySelectLastOption = function () {
        this.industrySelect.all(by.tagName('option')).last().click();
    }

    industrySelectOption = function (option) {
        this.industrySelect.sendKeys(option);
    }

    getIndustrySelect = function () {
        return this.industrySelect;
    }

    getIndustrySelectedOption = function () {
        return this.industrySelect.element(by.css('option:checked')).getText();
    }

    projectroleSelectLastOption = function () {
        this.projectroleSelect.all(by.tagName('option')).last().click();
    }

    projectroleSelectOption = function (option) {
        this.projectroleSelect.sendKeys(option);
    }

    getProjectroleSelect = function () {
        return this.projectroleSelect;
    }

    getProjectroleSelectedOption = function () {
        return this.projectroleSelect.element(by.css('option:checked')).getText();
    }

    servicecomponentSelectLastOption = function () {
        this.servicecomponentSelect.all(by.tagName('option')).last().click();
    }

    servicecomponentSelectOption = function (option) {
        this.servicecomponentSelect.sendKeys(option);
    }

    getServicecomponentSelect = function () {
        return this.servicecomponentSelect;
    }

    getServicecomponentSelectedOption = function () {
        return this.servicecomponentSelect.element(by.css('option:checked')).getText();
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
