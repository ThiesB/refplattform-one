import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Customers e2e test', () => {

    let navBarPage: NavBarPage;
    let customersDialogPage: CustomersDialogPage;
    let customersComponentsPage: CustomersComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Customers', () => {
        navBarPage.goToEntity('customers');
        customersComponentsPage = new CustomersComponentsPage();
        expect(customersComponentsPage.getTitle()).toMatch(/refplattformOneApp.customers.home.title/);

    });

    it('should load create Customers dialog', () => {
        customersComponentsPage.clickOnCreateButton();
        customersDialogPage = new CustomersDialogPage();
        expect(customersDialogPage.getModalTitle()).toMatch(/refplattformOneApp.customers.home.createOrEditLabel/);
        customersDialogPage.close();
    });

    it('should create and save Customers', () => {
        customersComponentsPage.clickOnCreateButton();
        customersDialogPage.setCustomerNameInput('customerName');
        expect(customersDialogPage.getCustomerNameInput()).toMatch('customerName');
        customersDialogPage.save();
        expect(customersDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class CustomersComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-customers div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CustomersDialogPage {
    modalTitle = element(by.css('h4#myCustomersLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    customerNameInput = element(by.css('input#field_customerName'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setCustomerNameInput = function (customerName) {
        this.customerNameInput.sendKeys(customerName);
    }

    getCustomerNameInput = function () {
        return this.customerNameInput.getAttribute('value');
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
