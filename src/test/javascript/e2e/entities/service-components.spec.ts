import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('ServiceComponents e2e test', () => {

    let navBarPage: NavBarPage;
    let serviceComponentsDialogPage: ServiceComponentsDialogPage;
    let serviceComponentsComponentsPage: ServiceComponentsComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ServiceComponents', () => {
        navBarPage.goToEntity('service-components');
        serviceComponentsComponentsPage = new ServiceComponentsComponentsPage();
        expect(serviceComponentsComponentsPage.getTitle()).toMatch(/refplattformOneApp.serviceComponents.home.title/);

    });

    it('should load create ServiceComponents dialog', () => {
        serviceComponentsComponentsPage.clickOnCreateButton();
        serviceComponentsDialogPage = new ServiceComponentsDialogPage();
        expect(serviceComponentsDialogPage.getModalTitle()).toMatch(/refplattformOneApp.serviceComponents.home.createOrEditLabel/);
        serviceComponentsDialogPage.close();
    });

    it('should create and save ServiceComponents', () => {
        serviceComponentsComponentsPage.clickOnCreateButton();
        serviceComponentsDialogPage.setServiceNameInput('serviceName');
        expect(serviceComponentsDialogPage.getServiceNameInput()).toMatch('serviceName');
        serviceComponentsDialogPage.consultingdivisionSelectLastOption();
        serviceComponentsDialogPage.save();
        expect(serviceComponentsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ServiceComponentsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-service-components div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ServiceComponentsDialogPage {
    modalTitle = element(by.css('h4#myServiceComponentsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    serviceNameInput = element(by.css('input#field_serviceName'));
    consultingdivisionSelect = element(by.css('select#field_consultingdivision'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setServiceNameInput = function (serviceName) {
        this.serviceNameInput.sendKeys(serviceName);
    }

    getServiceNameInput = function () {
        return this.serviceNameInput.getAttribute('value');
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
