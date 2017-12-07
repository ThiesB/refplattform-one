import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('ConsultingDivision e2e test', () => {

    let navBarPage: NavBarPage;
    let consultingDivisionDialogPage: ConsultingDivisionDialogPage;
    let consultingDivisionComponentsPage: ConsultingDivisionComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ConsultingDivisions', () => {
        navBarPage.goToEntity('consulting-division');
        consultingDivisionComponentsPage = new ConsultingDivisionComponentsPage();
        expect(consultingDivisionComponentsPage.getTitle()).toMatch(/refplattformOneApp.consultingDivision.home.title/);

    });

    it('should load create ConsultingDivision dialog', () => {
        consultingDivisionComponentsPage.clickOnCreateButton();
        consultingDivisionDialogPage = new ConsultingDivisionDialogPage();
        expect(consultingDivisionDialogPage.getModalTitle()).toMatch(/refplattformOneApp.consultingDivision.home.createOrEditLabel/);
        consultingDivisionDialogPage.close();
    });

    it('should create and save ConsultingDivisions', () => {
        consultingDivisionComponentsPage.clickOnCreateButton();
        consultingDivisionDialogPage.setDivisionNameInput('divisionName');
        expect(consultingDivisionDialogPage.getDivisionNameInput()).toMatch('divisionName');
        consultingDivisionDialogPage.save();
        expect(consultingDivisionDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ConsultingDivisionComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-consulting-division div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ConsultingDivisionDialogPage {
    modalTitle = element(by.css('h4#myConsultingDivisionLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    divisionNameInput = element(by.css('input#field_divisionName'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setDivisionNameInput = function (divisionName) {
        this.divisionNameInput.sendKeys(divisionName);
    }

    getDivisionNameInput = function () {
        return this.divisionNameInput.getAttribute('value');
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
